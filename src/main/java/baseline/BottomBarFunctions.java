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

    public Task addItem(DatePicker datePicker, TextField itemText){
        String date;
        String description = itemText.getText();

        // If there are no dates entered, make the date string blank
        if(datePicker.getValue() == null){
            date = " ";
        }
        // This will set the format for the date picker
        else{
            date = datePicker.getValue().format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
        }

        var newItem = addItemHelper(date, description);

        // Clear any entries from the date picker
        datePicker.getEditor().clear();
        datePicker.setValue(null);

        // Remove any entry from the text field
        itemText.setText("");

        return newItem;
    }

    public Task addItemHelper(String date, String description){
        return new Task(date, description);
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
