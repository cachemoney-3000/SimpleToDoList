package baseline;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class Controller {

    @FXML
    private Button completedButton;

    @FXML
    private Button dateButton;

    @FXML
    private ImageView deleteButton;

    @FXML
    private Button enterButton;

    @FXML
    private Button incompleteButton;

    @FXML
    private TextField itemText;

    @FXML
    private TextField listTitle;

    @FXML
    private Menu loadButton;

    @FXML
    private Button newButton;


    @FXML
    private Button planButton;

    @FXML
    private Menu saveButton;

    @FXML
    private Label label;

    @FXML
    private DatePicker datePicker;

    @FXML
    private VBox vBox;

    @FXML
    void addList(MouseEvent event) {

    }

    @FXML
    void completed(MouseEvent event) {

    }

    private ObservableList<Task> listOfTasks;

    @FXML
    void enteredItem(MouseEvent event) {
        int size = 1;
        try { //load task items to vbox
            Node[] nodes = new Node[size];
            for (int i = 0; i < nodes.length; i++) {
                //load specific item
                FXMLLoader loader = new FXMLLoader(getClass().getResource("item.fxml"));
                TaskController controller = new TaskController();

                nodes[i] = loader.load();
                //create new Event by getting values from gui
                vBox.getChildren().add(nodes[i]);
                controller.setData(itemText.getText(), datePicker.getValue());

            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }





    @FXML
    void incomplete(MouseEvent event) throws IOException {


    }

    @FXML
    void today(MouseEvent event) {

    }

    public void dateEntered(MouseEvent mouseEvent) {
    }
}
