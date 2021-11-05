/*
 * UCF COP3330 Fall 2021 Application Assignment 1 Solution
 * Copyright 2021 Joshua Samontanez
 */

package baseline;

import java.util.Objects;

public record Task(String date, String description){

    // Will get the date from the date picker
    public String getDate() {
        return date;
    }

    // Will get the description from the text field
    public String getDescription() {
        return description;
    }

    // This will process what is being shown into the list view and the array list
    @Override
    public String toString() {
         if (Objects.equals(description," ")){
            return null;
        }
        else if (Objects.equals(date, " ")) {
            return "Task: " + description;
        }
        else {
            return "Task: " + description + "   |Due Date: " + date ;
        }

    }
}
