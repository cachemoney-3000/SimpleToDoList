package baseline;

import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.CheckBoxListCell;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.util.Callback;
import javafx.util.converter.LocalDateStringConverter;

import java.io.*;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.*;

public class Controller implements Initializable {

    @FXML
    private Button completedButton;

    @FXML
    private DatePicker datePicker;


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
    public ListView<Task> itemList_completed;

    @FXML
    private ListView<Task> itemList_incomplete;

    @FXML
    private TextField itemText;

    @FXML
    private TextField listTitle, listTitle_new1, listTitle_new2, listTitle_new3, listTitle_new4;

    @FXML
    private MenuItem loadButton;

    @FXML
    private Button newButton, newButton1, newButton2, newButton3, newButton4;

    @FXML
    private VBox newListVbox;

    @FXML
    private Button planButton;

    @FXML
    private MenuItem saveButton;

    @FXML
    private Pane planPane, completedPane, incompletePane;

    @FXML
    private AnchorPane leftPane, newPane1, newPane2, newPane3, newPane4;

    @FXML
    private Button deleteButton_new4, deleteButton_new3, deleteButton_new2, deleteButton_new1;

    @FXML
    private ListView<Task> itemList_new4, itemList_new3, itemList_new2, itemList_new1;

    @FXML
    private Button deleteItem_new4, deleteItem_new3, deleteItem_new2, deleteItem_new1;

    @FXML
    private TextField itemText_new4, itemText_new3, itemText_new2, itemText_new1;

    @FXML
    private DatePicker datePicker_new4, datePicker_new3, datePicker_new2, datePicker_new1;

    @FXML
    private Button enterButton_new4, enterButton_new3, enterButton_new2, enterButton_new1;

    private static int count = 0;

    @FXML
    void tabButtonAction(ActionEvent event) throws IOException {


        if(event.getSource()==planButton){
            planPane.toFront();
        }
        else if(event.getSource() == completedButton){
            completedPane.toFront();
        }
        else if(event.getSource() == incompleteButton){
            incompletePane.toFront();
            System.out.println("incomplete");
        }
        else if(event.getSource() == newButton){
            if(count == 1)
                newButton1.setVisible(true);

            if (count == 2)
                newButton2.setVisible(true);

            else if (count == 3)
                newButton3.setVisible(true);

            else if (count == 4)
                newButton4.setVisible(true);
            else if (count > 4){
                FXMLLoader loader = new FXMLLoader(getClass().getResource("newButton.fxml"));
                newListVbox.getChildren().add(loader.load());
            }
        }

    }

    @FXML
    void newListAction(ActionEvent event){
        if(event.getSource() == newButton1)
            newPane1.toFront();
        else if(event.getSource() == newButton2)
            newPane2.toFront();
        else if(event.getSource() == newButton3)
            newPane3.toFront();
        else if(event.getSource() == newButton4)
            newPane4.toFront();
    }

    @FXML
    void addList(MouseEvent event) throws IOException {
    }

    public ObservableList<Task> item = FXCollections.observableArrayList();
    public ObservableList<Task> checkedItem = FXCollections.observableArrayList();
    public ObservableList<Task> uncheckedItem = FXCollections.observableArrayList();

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
    void enteredItem_new1(MouseEvent event) {
        //create new Event by getting values from gui.
        String date;

        if(datePicker_new1.getValue()==null){
            date = "";
        }
        else {
            date = datePicker_new1.getValue().format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
        }

        var newItem = new Task(date, itemText_new1.getText());

        int index = itemList_new1.getSelectionModel().getSelectedIndex();
        if(index >= 0){
            itemList_new1.getItems().remove(index);
            item.remove(index);
            itemList_new1.getItems().add(index, newItem);
            item.add(index, newItem);

            datePicker_new1.getEditor().clear();
            datePicker_new1.setValue(null);
        }

        else{
            // add the new event to the list
            itemList_new1.getItems().add(newItem);
            item.add(newItem);

            datePicker_new1.getEditor().clear();
            datePicker_new1.setValue(null);

            // set text empty
            itemText_new1.setText("");
        }
    }

    @FXML
    void enteredItem_new2(MouseEvent event) {
        //create new Event by getting values from gui.
        String date;

        if(datePicker_new2.getValue()==null){
            date = "";
        }
        else {
            date = datePicker_new2.getValue().format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
        }

        var newItem = new Task(date, itemText_new2.getText());

        int index = itemList_new2.getSelectionModel().getSelectedIndex();
        if(index >= 0){
            itemList_new2.getItems().remove(index);
            item.remove(index);
            itemList_new2.getItems().add(index, newItem);
            item.add(index, newItem);

            datePicker_new2.getEditor().clear();
            datePicker_new2.setValue(null);
        }

        else{
            // add the new event to the list
            itemList_new2.getItems().add(newItem);
            item.add(newItem);

            datePicker_new2.getEditor().clear();
            datePicker_new2.setValue(null);

            // set text empty
            itemText_new2.setText("");
        }
    }

