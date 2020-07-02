/*
 * Copyright (C) Gleidson Neves da Silveira
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package com.gn.module.dashboard;

import com.gn.GNCarousel;
import com.gn.global.plugin.ActionView;
import com.gn.global.plugin.GridFx;
import core.CurveFittedAreaChart;
import core.DonutChart;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.chart.*;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;

import java.net.URL;
import java.util.ResourceBundle;

/**
 * @author Gleidson Neves da Silveira | gleidisonmt@gmail.com
 * Create on  20/10/2018
 * Version 1.0
 */
public class Dashboard implements Initializable, ActionView {

    @FXML private StackPane root;
    @FXML private CurveFittedAreaChart<Number, Number> curve;
    @FXML private DonutChart donutChart;
    @FXML private GNCarousel carousel;

    @FXML private GridPane tiles;
    @FXML private GridPane bodyCenter;
    @FXML private GridPane graphic;
    @FXML private GridPane socialTiles;

    private CurveFittedAreaChart<Number, Number> curvedChart = new CurveFittedAreaChart<>();

    @FXML private BarChart<String, Number> barChart;
    @FXML private AreaChart<String, Number> areaChart;
    @FXML private LineChart<String, Number> lineChart;


    @FXML private PieChart pieChart;

    @SuppressWarnings("unchecked")
    @Override
    public void initialize(URL location, ResourceBundle resources) {

        ObservableList<PieChart.Data> pieChartData = FXCollections.observableArrayList(
                new PieChart.Data("Sun", 20),
                new PieChart.Data("IBM", 12),
                new PieChart.Data("HP", 25),
                new PieChart.Data("Dell", 22),
                new PieChart.Data("Apple", 30)
        );
//        pieChart.setData(pieChartData);
//        pieChart.setClockwise(false);
        donutChart.setData(pieChartData);

        carousel.setItems(createItems());

        final XYChart.Series<Number, Number> series2 = new XYChart.Series<Number, Number>();

        series2.getData().addAll(
                new XYChart.Data<Number, Number>(0, 20D),
                new XYChart.Data<Number, Number>(1, 40D),
                new XYChart.Data<Number, Number>(2, 50D),
                new XYChart.Data<Number, Number>(3, 30D),
                new XYChart.Data<Number, Number>(4, 80D),
                new XYChart.Data<Number, Number>(5, 10D),
                new XYChart.Data<Number, Number>(6, 50D));

        curvedChart = new CurveFittedAreaChart<>(
                new NumberAxis(),
                new NumberAxis()
        );

        curve.getData().add(series2);

        populateBarChart();
        populateAreaChart();
        populateLineChart();
    }


    private ObservableList<Node> createItems(){

        Label lb1 = new Label("First");
        Label lb2 = new Label("Second");
        Label lb3 = new Label("Third");
        Label lb4 = new Label("Fourth");
        Label lb5 = new Label("Fifth");

        lb1.setStyle("-fx-text-fill : white;");
        lb2.setStyle("-fx-text-fill : white;");
        lb3.setStyle("-fx-text-fill : white;");
        lb4.setStyle("-fx-text-fill : white;");
        lb5.setStyle("-fx-text-fill : white;");

        VBox v1 = new VBox(lb1);
        VBox v2 = new VBox(lb2);
        VBox v3 = new VBox(lb3);
        VBox v4 = new VBox(lb4);
        VBox v5 = new VBox(lb5);

        v1.setAlignment(Pos.CENTER);
        v2.setAlignment(Pos.CENTER);
        v3.setAlignment(Pos.CENTER);
        v4.setAlignment(Pos.CENTER);
        v5.setAlignment(Pos.CENTER);

        v1.setStyle("-fx-background-color : #FF3547;");
        v2.setStyle("-fx-background-color : #512DA8;");
        v3.setStyle("-fx-background-color : #48CFAD;");
        v4.setStyle("-fx-background-color : #02C852;");
        v5.setStyle("-fx-background-color : #EC407A;");

        return FXCollections.observableArrayList(v1, v2, v3, v4, v5);
    }

