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
import io.github.gleidsonmt.dashboardfx.core.controls.Rating;
import io.github.gleidsonmt.dashboardfx.core.controls.icon.IconContainer;
import io.github.gleidsonmt.dashboardfx.core.controls.icon.Icons;
import io.github.gleidsonmt.dashboardfx.core.datatable.DataTable;
import io.github.gleidsonmt.dashboardfx.core.interfaces.ActionView;
import io.github.gleidsonmt.dashboardfx.core.view.View;
import io.github.gleidsonmt.dashboardfx.core.view.layout.DialogContainer;
import io.github.gleidsonmt.dashboardfx.core.view.layout.options.SnackColors;
import io.github.gleidsonmt.dashboardfx.factory.DefaultEntityFactory;
import io.github.gleidsonmt.dashboardfx.factory.ListOptions;
import io.github.gleidsonmt.dashboardfx.factory.Option;
import io.github.gleidsonmt.dashboardfx.factory.cells.*;
import io.github.gleidsonmt.dashboardfx.factory.tasks.CreateDevelopers;
import io.github.gleidsonmt.dashboardfx.model.Developer;
import io.github.gleidsonmt.dashboardfx.model.Status;
import javafx.beans.binding.Bindings;
import javafx.beans.binding.ObjectBinding;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.ReadOnlyObjectWrapper;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.concurrent.Task;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.geometry.VPos;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.*;
import javafx.scene.text.Text;
import org.controlsfx.control.RangeSlider;
import org.jetbrains.annotations.ApiStatus;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.concurrent.Callable;
import java.util.function.Predicate;

/**
 * @author Gleidson Neves da Silveira | gleidisonmt@gmail.com
 * Create on  21/10/2023
 */
public class DataTableView extends ActionView implements View {

    private StackPane root = new StackPane();
    private DataTable dataTable;

    //Filters

    private ObjectBinding<Predicate<Developer>> filter = null;


