package modelo.datafinder;

import java.io.IOException;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public abstract class DataFetcherStrategy {
    // Se usan dos apis distintas.
    // Para obtener los datos de los jugadores y equipos:
    // https://api-sports.io/documentation/nba/v2
    // https://rapidapi.com/api-sports/api/api-nba
    final String apiUrl = "https://api-nba-v1.p.rapidapi.com";
    final String TOKEN = "X";
    // Para obtener los datos de los partidos:
    // https://www.balldontlie.io/#introduction
    final String apiGamesUrl = "https://www.balldontlie.io/api/v1";
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
