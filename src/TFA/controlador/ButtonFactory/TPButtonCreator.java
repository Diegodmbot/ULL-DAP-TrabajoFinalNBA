package TFA.controlador.ButtonFactory;

import javax.swing.*;

public class TPButtonCreator extends ButtonCreator {
    @Override
    public Button createButton() {
        return new TPButton();
    }
}
