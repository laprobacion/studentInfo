package main.model;


import javafx.beans.property.SimpleStringProperty;

public class GradeTable {

    private final SimpleStringProperty subject;
    private final SimpleStringProperty grade;

    public GradeTable(String subject, String grade) {
        this.subject = new SimpleStringProperty(subject);
        this.grade = new SimpleStringProperty(grade);
    }

    public String getSubject() {
        return subject.get();
    }

    public SimpleStringProperty subjectProperty() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject.set(subject);
    }

    public String getGrade() {
        return grade.get();
    }

    public SimpleStringProperty gradeProperty() {
        return grade;
    }

    public void setGrade(String grade) {
        this.grade.set(grade);
    }
}
