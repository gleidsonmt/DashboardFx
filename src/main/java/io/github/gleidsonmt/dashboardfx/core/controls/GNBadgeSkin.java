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

import io.github.gleidsonmt.dashboardfx.core.controls.icon.Icons;
import javafx.beans.binding.Bindings;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.geometry.VPos;
import javafx.scene.control.Label;
import javafx.scene.control.SkinBase;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.paint.Color;

public class GNBadgeSkin extends SkinBase<GNBadge> {

    private final Label lblInfo = new Label();

    public GNBadgeSkin( GNBadge control) {
        super(control);

        GNIconButton icon = new GNIconButton(control.getIcon());
        icon.getStyleClass().addAll( "icon", "no-border", "transparent");

        lblInfo.setAlignment(Pos.CENTER);
        lblInfo.getStyleClass().add("box");
        lblInfo.setMinSize(20,20);
        lblInfo.setPrefSize(10,10);
        lblInfo.setMaxSize(10,10);
        lblInfo.setMouseTransparent(true);
        icon.setMouseTransparent(true);

        lblInfo.textProperty().bind(Bindings.convert(control.numberOfNotificationsProperty()));

        this.getChildren().setAll(icon, lblInfo);
        updateBoxColor(Color.GRAY);

//        control.setStyle("-gn-color-circle: blue;");
//        this.setAlignment(Pos.TOP_RIGHT);
    }

    private void updateBoxColor(Color color) {
        lblInfo.setBackground(new Background(new BackgroundFill(
               color, new CornerRadii(100), Insets.EMPTY
        )));
    }

    @Override
    protected void layoutChildren(double x, double y, double w, double h) {
        super.layoutChildren(x, y, w, h);
        layoutInArea(lblInfo, x,y,w,h, 0, HPos.RIGHT, VPos.TOP);
    }

    @Override
    protected double computeMaxHeight(double width, double topInset, double rightInset, double bottomInset, double leftInset) {
        return 40;
    }

//    @Override
//    protected double computeMaxWidth(double height, double topInset, double rightInset, double bottomInset, double leftInset) {
//        return computePrefWidth(height, topInset, rightInset, bottomInset, leftInset);
//    }

//    @Override
//    protected double computeMinHeight(double width, double topInset, double rightInset, double bottomInset, double leftInset) {
//        return computePrefWidth(width, topInset, rightInset, bottomInset, leftInset);
//    }
//
//    @Override
//    protected double computeMinWidth(double height, double topInset, double rightInset, double bottomInset, double leftInset) {
//        return computePrefWidth(height, topInset, rightInset, bottomInset, leftInset);
//    }
}
