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


import animatefx.animation.AnimationFX;
import animatefx.animation.Pulse;
import io.github.gleidsonmt.dashboardfx.core.interfaces.AbsoluteWrapperContainer;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;

/**
 * @author Gleidson Neves da Silveira | gleidisonmt@gmail.com
 * Create on  05/10/2022
 */
public class Wrapper extends StackPane implements AbsoluteWrapperContainer {

    private Pos pos;

    public Wrapper() {
        reset();
        setId("wrapper");
    }

    public Wrapper content(Node node) {
        getChildren().setAll(node);
        return this;
    }

    public void reset() {
        setBackground(
                new Background(
                        new BackgroundFill(
                                Color.gray(0.5, 0.3),
                                CornerRadii.EMPTY,
                                Insets.EMPTY)
                )
        );
    }

    private Drawer drawer;
    public Drawer drawer() {
        if (drawer == null) drawer = new Drawer(this);
        return drawer;
    }

    @Override
    public AbsoluteWrapperContainer pos(Pos _pos) {
        this.pos = _pos;
        return this;
    }

    @Override
    public void show() {
        toFront();
        setOnMouseClicked(event -> {
            if (event.getTarget() == this)
                close();
        });

        setAlignment(pos != null ? pos : Pos.CENTER);

        AnimationFX animation = new Pulse(getChildren().get(0));
        animation.setSpeed(2.2);
        animation.play();
    }

    @Override
    public void close() {

        AnimationFX animation = new Pulse(getChildren().get(0));
        animation.setSpeed(2.2);
        animation.play();
        animation.getTimeline().setOnFinished(event -> toBack());
//        toBack();
    }

    public enum WrapperBackgroundType {
        NONE, GRAY, BLUR;
    }


}
