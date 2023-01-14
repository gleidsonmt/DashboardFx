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
import io.github.gleidsonmt.gncontrols.material.icon.IconContainer;
import io.github.gleidsonmt.gncontrols.material.icon.Icons;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.VPos;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Priority;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;

public class LeftSide extends VBox {

    private VBox body = new VBox();

    public LeftSide(Context context, Node control) {
        setPadding( new Insets(20));
        setPrefWidth(250);

        getStyleClass().addAll("border-r-1", "border-box");
        body.setSpacing(10);

        Label title = new Label("Designer Panel");
        title.setMaxHeight(100);
        title.getStyleClass().addAll("title");

        GridPane gridPane = new GridPane();

        Button button = new Button();
        button.getStyleClass().add("btn-flat");
        button.setContentDisplay(ContentDisplay.RIGHT);
        button.setPrefWidth(40);
        updateIconButton(button, true);

        SearchLeft searchLeft = new SearchLeft(control);
        VBox.setVgrow(searchLeft, Priority.ALWAYS);

        button.setOnAction(event -> {
            if (getChildren().contains(searchLeft)) {
                getChildren().remove(searchLeft);
                getChildren().add(body);
                updateIconButton(button, true);
            } else {
                getChildren().remove(body);
                getChildren().addAll(searchLeft);
                updateIconButton(button, false);
            }
        });

        gridPane.getChildren().setAll(title, button );

        GridPane.setConstraints(title, 0, 0, 1,1, HPos.LEFT, VPos.CENTER, Priority.ALWAYS, Priority.ALWAYS);
        GridPane.setConstraints(button, 1, 0, 1,1, HPos.RIGHT, VPos.CENTER, Priority.ALWAYS, Priority.ALWAYS);

        this.setSpacing(10);
        this.getChildren().add(gridPane);

        body.getChildren().addAll(
                new Separator(),
                new ColorBox(createHeader(), control),
                new Separator(),
                new FillBox(
                "Fill", new ToggleOptions(
                        control,
                        "Filled", "Outlined"
                    )
                ),
                new Separator(),
                new FillBox("Shape", new ToggleOptions(
                        control,
                        "Rect", "Rounded", "Round"
                )),
                new Separator(),
                new IconBox(
                        "Icon Selector",
                        new IconOptions(
                                control, Icons.FAVORITE, Icons.DONE,
                                Icons.ACCOUNT_CIRCLE
                        )
                ),
                new Separator(),
                new ShapeBox("Shape Box", new ShapeOptions(
                        control, "Heart", "Star", "Triangle"
                )),
                new Separator()

        );

        getChildren().addAll(body);
        VBox.setVgrow(body, Priority.ALWAYS);

    }

    private Node createHeader() {
        ToggleGroup group = new ToggleGroup();

        ToggleButton background = new ToggleButton("BA");
        ToggleButton border = new ToggleButton("BO");
        ToggleButton text = new ToggleButton("T");
        ToggleButton graphic = new ToggleButton("G");
        background.setPrefWidth(40);

        background.getStyleClass().addAll("toggle-left");
        graphic.getStyleClass().addAll("toggle-right");

        GridPane grid = new GridPane();
        grid.setHgap(0);
        grid.getChildren().setAll(background, border, text, graphic);

        GridPane.setConstraints(background, 0,0,1,1, HPos.LEFT, VPos.CENTER, Priority.ALWAYS, Priority.ALWAYS);
        GridPane.setConstraints(border, 1,0,1,1, HPos.LEFT, VPos.CENTER, Priority.ALWAYS, Priority.ALWAYS);
        GridPane.setConstraints(text, 2,0,1,1, HPos.LEFT, VPos.CENTER, Priority.ALWAYS, Priority.ALWAYS);
        GridPane.setConstraints(graphic, 3,0,1,1, HPos.RIGHT, VPos.CENTER, Priority.ALWAYS, Priority.ALWAYS);
        return grid;
    }

    private ToggleButton createOptions() {
        ToggleButton toggleButton = new ToggleButton();
        return toggleButton;
    }

    private void updateIconButton(Button button, boolean searchRoller) {
        if (searchRoller) {
            button.setGraphic(new IconContainer(Icons.IMAGE_SEARCH_ROLLER));
        } else {
            IconContainer icon = new IconContainer(Icons.IMAGE_SEARCH_ROLLER);
            button.setGraphic(new IconContainer(Icons.PALETTE));
        }

    }

}
