package TFA.controlador.ButtonFactory;

import TFA.modelo.Team;
import TFA.vista.NBAView;

import javax.swing.*;

public class WinLossButton implements Button {
    @Override
    public void onClick(Team team, NBAView view, JButton button) {
        button.setText("Resultados");
        button.addActionListener(e -> {
            view.displayPieChart("Resultados", team.getTeamResults());
        });
    }
}
