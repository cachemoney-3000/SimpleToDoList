/*
 * UCF COP3330 Fall 2021 Application Assignment 1 Solution
 * Copyright 2021 Joshua Samontanez
 */

package baseline;

import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class ToDoListApplication extends javafx.application.Application {
    @Override
    public void start(Stage stage) {
        try{
            // load the fxml file
            FXMLLoader fxmlLoader = new FXMLLoader(ToDoListApplication.class.getResource("toDoApplication.fxml"));
            // create a new scene
            Scene scene = new Scene(fxmlLoader.load());
            // the window cannot be resized
            stage.setResizable(false);

            // title of the application
            stage.setTitle("To-Do");
            stage.setScene(scene);
            stage.show();
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        launch();
    }
}