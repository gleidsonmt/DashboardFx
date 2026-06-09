package io.github.gleidsonmt.dashboardfx.presentation.presentations.charts;

import io.github.gleidsonmt.dashboardfx.presentation.CustomizablePresentation;
import io.github.gleidsonmt.dashboardfx.presentation.internal.Tutorial;
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
    public Tutorial create() {
        return new Tutorial()
                .h3("Line Chart")
                .text("""
                        LineChart displays data points connected by straight line segments.
                        It is useful for showing how values change over time or across ordered numeric values.
                        Each series is drawn as its own line, making it easy to compare trends between multiple data sets.
                        """)
                .legend("javafx.scene.chart.LineChart")
                .demo(createChart())
                .code("""
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
