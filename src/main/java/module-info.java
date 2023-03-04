module com.example.flights {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;

    opens com.example.flights.controller to javafx.fxml;
    exports com.example.flights.controller;

    opens com.example.flights.domain to javafx.fxml;
    exports com.example.flights.domain;

    opens com.example.flights to javafx.fxml;
    exports com.example.flights;
}