package vista;

import modelo.NBAModel;

public class PruebasVista {
    public static void main(String[] args) {
        NBAModel model = NBAModel.getInstance();
        try {
            model.setTeamList();
        } catch (Exception e){
            e.printStackTrace();
        }
        NBAView view = new NBAView();
        view.display();
    }
}
