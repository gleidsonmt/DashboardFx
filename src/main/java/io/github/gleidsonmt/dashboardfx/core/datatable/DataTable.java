package io.github.gleidsonmt.dashboardfx.core.datatable;

import com.dlsc.gemsfx.SearchTextField;
import io.github.gleidsonmt.dashboardfx.core.Context;
import io.github.gleidsonmt.dashboardfx.core.view.layout.DialogContainer;
import io.github.gleidsonmt.dashboardfx.model.Item;
import javafx.beans.binding.ObjectBinding;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.ReadOnlyObjectProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.concurrent.Task;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.geometry.VPos;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.control.*;
import javafx.scene.control.cell.CheckBoxTableCell;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import javafx.scene.shape.SVGPath;
import javafx.scene.text.Text;
import javafx.util.Callback;

import java.util.List;
import java.util.function.Predicate;

/**
 * @author Gleidson Neves da Silveira | gleidisonmt@gmail.com
 * Create on  30/08/2023
 */
@SuppressWarnings("all")
public class DataTable<T extends Item> {

    // Layout
    private VBox root;
    private GridPane bar;
    private HBox buttonBar;
    private SearchTextField search;
    private Text show;
    private HBox entriesBox;
    private ComboBox<Integer> entries;
    private Text entriesLegend;
    private TableView<T> tableView;
    private GridPane footer;
    private Text legend;
    private HBox paginationBox;
    private Button first;
    private Pagination pagination;
    private Button last;

    private Task<ObservableList<T>> task;

    private final Context context;

    public DataTable(Context context) {
        this.context = context;
    }

    public DataTable<T> task(Task<ObservableList<T>> task) {
        this.task = task;
        return this;
    }

    private ObservableList<T> items;

    public DataTable<T> items(ObservableList<T> items) {
        this.items = items;
        return this;
    }

    private EventHandler<ActionEvent> add;

    public DataTable<T> addEvent(EventHandler<ActionEvent> event) {
        this.add = event;
        return this;
    }

    private EventHandler<ActionEvent> printEvent;

    public DataTable<T> printEvent(EventHandler<ActionEvent> event) {
        this.printEvent = event;
        return this;
    }

    private EventHandler<ActionEvent> delete;

    public DataTable<T> deleteEvent(EventHandler<ActionEvent> event) {
        this.delete = event;
        return this;
    }

    private EventHandler<MouseEvent> doubleClick;

    public DataTable<T> doubleClick(EventHandler<MouseEvent> event) {
        this.doubleClick = event;
        return this;
    }

    private EventHandler<ActionEvent> importE;

    public DataTable<T> importEvent(EventHandler<ActionEvent> event) {
        this.importE = event;
        return this;
    }

    private EventHandler<ActionEvent> exportEvent;

    public DataTable<T> exportEvent(EventHandler<ActionEvent> event) {
        this.exportEvent = event;
        return this;
    }

    private EventHandler<ActionEvent> filterEvent;

    public DataTable<T> filterEvent(EventHandler<ActionEvent> event) {
        this.filterEvent = event;
        return this;
    }

    private Node filterContent;

    public DataTable<T> filterEvent(Node event, ObjectBinding<Predicate<T>> filter) {
        this.filterContent = event;
        this.filter = filter;
        return this;
    }

    private List<ObjectProperty<Predicate<T>>> filters;
    private ObjectBinding<Predicate<T>> filter;
    private Node filterNode;

    public final DataTable<T> filters(Node content, ObjectBinding<Predicate<T>> filter) {
//        this.filters = List.of(filters);
        this.filter = filter;
        this.filterNode = content;
        return this;
    }


    private ObservableList<TableColumn<T, ?>> columns;

    @SafeVarargs
    public final DataTable<T> columns(TableColumn<T, ?>... column) {
        this.columns = FXCollections.observableArrayList(column);
        return this;
    }

    public final DataTable<T> columns(List<TableColumn<T, ?>> columns) {
        this.columns = FXCollections.observableArrayList(columns);
        return this;
    }

    public Parent build() {
        createLayout();
        return root;
    }

    private void createLayout() {
        root = new VBox();
        root.setId("body");
        bar = new GridPane();
        bar.setId("bar");
        tableView = new TableView<>();
        tableView.setId("tableView");
        footer = new GridPane();
        legend = new Text("Showing 1-67 of 80");
        paginationBox = new HBox();
        paginationBox.setAlignment(Pos.CENTER);
        first = new Button();
        last = new Button();
        pagination = new Pagination();
        entriesBox = new HBox();
        buttonBar = new HBox();
        search = new SearchTextField();
        show = new Text();
        entriesLegend = new Text();
        entries = new ComboBox<>();
        tableView.setEditable(true);

        tableView.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);

