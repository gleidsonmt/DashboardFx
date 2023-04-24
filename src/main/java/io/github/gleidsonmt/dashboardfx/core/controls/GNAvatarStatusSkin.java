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

package io.github.gleidsonmt.dashboardfx.core.controls;

import javafx.geometry.HPos;
import javafx.geometry.VPos;
import javafx.scene.control.SkinBase;
import javafx.scene.paint.Color;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Circle;

/**
 * @author Gleidson Neves da Silveira | gleidisonmt@gmail.com
 * Create on  29/09/2022
 */
public class GNAvatarStatusSkin extends SkinBase<GNAvatarStatus> {

    private final Circle avatarContainer = new Circle();
    private final Circle status = new Circle();

    public GNAvatarStatusSkin(GNAvatarStatus _control) {
        super(_control);

        getChildren().add(avatarContainer);
        avatarContainer.radiusProperty().bind(_control.radiusProperty());

        if (_control.getImage() != null) {
            avatarContainer.setFill(new ImagePattern(_control.getImage()));
        }

        _control.imageProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue != null) {
                avatarContainer.setFill(new ImagePattern(newValue));
            }
        });


        updateStatus(_control.getStatus());

        _control.statusProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue != null) {
               updateStatus(newValue);
            }
        });

        status.setRadius(5);

        status.setManaged(false);
        status.setStroke(Color.WHITE);
        getChildren().add(status);
    }

    private void updateStatus(Status value) {
        switch (value) {
            case AVAILABLE -> status.setStyle("-fx-fill : -mint;");
            case BUSY -> status.setStyle("-fx-fill : -danger;");
        }
    }

    @Override
    protected void layoutChildren(double x, double y, double w, double h) {
        super.layoutChildren(x, y, w, h);
        layoutInArea(status, x,y,w, h, -1, HPos.RIGHT, VPos.BOTTOM);
    }

    @Override
    protected double computePrefWidth(double height, double topInset, double rightInset, double bottomInset, double leftInset) {
        return avatarContainer.prefWidth(height);
    }

    @Override
    protected double computeMaxWidth(double height, double topInset, double rightInset, double bottomInset, double leftInset) {
        return avatarContainer.maxWidth(height);
    }

    @Override
    protected double computeMaxHeight(double width, double topInset, double rightInset, double bottomInset, double leftInset) {
        return avatarContainer.maxHeight(width);
    }

    @Override
    protected double computePrefHeight(double width, double topInset, double rightInset, double bottomInset, double leftInset) {
        return avatarContainer.prefHeight(width);
    }
}
