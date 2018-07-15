package helloworld.demo.com.filesuploadandretrieve;

import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;

public interface GetUserClient {



    @Multipart

    @POST("apiretriveprofile.php")

    Call<Results> retrieveAccount(

            @Part("candidate_id") RequestBody id


    );
}
