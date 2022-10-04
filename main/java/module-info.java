module com.example.demojfx2 {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.demojfx2 to javafx.fxml;
    exports com.example.demojfx2;
}