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

import io.github.gleidsonmt.dashboardfx.core.layout.Wrapper;
import io.github.gleidsonmt.dashboardfx.core.layout.conteiners.creators.DeclarativeComponent;
import io.github.gleidsonmt.dashboardfx.core.layout.conteiners.interfaces.AbsoluteWrapperContainer;
import javafx.geometry.Pos;
import javafx.scene.layout.StackPane;

public class Dialog extends DeclarativeComponent<Dialog> implements AbsoluteWrapperContainer {

    private StackPane content;
    private Pos pos;

    private int width = 350;
    private int height = 300;

    private final Wrapper wrapper;

    public Dialog(Wrapper wrapper) {
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

    @Override
    public void close() {
        wrapper.toBack();
        wrapper.setAlignment(Pos.CENTER);
        wrapper.getChildren().clear();
        wrapper.setOnMouseClicked(null);
    }
}
