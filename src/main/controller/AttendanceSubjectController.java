package main.controller;


import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TitledPane;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import main.dao.AttendanceDAO;
import main.dao.AttendanceSubjectDAO;
import main.model.*;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class AttendanceSubjectController {
    @FXML
    public TableView<AttendanceSubjectTable> tableView;

    @FXML
    public TableColumn<AttendanceSubjectTable, String> date;

    @FXML
    public TitledPane titleWindow;

    public void setTitleWindow(String title){
        titleWindow.setText(title);
    }
    private Student student;

    public void setAttendanceSubject(Subject subject, Student student){
        tableView.setEditable(true);
        tableView.getColumns().clear();
        this.student = student;
        ObservableList<AttendanceSubjectTable> data = FXCollections.observableArrayList();
        List<AttendanceSubject> attendanceSubjectList = new AttendanceSubjectDAO().findByStudentSubject(subject,student);
        for(AttendanceSubject att : attendanceSubjectList){
            data.add( new AttendanceSubjectTable(formatDate(att.getDate())));
        }
        date.setCellValueFactory(new PropertyValueFactory<AttendanceSubjectTable, String>("date"));
        date.setMinWidth(200.0);
        tableView.setItems(data);
        tableView.getColumns().addAll(date);
    }

    @FXML
    public void closeWindow(ActionEvent actionEvent) {
        ((Stage) ((Node)(actionEvent.getSource())).getScene().getWindow()).close();
    }


    private String formatDate(Date date){
        DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
        return dateFormat.format(date);
    }
}
