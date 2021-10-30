package baseline;

import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.CheckBoxListCell;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.util.Callback;

import java.net.URL;
import java.util.ResourceBundle;

public class Controller implements Initializable {

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
    private Button deleteItem;

    @FXML
    private Button editItem;

    @FXML
    private ListView<Task> itemList;

    @FXML
    private ListView<Task> itemList_completed;

    @FXML
    private ListView<Task> itemList_incomplete;

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
    private Pane planPane, completedPane, incompletePane;

    @FXML
    void tabButtonAction(ActionEvent event){
        if(event.getSource()==planButton){
            planPane.toFront();
        }
        else if(event.getSource() == completedButton){
            completedPane.toFront();
        }
        else if(event.getSource() == incompleteButton){
            incompletePane.toFront();
        }

    }

    @FXML
    void addList(MouseEvent event) {

    }

    private ObservableList<Task> item = FXCollections.observableArrayList();
    private ObservableList<Task> checkedItem = FXCollections.observableArrayList();
    private ObservableList<Task> uncheckedItem = FXCollections.observableArrayList();



    @FXML
    void enteredItem(MouseEvent event) {
        //create new Event by getting values from gui
        var newItem = new Task(datePicker.getValue(), itemText.getText());

        int index = itemList.getSelectionModel().getSelectedIndex();
        if(index >= 0){
            itemList.getItems().remove(index);
            item.remove(index);
            itemList.getItems().add(index, newItem);
            item.add(index, newItem);

            datePicker.getEditor().clear();
            datePicker.setValue(null);
        }

        else{
            // add the new event to the list
            itemList.getItems().add(newItem);
            item.add(newItem);

            datePicker.getEditor().clear();
            datePicker.setValue(null);

            // set text empty
            itemText.setText("");
        }
    }


    @FXML
    void today(MouseEvent event) {

    }


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        itemList.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue)
                -> itemText.setText(String.valueOf(itemList.getSelectionModel().getSelectedItem())));


        itemList.setCellFactory(CheckBoxListCell.forListView(new Callback<>() {
            @Override
            public ObservableValue<Boolean> call(Task task) {
                BooleanProperty observable = new SimpleBooleanProperty();
                observable.addListener((obs, wasSelected, isNowSelected) -> {
                    if (isNowSelected) {
                        checkedItem.add(task);
                        System.out.println(checkedItem);
                    } else if (wasSelected) {
                        System.out.println(task);
                        uncheckedItem.add(task);
                    }
                });
                return observable;
            }
        }));

        planPane.toFront();
    }


    @FXML
    void completed(MouseEvent event) {
        for(int i = 0; i < checkedItem.size(); i++){
            itemList_completed.getItems().add(checkedItem.get(i));
        }
    }

    @FXML
    void incomplete(MouseEvent event) {
        for(int i = 0; i < uncheckedItem.size(); i++){
            itemList_completed.getItems().add(uncheckedItem.get(i));
        }
    }


    @FXML
    void deleted(MouseEvent event) {
        int index = itemList.getSelectionModel().getSelectedIndex();
        if(index >= 0){
            itemList.getItems().remove(index);
            item.remove(index);
        }
    }


}
