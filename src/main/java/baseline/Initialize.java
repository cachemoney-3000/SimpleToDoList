/*
 * UCF COP3330 Fall 2021 Application Assignment 1 Solution
 * Copyright 2021 Joshua Samontanez
 */

package baseline;

import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.collections.ObservableList;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.CheckBoxListCell;

public class Initialize {
    public void listCheckBox(ListView<Task> itemList, ObservableList<Task> checkedItem, ListView<Task> itemList_completed){
        // This method is responsible for allowing check box inside our listview
        itemList.setCellFactory(CheckBoxListCell.forListView(task -> {
            BooleanProperty observable = new SimpleBooleanProperty();
            observable.addListener((obs, wasSelected, isNowSelected) -> {
                if (isNowSelected){
                    // If an item is marked selected, that item will be stored into the checkedItem list
                    checkedItem.add(task);
                    itemList_completed.getItems().add(task);
                }
                else {
                    // If an item was unmarked, it will remove that item from the "checkedItem" list
                    checkedItem.remove(task);
                    itemList_completed.getItems().remove(task);
                    System.out.println("Unchecked" + task);
                }
            });
            return observable;
        }));
    }

    public void listListener(ListView<Task> itemList, TextField itemText){
        // This method is responsible for getting the info of the selected item and put it into the text field
        // This will allow the user to edit the description and the due date of that item
        itemList.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue)
                -> {
            String input = String.valueOf(itemList.getSelectionModel().getSelectedItem());
            itemText.setText(input);
        });
    }
}
