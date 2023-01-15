package modelo.datafinder;

import java.net.URI;
import java.net.http.HttpRequest;

public class StrategyGames extends DataFetcherStrategy {

    final String currentPages = "current_pages=100";
    final String gamesUrl = "/games?";
    //
    String season;
    String teamId;

    public StrategyGames(int teamId) {
        this.season = "season=" + getSeasonYear() ;
        this.teamId = "team=" + teamId;
    }
    @Override
    public void setRequest() {
        request = HttpRequest.newBuilder()
                .GET()
                .uri(URI.create(apiApiSportsUrl + gamesUrl + season + "&" + teamId))
                .header("X-RapidAPI-Key", TOKEN)
                .build();
    }
}

