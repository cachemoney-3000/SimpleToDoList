/*
 * UCF COP3330 Fall 2021 Application Assignment 1 Solution
 * Copyright 2021 Joshua Samontanez
 */

package baseline;

import javafx.collections.ObservableList;
import javafx.scene.control.DatePicker;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;

import java.time.format.DateTimeFormatter;

public class BottomBarFunctions {

    public ObservableList<Task> addItem(DatePicker datePicker, TextField itemText, ListView<Task> itemList, ObservableList<Task> item){
        String date;

        // If there are no dates entered, make the date string blank
        if(datePicker.getValue() == null)
            date = " ";

        // This will set the format for the date picker
        else
            date = datePicker.getValue().format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));

        // Will not add an item into the list view if the text field is blank
        if (itemText.getText().equals(""))
            return item;

        // This will help read the input from the date picker and text field
        var newItem = new Task(date, itemText.getText());

        // Add the new item into the list view and to the observable array
        itemList.getItems().add(newItem);
        item.add(newItem);

        // Clear any entries from the date picker
        datePicker.getEditor().clear();
        datePicker.setValue(null);

        // Remove any entry from the text field
        itemText.setText("");

        return item;
    }

    public ObservableList<Task> replaceItem(DatePicker datePicker, TextField itemText, ListView<Task> itemList,
                                            ObservableList<Task> item, int index){
        String date;

        if(datePicker.getValue()  == null)
            // If there are no dates entered, make the date string blank
            date = " ";

        else
            // This will set the format for the date picker
            date = datePicker.getValue().format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));


        if (itemText.getText().equals("")){
            return item;
        }

        // Call the task object to get the information from the date picker and the text field
        var newItem = new Task(date, itemText.getText());

        // Remove the previous entered item on the list view
        itemList.getItems().remove(index);
        // Remove the previously entered item on the observable array
        item.remove(index);

        // Add the newly edited item on the list view, the index will determine where to insert the item
        itemList.getItems().add(index, newItem);
        // Add the newly edited item to the observable array
        item.add(index, newItem);

        // Clear the date picker entry
        datePicker.getEditor().clear();
        // Remove any entry from the date picker
        datePicker.setValue(null);

        return item;
    }

    public ObservableList<Task> removeItem(int index, ListView<Task> itemList, ObservableList<Task> item){
        // This method will remove the item from the list view and the array list once the delete button is clicked
        if(index >= 0){
            itemList.getItems().remove(index);
            item.remove(index);
        }

        return item;
    }
}
