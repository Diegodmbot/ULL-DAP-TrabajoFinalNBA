package TFA.controlador.ButtonFactory;

import TFA.modelo.Team;
import TFA.vista.NBAView;

import javax.swing.*;

public class FGButton implements Button {

    @Override
    public void onClick(Team team, NBAView view, JButton button) {
        button.setText("FG%");
        button.addActionListener(e -> {
            view.displayBarChart("Tiros de Campo", team.getTeamStats("fgm", "fga"));
        });
    }
}
