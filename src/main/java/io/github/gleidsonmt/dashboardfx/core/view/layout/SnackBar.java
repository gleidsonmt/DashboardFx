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

package io.github.gleidsonmt.dashboardfx.core.view.layout;

import animatefx.animation.RollIn;
import animatefx.animation.RollOut;
import io.github.gleidsonmt.dashboardfx.core.impl.IRoot;
import io.github.gleidsonmt.dashboardfx.core.interfaces.WrapperContainer;
import io.github.gleidsonmt.dashboardfx.core.view.layout.options.SnackColors;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Cursor;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Region;
import javafx.scene.paint.Color;
import javafx.scene.paint.Material;
import javafx.scene.shape.SVGPath;
import javafx.util.Duration;
import org.jetbrains.annotations.NotNull;
import org.kordamp.ikonli.javafx.FontIcon;


/**
 * Class to create a snack bar.
 * @author Gleidson Neves da Silveira | gleidisonmt@gmail.com
 * Create on  04/10/2022
 */
public class SnackBar implements WrapperContainer {

    // IRoot to get background
    private final IRoot IRoot;

    // Events to set
    private EventHandler<ActionEvent> undoEvent;

    // ActionOptions to set
    private String message;
    private Node icon;
    private SnackColors color = SnackColors.GRAY;

    public SnackBar(IRoot IRoot) {
        this.IRoot = IRoot;
    }

    public SnackBar message(String message) {
        this.message = message;
        return this;
    }

    public SnackBar color(SnackColors color) {
        this.color = color;
        return this;
    }

//    public SnackBar icon(Material ikon) {
//
//        FontIcon icon = new FontIcon();
//        icon.setIconSize(18);
//        icon.setIconCode(ikon);
//        icon.getStyleClass().add("ikon");
//        icon.setIconColor(Color.WHITE);
//        this.icon = icon;
//
//        return this;
//    }

    public SnackBar icon(SVGPath _icon) {
        this.icon = _icon;
        _icon.setFill(Color.WHITE);
        return this;
    }

    public SnackBar undo(EventHandler<ActionEvent> undoEvent) {
        this.undoEvent = undoEvent;
        return this;
    }

    @Override
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
            IRoot.setAlignment(Pos.TOP_CENTER);
            snack.setTranslateY(10);
        } else {
            snack.setTranslateY(-10);
            IRoot.setAlignment(Pos.BOTTOM_CENTER);
        }

        IRoot.getChildren().add(snack);

        RollIn animation = new RollIn(snack);
        animation.setSpeed(1.5);
//
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

            labelAction.setStyle(
                    "-snack-color : " + color.getColor() + ";"
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
