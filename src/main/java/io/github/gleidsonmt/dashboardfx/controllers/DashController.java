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

import io.github.gleidsonmt.dashboardfx.core.app.interfaces.View;
import io.github.gleidsonmt.dashboardfx.core.app.material.color.Colors;
import io.github.gleidsonmt.dashboardfx.core.app.services.Context;
import io.github.gleidsonmt.dashboardfx.core.app.view_wrapper.ActionView;
import io.github.gleidsonmt.dashboardfx.core.app.view_wrapper.ResponsiveView;
import io.github.gleidsonmt.dashboardfx.core.controls.DonutChart;
import io.github.gleidsonmt.dashboardfx.core.controls.GNBadge;
import io.github.gleidsonmt.dashboardfx.core.layout.conteiners.creators.CardCreator;
import io.github.gleidsonmt.dashboardfx.core.layout.conteiners.creators.ScheduleListCreator;
import io.github.gleidsonmt.dashboardfx.core.layout.conteiners.creators.ScheduleListItem;
import io.github.gleidsonmt.gncontrols.controls.GNAvatarStatus;
import io.github.gleidsonmt.gncontrols.controls.GNIconButton;
import io.github.gleidsonmt.gncontrols.material.icon.Icons;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.geometry.VPos;
import javafx.scene.chart.*;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;

import java.net.URL;
import java.time.LocalTime;
import java.util.Arrays;
import java.util.ResourceBundle;

/**
 * @author Gleidson Neves da Silveira | gleidisonmt@gmail.com
 * Create on  04/10/2022
 */
@SuppressWarnings("unchecked")
public final class DashController extends ResponsiveView implements ActionView, Initializable {

    public Label lblCustom;
    @FXML
    private StackedAreaChart<Number, Number> graphic;
    @FXML
    private VBox body;
    @FXML
    private GridPane footer;

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        //Creating the Area chart
        graphic.setTitle("Sales by Region");

        //Prepare XYChart.Series objects by setting data
        XYChart.Series<Number, Number> series1 = new XYChart.Series<>();
        series1.setName("North");

        series1.getData().add(new XYChart.Data<>(10.5, 100.0));
        series1.getData().add(new XYChart.Data<>(18d, 70d));
        series1.getData().add(new XYChart.Data<>(10d, 21d));
        series1.getData().add(new XYChart.Data<>(42d, 90d));
        series1.getData().add(new XYChart.Data<>(45d, 110d));
        series1.getData().add(new XYChart.Data<>(57d, 90d));
        series1.getData().add(new XYChart.Data<>(59d, 86d));
        series1.getData().add(new XYChart.Data<>(86d, 20d));
        series1.getData().add(new XYChart.Data<>(97d, 30d));
        series1.getData().add(new XYChart.Data<>(99d, 110d));

        XYChart.Series<Number, Number> series2 = new XYChart.Series();
        series2.setName("East");

        series2.getData().add(new XYChart.Data<>(11d, 110d));
        series2.getData().add(new XYChart.Data<>(11.5d, 120d));
        series2.getData().add(new XYChart.Data<>(19d, 110d));
        series2.getData().add(new XYChart.Data<>(32d, 90d));
        series2.getData().add(new XYChart.Data<>(48d, 140d));
        series2.getData().add(new XYChart.Data<>(49d, 104d));
        series2.getData().add(new XYChart.Data<>(77d, 50d));
        series2.getData().add(new XYChart.Data<>(79d, 140d));
        series2.getData().add(new XYChart.Data<>(90d, 120d));
        series2.getData().add(new XYChart.Data<>(100d, 90d));

        XYChart.Series<Number, Number> series3 = new XYChart.Series<>();
        series3.setName("South");

        series3.getData().add(new XYChart.Data<>(5d, 15d));
        series3.getData().add(new XYChart.Data<>(20d, 110d));
        series3.getData().add(new XYChart.Data<>(13d, 230d));
        series3.getData().add(new XYChart.Data<>(27d, 180d));
        series3.getData().add(new XYChart.Data<>(42d, 160d));
        series3.getData().add(new XYChart.Data<>(49d, 100d));
        series3.getData().add(new XYChart.Data<>(53d, 150d));
        series3.getData().add(new XYChart.Data<>(58d, 200d));
        series3.getData().add(new XYChart.Data<>(70d, 190d));
        series3.getData().add(new XYChart.Data<>(94d, 160d));

        //Setting the data to area chart

//        graphic.getData().
        graphic.getData().addAll(series1, series2, series3);






