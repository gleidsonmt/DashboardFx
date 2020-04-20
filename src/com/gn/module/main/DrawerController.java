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

import animatefx.animation.BounceOut;
import com.gn.global.util.PopupCreator;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.geometry.NodeOrientation;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.shape.SVGPath;
import javafx.scene.text.Text;

/**
 * @author Gleidson Neves da Silveira | gleidisonmt@gmail.com
 * Create on  29/03/2020
 */
public class DrawerController {


    private VBox        drawer;
    private ScrollPane  scroll;
    private VBox        drawerBox;
    private VBox        drawerContent;
    private ToggleGroup group;
    private TextField   search;

    private ObservableList<ToggleButton> items         = FXCollections.observableArrayList();
    private ObservableList<ToggleButton> designItems   = FXCollections.observableArrayList();
    private ObservableList<ToggleButton> controlsItems = FXCollections.observableArrayList();
    private ObservableList<ToggleButton> chartsItems   = FXCollections.observableArrayList();

    private FilteredList<ToggleButton> filteredList;

    public DrawerController(VBox drawer) {

        this.group = new ToggleGroup();
        this.drawer = drawer;
        this.drawerBox = (VBox) drawer.lookup("#drawer-box");
        this.scroll = (ScrollPane) drawerBox.lookup("#drawer-scroll");
        this.drawerContent = (VBox) scroll.getContent();
        this.search = (TextField) drawer.lookup("#search");
        Button clear = (Button) drawer.lookup("#btnSearch");
        SVGPath searchIcon = new SVGPath();

        searchIcon.setContent("M15.5 14h-.79l-.28-.27C15.41 12.59 16 11.11 16 9.5 16 5.91 13.09 3 9.5 3S3 " +
                "5.91 3 9.5 5.91 16 9.5 16c1.61 0 3.09-.59 4.23-1.57l.27.28v.79l5 4.99L20.49 19l-4.99-5zm-6 0C7.01 14 5 11.99 " +
                "5 9.5S7.01 5 9.5 5 14 7.01 14 9.5 11.99 14 9.5 14z");
        clear.setGraphic(searchIcon);
        searchIcon.setStyle("-fx-fill : -inner-text;");

        filteredList = new FilteredList<>( items, s -> true );

        search.textProperty().addListener( obs -> {

            String filter = search.getText();
            if ( filter == null || filter.length() == 0 ) {
                barInitial();
                clear.setMouseTransparent(true);
                searchIcon.setContent("M15.5 14h-.79l-.28-.27C15.41 12.59 16 11.11 16 9.5 16 5.91 13.09 3 9.5 3S3 5.91 3 9.5 5.91 16 9.5 16c1.61 0 3.09-.59 4.23-1.57l.27.28v.79l5 4.99L20.49 19l-4.99-5zm-6 0C7.01 14 5 11.99 5 9.5S7.01 5 9.5 5 14 7.01 14 9.5 11.99 14 9.5 14z");
            } else {
                barFiltered(filter);
                clear.setMouseTransparent(false);
                searchIcon.setContent("M19 6.41L17.59 5 12 10.59 6.41 5 5 6.41 10.59 12 5 17.59 6.41 19 12 13.41 17.59 19 19 17.59 13.41 12z");
//
            }
        });

        // This function can be included in one custom control for drawer..
        // The stream is really util function..

        drawerContent.getChildren().stream()
                .filter(e -> e instanceof ToggleButton)
                .map(e -> (ToggleButton) e)
                .forEach(e -> {
                    e.setToggleGroup(group);
                    e.addEventFilter(MouseEvent.MOUSE_CLICKED, event -> {
                        PopupCreator.INSTANCE.closePopup();
                    });
                    e.addEventFilter(MouseEvent.MOUSE_PRESSED, event -> {
                        if (e.isSelected()) {
                            event.consume();
                        }
                        ToggleButton toggleButton = new ToggleButton();
                        toggleButton.setText(e.getText());
                        toggleButton.setToggleGroup(group);
                        toggleButton.setOnMouseClicked(e.getOnMouseClicked());
                        toggleButton.setOnAction(e.getOnAction());
                        toggleButton.getStyleClass().setAll(e.getStyleClass());
                        items.add(toggleButton);

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

                                    ToggleButton toggleButton = new ToggleButton();
                                    toggleButton.setText(c.getText());
                                    toggleButton.setToggleGroup(group);
                                    toggleButton.setOnMouseClicked(c.getOnMouseClicked());
                                    toggleButton.setOnAction(c.getOnAction());
                                    toggleButton.getStyleClass().setAll(c.getStyleClass());
                                    toggleButton.setContentDisplay(c.getContentDisplay());
                                    toggleButton.setNodeOrientation(NodeOrientation.INHERIT);
                                    toggleButton.setPrefWidth(this.drawer.getPrefWidth());
                                    items.add(toggleButton);
                                });
                    }
                });

    }

    public ObservableList<ToggleButton> getItems() {
        return items;
    }


    private void barFiltered(String filter){
        filteredList.setPredicate(s -> s.getText().toUpperCase().contains(filter.toUpperCase()));
        scroll.setContent(filter(filteredList));
    }

    private VBox filter(ObservableList<ToggleButton>  nodes){
        VBox vBox = new VBox();
        vBox.getStyleClass().add("drawer-content");
        vBox.setNodeOrientation(NodeOrientation.RIGHT_TO_LEFT);
        vBox.setAlignment(Pos.TOP_RIGHT);
        VBox.setVgrow(vBox, Priority.ALWAYS);
        for (ToggleButton node : nodes){
            if (node.getGraphic() != null) node.setContentDisplay(ContentDisplay.TEXT_ONLY);
            node.setAlignment(Pos.CENTER_LEFT);
        }
        vBox.getChildren().setAll(nodes);
        return vBox;
    }

    private void barInitial(){
        filteredList.setPredicate(s -> true);
        scroll.setContent(this.drawerContent);

    }
}