package main.weatherapp;

import io.github.cdimascio.dotenv.Dotenv;
import org.json.JSONObject;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.HashMap;

public class ApiClass {
    HashMap<String,Object> apidata;
    void ApiClass(String location) {
        Dotenv dotenv = Dotenv.load();
        String apiKey = dotenv.get("WEATHER_API_KEY");
        String apiuri = "http://api.openweathermap.org/data/2.5/weather?q=" + location + "&appid=" + apiKey;
        try {
            String response = makeGetRequest(apiuri);
            System.out.println("Response: "+ response);
            Apidata(response);
        } catch (Exception e) {
            System.out.println("Error");
        }
    }

    public static String makeGetRequest(String apiuri) throws IOException, InterruptedException {
        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder().uri(URI.create(apiuri)).GET().build();
        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
        return response.body();
    }
    public void Apidata(String jsonresponse) {
        apidata = new HashMap<>();
        JSONObject jsonObject = new JSONObject(jsonresponse);

        JSONObject main = jsonObject.getJSONObject("main");
        apidata.put("temp",main.getDouble("temp"));
        apidata.put("feels_temp",main.getDouble("feels_like"));
        apidata.put("humidity",main.getDouble("feels_like"));
        apidata.put("temp_min",main.getDouble("temp_min"));
        apidata.put("temp_max",main.getDouble("temp_max"));

        JSONObject wind = jsonObject.getJSONObject("wind");
        apidata.put("wind_speed",wind.getDouble("speed"));
        apidata.put("wind_deg",wind.getDouble("deg"));

        JSONObject clouds = jsonObject.getJSONObject("clouds");
        apidata.put("clouds_all",clouds.getInt("all"));

        JSONObject weather = jsonObject.getJSONArray("weather").getJSONObject(0);
        apidata.put("main_weather",weather.getString("main"));
        apidata.put("main_description",weather.getString("description"));
        apidata.put("city",jsonObject.getString("name"));
        apidata.put("timezone",jsonObject.getInt("timezone"));

        JSONObject sys = jsonObject.getJSONObject("sys");
        apidata.put("country",sys.getString("country"));

    }
    public  HashMap<String,Object> returnapidata() {
        return apidata;
    }
}