    @Override
    public void enter() {
        GridFx.add(tiles, "tiles");
        GridFx.add(bodyCenter, "body-center");
        GridFx.add(graphic, "graphic");
        GridFx.add(socialTiles, "social-tiles");
        GridFx.defineMin("min-dashboard", root, 2800, 2400, 2000, 1600, 1200);
    }

    @Override
    public void exit() {
        GridFx.remove("tiles");
        GridFx.remove("body-center");
        GridFx.remove("graphic");
        GridFx.remove("social-tiles");
    }

    private void populateBarChart(){
        XYChart.Series<String, Number> series = new XYChart.Series<>();
        series.setName("Legend 1");
        series.getData().add(new XYChart.Data<>("1", 8D));
        series.getData().add(new XYChart.Data<>("2", 5D));
        series.getData().add(new XYChart.Data<>("3", 3D));
        series.getData().add(new XYChart.Data<>("4", 6D));
        series.getData().add(new XYChart.Data<>("5", 8D));
        series.getData().add(new XYChart.Data<>("6", 5D));
        series.getData().add(new XYChart.Data<>("7", 6D));
        series.getData().add(new XYChart.Data<>("8", 5D));


        XYChart.Series<String, Number> secondaySeries = new XYChart.Series<>();
        secondaySeries.setName("Legend 2");
        secondaySeries.getData().add(new XYChart.Data<>("1", 2D));
        secondaySeries.getData().add(new XYChart.Data<>("2", 7D));
        secondaySeries.getData().add(new XYChart.Data<>("3", 8D));
        secondaySeries.getData().add(new XYChart.Data<>("4", 3D));
        secondaySeries.getData().add(new XYChart.Data<>("5", 2D));
        secondaySeries.getData().add(new XYChart.Data<>("6", 3D));
        secondaySeries.getData().add(new XYChart.Data<>("7", 4D));
        secondaySeries.getData().add(new XYChart.Data<>("8", 5D));


        XYChart.Series<String, Number> thirdSeries = new XYChart.Series<>();
        thirdSeries.setName("Legend 3");
        thirdSeries.getData().add(new XYChart.Data<>("1", 6D));
        thirdSeries.getData().add(new XYChart.Data<>("2", 3D));
        thirdSeries.getData().add(new XYChart.Data<>("3", 4D));
        thirdSeries.getData().add(new XYChart.Data<>("4", 3D));
        thirdSeries.getData().add(new XYChart.Data<>("5", 8D));
        thirdSeries.getData().add(new XYChart.Data<>("6", 3D));
        thirdSeries.getData().add(new XYChart.Data<>("7", 2D));
        thirdSeries.getData().add(new XYChart.Data<>("8", 1D));


        barChart.getData().setAll(series, secondaySeries, thirdSeries);
    }

