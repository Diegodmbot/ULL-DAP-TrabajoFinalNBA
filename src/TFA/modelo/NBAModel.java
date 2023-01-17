package TFA.modelo;

import TFA.modelo.datafinder.StrategyContext;
import TFA.modelo.datafinder.StrategyTeam;
import TFA.modelo.datafinder.StrategyTeamPlayers;
import org.json.JSONObject;

import java.util.ArrayList;

public class NBAModel {
    private static NBAModel instance = null;
    private final ArrayList<Team> teamsList;
    private StrategyContext strategyContext;

    private NBAModel() {
        teamsList = new ArrayList<>();
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
                teamsList.add(new Team(team.getInt("id"), team.getString("name"), team.getString("code")));
            }
        }
    }

    public ArrayList<Team> getTeamsList() {
        return teamsList;
    }

    // Obtiene la lista de jugadores de un equipo
    public ArrayList<Player> getPlayersListFromTeam(int teamId) {
        System.out.println("Obteniendo lista de jugadores...");
        strategyContext.setStrategy(new StrategyTeamPlayers(teamId));
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
        return playersList;
    }

    public Team getTeamByName(String selectedItem) {
        for (Team team : teamsList) {
            if (team.getName().equals(selectedItem)) {
                return team;
            }
        }
        return null;
    }
}
