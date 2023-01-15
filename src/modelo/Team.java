package modelo;

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
}