    private void populateAreaChart(){

        XYChart.Series<String, Number> series = new XYChart.Series<>();
        series.setName("Legend 1");
        series.getData().add(new XYChart.Data<>("0", 2D));
        series.getData().add(new XYChart.Data<>("1", 8D));
        series.getData().add(new XYChart.Data<>("2", 5D));
        series.getData().add(new XYChart.Data<>("3", 3D));
        series.getData().add(new XYChart.Data<>("4", 6D));
        series.getData().add(new XYChart.Data<>("5", 8D));
        series.getData().add(new XYChart.Data<>("6", 5D));
        series.getData().add(new XYChart.Data<>("7", 6D));
        series.getData().add(new XYChart.Data<>("8", 5D));


        XYChart.Series<String, Number> secondaySeries = new XYChart.Series<>();
        secondaySeries.setName("Legend 2");
        secondaySeries.getData().add(new XYChart.Data<>("0", 2D));
        secondaySeries.getData().add(new XYChart.Data<>("1", 2D));
        secondaySeries.getData().add(new XYChart.Data<>("2", 7D));
        secondaySeries.getData().add(new XYChart.Data<>("3", 8D));
        secondaySeries.getData().add(new XYChart.Data<>("4", 3D));
        secondaySeries.getData().add(new XYChart.Data<>("5", 2D));
        secondaySeries.getData().add(new XYChart.Data<>("6", 3D));
        secondaySeries.getData().add(new XYChart.Data<>("7", 4D));
        secondaySeries.getData().add(new XYChart.Data<>("8", 5D));


        XYChart.Series<String, Number> thirdSeries = new XYChart.Series<>();
        thirdSeries.setName("Legend 3");
        thirdSeries.getData().add(new XYChart.Data<>("0", 0D));
        thirdSeries.getData().add(new XYChart.Data<>("1", 6D));
        thirdSeries.getData().add(new XYChart.Data<>("2", 3D));
        thirdSeries.getData().add(new XYChart.Data<>("3", 4D));
        thirdSeries.getData().add(new XYChart.Data<>("4", 3D));
        thirdSeries.getData().add(new XYChart.Data<>("5", 8D));
        thirdSeries.getData().add(new XYChart.Data<>("6", 3D));
        thirdSeries.getData().add(new XYChart.Data<>("7", 2D));
        thirdSeries.getData().add(new XYChart.Data<>("8", 1D));


        areaChart.getData().setAll(series, secondaySeries, thirdSeries);
        areaChart.setCreateSymbols(true);
    }

    private void populateLineChart(){
        XYChart.Series<String, Number> series = new XYChart.Series<>();
        series.setName("Legend 1");
        series.getData().add(new XYChart.Data<>("1", 8D));
        series.getData().add(new XYChart.Data<>("2", 5D));
        series.getData().add(new XYChart.Data<>("3", 3D));
        series.getData().add(new XYChart.Data<>("4", 6D));
        series.getData().add(new XYChart.Data<>("5", 8D));
        series.getData().add(new XYChart.Data<>("6", 5D));
        series.getData().add(new XYChart.Data<>("7", 6D));
        series.getData().add(new XYChart.Data<>("8", 5D));


        XYChart.Series<String, Number> secondaySeries = new XYChart.Series<>();
        secondaySeries.setName("Legend 2");
        secondaySeries.getData().add(new XYChart.Data<>("1", 2D));
        secondaySeries.getData().add(new XYChart.Data<>("2", 7D));
        secondaySeries.getData().add(new XYChart.Data<>("3", 8D));
        secondaySeries.getData().add(new XYChart.Data<>("4", 3D));
        secondaySeries.getData().add(new XYChart.Data<>("5", 2D));
        secondaySeries.getData().add(new XYChart.Data<>("6", 3D));
        secondaySeries.getData().add(new XYChart.Data<>("7", 4D));
        secondaySeries.getData().add(new XYChart.Data<>("8", 5D));


        XYChart.Series<String, Number> thirdSeries = new XYChart.Series<>();
        thirdSeries.setName("Legend 3");
        thirdSeries.getData().add(new XYChart.Data<>("1", 6D));
        thirdSeries.getData().add(new XYChart.Data<>("2", 3D));
        thirdSeries.getData().add(new XYChart.Data<>("3", 4D));
        thirdSeries.getData().add(new XYChart.Data<>("4", 3D));
        thirdSeries.getData().add(new XYChart.Data<>("5", 8D));
        thirdSeries.getData().add(new XYChart.Data<>("6", 3D));
        thirdSeries.getData().add(new XYChart.Data<>("7", 2D));
        thirdSeries.getData().add(new XYChart.Data<>("8", 1D));


        lineChart.getData().setAll(series, secondaySeries, thirdSeries);
        lineChart.setCreateSymbols(true);
    }
}
