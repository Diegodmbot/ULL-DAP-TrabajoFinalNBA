package TFA.vista;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.JFreeChart;
import org.jfree.data.general.AbstractDataset;
import org.jfree.data.general.DefaultPieDataset;
import org.jfree.data.general.PieDataset;

import java.util.HashMap;

public class PieChart extends Chart {
    public PieChart(String title, HashMap<String, Integer> dataMap) {
        super(title, dataMap);
    }
    @Override
    public JFreeChart createChart() {
        chart = ChartFactory.createPieChart(title, (PieDataset) createDataset(), true, true, false);
        return chart;
    }


    @Override
    public AbstractDataset createDataset() {
        DefaultPieDataset pieDataset = new DefaultPieDataset();
        for (HashMap.Entry<String, Integer> entry : dataMap.entrySet()) {
            pieDataset.setValue(entry.getKey(), entry.getValue());
        }
        return pieDataset;
    }
}
