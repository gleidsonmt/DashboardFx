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

package io.github.gleidsonmt.dashboardfx.views;

import io.github.gleidsonmt.dashboardfx.core.Context;
import io.github.gleidsonmt.dashboardfx.core.controls.icon.IconContainer;
import io.github.gleidsonmt.dashboardfx.core.controls.icon.Icons;
import io.github.gleidsonmt.dashboardfx.core.datatable.DataTable;
import io.github.gleidsonmt.dashboardfx.core.interfaces.ActionView;
import io.github.gleidsonmt.dashboardfx.core.view.View;
import io.github.gleidsonmt.dashboardfx.core.view.layout.options.SnackColors;
import io.github.gleidsonmt.dashboardfx.model.Developer;
import io.github.gleidsonmt.dashboardfx.tasks.CreateDevelopers;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.concurrent.Task;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.control.TableColumn;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.StackPane;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author Gleidson Neves da Silveira | gleidisonmt@gmail.com
 * Create on  21/10/2023
 */
public class DataTableView extends ActionView implements View {

    private StackPane root = new StackPane();
    private DataTable dataTable;
    public DataTableView(Context context) {
        super(context);

        TableColumn<Developer, String> nameColumn = new TableColumn<>("Name");
        nameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));

        ObservableList<Developer> items = FXCollections.observableArrayList();
        Task<ObservableList<Developer>> task = new CreateDevelopers(items);

        AtomicInteger index = new AtomicInteger();

        dataTable = new DataTable<Developer>()
                .columns(nameColumn)
                .task(task)
                .deleteEvent(event -> {
//                    items.remove(index.getAndIncrement());
                    if (dataTable.getSelectedItems().isEmpty()) {
                        context.createSnackBar()
                                .color(SnackColors.WARNING)
                                .icon(new IconContainer(Icons.WARNING))
                                .message("No items selected")
                                .show(Pos.CENTER, new Insets(10,250,10,10));
                    } else {
                        items.removeAll(dataTable.getSelectedItems());
                    }
//                    System.out.println("index.getAndIncrement() = " + index.getAndIncrement());
                })
                .items(items)

        ;

        root.getChildren().add(dataTable.build());
    }

    @Override
    public String getName() {
        return "data_table";
    }

    @Override
    public Parent getRoot() {
        return root;
    }


}
