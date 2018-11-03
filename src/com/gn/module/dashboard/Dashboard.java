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

import eu.hansolo.tilesfx.Tile;
import eu.hansolo.tilesfx.chart.ChartData;
import eu.hansolo.tilesfx.chart.TilesFXSeries;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.PieChart;
import javafx.scene.chart.XYChart;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.paint.CycleMethod;
import javafx.scene.paint.LinearGradient;
import javafx.scene.paint.Stop;
import javafx.scene.shape.Circle;

import java.net.URL;
import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.ResourceBundle;

/**
 * @author Gleidson Neves da Silveira | gleidisonmt@gmail.com
 * Create on  20/10/2018
 * Version 1.0
 */
public class Dashboard implements Initializable {

    @FXML private Tile calendar;

    @FXML private PieChart pieChart;
    @FXML private ImageView avatarFollow;

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        calendar.setSkinType(Tile.SkinType.CALENDAR);
        calendar.setForegroundColor(Color.RED);
        calendar.setBackgroundColor(Color.TRANSPARENT);

        ZonedDateTime now          = ZonedDateTime.now();
        List<ChartData> calendarData = new ArrayList<>(10);
        calendarData.add(new ChartData("Item 1", now.minusDays(1).toInstant()));
        calendarData.add(new ChartData("Item 2", now.plusDays(2).toInstant()));
        calendarData.add(new ChartData("Item 3", now.plusDays(10).toInstant()));


        calendarData.add(new ChartData("Item 4", 0.0, javafx.scene.paint.Color.BLUE, javafx.scene.paint.Color.RED, now.plusDays(5).toInstant(), true, 1000));

        calendar.setChartData(calendarData);
        calendar.setTooltipText("Tol tip de merda");


        ObservableList<PieChart.Data> pieChartData = FXCollections.observableArrayList(
                new PieChart.Data("Sun", 20),
                new PieChart.Data("IBM", 12),
                new PieChart.Data("HP", 25),
                new PieChart.Data("Dell", 22),
                new PieChart.Data("Apple", 30)
        );
        pieChart.setData(pieChartData);
        pieChart.setClockwise(false);

        Circle circle = new Circle(80);
        circle.setStroke(Color.WHITE);
        circle.setStrokeWidth(5);
        circle.setStroke(Color.RED);
        circle.setCenterX(avatarFollow.getFitWidth() / 2);
        circle.setCenterY(avatarFollow.getFitHeight() / 2);
        avatarFollow.setClip(circle);
    }
}
