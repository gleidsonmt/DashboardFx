package io.github.gleidsonmt.dashboardfx.presentation.presentations.charts;

import io.github.gleidsonmt.dashboardfx.presentation.internal.Tutorial;
import javafx.collections.FXCollections;
import javafx.scene.Node;
import javafx.scene.chart.*;
import javafx.scene.layout.StackPane;

import java.util.Arrays;

/**
 * @author Gleidson Neves da Silveira | gleidisonmt@gmail.com
 * Create on  13/03/2025
 */
public class BarChartPres extends StackPane {
    public BarChartPres() {
        getChildren().setAll(
                new Tutorial()
                        .h3("Bar Chart")
                        .text("""
                                A chart that plots bars indicating data values for a category. 
                                The bars can be vertical or horizontal depending on which axis is a category axis.
                                Adding data with multiple occurrences of a category to a series shows the last occurrence.
                                """)
                        .legend("javafx.scene.chart.BarChart")
                        .demo(createChart())
                        .code("""
                                CategoryAxis xAxis = new CategoryAxis();
                                xAxis.setCategories(FXCollections.observableArrayList(
                                        Arrays.asList("10", "20", "30", "40", "50", "60", "70" )));
                        
                                NumberAxis yAxis = new NumberAxis(0, 1000, 100);
                                yAxis.setLabel("Population in Millions");
                        
                                BarChart<String, Number> barChart = new BarChart<>(xAxis, yAxis);
                                barChart.getStyleClass().addAll("border-box", "border-1");
                                XYChart.Series<String, Number> s = new XYChart.Series<>();
                                s.getData().add(new XYChart.Data<>("20", 40));
                                s.getData().add(new XYChart.Data<>("30", 300));
                                s.getData().add(new XYChart.Data<>("40", 500));
                                s.getData().add(new XYChart.Data<>("50", 798));
                                s.setName("North");
                        
                                XYChart.Series<String, Number> b = new XYChart.Series<>();
                                b.getData().add(new XYChart.Data<>("20", 146));
                                b.getData().add(new XYChart.Data<>("30", 456));
                                b.getData().add(new XYChart.Data<>("40", 234));
                                b.getData().add(new XYChart.Data<>("50", 609));
                                b.setName("South");
                        
                                XYChart.Series<String, Number> c = new XYChart.Series<>();
                                c.getData().add(new XYChart.Data<>("40", 200));
                                c.getData().add(new XYChart.Data<>("60", 280));
                                c.getData().add(new XYChart.Data<>("40", 900));
                                c.getData().add(new XYChart.Data<>("40", 700));
                                c.setName("East");
                                barChart.getData().addAll(s, b, c);
                                """)
                        .build().getRoot());

    }

    public Node createChart() {
        CategoryAxis xAxis = new CategoryAxis();
        xAxis.setCategories(FXCollections.observableArrayList(
                Arrays.asList("10", "20", "30" )));

        NumberAxis yAxis = new NumberAxis(0, 1000, 100);
        yAxis.setLabel("Population in Millions");

        BarChart<String, Number> barChart = new BarChart<>(xAxis, yAxis);
        barChart.setMinWidth(800);
        barChart.getStyleClass().addAll("border-box", "border-1");
        XYChart.Series<String, Number> s = new XYChart.Series<>();
        s.getData().add(new XYChart.Data<>("20", 40));
        s.getData().add(new XYChart.Data<>("30", 300));
        s.getData().add(new XYChart.Data<>("40", 500));
        s.getData().add(new XYChart.Data<>("50", 798));
        s.setName("North");

        XYChart.Series<String, Number> b = new XYChart.Series<>();
        b.getData().add(new XYChart.Data<>("20", 146));
        b.getData().add(new XYChart.Data<>("30", 456));
        b.getData().add(new XYChart.Data<>("40", 234));
        b.getData().add(new XYChart.Data<>("50", 609));
        b.setName("South");

        XYChart.Series<String, Number> c = new XYChart.Series<>();
        c.getData().add(new XYChart.Data<>("40", 200));
        c.getData().add(new XYChart.Data<>("60", 280));
        c.getData().add(new XYChart.Data<>("40", 900));
        c.getData().add(new XYChart.Data<>("40", 700));
        c.setName("East");
        barChart.getData().addAll(s, b, c);
        return barChart;
    }
}
