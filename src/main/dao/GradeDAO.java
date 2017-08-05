package main.dao;


import main.cache.MockData;
import main.model.Grade;
import main.model.Student;

import java.util.List;

public class GradeDAO {

    public List<Grade> findByStudent(Student student){
        return MockData.getInstance().getGradesByStudent(student);
    }

}
