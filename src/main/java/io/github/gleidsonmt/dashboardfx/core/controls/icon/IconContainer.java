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

import javafx.scene.paint.Color;
import javafx.scene.shape.SVGPath;
import org.jetbrains.annotations.NotNull;

/**
 * @author Gleidson Neves da Silveira | gleidisonmt@gmail.com
 * Create on  23/11/2021
 */
public class IconContainer extends SVGPath {

    private String name;

    public IconContainer() {
        this(Icons.NONE, false);
    }

    public IconContainer(boolean needsUpdate) {
        this(Icons.NONE, needsUpdate);
    }

    public IconContainer(Icons icons) {
        this(icons, false);
    }

    public IconContainer(Icons icon, boolean needsUpdate) {
        this(icon, Color.GRAY, needsUpdate);
    }

    public IconContainer(Icons icon, Color color, boolean needsUpdate) {
        setContent(icon);
        getStyleClass().add("icon");
        setFill(color);
        name = icon.name();
    }

    public void setContent(@NotNull Icons icon) {
        setContent(icon.getContent());
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
