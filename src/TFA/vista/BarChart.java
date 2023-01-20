package TFA.vista;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.CategoryDataset;
import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.data.general.AbstractDataset;

import java.util.HashMap;
public class BarChart extends Chart {
    public BarChart(String title, HashMap<String, Integer> dataMap)  {
        super(title, dataMap);
    }
    @Override
    public JFreeChart createChart() {
        chart = ChartFactory.createBarChart(title, null, null, (CategoryDataset) createDataset(), PlotOrientation.VERTICAL, true, true, false);
        return chart;
    }

    @Override
    public AbstractDataset createDataset() {
        DefaultCategoryDataset barDataset = new DefaultCategoryDataset();
        for (HashMap.Entry<String, Integer> entry : dataMap.entrySet()) {
            barDataset.setValue(entry.getValue(), entry.getKey(), "");
        }
        return barDataset;
    }
}
