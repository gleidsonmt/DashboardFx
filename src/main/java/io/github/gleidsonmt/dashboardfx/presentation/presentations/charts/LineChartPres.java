package io.github.gleidsonmt.dashboardfx.presentation.presentations.charts;

import io.github.gleidsonmt.dashboardfx.presentation.CustomizablePresentation;
import io.github.gleidsonmt.dashboardfx.presentation.internal.Tutorial;
import io.github.gleidsonmt.presentation.Presentation;
import javafx.scene.Node;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;

/**
 * @author Gleidson Neves da Silveira | gleidisonmt@gmail.com
 * Create on  13/03/2025
 */
public class LineChartPres extends CustomizablePresentation {


    public LineChartPres() {
        super("LineChart");
    }

    @Override
    public Presentation create() {
        return new Tutorial()
                .h3("Line Chart")
                .text("""
                        StackedAreaChart is a variation of AreaChart that displays trends of the contribution of each value. (over time e.g.) 
                        The areas are stacked so that each series adjoins but does not overlap the preceding series. 
                        This contrasts with the Area chart where each series overlays the preceding series. 
                        The cumulative nature of the StackedAreaChart gives an idea of the total Y data value at any given point along the X axis. 
                        Since data points across multiple series may not be common, StackedAreaChart interpolates values along the line joining the data points whenever necessary.
                        """)
                .legend("javafx.scene.chart.LineChart")
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
        xAxis.setLabel("No of employees");

        NumberAxis yAxis = new NumberAxis();
        yAxis.setLabel("Revenue per employee");

        LineChart<Number, Number> lineChart = new LineChart<>(xAxis, yAxis);

        XYChart.Series<Number, Number> dataSeries1 = new XYChart.Series<>();
        dataSeries1.setName("2014");

        dataSeries1.getData().add(new XYChart.Data<>(1, 200));
        dataSeries1.getData().add(new XYChart.Data<>(5, 300));
        dataSeries1.getData().add(new XYChart.Data<>(10, 150));
        dataSeries1.getData().add(new XYChart.Data<>(20, 245));
        dataSeries1.getData().add(new XYChart.Data<>(40, 123));
        dataSeries1.getData().add(new XYChart.Data<>(80, 312));

        lineChart.getData().add(dataSeries1);

        XYChart.Series<Number, Number> dataSeries2 = new XYChart.Series<>();
        dataSeries2.setName("2014");

        dataSeries2.getData().add(new XYChart.Data<>(1, 425));
        dataSeries2.getData().add(new XYChart.Data<>(5, 523));
        dataSeries2.getData().add(new XYChart.Data<>(10, 756));
        dataSeries2.getData().add(new XYChart.Data<>(20, 664));
        dataSeries2.getData().add(new XYChart.Data<>(40, 786));
        dataSeries2.getData().add(new XYChart.Data<>(80, 810));

        lineChart.getData().add(dataSeries2);

        return lineChart;
    }
}
