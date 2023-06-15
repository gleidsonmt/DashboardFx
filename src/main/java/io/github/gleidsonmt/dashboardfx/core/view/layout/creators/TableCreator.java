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

package io.github.gleidsonmt.dashboardfx.core.view.layout.creators;

import io.github.gleidsonmt.dashboardfx.core.Context;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

/**
 * @author Gleidson Neves da Silveira | gleidisonmt@gmail.com
 * Create on  20/05/2023
 */
public class TableCreator<T> implements BuildCreator {

    private TableView<T> table = new TableView<>();
    protected ObservableList<T> items = FXCollections.observableArrayList();
    private final Context context;

    public TableCreator(Context context) {
        this.context = context;
        table.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
        table.setEditable(false);

    }

    public TableCreator<T> size(double w, double h) {
        table.setPrefSize(w, h);
        table.setMinSize(w, h);
//        table.setMaxSize(w, h);
        return this;
    }
    public TableCreator<T> column(String name) {
        table.getColumns().add(createTableColumn(name));
        return this;
    }

    public TableCreator<T> columns(String... names) {
        for (String name : names) {
            table.getColumns().add(createTableColumn(name));
        }
        return this;
    }

    @SafeVarargs
    public final TableCreator<T> data(T... items) {
        this.items.setAll(items);
        table.setMinHeight((items.length * 45) + 50);
        return this;
    }

    private TableColumn<T, String> createTableColumn(String name) {
        TableColumn<T, String> column =  new TableColumn<>(name.substring(0,1).toUpperCase() + name.substring(1));
        column.setCellValueFactory(new PropertyValueFactory<>(name));
        return column;
    }

    @Override
    public TableCreator<T> build() {
        table.setItems(items);
        return this;
    }

    @Override
    public TableView<T> getRoot() {
        return table;
    }
}
