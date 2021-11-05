module baseline.todo {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.graphics;


    opens baseline to javafx.fxml;
    exports baseline;
}