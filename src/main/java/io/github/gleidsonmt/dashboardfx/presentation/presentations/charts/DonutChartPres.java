package io.github.gleidsonmt.dashboardfx.presentation.presentations.charts;

import io.github.gleidsonmt.dashboardfx.presentation.internal.Tutorial;
import io.github.gleidsonmt.glad.charts.DonutChart;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Node;
import javafx.scene.chart.PieChart;
import javafx.scene.layout.StackPane;

/**
 * @author Gleidson Neves da Silveira | gleidisonmt@gmail.com
 * Create on  13/03/2025
 */
public class DonutChartPres extends StackPane {
    public DonutChartPres() {
        getChildren().setAll(
                new Tutorial()
                        .h3("Pie Chart")
                        .text("""
                                Displays a PieChart. The chart content is populated by pie slices based on data set on the PieChart.
                                The clockwise property is set to true by default, which means slices are placed in the clockwise order. 
                                The labelsVisible property is used to either display pie slice labels or not.
                                """)
                        .legend("javafx.scene.chart.AreaChart")
                        .demo(createPieChart())
                        .code("""
                                PieChart pieChart = new PieChart();
                                ObservableList<PieChart.Data> data = FXCollections.observableArrayList();
                                data.add(new PieChart.Data("left", 30));
                                data.add(new PieChart.Data("top", 20));
                                data.add(new PieChart.Data("bottom", 10));
                                data.add(new PieChart.Data("right", 40));
                        
                                pieChart.setTitle("Processors");
                                pieChart.setAnimated(true);
                                pieChart.setLabelsVisible(true);
                                pieChart.setLabelLineLength(10);
                                pieChart.setMinHeight(300);
                        
                                pieChart.setData(data);
                                """)
                        .h3("Donut Chart")
                        .text("""
                                The same PieChart, but with a circle inside.
                                """)
                        .legend("io.github.gleidsonmt.glad.charts.DonutChart")
                        .demo(createChart())
                        .code("""
                                DonutChart donutChart = new DonutChart();
                                ObservableList<PieChart.Data> data = FXCollections.observableArrayList();
                                data.add(new PieChart.Data("left", 30));
                                data.add(new PieChart.Data("top", 20));
                                data.add(new PieChart.Data("bottom", 10));
                                data.add(new PieChart.Data("right", 40));
                        
                                donutChart.setTitle("Processors");
                                donutChart.setAnimated(true);
                                donutChart.setLabelsVisible(true);
                                donutChart.setLabelLineLength(10);
                        
                                donutChart.setData(data);
                                """)
                        .build().getRoot());

    }

    public Node createPieChart() {
        PieChart pieChart = new PieChart();
        ObservableList<PieChart.Data> data = FXCollections.observableArrayList();
        PieChart.Data data1 = new PieChart.Data("left", 30);
//        data1.getNode().setStyle("-fx-pie-color: #0088ff;");
        pieChart.setAnimated(true);
        data.add(data1);
        data.add(new PieChart.Data("top", 20));
        data.add(new PieChart.Data("bottom", 10));
        data.add(new PieChart.Data("right", 40));

        pieChart.setTitle("Processors");
        pieChart.setAnimated(true);
        pieChart.setLabelsVisible(true);
        pieChart.setLabelLineLength(20);

        pieChart.setData(data);
        return pieChart;
    }

    public Node createChart() {
        DonutChart donutChart = new DonutChart();
        ObservableList<PieChart.Data> data = FXCollections.observableArrayList();
        data.add(new PieChart.Data("left", 30));
        data.add(new PieChart.Data("top", 20));
        data.add(new PieChart.Data("bottom", 10));
        data.add(new PieChart.Data("right", 40));

        donutChart.setTitle("Processors");
        donutChart.setAnimated(true);
        donutChart.setLabelsVisible(true);
        donutChart.setLabelLineLength(10);
        donutChart.setMinHeight(300);
//        donutChart.setMaxWidth(325);

        donutChart.setData(data);
        return donutChart;
    }
}
