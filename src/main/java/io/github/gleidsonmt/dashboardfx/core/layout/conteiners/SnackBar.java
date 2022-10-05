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
import io.github.gleidsonmt.gncontrols.material.icon.IconContainer;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.ContentDisplay;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.util.Duration;

import java.util.Timer;
import java.util.TimerTask;

/**
 * @author Gleidson Neves da Silveira | gleidisonmt@gmail.com
 * Create on  04/10/2022
 */
public class SnackBar {

    private final Root root;

    private Colors color = Colors.GRAY;

    private final IconContainer iconContainer = new IconContainer();
    private final VBox box = new VBox(iconContainer);

    // Event handlers
    private EventHandler<ActionEvent> undoEvent;

    private String message;

    public enum Colors {
        GRAY("-dark-gray"),
        GRAPEFRUIT("-grapefruit"),
        INFO("-info"),
        WARNING("-warning"),
        SUCCESS("-mint");

        private final String color;

        Colors(String color) {
            this.color = color;
        }

        public String getColor() {
            return color;
        }
    }

    public SnackBar(Root root) {
        this.root = root;

//        snack.getStyleClass().add("depth-2");

        box.setPadding(new Insets(5));
        box.setMinSize(25,25);
        box.setMaxSize(25,25);
        box.setAlignment(Pos.CENTER);

//        labelAction.setButtonType(GNButtonType.SEMI_ROUNDED);
//
//        labelAction.setPrefSize(80,20);
//        labelAction.setMaxHeight(30);
//        labelAction.setCursor(Cursor.HAND);
//        labelAction.setAlignment(Pos.CENTER);
//
//        labelAction.getStyleClass().add("snack-button");

//        labelAction.getStyleClass().add("depth-1");



    }

    public SnackBar message(String message) {
        this.message = message;
        return this;
    }

    public SnackBar color(Colors color) {
        this.color = color;
        return this;
    }

    public SnackBar undo(EventHandler<ActionEvent> undoEvent) {
        this.undoEvent = undoEvent;
        return this;
    }

    public void show() {
        show(false);
    }

    private Label createSnack() {

        Label snack = new Label();
        snack.setPrefHeight(30);
        snack.setMaxHeight(30);
        snack.setGraphicTextGap(10D);
        snack.getStyleClass().add("snack-bar");
        snack.setContentDisplay(ContentDisplay.RIGHT);


        return snack;
    }

    private void show(boolean top) {

        Label snack = createSnack();
        snack.setText(this.message);

        if (top)
            root.setAlignment(Pos.TOP_CENTER);
        else
            root.setAlignment(Pos.BOTTOM_CENTER);

        root.getChildren().add(snack);


        snack.setTranslateY(-10);
//        box.setStyle("-fx-background-color : " + color.getColor() + "; -fx-background-radius : 100;");
//        setGraphic(box);

        snack.setStyle(
                "-fx-background-color : " + color.getColor() + ";" +
                        "-fx-padding : 10; -fx-background-radius : 5; " +
                        "-fx-text-fill : white; -fx-font-weight : bold;" );

//        labelAction.setStyle("-fx-border-width : 1px; " +
//                "-fx-text-fill : white;" +
//                "-fx-border-radius : 5; -fx-border-color : white;" +
//                "-fx-background-color : " + color.getColor() + ";" +
//                "-fx-background-radius : 5;"
//        );

        RollIn animation = new RollIn(snack);
        animation.setSpeed(1.5);

        animation.play();
        animation.getTimeline().setOnFinished(event -> {
            RollOut out = new RollOut(snack);
            out.setDelay(Duration.millis(3000));
            out.play();
        });

    }

    public SnackBar onHide(EventHandler<ActionEvent> event) {

        Timer timer = new Timer();

        timer.schedule(new TimerTask() {
            @Override
            public void run() {

                Platform.runLater( () -> {

                    event.handle(new ActionEvent());
//                    RollOut out = new RollOut(snack);
//                    out.getTimeline().setOnFinished(e -> root.getChildren().remove(snack));
//                    out.play();

                });

            }
        }, 3000);
        return this;
    }

    public SnackBar action(EventHandler<ActionEvent> eventHandler) {
//        snack.setGraphic(labelAction);

        eventHandler.handle(new ActionEvent());

//        labelAction.addEventFilter(ActionEvent.ACTION, event -> {
//            Tada animation = new Tada(snack);
//            animation.play();
//            animation.getTimeline().setOnFinished(e -> root.getChildren().remove(snack));
//            undoEvent.handle(new ActionEvent());
//        });

        return this;

    }
}
