package TFA.vista;

import TFA.controlador.NBAController;
import TFA.modelo.Player;
import TFA.modelo.Team;

import javax.swing.*;
import java.awt.*;
import java.net.URL;
import java.util.ArrayList;

public class NBAView {
    private JFrame frame;
    // Panel que muestra la información general del programa
    private JPanel mainPanel;
    // Panel que muestra toda la información del equipo seleccionado
    private JPanel teamPanel;
    // Panel que muestra la información general del equipo seleccionado
    private JPanel teamInfoPanel;
    private JLabel infoLabel;
    private JComboBox<String> teamsComboBox;
    private JList<String> playersList;
    private JScrollPane scrollPlayersList;


    public NBAView() {
        frame = new JFrame("NBA");
        mainPanel = new JPanel(new GridBagLayout());
        teamPanel = new JPanel(new GridBagLayout());
        teamInfoPanel = new JPanel(new GridBagLayout());
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
        teamPanelConstraints.gridx = 0;
        // Añadir el panel de la información general del equipo seleccionado
        teamInfoPanel.setBorder(BorderFactory.createTitledBorder("Team Info"));
        teamPanel.add(teamInfoPanel, teamPanelConstraints);
        teamPanelConstraints.gridx = 1;
        // Añadir el panel de la lista de jugadores del equipo seleccionado
        scrollPlayersList.setBorder(BorderFactory.createTitledBorder("Players"));
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

    public void showTeamInfo(Team teamToDisplay) {
        teamInfoPanel.removeAll();
        JLabel teamNameLabel = new JLabel("Nombre: " + teamToDisplay.getName() + " (" + teamToDisplay.getShortName() + ")");
        JLabel teamConferenceLabel = new JLabel("Conferencia: " + teamToDisplay.getConference());
        GridBagConstraints teamInfoPanelConstraints = new GridBagConstraints();
        teamInfoPanelConstraints.gridy = 0;
        teamInfoPanel.add(teamNameLabel, teamInfoPanelConstraints);
        teamInfoPanelConstraints.gridy = 1;
        teamInfoPanel.add(teamConferenceLabel, teamInfoPanelConstraints);
        teamInfoPanelConstraints.gridy = 2;
        try {
            URL url = new URL(teamToDisplay.getCrestUrl());
            Image icon = new ImageIcon(url).getImage();
            icon = icon.getScaledInstance(100, 100, Image.SCALE_SMOOTH);
            teamInfoPanel.add(new JLabel(new ImageIcon(icon)), teamInfoPanelConstraints);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void repaint(Team teamToDisplay) {
        showTeamInfo(teamToDisplay);
        teamInfoPanel.revalidate();
        teamInfoPanel.repaint();
        updatePlayersList(teamToDisplay.getPlayers());
        frame.repaint();
    }
}
