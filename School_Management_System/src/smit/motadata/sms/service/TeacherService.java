package smit.motadata.sms.service;

import smit.motadata.sms.data.Teacher;
import smit.motadata.sms.repository.DataBaseOfTeacher;

import java.util.HashMap;
import java.util.regex.Pattern;

public class TeacherService {
    static DataBaseOfTeacher dataBaseOfTeacher = new DataBaseOfTeacher();

    public static void insertToDatabase(Integer id, Teacher teacher) {

        dataBaseOfTeacher.insert(id, teacher);
    }

    public static HashMap<Integer, Teacher> readAllFromDatabase() {
        return dataBaseOfTeacher.readAll();
    }

    public static HashMap<Integer, Teacher> readByRegex(String filter) {
        filter = "^" + filter + "\\w+[ \\w]*";
        System.out.println(filter);
        Pattern filterPattern = Pattern.compile(filter);
        return DataBaseOfTeacher.readByFilter(filterPattern);
    }

    public static Boolean updateStudentByID(Integer id, String name) {
        return dataBaseOfTeacher.updateNameById(id, name);
    }

}