        View scheduleList = new ScheduleListCreator()
                .title("Schedule")
                .items(
                        new ScheduleListItem(
                                4,
                                "UI/UX Shopping",
                                "10 of 45 chapters",
                                "Gleidson",
                                LocalTime.of(12, 45)
                        ),
                        new ScheduleListItem(
                                20,
                                "UI/UX Shopping",
                                "10 of 45 chapters",
                                "Gleidson",
                                LocalTime.of(12, 45)
                        ),
                        new ScheduleListItem(
                                35,
                                "UI/UX Shopping",
                                "10 of 45 chapters",
                                "Gleidson",
                                LocalTime.of(12, 45),
                                event -> System.out.println("Click action trigged!")

                        )
                )
                .build();

        CategoryAxis xAxis = new CategoryAxis();
        xAxis.setCategories(FXCollections.observableArrayList(
                Arrays.asList("10", "20", "30", "40", "50", "60", "70" )));

        NumberAxis yAxis = new NumberAxis(0, 1000, 100);
        yAxis.setLabel("Population in Millions");

        BarChart<String, Number> barChart = new BarChart<>(xAxis, yAxis);
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

        Image image = new Image("logo_flier.png");
        CardCreator card = new CardCreator(
                image, "Title", """
                Lorem ipsum dolor sit amet, consectetur adipiscing elit. 
                Sed non convallis lorem. Nulla et sapien pulvinar, 
                vestibulum ex id, laoreet lectus. 
                """
        );

        footer.getChildren().addAll(createDonut(), barChart,  scheduleList.getRoot(), card);
        GridPane.setConstraints(footer.getChildren().get(0), 0, 0, 1, 1, HPos.LEFT, VPos.CENTER, Priority.SOMETIMES, Priority.SOMETIMES);
        GridPane.setConstraints(barChart, 1, 0, 1, 1, HPos.LEFT, VPos.CENTER, Priority.ALWAYS, Priority.ALWAYS);
        GridPane.setConstraints(scheduleList.getRoot(), 0, 1, 1, 1, HPos.LEFT, VPos.CENTER, Priority.ALWAYS, Priority.ALWAYS);
        GridPane.setConstraints(card, 1, 1, 1, 1, HPos.LEFT, VPos.CENTER, Priority.ALWAYS, Priority.ALWAYS);

    }

    private DonutChart createDonut() {
        DonutChart donutChart = new DonutChart();

        ObservableList<PieChart.Data> data = FXCollections.observableArrayList();
        data.add(new PieChart.Data("left", 30));
        data.add(new PieChart.Data("top", 20));
        data.add(new PieChart.Data("bottom", 10));
        data.add(new PieChart.Data("right", 40));

        donutChart.setTitle("Processors");
        donutChart.setAnimated(true);
        donutChart.setLabelsVisible(true);
        donutChart.setLabelLineLength(10);
        donutChart.setMinHeight(400);
        donutChart.setData(data);
        return donutChart;
    }

    boolean load = false;

    @Override
    public void onEnter(Context context) {
        if (!load) {

            Label title = new Label("Dashboard");
            title.textProperty().bindBidirectional(context.routes().title());
            title.setPadding(new Insets(0,0,0,5));
            title.getStyleClass().addAll("title", "text-14");

            context.bar().addInLeft(title);

            GNBadge notification = new GNBadge(Icons.NOTIFICATIONS);
            notification.setColorCircle(Color.web(Colors.AQUA.toString()));
            GNBadge sms = new GNBadge(Icons.SMS);
//            sms.setColorCircle(Color.web(Colors.GRAPEFRUIT.toString()));

            Pane space = new Pane();
            space.setMinWidth(10);

            HBox options = new HBox();
            options.setAlignment(Pos.CENTER);
            GNAvatarStatus avatarStatus = new GNAvatarStatus();
            avatarStatus.setImage(new Image("avatar.jpg"));

            GNIconButton btnArrow = new GNIconButton(Icons.ARROW_DROP_DOWN);
            btnArrow.getStyleClass().addAll("btn-flat", "no-border");
            options.getChildren().setAll(avatarStatus, new Text("Gleidson Neves"), btnArrow);
            avatarStatus.setPadding(new Insets(2));
            avatarStatus.setRadius(15);
            context.root().bar().addInRight(sms, notification, space, options);


            load = true;
        }
    }

    @Override
    public void onExit(Context context) {

    }

    @Override
    public void onInit(Context context) {
    }
}