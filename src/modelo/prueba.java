package modelo;

import modelo.datafinder.*;

public class prueba {
    public static void main(String[] args) throws Exception {
        StrategyContext context = new StrategyContext();
        context.setStrategy(new StrategyGames(2));
        context.executeRequest();
        context.setStrategy(new StrategyTeamPlayers(1));
        context.executeRequest();
        context.setStrategy(new StrategyTeam(1));
        context.executeRequest();
        context.setStrategy(new StrategyPlayer(290));
        context.executeRequest();
        context.setStrategy(new StrategyTeamStats(1));
        context.executeRequest();
    }
}
