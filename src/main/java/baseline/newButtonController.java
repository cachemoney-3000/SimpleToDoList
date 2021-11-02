package baseline;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class newButtonController implements Initializable {
    private Controller main;

    @FXML
    public Button newButton;


    @FXML
    void tabButtonAction(ActionEvent event){

    }

    @FXML
    void addList(MouseEvent event) throws IOException {

    }

    public String clicked(){
        return "clicked";
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }

    public void setText(int text){
        newButton.setText("New List " + text);
    }

}
