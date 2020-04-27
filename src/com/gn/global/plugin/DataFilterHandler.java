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
package com.gn.global.plugin;

import com.gn.global.model.Model;
import javafx.application.Platform;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.event.ActionEvent;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Region;

import java.util.List;
import java.util.Locale;
import java.util.ResourceBundle;
import java.util.stream.Collectors;

/**
 * @author Gleidson Neves da Silveira | gleidisonmt@gmail.com
 * Create on  26/04/2020
 */

@SuppressWarnings("unchecked")
public class DataFilterHandler<E extends Model> {

    private ComboBox<Integer>   entries;
    private TableView<E>        table;
    private Pagination          pagination;
    private Hyperlink           first;
    private Hyperlink           last;
    private Label               legend;

    private int maxPages;

    private BooleanProperty                     filtered = new SimpleBooleanProperty();
    private ObjectProperty<ResourceBundle>      bundle   = new SimpleObjectProperty<>();

    private Boolean pag = false;

    private FilteredList<E> filteredList;

    // For hacking and tests
    private GridPane paginationParent;
    private HBox container;


    public DataFilterHandler(TableView<E> table, Hyperlink first, Hyperlink last,
                             Pagination pagination, ComboBox<Integer> entries, Label legend,
                             FilteredList<E> filteredList)
    {
        this(table, first, last, pagination, entries, legend, filteredList, null);
    }

    public DataFilterHandler(TableView<E> table, Hyperlink first, Hyperlink last,
                             Pagination pagination, ComboBox<Integer> entries, Label legend,
                             FilteredList<E> filteredList, ResourceBundle bundle) {

        entries.getItems().addAll(5, 10, 50, 100);
        entries.setValue(5);

        entries.valueProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue != null) {
                pagination(pagination.getCurrentPageIndex(), newValue);
            }
        });

//        pagination

        this.table = table;
        this.entries = entries;
        this.first = first;
        this.last = last;
        this.pagination = pagination;
        this.legend = legend;
        this.filteredList = filteredList;
        this.bundle.set(bundle);

        // For hacking and tests
        container = (HBox) pagination.getParent();
        paginationParent = (GridPane) container.getParent();

        first.setMinWidth(0D);
        last.setMinWidth(0D);


        addPagination();
//        updatePagination(converter.size());
        updatePagination(filteredList.size()); // Testing

        pagination(0, entries.getValue());

        first.addEventHandler(ActionEvent.ACTION,
                event -> pagination(0, entries.getValue()));

        last.addEventHandler(ActionEvent.ACTION,
                event -> pagination(maxPages, entries.getValue()));

        filteredList.getSource().addListener((ListChangeListener<E>) c -> {
            if (c.next()) {
                if (table.getItems().size() < entries.getValue()) {
                    pagination();
                } else pagination(maxPages, entries.getValue());
            }
        });

        if(this.bundle.get() != null){
            first.setText(this.bundle.get().getString("DataTable.first"));
            last.setText(this.bundle.get().getString("DataTable.first"));
        } else {
            first.setText("First");
            last.setText("Last");
        }

        this.bundle.addListener((observable, oldValue, newValue) -> {
            if(newValue != null){
                first.setText(newValue.getString("DataTable.first"));
                last.setText(newValue.getString("DataTable.last"));
            }
        });

