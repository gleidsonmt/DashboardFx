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

package io.github.gleidsonmt.dashboardfx.controllers;

import io.github.gleidsonmt.dashboardfx.core.layout.DrawerBehavior;
import io.github.gleidsonmt.dashboardfx.core.layout.IWrapper;
import io.github.gleidsonmt.dashboardfx.core.layout.conteiners.creators.TutorialCreator;
import io.github.gleidsonmt.dashboardfx.views.TutorialUnderstanding;
import io.github.gleidsonmt.dashboardfx.views.WrappersView;
import io.github.gleidsonmt.dashboardfx.core.app.exceptions.NavigationException;
import io.github.gleidsonmt.dashboardfx.core.app.material.controls.ControlData;
import io.github.gleidsonmt.dashboardfx.core.app.material.controls.ControlViewPanel;
import io.github.gleidsonmt.dashboardfx.core.app.services.Context;
import io.github.gleidsonmt.dashboardfx.core.app.interfaces.View;
import io.github.gleidsonmt.dashboardfx.core.app.view_wrapper.ActionView;
import io.github.gleidsonmt.dashboardfx.core.layout.conteiners.creators.DeclarativeComponent;
import io.github.gleidsonmt.dashboardfx.core.layout.conteiners.interfaces.NestedWrapperContainer;
import io.github.gleidsonmt.dashboardfx.views.AreaChartView;
import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.geometry.HPos;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.control.ToggleButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.StackPane;
import javafx.util.Duration;

import java.net.URL;
import java.util.ResourceBundle;

public class Drawer extends DeclarativeComponent<Drawer> implements ActionView, NestedWrapperContainer {

    @FXML private ToggleGroup group;
    @FXML private StackPane root;
    private final IWrapper wrapper;
    private StackPane content;
    private final Timeline timeline = new Timeline();

    private double maxSize = 250;
    private HPos side;
    protected Context context;
    private final EventHandler<MouseEvent> closeEvent = event -> hide();

//    public Drawer() {};

    public Drawer(IWrapper _wrapper) {
        this.wrapper = _wrapper;
    }

    public Drawer content(StackPane _content) {
        this.content = _content;
        _content.setMaxWidth(maxSize);
        return this;
    }

    @Override
    public Drawer style(String style) {
        this.content.setStyle(style);
        return this;
    }

    @Override
    public Drawer size(double width, double height) {
        return this;
    }

    public void show() {
        this.wrapper.setAlignment(
                side == HPos.LEFT ?
                        Pos.CENTER_LEFT : Pos.CENTER_RIGHT
        );

        final double tx = side == HPos.LEFT ?
                -content.getMaxWidth() :
                250;


        if (!this.wrapper.getChildren().contains(content))
            this.wrapper.getChildren().setAll(content);

        timeline.getKeyFrames().setAll(
                new KeyFrame(Duration.ZERO, new KeyValue(
                    content.translateXProperty(), tx
                )),
                new KeyFrame(Duration.millis(100), new KeyValue(
                        content.translateXProperty(), 0
                ))
        );

        this.timeline.setOnFinished(null);
        this.wrapper.setOnMouseClicked(closeEvent);
        this.wrapper.toFront();
        timeline.play();
    }

    public void hide() {

        final double tx = side == HPos.LEFT ?
                -content.getMaxWidth() :
                250;

        timeline.getKeyFrames().setAll(
                new KeyFrame(Duration.ZERO, new KeyValue(
                        content.translateXProperty(), 0
                )),
                new KeyFrame(Duration.millis(200), new KeyValue(
                        content.translateXProperty(), tx
                ))
        );

        timeline.setOnFinished(event -> {
            wrapper.getChildren().clear();
            content.setTranslateX(0);
            wrapper.toBack();
        });

        timeline.play();
    }

    @Override
    public Drawer side(HPos _side) {
        this.side = _side;
        return this;
    }

    @Override
    public void onEnter(Context context) {
        this.context = context;
    }

    @Override
    public void onExit(Context context) {

    }

    @Override
    public void onInit(Context context) {
        this.context = context;
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        new DrawerBehavior(root, group);
    }

}
