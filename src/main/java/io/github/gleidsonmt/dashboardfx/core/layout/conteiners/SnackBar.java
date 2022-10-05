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

package io.github.gleidsonmt.dashboardfx.core.layout.conteiners;

import animatefx.animation.RollIn;
import animatefx.animation.RollOut;
import io.github.gleidsonmt.dashboardfx.core.layout.Root;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Cursor;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Region;
import javafx.scene.paint.Color;
import javafx.util.Duration;
import org.jetbrains.annotations.NotNull;
import org.kordamp.ikonli.javafx.FontIcon;
import org.kordamp.ikonli.material.Material;

/**
 * Class to create a snack bar.
 * @author Gleidson Neves da Silveira | gleidisonmt@gmail.com
 * Create on  04/10/2022
 */
public class SnackBar {

    // Root to get background
    private final Root root;

    // Events to set
    private EventHandler<ActionEvent> undoEvent;

    // Options to set
    private String message;
    private FontIcon icon;
    private SnackColors color = SnackColors.GRAY;

    public SnackBar(Root root) {
        this.root = root;
    }

    public SnackBar message(String message) {
        this.message = message;
        return this;
    }

    public SnackBar color(SnackColors color) {
        this.color = color;
        return this;
    }

    public SnackBar icon(Material ikon) {

        FontIcon icon = new FontIcon();
        icon.setIconSize(18);
        icon.setIconCode(ikon);
        icon.getStyleClass().add("ikon");
        icon.setIconColor(Color.WHITE);
        this.icon = icon;

        return this;
    }

    public SnackBar undo(EventHandler<ActionEvent> undoEvent) {
        this.undoEvent = undoEvent;
        return this;
    }

    public void show() {
        try {
            _show(false);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void showOnTop() {
        try {
            _show(true);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void _show(boolean top) throws Exception {

        if (message == null || message.isEmpty()) {
            throw new Exception("Error snacks can't be empty.");
        }

        GridPane snack = createSnack();

        if (top) {
            root.setAlignment(Pos.TOP_CENTER);
            snack.setTranslateY(10);
        } else {
            snack.setTranslateY(-10);
            root.setAlignment(Pos.BOTTOM_CENTER);
        }

        root.getChildren().add(snack);


        RollIn animation = new RollIn(snack);
        animation.setSpeed(1.5);

        animation.play();
        animation.getTimeline().setOnFinished(event -> {
            RollOut out = new RollOut(snack);
            out.setDelay(Duration.millis(3000));
            out.play();
        });

    }


    private @NotNull GridPane createSnack() {

        GridPane snack = new GridPane();
        snack.setPrefHeight(30);
        snack.setMaxHeight(30);
        snack.getStyleClass().add("snack-bar");
        snack.setHgap(5);

        Label infoText = new Label();
        infoText.getStyleClass().add("info-text");

        snack.setMaxWidth(Region.USE_PREF_SIZE);
        snack.getChildren().add(infoText);
        snack.maxWidthProperty().add(infoText.prefWidthProperty());


        if (undoEvent != null){
            Label labelAction = new Label("Desfazer");
            labelAction.setCursor(Cursor.HAND);

            snack.getChildren().add(labelAction);
            GridPane.setConstraints(labelAction, 1,0);

            labelAction.getStyleClass().add("label-action");

            labelAction.setStyle("-fx-border-width : 1px; " +
                    "-fx-text-fill : " + color.getColor() + ";" +
                    "-fx-border-radius : 3; -fx-border-color : white;" +
                    "-fx-background-color : white;" +
                    "-fx-background-radius : 3;"
            );

            labelAction.addEventFilter(MouseEvent.MOUSE_RELEASED, event -> undoEvent.handle(new ActionEvent()));
        }


        if (icon != null) {
            infoText.setGraphic(icon);
        }

        infoText.setText(this.message);

        snack.setStyle(
                "-fx-background-color : " + color.getColor() + ";" +
                        "-fx-padding : 10; -fx-background-radius : 5; " +
                        "-fx-text-fill : white; -fx-font-weight : bold;" );

        return snack;
    }
}
