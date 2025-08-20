package io.github.gleidsonmt.dashboardfx.presentation.presentations.charts;

import io.github.gleidsonmt.dashboardfx.presentation.core.CustomizablePresentation;
import io.github.gleidsonmt.dashboardfx.presentation.internal.Tutorial;
import javafx.scene.Node;
import javafx.scene.chart.*;
import javafx.scene.layout.StackPane;

/**
 * @author Gleidson Neves da Silveira | gleidisonmt@gmail.com
 * Create on  17/03/2025
 */
public class StackedBarChartPres extends CustomizablePresentation {

    public StackedBarChartPres() {
        super("StackedBarChart");
    }

    public Tutorial create() {
        return new Tutorial()
                .h3("Stacked Bar Chart")
                .text("""
                        StackedBarChart is a variation of BarChart that plots bars indicating data values for a category.
                        The bars can be vertical or horizontal depending on which axis is a category axis. 
                        The bar for each series is stacked on top of the previous series.
                        """)
                .legend("javafx.scene.chart.StackedBarChart")
                .demo(createChart())
                .code("""
                        CategoryAxis xAxis    = new CategoryAxis();
                        xAxis.setLabel("Devices");
                        xAxis.getCategories().addAll("Desktop", "Phone", "Tablet");
                        
                        NumberAxis yAxis = new NumberAxis();
                        yAxis.setLabel("Visits");
                        
                        StackedBarChart<String, Number> stackedBarChart = new StackedBarChart<>(xAxis, yAxis);
                        
                        XYChart.Series<String, Number> dataSeries1 = new XYChart.Series<>();
                        dataSeries1.setName("Desktop");
                        
                        dataSeries1.getData().add(new XYChart.Data<>("2014", 567));
                        dataSeries1.getData().add(new XYChart.Data<>("2015", 540));
                        
                        stackedBarChart.getData().add(dataSeries1);
                        
                        XYChart.Series<String, Number> dataSeries2 = new XYChart.Series<>();
                        dataSeries2.setName("Phone");
                        
                        dataSeries2.getData().add(new XYChart.Data<>("2014"  , 65));
                        dataSeries2.getData().add(new XYChart.Data<>("2015"  , 120));
                        
                        stackedBarChart.getData().add(dataSeries2);
                        
                        XYChart.Series<String, Number> dataSeries3 = new XYChart.Series<>();
                        dataSeries3.setName("Tablet");
                        
                        dataSeries3.getData().add(new XYChart.Data<>("2014"  , 23));
                        dataSeries3.getData().add(new XYChart.Data<>("2015"  , 36));
                        
                        stackedBarChart.getData().add(dataSeries3);
                        """);
    }


    public Node createChart() {
        CategoryAxis xAxis = new CategoryAxis();
        xAxis.setLabel("Devices");
        xAxis.getCategories().addAll("Desktop", "Phone", "Tablet");

        NumberAxis yAxis = new NumberAxis();
        yAxis.setLabel("Visits");

        StackedBarChart<String, Number> stackedBarChart = new StackedBarChart<>(xAxis, yAxis);

        XYChart.Series<String, Number> dataSeries1 = new XYChart.Series<>();
        dataSeries1.setName("Desktop");

        dataSeries1.getData().add(new XYChart.Data<>("2014", 567));
        dataSeries1.getData().add(new XYChart.Data<>("2015", 540));

        stackedBarChart.getData().add(dataSeries1);

        XYChart.Series<String, Number> dataSeries2 = new XYChart.Series<>();
        dataSeries2.setName("Phone");

        dataSeries2.getData().add(new XYChart.Data<>("2014", 65));
        dataSeries2.getData().add(new XYChart.Data<>("2015", 120));

        stackedBarChart.getData().add(dataSeries2);

        XYChart.Series<String, Number> dataSeries3 = new XYChart.Series<>();
        dataSeries3.setName("Tablet");

        dataSeries3.getData().add(new XYChart.Data<>("2014", 23));
        dataSeries3.getData().add(new XYChart.Data<>("2015", 36));

        stackedBarChart.getData().add(dataSeries3);
        return stackedBarChart;
    }
}
