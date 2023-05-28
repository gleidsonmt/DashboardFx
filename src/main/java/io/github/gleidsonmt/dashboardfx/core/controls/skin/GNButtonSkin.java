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

import io.github.gleidsonmt.dashboardfx.core.controls.GNButton;
import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.beans.value.ObservableValue;
import javafx.event.EventHandler;
import javafx.scene.control.skin.ButtonSkin;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.util.Duration;

/**
 * @author Gleidson Neves da Silveira | gleidisonmt@gmail.com
 * Create on  25/09/2022
 */
public class GNButtonSkin extends ButtonSkin {

    private final Timeline timeline = new Timeline();
    private final Circle circle = new Circle();

    private final Rectangle clip = new Rectangle();

    private final GNButton control;

    public GNButtonSkin(GNButton _control) {
        super(_control);
        this.control = _control;

        this.clip.widthProperty().bind(control.widthProperty());
        this.clip.heightProperty().bind(control.heightProperty());

        EventHandler<MouseEvent> onPressed = event -> {

            if (timeline.getStatus() == Animation.Status.RUNNING) {
                return;
            }

            circle.setRadius(0);
            circle.setStrokeWidth(0);

            circle.setLayoutX(event.getX());
            circle.setLayoutY(event.getY());

            circle.setOpacity(0.5);
            circle.setMouseTransparent(true);

            getChildren().add(circle);

            clip.setArcWidth(10);
            clip.setArcHeight(10);


            clip.setTranslateX(-(circle.getLayoutX()));
            clip.setTranslateY(-(circle.getLayoutY()));

            circle.setClip(clip);

            double diameter = Math.max(control.getWidth(), control.getHeight());
            double radius = diameter / 2;

            timeline.getKeyFrames().setAll(
                    new KeyFrame(Duration.ZERO, new KeyValue(circle.radiusProperty(), 0)),
                    new KeyFrame(Duration.millis(250), new KeyValue(circle.radiusProperty(), radius * 2))
            );


            timeline.play();

            timeline.setOnFinished(e -> getChildren().remove(circle));
        };

        control.addEventFilter(MouseEvent.MOUSE_CLICKED, onPressed);

        circle.setFill(_control.getCircleFill());

        registerChangeListener((ObservableValue<?>) _control.circleFillProperty(), c -> {
            if (c.getValue() != null) {
                circle.setFill((Paint) c.getValue());
            }
        });
    }


}
