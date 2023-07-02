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

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.geometry.NodeOrientation;
import javafx.scene.Node;
import javafx.scene.control.ContentDisplay;
import javafx.scene.control.TitledPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.Region;

/**
 * @author Gleidson Neves da Silveira | gleidisonmt@gmail.com
 * Create on  01/07/2023
 */
public class GNTitledPane extends TitledPane {
    public GNTitledPane() {
        this("Untitled", null);
    }

    public GNTitledPane(String text, Node node) {
        super(text, node);
        this.getStyleClass().addAll("gn-titled-pane");

        setOrientation(node);
        this.contentProperty().addListener((observable, oldValue, newValue) -> {
            setOrientation(newValue);
        });
        this.setNodeOrientation(NodeOrientation.RIGHT_TO_LEFT);
        this.setContentDisplay(ContentDisplay.RIGHT);
    }

    private void setOrientation(Node node) {
        if (node != null) {
            if (node instanceof Pane r) {
                r.setNodeOrientation(NodeOrientation.LEFT_TO_RIGHT);
            }
        }
    }
}
