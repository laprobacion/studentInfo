package main.model;


import javafx.beans.property.SimpleStringProperty;

public class AttendanceSubjectTable {

    private final SimpleStringProperty date;

    public AttendanceSubjectTable(String date) {
        this.date = new SimpleStringProperty(date);
    }

    public String getDate() {
        return date.get();
    }

    public SimpleStringProperty dateProperty() {
        return date;
    }

    public void setDate(String date) {
        this.date.set(date);
    }
}
