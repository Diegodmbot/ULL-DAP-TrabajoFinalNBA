package modelo.datafinder;

import java.net.URI;
import java.net.http.HttpRequest;

public class StrategyTeamStats extends DataFetcherStrategy {
    final String standingUrl = "/standings?";
    final String league = "league=standard";
    String season;
    String teamId;

    public StrategyTeamStats(int id) {
        this.season = "season=" + getSeasonYear();
        this.teamId = "team=" + id;
    }

    @Override
    public void setRequest() {
        request = HttpRequest.newBuilder()
                .GET()
                .uri(URI.create(apiUrl + standingUrl + league + "&" + season + "&" + teamId))
                .header("X-RapidAPI-Key", TOKEN)
                .build();
    }
}
