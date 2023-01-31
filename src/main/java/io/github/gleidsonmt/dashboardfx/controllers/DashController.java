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

import io.github.gleidsonmt.dashboardfx.core.app.exceptions.NavigationException;
import io.github.gleidsonmt.dashboardfx.core.app.interfaces.View;
import io.github.gleidsonmt.dashboardfx.core.app.model.NotifcationCell;
import io.github.gleidsonmt.dashboardfx.core.app.services.Context;
import io.github.gleidsonmt.dashboardfx.core.app.view_wrapper.ActionView;
import io.github.gleidsonmt.dashboardfx.core.app.view_wrapper.ResponsiveView;
import io.github.gleidsonmt.dashboardfx.core.controls.BoxUser;
import io.github.gleidsonmt.dashboardfx.core.controls.CurvedChart;
import io.github.gleidsonmt.dashboardfx.core.controls.DonutChart;
import io.github.gleidsonmt.dashboardfx.core.controls.GNBadge;
import io.github.gleidsonmt.dashboardfx.core.layout.IWrapper;
import io.github.gleidsonmt.dashboardfx.core.layout.conteiners.creators.CardCreator;
import io.github.gleidsonmt.dashboardfx.core.layout.conteiners.creators.ScheduleListCreator;
import io.github.gleidsonmt.dashboardfx.core.layout.conteiners.creators.ScheduleListItem;
import io.github.gleidsonmt.dashboardfx.core.layout.conteiners.layout.Direction;
import io.github.gleidsonmt.gncontrols.controls.GNAvatar;
import io.github.gleidsonmt.gncontrols.controls.GNAvatarStatus;
import io.github.gleidsonmt.gncontrols.controls.GNIconButton;
import io.github.gleidsonmt.gncontrols.material.icon.IconContainer;
import io.github.gleidsonmt.gncontrols.material.icon.Icons;
import io.github.gleidsonmt.gncontrols.options.model.Avatar;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.*;
import javafx.scene.Node;
import javafx.scene.chart.*;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.text.Text;
import javafx.scene.text.TextFlow;
import javafx.util.Callback;
import org.jetbrains.annotations.ApiStatus;

import java.net.URL;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeFormatterBuilder;
import java.time.format.FormatStyle;
import java.util.*;

/**
 * @author Gleidson Neves da Silveira | gleidisonmt@gmail.com
 * Create on  04/10/2022
 */
@ApiStatus.Internal
@SuppressWarnings("unchecked")
public final class DashController extends ResponsiveView implements ActionView, Initializable {

    public Label experimental;
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

