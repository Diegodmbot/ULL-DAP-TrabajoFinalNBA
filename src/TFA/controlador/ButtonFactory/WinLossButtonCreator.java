package TFA.controlador.ButtonFactory;

import javax.swing.*;

public class WinLossButtonCreator extends ButtonCreator {
    @Override
    public Button createButton() {
        return new WinLossButton();
    }
}
