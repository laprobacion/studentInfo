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
import main.dao.GradeDAO;
import main.model.*;

public class GradeController {

    @FXML
    public TableView<GradeTable> tableView;

    @FXML
    public TableColumn<GradeTable, String> colSubject;
    @FXML
    public TableColumn<GradeTable, String> colGrade;


    private Student student;

    public void setGrade(Student student){
        tableView.setEditable(true);
        tableView.getColumns().clear();
        this.student = student;
        ObservableList<GradeTable> data = FXCollections.observableArrayList();
        GradeDAO gradeDAO = new GradeDAO();
        for(Grade grade : gradeDAO.findByStudent(student)){
            String s = grade.getSubject().getName();
            if(s != null && !s.trim().equals("")) {
                data.add(new GradeTable(grade.getSubject().getName(), String.valueOf(grade.getGrade())));
            }
        }
        colSubject.setCellValueFactory(new PropertyValueFactory<GradeTable, String>("subject"));
        colGrade.setCellValueFactory(new PropertyValueFactory<GradeTable, String>("grade"));
        tableView.setItems(data);
        tableView.getColumns().addAll(colSubject, colGrade);
    }

    @FXML
    public void closeWindow(ActionEvent actionEvent) {
        ((Stage) ((Node)(actionEvent.getSource())).getScene().getWindow()).close();
    }
}
