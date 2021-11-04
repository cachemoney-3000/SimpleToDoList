/*
 * UCF COP3330 Fall 2021 Application Assignment 1 Solution
 * Copyright 2021 Joshua Samontanez
 */

package baseline;

import javafx.collections.ObservableList;
import javafx.scene.control.ListView;

public class SortTask {
    public ObservableList<Task> incomplete(ObservableList<Task> uncheckedItem, ObservableList<Task> item,
                           ObservableList<Task> checkedItem, ListView<Task> itemList_incomplete){

        // Will clear all the items inside the list view, so the items will not be duplicated
        itemList_incomplete.getItems().clear();
        // Also clear all the items inside the uncheckedItem array list
        uncheckedItem.clear();

        // Will remove all the items that is present on the checkedItem array list
        item.removeAll(checkedItem);
        // Add all the remaining items into the uncheckedItem list
        uncheckedItem.addAll(item);

        int i;
        // Loop through the uncheckedItem list
        for(i = 0; i < uncheckedItem.size(); i++){
            // Show all the items inside that list into its corresponding list view
            itemList_incomplete.getItems().add(uncheckedItem.get(i));
        }

        return uncheckedItem;
    }
}
