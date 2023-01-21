package TFA.controlador.ButtonFactory;

import javax.swing.*;

public class FTButtonCreator extends ButtonCreator {
    @Override
    public Button createButton() {
        return new FTButton();
    }
}
