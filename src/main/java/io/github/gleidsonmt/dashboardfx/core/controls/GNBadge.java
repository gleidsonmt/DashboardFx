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

package io.github.gleidsonmt.dashboardfx.core.controls;

import io.github.gleidsonmt.gncontrols.controls.GNIconButton;
import io.github.gleidsonmt.gncontrols.material.icon.Icons;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.layout.StackPane;
import javafx.scene.text.Text;
import org.jetbrains.annotations.ApiStatus;

public class GNBadge extends StackPane {

    public GNBadge(Icons _icon) {

        GNIconButton icon = new GNIconButton(_icon);
        icon.getStyleClass().addAll("btn-flat");
        Label lblInfo = new Label();
        lblInfo.setAlignment(Pos.CENTER);
        lblInfo.setText("4");
        lblInfo.setMinSize(20,20);
        lblInfo.setPrefSize(10,10);
        lblInfo.setMaxSize(10,10);
        lblInfo.setStyle("-fx-background-color : -grapefruit; -fx-background-radius : 100px; -fx-text-fill: white;");
        this.getChildren().setAll(icon, lblInfo);
        this.setAlignment(Pos.TOP_RIGHT);
    }
}
