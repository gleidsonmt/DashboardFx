/*
 * Copyright (C) Gleidson Neves da Silveira
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package com.gn.global.factory.badges;

import com.gn.global.plugin.ViewManager;
import com.gn.global.util.PopupCreator;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;
import javafx.scene.shape.SVGPath;

/**
 * @author Gleidson Neves da Silveira | gleidisonmt@gmail.com
 * Create on  01/04/2020
 */
public class BadgeSettings extends Button {

    public BadgeSettings() {
        SVGPath icon = new SVGPath();
        icon.setContent("M12 8c1.1 0 2-.9 2-2s-.9-2-2-2-2 .9-2 2 .9 2 2 2zm0 2c-1.1 0-2 .9-2 2s.9 2 2 2 2-.9 2-2-.9-2-2-2zm0 6c-1.1 0-2 .9-2 2s.9 2 2 2 2-.9 2-2-.9-2-2-2z");
        icon.getStyleClass().add("icon");

        this.setGraphic(icon);
        this.setPrefWidth(30);
        this.getStyleClass().addAll("btn-transparent", "round");

        this.setOnMouseClicked(event -> {
            VBox content = (VBox) ViewManager.INSTANCE.get("settings").getRoot();
            PopupCreator.INSTANCE.createDrawerRight(content);
        });
    }
}
