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

package io.github.gleidsonmt.dashboardfx.core.layout.conteiners.creators;

import io.github.gleidsonmt.dashboardfx.core.controls.GNBadge;
import io.github.gleidsonmt.dashboardfx.core.layout.conteiners.layout.Direction;
import javafx.scene.Node;
import javafx.scene.control.Control;
import javafx.scene.layout.Region;
import org.jetbrains.annotations.ApiStatus;

import java.util.List;

@ApiStatus.Internal
@SuppressWarnings("unchecked")
public abstract class DeclarativeComponent<T>  extends Node {

    protected String style;
    protected List<String> styleClass = List.of();

    public T style(String style) {
        this.style = style;
        return (T) this;
    }

    public T styleClass(String... styleClass) {
        this.styleClass = List.of(styleClass);
        return (T) this;
    }

    public abstract T size(double width, double height);

}
