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

import javafx.geometry.Orientation;
import javafx.scene.control.Skin;
import javafx.scene.control.Slider;

/**
 * @author Gleidson Neves da Silveira | gleidisonmt@gmail.com
 * Create on  28/06/2023
 */
public class PlusSlider extends Slider {

    public PlusSlider() {

        this.setOrientation(Orientation.VERTICAL);

        this.getStyleClass().add("s-zoom");

        this.setMinSize(8, 40);
        this.setPrefSize(1,1);
        this.setMaxSize(1,1);

        this.setMin(1.0D);
        this.setMax(3.0D);
        this.setValue(1.0D);

        this.setBlockIncrement(0.1);

        }


    @Override
    protected Skin<?> createDefaultSkin() {
        return new PlusSliderSkin(this);
    }
}
