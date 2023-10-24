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
package io.github.gleidsonmt.dashboardfx.core.controls.icon;

import javafx.beans.property.SimpleObjectProperty;
import javafx.scene.Group;
import javafx.scene.paint.Color;
import javafx.scene.shape.SVGPath;
import org.jetbrains.annotations.NotNull;

/**
 * @author Gleidson Neves da Silveira | gleidisonmt@gmail.com
 * Create on  23/11/2021
 */
public class IconContainer extends Group {

    private String name;
    private SimpleObjectProperty<Icons> icon = new SimpleObjectProperty<>();
    private SVGPath path = new SVGPath();

    public IconContainer() {
        this(Icons.NONE, false);
    }

    public IconContainer(boolean needsResize) {
        this(Icons.NONE, needsResize);
    }

    public IconContainer(Icons icons) {
        this(icons, false);
    }

    public IconContainer(Icons icon, boolean needsResize) {
        this(icon, Color.GRAY, needsResize);
    }

    public IconContainer(Icons icon, Color color, boolean needsResize) {
        this.icon.set(icon);
        if (needsResize) {
            setContentAndResize(icon);
        } else {
            setContent(icon);
        }

        this.getStyleClass().add("icon-container");
        path.getStyleClass().add("icon");
//        setFill(color);
        name = icon.name();

        this.icon.addListener((observable, oldValue, newValue) -> {
            if (newValue != null) {
                setContent(newValue);
            }
        });
    }

    public Icons getIcon() {
        return icon.get();
    }

    public SimpleObjectProperty<Icons> iconProperty() {
        return icon;
    }

    public void setIcon(Icons icon) {
        this.icon.set(icon);
    }

    public void setContent(@NotNull Icons icon) {
        path.setContent(icon.getContent());
        if (!this.getChildren().contains(path)) {
            this.getChildren().add(path);
        }
        name = icon.name();
    }

    private void setContentAndResize(@NotNull Icons icon) {
        path.setContent(icon.getContent());
        this.getChildren().add(path);
        path.setScaleX(0.023);
        path.setScaleY(0.023);
        name = icon.name();
    }


    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        final StringBuffer sb = new StringBuffer("IconContainer{");
        sb.append("name='").append(name).append('\'');
        sb.append('}');
        return sb.toString();
    }
}
