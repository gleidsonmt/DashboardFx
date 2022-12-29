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

import io.github.gleidsonmt.gncontrols.controls.GNIconButton;
import io.github.gleidsonmt.gncontrols.controls.GNTextBox;
import io.github.gleidsonmt.gncontrols.controls.skin.GNTextBoxBase;
import io.github.gleidsonmt.gncontrols.material.icon.Icons;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.css.*;
import javafx.geometry.Pos;
import javafx.scene.control.Control;
import javafx.scene.control.Label;
import javafx.scene.control.Skin;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.text.Text;
import org.jetbrains.annotations.ApiStatus;

import java.util.List;

public class GNBadge extends Control {

    private static final StyleablePropertyFactory<GNBadge> FACTORY =
            new StyleablePropertyFactory<>(Control.getClassCssMetaData());

    private final StyleableObjectProperty<Color> colorCircle =
            new SimpleStyleableObjectProperty<>(COLOR_CIRCLE, this, "colorCircle", Color.RED);

    private static final CssMetaData<GNBadge, Color> COLOR_CIRCLE =
            FACTORY.createColorCssMetaData("-gn-color-circle", f -> f.colorCircle);

    private Icons icon;

    public GNBadge(Icons _icon) {
        this.icon = _icon;
        getStyleClass().add("gn-badge");

    }

    @Override
    protected Skin<?> createDefaultSkin() {
        return new GNBadgeSkin(icon, this);
    }

    public static List<CssMetaData<? extends Styleable, ?>> getClassCssMetaData() {
        return FACTORY.getCssMetaData();
    }

    @Override
    public List<CssMetaData<? extends Styleable, ?>> getControlCssMetaData() {
        return FACTORY.getCssMetaData();
    }

    public Color getCircleColor() {
        return colorCircle.get();
    }

    public void setColorCircle(Color color) {
        this.colorCircle.setValue(color);
    }

    public StyleableObjectProperty<Color> colorCircleProperty() {
        return this.colorCircle;
    }
}
