package modelo.datafinder;

import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import org.json.JSONObject;

public abstract class DataFetcherStrategy {
    // Se usa la API Api-Sports de RapidAPI para obtener los datos
    // https://api-sports.io/documentation/nba/v2
    // https://rapidapi.com/api-sports/api/api-nba
    final String apiApiSportsUrl = "https://api-nba-v1.p.rapidapi.com";
    final String TOKEN = "X";
    // Atributos que se usan para la conexion
    HttpRequest request;

    public abstract void setRequest();
    public JSONObject executeRequest() {
        try {

        setRequest();
        HttpResponse<String> response = HttpClient.newHttpClient().send(request, HttpResponse.BodyHandlers.ofString());
        System.out.println("Status: " + response.statusCode());
        return new JSONObject(response.body());
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    // Se usa para obtener el año de la temporada actual
    protected int getSeasonYear() {
        LocalDate dateObj = LocalDate.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy");
        return Integer.parseInt(dateObj.format(formatter)) - 1;
    }
}
