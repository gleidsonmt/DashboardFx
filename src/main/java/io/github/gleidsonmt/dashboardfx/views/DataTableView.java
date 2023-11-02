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
import io.github.gleidsonmt.dashboardfx.factory.DefaultEntityFactory;
import io.github.gleidsonmt.dashboardfx.factory.cells.*;
import io.github.gleidsonmt.dashboardfx.model.Developer;
import io.github.gleidsonmt.dashboardfx.factory.tasks.CreateDevelopers;
import io.github.gleidsonmt.dashboardfx.model.Status;
import javafx.beans.property.ReadOnlyObjectWrapper;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.concurrent.Task;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.control.TableColumn;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.StackPane;

import java.math.BigDecimal;

/**
 * @author Gleidson Neves da Silveira | gleidisonmt@gmail.com
 * Create on  21/10/2023
 */
public class DataTableView extends ActionView implements View {

    private StackPane root = new StackPane();
    private DataTable dataTable;
    public DataTableView(Context context) {
        super(context);

//        TableColumn<Developer, String> nameColumn = new TableColumn<>("Name");
//        nameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));

        TableColumn<Developer, Developer> nameColumn = new TableColumn<>("Name");

        nameColumn.setCellValueFactory(new DefaultEntityFactory<>());
        nameColumn.setCellFactory(new ItemAvatarFactory<>());

        TableColumn<Developer, Status> statusColumn = new TableColumn<>("Status");
        statusColumn.setCellValueFactory(new PropertyValueFactory<>("status"));
        statusColumn.setCellFactory(new ItemStatusFactory<>(context));

        TableColumn<Developer, Developer> optionsColumn = new TableColumn<>("Options");
        optionsColumn.setCellValueFactory(new DefaultEntityFactory<>());
        optionsColumn.setCellFactory(new OptionsCellFactory<>(context));

        optionsColumn.setMaxWidth(100);
        optionsColumn.setPrefWidth(100);
        optionsColumn.setMinWidth(100);

        TableColumn<Developer, Experience> ratingColumn = new TableColumn<>("Rating");
        ratingColumn.setCellValueFactory((param) -> new ReadOnlyObjectWrapper<>(param.getValue().getExperience()));
        ratingColumn.setCellFactory(new ExperienceFactoryCell<>());

        TableColumn<Developer, BigDecimal> priceColumn = new TableColumn<>("Price/hour");
        priceColumn.setCellValueFactory((param) -> new ReadOnlyObjectWrapper<>(param.getValue().getPricePerHour()));
        priceColumn.setCellFactory(new MonetaryCellFactory<>());

        ObservableList<Developer> items = FXCollections.observableArrayList();
        Task<ObservableList<Developer>> task = new CreateDevelopers(context, items);

        dataTable = new DataTable<Developer>()
                .columns(nameColumn, statusColumn, ratingColumn, priceColumn, optionsColumn)
                .task(task)
                .filters()
                .deleteEvent(event -> {
//                    items.remove(index.getAndIncrement());
                    if (dataTable.getSelectedItems().isEmpty()) {
                        context.createSnackBar()
                                .color(SnackColors.WARNING)
                                .icon(new IconContainer(Icons.WARNING))
                                .message("No item selected")
                                .show(Pos.CENTER, new Insets(10,10,10,250));
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

