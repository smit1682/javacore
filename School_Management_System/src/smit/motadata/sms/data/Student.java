package smit.motadata.sms.data;


import smit.motadata.sms.util.Methods;

public class Student extends School {

    Methods methods = new Methods();
    private String studentName;
    private int studentAge;
    private final int studentId;
    private static int count=1;
    private String studentEmail;

    public Student(){
        super();
        this.studentId = count;
        count++;
    }

    public Student(String studentName, int studentAge){
        this.studentName = studentName;
        this.studentAge = studentAge;
        this.studentId = count;
        count++;
    }

   public Student(String studentName,int studentAge,String schoolName)
    {
        super(schoolName);
        this.studentName = studentName;
        this.studentAge = studentAge;
        this.studentId = count;
        count++;
    }

    public String getStudentName() {
        return studentName;
    }

    public int getStudentAge() {
        return studentAge;
    }

    public int getStudentId() {
        return studentId;
    }

    public void setName(String studentName){
        this.studentName = studentName;
    }

    public void setAge(int studentAge){
        this.studentAge = studentAge;
    }

    public Boolean setEmail(String email) {
         if(methods.validMail(email)){
             studentEmail = email;
             return true;
         }
         return false;

    }
}
