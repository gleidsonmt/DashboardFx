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

import io.github.gleidsonmt.dashboardfx.core.app.interfaces.Wrapper;
import io.github.gleidsonmt.dashboardfx.core.layout.IWrapper;
import io.github.gleidsonmt.dashboardfx.core.layout.conteiners.creators.DeclarativeComponent;
import io.github.gleidsonmt.dashboardfx.core.layout.conteiners.interfaces.AbsoluteWrapperContainer;
import io.github.gleidsonmt.dashboardfx.core.layout.conteiners.layout.Direction;
import javafx.geometry.Bounds;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Control;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.stage.Screen;
import org.jetbrains.annotations.TestOnly;

public class Dialog
       extends DeclarativeComponent<Dialog>
       implements AbsoluteWrapperContainer {

    protected Region content;
    private Pos pos;
    protected double width = 350;
    protected double height = 300;
    private final IWrapper wrapper;
    protected double paddingX = 0;
    private double paddingY = 0;

    public Dialog(IWrapper wrapper) {
        this.wrapper = wrapper;
        Region r = new Region();
    }

    public Dialog content(Region content) {
        this.content = content;
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

        this.content.setStyle(super.style);
        this.content.getStyleClass().addAll(super.styleClass);

        wrapper.toFront();
        wrapper.setOnMouseClicked(event -> close());
    }

    protected double clamp(double one, double two) {
        return Math.max(one, two) - Math.min(one, two);
    }

    protected double center(double one, double two) {
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
