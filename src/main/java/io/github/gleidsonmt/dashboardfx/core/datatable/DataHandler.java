package io.github.gleidsonmt.dashboardfx.core.datatable;

import com.dlsc.gemsfx.SearchTextField;
import io.github.gleidsonmt.dashboardfx.model.Developer;
import io.github.gleidsonmt.dashboardfx.model.Item;
import javafx.application.Platform;
import javafx.beans.binding.Bindings;
import javafx.beans.binding.ObjectBinding;
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
import javafx.concurrent.Task;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Region;
import javafx.scene.text.Text;

import java.util.List;
import java.util.Locale;
import java.util.ResourceBundle;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.function.Predicate;
import java.util.stream.Collectors;

/**
 * @author Gleidson Neves da Silveira | gleidisonmt@gmail.com
 * Create on  23/08/2023
 */
public class DataHandler<E extends Item> {

    private ComboBox<Integer> entries;
    private TableView<E> table;
    private Pagination pagination;
    private Button first;
    private Button last;
    private Text legend;

    private int maxPages;

    private BooleanProperty filtered = new SimpleBooleanProperty();
    private ObjectProperty<ResourceBundle> bundle = new SimpleObjectProperty<>();

    private Boolean pag = false;

    private FilteredList<E> filteredList;
    private ObservableList<E> items;

    // For hacking and tests
    private GridPane paginationParent;
    private HBox container;
    private CheckBox checkButton;

    private ObjectProperty<Predicate<E>> filter = new SimpleObjectProperty<>();
    private ObjectProperty<Predicate<E>> nameFilter = new SimpleObjectProperty<>();


//    public DataHandler(TableView<E> table, Button first, Button last,
//                       Pagination pagination, ComboBox<Integer> entries, Text legend, GridPane footer, CheckBox checkButton,
//                       Task<ObservableList<E>> task, List<ObjectProperty<Predicate<E>>> filters, ObservableList<E> _items)
//    {
//        this(table, first, last, pagination, entries, legend, footer, checkButton, task, filters, _items,null);
//    }

//    private ObservableList<Integer> values = FXCollections.observableArrayList(5, 10, 50, 100);

    ObjectBinding<Predicate<E>> bigPicture;

    public void applyFilter(ObjectBinding<Predicate<E>> fil) {

        bigPicture = Bindings.createObjectBinding(
                () -> nameFilter.get().and(fil.get()),
                nameFilter, fil
        );

        filteredList.predicateProperty().unbind();
        filteredList.predicateProperty().bind(bigPicture);
        pagination();
    }

    public void applyDefaultFilter() {

        bigPicture = Bindings.createObjectBinding(
                () -> nameFilter.get(),
                nameFilter
        );

        filteredList.predicateProperty().unbind();
        filteredList.predicateProperty().bind(bigPicture);
        pagination();
    }
    private void resetFilter() {
        bigPicture = Bindings.createObjectBinding(
                () -> nameFilter.get(),
                nameFilter
        );
        filteredList.predicateProperty().unbind();
        filteredList.predicateProperty().bind(bigPicture);
        pagination();
    }

