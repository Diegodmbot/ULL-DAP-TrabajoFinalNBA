package TFA.controlador.ButtonFactory;

import TFA.modelo.Team;
import TFA.vista.NBAView;

import javax.swing.*;

public class FTButton implements Button {

    @Override
    public void onClick(Team team, NBAView view, JButton button) {
        button.setText("FT%");
        button.addActionListener(e -> {
            view.displayBarChart("Tiros Libres", team.getTeamStats("ftm", "fta"));
        });
    }
}
