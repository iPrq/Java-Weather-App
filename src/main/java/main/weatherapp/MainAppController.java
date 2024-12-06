package main.weatherapp;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.awt.event.MouseEvent;
import java.time.Instant;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;

import java.util.HashMap;



public class MainAppController {

    @FXML
    private Label weatherdeslbl;

    @FXML
    private Label ftemplbl;

    @FXML
    private Label humlbl;

    @FXML
    private Pane locationimg;

    @FXML
    private Label locationlbl;

    @FXML
    private Pane mainPane, titlePane;

    @FXML
    private Label maxtemplbl;

    @FXML
    private Label mintemplbl;

    @FXML
    private Label templbl;

    @FXML
    private ImageView weather_icon, close, minimise;

    @FXML
    private Label weatherlbl;

    @FXML
    private Label windlbl,timelbl;
    private double x, y;
    private HashMap<String, Object> weatherdata;
    private  ApiClass Api ;
    private String time;
    private boolean day;

    public void init(Stage stage) {
        titlePane.setOnMousePressed(MouseEvent -> {
            x = MouseEvent.getSceneX();
            y = MouseEvent.getSceneY();
        });
        titlePane.setOnMouseDragged(MouseEvent -> {
            stage.setX(MouseEvent.getScreenX() - x);
            stage.setY(MouseEvent.getScreenY() - y);
        });
        close.setOnMouseClicked(MouseEvent -> stage.close());
        minimise.setOnMouseClicked(MouseEvent -> stage.setIconified(true));
    }
    @FXML
    public void locationset() {
        weatherdata = new HashMap<>();
        String location = "Chicago";
        Api = new ApiClass();
        Api.ApiClass(location);
        weatherdata = Api.returnapidata();
        setData();
    }

    public void setData() {
        if (weatherdata != null) {
            weatherlbl.setText(String.valueOf(weatherdata.get("main_weather")));
            locationlbl.setText(weatherdata.get("city") + ", " + weatherdata.get("country"));
            weatherdeslbl.setText(String.valueOf(weatherdata.get("main_description")));
            ftemplbl.setText(String.valueOf(weatherdata.get("feels_temp")));
            templbl.setText(String.valueOf(weatherdata.get("temp")));
            maxtemplbl.setText(String.valueOf(weatherdata.get("temp_max")));
            mintemplbl.setText(String.valueOf(weatherdata.get("temp_min")));
            humlbl.setText(String.valueOf(weatherdata.get("humidity")));
            weatherbg(String.valueOf(weatherdata.get("main_weather")));

            time = returndaynight((Integer) weatherdata.get("timezone"));
            timelbl.setText(time);
            if(Integer.parseInt(time.substring(0,2)) < 7 || Integer.parseInt(time.substring(0,2)) > 19) {
                day = false;
            }
            else {
                day = true;
            }
        }

    }
    public void weatherbg(String weather) {
        if (!day) {
            switch (weather) {
                case "Thunderstorm":
                    break;
                case "Haze":
                    break;
                case "Rain":
                    break;
                case "Snow":
                    break;
                case "Clear":
                    break;
                case "Clouds":
                    break;
                default:
                    break;
            }
        }
        else {
            switch (weather) {
                case "Thunderstorm":
                    break;
                case "Haze":
                    break;
                case "Rain":
                    break;
                case "Snow":
                    break;
                case "Clear":
                    break;
                case "Clouds":
                    break;
                default:
                    break;
            }
        }
    }
    String returndaynight(int timezone) {
        Instant instant = Instant.now();
        ZoneId zoneid = ZoneId.ofOffset("UTC",java.time.ZoneOffset.ofTotalSeconds(timezone));
        ZonedDateTime zonedDateTime = ZonedDateTime.ofInstant(instant,zoneid);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm");
        return zonedDateTime.format(formatter);
    }
}