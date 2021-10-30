package baseline;

import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
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
import javafx.util.converter.LocalDateStringConverter;

import java.io.BufferedReader;
import java.io.FileWriter;
import java.io.IOException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.ResourceBundle;

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
    private ListView<Task> itemList_completed;

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


        itemList.setCellFactory(CheckBoxListCell.forListView(new Callback<>() {
            @Override
            public ObservableValue<Boolean> call(Task task) {
                BooleanProperty observable = new SimpleBooleanProperty();
                observable.addListener((obs, wasSelected, isNowSelected) -> {
                    if (isNowSelected) {
                        checkedItem.add(task);
                    }

                });
                return observable;
            }
        }));


        checkedItem.addListener(new ListChangeListener<Task>() {
            @Override
            public void onChanged(Change<? extends Task> c) {
                System.out.println("Changed on " + c);
                if(c.next()){
                    itemList_completed.getItems().add(checkedItem.get(c.getFrom()));
                }
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

    private static void writeToTextFile(String filename, ObservableList<Task> tasks) throws IOException {
        FileWriter writer = new FileWriter("docs/" + filename);
        for(Task task : tasks){
            writer.write(task.getDescription()+ "/" + task.getDate());
            writer.write(",");
        }
        writer.close();
    }

    private static List<Task> readStudents(String filename) throws IOException {
        List<Task> tasks = new ArrayList<>();

        BufferedReader reader = Files.newBufferedReader(Path.of("docs/" + filename));
        String line;
        while ((line = reader.readLine()) != null) {
            String[] names = line.split(",");
            String[] dateString = line.split("/");

            // Add the student to the list
            tasks.add(new Task(dateString[0], names[0]));

        }

        return tasks;
    }


    @FXML
    void loadList(ActionEvent event) throws IOException {
        List<Task> inputStudents = null;
        try {
            inputStudents = readStudents("test.txt");
        } catch (IOException e) {
            e.printStackTrace();
        }
        
        if (inputStudents != null) {
            for (Task tasks : inputStudents) {
                itemList.getItems().add(tasks);
                item.add(tasks);
            }
        }
    }

    @FXML
    void saveList(ActionEvent event) throws IOException {
        writeToTextFile("test.txt", item);
        System.out.println("saved");
    }

}
