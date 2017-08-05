package main;

import com.sun.jna.Native;
import com.sun.jna.ptr.ByteByReference;
import com.sun.jna.ptr.IntByReference;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.stage.WindowEvent;
import jssc.*;
import main.cache.MockData;
import main.controller.StudentController;
import main.dao.StudentDAO;
import main.model.Student;


import java.io.IOException;
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Main extends Application {

    static SerialPort serialPort;
    private boolean hasError;
    private void loadSerialPort(){
        serialPort = new SerialPort("COM5");
        try {
            serialPort.openPort();//Open serial port
            serialPort.setParams(SerialPort.BAUDRATE_9600,SerialPort.DATABITS_8,SerialPort.STOPBITS_1,SerialPort.PARITY_NONE);//Set params. Also you can set params by this string: serialPort.setParams(9600, 8, 1, 0);
            serialPort.writeBytes("\002v0\003".getBytes());//Write data to port
            serialPort.closePort();//Close serial port
        }
        catch (SerialPortException ex) {
            System.out.println(ex);
        }
        try {
            serialPort.openPort();//Open port
            serialPort.setParams(9600, 8, 1, 0);//Set params
            int mask = SerialPort.MASK_RXCHAR + SerialPort.MASK_CTS + SerialPort.MASK_DSR;//Prepare mask
            serialPort.setEventsMask(mask);//Set mask
            serialPort.addEventListener(new SerialPortReader());//Add SerialPortEventListener
        }
        catch (SerialPortException ex) {
            System.out.println(ex);
            hasError = true;
        }
    }
    @Override
    public void start(Stage primaryStage) throws Exception{
        SerialPort arduinoPort = null;
        String[] serialPortNames = SerialPortList.getPortNames();
        for(String name: serialPortNames){
            System.out.println(name);

        }
        loadSerialPort();
        loadData();

        FXMLLoader loader = new FXMLLoader(getClass().getResource("screen/main.fxml"));
        Pane pane = (Pane) loader.load();
        Controller controller = loader.<Controller>getController();
        if(hasError) {
            controller.readerTxt.setText("Reader not found!");
        }
        primaryStage.initStyle(StageStyle.UNDECORATED);
        primaryStage.setTitle("IWTS");
        primaryStage.setScene(new Scene(pane, 600, 400));
        primaryStage.show();

        // -- below line is for testing without reader.!!!
        //loadStudentWindow("B01A7F7A");
    }

    private Student loadStudentWindow(String studentId){
        Student student = new StudentDAO().findById(studentId);
        try {
            if(student == null){
                return null;
            }
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
        return student;
    }
    private void loadData(){
        MockData.getInstance().loadData();
    }
    public static void main(String[] args) {
        launch(args);
    }

    class SerialPortReader implements SerialPortEventListener {

        @Override
        public void serialEvent(SerialPortEvent event) {
            try {

            } catch (Exception e) {
                System.out.println(e);
            }

            if (event.isRXCHAR()) {//If data is available
                if (event.getEventValue() == 10) {//Check bytes count in the input buffer
                    //Read data, if 10 bytes available
                    String uid = "";
                    try {
                        uid = "";
                        uid += serialPort.readString();

                    } catch (SerialPortException ex) {
                        System.out.println(ex);
                    }
                    final String finUid = uid;
                    Platform.runLater( new Runnable(){
                                           @Override
                                           public void run() {
                                               readIncomingId(finUid);
                                           }
                                       }
                    );
                    uid = "";
                }
            }


        }
    }

    private void readIncomingId(String uid){
        if(uid != null && !uid.trim().equals("") && !MockData.getInstance().isStudentWindowActive()){
            String cleanUID = uid = uid.replaceAll("\\r","").replaceAll("\\n","");
            if(loadStudentWindow(cleanUID) == null){
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Error Dialog");
                alert.setHeaderText("Student ID not found!");
                alert.setContentText("Contact your admin to register this ID tag.");

                alert.showAndWait();
            }else{
                MockData.getInstance().setStudentWindowActive(true);
            }
        }
    }
}
