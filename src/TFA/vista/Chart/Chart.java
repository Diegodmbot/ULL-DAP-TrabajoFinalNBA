package TFA.vista.Chart;

import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.data.general.AbstractDataset;

import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.util.HashMap;
import java.util.Map;

public abstract class Chart {
    protected String title;
    protected JFreeChart chart;
    protected Map<String, Integer> dataMap;

    public Chart(String title, HashMap<String, Integer> dataMap) {
        this.title = title;
        this.dataMap = dataMap;
    }
    public abstract JFreeChart createChart();

    public abstract AbstractDataset createDataset();

    public ChartPanel drawChart() {
        ChartPanel chartPanel =new ChartPanel(chart);
        chartPanel.setPreferredSize(new Dimension(500, 270));
        chartPanel.setBorder(new EmptyBorder(10,10,10,10));
        return chartPanel;
    }
}
