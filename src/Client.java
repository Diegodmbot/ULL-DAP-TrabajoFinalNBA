import controlador.NBAController;
import modelo.*;
import vista.NBAView;

public class Client {
    public static void main(String[] args) {
        NBAModel model = NBAModel.getInstance();
        NBAView view = new NBAView();
        NBAController controller = new NBAController(model, view);

    }
}
