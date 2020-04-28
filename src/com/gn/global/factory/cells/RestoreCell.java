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
import javafx.scene.control.TableView;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.SVGPath;

/**
 * @author Gleidson Neves da Silveira | gleidisonmt@gmail.com
 * Create on  30/07/2019
 */
public class RestoreCell<E extends Model> extends HBox implements DefaultCell<E> {

    private final Button btnRestore       = new Button("restore");
    private final ObjectProperty<E> item  = new SimpleObjectProperty<>();

    public RestoreCell(TableView<E> tableView, E item){
        setItem(item);
        createContent();
    }

    private void createContent(){
        SVGPath graphic = new SVGPath();
        graphic.setFill(Color.WHITE);
        graphic.setContent("M13 3c-4.97 0-9 4.03-9 9H1l3.89 3.89.07.14L9 12H6c0-3.87 3.13-7 7-7s7 3.13 7 7-3.13 7-7 7c-1.93 0-3.68-.79-4.94-2.06l-1.42 1.42C8.27 19.99 10.51 21 13 21c4.97 0 9-4.03 9-9s-4.03-9-9-9zm-1 5v5l4.28 2.54.72-1.21-3.5-2.08V8H12z");

        btnRestore.setContentDisplay(ContentDisplay.GRAPHIC_ONLY);
        btnRestore.setGraphic(graphic);
        btnRestore.setAlignment(Pos.CENTER);
        btnRestore.getStyleClass().addAll("btn-warning");

        double sX = 0.8D;
        double sY = 0.8D;
        double maxWidth = 30D;

        btnRestore.setMinWidth(maxWidth);
        btnRestore.setMaxWidth(maxWidth);
        btnRestore.setMinHeight(maxWidth);
        btnRestore.setMaxHeight(maxWidth);

        graphic.setScaleX(sX);
        graphic.setScaleY(sY);

        btnRestore.getStyleClass().addAll("round","btn-small", "btn-action");

        this.getChildren().add(btnRestore);
    }

    @Override
    public E getItem() {
        return item.get();
    }

    @Override
    public void setItem(E item) {
        this.item.set(item);
    }

}
