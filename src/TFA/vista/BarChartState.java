package TFA.vista;

public class BarChartState extends ChartState {
    BarChart barChart;

    @Override
    public void display() {
        try{
        barChart = new BarChart("Bar Chart", null);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
