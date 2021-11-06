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
    private Button allButton;

    @FXML
    private AnchorPane incompletePane;

    @FXML
    private ListView<Task> itemList;

    @FXML
    private ListView<Task> itemList_completed;

    @FXML
    private ListView<Task> itemList_incomplete;

    @FXML
    private ListView<Task> all_item;

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
    private AnchorPane allPane;

    @FXML
    private MenuItem saveButton;

    private ObservableList<Task> item = FXCollections.observableArrayList();
    // Stores the checked item, useful for shall #9
    private final ObservableList<Task> checkedItem = FXCollections.observableArrayList();
    // Stores all the unchecked item, useful for shall #9
    private ObservableList<Task> uncheckedItem = FXCollections.observableArrayList();

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        // Make the plan pane to be shown first when the program starts
        planPane.toFront();

        // Initialize object is where the important initializing methods are stored
        Initialize init = new Initialize();

        // Shall #9
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
        // When the "planButton" is clicked, it will show the planPane
        if(event.getSource()==planButton){
            // Shall #1, must have a single list of items
            planPane.toFront();
            System.out.println("Clicked plan tab button");
        }
        // When the "completedButton" is clicked, it will show the completedPane
        else if(event.getSource() == completedButton){
            // Shall #12
            completedPane.toFront();
            System.out.println("Clicked completed tab button");
        }
        // When the "incompleteButton" is clicked, it will show the incompletePane
        else if(event.getSource() == incompleteButton){
            // Shall #11
            incompletePane.toFront();
            System.out.println("Clicked incomplete tab button");
        }
        else if(event.getSource() == allButton){
            allPane.toFront();
            System.out.println("All is clicked");
        }
    }

    @FXML
    void changeTitle(KeyEvent event) {
        TopBarFunctions topBox = new TopBarFunctions();
        // If the "enter" key was pressed when finished typing on the listTitle text field
        // It will update the text inside the text field and its corresponding button
        if(event.getCode().equals(KeyCode.ENTER)){
            topBox.title(listTitle, planButton);
            System.out.println("Title changed");
        }
    }


    @FXML
    // Shall #2, Shall #3: method for adding description and due date into the listview
    // Due dates are optional, but user can't add an item if there is no description
    // Due date will be a valid date and in the correct format
    // Shall #4: this method will also add an item into the list
    void enteredItemAction(ActionEvent event){
        // This method is responsible for processing the input from the itemText text field and the date picker
        if(event.getSource() == enterButton){
            BottomBarFunctions bottomBox = new BottomBarFunctions();

            // If a user selected an item on the list view
            // The index of that particular item will be stored into the "index"
            int index = itemList.getSelectionModel().getSelectedIndex();
            if(index >= 0){
                // Shall #7 & #8, the user must edit the description and click the enter button to update the item

                Task newItem = bottomBox.addItem(datePicker, itemText);
                // Remove the previously entered item on the observable array
                item = bottomBox.replaceItemHelper(index, item, newItem);
                // Remove the previous entered item on the list view
                itemList.getItems().remove(index);
                // Add the newly edited item on the list view, the index will determine where to insert the item
                itemList.getItems().add(index, newItem);
                // Add the newly edited item to the observable array

                System.out.println("Item replaced");
            }

            else{
                // Will not add an item into the list view if the text field is blank
                if (itemText.getText().equals("")) {
                    System.out.println("Text field is blank");
                }
                else {
                    Task newItem = bottomBox.addItem(datePicker, itemText);

                    // Add the new item into the list view and to the observable array
                    itemList.getItems().add(newItem);
                    item.add(newItem);

                    System.out.println("Item added");
                }
            }

        }
    }

    // This method is responsible for the delete buttons inside the application
    @FXML
    void removeButton(ActionEvent event){
        // Satisfies shall #5
        // If the user clicked the "deleteItemButton" located at the bottom left
        // after they selected an item inside the list view
        // That specific item will be removed from the list view and the "item" array list
        if(event.getSource() == deleteItemButton){
            BottomBarFunctions bottomBox = new BottomBarFunctions();
            int index = itemList.getSelectionModel().getSelectedIndex();
            item = bottomBox.removeItem(index, item);
            itemList.getItems().remove(index);
            System.out.println("Item removed");
        }

        // Satisfies shall #6
        // If the user clicked the "deleteButton" located at the top right of the window
        // This will clear all the items in the list view and the "item" array list
        else if(event.getSource() == deleteButton){
            TopBarFunctions topBox = new TopBarFunctions();
            item = topBox.clearList(item);

            // Clear all stored items from different lists
            itemList.getItems().clear();
            itemList_completed.getItems().clear();
            itemList_incomplete.getItems().clear();

            System.out.println("List cleared");
        }

    }


    @FXML
    void incomplete(MouseEvent event) {
        // This method will sort all the incomplete task to be shown when the "incompleteButton" was clicked
        if(event.getSource() == incompleteButton){
            SortTask sort = new SortTask();

            // Creates a temporary list and copy all the elements from the "item" list into the "temp" list
            ObservableList<Task> temp = FXCollections.observableArrayList();
            temp.addAll(item);

            // Will clear all the items inside the list view, so the items will not be duplicated
            itemList_incomplete.getItems().clear();
            // Stores all the unmarked item into the "uncheckedItem array list"
            uncheckedItem = sort.incomplete(uncheckedItem, temp, checkedItem);

            // Loop through the uncheckedItem list
            for (Task task : uncheckedItem) {
                // Show all the items inside that list into its corresponding list view
                itemList_incomplete.getItems().add(task);
            }
        }
    }

    @FXML
    void showAll(MouseEvent event) {
        // Satisfies shall #10
        // When the "All" button is clicked, it will show all the items that was created
        if(event.getSource() == allButton) {
            SortTask sort = new SortTask();
            item = sort.all(all_item, item);
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
            // Shall #14
            file.loadList(itemList, item);
            System.out.println("File loaded");
        }

        // If the user clicked the save button
        // A file chooser will prompt user to type the name of the file and the location
        // This will get the items inside the "items" array list and put it into the text file
        else if(event.getSource() == saveButton){
            // Shall #13
            file.saveList(item);
            System.out.println("File saved");
        }
    }
}

