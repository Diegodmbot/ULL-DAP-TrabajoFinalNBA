package TFA.modelo;

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
        return name + " " + lastName + " (" + position + ")" + " #" + jersey;
    }

    public void printInfo() {
        System.out.println("ID: " + id);
        System.out.println("Nombre: " + name);
        System.out.println("Apellido: " + lastName);
        System.out.println("Posición: " + position);
        System.out.println("Jersey: " + jersey);
        System.out.println();
    }
}
