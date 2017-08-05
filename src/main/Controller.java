package main;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.stage.WindowEvent;
import main.cache.MockData;
import main.controller.StudentController;
import main.dao.StudentDAO;
import main.model.Student;

import java.io.IOException;

public class Controller {

    @FXML
    public Label readerTxt;

    @FXML
    public void closeWindow(ActionEvent actionEvent) {
        Platform.exit();
        System.exit(0);
    }


    /**
     * Code below is only for testing without the nfc reader.
     */
    @FXML
    public TextField idTxt;
    public void findStudent(ActionEvent actionEvent) {
        Student student = new StudentDAO().findById(idTxt.getText());
        try {
            FXMLLoader fxmlLoader = new FXMLLoader();
            Parent p = fxmlLoader.load(getClass().getResource("screen/student.fxml").openStream());
            StudentController studentController = (StudentController) fxmlLoader.getController();
            studentController.setStudent(student);
            Scene scene = new Scene(p, 600, 400);
            Stage stage = new Stage();
            stage.initStyle(StageStyle.UNDECORATED);
            stage.setTitle("Student");
            stage.setScene(scene);
            stage.show();
            stage.setOnCloseRequest(new EventHandler<WindowEvent>() {
                public void handle(WindowEvent we) {
                    MockData.getInstance().setStudentWindowActive(false);
                }
            });
        }catch (IOException e){
            e.printStackTrace();
        }
    }
}
