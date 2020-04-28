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
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.ContentDisplay;
import javafx.scene.control.Tooltip;
import javafx.scene.layout.HBox;
import javafx.scene.shape.SVGPath;

/**
 * @author Gleidson Neves da Silveira | gleidisonmt@gmail.com
 * Create on  13/01/2019
 * ActiveCell is a cell of table with button with actions view, edit and delete.
 */
@SuppressWarnings("unchecked")
public class ActionCell<E extends Model> extends HBox implements DefaultCell <E> {

    private final ObjectProperty<E> item  = new SimpleObjectProperty<>();

    private final Button btnView      = new Button("View");
    private final Button btnDelete    = new Button("Delete");
    private final Button btnEdit      = new Button("update");

    public ActionCell(E item){
        setItem(item);
        createContent();
    }

    private void createContent(){
        this.setAlignment(Pos.CENTER);

        String edit = "Edit";
        String view = "View";
        String delete = "Delete";

        Tooltip tolEdit = new Tooltip();
        tolEdit.setText(edit);
        Tooltip tolView = new Tooltip();
        tolView.setText(view);
        Tooltip tolDelete = new Tooltip();
        tolDelete.setText(delete);
        tolDelete.setStyle("-fx-text-fill : white;");
        tolEdit.setStyle("-fx-text-fill : white;");
        tolView.setStyle("-fx-text-fill : white;");

        SVGPath iconView = new SVGPath();
        SVGPath iconDelete = new SVGPath();
        SVGPath iconUpdate = new SVGPath();

        iconView.setContent("M12 4.5C7 4.5 2.73 7.61 1 12c1.73 4.39 6 7.5 11 7.5s9.27-3.11 11-7.5c-1.73-4.39-6-7.5-11-7.5zM12 17c-2.76 0-5-2.24-5-5s2.24-5 5-5 5 2.24 5 5-2.24 5-5 5zm0-8c-1.66 0-3 1.34-3 3s1.34 3 3 3 3-1.34 3-3-1.34-3-3-3z");

        iconUpdate.setContent("M3 17.25V21h3.75L17.81 9.94l-3.75-3.75L3 17.25zM20.71 7.04c.39-.39.39-1.02 0-1.41l-2.34-2.34c-.39-.39-1.02-.39-1.41 0l-1.83 1.83 3.75 3.75 1.83-1.83z");

        iconDelete.setContent("M6 19c0 1.1.9 2 2 2h8c1.1 0 2-.9 2-2V7H6v12zM19 4h-3.5l-1-1h-5l-1 1H5v2h14V4z");

        double sX = 0.8D;
        double sY = 0.8D;
        double maxWidth = 30D;

        iconView.setScaleX(sX);
        iconUpdate.setScaleX(sX);
        iconDelete.setScaleX(sX);

        iconView.setScaleY(sY);
        iconUpdate.setScaleY(sY);
        iconDelete.setScaleY(sY);

        btnView.setTooltip(tolView);
        btnEdit.setTooltip(tolEdit);
        btnDelete.setTooltip(tolDelete);

        btnView.setMinWidth(0);
        btnDelete.setMinWidth(0);
        btnEdit.setMinWidth(0);

        btnView.setMinHeight(0);
        btnDelete.setMinHeight(0);
        btnEdit.setMinHeight(0);

        btnView.setPrefWidth(maxWidth);
        btnDelete.setPrefWidth(maxWidth);
        btnEdit.setPrefWidth(maxWidth);

        btnView.getStyleClass().addAll("round", "btn-small","btn-action");
        btnEdit.getStyleClass().addAll("round","btn-small", "btn-action");
        btnDelete.getStyleClass().addAll("round","btn-small", "btn-action");

        btnView.setContentDisplay(ContentDisplay.GRAPHIC_ONLY);
        btnEdit.setContentDisplay(ContentDisplay.GRAPHIC_ONLY);
        btnDelete.setContentDisplay(ContentDisplay.GRAPHIC_ONLY);

        btnView.setGraphic(iconView);
        btnDelete.setGraphic(iconDelete);
        btnEdit.setGraphic(iconUpdate);

        this.setSpacing(5D);

        this.getChildren().addAll(btnView, btnEdit, btnDelete);

    }

    @Override
    public E getItem(){
        return this.item.get();
    }

    @Override
    public void setItem(E item) {
        this.item.set(item);
    }

}
