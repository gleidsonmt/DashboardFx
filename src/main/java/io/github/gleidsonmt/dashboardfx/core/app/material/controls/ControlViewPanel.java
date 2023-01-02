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

import io.github.gleidsonmt.dashboardfx.core.app.services.Context;
import io.github.gleidsonmt.dashboardfx.views.BlockCode;
import io.github.gleidsonmt.gncontrols.controls.GNTextBox;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Control;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Region;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;

public class ControlViewPanel extends BorderPane {

    private RightSide right;
    private LeftSide left;
    private VBox center;

    public ControlViewPanel(Context context) {
        GNTextBox textBox = new GNTextBox();
//        textBox.setPrefHeight();
        textBox.setMaxHeight(Region.USE_PREF_SIZE);
        textBox.setMaxWidth(Region.USE_PREF_SIZE);

        Label label = new Label("Label");
        label.setPrefSize(100, 40);
        label.setUserData(new ControlData("lbl"));


        center = new CenterView(context, label, """
                 Label label = new Label("Label");
                """);

        right = new RightSide();
        left = new LeftSide(label);

        this.setCenter(center);
        this.setLeft(left);
        this.setRight(right);
    }
}
