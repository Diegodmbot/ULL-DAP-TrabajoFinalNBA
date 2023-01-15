package modelo.datafinder;

public class PruebaDataFetcher {
    public static void main(String[] args) throws Exception {
        StrategyContext context = new StrategyContext();
        System.out.println("Games");
        context.setStrategy(new StrategyGames(2));
        context.executeRequest();
        System.out.println("Team");
        context.setStrategy(new StrategyTeam(1));
        context.executeRequest();
        System.out.println("Team Players");
        context.setStrategy(new StrategyTeamPlayers(1));
        context.executeRequest();
        System.out.println("Team Stats");
        context.setStrategy(new StrategyTeamStats(1));
        context.executeRequest();
        System.out.println("Team Standings");
        context.setStrategy(new StrategyTeamStandings(1));
        context.executeRequest();
        System.out.println("Player");
        context.setStrategy(new StrategyPlayer(290));
        context.executeRequest();
    }
}
