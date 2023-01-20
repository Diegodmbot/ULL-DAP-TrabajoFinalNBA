package TFA.vista;

public class ChartPanel {
    ChartState state;
    public ChartPanel() {
        this.state = new BarChartState();
    }
    public void setState(ChartState state) {
        this.state = state;
    }
    public void display() {
        state.display();
    }
}
