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

package io.github.gleidsonmt.dashboardfx.core.view;

import io.github.gleidsonmt.dashboardfx.core.interfaces.ActionView;
import io.github.gleidsonmt.dashboardfx.core.view.layout.Container;
import io.github.gleidsonmt.dashboardfx.core.view.layout.creators.BuildCreator;
import javafx.scene.Node;
import javafx.scene.Parent;
import org.jetbrains.annotations.NotNull;

/**
 * @author Gleidson Neves da Silveira | gleidisonmt@gmail.com
 * Version 0.0.1
 * Create on  13/05/2023
 */
public class SimpleView extends ActionView implements View {

    private final String name;
    private final Parent container;

    public SimpleView(String name, Node node) {
        this.name = name;
        container = new Container(node);
    }

    public SimpleView(String name, @NotNull BuildCreator node) {
        this.name = name;
        container = new Container(node.getRoot());
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public Parent getRoot() {
        return container;
    }

    @Override
    public ActionView getController() {
        return this;
    }
}
