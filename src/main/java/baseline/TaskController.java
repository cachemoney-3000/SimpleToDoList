package baseline;

import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.image.ImageView;

import java.net.URL;
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


    public void setTask(String task) {
        ContextMenu menu = new ContextMenu();
        itemDescription.setText(task);
        itemDescription.setContextMenu(menu);
    }

    public void setDate(LocalDate date) {
        ContextMenu menu = new ContextMenu();
        dueDate.setText(String.valueOf(date));
        dueDate.setContextMenu(menu);
    }
}
