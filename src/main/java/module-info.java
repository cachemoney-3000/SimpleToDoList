module baseline.todo {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.graphics;
    requires lombok;
    requires com.google.gson;


    opens baseline to javafx.fxml;
    exports baseline;
}