package modelo.datafinder;

import java.io.IOException;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public abstract class DataFetcherStrategy {
    // Se usa la API Api-Sports de RapidAPI para obtener los datos
    // https://api-sports.io/documentation/nba/v2
    // https://rapidapi.com/api-sports/api/api-nba
    final String apiApiSportsUrl = "https://api-nba-v1.p.rapidapi.com";
    final String TOKEN = "beb98c0c7dmsh1e890eea4e48b1dp1f6bf6jsnee9cce3c59cd";
    // Atributos que se usan para la conexion
    HttpRequest request;

    public abstract void setRequest();
    public void executeRequest() throws IOException, InterruptedException {
        setRequest();
        HttpResponse<String> response = HttpClient.newHttpClient().send(request, HttpResponse.BodyHandlers.ofString());
        System.out.println(response.body());
    }
    // Se usa para obtener la fecha de hoy en el formato que se necesita
    protected String getTodayDate() {
        LocalDate dateObj = LocalDate.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        return dateObj.format(formatter);
    }
    // Se usa para obtener el año de la temporada actual
    protected int getSeasonYear() {
        LocalDate dateObj = LocalDate.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy");
        return Integer.parseInt(dateObj.format(formatter)) - 1;
    }
}
