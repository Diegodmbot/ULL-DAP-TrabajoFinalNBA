package TFA.controlador.ButtonFactory;

import TFA.modelo.Team;
import TFA.vista.NBAView;

import javax.swing.*;

public class StealsTurnoverButton implements Button {
    @Override
    public void onClick(Team team, NBAView view, JButton button) {
        button.setText("STL/TOV");
        button.addActionListener(e -> {
            view.displayPieChart("Robos/Perdidas", team.getTeamStats("steals", "turnovers"));
        });

    }
}
