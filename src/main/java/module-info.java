module main.weatherapp {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.controlsfx.controls;
    requires org.kordamp.ikonli.javafx;
    requires org.kordamp.bootstrapfx.core;
    requires io.github.cdimascio.dotenv.java;
    requires java.net.http;
    requires org.json;
    requires java.desktop;

    opens main.weatherapp to javafx.fxml;
    exports main.weatherapp;
}