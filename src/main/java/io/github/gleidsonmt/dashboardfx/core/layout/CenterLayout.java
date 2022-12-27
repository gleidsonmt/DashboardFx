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
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.geometry.VPos;
import javafx.scene.Parent;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.image.Image;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;


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


        GNAvatarStatus avatarStatus = new GNAvatarStatus();
        avatarStatus.setImage(new Image("avatar.jpg"));
        avatarStatus.setPadding(new Insets(2));
        avatarStatus.setRadius(15);

        GNIconButton btnArrow = new GNIconButton(Icons.ARROW_DROP_DOWN);
        btnArrow.getStyleClass().addAll("btn-flat", "no-border");

        HBox box = new HBox();
        box.getChildren().addAll(avatarStatus, btnArrow);
        box.setAlignment(Pos.CENTER_RIGHT);


        GNBadge notification = new GNBadge(Icons.NOTIFICATIONS);
        GNBadge sms = new GNBadge(Icons.SMS);

        bar.addInRight(box);
        bar.addInRight(sms, notification);
//        bar.addInRight(notification);
//        bar.addInRight(sms);

        Label lblTitle = new Label("Dashboard");
        lblTitle.setPadding(new Insets(0,0,0,5));
        lblTitle.getStyleClass().addAll("title", "text-14");
        bar.addInLeft(lblTitle);

//        GridPane.setConstraints(badge, 1,0,1,1, HPos.RIGHT, VPos.CENTER, Priority.SOMETIMES, Priority.SOMETIMES);
//        GridPane.setConstraints(box, 2,0,1,1, HPos.RIGHT, VPos.CENTER, Priority.SOMETIMES, Priority.SOMETIMES);

        getChildren().add(0, body);
        getChildren().add(0, bar);
        body.setFitToWidth(true);
        body.setFitToHeight(true);
        VBox.setVgrow(body, Priority.ALWAYS);

//        VBox.setMargin(body, new Insets(30,0,0,0));

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
