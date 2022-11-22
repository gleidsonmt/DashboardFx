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

package io.github.gleidsonmt.dashboardfx.core.layout;

import io.github.gleidsonmt.dashboardfx.core.app.exceptions.NavigationException;
import io.github.gleidsonmt.dashboardfx.core.app.interfaces.Context;
import io.github.gleidsonmt.dashboardfx.core.app.view_wrapper.ActionView;
import io.github.gleidsonmt.dashboardfx.core.layout.conteiners.WrapperContainer;
import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.geometry.Pos;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.StackPane;
import javafx.util.Duration;

public class Drawer implements ActionView, WrapperContainer, Context {
    private final Wrapper wrapper;
    private StackPane content;
    private final Timeline timeline = new Timeline();

    private final EventHandler<MouseEvent> closeEvent = event -> hide();

    public Drawer(Wrapper _wrapper) {
        this.wrapper = _wrapper;
    }

    public Drawer content(StackPane _content) {
        this.content = _content;
        return this;
    }

    public void show() {

        content.setTranslateX(-content.getMaxWidth());
        this.wrapper.setAlignment(Pos.CENTER_LEFT);

        if (!this.wrapper.getChildren().contains(content))
            this.wrapper.getChildren().add(content);

        timeline.getKeyFrames().setAll(
                new KeyFrame(Duration.ZERO, new KeyValue(
                    content.translateXProperty(), -250
                )),
                new KeyFrame(Duration.millis(200), new KeyValue(
                        content.translateXProperty(), 0
                ))
        );

        this.timeline.setOnFinished(null);
        this.wrapper.setOnMouseClicked(closeEvent);
        this.wrapper.toFront();
        timeline.play();
    }

    public void hide() {
        timeline.getKeyFrames().setAll(
                new KeyFrame(Duration.ZERO, new KeyValue(
                        content.translateXProperty(), 0
                )),
                new KeyFrame(Duration.millis(200), new KeyValue(
                        content.translateXProperty(), -250
                ))
        );

        timeline.setOnFinished(event -> {
            wrapper.toBack();
        });

        timeline.play();
    }

    @FXML
    private void goWrapper() {
        try {
            context.getRoutes().setContent("wrapper");
        } catch (NavigationException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void onEnter() {

    }

    @Override
    public void onExit() {

    }


}
