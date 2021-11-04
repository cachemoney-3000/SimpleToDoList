/*
 * UCF COP3330 Fall 2021 Application Assignment 1 Solution
 * Copyright 2021 Joshua Samontanez
 */

package baseline;

import javafx.collections.ObservableList;
import javafx.scene.control.ListView;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.FileWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Scanner;

public class File {
    private void saveToText(java.io.File filename, ObservableList<Task> tasks) {
        try(FileWriter writer = new FileWriter(filename)){
            for(Task task : tasks){
                // This will get the items inside the observable list
                // Print those items into the text file
                // Separate the description and date with "/"
                writer.write(task.getDescription()+ "/" + task.getDate());
                writer.write("\n");
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    private List<Task> loadText(String filename) {
        List<Task> tasks = new ArrayList<>();
        java.io.File file = new java.io.File(filename);

        try(Scanner reader = new Scanner(file)){
            // Will keep scanning the file until there are no lines to scan
            while (reader.hasNextLine()) {
                // Stores one line into the "line" String
                String line = reader.nextLine();
                // Will store the description and date, with the help of line split
                String[] separator = line.split("/");

                String item = separator[0];
                String date = separator[1];

                if(Objects.equals(date, ""))
                    // If there are no dates entered, make the date string blank
                    date = " ";

                Task newItem = new Task(date, item);
                // Stores the scanned items and its corresponding due date into the array list
                tasks.add(newItem);
            }
        }catch (Exception e){
            e.printStackTrace();
        }

        return tasks;
    }

    public void loadList(ListView<Task> itemList, ObservableList<Task> item) {
        FileChooser file = new FileChooser();
        file.setTitle("Load");
        java.io.File selectedFile = file.showOpenDialog(null);
        List<Task> tasks;

        // After a text file was selected
        // It will scan the list inside it with the help of "loadText" method
        if(selectedFile != null){
            tasks = loadText(selectedFile.getAbsolutePath());
            for(Task t: tasks){
                // All the scanned items will then be shown into the list view
                itemList.getItems().add(t);
                // All the scanned items will also be added into the observable array list
                item.add(t);
            }
        }
    }

    public void saveList(ObservableList<Task> item){
        FileChooser fileChooser = new FileChooser();
        // Will set the default extension into a ".txt" file
        fileChooser.getExtensionFilters().addAll(new FileChooser.ExtensionFilter("txt","*.txt"));
        fileChooser.setTitle("Save");

        java.io.File fileName = fileChooser.showSaveDialog(new Stage());

        try{
            if(fileName != null){
                // Will take the file name, and the observable list as a parameters
                // It will then save all the items inside the list and print it into the text file
                saveToText(fileName, item);
            }
            System.out.println("saved");

        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
