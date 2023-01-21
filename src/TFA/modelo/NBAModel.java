package TFA.modelo;

import TFA.modelo.datafinder.*;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;

public class NBAModel {
    private static NBAModel instance = null;
    private final HashMap<String, Integer> teamsMap;
    private StrategyContext strategyContext;

    private NBAModel() {
        teamsMap = new HashMap<>();
        strategyContext = new StrategyContext();
    }

    // Usamos un Singleton para que solo haya una instancia de la clase NBAModel
    public static NBAModel getInstance() {
        if (instance == null) {
            instance = new NBAModel();
        }
        return instance;
    }

    // Obtenemos la lista de equipos de la NBA
    public void setTeamList() {
        System.out.println("Obteniendo lista de equipos...");
        strategyContext.setStrategy(new StrategyTeam());
        JSONObject object = strategyContext.executeRequest();
        // Recorre el array de equipos y selecciona los equipos de la NBA
        for (int i = 0; i < object.getJSONArray("response").length(); i++) {
            JSONObject team = object.getJSONArray("response").getJSONObject(i);
            if (team.getBoolean("nbaFranchise") && !team.getBoolean("allStar")) {
                teamsMap.put(team.getString("name"), team.getInt("id"));
            }
        }
    }

    public ArrayList<String> getTeamsList() {
        return new ArrayList<>(teamsMap.keySet());
    }

    // Obtiene la lista de jugadores de un equipo
    public void setPlayersListFromTeam(Team teamToDisplay) {
        System.out.println("Obteniendo lista de jugadores...");
        strategyContext.setStrategy(new StrategyTeamPlayers(teamToDisplay.getId()));
        JSONObject object = strategyContext.executeRequest();
        ArrayList<Player> playersList = new ArrayList<>();
        JSONObject playerLeagueData;
        for (int i = 0; i < object.getJSONArray("response").length(); i++) {
            JSONObject player = object.getJSONArray("response").getJSONObject(i);
            playerLeagueData = player.getJSONObject("leagues").getJSONObject("standard");
            playersList.add(new Player(
                        player.getInt("id"),
                        player.getString("firstname"),
                        player.getString("lastname"),
                        playerLeagueData.get("pos").toString(),
                        playerLeagueData.get("jersey").toString()
            ));
        }
        teamToDisplay.setPlayers(playersList);
    }

    public Team getTeamByName(String selectedItem) {
        System.out.println("Obteniendo información general del equipo...");
        int teamId = teamsMap.get(selectedItem);
        strategyContext.setStrategy(new StrategyTeam(teamId));
//        System.out.println(strategyContext.get);
        JSONObject object = strategyContext.executeRequest();
        JSONObject team = object.getJSONArray("response").getJSONObject(0);
        JSONObject teamLocation = team.getJSONObject("leagues").getJSONObject("standard");
        return new Team(
                team.getInt("id"),
                team.getString("name"),
                team.getString("code"),
                teamLocation.getString("conference"),
                team.get("logo").toString()
        );
    }

    public void setTeamStanding(Team teamToDisplay) {
        System.out.println("Obteniendo información sobre la posición del equipo...");
        strategyContext.setStrategy(new StrategyTeamStandings(teamToDisplay.getId()));
        JSONObject object = strategyContext.executeRequest();
        JSONObject teamLeagueData = object.getJSONArray("response").getJSONObject(0).getJSONObject("conference");
        teamToDisplay.setRank(teamLeagueData.getInt("rank"));
        teamToDisplay.addResult("win", teamLeagueData.getInt("win"));
        teamToDisplay.addResult("loss", teamLeagueData.getInt("loss"));
    }

    public void setTeamStats(Team teamToDisplay) {
        System.out.println("Obteniendo estadísticas del equipo...");
        strategyContext.setStrategy(new StrategyTeamStats(teamToDisplay.getId()));
        JSONObject object = strategyContext.executeRequest();
        JSONObject teamStats = object.getJSONArray("response").getJSONObject(0);
        // puntos
        teamToDisplay.addTeamStats("points", teamStats.getInt("points"));
        // tiros de campo
        teamToDisplay.addTeamStats("fga", teamStats.getInt("fga"));
        teamToDisplay.addTeamStats("fgm", teamStats.getInt("fgm"));
        // tiros de 3
        teamToDisplay.addTeamStats("tpa", teamStats.getInt("tpa"));
        teamToDisplay.addTeamStats("tpm", teamStats.getInt("tpm"));
        // tiros libres
        teamToDisplay.addTeamStats("fta", teamStats.getInt("fta"));
        teamToDisplay.addTeamStats("ftm", teamStats.getInt("ftm"));
        // rebotes
        teamToDisplay.addTeamStats("offReb", teamStats.getInt("offReb"));
        teamToDisplay.addTeamStats("defReb", teamStats.getInt("defReb"));
        // robos
        teamToDisplay.addTeamStats("steals", teamStats.getInt("steals"));
        // perdidas
        teamToDisplay.addTeamStats("turnovers", teamStats.getInt("turnovers"));
    }
}
