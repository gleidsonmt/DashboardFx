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

import io.github.gleidsonmt.dashboardfx.core.controls.GNAvatarStatus;
import io.github.gleidsonmt.dashboardfx.core.controls.GNIconButton;
import io.github.gleidsonmt.dashboardfx.core.controls.icon.Icons;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Cursor;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.layout.HBox;
import javafx.scene.text.Text;

public class BoxUser extends HBox {
    public BoxUser(String username, String av) {
        this.setId("box-user");
        this.setAlignment(Pos.CENTER);
        GNAvatarStatus avatarStatus = new GNAvatarStatus();
        avatarStatus.setImage(new Image(av));
//        Label user = new Label(username);
        avatarStatus.setMouseTransparent(true);
//        user.getStyleClass().addAll( "text-14");
        GNIconButton btnArrow = new GNIconButton(Icons.ARROW_DROP_DOWN);
        btnArrow.setMaxWidth(20);
        btnArrow.setMinWidth(20);
        btnArrow.getStyleClass().addAll("btn-flat", "no-border");
//        this.getChildren().setAll( avatarStatus);
        this.getChildren().setAll(avatarStatus, btnArrow);
        avatarStatus.setPadding(new Insets(2, 5, 8, 2));
//        user.setPadding(new Insets(0, 5, 15, 2));
        avatarStatus.setRadius(14);
        this.setCursor(Cursor.HAND);

    }
}