    public DataTableView(Context context) {
        super(context);

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

        Node filterPane = createFilterPane();

        dataTable = new DataTable<Developer>(context)
                .columns(nameColumn, statusColumn, ratingColumn, priceColumn, optionsColumn)
                .task(task)
                .filterEvent(filterPane, filter)
                .printEvent(event -> {
                    ListOptions listOptions = new ListOptions(context)
                            .items(
                                    new Option("PDF", Icons.NONE, null),
                                    new Option("Print", Icons.NONE, null)

                            );

                    if (event.getSource() instanceof Node node) {
                        context.flow().content(
                                        new DialogContainer(listOptions.build()).size(200, 100))
                                .show(Pos.BOTTOM_CENTER, node);
                    }


                })
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

    private Node createFilterPane2() {

        GridPane root = new GridPane();
        ComboBox<Status> comboBox = new ComboBox<>();
        ObservableList<Status> list = FXCollections.observableArrayList(Status.values());
        comboBox.setItems(list);

        root.getChildren().add(comboBox);

        Button apply = new Button("Apply");
        root.getChildren().add(apply);

        apply.setOnMouseClicked(event -> {
            ObjectProperty<Predicate<Developer>> statusFilter = new SimpleObjectProperty<>();

            statusFilter.bind(Bindings.createObjectBinding(() ->
                            item ->
                                    item.getStatus().equals(
                                            comboBox.getSelectionModel().getSelectedItem()),
                    comboBox.getSelectionModel().selectedItemProperty()));

            filter = Bindings.createObjectBinding(
                    statusFilter::get,
                    statusFilter
            );

            dataTable.applyFilter(filter);
        });

        return root;
    }

    private Node createFilterPane() {

        GridPane root = new GridPane();

        root.setPadding(new Insets(20));

        Node node = createRightList();
        Node range = createRangePanel();
        Node experiencePanel = createExperiencePanel();

        root.getChildren().addAll(node, experiencePanel);

        HBox actionContainer = new HBox();
        actionContainer.setAlignment(Pos.CENTER);
        Button apply = new Button("Apply");
        apply.setDefaultButton(true);
        Button reset = new Button("Reset");
        reset.setCancelButton(true);
        apply.setPrefWidth(200);
        reset.setPrefWidth(200);
        actionContainer.getChildren().addAll(apply, reset);
        actionContainer.setSpacing(10);
        root.getChildren().add(actionContainer);

        reset.setOnAction(event -> {
            dataTable.resetFilter();
            context.flow().close();
        });

        GridPane.setConstraints(node, 0,0,1,1, HPos.LEFT, VPos.CENTER, Priority.ALWAYS, Priority.ALWAYS);
        GridPane.setConstraints(experiencePanel, 0,1,1,1, HPos.LEFT, VPos.CENTER, Priority.ALWAYS, Priority.ALWAYS);
        GridPane.setConstraints(actionContainer, 0,2,1,1, HPos.LEFT, VPos.CENTER, Priority.ALWAYS, Priority.ALWAYS);

        ObjectProperty<Predicate<Developer>> statusFilter = new SimpleObjectProperty<>();
        ObjectProperty<Predicate<Developer>> ratingFilter = new SimpleObjectProperty<>();

        statusFilter.bind(Bindings.createObjectBinding(() ->
                        item ->
                                item.getStatus().equals(
                                        statusObject.getValue()),
                statusObject));

        ratingFilter.bind(Bindings.createObjectBinding(() ->
                        item ->
                                item.getExperience().getRange() ==
                                        ratingObject.get(),
                ratingObject));

        apply.setOnMouseClicked(event -> {

            if (statusObject.get() != null && ratingObject.get() != null) {
                filter = Bindings.createObjectBinding(
                        () -> statusFilter.get().and(ratingFilter.get()),
                        statusFilter, ratingFilter
                );
            } else if (statusObject.get() != null){
                filter = Bindings.createObjectBinding(
                        statusFilter::get,
                        statusFilter
                );
            } else if(ratingObject.get() != null) {
                filter = Bindings.createObjectBinding(
                        ratingFilter::get,
                        ratingFilter
                );
            }

            if (filter != null) {
                dataTable.applyFilter(filter);
            }

            context.flow().close();
//

        });

        return root;
    }

    private ToggleGroup statusGroup = new ToggleGroup();
    private ObjectProperty<Status> statusObject = new SimpleObjectProperty<>();
    private ObjectProperty<Integer> ratingObject = new SimpleObjectProperty<>();

    private Node createRightList() {
        VBox body = new VBox();
        Text title = new Text("Filters");
        title.getStyleClass().addAll("h5", "text-bold");
        Text legend = new Text("Status:");
        legend.getStyleClass().addAll("h5");

        RadioButton active = new RadioButton("Active");
        RadioButton inactive = new RadioButton("Inactive");
        RadioButton busy = new RadioButton("Busy");

        active.setUserData(Status.ACTIVE);
        inactive.setUserData(Status.INACTIVE);
        busy.setUserData(Status.BUSY);

        GridPane content = new GridPane();
        content.getChildren().setAll(active, inactive, busy);
        content.setHgap(10);

        GridPane.setConstraints(active, 0,0,1,1, HPos.LEFT, VPos.CENTER, Priority.ALWAYS, Priority.ALWAYS);
        GridPane.setConstraints(inactive, 1,0,1,1, HPos.CENTER, VPos.CENTER, Priority.ALWAYS, Priority.ALWAYS);
        GridPane.setConstraints(busy, 2,0,1,1, HPos.RIGHT, VPos.CENTER, Priority.ALWAYS, Priority.ALWAYS);

        statusGroup.selectedToggleProperty().addListener((observable, oldValue, newValue) -> statusObject.setValue((Status) newValue.getUserData()));

        body.setSpacing(10);
        body.getChildren().setAll(title, legend, content);
        statusGroup.getToggles().addAll(active, inactive, busy);

        return body;
    }

    @ApiStatus.Experimental
    private Node createExperiencePanel() {
        VBox box = new VBox();
        Text legend = new Text("Experience:");
        legend.getStyleClass().addAll("h5");

        Rating rating = new Rating(0, 4);


        rating.rangeProperty().addListener((observable, oldValue, newValue) -> {
            ratingObject.setValue(newValue.intValue() );
        });

        box.getChildren().addAll(legend, rating);
        return box;
    }

    @ApiStatus.Experimental
    private Node createRangePanel() {
        VBox box = new VBox();
        RangeSlider slider = new RangeSlider();
        slider.setBlockIncrement(10);
        slider.setMax(100);
        slider.setShowTickLabels(true);
        slider.setSnapToTicks(true);
//        slider.setSnapToTicks(true);
        Text legend = new Text("Status:");
        legend.getStyleClass().addAll("h5");
        box.getChildren().addAll(legend, slider);
        return box;
    }
}

