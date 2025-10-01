package io.github.gleidsonmt.dashboardfx.presentation.presentations.charts;

import io.github.gleidsonmt.dashboardfx.presentation.CustomizablePresentation;
import io.github.gleidsonmt.dashboardfx.presentation.internal.Tutorial;
import javafx.scene.Node;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.StackedAreaChart;
import javafx.scene.chart.XYChart;

/**
 * @author Gleidson Neves da Silveira | gleidisonmt@gmail.com
 * Create on  13/03/2025
 */
public class StackedAreaChartPres extends CustomizablePresentation {

    public StackedAreaChartPres() {
        super("StackedAreaChart");
    }

    public Tutorial create() {
        return new Tutorial()
                .h3("Stacked Area Chart")
                .text("""
                        StackedAreaChart is a variation of AreaChart that displays trends of the contribution of each value. (over time e.g.)
                        The areas are stacked so that each series adjoins but does not overlap the preceding series.
                        This contrasts with the Area chart where each series overlays the preceding series. 
                        The cumulative nature of the StackedAreaChart gives an idea of the total Y data value at any given point along the X axis. 
                        Since data points across multiple series may not be common, StackedAreaChart interpolates values along the line joining the data points whenever necessary.
                        """)
                .legend("javafx.scene.chart.StackedAreaChart")
                .demo(createChart())
                .code("""
                        NumberAxis xAxis = new NumberAxis();
                        xAxis.setLabel("No of employees");
                        
                        NumberAxis yAxis = new NumberAxis();
                        yAxis.setLabel("Revenue per employee");
                        StackedAreaChart<Number, Number> graphic = new StackedAreaChart<>(xAxis, yAxis);
                        
                        XYChart.Series<Number, Number> series1 = new XYChart.Series<>();
                        series1.setName("North");
                        
                        series1.getData().add(new XYChart.Data<>(10.5, 100.0));
                        series1.getData().add(new XYChart.Data<>(18d, 70d));
                        series1.getData().add(new XYChart.Data<>(10d, 21d));
                        series1.getData().add(new XYChart.Data<>(42d, 90d));
                        series1.getData().add(new XYChart.Data<>(45d, 110d));
                        series1.getData().add(new XYChart.Data<>(57d, 90d));
                        series1.getData().add(new XYChart.Data<>(59d, 86d));
                        series1.getData().add(new XYChart.Data<>(86d, 20d));
                        series1.getData().add(new XYChart.Data<>(97d, 30d));
                        series1.getData().add(new XYChart.Data<>(99d, 110d));
                        
                        XYChart.Series<Number, Number> series2 = new XYChart.Series<>();
                        series2.setName("East");
                        
                        series2.getData().add(new XYChart.Data<>(11d, 110d));
                        series2.getData().add(new XYChart.Data<>(11.5d, 120d));
                        series2.getData().add(new XYChart.Data<>(19d, 110d));
                        series2.getData().add(new XYChart.Data<>(32d, 90d));
                        series2.getData().add(new XYChart.Data<>(48d, 140d));
                        series2.getData().add(new XYChart.Data<>(49d, 104d));
                        series2.getData().add(new XYChart.Data<>(77d, 50d));
                        series2.getData().add(new XYChart.Data<>(79d, 140d));
                        series2.getData().add(new XYChart.Data<>(90d, 120d));
                        series2.getData().add(new XYChart.Data<>(100d, 90d));
                        
                        XYChart.Series<Number, Number> series3 = new XYChart.Series<>();
                        series3.setName("South");
                        
                        series3.getData().add(new XYChart.Data<>(5d, 15d));
                        series3.getData().add(new XYChart.Data<>(20d, 110d));
                        series3.getData().add(new XYChart.Data<>(13d, 230d));
                        series3.getData().add(new XYChart.Data<>(27d, 180d));
                        series3.getData().add(new XYChart.Data<>(42d, 160d));
                        series3.getData().add(new XYChart.Data<>(49d, 100d));
                        series3.getData().add(new XYChart.Data<>(53d, 150d));
                        series3.getData().add(new XYChart.Data<>(58d, 200d));
                        series3.getData().add(new XYChart.Data<>(70d, 190d));
                        series3.getData().add(new XYChart.Data<>(94d, 160d));
                        
                        //Setting the data to area chart
                        
                        graphic.getData().setAll(series1, series2, series3);
                        """);
    }

    public Node createChart() {
        NumberAxis xAxis = new NumberAxis();
        xAxis.setLabel("7 Day Interval");

        NumberAxis yAxis = new NumberAxis();
        yAxis.setLabel("Visits");

        StackedAreaChart<Number, Number> stackedAreaChart = new StackedAreaChart<>(xAxis, yAxis);

        XYChart.Series<Number, Number> dataSeries1 = new XYChart.Series<>();
        dataSeries1.setName("Desktop");

        dataSeries1.getData().add(new XYChart.Data<>(0, 567));
        dataSeries1.getData().add(new XYChart.Data<>(1, 612));
        dataSeries1.getData().add(new XYChart.Data<>(2, 800));
        dataSeries1.getData().add(new XYChart.Data<>(3, 780));
        dataSeries1.getData().add(new XYChart.Data<>(4, 650));
        dataSeries1.getData().add(new XYChart.Data<>(5, 610));
        dataSeries1.getData().add(new XYChart.Data<>(6, 590));

        stackedAreaChart.getData().add(dataSeries1);

        XYChart.Series<Number, Number> dataSeries2 = new XYChart.Series<>();
        dataSeries2.setName("Mobile");

        dataSeries2.getData().add(new XYChart.Data<>(0, 101));
        dataSeries2.getData().add(new XYChart.Data<>(1, 110));
        dataSeries2.getData().add(new XYChart.Data<>(2, 140));
        dataSeries2.getData().add(new XYChart.Data<>(3, 132));
        dataSeries2.getData().add(new XYChart.Data<>(4, 115));
        dataSeries2.getData().add(new XYChart.Data<>(5, 109));
        dataSeries2.getData().add(new XYChart.Data<>(6, 105));
        stackedAreaChart.getData().add(dataSeries2);

        XYChart.Series<Number, Number> dataSeries3 = new XYChart.Series<>();
        dataSeries3.setName("Tablet");
        dataSeries3.getData().add(new XYChart.Data<>(0, 110));
        dataSeries3.getData().add(new XYChart.Data<>(1, 120));
        dataSeries3.getData().add(new XYChart.Data<>(2, 150));
        dataSeries3.getData().add(new XYChart.Data<>(3, 142));
        dataSeries3.getData().add(new XYChart.Data<>(4, 125));
        dataSeries3.getData().add(new XYChart.Data<>(5, 119));
        dataSeries3.getData().add(new XYChart.Data<>(6, 115));

        stackedAreaChart.getData().add(dataSeries3);

        return stackedAreaChart;
    }
}
