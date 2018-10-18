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
package com.gn.module.main;

import com.gn.ViewManager;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXHamburger;
import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.geometry.NodeOrientation;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.util.Duration;
import org.controlsfx.control.PopOver;

import java.net.URL;
import java.util.ResourceBundle;

/**
 * @author Gleidson Neves da Silveira | gleidisonmt@gmail.com
 * Create on  08/10/2018
 * Version 1.0
 */
public class Main implements Initializable {

    @FXML private ImageView avatar;
    @FXML public VBox sideBar;
    @FXML private HBox searchBox;
    @FXML private HBox boxStatus;
    @FXML private VBox info;
    @FXML private VBox views;
    @FXML private Circle cStatus;
    @FXML private HBox status;
    @FXML private ScrollPane body;
    @FXML private Label title;
    @FXML private TextField search;
    @FXML private ScrollPane scrollBar;
    @FXML private TitledPane design;
    @FXML private TitledPane controls;
    @FXML private TitledPane menu;
    @FXML private TitledPane shapes;
    @FXML private TitledPane charts;
    @FXML private JFXButton  home;
    @FXML private JFXButton  about;
    @FXML private JFXHamburger hamburger;

    private FilteredList<JFXButton> filteredList = null;

    private final PopOver pop = new PopOver();

    private ObservableList<JFXButton> items      = FXCollections.observableArrayList();
    private ObservableList<JFXButton> designItems = FXCollections.observableArrayList();
    private ObservableList<JFXButton> controlsItems = FXCollections.observableArrayList();
    private ObservableList<JFXButton> menuItems = FXCollections.observableArrayList();
    private ObservableList<JFXButton> shapesItems = FXCollections.observableArrayList();
    private ObservableList<JFXButton> chartsItems = FXCollections.observableArrayList();

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        Circle circle = new Circle(45);
        circle.setStroke(Color.WHITE);
        circle.setCenterX(avatar.getFitWidth() / 2);
        circle.setCenterY(avatar.getFitHeight() / 2);
        avatar.setClip(circle);

        configPop();
        populateItems();
        filteredList = new FilteredList<>(items, s -> true);

        search.textProperty().addListener(obs -> {
            String filter = search.getText();
            if (filter == null || filter.length() == 0) {
                barInitial();
            } else {
                barFiltered(filter);
            }
        });

