package TFA.controlador.ButtonFactory;

import TFA.modelo.Team;
import TFA.vista.NBAView;

import javax.swing.*;

public interface Button {
    public void onClick(Team team, NBAView view, JButton button);
}

