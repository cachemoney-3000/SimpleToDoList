package baseline;

import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.CheckBoxListCell;
import javafx.scene.image.ImageView;
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
    private TextField listTitle;

    @FXML
    private MenuItem loadButton;

    @FXML
    private Button newButton;

    @FXML
    private VBox newListVbox;

    @FXML
    private Button planButton;

    @FXML
    private MenuItem saveButton;

    @FXML
    private Pane planPane, completedPane, incompletePane;

    @FXML
    public AnchorPane leftPane, newPane;

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
            System.out.println("incomplete");
        }
        else if(event.getSource() == newButton){
            //newPane.toFront();
        }

    }

    @FXML
    void addList(MouseEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(Application.class.getResource("newButton.fxml"));
        newListVbox.getChildren().add(loader.load());

        FXMLLoader loader1 = new FXMLLoader(Application.class.getResource("newPane.fxml"));
        leftPane.getChildren().add(loader1.load());

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
                itemList_completed.getItems().add(checkedItem.get(c.getFrom()));
            }
        });

        planPane.toFront();
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


    public void load() throws IOException {
        System.out.println("load");

    }
}

