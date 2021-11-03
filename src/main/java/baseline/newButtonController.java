package baseline;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class newButtonController implements Initializable {
    private Controller controller = new Controller();

    @FXML
    public Button newButton;

    @FXML
    void tabButtonAction(ActionEvent event){

    }

    @FXML
    void addList(MouseEvent event) throws IOException {
        AnchorPane p = new AnchorPane();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("newPane.fxml"));
        controller.leftPane.getChildren().add(loader.load());
    }


    @Override
    public void initialize(URL location, ResourceBundle resources) {
    }


}
