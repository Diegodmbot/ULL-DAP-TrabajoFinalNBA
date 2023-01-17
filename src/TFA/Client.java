package TFA;

import TFA.controlador.NBAController;
import TFA.modelo.NBAModel;
import TFA.vista.NBAView;

public class Client {
    public static void main(String[] args) {
        NBAModel model = NBAModel.getInstance();
        NBAView view = new NBAView();
        NBAController controller = new NBAController(model, view);
        view.setController(controller);
        view.display();
    }
}
