package baseline;

import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class Application extends javafx.application.Application {
    @Override
    public void start(Stage stage) {
        try{
            // load the fxml file
            FXMLLoader fxmlLoader = new FXMLLoader(Application.class.getResource("tda.fxml"));
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