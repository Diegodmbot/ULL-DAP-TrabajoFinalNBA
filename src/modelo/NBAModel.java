package modelo;

import modelo.datafinder.*;
import org.json.JSONObject;

import java.util.ArrayList;

public class NBAModel {
    private static NBAModel instance = null;
    private ArrayList<Team> teams;
    private StrategyContext strategyContext;
    private NBAModel() {
        teams = new ArrayList<>();
        strategyContext = new StrategyContext();
    }
    public static NBAModel getInstance() {
        if (instance == null) {
            instance = new NBAModel();
        }
        return instance;
    }

    public void setTeamList() throws Exception {
        strategyContext.setStrategy(new StrategyTeam());
        JSONObject object = strategyContext.executeRequest();
        for (int i = 0; i < object.getJSONArray("api").length(); i++) {
            JSONObject team = object.getJSONArray("api").getJSONObject(i);
            teams.add(new Team(team.getInt("teamId"), team.getString("fullName"), team.getString("shortName")));
        }
    }
}
