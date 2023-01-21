package TFA.controlador.ButtonFactory;

public class FGButtonCreator extends ButtonCreator {
    @Override
    public Button createButton() {
        return new FGButton();
    }
}
