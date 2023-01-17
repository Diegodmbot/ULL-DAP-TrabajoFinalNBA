package TFA.modelo.datafinder;

import java.net.URI;
import java.net.http.HttpRequest;

public class StrategyTeamPlayers extends DataFetcherStrategy {
    final String playerUrl = "/players?";
    final String season = "season=" + getSeasonYear();
    String teamId;

    public StrategyTeamPlayers(int teamId) {
        this.teamId = "team=" + teamId;
    }
    @Override
    public void setRequest() {
        request = HttpRequest.newBuilder()
                .GET()
                .uri(URI.create(apiApiSportsUrl + playerUrl + teamId + "&" + season))
                .header("X-RapidAPI-Key", TOKEN)
                .build();
    }
}
