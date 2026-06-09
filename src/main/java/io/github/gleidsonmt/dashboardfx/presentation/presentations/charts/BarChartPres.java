package io.github.gleidsonmt.dashboardfx.presentation.presentations.charts;

import io.github.gleidsonmt.dashboardfx.presentation.CustomizablePresentation;
import io.github.gleidsonmt.dashboardfx.presentation.internal.Tutorial;
import io.github.gleidsonmt.presentation.Presentation;
import javafx.collections.FXCollections;
import javafx.scene.Node;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;

import java.util.Arrays;

/**
 * @author Gleidson Neves da Silveira | gleidisonmt@gmail.com
 * Create on  13/03/2025
 */
public class BarChartPres extends CustomizablePresentation {

    public BarChartPres() {
        super("BarChart");
    }

    public Presentation create() {
        return new Tutorial()
                .overview()
                .h3("Bar Chart")
                .separator()
                .text("""
                        A chart that plots bars indicating data values for a category.
                        The bars can be vertical or horizontal depending on which axis is a category axis.
                        Adding data with multiple occurrences of a category to a series shows the last occurrence.
                        """)
                .legend("javafx.scene.chart.BarChart")
                .demo(createChartVertical())
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

                .h3("Horizonal")
                .demo(createChartHorizontal())
                .code("""
                        CategoryAxis yAxis = new CategoryAxis();
                        yAxis.setCategories(FXCollections.observableArrayList(
                                Arrays.asList("10", "20", "30")));
                        yAxis.setLabel("Population in Millions");
                
                        NumberAxis xAxis = new NumberAxis(0, 1000, 100);
                
                        BarChart<Number, String> barChart = new BarChart<>(xAxis, yAxis);
                        barChart.setMinWidth(800);
                        barChart.getStyleClass().addAll("border-box", "border-1");
                        XYChart.Series<Number, String> s = new XYChart.Series<>();
                        s.getData().add(new XYChart.Data<>(40, "20"));
                        s.getData().add(new XYChart.Data<>(300, "30"));
                        s.getData().add(new XYChart.Data<>(500, "40"));
                        s.getData().add(new XYChart.Data<>(798, "50"));
                        s.setName("North");
                
                        XYChart.Series<Number, String> b = new XYChart.Series<>();
                        b.getData().add(new XYChart.Data<>(146, "20"));
                        b.getData().add(new XYChart.Data<>(456, "30"));
                        b.getData().add(new XYChart.Data<>(234, "40"));
                        b.getData().add(new XYChart.Data<>(609, "50"));
                        b.setName("South");
                
                        XYChart.Series<Number, String> c = new XYChart.Series<>();
                        c.getData().add(new XYChart.Data<>(200,"40" ));
                        c.getData().add(new XYChart.Data<>(280,"60" ));
                        c.getData().add(new XYChart.Data<>(900,"40" ));
                        c.getData().add(new XYChart.Data<>(700,"40" ));
                        c.setName("East");
                        //noinspection unchecked
                        barChart.getData().addAll(s, b, c);
                        """)
                ;
    }

    private BarChart<Number, String> createChartHorizontal() {

        CategoryAxis yAxis = new CategoryAxis();
        yAxis.setCategories(FXCollections.observableArrayList(
                Arrays.asList("10", "20", "30")));
        yAxis.setLabel("Population in Millions");

        NumberAxis xAxis = new NumberAxis(0, 1000, 100);

        BarChart<Number, String> barChart = new BarChart<>(xAxis, yAxis);
        barChart.setMinWidth(800);
        barChart.getStyleClass().addAll("border-box", "border-1");
        XYChart.Series<Number, String> s = new XYChart.Series<>();
        s.getData().add(new XYChart.Data<>(40, "20"));
        s.getData().add(new XYChart.Data<>(300, "30"));
        s.getData().add(new XYChart.Data<>(500, "40"));
        s.getData().add(new XYChart.Data<>(798, "50"));
        s.setName("North");

        XYChart.Series<Number, String> b = new XYChart.Series<>();
        b.getData().add(new XYChart.Data<>(146, "20"));
        b.getData().add(new XYChart.Data<>(456, "30"));
        b.getData().add(new XYChart.Data<>(234, "40"));
        b.getData().add(new XYChart.Data<>(609, "50"));
        b.setName("South");

        XYChart.Series<Number, String> c = new XYChart.Series<>();
        c.getData().add(new XYChart.Data<>(200,"40" ));
        c.getData().add(new XYChart.Data<>(280,"60" ));
        c.getData().add(new XYChart.Data<>(900,"40" ));
        c.getData().add(new XYChart.Data<>(700,"40" ));
        c.setName("East");
        //noinspection unchecked
        barChart.getData().addAll(s, b, c);
        return barChart;
    }

    public Node createChartVertical() {
        CategoryAxis xAxis = new CategoryAxis();
        xAxis.setCategories(FXCollections.observableArrayList(
                Arrays.asList("10", "20", "30")));

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
        //noinspection unchecked
        barChart.getData().addAll(s, b, c);
        return barChart;
    }
}
