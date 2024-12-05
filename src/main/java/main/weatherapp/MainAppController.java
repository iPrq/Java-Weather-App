package main.weatherapp;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.awt.event.MouseEvent;

public class MainAppController {

    @FXML
    private Label displaylbl;

    @FXML
    private Label ftemplbl;

    @FXML
    private Label humlbl;

    @FXML
    private Pane locationimg;

    @FXML
    private Label locationlbl;

    @FXML
    private Pane mainPane,titlePane;

    @FXML
    private Label maxtemplbl;

    @FXML
    private Label mintemplbl;

    @FXML
    private Label templbl;

    @FXML
    private ImageView weather_icon,close,minimise;

    @FXML
    private Label weatherlbl;

    @FXML
    private Label windlbl;
    private double x,y;

    public void  init(Stage stage) {
        titlePane.setOnMousePressed(MouseEvent -> {
            x = MouseEvent.getSceneX();
            y = MouseEvent.getSceneY();
        });
        titlePane.setOnMouseDragged(MouseEvent -> {
            stage.setX(MouseEvent.getScreenX()-x);
            stage.setY(MouseEvent.getScreenY()-y);
        });
        close.setOnMouseClicked(MouseEvent -> stage.close());
        minimise.setOnMouseClicked(MouseEvent -> stage.setIconified(true));
    }


}