        View scheduleList = new ScheduleListCreator("schedule-01")
                .title("Schedule")
                .items(
                        new ScheduleListItem(
                                4,
                                "Software Enginer",
                                "10000 steps",
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
                                "Mobile Analytics",
                                "10 of 23 chapters",
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

        Image image = new Image(getClass().getResource("/core.app/img/logo_flier.png").toExternalForm());
        CardCreator card = new CardCreator(
                image, "Title", """
                Lorem ipsum dolor sit amet, consectetur adipiscing elit. 
                Sed non convallis lorem. Nulla et sapien pulvinar, 
                vestibulum ex id, laoreet lectus. 
                """
        );

        CurvedChart<Number, Number> curvedChart = new CurvedChart<>(
                new NumberAxis(),
                new NumberAxis()
        );

        final XYChart.Series<Number, Number> series22 = new XYChart.Series<Number, Number>();

        series22.getData().addAll(
                new XYChart.Data<Number, Number>(0, 20D),
                new XYChart.Data<Number, Number>(1, 40D),
                new XYChart.Data<Number, Number>(2, 50D),
                new XYChart.Data<Number, Number>(3, 30D),
                new XYChart.Data<Number, Number>(4, 80D),
                new XYChart.Data<Number, Number>(5, 10D),
                new XYChart.Data<Number, Number>(6, 50D));

        curvedChart.getData().add(series22);


        footer.getChildren().addAll(createDonut(), barChart,  scheduleList.getRoot(), curvedChart);
        GridPane.setConstraints(footer.getChildren().get(0), 0, 0, 1, 1, HPos.LEFT, VPos.CENTER, Priority.SOMETIMES, Priority.SOMETIMES);
        GridPane.setConstraints(barChart, 1, 0, 1, 1, HPos.LEFT, VPos.CENTER, Priority.ALWAYS, Priority.ALWAYS);
        GridPane.setConstraints(scheduleList.getRoot(), 0, 1, 1, 1, HPos.LEFT, VPos.CENTER, Priority.ALWAYS, Priority.ALWAYS);
        GridPane.setConstraints(curvedChart, 1, 1, 1, 1, HPos.LEFT, VPos.CENTER, Priority.ALWAYS, Priority.ALWAYS);

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
            notification.setNumberOfNotifications(2);
            notification.getStyleClass().add("bd-danger");
//            notification.setColorCircle(Color.web(Colors.AQUA.toString()));
            GNBadge sms = new GNBadge(Icons.SMS);
            sms.setNumberOfNotifications(39);
            sms.getStyleClass().add("bd-info");
//            sms.setColorCircle(Color.web(Colors.GRAPEFRUIT.toString()));

            Pane space = new Pane();
            space.setMinWidth(20);

            BoxUser boxUser = new BoxUser("Jane Doe");

            VBox boxUserDialog = new VBox();
            Button btnProfile = createBtn("Profile", event -> {
               upadteContent(context, "profile");
            });
            Button btnSettings = createBtn("Settings", event -> {
//                upadteContent(context, "profile");
            });
            Button btnLogout = createBtn("Logout", event -> {
//                upadteContent(context, "profile");
            });


            boxUserDialog.getChildren().setAll(btnProfile, btnSettings, new Separator(), btnLogout);

            boxUser.setOnMouseClicked(event ->
                    context.wrapper()
                    .getPopup()
                    .size(300, 150)
                    .moveX(200)
                    .content(boxUserDialog)
                    .show(Direction.BOTTOM_LEFT, boxUser));

            context.root().bar().addInRight(sms, notification, space, boxUser);

            VBox b = createDialogNotification();

            notification.setOnMouseClicked(event -> context.wrapper()
                    .getPopup()
                    .size(400,300)
                    .content(b)
                    .background(IWrapper.WrapperBackgroundType.GRAY)
                    .show(Direction.BOTTOM_LEFT, notification));

//            b.setMinSize(200, 300);

//            context.getWrapper()
//                    .getDialog()
//                    .size(100, 40)
//                    .moveX(-100)
//                    .content(b)
//                    .contextDialog(
//                            Direction.BOTTOM_RIGHT,
//                            experimental
//                    );

            load = true;
        }
    }

