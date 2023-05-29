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

package io.github.gleidsonmt.dashboardfx.core.impl.layout;

//import animatefx.animation.AnimationFX;
//import animatefx.animation.Pulse;

import io.github.gleidsonmt.dashboardfx.core.impl.IRoot;
import io.github.gleidsonmt.dashboardfx.core.interfaces.WrapperContainer;
import io.github.gleidsonmt.dashboardfx.core.view.layout.Wrapper;
import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.geometry.Bounds;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.control.skin.TextInputControlSkin;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Region;
import javafx.stage.Screen;
import javafx.util.Duration;

/**
 * @author Gleidson Neves da Silveira | gleidisonmt@gmail.com
 * Version 0.0.1
 * Create on  05/03/2023
 */
public class Flow implements WrapperContainer {

    private final IRoot root;
    private Region content;

    public Flow(IRoot root) {
        this.root = root;

        root.widthProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue != null) close();
        });

    }

    public Flow content(Region region) {
        this.content = region;
//        if (region.getPrefWidth() == -1 || region.getPrefHeight() == -1) {
//                region.setPrefSize(300, 400);
//            region.setMaxSize(region.getPrefWidth(), region.getPrefHeight());
//        }

        content.addEventFilter(MouseEvent.MOUSE_EXITED, event -> {
            if (root.getChildren().size() <= 2) return;
            if (event.getPickResult().getIntersectedNode().getStyleClass().contains("gn-badge")) return;

            if (!opened) return;
            close();
        });
        return this;
    }

    private boolean opened = true;

    @Override
    public void close() {
        opened = false;

        if (root.getChildren().contains(content)) {
            root.getChildren().remove(this.content);
        }
    }

    @Override
    public void show() {
//        AnimationFX animationFX = new Pulse(this.content);
//        animationFX.setSpeed(1.8);
//        animationFX.play();
    }

    public void show(Direction direction,  Node target) {
        show(direction, target, 0, 0);
    }

    public void show(Direction direction, Node target, double x, double y) {

        opened = true;

        if (root.getChildren().contains(content)) {
            return;
        }

        if(root.getChildren().stream().anyMatch(p -> p.getStyleClass().contains("container"))) {
            root.getChildren().remove(root.getChildren().size()-1);
            return;
        }


        content.setTranslateX(0);
        content.setTranslateY(0);

        root.getChildren().add( root.getChildren().size()-1, content);
        content.toFront();

//        node.addEventHandler(MouseEvent.MOUSE_CLICKED, event -> {
//        content.getStyleClass().addAll(super.styleClass);
        content.getStyleClass().add("depth-1");
        content.setPadding(new Insets(10));

        Bounds bounds = target.localToScene(target.getLayoutBounds());
//        wrapper.toFront();
//        content.setTranslateX(bounds.getMinX());
//        content.setTranslateY(bounds.getMaxY());

        switch (direction) {
            case TOP_LEFT -> {
                content.setTranslateX(clamp(bounds.getMinX(), content.getMaxWidth()));
                content.setTranslateY(clamp(bounds.getMinY(), content.getMaxHeight()));
//
            }
            case TOP_CENTER -> {
                content.setTranslateX(
                        bounds.getMinX() -
                                (center(bounds.getWidth() , content.getMaxWidth()))
                );
                content.setTranslateY(bounds.getMinY() - content.getMaxHeight());
            }
            case TOP_RIGHT -> {
                content.setTranslateX(bounds.getMinX() + bounds.getWidth());
                content.setTranslateY(bounds.getMinY() - content.getMaxHeight());
            }
            case RIGHT_CENTER -> {
                content.setTranslateX(bounds.getMinX() + bounds.getWidth());
                content.setTranslateY( (bounds.getMaxY() - bounds.getHeight()) -
                         content.getMaxHeight()/2);
            }
            case RIGHT_TOP -> {
                content.setTranslateX(bounds.getMinX() + bounds.getWidth());
                content.setTranslateY(bounds.getMinY());
            }
            case BOTTOM_RIGHT -> {
                content.setTranslateX((bounds.getMinX() + bounds.getWidth()));
                content.setTranslateY(bounds.getMinY() + bounds.getHeight());
            }

            case BOTTOM_CENTER -> {
                content.setTranslateX(bounds.getMinX() -
                        (center(bounds.getWidth(), content.getMaxWidth())) );
                content.setTranslateY(bounds.getMaxY());
            }

            // Done
            case BOTTOM_LEFT -> {
                content.setTranslateX(bounds.getMinX() - content.getMaxWidth());
                content.setTranslateY(bounds.getMaxY());
            }

            case LEFT_CENTER -> {
                content.setTranslateX(clamp(bounds.getMinX(), content.getMaxWidth()));
                content.setTranslateY(bounds.getMinY() + (
                        center(bounds.getWidth(), content.getMaxWidth())
                ));
            }

            case LEFT_TOP -> {
                content.setTranslateX(clamp(bounds.getMinX(), content.getMaxWidth()));
                content.setTranslateY(bounds.getMinY());
            }
        }

        content.setTranslateX(content.getTranslateX() + x);
        content.setTranslateY(content.getTranslateY() + y);
//
//        // se sair pelo canto esquerdo...
//        double whole = bounds.getMaxX();
//        double half = bounds.getMaxX() - (width / 2);
//        double max = wrapper.getWidth() - width;
//
//        System.out.println("width = " + content.getTranslateX());
//
//        double mx = content.localToScene(content.getLayoutBounds()).getMaxX(); // 1095
        Bounds bd = target.localToScene(content.getLayoutBounds());
        double md = Screen.getPrimary().getVisualBounds().getMaxX(); // 1366 - 400 = 966,


//
//        if (half > max) {
////            System.out.println("(content.getTranslateX() - ((i - max))) = " + (content.getTranslateX() - ((i - max))));
////            content.setTranslateX(content.getTranslateX() - ((half - max)) );
//        }

//        double cp = content.getTranslateX() + content.getMaxWidth();
//
//        if (cp > root.getWidth()) {
//            content.setTranslateX(
//                (content.getTranslateX() - ( cp - root.getWidth())) -10);
//        }

//        Timeline animation = new Timeline();
//        animation.getKeyFrames().setAll(
//                new KeyFrame(Duration.ZERO, new KeyValue(
//                        content.translateYProperty(), content.getTranslateY() - 10
//                )),
//                new KeyFrame(Duration.millis(100), new KeyValue(
//                        content.translateYProperty(), content.getTranslateY() + 10
//                ))
//        );
//        animation.play();
    }

    protected double clamp(double one, double two) {
        return Math.max(one, two) - Math.min(one, two);
    }

    protected double center(double one, double two) {
        return (clamp(one, two) / 2);
    }
}
