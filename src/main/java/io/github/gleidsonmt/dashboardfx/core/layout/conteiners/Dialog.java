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

import io.github.gleidsonmt.dashboardfx.core.layout.IWrapper;
import io.github.gleidsonmt.dashboardfx.core.layout.conteiners.creators.DeclarativeComponent;
import io.github.gleidsonmt.dashboardfx.core.layout.conteiners.interfaces.AbsoluteWrapperContainer;
import io.github.gleidsonmt.dashboardfx.core.layout.conteiners.layout.Direction;
import javafx.geometry.Bounds;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Control;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.StackPane;
import org.jetbrains.annotations.TestOnly;

public class Dialog extends DeclarativeComponent<Dialog> implements AbsoluteWrapperContainer {

    private StackPane content;
    private Pos pos;

    private double width = 350;
    private double height = 300;

    private final IWrapper wrapper;

    private double paddingX = 0;
    private double paddingY = 0;

    public Dialog(IWrapper wrapper) {
        this.wrapper = wrapper;

    }

    public Dialog content(StackPane content) {
        this.content = content;
        this.content.setStyle("-fx-background-color : -fx-foreground;");
        wrapper.getChildren().setAll(content);
        return this;
    }

    @Override
    public Dialog pos(Pos _pos) {
        this.pos = _pos;
        return this;
    }

    @Override
    public Dialog size(double width, double height) {
        this.width = width;
        this.height = height;
        return this;
    }

    @TestOnly
    public Dialog moveX(double x) {
        this.paddingX = x;
        return this;
    }

    @Override
    public void show() {
        if (pos == null) {
            pos = Pos.CENTER;
            wrapper.setAlignment(pos);
        }

        if (this.content != null)
            this.content.setMaxSize(width, height);

        wrapper.toFront();
        wrapper.setOnMouseClicked(event -> close());
    }

    public void show(double x, double y) {

        this.wrapper.setAlignment(Pos.TOP_LEFT);

        if (this.content != null)
            this.content.setMaxSize(width, height);
        else return;

        this.content.setTranslateX(x);
        this.content.setTranslateY(y);


        wrapper.toFront();
        this.content.toFront();
        wrapper.setOnMouseClicked(event -> close());
    }

    public void show(Node node) {

        Bounds bounds = node.localToScene(node.getLayoutBounds());

        this.wrapper.setAlignment(Pos.TOP_LEFT);

        if (this.content != null) {
            this.content.setMinSize(width, height);
            this.content.setPrefSize(width, height);
            this.content.setMaxSize(width, height);
        }

        else return;

        this.content.setTranslateX(bounds.getMaxX() - content.getMaxWidth());
        this.content.setTranslateY(bounds.getMaxY());

        wrapper.toFront();
        this.content.toFront();
        wrapper.setOnMouseClicked(event -> close());
    }

    @Override
    public Dialog style(String style) {
        return super.style(style);
    }

    public void contextDialog(Direction direction, Node node) {
//        this.content = content;
//
//        if (!this.wrapper.getChildren().contains(this.content)) {
//            this.wrapper.getChildren().add(this.content);
//        }

        this.wrapper.setAlignment(Pos.TOP_LEFT);


        node.addEventHandler(MouseEvent.MOUSE_CLICKED, event -> {

            Bounds bounds = node.localToScene(node.getLayoutBounds());
//            Bounds contentBound = content.localToScene(content.getLayoutBounds());

            wrapper.toFront();
//            this.content.toFront();


            if (this.content != null) {
                this.content.setMinSize(width, height);
                this.content.setPrefSize(width, height);
                this.content.setMaxSize(width, height);
            }

            System.out.println("bounds = " + bounds);

    //        else return;

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
                            (center(bounds.getWidth(), content.getMaxWidth())));
                    this.content.setTranslateY(bounds.getMaxY());
                }

                case BOTTOM_LEFT -> {
                    this.content.setTranslateX(clamp(bounds.getMinX(), content.getMaxWidth()));
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

            wrapper.setOnMouseClicked(e -> close());
        });

    }

    private double clamp(double one, double two) {
        return Math.max(one, two) - Math.min(one, two);
    }

    private double center(double one, double two) {
        return (clamp(one, two) / 2);
    }

    @Override
    public void close() {
        wrapper.toBack();
//        wrapper.setAlignment(Pos.CENTER);
//        wrapper.getChildren().clear();
//        wrapper.setOnMouseClicked(null);
    }
}
