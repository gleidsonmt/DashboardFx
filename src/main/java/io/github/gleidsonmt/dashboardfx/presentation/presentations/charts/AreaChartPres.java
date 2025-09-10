package io.github.gleidsonmt.dashboardfx.presentation.presentations.charts;

import io.github.gleidsonmt.dashboardfx.presentation.CustomizablePresentation;
import io.github.gleidsonmt.dashboardfx.presentation.internal.Tutorial;
import io.github.gleidsonmt.glad.charts.CurvedChart;
import javafx.scene.Node;
import javafx.scene.chart.AreaChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;

/**
 * @author Gleidson Neves da Silveira | gleidisonmt@gmail.com
 * Create on  13/03/2025
 */
public class AreaChartPres extends CustomizablePresentation {

    public AreaChartPres() {
        super("AreaChart");
    }

    public Tutorial create() {
        return new Tutorial()
                .h3("Area Chart")
                .text("""
                        AreaChart - Plots the area between the line that connects the data points and the 0 line on the Y axis.
                        """)
                .legend("javafx.scene.chart.AreaChart")
                .demo(createChart())
                .code("""
                        NumberAxis xAxis = new NumberAxis();
                        xAxis.setLabel("No of employees");
                        
                        NumberAxis yAxis = new NumberAxis();
                        yAxis.setLabel("Revenue per employee");
                        
                        AreaChart<Number, Number> areaChart = new AreaChart<>(xAxis, yAxis);
                        
                        XYChart.Series<Number, Number> dataSeries1 = new XYChart.Series<>();
                        dataSeries1.setName("2014");
                        
                        dataSeries1.getData().add(new XYChart.Data<>( 1, 120));
                        dataSeries1.getData().add(new XYChart.Data<>( 5, 150));
                        dataSeries1.getData().add(new XYChart.Data<>(10, 354));
                        dataSeries1.getData().add(new XYChart.Data<>(20, 500));
                        dataSeries1.getData().add(new XYChart.Data<>(40, 452));
                        dataSeries1.getData().add(new XYChart.Data<>(80, 630));
                        areaChart.getData().add(dataSeries1);
                        """)
                .h3("Curved Area Chart")
                .text("""
                        Descendant of area chart with curves.
                        """)
                .legend("io.github.gleidsonmt.glad.charts.ContributionsChart")
                .demo(createCurved())
                .code("""
                        NumberAxis xAxis = new NumberAxis();
                        xAxis.setLabel("No of employees");
                        
                        NumberAxis yAxis = new NumberAxis();
                        yAxis.setLabel("Revenue per employee");
                        
                        
                        XYChart.Series<Number, Number> dataSeries1 = new XYChart.Series<>();
                        dataSeries1.setName("2014");
                        
                        dataSeries1.getData().add(new XYChart.Data<>(1, 120));
                        dataSeries1.getData().add(new XYChart.Data<>(5, 150));
                        dataSeries1.getData().add(new XYChart.Data<>(10, 354));
                        dataSeries1.getData().add(new XYChart.Data<>(20, 500));
                        dataSeries1.getData().add(new XYChart.Data<>(40, 452));
                        dataSeries1.getData().add(new XYChart.Data<>(80, 630));
                        
                        ContributionsChart<Number, Number> curvedChart = new ContributionsChart<>(
                                new NumberAxis(),
                                new NumberAxis()
                        );
                        curvedChart.getData().add(dataSeries1);
                        """);
    }

    public Node createChart() {
        NumberAxis xAxis = new NumberAxis();
        xAxis.setLabel("No of employees");

        NumberAxis yAxis = new NumberAxis();
        yAxis.setLabel("Revenue per employee");

        AreaChart<Number, Number> areaChart = new AreaChart<>(xAxis, yAxis);

        XYChart.Series<Number, Number> dataSeries1 = new XYChart.Series<>();
        dataSeries1.setName("2014");

        dataSeries1.getData().add(new XYChart.Data<>(1, 120));
        dataSeries1.getData().add(new XYChart.Data<>(5, 150));
        dataSeries1.getData().add(new XYChart.Data<>(10, 354));
        dataSeries1.getData().add(new XYChart.Data<>(20, 500));
        dataSeries1.getData().add(new XYChart.Data<>(40, 452));
        dataSeries1.getData().add(new XYChart.Data<>(80, 630));
        areaChart.getData().add(dataSeries1);
        return areaChart;
    }

    private Node createCurved() {

        NumberAxis xAxis = new NumberAxis();
        xAxis.setLabel("No of employees");

        NumberAxis yAxis = new NumberAxis();
        yAxis.setLabel("Revenue per employee");


        XYChart.Series<Number, Number> dataSeries1 = new XYChart.Series<>();
        dataSeries1.setName("2014");

        dataSeries1.getData().add(new XYChart.Data<>(1, 120));
        dataSeries1.getData().add(new XYChart.Data<>(5, 150));
        dataSeries1.getData().add(new XYChart.Data<>(10, 354));
        dataSeries1.getData().add(new XYChart.Data<>(20, 500));
        dataSeries1.getData().add(new XYChart.Data<>(40, 452));
        dataSeries1.getData().add(new XYChart.Data<>(80, 630));

        CurvedChart<Number, Number> curvedChart = new CurvedChart<>(
                new NumberAxis(),
                new NumberAxis()
        );
        curvedChart.getData().add(dataSeries1);

        return curvedChart;
    }
}

//CategoryAxis xAxis = new CategoryAxis();
//        xAxis.setCategories(FXCollections.observableArrayList(
//        Arrays.asList("10", "20", "30", "40", "50", "60", "70" )));
//
//NumberAxis yAxis = new NumberAxis(0, 1000, 100);
//        yAxis.setLabel("Population in Millions");
//
//BarChart<String, Number> barChart = new BarChart<>(xAxis, yAxis);
//        barChart.getStyleClass().addAll("border-box", "border-1");
//XYChart.Series<String, Number> s = new XYChart.Series<>();
//        s.getData().add(new XYChart.Data<>("20", 40));
//        s.getData().add(new XYChart.Data<>("30", 300));
//        s.getData().add(new XYChart.Data<>("40", 500));
//        s.getData().add(new XYChart.Data<>("50", 798));
//        s.setName("North");
//
//XYChart.Series<String, Number> b = new XYChart.Series<>();
//        b.getData().add(new XYChart.Data<>("20", 146));
//        b.getData().add(new XYChart.Data<>("30", 456));
//        b.getData().add(new XYChart.Data<>("40", 234));
//        b.getData().add(new XYChart.Data<>("50", 609));
//        b.setName("South");
//
//XYChart.Series<String, Number> c = new XYChart.Series<>();
//        c.getData().add(new XYChart.Data<>("40", 200));
//        c.getData().add(new XYChart.Data<>("60", 280));
//        c.getData().add(new XYChart.Data<>("40", 900));
//        c.getData().add(new XYChart.Data<>("40", 700));
//        c.setName("East");
//        barChart.getData().addAll(s, b, c);
//
//ContributionsChart<Number, Number> curvedChart = new ContributionsChart<>(
//        new NumberAxis(),
//        new NumberAxis()
//);

