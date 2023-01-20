package TFA.controlador;

import TFA.modelo.NBAModel;
import TFA.modelo.Team;
import TFA.vista.NBAView;

import javax.swing.*;
import java.util.Objects;

public class NBAController {
    private NBAModel model;
    private NBAView view;
    private Team teamToDisplay;

    public NBAController(NBAModel model, NBAView view) {
        this.model = model;
        this.view = view;
    }


    public void setTeamsComboBox(JComboBox<String> teamsComboBox) {
        model.setTeamList();
        teamsComboBox.addItem("");
        for (String team : model.getTeamsList()) {
            teamsComboBox.addItem(team);
        }
        teamsComboBox.setSelectedIndex(0);
    }
    public void addActionListenerToTeamsBox(JComboBox<String> teamsComboBox) {
        teamsComboBox.addActionListener(e -> {
            // Si no se selecciona un equipo no pasará nada
            if (Objects.equals(teamsComboBox.getSelectedItem(), "")) {
                return;
            }
            try {
                // MODEL
                // Obtener información general del equipo seleccionado
                System.out.println("Equipo seleccionado: " + teamsComboBox.getSelectedItem());
                teamToDisplay = model.getTeamByName((String) teamsComboBox.getSelectedItem());
                // Obtener los jugadores del equipo seleccionado
                model.setPlayersListFromTeam(teamToDisplay);
                // Obtener la información sobre la posición del equipo seleccionado
                model.setTeamStanding(teamToDisplay);
                // Obtener las estadísticas del equipo seleccionado
                model.setTeamStats(teamToDisplay);
                // VIEW
                view.repaint(teamToDisplay);
            } catch (Exception exception) {
                exception.printStackTrace();
            }
        });
    }

    public void addActionListenerToButtons(JButton button) {
        if(button.getText().equals("Ranking")) {
            button.addActionListener(e -> {
                try {
                    // Crear dataset que mostrar
                    view.displayChart("Ranking", teamToDisplay.getTeamResults());
                } catch (Exception exception) {
                    exception.printStackTrace();
                }
            });
        } else {
            button.addActionListener(e -> {
                try {
                    System.out.println("Boton: " + button.getText());
                } catch (Exception exception) {
                    exception.printStackTrace();
                }
            });
        }
    }
}
