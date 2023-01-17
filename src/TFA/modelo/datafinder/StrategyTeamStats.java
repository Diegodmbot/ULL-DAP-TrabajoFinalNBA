package TFA.modelo.datafinder;

import java.net.URI;
import java.net.http.HttpRequest;
//teams/statistics?id=1&season=2020
public class StrategyTeamStats extends DataFetcherStrategy {
    final String teamStatsUrl = "/teams/statistics?";
    String season;
    String teamId;

    public StrategyTeamStats(int id) {
        this.season = "season=" + getSeasonYear();
        this.teamId = "id=" + id;
    }

    @Override
    public void setRequest() {
        request = HttpRequest.newBuilder()
                .GET()
                .uri(URI.create(apiApiSportsUrl + teamStatsUrl + teamId + "&" + season))
                .header("X-RapidAPI-Key", TOKEN)
                .build();
    }
}