    @FXML
    void enteredItem_new3(MouseEvent event) {
        //create new Event by getting values from gui.
        String date;

        if(datePicker_new3.getValue()==null){
            date = "";
        }
        else {
            date = datePicker_new3.getValue().format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
        }

        var newItem = new Task(date, itemText_new3.getText());

        int index = itemList_new3.getSelectionModel().getSelectedIndex();
        if(index >= 0){
            itemList_new3.getItems().remove(index);
            item.remove(index);
            itemList_new3.getItems().add(index, newItem);
            item.add(index, newItem);

            datePicker_new3.getEditor().clear();
            datePicker_new3.setValue(null);
        }

        else{
            // add the new event to the list
            itemList_new3.getItems().add(newItem);
            item.add(newItem);

            datePicker_new3.getEditor().clear();
            datePicker_new3.setValue(null);

            // set text empty
            itemText_new3.setText("");
        }
    }

    @FXML
    void enteredItem_new4(MouseEvent event) {
        //create new Event by getting values from gui.
        String date;

        if(datePicker_new4.getValue()==null){
            date = "";
        }
        else {
            date = datePicker_new4.getValue().format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
        }

        var newItem = new Task(date, itemText_new4.getText());

        int index = itemList_new4.getSelectionModel().getSelectedIndex();
        if(index >= 0){
            itemList_new4.getItems().remove(index);
            item.remove(index);
            itemList_new4.getItems().add(index, newItem);
            item.add(index, newItem);

            datePicker_new4.getEditor().clear();
            datePicker_new4.setValue(null);
        }

        else{
            // add the new event to the list
            itemList_new4.getItems().add(newItem);
            item.add(newItem);

            datePicker_new4.getEditor().clear();
            datePicker_new4.setValue(null);

            // set text empty
            itemText_new4.setText("");
        }
    }





    @Override
    public void initialize(URL location, ResourceBundle resources) {
        listViewCheckBox();


        checkedItem.addListener((ListChangeListener<Task>) c -> {
            System.out.println("Changed on " + c);
            if(c.next()){
                itemList_completed.getItems().add(checkedItem.get(c.getFrom()));
            }
        });

        listTitleChanged();



        newButton.setOnMousePressed(event -> count++);

        newButtonVisibility();

        planPane.toFront();


    }

