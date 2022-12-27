/*
 *    Copyright (C) Gleidson Neves da Silveira
 *
 *    This program is free software: you can redistribute it and/or modify
 *    it under the terms of the GNU General Public License as published by
 *    the Free Software Foundation, either version 3 of the License, or
 *    (at your option) any later version.
 *
 *    This program is distributed in the hope that it will be useful,
 *    but WITHOUT ANY WARRANTY; without even the implied warranty of
 *    MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *    GNU General Public License for more details.
 *
 *    You should have received a copy of the GNU General Public License
 *    along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */

package io.github.gleidsonmt.dashboardfx.core.layout;

import javafx.geometry.HPos;
import javafx.geometry.Pos;
import javafx.geometry.VPos;
import javafx.scene.Node;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import org.jetbrains.annotations.ApiStatus;

import java.util.List;

/**
 * @author Gleidson Neves da Silveira | gleidisonmt@gmail.com
 * Create on  08/10/2022
 */

@ApiStatus.Internal
@ApiStatus.AvailableSince("0.1")
@SuppressWarnings("unused")
public class Bar extends GridPane {
    private final HBox left = new HBox();
    private final HBox right = new HBox();

    public Bar() {
        left.setId("bar-left");
        right.setId("bar-right");
        left.setSpacing(2);
        right.setSpacing(2);
        left.setAlignment(Pos.CENTER_LEFT);
        right.setAlignment(Pos.CENTER_RIGHT);
        this.getChildren().setAll(left, right);
        GridPane.setConstraints(left, 0,0,1,1, HPos.LEFT, VPos.CENTER, Priority.ALWAYS, Priority.ALWAYS);
        GridPane.setConstraints(right, 1,0,1,1, HPos.RIGHT, VPos.CENTER, Priority.ALWAYS, Priority.ALWAYS);
    }

    public void addInLeft(Node node) {
        addInLeft(0, node);
    }

    public void addInRight(Node node) {
        addInRight(0, node);
    }

    public void addInLeft(Node... nodes) {
        addInLeft(0, nodes);
    }

    public void addInRight(Node... nodes) {
        addInRight(0, nodes);
    }

    public void addInLeft(int index, Node node) {
        this.left.getChildren().add(index,node);
    }

    public void addInRight(int index, Node node) {
        this.right.getChildren().add(index, node);
    }

    public void addInLeft(int start, Node... nodes) {
        this.left.getChildren().addAll(start, List.of(nodes));
    }

    public void addInRight(int start, Node... nodes) {
        this.right.getChildren().addAll(start, List.of(nodes));
    }

}
