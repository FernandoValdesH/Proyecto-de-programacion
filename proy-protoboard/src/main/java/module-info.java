module com.example.proyprotoboard {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.proyprotoboard to javafx.fxml;
    exports com.example.proyprotoboard;
}