        root.setPadding(new Insets(10));
        root.setSpacing(10);
        entriesBox.setAlignment(Pos.CENTER_RIGHT);

        HBox.setHgrow(search, Priority.ALWAYS);

        CheckBox checkBox = createCheckBox();
        Button delete = createDelete();
        Button _import = createImport();
        Button _export = createExport();
        Button print = createPrint();
        Button add = createAdd();
        Button _filter = createFilter();

        buttonBar.getChildren().setAll(checkBox, delete, _import, _export, print, add, _filter, search);

        HBox.setMargin(checkBox, new Insets(2, 5, 0, 0));
        entriesBox.getChildren().setAll(show, entries, entriesLegend);
        bar.getChildren().setAll(buttonBar);
        paginationBox.getChildren().setAll(first, pagination, last);

        buttonBar.setPadding(new Insets(5, 10, 5, 10));
        buttonBar.setAlignment(Pos.CENTER_LEFT);
        buttonBar.setStyle("-fx-background-color:  -light-gray; -fx-background-radius: 100px;");
        entries.getStyleClass().add("inner");
        search.getStyleClass().add("round");
        tableView.setStyle("-fx-border-width: 1px;");
        first.getStyleClass().add("btn-flat");
        last.getStyleClass().add("btn-flat");
        pagination.setStyle("-fx-page-information-visible: false;");

//        tableView.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY_LAST_COLUMN);
        tableView.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
        footer.getChildren().setAll(legend, paginationBox, entriesBox);
        HBox.setMargin(first, new Insets(10, 0, 0, 0));
        HBox.setMargin(last, new Insets(10, 0, 0, 0));

        VBox.setVgrow(tableView, Priority.ALWAYS);
        GridPane.setConstraints(buttonBar, 0, 0, 1, 1, HPos.LEFT, VPos.CENTER, Priority.SOMETIMES, Priority.ALWAYS);

        GridPane.setConstraints(entriesBox, 2, 0, 1, 1, HPos.RIGHT, VPos.CENTER, Priority.SOMETIMES, Priority.ALWAYS);
        GridPane.setConstraints(legend, 0, 0, 1, 1, HPos.LEFT, VPos.CENTER, Priority.SOMETIMES, Priority.ALWAYS);
        GridPane.setConstraints(paginationBox, 1, 0, 1, 1, HPos.RIGHT, VPos.CENTER, Priority.SOMETIMES, Priority.ALWAYS);

        root.getChildren().setAll(bar, tableView, footer);

        tableView.getColumns().add(0, createTableChecked());

        if (columns != null)
            tableView.getColumns().addAll(columns);

        footer.setVisible(false);
        footer.setMaxHeight(0);
        footer.setMinHeight(0);

//        ResourceBundle resources = ResourceBundle.getBundle("i18n.Lang", Locale.getDefault());
//        entriesLegend.setText(I18n.get("datatable.entries"));
//        show.setText(I18n.get("datatable.show"));

        if (doubleClick != null)
            tableView.setRowFactory(new DefaultDoubleClickRowFactory<>(doubleClick));
        else tableView.setRowFactory(new DefaultRowFactory<T>());

