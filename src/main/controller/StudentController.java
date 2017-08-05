package main.controller;


import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import main.cache.MockData;
import main.dao.AttendanceDAO;
import main.dao.StudentDAO;
import main.model.Attendance;
import main.model.Student;

import java.io.IOException;
import java.net.URL;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.ResourceBundle;

public class StudentController{

    @FXML
    public ImageView studentInfo;
    @FXML
    public Label firstNamelbl;

    @FXML
    public Label lastNamelbl;

    @FXML
    public Button studentInfoButton;

    @FXML
    public Button scheduleButton;

    @FXML
    public Button gradeButton;

    @FXML
    public Button inButton;

    @FXML
    public Button outButton;

    private Student student;



    public void setStudent(Student student){
        firstNamelbl.setText(student.getFirstName());
        lastNamelbl.setText(student.getLastName());
        this.student = student;
        setStudentInOrOut();
    }

    public void showStudentInfo(ActionEvent actionEvent) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader();
            Parent p = fxmlLoader.load(getClass().getResource("../screen/studentInformation.fxml").openStream());
            StudentInfoController studentController = (StudentInfoController) fxmlLoader.getController();
            studentController.setStudent(student);
            Scene scene = new Scene(p, 600, 400);
            Stage stage = new Stage();
            stage.initStyle(StageStyle.UNDECORATED);
            stage.setTitle("Student Information");
            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void showSchedule(ActionEvent actionEvent) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader();
            Parent p = fxmlLoader.load(getClass().getResource("../screen/schedule.fxml").openStream());
            ScheduleController scheduleController = (ScheduleController) fxmlLoader.getController();
            scheduleController.setSchedule(student);
            Scene scene = new Scene(p, 600, 400);
            Stage stage = new Stage();
            stage.initStyle(StageStyle.UNDECORATED);
            stage.setTitle("Schedule");
            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void showGrade(ActionEvent actionEvent) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader();
            Parent p = fxmlLoader.load(getClass().getResource("../screen/grade.fxml").openStream());
            GradeController gradeController = (GradeController) fxmlLoader.getController();
            gradeController.setGrade(student);
            Scene scene = new Scene(p, 600, 400);
            Stage stage = new Stage();
            stage.initStyle(StageStyle.UNDECORATED);
            stage.setTitle("Grades");
            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void showAttendance(ActionEvent actionEvent) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader();
            Parent p = fxmlLoader.load(getClass().getResource("../screen/attendance.fxml").openStream());
            AttendanceController attendanceController = (AttendanceController) fxmlLoader.getController();
            attendanceController.setAttendance(student);
            Scene scene = new Scene(p, 600, 400);
            Stage stage = new Stage();
            stage.initStyle(StageStyle.UNDECORATED);
            stage.setTitle("Attendance");
            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void checkIn(ActionEvent actionEvent) {
        student.setInVicinity(true);
        Attendance att = new Attendance();
        att.setId("");
        att.setStudent(student);
        att.setDate(new Date());
        att.setIn(student.isInVicinity());
        AttendanceDAO dao = new AttendanceDAO();
        dao.save(att);
        inButton.setDisable(true);
        outButton.setDisable(false);
    }

    public void checkOut(ActionEvent actionEvent) {
        student.setInVicinity(false);
        Attendance att = new Attendance();
        att.setId("");
        att.setStudent(student);
        att.setDate(new Date());
        att.setIn(student.isInVicinity());
        AttendanceDAO dao = new AttendanceDAO();
        dao.save(att);
        inButton.setDisable(false);
        outButton.setDisable(true);
    }

    public void setStudentInOrOut(){
        inButton.setDisable(student.isInVicinity());
        outButton.setDisable(!student.isInVicinity());
    }

    @FXML
    public void closeWindow(ActionEvent actionEvent) {
        MockData.getInstance().setStudentWindowActive(false);
        ((Stage) ((Node)(actionEvent.getSource())).getScene().getWindow()).close();
    }

    @FXML
    private AnchorPane root ;

    public void initialize() {
        //root.getStylesheets().add(getClass().getResource("bootstrap2.css").toExternalForm());
    }
}
