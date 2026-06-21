package io.github.gleidsonmt.dashboardfx.dashboard;

import javafx.collections.FXCollections;
import javafx.scene.chart.*;
import javafx.scene.control.Label;

import java.util.Arrays;

/**
 * @author Gleidson Neves da Silveira | gleidisonmt@gmail.com
 * Create on  10/08/2025
 */
public class VideoCardChart extends BarChart<String, Number> {
    public VideoCardChart() {
        super(
                new CategoryAxis(FXCollections.observableArrayList("1080 FPS", "1440 FPS", "4K FPS")),
                new NumberAxis(0, 500, 100)
        );

        super.getXAxis().setAnimated(true);
        super.getYAxis().setAnimated(true);
        super.getYAxis().setLabel("Default FPS");

        setCategoryGap(50);
        setSnapToPixel(true);
        setMinHeight(300);
        setLegendVisible(true);
        setAnimated(true);
        setTitle("Graphic Cards Performance");
        getStyleClass().addAll("border-box", "border-1");
        getStyleClass().addAll("bg-foreground border-2 border-theme radius-5 font-poppins".split(" "));

        XYChart.Series<String, Number> rtx5090 = new XYChart.Series<>();
        rtx5090.setNode(new Label("LUAL"));
        rtx5090.setName("RTX 5090");
        rtx5090.getData().add(new XYChart.Data<>("1080 FPS", 409.7));
        rtx5090.getData().add(new XYChart.Data<>("1440 FPS", 309.2));
        rtx5090.getData().add(new XYChart.Data<>("4K FPS", 194.4));
        getData().add(rtx5090);

        XYChart.Series<String, Number> rtx4090 = new XYChart.Series<>();
        rtx4090.setName("RTX 4090");
        rtx4090.getData().add(new XYChart.Data<>("1080 FPS", 356.1));
        rtx4090.getData().add(new XYChart.Data<>("1440 FPS", 272.5));
        rtx4090.getData().add(new XYChart.Data<>("4K FPS", 172.7));
        getData().add(rtx4090);

        XYChart.Series<String, Number> rtx4080ti = new XYChart.Series<>();
        rtx4080ti.setName("RTX 4080 Ti");
        rtx4080ti.getData().add(new XYChart.Data<>("1080 FPS", 332.3));
        rtx4080ti.getData().add(new XYChart.Data<>("1440 FPS", 254.3));
        rtx4080ti.getData().add(new XYChart.Data<>("4K FPS", 161.2));
        getData().add(rtx4080ti);

        XYChart.Series<String, Number> rtx4080 = new XYChart.Series<>();
        rtx4080.setName("RTX 4080 Ti");
        rtx4080.getData().add(new XYChart.Data<>("1080 FPS", 308.6));
        rtx4080.getData().add(new XYChart.Data<>("1440 FPS", 236));
        rtx4080.getData().add(new XYChart.Data<>("4K FPS", 149.6));
        getData().add(rtx4080);
    }
}
