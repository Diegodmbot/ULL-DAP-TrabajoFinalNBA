package TFA.modelo;

import java.util.Objects;

public class Player {
    // Datos Generales del Jugador (StrategyPlayer)
    private int id;
    private String name;
    private String lastName;
    private String position;
    private String jersey;

    public Player(int id, String name, String lastName, String position, String jersey) {
        this.id = id;
        this.name = name;
        this.lastName = lastName;
        this.position = position;
        this.jersey = jersey;
    }

    @Override
    public String toString() {
        String positionToPrint = !Objects.equals(position, "null") ? position : "N/A";
        String jerseyToPrint = !Objects.equals(jersey, "null") ? jersey : "N/A";
        return name + " " + lastName + " (" + positionToPrint + ") " + "#" + jerseyToPrint;
    }
}
