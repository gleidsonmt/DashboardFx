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

package io.github.gleidsonmt.dashboardfx.core.picture_selector;

import javafx.geometry.Pos;
import javafx.scene.control.Slider;
import javafx.scene.control.skin.SliderSkin;
import javafx.scene.layout.StackPane;
import javafx.scene.shape.SVGPath;

/**
 * @author Gleidson Neves da Silveira | gleidisonmt@gmail.com
 * Create on  29/06/2023
 */
public class PlusSliderSkin  extends SliderSkin {

    public PlusSliderSkin(Slider control) {
            super(control);

            StackPane thumb = (StackPane) control.lookup(".thumb");

            SVGPath icon = new SVGPath();
            icon.setContent("M9.188 15.083v-4.291H4.896V9.167h4.292V4.875h1.624v4.292h4.292v1.625h-4.292v4.291Z");
            icon.getStyleClass().add("icon");

            thumb.setAlignment(Pos.CENTER);

            thumb.getChildren().add(icon);


            control.valueProperty().addListener((observable, oldValue, newValue) -> {
                if (newValue.doubleValue() >= oldValue.doubleValue()) {
                    icon.setContent("M9.188 15.083v-4.291H4.896V9.167h4.292V4.875h1.624v4.292h4.292v1.625h-4.292v4.291Z");
                } else {
                    icon.setContent("M4.875 10.792V9.167h10.25v1.625Z");
                }
            });
        }
}
