package TFA.controlador.ButtonFactory;

import TFA.modelo.Team;
import TFA.vista.NBAView;

import javax.swing.*;

public class TPButton implements Button {

    @Override
    public void onClick(Team team, NBAView view, JButton button) {
        button.setText("TP%");
        button.addActionListener(e -> {
            view.displayBarChart("Triples", team.getTeamTP());
        });
    }
}
