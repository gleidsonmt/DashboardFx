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
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Priority;

import java.util.List;

public class BubbleBox extends GridPane {

    private ToggleGroup group = new ToggleGroup();
    private List<String> colors = List.of(
      "info", "success", "danger", "warning", "cyan", "grapefruit"
    );

    public BubbleBox(Node control) {

        this.setVgap(10);
        this.setHgap(10);
        this.setPadding(new Insets(5));
        int rows = 0;
        int cols = 0;
        int count = 0;

        while (count < colors.size()) {

            BubbleItem item = createBubbleItem(colors.get(count));
            this.getChildren().add(item);

            int finalCount = count;

            item.selectedProperty().addListener((observable, oldValue, newValue) -> {
                if (control.getUserData() != null) {
                    if (control.getUserData() instanceof ControlData controlData) {
                        if (newValue) {
                            if (!control.getStyleClass().contains(controlData.prefix() + "-outlined")
                                    || !control.getStyleClass().contains("outlined")) {
                                control.getStyleClass().addAll(controlData.prefix()
                                        + "-" + item.getColor());
//                                control.setStyle("-fx-border-color : -" + colors.get(finalCount));
                            } else {
                                control.getStyleClass().removeAll(controlData.prefix()
                                        + "-" + item.getColor());
//                                control.setStyle("-fx-background-color : -" + colors.get(finalCount));
                            }
                    } else {
                        control.getStyleClass().removeAll(controlData.prefix()
                                + "-" + item.getColor());
                    }
                }


//                    control.setStyle("-box-color : -" + colors.get(finalCount));
                }
            });

            if (cols > 4) {
                cols = 0;
                rows++;
            }

            group.getToggles().add(item);
            GridPane.setConstraints(item, cols, rows, 1,1, HPos.CENTER, VPos.CENTER, Priority.ALWAYS, Priority.ALWAYS);

            cols++;
            count++;

        }

    }

    private BubbleItem createBubbleItem(String color) {
        return new BubbleItem(color);
    }
}
