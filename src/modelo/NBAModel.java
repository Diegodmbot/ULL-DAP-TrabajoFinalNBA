package modelo;

import modelo.datafinder.*;
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

    public void setTeamList() {
        System.out.println("Obteniendo lista de equipos...");
        strategyContext.setStrategy(new StrategyTeam());
        JSONObject object = strategyContext.executeRequest();
        // Recorre el array de equipos y selecciona los equipos de la NBA
        for (int i = 0; i < object.getJSONArray("response").length(); i++) {
            JSONObject team = object.getJSONArray("response").getJSONObject(i);
            if(team.getBoolean("nbaFranchise")) {
                teamsList.add(new Team(team.getInt("id"), team.getString("name"), team.getString("code")));
            }
        }
    }

    public ArrayList<Team> getTeamsList() {
        return teamsList;
    }
}
