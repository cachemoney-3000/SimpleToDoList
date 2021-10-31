package baseline;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import java.time.LocalDate;

public class datePickerController {
    @FXML
    private DatePicker datePicker;

    @FXML
    private Button dueDateButton;

    @FXML
    void dateEntered(MouseEvent event) {
        //Stage stage = (Stage) dueDateButton.getScene().getWindow();
        //stage.close();
    }

    public LocalDate date() {
        return datePicker.getValue();
    }
}
