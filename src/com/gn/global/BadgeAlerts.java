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
package com.gn.global;

import com.gn.decorator.component.GNControl;
import com.jfoenix.controls.JFXBadge;
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIconView;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.layout.StackPane;

/**
 * @author Gleidson Neves da Silveira | gleidisonmt@gmail.com
 * Create on  01/04/2020
 */
public class BadgeAlerts extends GNControl {

    public BadgeAlerts(){
        this(null, null);
    }

    public BadgeAlerts(String text, String subtitle) {
        super(text, subtitle);
    }

    @Override
    public Node icon() {
        return null;
    }

    @Override
    public Node status() {
        return null;
    }

    @Override
    public Node action() {

        JFXBadge badgeM = new JFXBadge();
        badgeM.setPrefSize(35, 20);
        badgeM.setPosition(Pos.TOP_RIGHT);
        badgeM.getStyleClass().addAll("icon", "icon-danger");
        badgeM.setText("12");

        StackPane control = new StackPane();
        control.setAlignment(Pos.CENTER);
        control.getStyleClass().add("icon-alert");
        control.setStyle("-fx-padding : 0px;");
        control.setPrefSize(30,20);
        FontAwesomeIconView icon = new FontAwesomeIconView();
        icon.getStyleClass().add("icon");
        icon.setGlyphName("FLAG");
        icon.setSize("16");
        control.getChildren().add(icon);

        badgeM.setControl(control);

        return badgeM;
    }
}
