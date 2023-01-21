package TFA.controlador.ButtonFactory;

import javax.swing.*;

public class StealsTurnoversButtonCreator extends ButtonCreator {
    @Override
    public Button createButton() {
        return new StealsTurnoverButton();
    }
}

