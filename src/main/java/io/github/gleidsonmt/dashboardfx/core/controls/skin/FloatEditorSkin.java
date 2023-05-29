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
import javafx.css.PseudoClass;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.VPos;
import javafx.scene.Parent;
import javafx.scene.control.Label;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.util.Duration;

/**
 * @author Gleidson Neves da Silveira | gleidisonmt@gmail.com
 * Create on  08/09/2022
 */
public class FloatEditorSkin extends EditorSkin {

    private static final PseudoClass FLOAT_PSEUDO_CLASS =
            PseudoClass.getPseudoClass("float");

    private final Label moveablePrompt  = new Label("Float Prompt");

    private final FloatEditor control;
    private VPos                vPos;

    private final double aniVeloz = 100;

    private final Timeline upAnimation = new Timeline();
    private final Timeline downAnimation = new Timeline();


    public FloatEditorSkin(FloatEditor _control) {
        super(_control);
        this.control = _control;

        // Setting default Conventions
        moveablePrompt.getStyleClass().add("label-prompt");

        moveablePrompt.textProperty().bind(control.promptTextProperty());
        moveablePrompt.setFont(new Font(12));

        moveablePrompt.getStyleClass().add("prompt-text");
        moveablePrompt.setFocusTraversable(false);
        moveablePrompt.setMouseTransparent(true);
        moveablePrompt.toFront();


        // add prompt and hide old prompt
        getChildren().add(moveablePrompt);
        setPromptTextFill(Color.TRANSPARENT);


        if (_control.getText() != null && !_control.getText().isEmpty())
            vPos = getPos();
        else
            vPos = VPos.CENTER;

        Parent inFocus; // Change the focus when the parent is gntextbox
        if (control.getParent() instanceof GNTextBoxBase) {
            inFocus = control.getParent();
        } else
            inFocus = control;


//      When focused start animation
        registerChangeListener(inFocus.focusedProperty(), c -> {

            if ((boolean) c.getValue()) {

                control.requestFocus();

                if (downAnimation.getStatus() == Animation.Status.RUNNING) {
                    downAnimation.stop();
                    up();

                } else
                    if (isPromptInCenter()) {
                        up();
                    }

            } else {

                if (upAnimation.getStatus() == Animation.Status.RUNNING) {
                    upAnimation.stop();

                    down();
                } else {

                    if (isPromptInTop()) {
                        down();
                    }
                }
            }
        });

        registerChangeListener(control.floatAlignmentProperty(), c -> {
            vPos = (VPos) c.getValue();

            if (vPos == VPos.TOP) // It's for calcs.. use the padding for relocate the prompt in line of border
                control.setPadding(new Insets(0, 6,0,6));
            else {
                control.setPadding(new Insets(4, 7, 4, 5));
            }

        });

        if (control.getText() != null && !control.getText().isEmpty()) {
            moveablePrompt.setTranslateX(control.getDistanceX());
        }


        _control.textProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue != null && !newValue.isEmpty()) {
                if (!isPromptInTop()) {
                    up();
                }
            }
        });
    }

    // Getting the middle of prompt
    public double getPromptHalf() {
//        return (moveablePrompt.getHeight() / 2) ;
        return moveablePrompt.getHeight() ;
    }

    // Getting the top line from the center of this skin
    public double getMaxTopPosition() {
        return - (control.getHeight() / 2 );
    }

    // Convetion for define in center
    private boolean isPromptInCenter() {
        return vPos == VPos.CENTER;
    }

    // Convention for define is in top
    private boolean isPromptInTop() {
        return vPos == VPos.BASELINE || vPos ==  VPos.TOP;
    }

    private void configAnimation() {

    }

    @Override
    protected void layoutChildren(double x, double y, double w, double h) {
        super.layoutChildren(x, y, w, h);


        if (downAnimation.getStatus() == Animation.Status.RUNNING ||
            upAnimation.getStatus() == Animation.Status.RUNNING) {

                positionInArea(moveablePrompt, x,
                        y, w, h , -1, HPos.LEFT, VPos.CENTER);

            return;
        }

        moveablePrompt.setTranslateY(0);


        if (isPromptInTop()) {
            positionInArea(moveablePrompt, x, y, w, h , -1, HPos.LEFT, getPos());
//            positionInArea(filledBorder, x, y, w, h , -1, HPos.LEFT, getPos());

        } else {
            positionInArea(moveablePrompt, x, y, w, h , -1, HPos.LEFT, VPos.CENTER);
        }

    }

    private VPos getPos() {
        if (control.getFloatAlignment() == FloatAlignment.TOP) return VPos.TOP;
        else return VPos.BASELINE;
    }


    // Animation for top
    private void up() {

//        if (!control.getText().isEmpty()) return;

        double ty;

        if (getPos() == VPos.TOP) {
            ty = getMaxTopPosition() + snappedTopInset() ;
        } else {
            ty = getMaxTopPosition() ;

        }


        upAnimation.getKeyFrames().setAll(
                new KeyFrame(Duration.ZERO, new KeyValue(
                        moveablePrompt.translateYProperty(), moveablePrompt.getTranslateY()
                )),

                new KeyFrame(Duration.millis(aniVeloz), new KeyValue(
                        moveablePrompt.translateYProperty(), ty
                )),

                new KeyFrame(Duration.ZERO, new KeyValue(
                        moveablePrompt.translateXProperty(), control.getTranslateX()
                )),

                new KeyFrame(Duration.millis(aniVeloz), new KeyValue(
                        moveablePrompt.translateXProperty(),  control.getDistanceX()
                )),

                new KeyFrame(Duration.ZERO, new KeyValue(
                        moveablePrompt.fontProperty(), moveablePrompt.getFont()
                )),

                new KeyFrame(Duration.millis(aniVeloz), new KeyValue(
                        moveablePrompt.fontProperty(), new Font(11)
                ))
        );

        control.pseudoClassStateChanged(FLOAT_PSEUDO_CLASS, true);
        upAnimation.setOnFinished(event -> vPos = getPos());

        upAnimation.play();

//        control.setClip(moveablePrompt);
    }

    // Animation for bottom
    private void down() {

        if (control.getText() != null && !control.getText().isEmpty()) return;
//        if (!control.getText().isEmpty()) return;
//        if (animation.getStatus() == Animation.Status.RUNNING) return;

        double ty;
//
        if (getPos() == VPos.TOP) {
            ty = getMaxTopPosition() + snappedTopInset();

        } else {
            ty = getMaxTopPosition();
        }

        double startY = moveablePrompt.getTranslateY() >= 0 ? ty : moveablePrompt.getTranslateY();
//        startY = 0;

        downAnimation.getKeyFrames().setAll(
                new KeyFrame(Duration.ZERO, new KeyValue(
                        moveablePrompt.translateYProperty(), startY
                )),
                new KeyFrame(Duration.millis(aniVeloz), new KeyValue(
                        moveablePrompt.translateYProperty(), 0
                )),


                new KeyFrame(Duration.ZERO, new KeyValue(
                        moveablePrompt.translateXProperty(), moveablePrompt.getTranslateX()
                )),

                new KeyFrame(Duration.millis(aniVeloz), new KeyValue(
                        moveablePrompt.translateXProperty(), 0
                )),

                new KeyFrame(Duration.ZERO, new KeyValue(
                        moveablePrompt.fontProperty(), moveablePrompt.getFont()
                )),

                new KeyFrame(Duration.millis(aniVeloz), new KeyValue(
                        moveablePrompt.fontProperty(), new Font(12)
                ))
        );

        control.pseudoClassStateChanged(FLOAT_PSEUDO_CLASS, false);

        downAnimation.setOnFinished(event -> {
            vPos = VPos.CENTER;

//            moveablePrompt.setTranslateY(0);
        });

        downAnimation.play();

    }

    @Override
    protected double computeMinHeight(double width, double topInset, double rightInset, double bottomInset, double leftInset) {
        return super.computeMinHeight(width, topInset, rightInset, bottomInset, leftInset) ;
    }

    @Override
    protected double computeMinWidth(double height, double topInset, double rightInset, double bottomInset, double leftInset) {
        return super.computeMinWidth(height, topInset, rightInset, bottomInset, leftInset) +
                (moveablePrompt.prefWidth(height) + (snappedRightInset() + snappedLeftInset()));
    }

    @Override
    protected double computePrefHeight(double width, double topInset, double rightInset, double bottomInset, double leftInset) {
        return super.computePrefHeight(width, topInset, rightInset, bottomInset, leftInset);
    }

    @Override
    protected double computePrefWidth(double heigth, double topInset, double rightInset, double bottomInset, double leftInset) {
        return super.computePrefWidth(heigth, topInset, rightInset, bottomInset, leftInset);
    }


}
