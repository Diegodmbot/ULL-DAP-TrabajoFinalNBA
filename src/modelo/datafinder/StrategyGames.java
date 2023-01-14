package modelo.datafinder;

import java.net.URI;
import java.net.http.HttpRequest;

public class StrategyGames extends DataFetcherStrategy {

    final String currentPages = "current_pages=100";
    final String gamesUrl = "/games?";
    //
    String date;
    String teamId;

    public StrategyGames(int teamId) {
        this.teamId = "team_ids[]=" + teamId;
        this.date = "start_date=" + getTodayDate();
    }
    @Override
    public void setRequest() {
        request = HttpRequest.newBuilder()
                .GET()
                .uri(URI.create(apiGamesUrl + gamesUrl + teamId + "&" + date + "&" + currentPages))
                .build();
    }
}

