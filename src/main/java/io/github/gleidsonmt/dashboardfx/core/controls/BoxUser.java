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

package io.github.gleidsonmt.dashboardfx.core.controls;

import io.github.gleidsonmt.gncontrols.controls.GNAvatarStatus;
import io.github.gleidsonmt.gncontrols.controls.GNIconButton;
import io.github.gleidsonmt.gncontrols.material.icon.Icons;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Cursor;
import javafx.scene.image.Image;
import javafx.scene.layout.HBox;
import javafx.scene.text.Text;

import java.util.Objects;

public class BoxUser extends HBox {
    public BoxUser(String username) {

        this.setAlignment(Pos.CENTER);
        GNAvatarStatus avatarStatus = new GNAvatarStatus();
        avatarStatus.setImage(new Image(Objects.requireNonNull(getClass().getResource("/core.app/img/avatar.png")).toExternalForm()));
        Text user = new Text(username);
        avatarStatus.setMouseTransparent(true);
        user.getStyleClass().addAll( "text-14");
        GNIconButton btnArrow = new GNIconButton(Icons.ARROW_DROP_DOWN);
        btnArrow.setMaxWidth(20);
        btnArrow.setMinWidth(20);
        btnArrow.getStyleClass().addAll("btn-flat", "no-border");
//        this.getChildren().setAll( avatarStatus, user, btnArrow);
        this.getChildren().setAll(user, avatarStatus);
        avatarStatus.setPadding(new Insets(2, 5, 2, 2));
        avatarStatus.setRadius(15);
        this.setCursor(Cursor.HAND);

    }
}
