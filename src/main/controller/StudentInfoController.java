package main.controller;


import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import main.model.Student;

public class StudentInfoController {
    private Student student;

    @FXML
    public Label firstNamelbl;

    @FXML
    public Label lastNamelbl;
    @FXML
    public Label agelbl;
    @FXML
    public Label genderlbl;
    @FXML
    public Label contactlbl;
    @FXML
    public Label emaillbl;


    public void setStudent(Student student){
        this.student = student;
        setLabels();
    }

    private void setLabels(){
        firstNamelbl.setText(student.getFirstName());
        lastNamelbl.setText(student.getLastName());
        agelbl.setText(student.getAge());
        genderlbl.setText(student.getGender());
        contactlbl.setText(student.getContact());
        emaillbl.setText(student.getEmailAddress());
    }

    @FXML
    public void closeWindow(ActionEvent actionEvent) {
        ((Stage) ((Node)(actionEvent.getSource())).getScene().getWindow()).close();
    }
}
