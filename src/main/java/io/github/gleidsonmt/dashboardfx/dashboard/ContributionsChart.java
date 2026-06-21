package io.github.gleidsonmt.dashboardfx.dashboard;

import io.github.gleidsonmt.glad.charts.CurvedChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Tooltip;
import javafx.scene.shape.Circle;
import javafx.util.Pair;

import java.util.List;

/**
 * @author Gleidson Neves da Silveira | gleidisonmt@gmail.com
 * Create on  10/08/2025
 */
public class ContributionsChart extends CurvedChart<Number, Number> {

    public ContributionsChart() {
        super(new NumberAxis(2016, 2025, 1), new NumberAxis(0, 700, 100));
        super.getYAxis().setLabel("Total");

        XYChart.Series<Number, Number> dataSeries1 = new XYChart.Series<>();
        dataSeries1.setName("Years");
        XYChart.Data<Number, Number> testeOne = new XYChart.Data<>(2016, 1);
        testeOne.setExtraValue(2016);

        List<Pair<Integer, Integer>> data = List.of(
                new Pair<>(2016, 1),
                new Pair<>(2017, 1),
                new Pair<>(2018, 496),
                new Pair<>(2019, 194),
                new Pair<>(2020, 145),
                new Pair<>(2021, 14),
                new Pair<>(2022, 631),
                new Pair<>(2023, 483),
                new Pair<>(2024, 420),
                new Pair<>(2025, 223)
        );

        data.forEach(pair -> {
            XYChart.Data<Number, Number> data1 = new XYChart.Data<>(pair.getKey(), pair.getValue());
            dataSeries1.getData().add(data1);
            Circle circle = new Circle();
            circle.setRadius(5);
            Tooltip.install(circle, new Tooltip(pair.getValue() + " Entries in " + pair.getKey()));
            data1.setNode(circle);
            circle.toFront();
        });

        setTitle("Contributions (GitHub)");
        //noinspection unchecked
        getData().setAll(dataSeries1);

        getStyleClass().addAll("bg-foreground", "border-2", "border-theme", "radius-5", "font-instagram");


    }
}
