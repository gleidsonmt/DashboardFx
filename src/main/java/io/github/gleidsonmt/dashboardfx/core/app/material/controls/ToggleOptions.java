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
import javafx.geometry.VPos;
import javafx.scene.Node;
import javafx.scene.control.ToggleButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Priority;

public class ToggleOptions extends GridPane {

    public ToggleOptions(Node node, String... options) {

        ToggleGroup group = new ToggleGroup();

        for (int i = 0; i < options.length; i++) {

            ToggleButton toggle = new ToggleButton(options[i]);

            if (i == 0 && options.length > 1) {
                toggle.getStyleClass().addAll("toggle-left");
            } else if ( (i + 1) == options.length) {
                toggle.getStyleClass().addAll("toggle-right");
            }

            toggle.getUserData();
            group.getToggles().add(toggle);
            this.getChildren().add(toggle);
            GridPane.setConstraints(toggle, i,0,1,1, HPos.LEFT, VPos.CENTER, Priority.ALWAYS, Priority.ALWAYS);
            toggle.setMaxWidth(Double.MAX_VALUE);

            int finalI = i;
            toggle.selectedProperty().addListener((observable, oldValue, newValue) -> {
                if (node.getUserData() instanceof ControlData controlData) {
                    if (newValue) {
                        node.getStyleClass().add(controlData.prefix()
                                + "-" + options[finalI].toLowerCase());
                    } else
                        node.getStyleClass().remove(controlData.prefix()
                                + "-" + options[finalI].toLowerCase());
                }
            });

        }
    }

}
