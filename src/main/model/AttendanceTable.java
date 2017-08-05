package main.model;


import javafx.beans.property.SimpleStringProperty;

public class AttendanceTable {

    private final SimpleStringProperty in;
    private final SimpleStringProperty date;

    public AttendanceTable(String in, String date) {
        this.in = new SimpleStringProperty(in);
        this.date = new SimpleStringProperty(date);
    }

    public String getIn() {
        return in.get();
    }

    public SimpleStringProperty inProperty() {
        return in;
    }

    public void setIn(String in) {
        this.in.set(in);
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
