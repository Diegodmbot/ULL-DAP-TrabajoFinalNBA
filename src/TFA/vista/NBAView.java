package TFA.vista;

import TFA.controlador.NBAController;
import TFA.modelo.Player;
import TFA.modelo.Team;
import org.jfree.chart.ChartPanel;

import javax.swing.*;
import java.awt.*;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;

public class NBAView {
    private JFrame teamFrame;
    private HashMap<String, JFrame> chartsFrames;
    // Panel que muestra la información general del programa
    private JPanel mainPanel;
    private JComboBox<String> teamsComboBox;
    // Panel que muestra la información general del equipo seleccionado
    private JPanel teamInfoPanel;
    private JLabel infoLabel;
    // Panel que muestra los jugadores del equipo seleccionado
    private JPanel teamPlayerPanel;
    private JList<String> playersList;
    private JScrollPane scrollPlayersList;
    // Panel que muestra graficas de las estadisticas de los equipos
    private JPanel teamStatsPanel;

    // Botones para mostrar las graficas
    // Raking: Muestra el ranking del equipo y sus victorias y derrotas
    // fieldGoals: Muestra el porcentaje de tiros de campo, ademas de los tiros de campo realizados y acertados
    // threePoints: Muestra el porcentaje de tiros de 3 puntos, ademas de los tiros de 3 puntos realizados y acertados
    // freeThrows: Muestra el porcentaje de tiros libres, ademas de los tiros libres realizados y acertados
    // rebounds: Muestra los rebotes totales, ademas de los rebotes ofensivos y defensivos
    // stealsTurnovers: Muestra los robos y perdidas de balon
    private JButton rankingButton, fieldGoalsButton, threePointsButton, freeThrowsButton, reboundsButton, stealsTurnoversButton;


    public NBAView() {
        teamFrame = new JFrame("NBA");
        chartsFrames = new HashMap<>();

        mainPanel = new JPanel(new GridBagLayout());
        teamPlayerPanel = new JPanel(new GridBagLayout());
        teamInfoPanel = new JPanel(new GridBagLayout());
        teamStatsPanel = new JPanel(new GridBagLayout());

        infoLabel = new JLabel("NBA TEAM STATS", SwingConstants.CENTER);
        teamsComboBox = new JComboBox<>();
        playersList = new JList<>();
        scrollPlayersList = new JScrollPane(playersList);
        scrollPlayersList.setPreferredSize(new Dimension(200, 200));
        rankingButton = new JButton("Ranking");
        fieldGoalsButton = new JButton("Field Goals");
        threePointsButton = new JButton("Three Points");
        freeThrowsButton = new JButton("Free Throws");
        reboundsButton = new JButton("Rebounds");
        stealsTurnoversButton = new JButton("Steals & Turnovers");
    }

