package TFA.modelo;

import java.util.ArrayList;

public class PruebaModel {
        public static void main(String[] args) {
            NBAModel model = NBAModel.getInstance();
            try {
                // model.setTeamList();
                ArrayList<Player> players = model.getPlayersListFromTeam(2);
                for (Player player : players) {
                    player.printInfo();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
}
