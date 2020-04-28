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
package com.gn.global.factory.cells;

import com.gn.global.model.Model;
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIconView;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.scene.control.Button;
import javafx.scene.control.ContentDisplay;
import javafx.scene.layout.StackPane;
import org.controlsfx.control.PopOver;

/**
 * @author Gleidson Neves da Silveira | gleidisonmt@gmail.com
 * Create on  21/12/2019
 */
public class OptionCell<E extends Model>  extends Button implements DefaultCell<E> {

    private final ObjectProperty<E> item  = new SimpleObjectProperty<>();

    private final PopOver popup = new PopOver();

    public OptionCell(E item) {
//        FontAwesomeIconView glyphName="ENVELOPE" size="20" styleClass="icon" />

        setItem(item);

        FontAwesomeIconView icon = new FontAwesomeIconView();
        icon.setSize("20");
        icon.setGlyphName("COG");
        this.setGraphic(icon);

        this.getStyleClass().addAll("round", "btn-small","btn-action", "btn-infer");


        this.setContentDisplay(ContentDisplay.GRAPHIC_ONLY);

        this.setMinHeight(this.getPrefHeight());


        popup.setDetached(false);
        popup.setDetachable(false);
        popup.setCloseButtonEnabled(false);
        popup.setArrowSize(0);
        popup.setHeaderAlwaysVisible(false);
//        popup.setArrowLocation(PopOver.ArrowLocation.TOP_RIGHT);
        popup.setArrowLocation(PopOver.ArrowLocation.BOTTOM_LEFT);

        this.setOnMouseClicked(event -> {
            if(popup.isShowing()) popup.hide();
            popup.show(this);
        });

    }

    public void invitePopup(StackPane content){
        popup.setContentNode(content);
        popup.getScene().getStylesheets().add(getClass().getResource("/com/gn/theme/css/poplight.css").toExternalForm());
        popup.getRoot().getStylesheets().add(getClass().getResource("/com/gn/module/main/popitems.css").toExternalForm());
    }

    public PopOver getPopup() {
        return popup;
    }

    @Override
    public E getItem() {
        return this.item.get();
    }

    @Override
    public void setItem(E item) {
        this.item.set(item);
    }
}