    public DataHandler(TableView<E> table, Button first, Button last,
                       Pagination pagination, ComboBox<Integer> entries, Text legend, GridPane footer, CheckBox checkButton,
                       Task<ObservableList<E>> task, ObjectBinding<Predicate<E>> filter, ObservableList<E> _items, ResourceBundle bundle, SearchTextField search) {

        entries.getItems().addAll(5, 10, 50, 100);
        entries.setValue(5);
        entries.setVisible(false);

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
        this.checkButton = checkButton;
        this.items = _items;

        checkButton.setOnAction(event -> {
            if (!checkButton.isSelected()) {
                table.getSelectionModel().clearSelection();
            } else {
                table.getSelectionModel().selectAll();
            }
        });

        this.bundle.set(bundle);

        // For hacking and tests
        container = (HBox) pagination.getParent();
        paginationParent = (GridPane) container.getParent();

        first.setMinWidth(0D);
        last.setMinWidth(0D);

        filteredList = new FilteredList<>(items, f -> true);

        if (task != null) {
            task.setOnRunning(event -> table.setPlaceholder(new SimpleLoadCircle()));

            task.setOnSucceeded(e -> {
                items = task.getValue();
                pagination(0, getVisibleEntries());
                table.setPlaceholder(null);
            });

            Thread thread = new Thread(task);
            thread.start();
        } else if (items.isEmpty()) {
            //
        }
//
        addPagination();
//
//            filteredList = new FilteredList<>(task.getValue());
//
////        updatePagination(converter.size());
//        updatePagination(filteredList.size()); // Testing

        updatePagination(filteredList.size());
        updateButtons(filteredList.size()); // Testing
//
        pagination(0, getVisibleEntries());
//
        first.addEventHandler(ActionEvent.ACTION,
                event -> pagination(0, getVisibleEntries()));

        last.addEventHandler(ActionEvent.ACTION,
                event -> pagination(maxPages, getVisibleEntries()));
//
        filteredList.getSource().addListener((ListChangeListener<E>) c -> {
            if (c.next()) {

                if (table.getItems().size() < getVisibleEntries()) {
                    Platform.runLater(() -> pagination(pagination.getCurrentPageIndex(), getVisibleEntries()));
                } else {
                    Platform.runLater(() -> pagination(pagination.getCurrentPageIndex(), getVisibleEntries()));
                }
                Platform.runLater(() ->
                        updatePagination(
                                pagination.getCurrentPageIndex())
                );
            }
        });

//            if(this.bundle.get() != null){
//                first.setText(this.bundle.get().getString("datatable.first"));
//                last.setText(this.bundle.get().getString("datatable.last"));
//
//            } else {
        first.setText("First");
        last.setText("Last");
//            }

//            this.bundle.addListener((observable, oldValue, newValue) -> {
//                if(newValue != null){
//                    first.setText(newValue.getString("datatable.first"));
//                    last.setText(newValue.getString("datatable.last"));
//                }
//            });
//
        footer.setMinHeight(60);
        footer.setMaxHeight(Region.USE_COMPUTED_SIZE);
        footer.setVisible(true);


        nameFilter.bind(Bindings.createObjectBinding(() ->
                        person -> person.getName().toLowerCase().contains(search.getText().toLowerCase()),
                search.textProperty()));

        bigPicture = Bindings.createObjectBinding(
                () -> nameFilter.get(),
                nameFilter
        );

        filteredList.predicateProperty().bind(bigPicture);

//        filteredList.predicateProperty().addListener((observable, oldValue, newValue) -> pagination());

        bigPicture.addListener((observable, oldValue, newValue) -> {

            if (filteredList.size() != _items.size()) {
                setFiltered(true);
                pagination();
                updatePagination(filteredList.size());
            } else {
                setFiltered(false);
                pagination();
                updatePagination(_items.size());
            }
        });
//
//        filters.forEach(c -> {
//            System.out.println("c = " + c);
//            filteredList.predicateProperty().bind(
//                    Bindings.createObjectBinding(
//                            c::get, c
//                    )
//            );
//        });


//        filteredList.predicateProperty().bind(Bindings.createObjectBinding(
//                () -> nameFilter.get().and(priceFilter.get()),
//                nameFilter)
//        );


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

    public void setFilters(Predicate<E> predicate) {
        filteredList.setPredicate(
                nameFilter.get().and(predicate)
        );
    }

    public void pagination(int index, int limit) {
        pag = true;

//        filteredList.forEach(c -> c.setChecked(false));

        if (isFiltered()) {
            paginationFilter(filteredList, index);
            entries.setVisible(false);
        } else {
//             int size = converter.size(); // Testing
            int size = filteredList.size();
            entries.setVisible(true);

//             converter.pagination(table, index, limit); old method

            table.getItems().clear();

            List<E>
                    list = filteredList.
                    stream()
                    .skip((long) index * limit)  // Equivalent to SQL's offset
                    .limit(limit) // Equivalent to SQL's limit
                    .collect(Collectors.toList());

            table.setItems(FXCollections.observableArrayList(list));

            table.getItems()
                    .forEach(c -> c.checkedProperty().addListener((observable, oldValue, newValue) -> {
                        verify();
                    }));

            altLegend(
                    index == 0 ? table.getItems().isEmpty() ? 0 : index + 1
                            : ((index + 1) * getVisibleEntries()) - (getVisibleEntries() - 1),
                    index == 0 ? table.getItems().size() : (getVisibleEntries() * pagination.getCurrentPageIndex()) + table.getItems().size(),
                    size
            );

//            updatePagination(size);

            updateButtons(size);
//            updatePagination(size);

            pagination.setCurrentPageIndex(index);
        }
        pag = false;
//        return list;
    }

    private boolean needsAction = true;

    private void verify() {
        AtomicInteger count = new AtomicInteger();
        table.getItems().forEach(c -> {
            if (c.isChecked()) {
                count.getAndIncrement();
            }
        });

        if (!needsAction) return;

        if (count.get() == 0) {
            checkButton.setIndeterminate(false);
            checkButton.setSelected(false);
        } else if (count.get() == table.getItems().size()) {
            checkButton.setIndeterminate(false);
            checkButton.setSelected(true);
        } else {
            checkButton.setSelected(false);
            checkButton.setIndeterminate(true);
        }

    }

    private void updateButtons(int size) {

        int deff = 5;

        double division = size % getVisibleEntries();
        maxPages = division == 0 ? (size / getVisibleEntries()) : (size / getVisibleEntries()) + 1;

        pagination.setPageCount(maxPages);

        if (maxPages < 2) {
            first.setVisible(false);
            last.setVisible(false);
            pagination.setVisible(false);
        } else {
            first.setVisible(true);
            last.setVisible(true);
            pagination.setVisible(true);
        }


//        // Hacking
        last.setMaxWidth(maxPages < deff ? 0D : Region.USE_COMPUTED_SIZE);
        last.setPrefWidth(maxPages < deff ? 0D : Region.USE_COMPUTED_SIZE);
        last.setMinWidth(maxPages < deff ? 0D : Region.USE_COMPUTED_SIZE);

        first.setMaxWidth(maxPages < deff ? 0D : Region.USE_COMPUTED_SIZE);
        first.setPrefWidth(maxPages < deff ? 0D : Region.USE_COMPUTED_SIZE);
        first.setMinWidth(maxPages < deff ? 0D : Region.USE_COMPUTED_SIZE);

        pagination.setPrefWidth((pagination.getMaxPageIndicatorCount() + 2) * 30D);

        removePagination(maxPages < 2);
        pagination.setMaxPageIndicatorCount(Math.min(maxPages, deff));
    }

    public void refresh() {
        setBundle(ResourceBundle.getBundle(this.bundle.get().getBaseBundleName(), Locale.getDefault()));
        pagination();
    }

    private void pagination() {
        pagination(pagination.getCurrentPageIndex(), getVisibleEntries());
    }

    private ObservableList<E> paginationFilter(ObservableList<E> filtered, int index) {

        pag = true;
        int newList = index * getVisibleEntries();
        List<E> subList;
        subList = filtered.subList(Math.min(newList, filtered.size()), Math.min(filtered.size(), newList + getVisibleEntries()));

        ObservableList<E> newData = FXCollections.observableArrayList(subList);
        table.setItems(newData);

//        updatePagination(filtered.size());
        updateButtons(filtered.size());

        altLegend(
                subList.isEmpty() ? 0 :
                        index == 0 ? index + 1 : ((index + 1) * getVisibleEntries()) - (getVisibleEntries() - 1),
                index == 0 ? subList.size() : (getVisibleEntries() * pagination.getCurrentPageIndex()) + subList.size(),
                filtered.size(),
                filtered.size()
        );

        pagination.setCurrentPageIndex(index);

        pag = false;
        return newData;
    }

    private void addPagination() {
        pagination.setCurrentPageIndex(0);
        pagination.currentPageIndexProperty().addListener((ObservableValue<? extends Number> observable, Number oldValue, Number newValue) -> {
            int index = pagination.getCurrentPageIndex();
            pagination(newValue.intValue(), getVisibleEntries());
//
//                all.size()
//        );
//            else
//                filter(filteredData, newValue.intValue());

            first.setDisable(newValue.intValue() == 0);
            last.setDisable(newValue.intValue() == pagination.getPageCount() - 1);

//            search.setText(null);
        });
    }


    private void updatePagination(int size) {

        int comp = items.size();

        if (comp < 5) {
            entries.setVisible(false);
//            entries.setValue(5);
        } else {
            entries.setVisible(true);
            if (comp > 5 && comp <= 10) {
                entries.getItems().setAll(5, 10);
                entries.setValue(10);
            } else if (comp > 10 && comp <= 50) {
                entries.getItems().setAll(5, 10, 50);
                entries.setValue(10);
            } else {
                entries.getItems().setAll(5, 10, 50, 100);
                entries.setValue(10);
            }
        }

    }

    // For hacking
    private void removePagination(boolean remove) {

        if (remove) {
            paginationParent.getChildren().remove(container);
        } else {
            if (!paginationParent.getChildren().contains(container)) {
                paginationParent.getChildren().add(container);
            }
        }
    }

    private void altLegend(int init, int end, int total) {
        Platform.runLater(() -> {

            if (this.bundle.get() != null) {
                legend.setText(
                        bundle.get().getString("datatable.showing") + " "
                                + init + " " + bundle.get().getString("datatable.to") + " "
                                + end + " " + bundle.get().getString("datatable.of") + " " + total + " "
                                + bundle.get().getString("datatable.entries") + "."
                );
            } else {

                legend.setText(
                        "Showing "
                                + init + " - "
                                + end + " of "
                                + total
                                + " entries."
                );
            }
        });
    }

//    (filtered from 57 total entries)

    private void altLegend(int init, int end, int total, int filtered) {
        Platform.runLater(() -> {
//            if(this.bundle != null){
//                legend.setText(
//                        bundle.get().getString("DataTableView.showing") + " "
//                                + init + " " + bundle.get().getString("DataTableView.to") + " "
//                                + end + " " + bundle.get().getString("DataTableView.of") + " " + total + " "
//                                + bundle.get().getString("DataTableView.entries") + ". "
//                                + "(" + bundle.get().getString("DataTableView.filtered") + " "
//                                + bundle.get().getString("DataTableView.from") + " "
//                                + filtered +" total " + bundle.get().getString("DataTableView.entries") + ")"
//                );
//            } else {
//                legend.setText(
//                        "Showing "
//                                + init + " to "
//                                + end + " of "
//                                + total
//                                + " entries."
//                                + "(Filtered "
//                                + "from "
//                                + filtered + " total entries)"
//                );
//            }
        });
    }

    public void setBundle(ResourceBundle bundle) {
        this.bundle.set(bundle);
    }

    public boolean isFiltered() {
        return filtered.get();
    }

    public BooleanProperty filteredProperty() {
        return filtered;
    }

    public int getVisibleEntries() {
        return entries.getValue() != null ? entries.getValue() : 5;
    }

    public void setFiltered(boolean filtered) {
        this.filtered.set(filtered);
    }

    public int getIndex() {
        return pagination.getCurrentPageIndex();
    }
}

