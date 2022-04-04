package smit.motadata.sms.repository;

import smit.motadata.sms.data.Student;

import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class DataBaseOfStudent implements Crud<Student> {
    static HashMap<Integer, Student> database = new HashMap<>();

    public  void insert(Integer id, Student student)
    {
        database.put(id,student);
    }


    public static  Student readByid(Integer id){
        return database.get(id);
    }

    public  HashMap<Integer,Student> readAll(){
        Student student = database.get(1);



        return database;
    }

    public static HashMap<Integer, Student> readByFilter(Pattern filterPattern){
        HashMap<Integer,Student> outputmap = new HashMap<>();
        for (Map.Entry<Integer,Student> s : database.entrySet()) {
            Matcher matcher = filterPattern.matcher(s.getValue().getStudentName());
            if (matcher.find())
            {
                outputmap.put(s.getKey(),s.getValue());
            }
        }
        return outputmap;
    }


    public boolean updateNameById(Integer id,String name){
        if( database.containsKey(id))
        {
            database.remove(id);
            return true;
        }
        return false;
    }


}
