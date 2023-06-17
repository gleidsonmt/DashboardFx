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

package io.github.gleidsonmt.dashboardfx.core.controls;

import javafx.animation.FillTransition;
import javafx.animation.ParallelTransition;
import javafx.animation.TranslateTransition;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.scene.Parent;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.util.Duration;

/**
 * @author Gleidson Neves da Silveira | gleidisonmt@gmail.com
 * Create on  15/06/2023
 */
@SuppressWarnings("unused")
public class ToggleSwitch extends Parent {

    private final BooleanProperty switchedOn = new SimpleBooleanProperty(false);

    private final TranslateTransition translateAnimation = new TranslateTransition(Duration.seconds(0.15));
    private final FillTransition fillAnimation = new FillTransition(Duration.seconds(0.15));
    private final ParallelTransition animation = new ParallelTransition(translateAnimation, fillAnimation);

    private BooleanProperty switchedOnProperty() {
        return switchedOn;
    }

    public ToggleSwitch() {
        getStyleClass().add("toggle-switch");
        Rectangle background = new Rectangle(40, 26);
        background.getStyleClass().add("background");
        background.setArcWidth(25);
        background.setArcHeight(25);
        background.setFill(Color.WHITE);
        background.setStroke(Color.LIGHTGRAY);
        Circle trigger = new Circle(10);
        trigger.setCenterX(12);
        trigger.setCenterY(13);
        trigger.setFill(Color.WHITE);
        trigger.setStroke(Color.LIGHTGRAY);
        getChildren().addAll(background, trigger);

        translateAnimation.setNode(trigger);
        fillAnimation.setShape(background);

        switchedOn.addListener((obs, oldState, newState) -> {
            boolean isOn = newState;
            translateAnimation.setToX(
                    isOn ? 100 - 84 : 0
            );
            fillAnimation.setFromValue(isOn ? Color.WHITE : Color.web("#00BCD4"));
            fillAnimation.setToValue(isOn ? Color.web("#00BCD4") : Color.WHITE);
            animation.play();
        });

        setOnMouseClicked(event -> switchedOn.set(!switchedOn.get()));
    }
}
