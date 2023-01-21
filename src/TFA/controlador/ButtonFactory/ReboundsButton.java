package TFA.controlador.ButtonFactory;

import TFA.modelo.Team;
import TFA.vista.NBAView;

import javax.swing.*;

public class ReboundsButton implements Button {
    @Override
    public void onClick(Team team, NBAView view, JButton button) {
        button.setText("Rebotes");
        button.addActionListener(e -> {
            view.displayPieChart("Rebotes", team.getTeamStats("defReb", "offReb"));
        });
    }
}
