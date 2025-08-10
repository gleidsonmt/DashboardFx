package io.github.gleidsonmt.dashboardfx.dashboard;

import io.github.gleidsonmt.glad.charts.DonutChart;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.chart.PieChart;

/**
 * @author Gleidson Neves da Silveira | gleidisonmt@gmail.com
 * Create on  10/08/2025
 */
public class HardwarePartsDonut extends DonutChart {

    public HardwarePartsDonut() {
        getStyleClass().addAll("bg-white", "border-2", "border-light-gray-2", "radius-5");
        ObservableList<Data> data = FXCollections.observableArrayList();
        setTitle("Most Valuable Parts");
        setAnimated(true);
        setLabelsVisible(true);
        setLabelLineLength(10);
        setMinHeight(300);

        data.add(new PieChart.Data("Chip", 2_199.88));
        data.add(new PieChart.Data("Monitor 4k", 1_264.98));
        data.add(new PieChart.Data("Motherboard", 1_552.82));
        data.add(new PieChart.Data("Video Card", 3_800.55));

//        setMaxWidth(325);

        setData(data);
    }
}
