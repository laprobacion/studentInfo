package main.controller;


import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import main.dao.AttendanceDAO;
import main.model.*;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class AttendanceController {
    @FXML
    public TableView<AttendanceTable> tableView;

    @FXML
    public TableColumn<AttendanceTable, String> in;
    @FXML
    public TableColumn<AttendanceTable, String> date;

    private Student student;

    public void setAttendance(Student student){
        tableView.setEditable(true);
        tableView.getColumns().clear();
        this.student = student;
        ObservableList<AttendanceTable> data = FXCollections.observableArrayList();
        List<Attendance> attendanceList = new AttendanceDAO().findByStudent(student);
        for(Attendance att : attendanceList){
            data.add( new AttendanceTable(convertToString(att.isIn()), formatDate(att.getDate())));
        }
        in.setCellValueFactory(new PropertyValueFactory<AttendanceTable, String>("in"));
        date.setCellValueFactory(new PropertyValueFactory<AttendanceTable, String>("date"));
        tableView.setItems(data);
        tableView.getColumns().addAll(in, date);
    }

    @FXML
    public void closeWindow(ActionEvent actionEvent) {
        ((Stage) ((Node)(actionEvent.getSource())).getScene().getWindow()).close();
    }

    private String convertToString(boolean isIn){
        if(isIn){
            return "In";
        }else{
            return "Out";
        }
    }

    private String formatDate(Date date){
        DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
        return dateFormat.format(date);
    }
}
