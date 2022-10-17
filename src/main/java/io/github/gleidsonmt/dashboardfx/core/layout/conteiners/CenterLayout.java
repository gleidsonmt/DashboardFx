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

package io.github.gleidsonmt.dashboardfx.core.layout.conteiners;

import io.github.gleidsonmt.dashboardfx.core.app.interfaces.Context;
import javafx.scene.Parent;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;


/**
 * @author Gleidson Neves da Silveira | gleidisonmt@gmail.com
 * Create on  15/02/2022
 */
public class CenterLayout extends VBox implements Context {

    private final ScrollPane    body        = new ScrollPane();

    public CenterLayout() {

        getChildren().add(0, body);
        body.setFitToWidth(true);
        body.setFitToHeight(true);
        VBox.setVgrow(body, Priority.ALWAYS);

        this.getStyleClass().add("center-layout");
        body.getStyleClass().add("center-body");
//        VBox.setMargin(body, new Insets(0, 0, 0,0));
//        body.getStyleClass().addAll("border", "border-t-1");

    }

    public void setBody(Parent body) {
        this.body.setContent(body);
    }

    public Parent getBody() {
        return this.body;
    }




}
