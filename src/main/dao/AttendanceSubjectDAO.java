package main.dao;

import main.cache.MockData;
import main.model.Attendance;
import main.model.AttendanceSubject;
import main.model.Student;
import main.model.Subject;

import java.util.List;

public class AttendanceSubjectDAO {

    public List<AttendanceSubject> findByStudentSubject(Subject subject, Student student){
        return MockData.getInstance().getAttendanceSubject(subject, student);
    }

    public void save(AttendanceSubject attendance){
        MockData.getInstance().saveAttendanceSubject(attendance);
    }
}