    public void display() {
        // Panel para mostrar la información del equipo seleccionado
        GridBagConstraints teamPanelConstraints = new GridBagConstraints();
        teamPanelConstraints.fill = GridBagConstraints.HORIZONTAL;
        teamPanelConstraints.gridx = 0;
        teamPanelConstraints.insets = new Insets(10, 10, 10, 10);
        // Añadir el panel de la información general del equipo seleccionado
        teamInfoPanel.setBorder(BorderFactory.createTitledBorder("Team Info"));
        teamInfoPanel.setVisible(false);
        teamPlayerPanel.add(teamInfoPanel, teamPanelConstraints);
        // Añadir el panel de la lista de jugadores del equipo seleccionado
        scrollPlayersList.setBorder(BorderFactory.createTitledBorder("Players"));
        teamPanelConstraints.gridx = 1;
        scrollPlayersList.setVisible(false);
        teamPlayerPanel.add(scrollPlayersList, teamPanelConstraints);
        // Añadir panel que muestre las graficas de las estadisticas de los equipos
        teamStatsPanel.setBorder(BorderFactory.createTitledBorder("Team Stats"));
        teamStatsPanel.setVisible(false);
        GridBagConstraints teamStatsPanelConstraints = new GridBagConstraints();
        teamStatsPanelConstraints.gridx = 0;
        teamStatsPanelConstraints.insets = new Insets(5, 5, 5, 10);
        teamStatsPanel.add(rankingButton, teamStatsPanelConstraints);
        teamStatsPanel.add(fieldGoalsButton, teamStatsPanelConstraints);
        teamStatsPanel.add(threePointsButton, teamStatsPanelConstraints);
        teamStatsPanelConstraints.gridx = 1;
        teamStatsPanel.add(freeThrowsButton, teamStatsPanelConstraints);
        teamStatsPanel.add(reboundsButton, teamStatsPanelConstraints);
        teamStatsPanel.add(stealsTurnoversButton, teamStatsPanelConstraints);
        teamPanelConstraints.gridx = 2;
        teamPlayerPanel.add(teamStatsPanel, teamPanelConstraints);

        //Panel de la ventana principal comun a todos los equipos
        GridBagConstraints mainPanelConstraints = new GridBagConstraints();
        mainPanelConstraints.gridy = 0;
        mainPanelConstraints.insets = new Insets(10, 10, 10, 10);
        mainPanel.add(infoLabel, mainPanelConstraints);
        mainPanelConstraints.gridy = 1;
        mainPanel.add(teamsComboBox, mainPanelConstraints);
        mainPanelConstraints.gridy = 2;
        mainPanel.add(teamPlayerPanel, mainPanelConstraints);

        teamFrame.add(mainPanel);
        teamFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        teamFrame.pack();
        teamFrame.setSize(1000, 500);
        teamFrame.setLocationRelativeTo(null);
        teamFrame.setVisible(true);
    }

    public void setController(NBAController controller) {
        controller.setTeamsComboBox(teamsComboBox);
        controller.addActionListenerToTeamsBox(teamsComboBox);
        controller.addActionListenerToButtons(rankingButton);
        controller.addActionListenerToButtons(fieldGoalsButton);
        controller.addActionListenerToButtons(threePointsButton);
        controller.addActionListenerToButtons(freeThrowsButton);
        controller.addActionListenerToButtons(reboundsButton);
        controller.addActionListenerToButtons(stealsTurnoversButton);
    }

    public void updatePlayersList(ArrayList<Player> players) {
        DefaultListModel<String> listModel = new DefaultListModel<>();
        for (Player player : players) {
            listModel.addElement(player.toString());
        }
        playersList.setModel(listModel);
    }

    private void showTeamInfo(Team teamToDisplay) {
        teamInfoPanel.removeAll();
        JLabel teamNameLabel = new JLabel("Nombre: " + teamToDisplay.getName() + " (" + teamToDisplay.getShortName() + ")");
        JLabel teamConferenceLabel = new JLabel("Conferencia: " + teamToDisplay.getConference());
        GridBagConstraints teamInfoPanelConstraints = new GridBagConstraints();
        teamInfoPanelConstraints.gridy = 0;
        // add teamNameLabel at the left of the panel
        teamInfoPanelConstraints.anchor = GridBagConstraints.LINE_START;
        teamInfoPanel.add(teamNameLabel, teamInfoPanelConstraints);
        teamInfoPanelConstraints.gridy = 1;
        teamInfoPanel.add(teamConferenceLabel, teamInfoPanelConstraints);
        teamInfoPanelConstraints.gridy = 2;
        teamInfoPanelConstraints.anchor = GridBagConstraints.CENTER;
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
        for (JFrame frame : chartsFrames.values()) {
            frame.dispose();
        }
        showTeamInfo(teamToDisplay);
        teamInfoPanel.revalidate();
        updatePlayersList(teamToDisplay.getPlayers());
        scrollPlayersList.setVisible(true);
        teamStatsPanel.setVisible(true);
        teamInfoPanel.setVisible(true);
        teamFrame.repaint();
    }

    public void displayChart(String chartName, HashMap<String, Integer> data) {
        JFrame chartFrame = new JFrame(chartName);
        chartFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        chartFrame.setSize(500, 500);
        chartFrame.setVisible(true);
        Chart pieChart = new PieChart(chartName, data);
        pieChart.createChart();
        chartFrame.add(pieChart.drawChart());
        chartsFrames.put(chartName, chartFrame);
    }
}
