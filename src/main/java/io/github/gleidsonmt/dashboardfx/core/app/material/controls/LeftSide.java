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

import javafx.geometry.Insets;
import javafx.scene.control.Label;
import javafx.scene.control.Separator;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;

public class LeftSide extends VBox {

    public LeftSide() {
        setPadding( new Insets(20));
        setPrefWidth(250);

        getStyleClass().addAll("border-r-1", "border-box");
        this.setSpacing(10);

        Label title = new Label("Designer Panel");
        title.getStyleClass().addAll("title");
        Separator separator = new Separator();
        getChildren().setAll(title, separator, new ColorBox(), new FillBox(
                "Fill", new StackPane(new Label("Welcome"))
        ));



    }

    private void sum(int[][] accounts) {
        for(int[] costumer : accounts) {
            for (int bank : costumer) {
                
            }
        }
    }
}
