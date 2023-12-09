module com.example.ap_project_stick_hero {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.web;

    requires org.controlsfx.controls;
    requires com.dlsc.formsfx;
    requires net.synedra.validatorfx;
    requires org.kordamp.ikonli.javafx;
    requires org.kordamp.bootstrapfx.core;
    requires com.almasb.fxgl.all;
    requires javafx.media;
    requires junit;

    opens com.example.ap_project_stick_hero to javafx.fxml;
    exports com.example.ap_project_stick_hero;
}