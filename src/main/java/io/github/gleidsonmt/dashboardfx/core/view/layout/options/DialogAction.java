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

package io.github.gleidsonmt.dashboardfx.core.view.layout.options;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.ButtonType;

public final class DialogAction {

    private final String name;
    private final EventHandler<ActionEvent> action;
    private final ButtonType buttonType;

    public DialogAction(String name, EventHandler<ActionEvent> action) {
        this(name, ButtonType.OK, action);
    }

    public DialogAction(String name, ButtonType buttonType, EventHandler<ActionEvent> action) {
        this.buttonType = buttonType;
        this.name = name;
        this.action = action;
    }

    public String getName() {
        return name;
    }

    public EventHandler<ActionEvent> getAction() {
        return action;
    }

    public ButtonType getButtonType() {
        return buttonType;
    }
}
