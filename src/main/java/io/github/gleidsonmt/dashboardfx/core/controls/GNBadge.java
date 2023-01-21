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
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.css.*;
import javafx.geometry.Pos;
import javafx.scene.Cursor;
import javafx.scene.control.Control;
import javafx.scene.control.Label;
import javafx.scene.control.Skin;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.text.Text;
import org.jetbrains.annotations.ApiStatus;

import java.util.List;

public class GNBadge extends Control {

    private final Icons icon;
    private IntegerProperty numberOfNotifications = new SimpleIntegerProperty(this, "numberOfNotification",0);

    public GNBadge() {
        this(Icons.BADGE);
    }

    public GNBadge(Icons _icon) {
        this.icon = _icon;
        getStyleClass().add("gn-badge");
        setCursor(Cursor.HAND);
    }

    @Override
    protected Skin<?> createDefaultSkin() {
        return new GNBadgeSkin(icon, this);
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
}
