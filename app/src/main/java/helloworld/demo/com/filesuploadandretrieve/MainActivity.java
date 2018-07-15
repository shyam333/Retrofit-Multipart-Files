package helloworld.demo.com.filesuploadandretrieve;

import android.Manifest;
import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.media.Image;
import android.net.Uri;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.google.gson.Gson;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.File;

import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {

    private static final int MY_PERMISSIONS_REQUEST_READ_MEDIA = 3;
    private static final int PICK_IMAGE_REQUEST = 1;
    private static final int PICK_PDF_REQUEST = 2;
    String filePath1,filePath2,strimg,strpdf;
    Button button1,button2,button3,button4;
    ImageView imageView;
    TextView textView;
    private File file1,file2;

    String PICTURE_BASE_URL = "http://www.godigitell.in/dev/hrportal/uploads/profile_picture/";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        button1 = (Button)findViewById(R.id.btn1);
        button2 = (Button)findViewById(R.id.btn2);
        button3 = (Button)findViewById(R.id.btn3);
        button4 = (Button)findViewById(R.id.btn4);
        imageView = (ImageView)findViewById(R.id.img);
        textView = (TextView)findViewById(R.id.txt1);

        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                permissionCheck();

            }
        });

        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                readPdfFileDataExternal();
            }
        });

        button3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                sendNetworkRequest();
            }
        });

        button4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                retrieveRequest();

            }
        });

    }

    private void permissionCheck() {

        int permissionCheck = ContextCompat.checkSelfPermission(this, Manifest.permission.READ_EXTERNAL_STORAGE);

        if (permissionCheck != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.READ_EXTERNAL_STORAGE}, MY_PERMISSIONS_REQUEST_READ_MEDIA);
        } else {
            readImageFileDataExternal();
        }
    }

    private void readImageFileDataExternal() {

        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);//
        startActivityForResult(Intent.createChooser(intent, "Select File"), PICK_IMAGE_REQUEST);

    }

    private void readPdfFileDataExternal() {

        Intent intent = new Intent();
        intent.setType("application/pdf");
        intent.setAction(Intent.ACTION_GET_CONTENT);//
        startActivityForResult(Intent.createChooser(intent, "Select File"), PICK_PDF_REQUEST);

    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String permissions[], int[] grantResults) {
        switch (requestCode) {
            case MY_PERMISSIONS_REQUEST_READ_MEDIA:
                if ((grantResults.length > 0) && (grantResults[0] == PackageManager.PERMISSION_GRANTED)) {
                    readImageFileDataExternal();
                    readPdfFileDataExternal();
                }
                break;

            default:
                break;
        }
    }

        @Override
        protected void onActivityResult(int requestCode, int resultCode, Intent data) {

            switch(requestCode){
                case PICK_IMAGE_REQUEST:
                    if (resultCode == Activity.RESULT_OK) {

                        Uri picUri = data.getData();

                        filePath1 = RealPathUtil.getFilePathFromURI(getApplicationContext(), picUri);

                        imageView.setImageURI(picUri);

                        file1 = new File(filePath1);
                    }
                    break;
                case PICK_PDF_REQUEST:

                    Uri pdfUri = data.getData();

                    filePath2 = RealPathUtil.getFilePathFromURI(getApplicationContext(), pdfUri);

                    file2 = new File(filePath2);

                    textView.setText(file2.getName());

                    break;
            }
        }


    private void sendNetworkRequest() {

        String id = "34457495";

        Retrofit.Builder builder = new Retrofit.Builder()
                .baseUrl("http://www.godigitell.in/dev/hrportal/")
                .addConverterFactory(GsonConverterFactory.create());

        Retrofit retrofit = builder.build();

        UserClient client = retrofit.create(UserClient.class);

        RequestBody idrequest = RequestBody.create(MediaType.parse("text/plain"),id);
        MultipartBody.Part filepart1 = MultipartBody.Part.createFormData("photo",file1.getName(), RequestBody.create(MediaType.parse("image/*"),file1));
        MultipartBody.Part filepart2 = MultipartBody.Part.createFormData("resume",file2.getName(),RequestBody.create(MediaType.parse("application/pdf/*"),file2));
        Call<User> call = client.createAccount(idrequest,filepart1,filepart2);


        call.enqueue(new Callback<User>() {
            @Override
            public void onResponse(Call<User> call, Response<User> response) {
                Toast.makeText(MainActivity.this,"Success",Toast.LENGTH_SHORT).show();
                Log.d("RESPONSE",response.toString());
            }

            @Override
            public void onFailure(Call<User> call, Throwable t) {
                Toast.makeText(MainActivity.this,"Not Success",Toast.LENGTH_SHORT).show();
            }
        });

    }

    private void retrieveRequest() {

        String id = "34457495";

        Retrofit.Builder builder = new Retrofit.Builder()
                .baseUrl("http://www.godigitell.in/dev/hrportal/")
                .addConverterFactory(GsonConverterFactory.create());

        Retrofit retrofit = builder.build();

        GetUserClient client = retrofit.create(GetUserClient.class);

        RequestBody idrequest = RequestBody.create(MediaType.parse("text/plain"),id);

        Call<Results> call = client.retrieveAccount(idrequest);

        call.enqueue(new Callback<Results>() {
            @Override
            public void onResponse(Call<Results> call, Response<Results> response){

                Toast.makeText(MainActivity.this, "Retrieve Success", Toast.LENGTH_SHORT).show();

                String resultJSONResponse = new Gson().toJson(response.body());
                // Log.e("TAG", "response 33: "+new Gson().toJson(response.body()) );
                try {
                    JSONObject jsonObject = new JSONObject(resultJSONResponse);

                    JSONArray jsonArray = jsonObject.getJSONArray("data");

                    for (int i = 0; i < jsonArray.length(); i++)
                    {
                        JSONObject o = jsonArray.getJSONObject(i);

                        strimg = o.getString("photo");
                        strpdf = o.getString("resume");

                        showMethod();

                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }

            @Override
            public void onFailure(Call<Results> call, Throwable t) {

                Toast.makeText(MainActivity.this,"Retrieve Not Success",Toast.LENGTH_SHORT).show();
            }
        });


    }

    private void showMethod() {

        String Picture = PICTURE_BASE_URL + strimg;

        loadImageFromInternetUrl(Picture);

        String newstr = strpdf.replaceAll("[^A-Za-z]+", "");

        textView.setText(newstr);

    }

    private void loadImageFromInternetUrl(String picture) {

        Glide

                .with(MainActivity.this)
                .load(picture)
                .into(imageView);

    }


}
