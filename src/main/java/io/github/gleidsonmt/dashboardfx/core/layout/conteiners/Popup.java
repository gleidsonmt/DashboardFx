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

package io.github.gleidsonmt.dashboardfx.core.layout.conteiners;

import animatefx.animation.AnimationFX;
import animatefx.animation.Bounce;
import animatefx.animation.Shake;
import io.github.gleidsonmt.dashboardfx.core.layout.IWrapper;
import io.github.gleidsonmt.dashboardfx.core.layout.conteiners.creators.DeclarativeComponent;
import io.github.gleidsonmt.dashboardfx.core.layout.conteiners.layout.Direction;
import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.geometry.Bounds;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.Region;
import javafx.scene.paint.Color;
import javafx.stage.Screen;
import javafx.util.Duration;

import java.util.List;

/**
 * @author Gleidson Neves da Silveira | gleidisonmt@gmail.com
 * Version 0.0.1
 * Create on  23/01/2023
 */
public class Popup extends Dialog {

    private final IWrapper wrapper;

    public Popup(IWrapper wrapper) {
        super(wrapper);
        this.wrapper = wrapper;

    }


    public Popup background(IWrapper.WrapperBackgroundType type) {
        switch (type) {
            case NONE -> this.wrapper.setBackground(
                    new Background(
                            new BackgroundFill(
                                    Color.gray(0.5, 0.3),
                                    CornerRadii.EMPTY,
                                    Insets.EMPTY)
                    )
            );
            case GRAY -> this.wrapper.setBackground(
                    new Background(
                            new BackgroundFill(
                                    Color.TRANSPARENT,
                                    CornerRadii.EMPTY,
                                    Insets.EMPTY)
                    )
            );

        }
        return this;
    }

    @Override
    public void show() {
        super.show();
    }

    @Deprecated
    public void show(double x, double y) {

        this.wrapper.setAlignment(Pos.TOP_LEFT);

        if (this.content != null)
            this.content.setMaxSize(width, height);
        else return;

        this.content.setStyle(super.style);
        this.content.getStyleClass().addAll(super.styleClass);

        this.content.setTranslateX(x);
        this.content.setTranslateY(y);


        wrapper.toFront();
        this.content.toFront();
        wrapper.setOnMouseClicked(event -> close());
    }

