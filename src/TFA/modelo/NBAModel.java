package TFA.modelo;

import TFA.modelo.datafinder.StrategyContext;
import TFA.modelo.datafinder.StrategyTeam;
import TFA.modelo.datafinder.StrategyTeamPlayers;
import TFA.modelo.datafinder.StrategyTeamStandings;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;

import static TFA.modelo.Result.WIN;
import static TFA.modelo.Result.LOSS;

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
            if (team.getBoolean("nbaFranchise")) {
                teamsMap.put(team.getString("name"), team.getInt("id"));
            }
        }
    }

    public ArrayList<String> getTeamsMap() {
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
        JSONObject object = strategyContext.executeRequest();
        JSONObject team = object.getJSONArray("response").getJSONObject(0);
        JSONObject teamLeagueData = team.getJSONObject("leagues").getJSONObject("standard");
        return new Team(
                team.getInt("id"),
                team.getString("name"),
                team.getString("code"),
                teamLeagueData.getString("conference"),
                team.get("logo").toString()
        );
    }

    public void setTeamStanding(Team teamToDisplay) {
        System.out.println("Obteniendo información sobre la posición del equipo...");
        strategyContext.setStrategy(new StrategyTeamStandings(teamToDisplay.getId()));
        JSONObject object = strategyContext.executeRequest();
        JSONObject teamLeagueData = object.getJSONArray("response").getJSONObject(0).getJSONObject("division");
        teamToDisplay.setRank(teamLeagueData.getInt("rank"));
        teamToDisplay.addResult(WIN, teamLeagueData.getInt("win"));
        teamToDisplay.addResult(LOSS, teamLeagueData.getInt("loss"));
    }
}
