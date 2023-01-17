package TFA.modelo.datafinder;

import org.json.JSONObject;

import java.io.FileWriter;

public class PruebaDataFetcher {
    public static void main(String[] args) throws Exception {
        StrategyContext context = new StrategyContext();
        String title = "Games";
        System.out.println("Games");
        context.setStrategy(new StrategyGames(2));
        JSONObject obj = context.executeRequest();
        FileWriter file = new FileWriter("json/" + title +".json");
        file.write(obj.toString(4));
        file.close();
        System.out.println("Team");
        title = "Team";
        context.setStrategy(new StrategyTeam());
        obj = context.executeRequest();
        file = new FileWriter("json/" + title +".json");
        file.write(obj.toString(4));
        file.close();
        System.out.println("Team Players");
        title = "Team Players";
        context.setStrategy(new StrategyTeamPlayers(1));
        obj = context.executeRequest();
        file = new FileWriter("json/" + title +".json");
        file.write(obj.toString(4));
        file.close();
        System.out.println("Team Stats");
        title = "Team Stats";
        context.setStrategy(new StrategyTeamStats(1));
        obj = context.executeRequest();
        file = new FileWriter("json/" + title +".json");
        file.write(obj.toString(4));
        file.close();
        System.out.println("Team Standings");
        title = "Team Standings";
        context.setStrategy(new StrategyTeamStandings(1));
        obj = context.executeRequest();
        file = new FileWriter("json/" + title +".json");
        file.write(obj.toString(4));
        file.close();
        System.out.println("Player");
        title = "Player";
        context.setStrategy(new StrategyPlayer(290));
        obj = context.executeRequest();
        file = new FileWriter("json/" + title +".json");
        file.write(obj.toString(4));
        file.close();
    }
}
