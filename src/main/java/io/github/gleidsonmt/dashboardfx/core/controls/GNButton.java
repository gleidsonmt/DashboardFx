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

import io.github.gleidsonmt.dashboardfx.core.controls.skin.GNButtonSkin;
import javafx.beans.DefaultProperty;
import javafx.css.*;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Control;
import javafx.scene.control.Skin;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;

import java.util.List;

/**
 * @author Gleidson Neves da Silveira | gleidisonmt@gmail.com
 * Create on  29/09/2022
 */
@DefaultProperty("control")
public class GNButton extends Button {

    private static final StyleablePropertyFactory<GNButton> FACTORY =
            new StyleablePropertyFactory<>(Control.getClassCssMetaData());

    private final StyleableProperty<Color> circleFill =
            new SimpleStyleableObjectProperty<>(CIRCLE_FILL, this, "circleFill", Color.WHITE);

    private static final CssMetaData<GNButton, Color> CIRCLE_FILL =
            FACTORY.createColorCssMetaData("-gn-circle-fill",
                    GNButton::circleFillProperty, Color.WHITE, true);

    public GNButton() {
        this(null);
    }

    public GNButton(String text) {
        setText(text == null ? "Button" : text);
        setPrefSize(100, 40);
        getStyleClass().add("gn-button");
        setAlignment(Pos.CENTER);
    }

    @Override
    protected Skin<?> createDefaultSkin() {
        return new GNButtonSkin(this);
    }

    public static List<CssMetaData<? extends Styleable, ?>> getClassCssMetaData() {
        return FACTORY.getCssMetaData();
    }

    @Override
    public List<CssMetaData<? extends Styleable, ?>> getControlCssMetaData() {
        return FACTORY.getCssMetaData();
    }

    public StyleableProperty<Color> circleFillProperty() {
        return circleFill;
    }

    public Color getCircleFill() {
        return circleFill.getValue();
    }
}