package TFA.vista;

import TFA.controlador.NBAController;
import TFA.modelo.NBAModel;
import TFA.modelo.Player;
import TFA.modelo.Team;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class NBAView {
    private JFrame frame;
    private JPanel mainPanel;
    private JPanel teamPanel;
    private JLabel infoLabel;
    private JComboBox<String> teamsComboBox;
    private JList<String> playersList;
    private JScrollPane scrollPlayersList;


    public NBAView() {
        frame = new JFrame("NBA");
        mainPanel = new JPanel(new GridBagLayout());
        teamPanel = new JPanel(new GridBagLayout());
        infoLabel = new JLabel("NBA TEAM STATS", SwingConstants.CENTER);
        teamsComboBox = new JComboBox<>();
        playersList = new JList<>();
        scrollPlayersList = new JScrollPane(playersList);
        scrollPlayersList.setPreferredSize(new Dimension(200, 200));
    }
    public void display() {
        /**
         *  Panel de cada equipo de la NBA
         */
        GridBagConstraints teamPanelConstraints = new GridBagConstraints();
        teamPanelConstraints.fill = GridBagConstraints.HORIZONTAL;
        teamPanelConstraints.gridy = 0;
        teamPanel.add(scrollPlayersList, teamPanelConstraints);

        /**
         * Panel de la ventana principal comun a todos los equipos
         */
        GridBagConstraints mainPanelConstraints = new GridBagConstraints();
        mainPanelConstraints.gridy = 0;
        mainPanelConstraints.insets = new Insets(10, 10, 10, 10);
        mainPanel.add(infoLabel, mainPanelConstraints);
        mainPanelConstraints.gridy = 1;
        mainPanel.add(teamsComboBox, mainPanelConstraints);
        mainPanelConstraints.gridy = 2;
        mainPanel.add(teamPanel, mainPanelConstraints);

        frame.add(mainPanel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setSize(1080,720);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }

    public void setController(NBAController controller) {
        controller.setTeamsComboBox(teamsComboBox);
        controller.addActionListenerToTeamsBox(teamsComboBox);
    }

    public void updatePlayersList(ArrayList<Player> players) {
        DefaultListModel<String> listModel = new DefaultListModel<>();
        for (Player player : players) {
            listModel.addElement(player.toString());
        }
        playersList.setModel(listModel);
    }
}
