package main.cache;


import main.model.*;

import javax.print.AttributeException;
import java.util.*;

/**
 * This serves as a temporary data and stored in a cache.
 */
public class MockData {
    private static MockData _this;
    private List<Subject> subjectList;
    private List<Grade> gradeList;
    private List<Attendance> attendanceList;
    private List<AttendanceSubject> attendanceSubjectsList;
    private Map<String,Student> studentList;
    private boolean isStudentWindowActive;
    public void setStudentWindowActive(boolean flag){
        this.isStudentWindowActive = flag;
    }
    public boolean isStudentWindowActive() {
        return isStudentWindowActive;
    }

    private MockData(){}

    private void setGrades(){
        for(Map.Entry<String, Student> entry : studentList.entrySet()) {
            String key = entry.getKey();
            Student value = entry.getValue();
            for(Subject s : value.getSubjectList()) {
                Random random = new Random();
                int randomNum = random.nextInt(99 - 70 + 1) + 70;
                Grade g = new Grade();
                g.setId(randomNum + key + s.getName());
                g.setStudent(value);
                g.setSubject(s);
                g.setGrade(randomNum);
                _this.gradeList.add(g);
            }
        }
    }
    public static MockData getInstance(){
        if(_this == null){
            _this = new MockData();
        }
        return _this;
    }

    private void initData(){
        _this.subjectList = new ArrayList<Subject>();
        _this.studentList = new HashMap<String, Student>();
        _this.gradeList = new ArrayList<Grade>();
        _this.attendanceList = new ArrayList<Attendance>();
        _this.attendanceSubjectsList = new ArrayList<AttendanceSubject>();
        loadSubjectSchedule();
        loadStudents();
        setGrades();
    }
    public void saveAttendanceSubject(AttendanceSubject attSubj){
        _this.attendanceSubjectsList.add(attSubj);
    }
    public List<AttendanceSubject> getAttendanceSubject(Subject subject, Student student){
        List<AttendanceSubject> list = new ArrayList<AttendanceSubject>();
        for(AttendanceSubject att : _this.attendanceSubjectsList){
            if(att.getStudent().getId().equals(student.getId()) && att.getSubject().getId().equals(subject.getId())){
                list.add(att);
            }
        }
        return list;
    }
    public void saveAttendance(Attendance attendance){
        _this.attendanceList.add(attendance);
    }
    public List<Attendance> getStudentAttendance(Student student){
        List<Attendance> list = new ArrayList<Attendance>();
        for(Attendance att : _this.attendanceList){
            if(att.getStudent().getId().equals(student.getId())){
                list.add(att);
            }
        }
        return list;
    }
    public void loadData(){
        _this.initData();
    }

    public List<Grade> getGradesByStudent(Student student){
        List<Grade> grade = new ArrayList<Grade>();
        for(Grade g: _this.gradeList){
            if(g.getStudent().getId().equals(student.getId())){
                grade.add(g);
            }
        }
        return grade;
    }

    public Map<String,Student> getStudents(){
        return _this.studentList;
    }

    private void loadStudents(){
        Student student1 = new Student();
        student1.setId("B01A7F7A");
        student1.setFirstName("Roland");
        student1.setLastName("Sonsing");
        student1.setAge("18");
        student1.setGender("Male");
        student1.setContact("09959850916");
        student1.setEmailAddress("rolandvictor.sonsing@ama.edu.ph");
        student1.setAddress1("111 First Street");
        student1.setAddress2("Alabang Village");
        student1.setCity("Muntinlupa");
        student1.setSubjectList(subjectList);
        _this.studentList.put(student1.getId(), student1);

        Student student2 = new Student();
        student2.setId("76B4AABB");
        student2.setFirstName("Nikko");
        student2.setLastName("Prestoza");
        student2.setAge("19");
        student2.setGender("Male");
        student2.setContact("09951070185");
        student2.setEmailAddress("nikko.prestoza@ama.edu.ph");
        student2.setAddress1("213 Second Street");
        student2.setAddress2("Alabang Village");
        student2.setCity("Muntinlupa");
        student2.setSubjectList(subjectList);
        _this.studentList.put(student2.getId(), student2);

        Student student3 = new Student();
        student3.setId("STUDAAA113");
        student3.setFirstName("Ronald");
        student3.setLastName("Reyes");
        student3.setAge("20");
        student3.setGender("Male");
        student3.setContact("09051816432");
        student3.setEmailAddress("johnronald.reyes@ama.edu.ph");
        student3.setAddress1("999 Third Street");
        student3.setAddress2("Alabang Village");
        student3.setCity("Muntinlupa");
        student3.setSubjectList(subjectList);
        _this.studentList.put(student3.getId(), student3);
    }

    private void loadSubjectSchedule(){


        Subject subject1 = new Subject();
        subject1.setId("SUBMATH");
        subject1.setCode("MATH01");
        subject1.setName("ALGEBRA");
        subject1.setDescription("Math 1 - Algebra");

        Schedule sched1 = new Schedule();
        sched1.setId("SCHED1");
        sched1.setDay("Monday");
        sched1.setTime("7AM - 10AM");
        subject1.setSchedule(sched1);
        _this.subjectList.add(subject1);

        Subject subject2 = new Subject();
        subject2.setId("SUBENG");
        subject2.setCode("ENG01");
        subject2.setName("ENGLISH");
        subject2.setDescription("English 1 - Basic Language");

        Schedule sched2 = new Schedule();
        sched2.setId("SCHED2");
        sched2.setDay("Monday");
        sched2.setTime("11AM - 1PM");
        subject2.setSchedule(sched2);
        _this.subjectList.add(subject2);

        Subject subject3 = new Subject();
        subject3.setId("SUBFIL");
        subject3.setCode("FIL01");
        subject3.setName("FILIPINO");
        subject3.setDescription("FILIPINO 1");

        Schedule sched3 = new Schedule();
        sched3.setId("SCHED3");
        sched3.setDay("Tuesday");
        sched3.setTime("7AM - 9AM");
        subject3.setSchedule(sched3);
        _this.subjectList.add(subject3);

        Subject subject4 = new Subject();
        subject4.setId("SUBSCI1");
        subject4.setCode("SCI1");
        subject4.setName("BIOLOGY");
        subject4.setDescription("SCIENCE 1 - BIOLOGY");

        Schedule sched4 = new Schedule();
        sched4.setId("SCHED4");
        sched4.setDay("Friday");
        sched4.setTime("7AM - 9AM");
        subject4.setSchedule(sched4);
        _this.subjectList.add(subject4);
    }

    public List<Subject> getSubjectList(){
        return _this.subjectList;
    }
}
