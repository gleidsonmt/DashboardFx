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

import io.github.gleidsonmt.dashboardfx.core.controls.GNIconButton;
import io.github.gleidsonmt.dashboardfx.core.controls.GNTextBox;
import io.github.gleidsonmt.dashboardfx.core.controls.icon.Icons;
import io.github.gleidsonmt.dashboardfx.core.controls.text_box.CounterLabel;
import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.application.Platform;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.EventHandler;
import javafx.geometry.HPos;
import javafx.geometry.VPos;
import javafx.scene.control.Label;
import javafx.scene.control.SkinBase;
import javafx.scene.input.MouseEvent;
import javafx.util.Duration;
import org.jetbrains.annotations.NotNull;


/**
 * @author Gleidson Neves da Silveira | gleidisonmt@gmail.com
 * Create on  22/09/2022
 */
public class GNTextBoxSkin extends SkinBase<GNTextBox> {

    private final Label         helperLabel         = new Label();
    private final GNTextBoxBase base                = new GNTextBoxBase();
//    private final IconContainer iconContainer       = new IconContainer();
    private final GNIconButton iconContainer        = new GNIconButton();
    private final CounterLabel counterLabel         = new CounterLabel();

    private final Timeline timeline = new Timeline();


    private GNIconButton  iconButton;

    private final GNTextBox control;

    public GNTextBoxSkin(GNTextBox _control) {
        super(_control);
        this.control = _control;
        this.iconButton = new GNIconButton(Icons.CLEAR);
        this.iconButton.setFocusTraversable(false);
        this.iconButton.setManaged(false);
        this.iconButton.setOnMouseClicked(createAction());

        helperLabel.getStyleClass().add("helper-text");
        iconContainer.getStyleClass().add("action-button");
        iconButton.getStyleClass().add("action-button");

        base.setMaskText(_control.isMaskText());

        if (_control.isVisibleHelperText()) {
            getChildren().add(helperLabel);
        }

        _control.visibleHelperTextProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue) {
                getChildren().add(helperLabel);
            } else getChildren().remove(helperLabel);
        });

        _control.iconProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue == null || newValue == Icons.NONE) {
                base.setLeftNode(null);
            } else {
                base.setLeftNode(iconContainer);
                iconContainer.setIcon(newValue);
            }
        });

        registerChangeListener(_control.focusedProperty(), c -> {
            if ((boolean) c.getValue()) {
                base.requestFocus();
            }
        });

        helperLabel.setFocusTraversable(false);
        counterLabel.setFocusTraversable(false);
        helperLabel.setManaged(false);
        counterLabel.setManaged(false);

        getChildren().add(base);

        bind(_control);

        control.validProperty().addListener((observable, oldValue, newValue) -> {

            if (!newValue) {
                timeline.play();
            } else helperLabel.setOpacity(0.0);

        });

        double x = 30;
        iconContainer.setMaxSize(x, x);
        iconContainer.setMinSize(x, x);
        iconContainer.setPrefSize(x, x);


        helperLabel.setOpacity(0.0);
        setAnimatedValidate();

//        Platform.runLater(() -> control.setEditor(base.getEditor()));

    }

    public void validate() {
        if (!control.isValid())
            timeline.play();
        else helperLabel.setOpacity(0.0);
    }


    private void setAnimatedValidate() {
        double aniV = 200;
        timeline.getKeyFrames().setAll(
                new KeyFrame(Duration.ZERO, new KeyValue(
                        helperLabel.opacityProperty(), 0.0
                )),
                new KeyFrame(Duration.millis(aniV), new KeyValue(
                        helperLabel.opacityProperty(), 1.0
                )),

                new KeyFrame(Duration.ZERO, new KeyValue(
                        helperLabel.translateYProperty(), 15
                )),
                new KeyFrame(Duration.millis(aniV), new KeyValue(
                        helperLabel.translateYProperty(), 0
                ))
        );

    }

    protected void setActionButton(GNIconButton node) {
        this.iconButton = node;
    }

    protected GNIconButton getActionButton() {
        return this.iconButton;
    }

    private void bind(@NotNull GNTextBox editor) {

        base.textProperty().bindBidirectional(editor.textProperty());
        base.promptTextProperty().bindBidirectional(editor.promptTextProperty());
        helperLabel.textProperty().bindBidirectional(editor.helperTextProperty());

        base.setFieldType(editor.getFieldType());
        base.setAnimated(control.isAnimated());

        if (control.getIcon() != null && control.getIcon() != Icons.NONE) {
            base.setLeftNode(iconContainer);
            iconContainer.setIcon(control.getIcon());
            base.setLeftNode(iconContainer);
        } else base.setLeftNode(null);


        if (control.getText() != null && control.isAction()) {
            if (control.isAction() && !control.getText().isEmpty()) {
                base.setRightNode(control.getActionIcon());
            }
        }

        if (control.getText() != null && !control.getText().isEmpty()) {
            base.setRightNode(getActionButton());
        }

        control.textProperty().addListener(hideAction);

        //
        counterLabel.setManaged(false);

        if (control.getCount() > 0) {

            getChildren().add(counterLabel);

            counterLabel.maxProperty().unbind();
            counterLabel.actProperty().unbind();

            counterLabel.maxProperty().bind(control.countProperty());
            counterLabel.actProperty().bind(control.textProperty().length());

            // The editor only placed before javafx ui thread start
            Platform.runLater(() -> base.getEditor().maxTextProperty().bindBidirectional(counterLabel.maxProperty()));

        }
    }

    private final ChangeListener<String> hideAction = new ChangeListener<>() {
        @Override
        public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
            base.setRightNode(newValue != null && !newValue.isEmpty() && control.isAction() ? getActionButton() : null);
        }
    };

    private @NotNull EventHandler<MouseEvent> createAction() {
        return event -> {
            base.getEditor().clear();
            base.getEditor().requestFocus();
        };
    }

    protected void reset() {
        control.textProperty().removeListener(hideAction);
    }

    @Override
    protected void layoutChildren(double x, double y, double w, double h) {

       double editorSize = getChildren().contains(counterLabel) ? counterLabel.getHeight() : getChildren().contains(helperLabel) ? helperLabel.getHeight() : 0;

        layoutInArea(base, x, y, w, h - editorSize, -1, HPos.LEFT, VPos.TOP);
        layoutInArea(helperLabel, x, y, w, h, -1, HPos.LEFT, VPos.BOTTOM);
        layoutInArea(counterLabel, x, y, w, h, -1, HPos.RIGHT, VPos.BOTTOM);

    }

    @Override
    protected double computeMinHeight(double width, double topInset, double rightInset, double bottomInset, double leftInset) {
        return base.minHeight(width) + helperLabel.minHeight(width) ;
    }

    @Override
    protected double computePrefHeight(double width, double topInset, double rightInset, double bottomInset, double leftInset) {
        return base.prefHeight(width) + helperLabel.prefHeight(width)  ;
    }

    protected GNTextBoxBase getBase() {
        return base;
    }
}
