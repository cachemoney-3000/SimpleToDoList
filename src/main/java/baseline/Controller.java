/*
 * UCF COP3330 Fall 2021 Application Assignment 1 Solution
 * Copyright 2021 Joshua Samontanez
 */

package baseline;

import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;

import java.net.URL;
import java.util.ResourceBundle;

public class Controller implements Initializable {

    @FXML
    private Button completedButton;

    @FXML
    private AnchorPane completedPane;

    @FXML
    private DatePicker datePicker;

    @FXML
    private Button deleteButton;

    @FXML
    private Button deleteItemButton;

    @FXML
    private Button enterButton;

    @FXML
    private Button incompleteButton;

    @FXML
    private AnchorPane incompletePane;

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
    private Button planButton;

    @FXML
    private AnchorPane planPane;

    @FXML
    private MenuItem saveButton;


    public ObservableList<Task> item = FXCollections.observableArrayList();
    public ObservableList<Task> checkedItem = FXCollections.observableArrayList();
    public ObservableList<Task> uncheckedItem = FXCollections.observableArrayList();

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        // Make the plan pane to be shown first when the program starts
        planPane.toFront();

        // Initialize object is where the important initializing methods are stored
        Initialize init = new Initialize();

        // Will make the list view be able to have check box
        init.listCheckBox(itemList, checkedItem);
        // When an item on the list view was selected
        // It will get its description and show it into the itemText text field
        init.listListener(itemList, itemText);

        // When an item was marked checked, that specific item will now be added into a new list
        checkedItem.addListener((ListChangeListener<Task>) c -> {
            if(c.next())
                itemList_completed.getItems().add(checkedItem.get(c.getFrom()));
        });
    }

    @FXML
    void tabButtonAction(ActionEvent event) {
        TabVisibility tab = new TabVisibility();

        // When the "planButton" is clicked, it will show the planPane
        if(event.getSource()==planButton){
            tab.makeVisible(planPane);
            System.out.println("Clicked plan tab button");
        }
        // When the "completedButton" is clicked, it will show the completedPane
        else if(event.getSource() == completedButton){
            tab.makeVisible(completedPane);
            System.out.println("Clicked completed tab button");
        }
        // When the "incompleteButton" is clicked, it will show the incompletePane
        else if(event.getSource() == incompleteButton){
            tab.makeVisible(incompletePane);
            System.out.println("Clicked incomplete tab button");
        }
    }

    @FXML
    void changeTitle(KeyEvent event) {
        TopBarFunctions topBox = new TopBarFunctions();
        // If the "enter" key was pressed when finished typing on the listTitle text field
        // It will update the text inside the text field and its corresponding button
        if(event.getCode().equals(KeyCode.ENTER)){
            topBox.title(listTitle, planButton);
        }
    }


    @FXML
    void enteredItemAction(ActionEvent event){
        // This method is responsible for processing the input from the itemText text field and the date picker
        if(event.getSource() == enterButton){
            BottomBarFunctions bottomBox = new BottomBarFunctions();

            // If a user selected an item on the list view
            // The index of that particular item will be stored into the "index"
            int index = itemList.getSelectionModel().getSelectedIndex();
            if(index >= 0)
                // If the user selected an item, and changed the values of it
                // This will replace the old input with the new input, once the "enterButton" was clicked
                item = bottomBox.replaceItem(datePicker, itemText, itemList, item, index);

            else
                // If the user did not select any item
                // It will just add an item normally into the list view
                item = bottomBox.addItem(datePicker, itemText, itemList, item);
        }
    }

    @FXML
    void removeButton(ActionEvent event){
        // This method is responsible for the delete buttons inside the application

        // If the user clicked the "deleteItemButton" located at the bottom left
        // after they selected an item inside the list view
        // That specific item will be removed from the list view and the "item" array list
        if(event.getSource() == deleteItemButton){
            BottomBarFunctions bottomBox = new BottomBarFunctions();
            int index = itemList.getSelectionModel().getSelectedIndex();
            item = bottomBox.removeItem(index, itemList, item);
            System.out.println("Remove");
        }

        // If the user clicked the "deleteButton" located at the top right of the window
        // This will clear all the items in the list view and the "item" array list
        else if(event.getSource() == deleteButton){
            TopBarFunctions topBox = new TopBarFunctions();
            item = topBox.clearList(itemList, item);

            itemList_completed.getItems().clear();

            System.out.println("Cleared the list");
        }

    }


    @FXML
    void incomplete(MouseEvent event) {
        // This method will sort all the incomplete task to be shown when the "incompleteButton" was clicked
        if(event.getSource() == incompleteButton){
            SortTask sort = new SortTask();
            // Stores all the unmarked item into the "uncheckedItem array list"
            uncheckedItem = sort.incomplete(uncheckedItem, item, checkedItem, itemList_incomplete);
        }
    }


    @FXML
    void fileActionButton(ActionEvent event) {
        File file = new File();

        // If the user clicked the load button
        // A file chooser will prompt the user to select a text file
        // This will read the text file and show all the items inside it into the list view
        // It will also add the items inside the text file into the "items" array list
        if(event.getSource() == loadButton){
            file.loadList(itemList, item);
        }

        // If the user clicked the save button
        // A file chooser will prompt user to type the name of the file and the location
        // This will get the items inside the "items" array list and put it into the text file
        else if(event.getSource() == saveButton){
            file.saveList(item);
        }
    }
}

