module com.example.eindopdracht3 {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.controlsfx.controls;
    requires org.kordamp.bootstrapfx.core;

    opens com.example.eindopdracht3 to javafx.fxml;
    exports com.example.eindopdracht3;
}