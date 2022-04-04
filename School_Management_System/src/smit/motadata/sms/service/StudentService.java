package smit.motadata.sms.service;

import smit.motadata.sms.data.Student;
import smit.motadata.sms.repository.DataBaseOfStudent;

import java.util.HashMap;
import java.util.regex.Pattern;


public class StudentService {
    static DataBaseOfStudent dataBaseOfStudent = new DataBaseOfStudent();

    public static void insertToDatabase(Integer id, Student student) {

        dataBaseOfStudent.insert(id, student);
    }

    public static HashMap<Integer, Student> readAllFromDatabase() {
        return dataBaseOfStudent.readAll();
    }

    public static HashMap<Integer, Student> readByRegex(String filter) {
        filter = "^" + filter + "\\w+[ \\w]*";
        System.out.println(filter);
        Pattern filterPattern = Pattern.compile(filter);
        return DataBaseOfStudent.readByFilter(filterPattern);
    }

    public static Boolean updateStudentByID(Integer id, String name) {
        return dataBaseOfStudent.updateNameById(id, name);
    }

}
