package TFA.controlador.ButtonFactory;

import javax.swing.*;

public class ReboundsButtonCreator extends ButtonCreator {
    @Override
    public Button createButton() {
        return new ReboundsButton();
    }
}
