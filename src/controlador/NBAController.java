package controlador;

import modelo.*;
import modelo.datafinder.StrategyContext;
import vista.NBAView;

public class NBAController {
    private NBAModel model;
    private NBAView view;
    private StrategyContext strategyContext;

    public NBAController(NBAModel model, NBAView view) {
        this.model = model;
        this.view = view;
    }
}
