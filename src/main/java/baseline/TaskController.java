package baseline;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;

import java.net.URL;
import java.time.LocalDate;
import java.util.ResourceBundle;

public class TaskController implements Initializable {

    @FXML
    private CheckBox checkBox;

    @FXML
    private Button deleteItem;

    @FXML
    private Label dueDate;

    @FXML
    private TextField itemDescription;

    private String title;
    private LocalDate date;

    public TaskController(String title) {
        this.title = title;
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        itemDescription.setText("Hello");
    }

    public void setData(){
        itemDescription.setText(title);
    }

}
