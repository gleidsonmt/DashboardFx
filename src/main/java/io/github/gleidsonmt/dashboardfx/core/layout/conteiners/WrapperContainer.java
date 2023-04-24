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

//import animatefx.animation.AnimationFX;
//import animatefx.animation.Pulse;
import io.github.gleidsonmt.dashboardfx.core.layout.Wrapper;
import io.github.gleidsonmt.dashboardfx.core.layout.conteiners.creators.DeclarativeComponent;
import io.github.gleidsonmt.dashboardfx.core.layout.conteiners.interfaces.AbsoluteWrapperContainer;
import javafx.geometry.Pos;
import javafx.scene.layout.*;
import org.jetbrains.annotations.TestOnly;

public class WrapperContainer
       implements AbsoluteWrapperContainer {

    protected Region content;
    private Pos pos;
    protected double width = 350;
    protected double height = 300;
    private final Wrapper wrapper;
    protected double paddingX = 0;
    private double paddingY = 0;

    public WrapperContainer(Wrapper wrapper) {
        this.wrapper = wrapper;
        Region r = new Region();
    }

    public WrapperContainer content(Region content) {
        this.content = content;

        wrapper.getChildren().setAll(content);
        return this;
    }

    @Override
    public WrapperContainer pos(Pos _pos) {
        this.pos = _pos;
        return this;
    }

    @TestOnly
    public WrapperContainer moveX(double x) {
        this.paddingX = x;
        return this;
    }


    @Override
    public void show() {
        if (pos == null) {
            pos = Pos.CENTER;
            wrapper.setAlignment(pos);
        }

        wrapper.toFront();
        wrapper.setOnMouseClicked(event -> close());

//        AnimationFX animation = new Pulse(content);
//        animation.setSpeed(1.8);
//        animation.play();
    }

    @Override
    public void close() {

//        AnimationFX animation = new Pulse(content);
//        animation.setSpeed(1.8);
//        animation.play();
//
//        animation.getTimeline().setOnFinished(event -> {
//            wrapper.toBack();
//            wrapper.setAlignment(Pos.CENTER);
//        });
//        wrapper.setOnMouseClicked(null);
    }

    protected double clamp(double one, double two) {
        return Math.max(one, two) - Math.min(one, two);
    }

    protected double center(double one, double two) {
        return (clamp(one, two) / 2);
    }
}
