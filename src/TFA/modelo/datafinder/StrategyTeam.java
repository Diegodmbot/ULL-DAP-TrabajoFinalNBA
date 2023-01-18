package TFA.modelo.datafinder;

import java.net.URI;
import java.net.http.HttpRequest;

public class StrategyTeam extends DataFetcherStrategy {
    final String teamUrl = "/teams?";
    String teamId = "";

    public StrategyTeam() {
    }
    public StrategyTeam(int teamId) {
        this.teamId = "id=" + teamId;
    }

    @Override
    public void setRequest() {
        request = HttpRequest.newBuilder()
                .GET()
                .uri(URI.create(apiApiSportsUrl + teamUrl))
                .header("X-RapidAPI-Key", TOKEN)
                .build();
    }
}

