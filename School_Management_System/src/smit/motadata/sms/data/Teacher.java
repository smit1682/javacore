package smit.motadata.sms.data;

public class Teacher {

    private String teacherName;
    private int teacherAge;
    private final int teacherId;
    private static int count = 1;
    Teacher(String teacherName,int teacherAge){
        this.teacherName = teacherName;
        this.teacherAge = teacherAge;
        this.teacherId = count;
        count++;
    }

    public String getTeacherName() {
        return teacherName;
    }
    public int getTeacherAge() {
        return teacherAge;
    }
    public int getTeacherId() {
        return teacherId;
    }

    public void setName(String teacherName){
        this.teacherName = teacherName;
    }
    public void setAge(int teacherAge){
        this.teacherAge = teacherAge;
    }

}
