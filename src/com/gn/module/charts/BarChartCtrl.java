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
package com.gn.module.charts;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.*;

import java.net.URL;
import java.util.ResourceBundle;

/**
 * @author Gleidson Neves da Silveira | gleidisonmt@gmail.com
 * Create on  21/10/2018
 * Version 1.0
 */
public class BarChartCtrl implements Initializable {

    @FXML
    private BarChart<String, Number> barChart;

    @SuppressWarnings("unchecked")
    @Override
    public void initialize(URL location, ResourceBundle resources) {

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
//        barChart.setCreateSymbols(true);
    }
}
