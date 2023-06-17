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

package io.github.gleidsonmt.dashboardfx.core.controls.skin;

import io.github.gleidsonmt.dashboardfx.core.controls.GNAvatar;
import javafx.scene.control.SkinBase;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Circle;

public class GNAvatarSkin extends SkinBase<GNAvatar> {
private final Circle avatarContainer = new Circle();

    public GNAvatarSkin(GNAvatar _control) {
        super(_control);
        this.getChildren().add(this.avatarContainer);
        this.avatarContainer.radiusProperty().bind(_control.radiusProperty());
        if (_control.getImage() != null) {
            this.avatarContainer.setFill(new ImagePattern(_control.getImage()));
        }

        _control.imageProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue != null) {
                this.avatarContainer.setFill(new ImagePattern(newValue));
            }

        });
    }

    protected void layoutChildren(double x, double y, double w, double h) {
        super.layoutChildren(x, y, w, h);
    }

    protected double computePrefWidth(double height, double topInset, double rightInset, double bottomInset, double leftInset) {
        return this.avatarContainer.prefWidth(height);
    }

    protected double computeMaxWidth(double height, double topInset, double rightInset, double bottomInset, double leftInset) {
        return this.avatarContainer.maxWidth(height);
    }

    protected double computeMaxHeight(double width, double topInset, double rightInset, double bottomInset, double leftInset) {
        return this.avatarContainer.maxHeight(width);
    }

    protected double computePrefHeight(double width, double topInset, double rightInset, double bottomInset, double leftInset) {
        return this.avatarContainer.prefHeight(width);
    }
}
