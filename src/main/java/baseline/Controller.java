package baseline;

import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.value.ChangeListener;
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
import javafx.scene.input.KeyCode;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.net.URL;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.Scanner;

public class Controller implements Initializable {

    @FXML
    private Button completedButton;

    @FXML
    private DatePicker datePicker;

    @FXML
    private Button incompleteButton;

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
    private Button planButton;

    @FXML
    private Pane planPane, completedPane, incompletePane;

    @FXML
    private Button  deleteButton;


    @FXML
    private Button enterButton;



    public ObservableList<Task> item = FXCollections.observableArrayList();
    public ObservableList<Task> checkedItem = FXCollections.observableArrayList();
    public ObservableList<Task> uncheckedItem = FXCollections.observableArrayList();

    @FXML
    void deleteButtonAction(ActionEvent event) {
        DeleteList del = new DeleteList();

        if (event.getSource() == deleteButton) {
            item = del.deleteMain(itemList, item);
            System.out.println("Cleared the list");
        }
    }

    @FXML
    void tabButtonAction(ActionEvent event) {
        TabVisibility tab = new TabVisibility();

        if(event.getSource()==planButton){
            tab.makeVisible(planPane);
            System.out.println("Clicked plan tab button");
        }
        else if(event.getSource() == completedButton){
            tab.makeVisible(completedPane);
            System.out.println("Clicked completed tab button");
        }
        else if(event.getSource() == incompleteButton){
            tab.makeVisible(incompletePane);
            System.out.println("Clicked incomplete tab button");
        }
    }


    @FXML
    void enteredItemAction(ActionEvent event){
        if(event.getSource() == enterButton){
            AddItem enter = new AddItem();
            int index = itemList.getSelectionModel().getSelectedIndex();
            if(index >= 0)
                item = enter.replaceItem(datePicker, itemText, itemList, item, index);

            else
                item = enter.newItem(datePicker, itemText, itemList, item);
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


        planPane.toFront();

        listTitleChanged();

    }

    private void listViewCheckBox(){
        // Plan tab
        itemList.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue)
                -> {
            String input = String.valueOf(itemList.getSelectionModel().getSelectedItem());
            itemText.setText(input);
        });



        itemList.setCellFactory(CheckBoxListCell.forListView(task -> {
            BooleanProperty observable = new SimpleBooleanProperty();
            observable.addListener((obs, wasSelected, isNowSelected) -> {
                if (isNowSelected) {
                    checkedItem.add(task);
                }

            });
            return observable;
        }));


    }


    private void listTitleChanged(){
        listTitle.setOnKeyPressed(event -> {
            if(event.getCode().equals(KeyCode.ENTER)){
                listTitle.setText(listTitle.getText());
                planButton.setText(listTitle.getText());
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

}

