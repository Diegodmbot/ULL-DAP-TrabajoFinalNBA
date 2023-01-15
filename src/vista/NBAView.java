package vista;

import modelo.*;

import javax.swing.*;
import java.awt.*;

public class NBAView {
    private JFrame frame;
    private JPanel mainPanel;
    private JLabel infoLabel;
    private JComboBox<String> teamsComboBox;

    public NBAView() {
        frame = new JFrame("NBA");
        mainPanel = new JPanel(new GridBagLayout());
        infoLabel = new JLabel("NBA TEAM STATS");
        teamsComboBox = new JComboBox<>();
    }
    public void display() {
        setTeamsComboBox();

        GridBagConstraints mainPanelConstraints = new GridBagConstraints();
        mainPanelConstraints.gridy = 0;
        mainPanel.add(infoLabel, mainPanelConstraints);
        mainPanelConstraints.gridy = 1;
        mainPanel.add(teamsComboBox, mainPanelConstraints);

        frame.add(mainPanel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setSize(1080,720);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }

    private void setTeamsComboBox() {
        for (Team team : NBAModel.getInstance().getTeamsList()) {
            teamsComboBox.addItem(team.getName());
        }
        teamsComboBox.setSelectedIndex(0);
        teamsComboBox.addActionListener(e -> {
            try {
                System.out.println("Obteniendo estadísticas del equipo " + teamsComboBox.getSelectedItem());
                // TODO Llamar al controlador para que obtenga las estadísticas del equipo seleccionado
            } catch (Exception exception) {
                exception.printStackTrace();
            }
        });
    }
}