    private void listViewCheckBox(){
        // Plan tab
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

        // New list 1 tab
        itemList_new1.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue)
                -> itemText.setText(String.valueOf(itemList_new1.getSelectionModel().getSelectedItem())));


        itemList_new1.setCellFactory(CheckBoxListCell.forListView(task -> {
            BooleanProperty observable = new SimpleBooleanProperty();
            observable.addListener((obs, wasSelected, isNowSelected) -> {
                if (isNowSelected) {
                    checkedItem.add(task);
                }

            });
            return observable;
        }));

        // New list 2 tab
        itemList_new2.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue)
                -> itemText_new2.setText(String.valueOf(itemList_new2.getSelectionModel().getSelectedItem())));


        itemList_new2.setCellFactory(CheckBoxListCell.forListView(task -> {
            BooleanProperty observable = new SimpleBooleanProperty();
            observable.addListener((obs, wasSelected, isNowSelected) -> {
                if (isNowSelected) {
                    checkedItem.add(task);
                }

            });
            return observable;
        }));

        // New list 3 tab
        itemList_new3.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue)
                -> itemText_new3.setText(String.valueOf(itemList_new3.getSelectionModel().getSelectedItem())));


        itemList_new3.setCellFactory(CheckBoxListCell.forListView(task -> {
            BooleanProperty observable = new SimpleBooleanProperty();
            observable.addListener((obs, wasSelected, isNowSelected) -> {
                if (isNowSelected) {
                    checkedItem.add(task);
                }

            });
            return observable;
        }));

        // New list 3 tab
        itemList_new4.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue)
                -> itemText_new4.setText(String.valueOf(itemList_new4.getSelectionModel().getSelectedItem())));


        itemList_new4.setCellFactory(CheckBoxListCell.forListView(task -> {
            BooleanProperty observable = new SimpleBooleanProperty();
            observable.addListener((obs, wasSelected, isNowSelected) -> {
                if (isNowSelected) {
                    checkedItem.add(task);
                }

            });
            return observable;
        }));
    }

    private void newButtonVisibility(){
        newButton1.setVisible(false);
        newButton2.setVisible(false);
        newButton3.setVisible(false);
        newButton4.setVisible(false);
    }

    private void listTitleChanged(){
        listTitle_new1.setOnKeyPressed(event -> {
            if(event.getCode().equals(KeyCode.ENTER)){
                listTitle_new1.setText(listTitle_new1.getText());
                newButton1.setText(listTitle_new1.getText());
            }
        });

        listTitle_new2.setOnKeyPressed(event -> {
            if(event.getCode().equals(KeyCode.ENTER)){
                listTitle_new2.setText(listTitle_new2.getText());
                newButton2.setText(listTitle_new2.getText());
            }
        });

        listTitle_new3.setOnKeyPressed(event -> {
            if(event.getCode().equals(KeyCode.ENTER)){
                listTitle_new3.setText(listTitle_new3.getText());
                newButton3.setText(listTitle_new3.getText());
            }
        });

        listTitle_new4.setOnKeyPressed(event -> {
            if(event.getCode().equals(KeyCode.ENTER)){
                listTitle_new4.setText(listTitle_new4.getText());
                newButton4.setText(listTitle_new4.getText());
            }
        });
    }

    @FXML
    void incomplete(MouseEvent event) {
        itemList_incomplete.getItems().clear();
        uncheckedItem.clear();

        item.removeAll(checkedItem);
        uncheckedItem.addAll(item);

        int i;
        for(i = 0; i < uncheckedItem.size(); i++){
            itemList_incomplete.getItems().add(uncheckedItem.get(i));
        }
    }

    @FXML
    void viewList(MouseEvent event) {

    }


    @FXML
    void deleted(MouseEvent event) {
        int index = itemList.getSelectionModel().getSelectedIndex();
        if(index >= 0){
            itemList.getItems().remove(index);
            item.remove(index);
        }
    }

    @FXML
    void deleted_new1(MouseEvent event) {
        int index = itemList_new1.getSelectionModel().getSelectedIndex();
        if(index >= 0){
            itemList_new1.getItems().remove(index);
            item.remove(index);
        }
    }

    @FXML
    void deleted_new2(MouseEvent event) {
        int index = itemList_new2.getSelectionModel().getSelectedIndex();
        if(index >= 0){
            itemList_new2.getItems().remove(index);
            item.remove(index);
        }
    }

    @FXML
    void deleted_new3(MouseEvent event) {
        int index = itemList_new3.getSelectionModel().getSelectedIndex();
        if(index >= 0){
            itemList_new3.getItems().remove(index);
            item.remove(index);
        }
    }

    @FXML
    void deleted_new4(MouseEvent event) {
        int index = itemList_new4.getSelectionModel().getSelectedIndex();
        if(index >= 0){
            itemList_new4.getItems().remove(index);
            item.remove(index);
        }
    }



    private static void writeToTextFile(File filename, ObservableList<Task> tasks) throws IOException {
        FileWriter writer = new FileWriter(filename);
        for(Task task : tasks){
            writer.write(task.getDescription()+ "/ " + task.getDate());
            writer.write("\n");
        }
        writer.close();
    }

    private static List<Task> readTask(String filename) throws IOException {
        List<Task> tasks = new ArrayList<>();

        File file = new File(filename);
        Scanner reader = new Scanner(file);

        while (reader.hasNextLine()) {
            String line = reader.nextLine();
            String[] separator = line.split("/");
            String date = separator[1];
            String item = separator[0];

            Task newItem = new Task(date, item);
            tasks.add(newItem);
        }
        return tasks;
    }


    @FXML
    void loadList(ActionEvent event) throws IOException {
        FileChooser file = new FileChooser();
        File selectedFile = file.showOpenDialog(null);
        List<Task> tasks;

        if(selectedFile != null){
            try {
                tasks = readTask(selectedFile.getAbsolutePath());
                for(Task t: tasks){
                    itemList.getItems().add(t);
                    item.add(t);
                }
            }catch (FileNotFoundException e){
                e.printStackTrace();
            }
        }
    }

    @FXML
    void saveList(ActionEvent event) throws IOException {
        FileChooser fileChooser = new FileChooser();
        fileChooser.getExtensionFilters().addAll(new FileChooser.ExtensionFilter("txt","*.txt"));
        fileChooser.setTitle("Save");

        File f = fileChooser.showSaveDialog(new Stage());

        try{
            if(f != null){
                writeToTextFile(f, item);
            }
            System.out.println("saved");

        }catch (Exception e){
            e.printStackTrace();
        }
    }

}

