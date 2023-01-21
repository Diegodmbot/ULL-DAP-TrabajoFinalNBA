package TFA.controlador.ButtonFactory;

import TFA.modelo.Team;
import TFA.vista.NBAView;

import javax.swing.*;

public abstract class ButtonCreator {
    private JButton buttonComponent;
    public ButtonCreator(){
        buttonComponent = new JButton();
    }
    public void operate(Team team, NBAView view){
        Button button = createButton();
        button.onClick(team, view, buttonComponent);
    }
    public JButton getButton(){
        return buttonComponent;
    }
    public abstract Button createButton();
}
