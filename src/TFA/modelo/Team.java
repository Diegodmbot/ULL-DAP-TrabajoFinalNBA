package TFA.modelo;

import java.util.ArrayList;
import java.util.HashMap;

public class Team {
    // Datos generales del equipo (StrategyTeam)
    private int id;
    private String name;
    private String shortName;
    private String conference;
    private String crestUrl;
    // Jugadores del equipo (StrategyTeamPlayers)
    private ArrayList<Player> players;
    // Posición del equipo (StrategyTeamStandings)
    private int ranks;
    // Estadísticas del equipo (StrategyTeamStats)
    private int gamesPlayed;
    private int points;
    private float fieldGoalsPercentage;
    private float threePointsPercentage;
    private float freeThrowsPercentage;

    // Partidos del equipo (StrategyGames)
    HashMap<Result, Integer> results;

    public Team(int teamId, String fullName, String shortName) {
        this.id = teamId;
        this.name = fullName;
        this.shortName = shortName;
        this.players = new ArrayList<>();
        this.results = new HashMap<>();
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getShortName() {
        return shortName;
    }

    public void setShortName(String shortName) {
        this.shortName = shortName;
    }

    public String getConference() {
        return conference;
    }

    public void setConference(String conference) {
        this.conference = conference;
    }

    public String getCrestUrl() {
        return crestUrl;
    }

    public void setCrestUrl(String crestUrl) {
        this.crestUrl = crestUrl;
    }
}
