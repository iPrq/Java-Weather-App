package main.weatherapp;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.paint.Color;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.scene.layout.VBox;
import javafx.stage.Window;

import java.io.IOException;

public class MainApplication extends Application {
    public static String location;
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

    public static void locationstage() {
        Stage locationstage = new Stage();
        VBox EntryLayout = new VBox(10);
        EntryLayout.setPadding(new javafx.geometry.Insets(10));
        Label label = new Label("Enter Location:");
        TextField locationField = new TextField();
        locationField.setPromptText("Location");
        Button submitButton = new Button("Submit");
        submitButton.setOnAction(e -> { String location = locationField.getText(); locationstage.close(); MainApplication.location = locationField.getText();});
        EntryLayout.getChildren().addAll(label, locationField, submitButton);
        Scene secondaryScene = new Scene(EntryLayout, 300, 150);
        locationstage.setTitle("Enter Location");
        locationstage.setScene(secondaryScene);
        locationstage.setResizable(false);
        locationstage.initModality(Modality.APPLICATION_MODAL);
        locationstage.initOwner(Stage.getWindows().filtered(Window::isShowing).get(0));
        locationstage.showAndWait();
    }

    public static void showerror(String title,String message) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }

    public static void run() {
        launch();
    }
}