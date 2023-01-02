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

import io.github.gleidsonmt.gncontrols.material.icon.IconContainer;
import io.github.gleidsonmt.gncontrols.material.icon.Icons;
import javafx.geometry.HPos;
import javafx.geometry.VPos;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Priority;

public class IconOptions extends GridPane {

    public IconOptions(Node node, Icons... options) {

        ToggleGroup group = new ToggleGroup();

        for (int i = 0; i < options.length; i++) {

            System.out.println(options[i]);
            ToggleButton toggleButton = new ToggleButton();
            toggleButton.setContentDisplay(ContentDisplay.GRAPHIC_ONLY);
            toggleButton.setGraphic(new IconContainer(options[i]));

            group.getToggles().add(toggleButton);
            toggleButton.setUserData(options[i]);

            toggleButton.getStyleClass().addAll("toggle-box");
            this.getChildren().add(toggleButton);
            GridPane.setConstraints(toggleButton, i,0,1,1, HPos.LEFT, VPos.CENTER, Priority.ALWAYS, Priority.ALWAYS);
            toggleButton.setMaxWidth(Double.MAX_VALUE);
        }

            group.selectedToggleProperty().addListener((olbservale, oldValue, newValue) -> {
                if (newValue == null) return;
                if (node instanceof Label control) {
                    control.setGraphic(new IconContainer(
                            (Icons) newValue.getUserData()
                    ));
                }
            });

//            group.selectToggle(toggleButton);

    }

}
