package main.Element;


import com.sun.prism.impl.Disposer;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.TableCell;
import main.controller.ScheduleController;
import main.dao.AttendanceSubjectDAO;
import main.dao.SubjectDAO;
import main.model.AttendanceSubject;
import main.model.ScheduleTable;
import main.model.Student;
import main.model.Subject;

import java.util.Date;
import java.util.UUID;

public class InButtonCell extends TableCell<Disposer.Record, Boolean> {
    final Button cellButton = new Button("In");

    public InButtonCell(final Student student){

        //Action when the button is pressed
        cellButton.setOnAction(new EventHandler<ActionEvent>(){

            @Override
            public void handle(ActionEvent t) {
                // get Selected Item
                ScheduleTable subjectRow = (ScheduleTable) InButtonCell.this.getTableView().getItems().get(InButtonCell.this.getIndex());
                Subject subject = new SubjectDAO().findSubjectByName(subjectRow.getSubject());
                AttendanceSubject attendanceSubject = new AttendanceSubject();
                String uniqueID = UUID.randomUUID().toString();
                attendanceSubject.setId(uniqueID);
                attendanceSubject.setStudent(student);
                attendanceSubject.setSubject(subject);
                attendanceSubject.setDate(new Date());
                new AttendanceSubjectDAO().save(attendanceSubject);

            }
        });
    }

    //Display button if the row is not empty
    @Override
    protected void updateItem(Boolean t, boolean empty) {
        super.updateItem(t, empty);
        this.setAlignment(Pos.CENTER);
        if(!empty){
            setGraphic(cellButton);
        }
        else{
            setGraphic(null);
        }
    }
}
