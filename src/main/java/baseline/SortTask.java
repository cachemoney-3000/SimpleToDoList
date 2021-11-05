/*
 * UCF COP3330 Fall 2021 Application Assignment 1 Solution
 * Copyright 2021 Joshua Samontanez
 */

package baseline;

import javafx.collections.ObservableList;
import javafx.scene.control.ListView;

public class SortTask {
    public ObservableList<Task> incomplete(ObservableList<Task> uncheckedItem, ObservableList<Task> temp,
                           ObservableList<Task> checkedItem, ListView<Task> itemList_incomplete){
        // Will clear all the items inside the list view, so the items will not be duplicated
        itemList_incomplete.getItems().clear();
        // Also clear all the items inside the uncheckedItem array list
        uncheckedItem.clear();
        // Will remove all the items that is present on the checkedItem array list
        temp.removeAll(checkedItem);
        // Add all the remaining items into the uncheckedItem list
        uncheckedItem.addAll(temp);

        // Loop through the uncheckedItem list
        for (Task task : uncheckedItem) {
            // Show all the items inside that list into its corresponding list view
            itemList_incomplete.getItems().add(task);
        }

        return uncheckedItem;
    }

    public ObservableList<Task> all(ListView<Task> all_item, ObservableList<Task> item){
        // Will always clear all the items stored in the list view, so it will not be duplicated
        all_item.getItems().clear();

        // Will keep adding all the items from the observable list into the list view
        // This will make the items visible when the "all" button is clicked
        for (Task task : item) {
            System.out.println(item);
            all_item.getItems().add(task);
        }

        return item;
    }
}
