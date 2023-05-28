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
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.scene.Cursor;
import javafx.scene.control.Control;
import javafx.scene.control.Skin;

public class GNBadge extends Control {

    private final ObjectProperty<Icons> icon = new SimpleObjectProperty<>(this, "icon");
    private final IntegerProperty numberOfNotifications = new SimpleIntegerProperty(this, "numberOfNotification",0);

    public GNBadge() {
        this(Icons.BADGE, 0);
    }

    public GNBadge(Icons _icon) {
        this(_icon, 0);
    }

    public GNBadge(Icons _icon, int number) {
        this.icon.set(_icon);
        getStyleClass().add("gn-badge");
        setCursor(Cursor.HAND);
        numberOfNotifications.set(number);
    }

    @Override
    protected Skin<?> createDefaultSkin() {
        return new GNBadgeSkin( this);
    }

    public int getNumberOfNotifications() {
        return numberOfNotifications.get();
    }

    public IntegerProperty numberOfNotificationsProperty() {
        return numberOfNotifications;
    }

    public void setNumberOfNotifications(int numberOfNotifications) {
        this.numberOfNotifications.set(numberOfNotifications);
    }

    public Icons getIcon() {
        return icon.get();
    }

    public ObjectProperty<Icons> iconProperty() {
        return icon;
    }

    public void setIcon(Icons icon) {
        this.icon.set(icon);
    }
}