    private VBox createDialogNotification() {
        VBox root = new VBox();
        root.setAlignment(Pos.TOP_CENTER);

        Text title = new Text("Notifications");
        title.getStyleClass().addAll("h5", "text-bold");
        Hyperlink btn = new Hyperlink("Mark as read");
        btn.setGraphic(new IconContainer(Icons.DONE_ALL));
        btn.setPadding(new Insets(10));
        btn.getStyleClass().addAll("text-bold","transparent", "text-info", "no-border");

        GridPane header = new GridPane();
        header.getChildren().addAll(title, btn);
        GridPane.setConstraints(title, 0,0,1,1, HPos.LEFT, VPos.CENTER, Priority.ALWAYS, Priority.ALWAYS);
        GridPane.setConstraints(btn, 1,0,1,1, HPos.RIGHT, VPos.CENTER, Priority.ALWAYS, Priority.ALWAYS);

//        ListView<NotifcationCell> listView = new ListView<>();
//        listView.setCellFactory(new NotificationListFactory());

        VBox vBox = createNotifications(
                new NotifcationCell(
                        true,
                        "Your Password has been changed succesfully.",
                        new GNIconButton(Icons.BADGE),
                        LocalDateTime.now()
                ),
                new NotifcationCell(
                        false,
                        "Thank you for booking a meeting with us.",
                        new GNAvatar(new Image(Objects.requireNonNull(getClass().getResource("/core.app/img/avatar.jpg")).toExternalForm()), 20),
                        LocalDateTime.now()
                ),
                new NotifcationCell(
                        false,
                        "Great News! We are lauching our 5th fund: DLE Senior Living.",
                        new GNAvatar(new Image(Objects.requireNonNull(getClass().getResource("/core.app/img/avatar.png")).toExternalForm()), 20),
                        LocalDateTime.now()
                )
        );


//        listView.setPrefHeight(3 * 45);
//        listView.setStyle("-fx-fixed-cell-size : 100px;");
//

        Hyperlink btnAll = new Hyperlink("View All Notifications");
        btnAll.setPadding(new Insets(10));
        btnAll.getStyleClass().addAll("text-bold", "transparent", "no-border", "text-info");

        root.getChildren().setAll(header, vBox, btnAll);
        return root;
    }

    private Button createBtn(String text, EventHandler<ActionEvent> event) {
        Button btnProfile = new Button(text);
        btnProfile.setMaxWidth(Double.MAX_VALUE);
        btnProfile.getStyleClass().addAll("btn-flat", "no-border");
        btnProfile.setAlignment(Pos.CENTER_LEFT);
        btnProfile.setPadding(new Insets(10));
        btnProfile.setOnAction(event);
        return btnProfile;
    }

    private void upadteContent(Context context, String content) {
        try {
            context.routes().setContent(content);
            context.wrapper().getDialog().close();
        } catch (NavigationException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void onExit(Context context) {

    }

    @Override
    public void onInit(Context context) {
    }

    private VBox createNotifications(NotifcationCell... cells) {
        VBox box = new VBox();
        for (NotifcationCell item : cells) {

            ToggleButton toggleButton = new ToggleButton();
            toggleButton.setMaxWidth(Double.MAX_VALUE);
            toggleButton.getStyleClass().addAll("btn-flat", "transparent");

            Text text = new Text(item.text());
            TextFlow textFlow = new TextFlow(text);
            text.getStyleClass().addAll("text-12", "text-bold");
            String pattern = "dd MMM yyyy HH:mm:ss";
            Text time = new Text(item.time().format(DateTimeFormatter.ofPattern(pattern, Locale.US)));
            GridPane grid = new GridPane();
            Node icon = item.icon();
            icon.setStyle("-fx-fill : white; -fx-text-fill: white; -text-color : white;");
            Circle circle = new Circle();
            circle.setRadius(5);
            if (item.read()) {
                circle.setStyle("-fx-fill : -info;");
            } else {
                circle.setStyle("-fx-fill : white;");
            }
            grid.getChildren().setAll(circle, textFlow, time, icon);
//            grid.setGridLinesVisible(true);
            GridPane.setConstraints(circle, 0,0,1,2, HPos.LEFT, VPos.CENTER, Priority.ALWAYS, Priority.ALWAYS);
            GridPane.setConstraints(textFlow, 1,0,1,1, HPos.LEFT, VPos.CENTER, Priority.ALWAYS, Priority.ALWAYS);
            GridPane.setConstraints(time, 1,1,1,1, HPos.LEFT, VPos.TOP, Priority.ALWAYS, Priority.ALWAYS);
            GridPane.setConstraints(icon, 2,0,1,2, HPos.RIGHT, VPos.CENTER, Priority.ALWAYS, Priority.ALWAYS);
            grid.setHgap(10);
            toggleButton.setGraphic(grid);
            box.getChildren().addAll(toggleButton, new Separator());
        }

        return box;
    }
}