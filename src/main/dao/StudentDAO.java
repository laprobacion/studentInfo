package main.dao;


import main.cache.MockData;
import main.model.Student;

public class StudentDAO {

    public Student findById(String id){
        return MockData.getInstance().getStudents().get(id);
    }
}
