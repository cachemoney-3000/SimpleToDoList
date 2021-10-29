package baseline;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

import java.time.LocalDate;

public class TaskController {

    @FXML
    private CheckBox checkBox;

    @FXML
    private Button deleteItem;

    @FXML
    private Label dueDate;

    @FXML
    private TextField itemDescription;

    public void setData(String task, LocalDate date) {
        itemDescription.setText(task);
        dueDate.setText(String.valueOf(date));
    }

}
