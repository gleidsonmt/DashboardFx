/*
 *
 *    Copyright (C) Gleidson Neves da Silveira
 *
 *    This program is free software: you can redistribute it and/or modify
 *    it under the terms of the GNU General Public License as published by
 *    the Free Software Foundation, either version 3 of the License, or
 *   (at your option) any later version.
 *
 *    This program is distributed in the hope that it will be useful,
 *    but WITHOUT ANY WARRANTY; without even the implied warranty of
 *    MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *    GNU General Public License for more details.
 *
 *     You should have received a copy of the GNU General Public License
 *     along with this program.  If not, see <http://www.gnu.org/licenses/>.
 *
 */

package io.github.gleidsonmt.dashboardfx.controllers;

import io.github.gleidsonmt.dashboardfx.core.Context;
import io.github.gleidsonmt.dashboardfx.core.controls.GNIconButton;
import io.github.gleidsonmt.dashboardfx.core.controls.icon.Icons;
import io.github.gleidsonmt.dashboardfx.core.interfaces.ActionView;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.geometry.VPos;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;

/**
 * @author Gleidson Neves da Silveira | gleidisonmt@gmail.com
 * Create on  01/07/2023
 */
public class HeroCodeController extends ActionView {

    @FXML private StackPane root;
    @FXML private HBox bar;
    @FXML private Hyperlink loginButton;
    @FXML private GridPane grid;

    private final GNIconButton hamburger = new GNIconButton(Icons.HAMBURGER);
    private final GNIconButton close = new GNIconButton(Icons.CLEAR);

    @Override
    public void onInit(Context context) {
        super.onInit(context);

        hamburger.getStyleClass().addAll("hamburger", "btn-flat");
        close.getStyleClass().addAll("hamburger", "btn-flat");

        root.widthProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue.doubleValue() < 600) {
                grid.getChildren().remove(loginButton);
                if(!grid.getChildren().contains(hamburger)) {
                    grid.getChildren().add(hamburger);
                }
                grid.getChildren().remove(bar);
                GridPane.setConstraints(hamburger, 2, 0, 1, 1, HPos.RIGHT, VPos.CENTER);
            } else if (!grid.getChildren().contains(loginButton)){
                if(!grid.getChildren().contains(bar)) {
                    grid.getChildren().add(loginButton);
                    grid.getChildren().add(bar);
                }
                grid.getChildren().remove(hamburger);
                GridPane.setConstraints(loginButton, 2, 0, 1, 1, HPos.RIGHT, VPos.CENTER);
                GridPane.setConstraints(bar, 1, 0, 1, 1, HPos.CENTER, VPos.CENTER);
            }
        });


        hamburger.setOnMouseClicked(event -> {

            VBox box = new VBox();
            box.getStyleClass().addAll("container");
            box.setStyle("-fx-border-width: 0px 0px 0px 1px; -fx-border-color: -light-gray-2;");
            HBox top = new HBox();
            top.setAlignment(Pos.CENTER_RIGHT);
            top.getChildren().addAll(close);
            top.setPadding(new Insets(15, 20 , 10, 20));
            top.setMinHeight(75);
            box.setMaxWidth(250);
            box.getChildren().addAll(top);

            root.getChildren().add(box);

            StackPane.setAlignment(box, Pos.CENTER_RIGHT);

            ListView<Label> list = new ListView<>();
            list.setStyle("-fx-padding: 10px;");

            bar.getChildren()
                    .stream()

                    .map(m -> (Button) m)
                    .forEach(c -> {
                        list.getItems().addAll(new Label(c.getText()));
                    });

            box.getChildren().add(list);
            list.getItems().add(new Label("Login"));

        });

        close.setOnMouseClicked(event -> {
            root.getChildren().remove(1);
        });
    }
}
