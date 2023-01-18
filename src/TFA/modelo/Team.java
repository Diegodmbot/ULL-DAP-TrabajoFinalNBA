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
    // Posición del equipo (StandingTeamStandings)
    private int rank;
    HashMap<Result, Integer> results;
    // Estadísticas del equipo (StrategyTeamStats)
    private int gamesPlayed;
    private int points;
    private float fieldGoalsPercentage;
    private float threePointsPercentage;
    private float freeThrowsPercentage;

    // Partidos del equipo (StrategyGames)
    //private ArrayList<Match> matches;

    public Team(int teamId, String fullName, String shortName, String conference, String crestUrl) {
        this.id = teamId;
        this.name = fullName;
        this.shortName = shortName;
        this.conference = conference;
        this.crestUrl = crestUrl;
        this.players = new ArrayList<>();
        this.results = new HashMap<>();
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getShortName() {
        return shortName;
    }

    public String getConference() {
        return conference;
    }

    public String getCrestUrl() {
        return crestUrl;
    }

    public ArrayList<Player> getPlayers() {
        return players;
    }

    public void setPlayers(ArrayList<Player> players) {
        this.players = players;
    }

    public void setRank(int rank) {
        this.rank = rank;
    }

    public void addResult(Result result, int value) {
        this.results.put(result, value);
    }
}
