/*
 * Copyright (C) Gleidson Neves da Silveira
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package com.gn.module.main;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;

/**
 * @author Gleidson Neves da Silveira | gleidisonmt@gmail.com
 * Create on  29/03/2020
 */
public class DrawerController {


    //    private VBox drawer;
    private ScrollPane  scroll;
    private VBox        drawerBox;
    private VBox        drawerContent;
    private ToggleGroup group;

    private ObservableList<ToggleButton> items         = FXCollections.observableArrayList();
    private ObservableList<ToggleButton> designItems   = FXCollections.observableArrayList();
    private ObservableList<ToggleButton> controlsItems = FXCollections.observableArrayList();
    private ObservableList<ToggleButton> chartsItems   = FXCollections.observableArrayList();

    public DrawerController(VBox drawer) {

        this.group = new ToggleGroup();
//        this.drawer = drawer;
        this.drawerBox = (VBox) drawer.lookup("#drawer-box");
        this.scroll = (ScrollPane) drawerBox.lookup("#drawer-scroll");
        this.drawerContent = (VBox) scroll.getContent();



        // This function can be included in one custom control for drawer..
        // The stream is really util function..

        drawerContent.getChildren().stream()
                .filter(e -> e instanceof ToggleButton)
                .map(e -> (ToggleButton) e)
                .forEach(e -> {
                    e.setToggleGroup(group);
                    e.addEventFilter(MouseEvent.MOUSE_PRESSED, event -> {
                        if (e.isSelected()) {
                            event.consume();
                        }
                    });
                });

        drawerContent.getChildren()
                .stream()
                .filter(e -> e instanceof TitledPane)
                .map(e -> (TitledPane) e)
                .forEach(e -> {
                    if (e.getContent() instanceof VBox) {
                        ((VBox) e.getContent()).getChildren()
                                .stream()
                                .filter(c -> c instanceof ToggleButton)
                                .map(c -> (ToggleButton) c)
                                .forEach(c -> {
                                    c.setToggleGroup(group);
                                    this.group.selectedToggleProperty().addListener((observable, oldValue, newValue) -> {
                                        if(newValue != null) {
                                            if (((VBox) e.getContent()).getChildren().contains(newValue)) {
                                                e.getStyleClass().addAll("menu-selected");
                                            } else e.getStyleClass().removeAll("menu-selected");
                                        }
                                    });
                                });
                    }
                });

        configActions();
    }

    public ObservableList<ToggleButton> getItems() {
        return items;
    }

    public void configActions() {
        items = this.getItems();
    }

    private void populateItems() {

//        for (Node node : this.drawerContent.getChildren()) {
//            if (node instanceof ToggleButton) {
//                items.add( (ToggleButton) node);
//            }
//        }
//
//        for (Node node : ((VBox) controls.getContent()).getChildren()) {
//            controlsItems.add((ToggleButton) node);
//            items.add((ToggleButton) node);
//        }
//
//        for (Node node : ((VBox) design.getContent()).getChildren()) {
//            designItems.add((ToggleButton) node);
//            items.add((ToggleButton) node);
//        }
//
//        for (Node node : ((VBox) charts.getContent()).getChildren()) {
//            chartsItems.add((ToggleButton) node);
//            items.add((ToggleButton) node);
        }

    public void close() {

    }
}