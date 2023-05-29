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

package io.github.gleidsonmt.dashboardfx.core.services;

import io.github.gleidsonmt.dashboardfx.core.Context;
import io.github.gleidsonmt.dashboardfx.core.model.SearchItemAdapter;
import javafx.scene.Node;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TitledPane;
import javafx.scene.control.ToggleButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;

/**
 * @author Gleidson Neves da Silveira | gleidisonmt@gmail.com
 * Version 0.0.1
 * Create on  24/04/2023
 */
public class DrawerBehavior {

    public DrawerBehavior(StackPane content, ToggleGroup group, Context context) {
        VBox body = (VBox) content.lookup("#drawer-content");
        ScrollPane scrollPane = (ScrollPane) body.lookup("#drawer-scroll");
        VBox box = (VBox) scrollPane.getContent();


        box.getChildren()
                .stream()
                .filter(fil ->
                        fil instanceof ToggleButton && fil.getStyleClass().contains("drawer-item"))
                .map(item -> (ToggleButton) item)
                .forEach(each -> {
                    each.addEventFilter(MouseEvent.MOUSE_PRESSED, event -> {
                        if (each.isSelected()) event.consume();
                    });
                    context.searchItems().add(SearchItemAdapter.adapter(each));
                });

        box.getChildren()
                .stream()
                .filter(fill -> fill instanceof TitledPane
                        && ((TitledPane) fill).getContent() instanceof VBox
                )
                .map(item -> (VBox) ((TitledPane) item).getContent())
                .forEach(each -> {
                    for (Node fill : each.getChildren()) {
                        if (fill instanceof ToggleButton e) {
                            e.setToggleGroup(group);
                            e.addEventFilter(MouseEvent.MOUSE_PRESSED, event -> {
                                if (e.isSelected()) {
                                    event.consume();
                                }
                            });
                            e.selectedProperty().addListener((observable, oldValue, newValue) -> {
                                if (!newValue) {
                                    e.getParent().getParent().getParent().setId(null);
                                } else {
                                    e.getParent().getParent().getParent().setId("drawer-menu-selected");
                                }
                            });

                            context.searchItems().add(SearchItemAdapter.adapter(e));
                        }
                    }
                });

    }
}
