module com.example.code {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.graphics;
    requires java.sql;


    opens com.example.code to javafx.fxml;
    exports com.example.code;
}