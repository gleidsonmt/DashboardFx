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

import io.github.gleidsonmt.dashboardfx.core.controls.skin.GNAvatarSkin;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.scene.control.Control;
import javafx.scene.control.Skin;
import javafx.scene.image.Image;

public class GNAvatar extends Control {
    private final ObjectProperty<Image> image;
    private final DoubleProperty radius;

    public GNAvatar() {
        this(20.0);
    }

    public GNAvatar(double _radius) {
        this.image = new SimpleObjectProperty();
        this.radius = new SimpleDoubleProperty();
        this.radius.set(_radius);
        this.setPrefSize(_radius, _radius);
    }

    public GNAvatar(Image image, double _radius) {
        this.image = new SimpleObjectProperty<>();
        this.radius = new SimpleDoubleProperty();
        this.radius.set(_radius);
        this.setPrefSize(_radius, _radius);
        this.setImage(image);
    }

    protected Skin<?> createDefaultSkin() {
        return new GNAvatarSkin(this);
    }

    public Image getImage() {
        return (Image)this.image.get();
    }

    public ObjectProperty<Image> imageProperty() {
        return this.image;
    }

    public void setImage(Image image) {
        this.image.set(image);
    }

    public double getRadius() {
        return this.radius.get();
    }

    public DoubleProperty radiusProperty() {
        return this.radius;
    }

    public void setRadius(double radius) {
        this.radius.set(radius);
    }
}
