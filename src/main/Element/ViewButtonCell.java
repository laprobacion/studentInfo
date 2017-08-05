package main.Element;


import com.sun.prism.impl.Disposer;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.TableCell;
import main.controller.ScheduleController;
import main.dao.SubjectDAO;
import main.model.ScheduleTable;
import main.model.Student;
import main.model.Subject;

public class ViewButtonCell extends TableCell<Disposer.Record, Boolean> {
    final Button cellButton = new Button("View");

    public ViewButtonCell(final ScheduleController controller, final Student student){

        //Action when the button is pressed
        cellButton.setOnAction(new EventHandler<ActionEvent>(){

            @Override
            public void handle(ActionEvent t) {
                // get Selected Item
                ScheduleTable subjectRow = (ScheduleTable) ViewButtonCell.this.getTableView().getItems().get(ViewButtonCell.this.getIndex());
                Subject subject = new SubjectDAO().findSubjectByName(subjectRow.getSubject());
                controller.showSubjectAttendance(subject, student);
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
