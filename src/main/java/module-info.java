module com.example.eindopdracht {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.controlsfx.controls;
    requires com.dlsc.formsfx;
    requires org.kordamp.bootstrapfx.core;
    requires java.sql;

    opens com.example.eindopdracht to javafx.fxml;
    exports com.example.eindopdracht;
    exports com.example.eindopdracht.controllers;
    opens com.example.eindopdracht.controllers to javafx.fxml;
    opens com.example.eindopdracht.database.models;
    opens com.example.eindopdracht.database.classes;
}