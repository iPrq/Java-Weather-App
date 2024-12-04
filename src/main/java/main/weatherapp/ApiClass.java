package main.weatherapp;

import io.github.cdimascio.dotenv.Dotenv;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class ApiClass {
    public static void apicall() {
        Dotenv dotenv = Dotenv.load();
        String apiKey = dotenv.get("WEATHER_API_KEY");
        String location = "Bangalore";
        String apiuri = "http://api.openweathermap.org/data/2.5/weather?q=" + location + "&appid=" + apiKey;
        try {
            String response = makeGetRequest(apiuri);
            System.out.println("Response: "+ response);
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
}
