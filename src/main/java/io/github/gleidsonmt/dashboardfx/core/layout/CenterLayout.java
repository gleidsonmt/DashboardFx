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

package io.github.gleidsonmt.dashboardfx.core.layout;

import io.github.gleidsonmt.dashboardfx.core.controls.GNBadge;
import io.github.gleidsonmt.gncontrols.controls.GNAvatarStatus;
import io.github.gleidsonmt.gncontrols.controls.GNIconButton;
import io.github.gleidsonmt.gncontrols.material.icon.Icons;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.ListChangeListener;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.geometry.VPos;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.image.Image;
import javafx.scene.layout.*;


/**
 * @author Gleidson Neves da Silveira | gleidisonmt@gmail.com
 * Create on  15/02/2022
 */
public class CenterLayout extends VBox {
    private final ScrollPane    body        = new ScrollPane();
    private final Bar      bar = new Bar();

    public CenterLayout() {

        this.getStyleClass().add("center-layout");
        bar.getStyleClass().add("bar");
        bar.setMinHeight(30);

        bar.hasChild.addListener((observable, oldValue, newValue) -> {
            if (newValue) getChildren().add(0, bar);
            else getChildren().remove(bar);
        });

        getChildren().add(0, body);
        body.setFitToWidth(true);
        body.setFitToHeight(true);
        VBox.setVgrow(body, Priority.ALWAYS);
        this.getStyleClass().add("center-layout");
        body.getStyleClass().add("center-body");

    }

    public void setBody(Parent body) {
        this.body.setContent(body);
    }

    public Parent getBody() {
        return this.body;
    }

    public Bar getBar() {
        return bar;
    }

}
