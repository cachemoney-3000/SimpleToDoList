package baseline;

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
    private VBox newListVbox;

    @FXML
    private Button planButton;

    @FXML
    private Menu saveButton;

    @FXML
    private Button dueDateButton;

    @FXML
    private Label label;

    @FXML
    private DatePicker datePicker;

    @FXML
    void addList(MouseEvent event) {

    }

    @FXML
    void completed(MouseEvent event) {

    }
    @FXML
    void dueDate(MouseEvent event) {
        try{
            // load the fxml file
            FXMLLoader fxmlLoader = new FXMLLoader(Application.class.getResource("datePickerWindow.fxml"));
            Scene scene = new Scene(fxmlLoader.load());
            Stage stage = new Stage();
            stage.setResizable(false);
            stage.setScene(scene);
            stage.show();

        }catch (Exception e){
            e.printStackTrace();
        }
    }

    //@FXML
    //void dateEntered(MouseEvent event) {
       // Stage stage = (Stage) dueDateButton.getScene().getWindow();
       // stage.close();
        //label.setText("Due date: " + datePicker.getValue());

   // }
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

                datePickerController dueDate = new datePickerController();
                //String pattern = "MM/dd/yyyy HH:mm:ss";
                //DateFormat df = new SimpleDateFormat(pattern);
                //String date = df.format(dueDate.date());


                nodes[i] = loader.load();
                newListVbox.getChildren().add(nodes[i]);
                controller.setTask(itemText.getText());
                controller.setDate(dueDate.date());

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
