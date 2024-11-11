module com.example.taixesf {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;


    opens com.example.taixesf to javafx.fxml;
    opens com.example.taixesf.controller to javafx.fxml;

    exports com.example.taixesf;
    exports com.example.taixesf.controller;
}