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

package io.github.gleidsonmt.dashboardfx.core.datatable;

import io.github.gleidsonmt.dashboardfx.model.Item;
import javafx.event.EventHandler;
import javafx.scene.control.TableRow;
import javafx.scene.control.TableView;
import javafx.scene.input.MouseEvent;
import javafx.util.Callback;

/**
 * @author Gleidson Neves da Silveira | gleidisonmt@gmail.com
 * Create on  23/10/2023
 */
public class DefaultDoubleClickRowFactory<T extends Item> implements Callback<TableView<T>, TableRow<T>> {

    private final EventHandler<MouseEvent> doubleClick;

    public DefaultDoubleClickRowFactory(EventHandler<MouseEvent> doubleClick) {
        this.doubleClick = doubleClick;
    }

    @Override
    public TableRow<T> call(TableView<T> param) {
        return new TableRow<>() {
            @Override
            protected void updateItem(T item, boolean empty) {
                super.updateItem(item, empty);

                if (item != null && !empty) {
                    setItem(item);
                    setOnMouseClicked(doubleClick);
                    item.checkedProperty().bind(selectedProperty());
                } else {
                    setItem(null);
                }
            }
        };

    }
}