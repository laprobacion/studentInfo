package main.model;


import javafx.beans.property.SimpleStringProperty;

public class ScheduleTable {

    private final SimpleStringProperty subject;
    private final SimpleStringProperty day;
    private final SimpleStringProperty time;

    public ScheduleTable(String subject, String day, String time) {
        this.subject = new SimpleStringProperty(subject);
        this.day = new SimpleStringProperty(day);
        this.time = new SimpleStringProperty(time);
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

    public String getDay() {
        return day.get();
    }

    public SimpleStringProperty dayProperty() {
        return day;
    }

    public void setDay(String day) {
        this.day.set(day);
    }

    public String getTime() {
        return time.get();
    }

    public SimpleStringProperty timeProperty() {
        return time;
    }

    public void setTime(String time) {
        this.time.set(time);
    }
}
