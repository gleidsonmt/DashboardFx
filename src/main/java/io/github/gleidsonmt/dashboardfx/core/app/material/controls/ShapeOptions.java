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

package io.github.gleidsonmt.dashboardfx.core.app.material.controls;

import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.VPos;
import javafx.scene.Node;
import javafx.scene.control.ContentDisplay;
import javafx.scene.control.Label;
import javafx.scene.control.ToggleButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Priority;

public class ShapeOptions extends GridPane {

    public ShapeOptions(Node node, String... options) {
        ToggleGroup group = new ToggleGroup();
        this.setVgap(20);
        this.setHgap(30);
        this.setPadding(new Insets(10, 10, 0, 10));

        for (int i = 0; i < options.length; i++) {

            ToggleButton toggleButton = new ToggleButton();
            toggleButton.setContentDisplay(ContentDisplay.GRAPHIC_ONLY);
            toggleButton.getStyleClass().add(options[i].toLowerCase());
            toggleButton.setMaxSize(100, 100);

            group.getToggles().add(toggleButton);
            toggleButton.setUserData(options[i]);

            toggleButton.getStyleClass().addAll("info");
            this.getChildren().add(toggleButton);
            GridPane.setConstraints(toggleButton, i, 0, 1, 1, HPos.LEFT, VPos.CENTER, Priority.ALWAYS, Priority.ALWAYS);
            toggleButton.setMaxWidth(Double.MAX_VALUE);

            int finalI = i;
            toggleButton.selectedProperty().addListener((observable, oldValue, newValue) -> {
                if (node.getUserData() instanceof ControlData controlData) {
                    if (newValue) {
                        node.getStyleClass().add(options[finalI].toLowerCase());
                    } else
                        node.getStyleClass().remove(options[finalI].toLowerCase());
                }
            });
        }

    }

}