    public void show(Direction direction, Node node) {
        Screen screen = Screen.getPrimary();
        if (this.content == null) return;

        this.wrapper.setBackground(
                new Background(
                        new BackgroundFill(
                                Color.TRANSPARENT,
                                CornerRadii.EMPTY,
                                Insets.EMPTY)
                )
        );

        this.wrapper.setAlignment(Pos.TOP_LEFT);
        this.content.setMinSize(width, height);
        this.content.setPrefSize(width, height);
        this.content.setMaxSize(width, height);

//        node.addEventHandler(MouseEvent.MOUSE_CLICKED, event -> {

        this.content.setStyle(super.style + """
                -fx-padding: 20;-fx-background-color : white;
                -fx-background-radius : 5px; -fx-border-radius : 5px;
                """);

        this.content.getStyleClass().addAll(super.styleClass);
        this.content.getStyleClass().add("depth-1");
        this.content.setPadding(new Insets(10));
        Bounds bounds = node.localToScene(node.getLayoutBounds());
        wrapper.toFront();
        this.content.setTranslateX(0);

        switch (direction) {
            case TOP_LEFT -> {
                this.content.setTranslateX(clamp(bounds.getMinX(), content.getMaxWidth() ) );
                this.content.setTranslateY(clamp(bounds.getMinY(), content.getMaxHeight()));
            }
            case TOP_CENTER -> {
                this.content.setTranslateX(
                        bounds.getMinX() -
                                (center(bounds.getWidth() , content.getMaxWidth()))
                );
                this.content.setTranslateY(bounds.getMinY() - content.getMaxHeight());
            }
            case TOP_RIGHT -> {
                this.content.setTranslateX(bounds.getMinX() + bounds.getWidth());
                this.content.setTranslateY(bounds.getMinY() - content.getMaxHeight());
            }
            case RIGHT_CENTER -> {
                this.content.setTranslateX(bounds.getMinX() + bounds.getWidth());
                this.content.setTranslateY( (bounds.getMinY() ) +
                        (center(bounds.getWidth(), content.getMaxHeight())/2));
            }
            case RIGHT_TOP -> {
                this.content.setTranslateX(bounds.getMinX() + bounds.getWidth());
                this.content.setTranslateY(bounds.getMinY());
            }
            case BOTTOM_RIGHT -> {
                this.content.setTranslateX((bounds.getMinX() + bounds.getWidth()) + paddingX);
                this.content.setTranslateY(bounds.getMinY() + bounds.getHeight());
            }

            case BOTTOM_CENTER -> {
                this.content.setTranslateX(bounds.getMinX() -
                        (center(bounds.getWidth(), content.getMaxWidth())) + content.getPadding().getRight());
                this.content.setTranslateY(bounds.getMaxY());
            }

            case BOTTOM_LEFT -> {
                this.content.setTranslateX(clamp(bounds.getMaxX(), content.getMaxWidth()) - content.getPadding().getRight());
                this.content.setTranslateY(bounds.getMaxY());
            }

            case LEFT_CENTER -> {
                this.content.setTranslateX(clamp(bounds.getMinX(), content.getMaxWidth()));
                this.content.setTranslateY(bounds.getMinY() + (
                        center(bounds.getWidth(), content.getMaxWidth())
                ));
            }

            case LEFT_TOP -> {
                this.content.setTranslateX(clamp(bounds.getMinX(), content.getMaxWidth()));
                this.content.setTranslateY(bounds.getMinY());
            }

        }

        // se sair pelo canto esquerdo...
        double whole = bounds.getMaxX();
        double half = bounds.getMaxX() - (width / 2);
        double max = wrapper.getWidth() - width;

        System.out.println("width = " + this.content.getTranslateX());

        double mx = content.localToScene(content.getLayoutBounds()).getMaxX(); // 1095
        double md = screen.getVisualBounds().getMaxX() - width; // 1366 - 400 = 966,

        if (half > max) {
//            System.out.println("(this.content.getTranslateX() - ((i - max))) = " + (this.content.getTranslateX() - ((i - max))));
//            this.content.setTranslateX(this.content.getTranslateX() - ((half - max)) );
        }

        Timeline animation = new Timeline();
        animation.getKeyFrames().setAll(
            new KeyFrame(Duration.ZERO, new KeyValue(
                  this.content.translateYProperty(), this.content.getTranslateY() - 10
            )),
            new KeyFrame(Duration.millis(100), new KeyValue(
                    this.content.translateYProperty(), this.content.getTranslateY() + 10
            ))
        );
        animation.play();
        wrapper.setOnMouseClicked(e -> close());
//        });

    }

    @Override
    public Popup size(double width, double height) {
        return (Popup) super.size(width, height);
    }

    @Override
    public Popup content(Region content) {
        return (Popup) super.content(content);
    }

    @Override
    public Popup pos(Pos _pos) {
        return (Popup) super.pos(_pos);
    }


    @Override
    public Popup style(String style) {
        super.style(style);
        return this;
    }

    @Override
    public Popup styleClass(String... styleClass) {
        super.styleClass = List.of(styleClass);
        return this;
    }

    @Override
    public Popup moveX(double x) {
        return (Popup) super.moveX(x);
    }

    @Override
    public void close() {

        Timeline animation = new Timeline();
        animation.getKeyFrames().setAll(
                new KeyFrame(Duration.ZERO, new KeyValue(
                        this.content.translateYProperty(), this.content.getTranslateY()
                )),
                new KeyFrame(Duration.millis(100), new KeyValue(
                        this.content.translateYProperty(), this.content.getTranslateY() - 10
                ))
        );
        animation.play();
        animation.setOnFinished(event -> {
            super.close();
            wrapper.reset();
        });
    }
}
