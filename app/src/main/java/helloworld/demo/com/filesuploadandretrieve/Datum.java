package helloworld.demo.com.filesuploadandretrieve;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;


public class Datum {


        @SerializedName("candidate_id")
        @Expose
        private String candidateId;
        @SerializedName("name")
        @Expose
        private String name;
        @SerializedName("education")
        @Expose
        private String education;
        @SerializedName("experience")
        @Expose
        private String experience;
        @SerializedName("contact_no")
        @Expose
        private String contactNo;
        @SerializedName("location_id")
        @Expose
        private String locationId;
        @SerializedName("location_name")
        @Expose
        private String locationName;
        @SerializedName("keyskills")
        @Expose
        private String keyskills;
        @SerializedName("designation")
        @Expose
        private String designation;
        @SerializedName("notice_period")
        @Expose
        private String noticePeriod;
        @SerializedName("np_negotiable")
        @Expose
        private String npNegotiable;
        @SerializedName("ctc")
        @Expose
        private String ctc;
        @SerializedName("ectc")
        @Expose
        private String ectc;
        @SerializedName("industry_id")
        @Expose
        private String industryId;
        @SerializedName("industry_name")
        @Expose
        private String industryName;
        @SerializedName("photo")
        @Expose
        private String photo;
        @SerializedName("resume")
        @Expose
        private String resume;
        @SerializedName("remarks")
        @Expose
        private String remarks;

        public String getCandidateId() {
            return candidateId;
        }

        public void setCandidateId(String candidateId) {
            this.candidateId = candidateId;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getEducation() {
            return education;
        }

        public void setEducation(String education) {
            this.education = education;
        }

        public String getExperience() {
            return experience;
        }

        public void setExperience(String experience) {
            this.experience = experience;
        }

        public String getContactNo() {
            return contactNo;
        }

        public void setContactNo(String contactNo) {
            this.contactNo = contactNo;
        }

        public String getLocationId() {
            return locationId;
        }

        public void setLocationId(String locationId) {
            this.locationId = locationId;
        }

        public String getLocationName() {
            return locationName;
        }

        public void setLocationName(String locationName) {
            this.locationName = locationName;
        }

        public String getKeyskills() {
            return keyskills;
        }

        public void setKeyskills(String keyskills) {
            this.keyskills = keyskills;
        }

        public String getDesignation() {
            return designation;
        }

        public void setDesignation(String designation) {
            this.designation = designation;
        }

        public String getNoticePeriod() {
            return noticePeriod;
        }

        public void setNoticePeriod(String noticePeriod) {
            this.noticePeriod = noticePeriod;
        }

        public String getNpNegotiable() {
            return npNegotiable;
        }

        public void setNpNegotiable(String npNegotiable) {
            this.npNegotiable = npNegotiable;
        }

        public String getCtc() {
            return ctc;
        }

        public void setCtc(String ctc) {
            this.ctc = ctc;
        }

        public String getEctc() {
            return ectc;
        }

        public void setEctc(String ectc) {
            this.ectc = ectc;
        }

        public String getIndustryId() {
            return industryId;
        }

        public void setIndustryId(String industryId) {
            this.industryId = industryId;
        }

        public String getIndustryName() {
            return industryName;
        }

        public void setIndustryName(String industryName) {
            this.industryName = industryName;
        }

        public String getPhoto() {
            return photo;
        }

        public void setPhoto(String photo) {
            this.photo = photo;
        }

        public String getResume() {
            return resume;
        }

        public void setResume(String resume) {
            this.resume = resume;
        }

        public String getRemarks() {
            return remarks;
        }

        public void setRemarks(String remarks) {
            this.remarks = remarks;
        }

    }



