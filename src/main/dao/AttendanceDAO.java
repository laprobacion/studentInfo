package main.dao;

import main.cache.MockData;
import main.model.Attendance;
import main.model.Student;

import java.util.List;

public class AttendanceDAO {

    public List<Attendance> findByStudent(Student student){
        return MockData.getInstance().getStudentAttendance(student);
    }

    public void save(Attendance attendance){

        MockData.getInstance().saveAttendance(attendance);
    }
}
