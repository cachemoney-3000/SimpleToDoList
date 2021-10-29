package baseline;

import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.ListView;
import javafx.scene.control.Menu;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.CheckBoxListCell;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;
import javafx.util.Callback;

import java.time.LocalDate;

public class Controller {

    @FXML
    private Button completedButton;

    @FXML
    private DatePicker datePicker;

    @FXML
    private ImageView deleteButton;

    @FXML
    private Button enterButton;

    @FXML
    private Button incompleteButton;

    @FXML
    private ListView<Task> itemList;

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
    void addList(MouseEvent event) {

    }

    @FXML
    void completed(MouseEvent event) {

    }
    private ObservableList<String> item = FXCollections.observableArrayList();

    @FXML
    void enteredItem(MouseEvent event) {
        var newItem = new Task(datePicker.getValue(), itemText.getText());

        itemList.getItems().add(newItem);
        datePicker.setValue(LocalDate.now());


        itemList.setCellFactory(CheckBoxListCell.forListView(new Callback<Task, ObservableValue<Boolean>>() {
            @Override
            public ObservableValue<Boolean> call(Task param) {
                BooleanProperty observable = new SimpleBooleanProperty();
                observable.addListener((obs, wasSelected, isNowSelected) ->
                        System.out.println("Check box for "+item+" changed from "+wasSelected+" to "+isNowSelected)
                );
                return observable;
            }
        }));
    }





    @FXML
    void incomplete(MouseEvent event) {

    }

    @FXML
    void today(MouseEvent event) {

    }

}
