package modelo.datafinder;

import java.net.URI;
import java.net.http.HttpRequest;

public class StrategyPlayer extends DataFetcherStrategy {
    final String playerUrl = "/players?";
    String playerId;

    public StrategyPlayer(int playerId) {
        this.playerId = "id=" + playerId;
    }

    @Override
    public void setRequest() {
        request = HttpRequest.newBuilder()
                .GET()
                .uri(URI.create(apiApiSportsUrl + playerUrl + playerId))
                .header("X-RapidAPI-Key", TOKEN)
                .build();
    }
}

