package modelo;

public class PruebaModel {
        public static void main(String[] args) {
            NBAModel model = NBAModel.getInstance();
            try {
                model.setTeamList();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
}
