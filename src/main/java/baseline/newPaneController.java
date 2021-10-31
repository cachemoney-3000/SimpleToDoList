package baseline;

import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.CheckBoxListCell;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;

import java.net.URL;
import java.time.format.DateTimeFormatter;
import java.util.ResourceBundle;

public class newPaneController implements Initializable {

    @FXML
    private DatePicker datePicker;

    @FXML
    private Button deleteButton;

    @FXML
    private Button deleteItem;

    @FXML
    private Button enterButton;

    @FXML
    private ListView<Task> itemList;

    @FXML
    private TextField itemText;

    @FXML
    private TextField listTitle;

    @FXML
    public Pane newPane;

    @FXML
    void deleted(MouseEvent event) {
        int index = itemList.getSelectionModel().getSelectedIndex();
        if(index >= 0){
            itemList.getItems().remove(index);
            item.remove(index);
        }
    }

    private ObservableList<Task> item = FXCollections.observableArrayList();
    private ObservableList<Task> checkedItem = FXCollections.observableArrayList();
    private ObservableList<Task> uncheckedItem = FXCollections.observableArrayList();

    public Controller main = new Controller();

    @FXML
    void enteredItem(MouseEvent event) {
        //create new Event by getting values from gui.
        String date;

        if(datePicker.getValue()==null){
            date = "";
        }
        else {
            date = datePicker.getValue().format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
        }

        var newItem = new Task(date, itemText.getText());

        int index = itemList.getSelectionModel().getSelectedIndex();
        if(index >= 0){
            itemList.getItems().remove(index);
            main.item.remove(index);
            itemList.getItems().add(index, newItem);
            main.item.add(index, newItem);

            datePicker.getEditor().clear();
            datePicker.setValue(null);
        }

        else{
            // add the new event to the list
            itemList.getItems().add(newItem);
            main.item.add(newItem);

            datePicker.getEditor().clear();
            datePicker.setValue(null);

            // set text empty
            itemText.setText("");
        }
    }


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        itemList.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue)
                -> itemText.setText(String.valueOf(itemList.getSelectionModel().getSelectedItem())));


        itemList.setCellFactory(CheckBoxListCell.forListView(task -> {
            BooleanProperty observable = new SimpleBooleanProperty();
            observable.addListener((obs, wasSelected, isNowSelected) -> {
                if (isNowSelected) {
                    checkedItem.add(task);
                }

            });
            return observable;
        }));

        checkedItem.addListener((ListChangeListener<Task>) c -> {
            System.out.println("Changed on " + c);
            if(c.next()){
                main.itemList_completed.getItems().add(checkedItem.get(c.getFrom()));
            }
        });
    }
}
