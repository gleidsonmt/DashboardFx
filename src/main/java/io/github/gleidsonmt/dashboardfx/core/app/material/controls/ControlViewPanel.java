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
import javafx.collections.ListChangeListener;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Control;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Region;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;

import java.util.Objects;

public class ControlViewPanel extends BorderPane {

    private RightSide right;
    private LeftSide left;
    private VBox center;

    private Node node;
    private Context context;

    private BlockCopyControl blockCopyControl;

    public ControlViewPanel(Context context, Node node) {
        this.context = context;

        blockCopyControl = new BlockCopyControl(context, node);

        center = new CenterView(
                context,
                node,
                blockCopyControl.getJavaBlock(),
                blockCopyControl.getFxmlBlock());

        right = new RightSide(context);
        left = new LeftSide(context, node);

        this.setCenter(center);
        this.setLeft(left);
        this.setRight(right);
    }

    public void setNode(Node _node) {
        this.node = _node;
        blockCopyControl = new BlockCopyControl(context, node);
        left = new LeftSide(context, node);
        center = new CenterView(
                context,
                node,
                blockCopyControl.getJavaBlock(),
                blockCopyControl.getFxmlBlock());

        setCenter(center);
        setLeft(left);
    }
}
