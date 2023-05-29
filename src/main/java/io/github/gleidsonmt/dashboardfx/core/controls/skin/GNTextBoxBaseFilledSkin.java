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

package io.github.gleidsonmt.dashboardfx.core.controls.skin;

import io.github.gleidsonmt.dashboardfx.core.controls.enums.FloatAlignment;
import io.github.gleidsonmt.dashboardfx.core.controls.text_box.FloatEditor;
import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.VPos;
import javafx.scene.layout.Pane;
import javafx.util.Duration;

/**
 * @author Gleidson Neves da Silveira | gleidisonmt@gmail.com
 * Create on  16/09/2022
 */
public class GNTextBoxBaseFilledSkin extends GNTextBoxBaseSkin {

    private final GNTextBoxBase control;

    // Only Tests

    private final Pane filledBorder = new Pane();

    private final double animationDuration = 200;

    private final Timeline increase = new Timeline();
    private final Timeline decrease = new Timeline();


    public GNTextBoxBaseFilledSkin(GNTextBoxBase control) {
        super(control);
        this.control = control;

        filledBorder.getStyleClass().add("filled-border");

        filledBorder.setMinHeight(1D);
        filledBorder.setMaxHeight(1D);
        filledBorder.setPrefHeight(1D);

        filledBorder.setMaxWidth(0);

//        filledBorder.setFill(Color.RED);

        getChildren().add(filledBorder);

        if (control.getEditor() instanceof FloatEditor)
            filledBorder.setManaged(false);

        filledBorder.setStyle("-fx-background-color : -fx-accent;");

        if (control.getEditor() instanceof FloatEditor)
            ((FloatEditor) control.getEditor()).setFloatAlignment(FloatAlignment.TOP);

        if (getChildren().contains(control.getLeftNode())) {
            control.setPadding(new Insets(0, 5, 0, 10));
        } else {
            control.setPadding(new Insets(0, 5, 0, 5));
        }

        control.leftNodeProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue != null) {
                control.setPadding(new Insets(0, 5, 0, 10));
            } else control.setPadding(new Insets(0, 5, 0, 5));
        });

        if (control.isFocused()) {
            filledBorder.maxWidthProperty().bind(control.widthProperty());
        }

        registerChangeListener(control.focusedProperty(), c -> {
            filledBorder.maxWidthProperty().unbind();

            if ((boolean) c.getValue()) {
                openFilled();
            }
            else {
                closeFilled();
            }
        });

    }

    @Override
    protected void layoutChildren(double x, double y, double w, double h) {
        super.layoutChildren(x, y, w, h);
        layoutInArea(filledBorder, x - (snappedLeftInset()), 0, w + snappedRightInset() + snappedLeftInset(), h, -1, HPos.CENTER, VPos.BOTTOM);

//        filledBorder.relocate(x, y);
    }


    private void openFilled() {

        if (decrease.getStatus() == Animation.Status.RUNNING) {
            decrease.stop();
//            return;
        }

        increase.getKeyFrames().setAll(

                new KeyFrame(Duration.ZERO, new KeyValue(
                        filledBorder.maxWidthProperty(), filledBorder.getMaxWidth()
                )),

                new KeyFrame(Duration.millis(animationDuration), new KeyValue(
                        filledBorder.maxWidthProperty(), control.getWidth()
                ))
        );

        increase.setOnFinished(event -> {
            filledBorder.setMaxWidth(-1);
        });

        increase.play();
    }

    private void closeFilled() {

//
        if (increase.getStatus() == Animation.Status.RUNNING) {
            increase.stop();
        }

        decrease.getKeyFrames().setAll(
                new KeyFrame(Duration.ZERO,
                        new KeyValue(filledBorder.maxWidthProperty(), filledBorder.getMaxWidth())
                ),
                new KeyFrame(Duration.millis(animationDuration),
                        new KeyValue(filledBorder.maxWidthProperty(), 0)
                )
        );

        decrease.setOnFinished(event -> {
            filledBorder.setMaxWidth(0);
        });

        decrease.play();
    }

}