//        table.getItems().addListener((ListChangeListener) c -> {
//            if(c.next()){
//                if (c.wasRemoved() && !pag){
//
//                    int index = table.getSelectionModel().getSelectedIndex();
//
//                    converter.remove( c.getRemoved());
//                    Platform.runLater(() ->{
//                        pagination();
//                        table.getSelectionModel().select(index);
//                    });
//                }
//            }
//        });



    }



    public void pagination(int index, int limit){
        pag = true;

        if(isFiltered()){
            paginationFilter(filteredList, index);
        } else {
//             int size = converter.size(); // Testing
            int size = filteredList.size();

//             converter.pagination(table, index, limit); old method

            table.getItems().clear();

            List<E>
                    list = filteredList.
                    stream()
                    .skip(index * limit)  // Equivalent to SQL's offset
                    .limit(limit) // Equivalent to SQL's limit
                    .collect(Collectors.toList());

            table.setItems(FXCollections.observableArrayList(list));

            altLegend(
                    index == 0 ? table.getItems().isEmpty() ? 0 : index + 1
                            : ((index + 1) * entries.getValue()) - (entries.getValue() - 1),
                    index == 0 ? table.getItems().size() : (entries.getValue() * pagination.getCurrentPageIndex()) + table.getItems().size(),
                    size
            );

            updatePagination(size);
            pagination.setCurrentPageIndex(index);
        }
        pag = false;
//        return list;
    }

    public void refresh(){
        setBundle(ResourceBundle.getBundle(this.bundle.get().getBaseBundleName(), Locale.getDefault()));
        pagination();
    }

    private void pagination(){
        pagination(pagination.getCurrentPageIndex(), entries.getValue());
    }


    private ObservableList<E> paginationFilter(ObservableList<E> filtered, int index) {

        pag = true;
        int newList = index * entries.getValue();
        List<E> subList;
        subList = filtered.subList(Math.min(newList, filtered.size()), Math.min(filtered.size(), newList + entries.getValue()));

        ObservableList<E> newData = FXCollections.observableArrayList(subList);
        table.setItems(newData);

        updatePagination(filtered.size());

        altLegend(
                subList.size() == 0 ? 0 :
                        index == 0 ? index + 1 : ( (index+1) * entries.getValue()) - (entries.getValue() - 1),
                index == 0 ? subList.size() : (entries.getValue() * pagination.getCurrentPageIndex()) + subList.size(),
                filtered.size(),
                filtered.size()
        );

        pagination.setCurrentPageIndex(index);

        pag = false;
        return newData;
    }

    private void addPagination(){
        pagination.setCurrentPageIndex(0);
        pagination.currentPageIndexProperty().addListener((ObservableValue<? extends Number> observable, Number oldValue, Number newValue) -> {
            int index = pagination.getCurrentPageIndex();
            pagination(newValue.intValue(), entries.getValue());
//
//                all.size()
//        );
//            else
//                filter(filteredData, newValue.intValue());

            if(newValue.intValue() == 0){
                first.setDisable(true);
            } else first.setDisable(false);

            if (newValue.intValue() == pagination.getPageCount() -1){
                last.setDisable(true);
            } else last.setDisable(false);

//            search.setText(null);
        });
    }

    private void updatePagination(int size){

        int deff = 5;

        double division = size % entries.getValue();
        maxPages = division == 0 ? (size/entries.getValue()) : (size/entries.getValue()) + 1;

        pagination.setPageCount(maxPages);

//        if(maxPages < 2){
//            first.setVisible(false);
//            last.setVisible(false);
//            pagination.setVisible(false);
//        } else {
//            first.setVisible(true);
//            last.setVisible(true);
//            pagination.setVisible(true);
//        }

        if(maxPages < deff){
            pagination.setVisible(true);
        } else {
            pagination.setVisible(true);
        }


        // Hacking
        last.setMaxWidth(maxPages < deff ? 0D : Region.USE_COMPUTED_SIZE);
        last.setPrefWidth(maxPages < deff ? 0D : Region.USE_COMPUTED_SIZE);
        last.setMinWidth(maxPages < deff ? 0D : Region.USE_COMPUTED_SIZE);

        first.setMaxWidth(maxPages < deff ? 0D : Region.USE_COMPUTED_SIZE);
        first.setPrefWidth(maxPages < deff ? 0D : Region.USE_COMPUTED_SIZE);
        first.setMinWidth(maxPages < deff ? 0D : Region.USE_COMPUTED_SIZE);

        pagination.setPrefWidth((pagination.getMaxPageIndicatorCount() + 2) * 30D);

        removePagination(maxPages < 2);
        pagination.setMaxPageIndicatorCount(maxPages < deff ? maxPages : deff);
    }

    // For hacking
    private void removePagination(boolean remove){

        if(remove){
            paginationParent.getChildren().remove(container);
        } else {
            if(!paginationParent.getChildren().contains(container)) {
                paginationParent.getChildren().add(container);
            }
        }
    }

    private void altLegend(int init, int end, int total){
        Platform.runLater(() -> {

            if(this.bundle != null){
                legend.setText(
                        bundle.get().getString("DataTable.showing") + " "
                                + init + " " + bundle.get().getString("DataTable.to") + " "
                                + end + " " + bundle.get().getString("DataTable.of") + " " + total + " "
                                + bundle.get().getString("DataTable.entries") + "."
                );
            } else {

                legend.setText(
                        "Showing "
                                + init + " "
                                + end + " of "
                                + total
                                + " entries."
                );
            }
        });
    }

//    (filtered from 57 total entries)

    private void altLegend(int init, int end, int total, int filtered){
        Platform.runLater(() -> {
            if(this.bundle != null){
                legend.setText(
                        bundle.get().getString("DataTable.showing") + " "
                                + init + " " + bundle.get().getString("DataTable.to") + " "
                                + end + " " + bundle.get().getString("DataTable.of") + " " + total + " "
                                + bundle.get().getString("DataTable.entries") + ". "
                                + "(" + bundle.get().getString("DataTable.filtered") + " "
                                + bundle.get().getString("DataTable.from") + " "
                                + filtered +" total " + bundle.get().getString("DataTable.entries") + ")"
                );
            } else {
                legend.setText(
                        "Showing "
                                + init + " to "
                                + end + " of "
                                + total
                                + " entries."
                                + "(Filtered "
                                + "from "
                                + filtered + " total entries)"
                );
            }
        });
    }

    public void setBundle(ResourceBundle bundle){
        this.bundle.set(bundle);
    }

    public boolean isFiltered() {
        return filtered.get();
    }

    public BooleanProperty filteredProperty() {
        return filtered;
    }

    public void setFiltered(boolean filtered) {
        this.filtered.set(filtered);
    }

    public int getIndex(){
        return pagination.getCurrentPageIndex();
    }

    public int getLimit(){
        return entries.getValue();
    }
}