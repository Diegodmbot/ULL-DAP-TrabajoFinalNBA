package TFA.modelo;

import TFA.modelo.datafinder.StrategyContext;
import TFA.modelo.datafinder.StrategyTeam;
import TFA.modelo.datafinder.StrategyTeamPlayers;
import TFA.modelo.datafinder.StrategyTeamStandings;
import org.json.JSONObject;

import java.util.ArrayList;

import static TFA.modelo.Result.WIN;
import static TFA.modelo.Result.LOSS;

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
                JSONObject teamLeagueData = team.getJSONObject("leagues").getJSONObject("standard");
                teamsList.add(new Team(
                        team.getInt("id"),
                        team.getString("name"),
                        team.getString("code"),
                        teamLeagueData.getString("conference"),
                        team.get("logo").toString()
                ));
            }
        }
    }

    public ArrayList<Team> getTeamsList() {
        return teamsList;
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
        for (Team team : teamsList) {
            if (team.getName().equals(selectedItem)) {
                return team;
            }
        }
        return null;
    }

    public void setTeamStanding(Team teamToDisplay) {
        strategyContext.setStrategy(new StrategyTeamStandings(teamToDisplay.getId()));
        JSONObject object = strategyContext.executeRequest();
        JSONObject teamLeagueData = object.getJSONArray("response").getJSONObject(0).getJSONObject("division");
        teamToDisplay.setRank(teamLeagueData.getInt("rank"));
        teamToDisplay.addResult(WIN, teamLeagueData.getInt("win"));
        teamToDisplay.addResult(LOSS, teamLeagueData.getInt("loss"));
    }
}
