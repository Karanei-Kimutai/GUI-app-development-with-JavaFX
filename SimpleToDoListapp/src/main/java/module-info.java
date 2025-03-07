module javafxprojects.simpletodolistapp {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.web;

    requires org.controlsfx.controls;
    requires com.dlsc.formsfx;
    requires org.kordamp.ikonli.javafx;
    requires org.kordamp.bootstrapfx.core;
    requires eu.hansolo.tilesfx;

    opens javafxprojects.simpletodolistapp to javafx.fxml;
    exports javafxprojects.simpletodolistapp;
}