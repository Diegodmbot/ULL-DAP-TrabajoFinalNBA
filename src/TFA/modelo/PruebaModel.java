package TFA.modelo;

import java.util.ArrayList;

public class PruebaModel {
        public static void main(String[] args) {
            NBAModel model = NBAModel.getInstance();
            try {
                System.out.println("Hola");
                // model.setTeamList();
                //ArrayList<Player> players = model.setPlayersListFromTeam(2);
//                for (Player player : players) {
//                    player.printInfo();
                //}
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
}
