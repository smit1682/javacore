package smit.motadata.sms.repository;

import java.util.HashMap;

public interface Crud<T> {

      void insert(Integer a, T obj);
     HashMap<Integer,T> readAll();
     boolean updateNameById(Integer id,String name);

}
