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

package io.github.gleidsonmt.dashboardfx.core.app.material.controls;

import io.github.gleidsonmt.dashboardfx.core.app.services.Context;
import io.github.gleidsonmt.dashboardfx.views.BlockCode;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;

public class CenterView extends VBox {

    private VBox title = new VBox();
    private VBox body  = new VBox();

    public CenterView(Context context, Node node, BlockCode java, BlockCode fxml) {
        java.setMaxHeight(100);
        fxml.setMaxHeight(100);
        title.setAlignment(Pos.CENTER);
        VBox.setVgrow(title, Priority.ALWAYS);
        title.getChildren().addAll(node);
        getChildren().setAll(title, body);
        this.setAlignment(Pos.BOTTOM_CENTER);

        TabPane tabPane = new TabPane();
        tabPane.getStyleClass().addAll("border-t-1", "border-light-gray-2");
        Tab javaCode = new Tab("Java Code");
        javaCode.setContent(java);
        Tab tabFxml = new Tab("FXML Markup");
        tabFxml.setContent(fxml);
        tabPane.getTabs().setAll(javaCode, tabFxml);
        body.getChildren().setAll(tabPane);
    }
}
