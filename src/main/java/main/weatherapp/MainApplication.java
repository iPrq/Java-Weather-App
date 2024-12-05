package main.weatherapp;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.awt.*;
import java.io.IOException;

public class MainApplication extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(MainApplication.class.getResource("WeatherAppGUI.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        stage.setTitle("Weather App");
        stage.setResizable(false);
        scene.setFill(Color.TRANSPARENT);
        stage.initStyle(StageStyle.TRANSPARENT);
        stage.setScene(scene);
        ((MainAppController)fxmlLoader.getController()).init(stage);
        stage.show();
    }

    public static void run() {
        launch();
    }
}