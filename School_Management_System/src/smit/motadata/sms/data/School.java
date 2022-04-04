package smit.motadata.sms.data;

public class School {

       public String schoolName;
       public String schoolAddress;

        School(){
            this.schoolName = "A-one school";
            this.schoolAddress = "Address";
        }
        School(String schoolAddress){
            this.schoolName = "xyz school";
            this.schoolAddress = "xyz address";
        }
        public void setSchoolName(String schoolName) {
            this.schoolName = schoolName;
        }

        public void setSchoolAddress(String schoolAddress) {
            this.schoolAddress = schoolAddress;
        }


}
