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
package com.gn;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXPopup;
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIconView;
import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.ContentDisplay;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TitledPane;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.SVGPath;
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

    @FXML
    private ImageView avatar;
    @FXML
    public VBox sideBar;
    @FXML
    private HBox searchBox;
    @FXML
    private HBox boxStatus;
    @FXML
    private VBox info;
    @FXML
    private VBox views;
    @FXML
    private Circle cStatus;
    @FXML
    private HBox status;
    @FXML
    private ScrollPane body;
    @FXML
    private Label title;


    private final PopOver pop = new PopOver();

    private ObservableList<JFXButton> controls = FXCollections.observableArrayList();

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        Circle circle = new Circle(45);
        circle.setStroke(Color.WHITE);
        circle.setCenterX(avatar.getFitWidth() / 2);
        circle.setCenterY(avatar.getFitHeight() / 2);
        avatar.setClip(circle);

        configPop();

        body.setContent(ViewManager.getInstance().get("button"));
    }

    private void configPop() {
        pop.setCornerRadius(0);
        pop.setArrowIndent(10);
        pop.setArrowSize(0);
        pop.getRoot().getStylesheets().add(getClass().getResource("/com/gn/popover.css").toExternalForm());
    }

    @FXML
    private void altLayout() {
        Timeline open = new Timeline();

        double init = 70;
        double end = 250;
        boolean scrolling;

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

    @FXML
    private void buttons() {
        body.setContent(ViewManager.getInstance().get("button"));
    }

    @FXML
    private void designer() {
        body.setContent(ViewManager.getInstance().get("designer"));
    }

}
