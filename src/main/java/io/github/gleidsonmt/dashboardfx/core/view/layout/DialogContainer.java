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

package io.github.gleidsonmt.dashboardfx.core.view.layout;

import io.github.gleidsonmt.dashboardfx.core.view.layout.creators.DeclarativeComponent;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;

/**
 * @author Gleidson Neves da Silveira | gleidisonmt@gmail.com
 * Version 0.0.1
 * Create on  04/03/2023
 */
public class DialogContainer extends DeclarativeComponent<DialogContainer> {

    public DialogContainer(Node... nodes) {
        getStyleClass().add("container");
        getChildren().setAll(nodes);
        setPadding(new Insets(10));
        setAlignment(Pos.CENTER);
//        setMaxSize(400, 300);
    }

    public DialogContainer content(Node node) {
        this.getChildren().setAll(node);
        return this;
    }

}
