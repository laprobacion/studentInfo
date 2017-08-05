package main.controller;


import javafx.beans.property.SimpleBooleanProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import main.Element.InButtonCell;
import main.Element.ViewButtonCell;
import main.dao.SubjectDAO;
import main.model.Schedule;
import main.model.ScheduleTable;
import main.model.Student;
import main.model.Subject;
import com.sun.prism.impl.Disposer.Record;
import javafx.beans.value.ObservableValue;
import javafx.util.Callback;
import javafx.scene.control.TableCell;
import javafx.scene.control.Button;

import java.io.IOException;

public class ScheduleController {

    @FXML
    public TableView<ScheduleTable> tableView;

    @FXML
    public TableColumn<ScheduleTable, String> colSubject;
    @FXML
    public TableColumn<ScheduleTable, String> colDay;
    @FXML
    public TableColumn<ScheduleTable, String> colTime;
    ObservableList<ScheduleTable> data;
    private Student student;

    public void setSchedule(Student student){
        tableView.setEditable(true);
        tableView.getColumns().clear();
        this.student = student;
        data = FXCollections.observableArrayList();
        for(Subject subject : student.getSubjectList()){
            Schedule sched = subject.getSchedule();
            data.add( new ScheduleTable(subject.getName(),sched.getDay(),sched.getTime()));
        }
        colSubject.setCellValueFactory(new PropertyValueFactory<ScheduleTable, String>("subject"));
        colDay.setCellValueFactory(new PropertyValueFactory<ScheduleTable, String>("day"));
        colTime.setCellValueFactory(new PropertyValueFactory<ScheduleTable, String>("time"));
        TableColumn viewAttendance = new TableColumn("View Attendance");
        TableColumn inButton = new TableColumn("Action In");
        viewAttendance.setMinWidth(120.0);
        inButton.setMinWidth(80.0);
        tableView.getColumns().addAll(colSubject, colDay, colTime, viewAttendance, inButton);


        setInButton(inButton);
        setViewAttendanceButton(viewAttendance);
        tableView.setItems(data);
    }

    @FXML
    public void closeWindow(ActionEvent actionEvent) {
        ((Stage) ((Node)(actionEvent.getSource())).getScene().getWindow()).close();
    }

    private void setInButton(TableColumn inButton){
        inButton.setCellValueFactory(
                new Callback<TableColumn.CellDataFeatures<Record, Boolean>,
                        ObservableValue<Boolean>>() {

                    @Override
                    public ObservableValue<Boolean> call(TableColumn.CellDataFeatures<Record, Boolean> p) {
                        return new SimpleBooleanProperty(p.getValue() != null);
                    }
                });

        //Adding the Button to the cell
        inButton.setCellFactory(
                new Callback<TableColumn<Record, Boolean>, TableCell<Record, Boolean>>() {
                    @Override
                    public TableCell<Record, Boolean> call(TableColumn<Record, Boolean> p) {
                        return new InButtonCell(student);
                    }
                });
    }
    private void setViewAttendanceButton(TableColumn viewAttendance){
        viewAttendance.setCellValueFactory(
                new Callback<TableColumn.CellDataFeatures<Record, Boolean>,
                        ObservableValue<Boolean>>() {

                    @Override
                    public ObservableValue<Boolean> call(TableColumn.CellDataFeatures<Record, Boolean> p) {
                        return new SimpleBooleanProperty(p.getValue() != null);
                    }
                });

        //Adding the Button to the cell
        viewAttendance.setCellFactory(
                new Callback<TableColumn<Record, Boolean>, TableCell<Record, Boolean>>() {

                    @Override
                    public TableCell<Record, Boolean> call(TableColumn<Record, Boolean> p) {
                        return new ViewButtonCell(ScheduleController.this, student);
                    }
                });
    }

    public void showSubjectAttendance(Subject subject, Student student) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader();
            Parent p = fxmlLoader.load(getClass().getResource("../screen/attendanceSubject.fxml").openStream());
            AttendanceSubjectController controller = (AttendanceSubjectController) fxmlLoader.getController();
            controller.setAttendanceSubject(subject, student);
            Scene scene = new Scene(p, 600, 400);
            Stage stage = new Stage();
            stage.initStyle(StageStyle.UNDECORATED);
            String indent = "       ";
            String title= indent + student.getFirstName() + " " + student.getLastName() + "'s Attendance in " + subject.getDescription();
            controller.setTitleWindow(title);
            stage.setTitle(title);
            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