        body.setContent(ViewManager.getInstance().get("colorpicker"));
    }

    private void configPop() {
        pop.setCornerRadius(0);
        pop.setArrowIndent(10);
        pop.setArrowSize(0);
        pop.getRoot().getStylesheets().add(getClass().getResource("/com/gn/theme/css/popover.css").toExternalForm());
    }

    @FXML
    private void altLayout() {
        Timeline open = new Timeline();

        double init = 70;
        double end = 250;
        boolean scrolling = false;

        if (sideBar.getPrefWidth() == end) {
            open.getKeyFrames().addAll(
                    new KeyFrame(Duration.ZERO, new KeyValue(sideBar.prefWidthProperty(), end)),
                    new KeyFrame(Duration.millis(300), new KeyValue(sideBar.prefWidthProperty(), init))
            );

            sideBar.getChildren().remove(searchBox);
            info.getChildren().remove(boxStatus);
            avatar.setFitHeight(60);
            avatar.setFitWidth(60);

            Circle circle = new Circle(20);
            circle.setStroke(Color.WHITE);
            circle.setCenterX(avatar.getFitWidth() / 2);
            circle.setCenterY(avatar.getFitHeight() / 2);
            avatar.setClip(circle);

            info.setAlignment(Pos.TOP_CENTER);
            info.getChildren().add(cStatus);
            cStatus.setRadius(8);
            scrolling = true;

            for (Node node : views.getChildren()) {

                if (node instanceof TitledPane) {

                    TitledPane pane = (TitledPane) node;

                    pane.expandedProperty().set(false);

                    pane.setContentDisplay(ContentDisplay.GRAPHIC_ONLY);
                    pane.setAlignment(Pos.CENTER);
                    pane.setCollapsible(false);

                    if (pane.getGraphic() instanceof ImageView) {
                        ((ImageView) pane.getGraphic()).setFitHeight(25);
                        ((ImageView) pane.getGraphic()).setFitWidth(25);
                    }
                    addEvent(node);
                }

                if (node instanceof JFXButton) {
                    JFXButton button = (JFXButton) node;
                    button.setContentDisplay(ContentDisplay.GRAPHIC_ONLY);
                    button.setAlignment(Pos.CENTER);
                    button.setPadding(new Insets(0, 0, 0, 6));

                    button.setOnMouseEntered(e -> {
                        if (pop.isShowing())
                            pop.hide();
                    });
                }
            }
        } else {
            open.getKeyFrames().addAll(
                    new KeyFrame(Duration.ZERO, new KeyValue(sideBar.prefWidthProperty(), init)),
                    new KeyFrame(Duration.millis(300), new KeyValue(sideBar.prefWidthProperty(), end))
            );

            avatar.setFitHeight(117);
            avatar.setFitWidth(103);
            Circle circle = new Circle(40);
            circle.setStroke(Color.WHITE);
            circle.setCenterX(avatar.getFitWidth() / 2);
            circle.setCenterY(avatar.getFitHeight() / 2);
            avatar.setClip(circle);

            info.setAlignment(Pos.CENTER);
            info.getChildren().add(boxStatus);

            sideBar.getChildren().add(searchBox);
            searchBox.toBack();
            info.toBack();
            info.getChildren().remove(cStatus);
            cStatus.setRadius(4);
            status.getChildren().add(cStatus);
            cStatus.toBack();

            for (Node node : views.getChildren()) {

                if (node instanceof TitledPane) {

                    TitledPane pane = (TitledPane) node;

                    pane.setContentDisplay(ContentDisplay.RIGHT);
                    pane.setAlignment(Pos.CENTER_RIGHT);
                    pane.setCollapsible(true);

                    pane.expandedProperty().set(false);

                    pane.setOnMouseEntered(null);
                    pane.setOnMouseExited(null);

                    if (pane.getGraphic() instanceof ImageView) {
                        ((ImageView) pane.getGraphic()).setFitHeight(20);
                        ((ImageView) pane.getGraphic()).setFitWidth(20);
                    }
                }
                if (node instanceof JFXButton) {
                    JFXButton button = (JFXButton) node;
                    button.setContentDisplay(ContentDisplay.RIGHT);
                    button.setAlignment(Pos.CENTER_RIGHT);
                    button.setPadding(new Insets(0, 8, 0, 0));

                }
            }
        }

        boolean finalScrolling = scrolling;

        open.setOnFinished(event -> {
            if (finalScrolling) {
                scrollBar.setVbarPolicy(ScrollPane.ScrollBarPolicy.AS_NEEDED);
                scrollBar.setHbarPolicy(ScrollPane.ScrollBarPolicy.AS_NEEDED);
//                    sideBar.setMinWidth(end);
            } else {
                scrollBar.setVbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
                scrollBar.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
//                    sideBar.setMinWidth(init);

            }
        });

        open.playFrom(Duration.millis(100));
    }

    private void addEvent(Node node) {

        ScrollPane scrollPane = new ScrollPane();
        VBox v = new VBox();
        v.setPrefWidth(200);

        TitledPane pane = (TitledPane) node;
        VBox vbox = (VBox) pane.getContent();

        for (Node btn : vbox.getChildren()) {
            EventHandler event = ((JFXButton) btn).getOnMouseClicked();
            String title = ((JFXButton) btn).getText();
            JFXButton button = new JFXButton(title);
            button.getStyleClass().addAll("btn-transparent", "text-gray");
            button.setOnMouseReleased(e -> pop.hide());
            button.setFocusTraversable(false);
            button.setPrefWidth(v.getPrefWidth());
            button.setOnMouseClicked(event);
            button.setMinHeight(40);
            v.getChildren().add(button);
        }

        scrollPane.setContent(v);
        scrollPane.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);

        node.setOnMouseEntered((Event e) -> {
            pop.getRoot().setMaxHeight(600);
            pop.setContentNode(scrollPane);
            pop.show(node, 0);
            scrollPane.setFocusTraversable(true);

        });
    }

    private void barInitial(){
        filteredList.setPredicate(s -> true);
        scrollBar.setContent(views);
        ( (VBox) design.getContent()).getChildren().setAll(designItems);
        ( (VBox) controls.getContent()).getChildren().setAll(controlsItems);
        ( (VBox) menu.getContent()).getChildren().setAll(menuItems);
        ( (VBox) shapes.getContent()).getChildren().setAll(shapesItems);
        ( (VBox) charts.getContent()).getChildren().setAll(chartsItems);

        views.getChildren().removeAll(home, about);
        views.getChildren().add(home);
        views.getChildren().add(about);
        home.setContentDisplay(ContentDisplay.RIGHT);
        about.setContentDisplay(ContentDisplay.RIGHT);
        home.toBack();
        about.toFront();
        hamburger.setMouseTransparent(false);
    }

    private void barFiltered(String filter){
        views.getChildren().removeAll(home, about);
        filteredList.setPredicate(s -> s.getText().toUpperCase().contains(filter.toUpperCase()));
        scrollBar.setContent(filter(filteredList));
        hamburger.setMouseTransparent(true);
    }

    private VBox filter(ObservableList<JFXButton>  nodes){
        VBox vBox = new VBox();
        vBox.setNodeOrientation(NodeOrientation.RIGHT_TO_LEFT);
        vBox.setAlignment(Pos.TOP_RIGHT);
        VBox.setVgrow(vBox, Priority.ALWAYS);
        for (JFXButton node : nodes){
            if (node.getGraphic() != null) node.setContentDisplay(ContentDisplay.TEXT_ONLY);
            node.setAlignment(Pos.CENTER_RIGHT);
        }
        vBox.getChildren().setAll(nodes);
        return vBox;
    }

    private void populateItems() {

        for (Node node : views.getChildren()) {
            if (node instanceof JFXButton) {
                items.add((JFXButton) node);
            }
        }

        for (Node node : ((VBox) controls.getContent()).getChildren()) {
            controlsItems.add((JFXButton) node);
            items.add((JFXButton) node);
        }

        for (Node node : ((VBox) design.getContent()).getChildren()) {
            designItems.add((JFXButton) node);
            items.add((JFXButton) node);
        }

        for (Node node : ((VBox) shapes.getContent()).getChildren()) {
            shapesItems.add((JFXButton) node);
        }

        for (Node node : ((VBox) menu.getContent()).getChildren()) {
            menuItems.add((JFXButton) node);
            items.add((JFXButton) node);
        }

        for (Node node : ((VBox) charts.getContent()).getChildren()) {
            chartsItems.add((JFXButton) node);
            items.add((JFXButton) node);
        }
    }

    @FXML
    private void colors() {
        title.setText("Designer");
        body.setContent(ViewManager.getInstance().get("colors"));
    }

    @FXML
    private void buttons() {
        body.setContent(ViewManager.getInstance().get("button"));
        title.setText("Button");
    }

    @FXML
    private void toggle() {
        title.setText("Toggle Button");
        body.setContent(ViewManager.getInstance().get("toggle"));
    }

    @FXML
    private void cards(){
        title.setText("Cards");
        body.setContent(ViewManager.getInstance().get("cards"));
    }

    @FXML
    private void textField(){
        title.setText("TextField");
        body.setContent(ViewManager.getInstance().get("textfield"));
    }

    @FXML
    private void datePicker(){
        title.setText("DatePicker");
        body.setContent(ViewManager.getInstance().get("datepicker"));
    }

    @FXML
    private void checkBox(){
        title.setText("CheckBox");
        body.setContent(ViewManager.getInstance().get("checkbox"));
    }

    @FXML
    private void comboBox(){
        title.setText("ComboBox");
        body.setContent(ViewManager.getInstance().get("combobox"));
    }

    @FXML
    private void colorPicker(){
        title.setText("ComboBox");
        body.setContent(ViewManager.getInstance().get("colorpicker"));
    }


    @FXML
    private void choiceBox(){
        title.setText("ChoiceBox");
        body.setContent(ViewManager.getInstance().get("choicebox"));
    }

    @FXML
    private void splitMenuButton(){
        title.setText("SplitMenuButton");
        body.setContent(ViewManager.getInstance().get("splitmenubutton"));
    }

    @FXML
    private void menuButton(){
        title.setText("MenuButton");
        body.setContent(ViewManager.getInstance().get("menubutton"));
    }

    @FXML
    private void menuBar(){
        title.setText("MenuBar");
        body.setContent(ViewManager.getInstance().get("menubar"));
    }

    @FXML
    private void slider(){
        title.setText("Slider");
        body.setContent(ViewManager.getInstance().get("slider"));
    }

    @FXML
    private void html(){
        title.setText("HTMLEditor");
        body.setContent(ViewManager.getInstance().get("htmleditor"));
    }

    @FXML
    private void radio(){
        title.setText("RadioButton");
        body.setContent(ViewManager.getInstance().get("radiobutton"));
    }
}