        dataHandler = new DataHandler<>(tableView, first, last, pagination, entries, legend, footer, checkBox, task, filter, items, null, search);

//            public DataHandler(TableView<E> table, Button first, Button last,
//                Pagination pagination, ComboBox<Integer> entries, Label legend,
//                FilteredList<E> filteredList)
    }

    private DataHandler<T> dataHandler;

    public void applyFilter(ObjectBinding<Predicate<T>> filter) {
        dataHandler.applyFilter(filter);
    }

    public void resetFilter() {
        dataHandler.applyDefaultFilter();
    }

    public T getSelectedItem() {
        return tableView.getSelectionModel().getSelectedItem();
    }

    public ObservableList<T> getSelectedItems() {
        return tableView.getSelectionModel().getSelectedItems();
    }

    public ReadOnlyObjectProperty<T> selectedItemProperty() {
        return tableView.getSelectionModel().selectedItemProperty();
    }

    private TableColumn<T, Boolean> createTableChecked() {
        TableColumn<T, Boolean> tableColumn = new TableColumn<>();
        tableColumn.setCellValueFactory(new PropertyValueFactory<>("checked"));

        tableColumn.setCellFactory(CheckBoxTableCell.forTableColumn(tableColumn));

        tableColumn.setCellFactory(
                new Callback<>() {
                    @Override
                    public TableCell<T, Boolean> call(TableColumn<T, Boolean> p) {
                        return new CheckBoxTableCell<>() {
                            @Override
                            public void updateItem(Boolean item, boolean empty) {
                                super.updateItem(item, empty);
                                if (item != null) {
                                    setMouseTransparent(true);
                                }
                            }
                        };
                    }

                });

        tableColumn.setCellValueFactory(
                p -> {
                    return p.getValue().checkedProperty();
//                   return new SimpleBooleanProperty(p.getValue() != null);

                });

        tableColumn.setStyle("-fx-alignment: center;");
        tableColumn.setMinWidth(40);
        tableColumn.setPrefWidth(40);
        tableColumn.setMaxWidth(40);
        return tableColumn;
    }

    private CheckBox createCheckBox() {
        CheckBox checkBox = new CheckBox();
        checkBox.setContentDisplay(ContentDisplay.GRAPHIC_ONLY);
        return checkBox;
    }

    private Button createDelete() {
        Button button = createButton();
        button.setOnAction(delete);
        button.setGraphic(createGraphic("M16 9v10H8V9h8m-1.5-6h-5l-1 1H5v2h14V4h-3.5l-1-1zM18 7H6v12c0 1.1.9 2 2 2h8c1.1 0 2-.9 2-2V7z"));
        return button;
    }

    private Button createImport() {
        Button button = createButton();
        button.setGraphic(createGroup("M480-320 280-520l56-58 104 104v-326h80v326l104-104 56 58-200 200ZM240-160q-33 0-56.5-23.5T160-240v-120h80v120h480v-120h80v120q0 33-23.5 56.5T720-160H240Z"));
        button.setOnAction(importE);
        return button;
    }

    private Button createExport() {
        Button button = createButton();
        button.setGraphic(createGroup("M440-320v-326L336-542l-56-58 200-200 200 200-56 58-104-104v326h-80ZM240-160q-33 0-56.5-23.5T160-240v-120h80v120h480v-120h80v120q0 33-23.5 56.5T720-160H240Z"));
        button.setOnAction(exportEvent);
        return button;
    }

    private Button createPrint() {
        Button button = createButton();
        button.setGraphic(createGraphic("M19 8H5c-1.66 0-3 1.34-3 3v6h4v4h12v-4h4v-6c0-1.66-1.34-3-3-3zm-3 11H8v-5h8v5zm3-7c-.55 0-1-.45-1-1s.45-1 1-1 1 .45 1 1-.45 1-1 1zm-1-9H6v4h12V3z"));
        button.setOnAction(printEvent);
        return button;
    }

    private Button createAdd() {
        Button button = createButton();
        button.setGraphic(createGraphic("M12 2C6.48 2 2 6.48 2 12s4.48 10 10 10 10-4.48 10-10S17.52 2 12 2zm5 11h-4v4h-2v-4H7v-2h4V7h2v4h4v2z"));
        button.setOnAction(add);
        return button;
    }

    private Button createFilter() {
        Button button = createButton();
        button.setGraphic(createGroup("M400-240v-80h160v80H400ZM240-440v-80h480v80H240ZM120-640v-80h720v80H120Z"));
        button.setOnAction(filterEvent);
        if (filterContent != null) {

            button.addEventFilter(ActionEvent.ACTION, event -> {
                context.flow()
                        .content(
                                new DialogContainer(filterContent)
                                        .size(300, 300)
                        )
                        .show(Pos.BOTTOM_CENTER, button, 0, 0);
            });

        }

        return button;
    }

    private Button createButton() {
        Button button = new Button();
        button.getStyleClass().addAll("btn-flat", "round", "btn-inverse");
        button.setContentDisplay(ContentDisplay.GRAPHIC_ONLY);
        button.setMinWidth(35);
        return button;
    }

    private SVGPath createGraphic(String content) {
        SVGPath svgPath = new SVGPath();
        svgPath.setContent(content);
        svgPath.setScaleX(1);
        svgPath.setScaleY(1);
        return svgPath;
    }

    private SVGPath createGraphic(String content, double scaleX, double scaleY) {
        SVGPath svgPath = new SVGPath();
        svgPath.setContent(content);
        svgPath.setScaleX(scaleX);
        svgPath.setScaleY(scaleY);
        return svgPath;
    }

    private Group createGroup(String content) {
        Group group = new Group();
        group.getChildren().add(createGraphic(content, 0.025, 0.025));
        return group;
    }

}