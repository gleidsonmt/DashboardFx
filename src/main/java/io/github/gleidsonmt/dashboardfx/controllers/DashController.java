/*
 *    Copyright (C) Gleidson Neves da Silveira
 *
 *    This program is free software: you can redistribute it and/or modify
 *    it under the terms of the GNU General Public License as published by
 *    the Free Software Foundation, either version 3 of the License, or
 *    (at your option) any later version.
 *
 *    This program is distributed in the hope that it will be useful,
 *    but WITHOUT ANY WARRANTY; without even the implied warranty of
 *    MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *    GNU General Public License for more details.
 *
 *    You should have received a copy of the GNU General Public License
 *    along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */

package io.github.gleidsonmt.dashboardfx.controllers;

import io.github.gleidsonmt.dashboardfx.core.app.services.Context;
import io.github.gleidsonmt.dashboardfx.core.app.view_wrapper.ActionView;
import io.github.gleidsonmt.dashboardfx.core.app.view_wrapper.ResponsiveView;
import io.github.gleidsonmt.dashboardfx.core.controls.DonutChart;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.HPos;
import javafx.geometry.VPos;
import javafx.scene.chart.*;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;

import java.net.URL;
import java.util.Arrays;
import java.util.ResourceBundle;

/**
 * @author Gleidson Neves da Silveira | gleidisonmt@gmail.com
 * Create on  04/10/2022
 */
public final class DashController extends ResponsiveView implements ActionView, Initializable {

    public Label lblCustom;
    @FXML private StackedAreaChart<String, Number> graphic;
    @FXML private VBox body;
    @FXML private GridPane footer;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        CategoryAxis xAxis = new CategoryAxis();
        xAxis.setCategories(FXCollections.observableArrayList(
                Arrays.asList("1750", "1800", "1850", "1900", "1950", "1999", "2050" )));

        NumberAxis yAxis = new NumberAxis(0, 10000, 2500);
        yAxis.setLabel("Population in Millions");

        //Creating the Area chart
        graphic.setTitle("Sales by Region");

        //Prepare XYChart.Series objects by setting data
        XYChart.Series series1 = new XYChart.Series();
        series1.setName("North");

        series1.getData().add(new XYChart.Data(10.5, 100));
        series1.getData().add(new XYChart.Data(18, 70));
        series1.getData().add(new XYChart.Data(10, 21));
        series1.getData().add(new XYChart.Data(42, 90));
        series1.getData().add(new XYChart.Data(45, 110));
        series1.getData().add(new XYChart.Data(57, 90));
        series1.getData().add(new XYChart.Data(59, 86));
        series1.getData().add(new XYChart.Data(86, 20));
        series1.getData().add(new XYChart.Data(97, 30));
        series1.getData().add(new XYChart.Data(99, 110));

        XYChart.Series series2 = new XYChart.Series();
        series2.setName("East");

        series2.getData().add(new XYChart.Data(11, 110));
        series2.getData().add(new XYChart.Data(11.5, 120));
        series2.getData().add(new XYChart.Data(19, 110));
        series2.getData().add(new XYChart.Data(32, 90));
        series2.getData().add(new XYChart.Data(48, 140));
        series2.getData().add(new XYChart.Data(49, 104));
        series2.getData().add(new XYChart.Data(77, 50));
        series2.getData().add(new XYChart.Data(79, 140));
        series2.getData().add(new XYChart.Data(90, 120));
        series2.getData().add(new XYChart.Data(100, 90));

        XYChart.Series series3 = new XYChart.Series();
        series3.setName("South");

        series3.getData().add(new XYChart.Data(5, 15));
        series3.getData().add(new XYChart.Data(20, 110));
        series3.getData().add(new XYChart.Data(13, 230));
        series3.getData().add(new XYChart.Data(27, 180));
        series3.getData().add(new XYChart.Data(42, 160));
        series3.getData().add(new XYChart.Data(49, 100));
        series3.getData().add(new XYChart.Data(53, 150));
        series3.getData().add(new XYChart.Data(58, 200));
        series3.getData().add(new XYChart.Data(70, 190));
        series3.getData().add(new XYChart.Data(94, 160));

        //Setting the data to area chart
        graphic.getData().addAll(series1, series2, series3);


        ObservableList<PieChart.Data> data = FXCollections.observableArrayList();
        data.add(new PieChart.Data("left", 30));
        data.add(new PieChart.Data("top", 20));
        data.add(new PieChart.Data("bottom", 10));
        data.add(new PieChart.Data("right", 40));

        DonutChart donutChart = new DonutChart();
        donutChart.setTitle("Processors");
        donutChart.setAnimated(true);
        donutChart.setLabelsVisible(true);
        donutChart.setLabelLineLength(10);
        donutChart.setMinHeight(400);
        donutChart.setData(data);

        footer.setGridLinesVisible(true);
        footer.getChildren().add(donutChart);
        GridPane.setConstraints(donutChart, 0,0,1,1, HPos.LEFT, VPos.CENTER, Priority.NEVER, Priority.NEVER);
    }

    @Override
    public void onEnter(Context context) {
    }

    @Override
    public void onExit(Context context) {

    }

    @Override
    public void onInit(Context context) {

    }

    @FXML
    private void createSnackSuccess() {
//
//        context.getDecorator()
//                .getRoot()
//                .createSnackBar()
//                .message("My Snack Succes")
//                .undo(event -> {
//                    lblCustom.setText(text);
//                })
//                .icon(Material.PERSON_ADD)
//                .color(SnackColors.SUCCESS)
//                .show();
    }

    @FXML
    private  void createSnackDanger() {
        lblCustom.setText("Created by danger");
//        context.getDecorator()
//                .getRoot()
//                .createSnackBar()
//                .message("My Dangerous Succes")
//                .undo(event -> {
//                    System.out.println("Event from snack");
//                })
//                .icon(Material.INFO)
//                .color(SnackColors.INFO)
//                .showOnTop();
    }

    @FXML
    private void createAlert(){
//        context.getDecorator().getRoot()
//                .getWrapper()
//                .getAlert()
////                .type(AlertType.WARNING)
////                .text("There's no tomorrow rock balboa.")
//                .title("Test in title")
//                .show();
//
//        Alert alert = new Alert(Alert.AlertType.INFORMATION);
//        alert.getButtonTypes().add(ButtonType.APPLY);
    }
}
