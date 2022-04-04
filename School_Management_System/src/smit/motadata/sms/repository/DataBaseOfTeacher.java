package smit.motadata.sms.repository;

import smit.motadata.sms.data.Teacher;

import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class DataBaseOfTeacher implements Crud<Teacher> {
    static HashMap<Integer, Teacher> database = new HashMap<>();

    public void insert(Integer id, Teacher teacher)
    {
        database.put(id,teacher);
    }


    public static  Teacher readByid(Integer id){
        return database.get(id);
    }

    public   HashMap<Integer,Teacher> readAll(){
        return database;
    }

    public static HashMap<Integer, Teacher> readByFilter(Pattern filterPattern){
        HashMap<Integer,Teacher> outputmap = new HashMap<>();
        for (Map.Entry<Integer,Teacher> s : database.entrySet()) {
            Matcher matcher = filterPattern.matcher(s.getValue().getTeacherName());
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
