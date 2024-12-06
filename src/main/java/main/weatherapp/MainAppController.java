package main.weatherapp;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
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
    private Image image;

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
            humlbl.setText(String.valueOf(weatherdata.get("humidity"))+"%");
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
        day = false;
        if (!day) {
            switch (weather) {
                case "Thunderstorm":
                    image = new Image("file:src/main/resources/images/weathericons/thunderstormday.png");
                    weather_icon.setImage(image);
                    mainPane.setStyle("-fx-background-color: linear-gradient(to bottom, #0a0a0a, #3a3a3a)");
                    break;
                case "Rain":
                    image = new Image("file:src/main/resources/images/weathericons/rainyday.png");
                    weather_icon.setImage(image);
                    mainPane.setStyle("-fx-background-color: linear-gradient(to bottom, #647d8e, #3a506b, #2c3e50);");
                    break;
                case "Snow":
                    image = new Image("file:src/main/resources/images/weathericons/snowday.png");
                    weather_icon.setImage(image);
                    mainPane.setStyle("-fx-background-color: linear-gradient(to bottom, #ffffff, #d0e6f6, #a9cce3, #83b4d0);;");
                    break;
                case "Clear":
                    image = new Image("file:src/main/resources/images/weathericons/clearday.png");
                    weather_icon.setImage(image);
                    mainPane.setStyle("-fx-background-color: linear-gradient(to bottom, #87CEFA, #00BFFF, #1E90FF);");
                    break;
                case "Clouds":
                    image = new Image("file:src/main/resources/images/weathericons/cloudday.png");
                    weather_icon.setImage(image);
                    mainPane.setStyle("-fx-background-color: linear-gradient(to bottom, #B0C4DE, #D3D3D3, #A9A9A9);");
                    break;
                default:
                    image = new Image("file:src/main/resources/images/weathericons/mistday.png");
                    weather_icon.setImage(image);
                    mainPane.setStyle("linear-gradient(to bottom, #505050, #a9a9a9, #bdbbbb);");
                    break;
            }
        }
        else {
            switch (weather) {
                case "Thunderstorm":
                    image = new Image("file:src/main/resources/images/weathericons/thunderstormnight.png");
                    weather_icon.setImage(image);
                    mainPane.setStyle("-fx-background-color: linear-gradient(to bottom, #0a0a0a, #3a3a3a)");
                    break;
                case "Rain":
                    image = new Image("file:src/main/resources/images/weathericons/rainynight.png");
                    weather_icon.setImage(image);
                    mainPane.setStyle("-fx-background-color: linear-gradient(to bottom, #1c1c1c, #2c3e50, #3a506b, #2c3e50);");
                    break;
                case "Snow":
                    image = new Image("file:src/main/resources/images/weathericons/snownight.png");
                    weather_icon.setImage(image);
                    mainPane.setStyle("-fx-background-color: linear-gradient(to bottom, #0a0a0a, #1c1c1c, #3a3a3a, #87CEFA);");
                    break;
                case "Clear":
                    image = new Image("file:src/main/resources/images/weathericons/clearnight.png");
                    weather_icon.setImage(image);
                    mainPane.setStyle("-fx-background-color: linear-gradient(to bottom, #000033, #000066, #000099, #0000cc);");
                    break;
                case "Clouds":
                    image = new Image("file:src/main/resources/images/weathericons/cloudnight.png");
                    weather_icon.setImage(image);
                    mainPane.setStyle("-fx-background-color: linear-gradient(to bottom, #2c3e50, #3a3a3a, #1c1c1c);");
                    break;
                default:
                    image = new Image("file:src/main/resources/images/weathericons/mistday.png");
                    weather_icon.setImage(image);
                    mainPane.setStyle("linear-gradient(to bottom, #505050, #a9a9a9, #bdbbbb);");
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