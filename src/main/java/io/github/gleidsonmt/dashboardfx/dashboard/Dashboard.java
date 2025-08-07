package io.github.gleidsonmt.dashboardfx.dashboard;

import io.github.gleidsonmt.dashboardfx.model.*;
import io.github.gleidsonmt.dashboardfx.utils.Assets;
import io.github.gleidsonmt.glad.base.Root;
import io.github.gleidsonmt.glad.base.responsive.Break;
import io.github.gleidsonmt.glad.charts.DonutChart;
import io.github.gleidsonmt.glad.controls.avatar.AvatarView;
import io.github.gleidsonmt.glad.controls.icon.Icon;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.chart.*;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.*;
import javafx.scene.text.Text;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;

/**
 * @author Gleidson Neves da Silveira | gleidisonmt@gmail.com
 * Create on  12/03/2025
 */
public class Dashboard extends StackPane implements ActionableView {

    private GridPane grid = new GridPane();
    ScrollPane scrollPane = new ScrollPane();

    Text title = new Text("Welcome, Jhon Doe!");

    TileBlock one = new TileBlock(Icon.CHAT, "39", "Messages", "-info");
    TileBlock two = new TileBlock(Icon.SHOPPING_CART, "125", "Sales", "-danger");
    TileBlock three = new TileBlock(Icon.NOTIFICATION_IMPORTANT, "4", "Notifications", "-warning");
    TileBlock four = new TileBlock(Icon.TODAY, "18", "Schedules", "-success");
    TableView<Activity> tableView = new TableView<>();
    Node boxTable = createBox("Hardware", tableView);
    BarChart<String, Number> barChart = createBarchart();
    DonutChart donutChart = createDonut();

    Node boxAudience = createBox("Technologies", createListView());
    Node boxLineChart = createBox("Sales", createLineChart());

    public Dashboard() {
        scrollPane.setFitToHeight(true);
        scrollPane.setFitToWidth(true);
        scrollPane.setContent(grid);
        grid.setPadding(new Insets(20));
        getChildren().add(scrollPane);

        grid.setHgap(10);
        grid.setVgap(10);

        title.getStyleClass().addAll("h3", "font-instagram");
        title.minHeight(180);

        tableView.getStyleClass().addAll("transparent-table");
        tableView.setMinHeight(300);
        tableView.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY_ALL_COLUMNS);

        tableView.getItems().addAll(
                new Activity(
                        new Company(Assets.getImage("technology/chip.png", 80), "Intel Core i7-10700F", "Intel"),
                        Type.CORPORATE, Status.BUSY, new BigDecimal(225132),
                        new User(Assets.getImage("avatar1.png"), "Username", "Name"),
                        new User(Assets.getImage("avatar3.png"), "Username", "Name"),
                        new User(Assets.getImage("avatar4.png"), "Username", "Name"),
                        new User(Assets.getImage("avatar2.jpg"), "Username", "Name"),
                        new User(Assets.getImage("default_avatar.jpg"), "Username", "Name"),
                        new User(Assets.getImage("default_avatar.jpg"), "Username", "Name"),
                        new User(Assets.getImage("default_avatar.jpg"), "Username", "Name"),
                        new User(Assets.getImage("default_avatar.jpg"), "Username", "Name"),
                        new User(Assets.getImage("default_avatar.jpg"), "Username", "Name"),
                        new User(Assets.getImage("default_avatar.jpg"), "Username", "Name")
                ),
                new Activity(
                        new Company(Assets.getImage("technology/motherboard.png", 70), "Asus TUF Gaming Z490-Plus (Wi-Fi)", "Asus"),
                        Type.CONVENTIONAL, Status.FREE, new BigDecimal(35154),
                        new User(Assets.getImage("avatar1.png"), "Username", "Name"),
                        new User(Assets.getImage("avatar2.jpg"), "Username", "Name"),
                        new User(Assets.getImage("avatar4.png"), "Username", "Name")
                ),
                new Activity(
                        new Company(Assets.getImage("technology/computer.png", 90), "Gamer Sharkoon Pure Steel White RGB", "Microsoft Way/Redmond"),
                        Type.CONVENTIONAL, Status.FREE, new BigDecimal(35154),
                        new User(Assets.getImage("avatar1.png"), "Username", "Name")
                ),
                new Activity(
                        new Company(Assets.getImage("technology/ram-memory.png", 80), "Patriot Viper Steel 16GB", "Menlo Park, California"),
                        Type.CORPORATE, Status.BUSY, new BigDecimal(225132),
                        new User(Assets.getImage("avatar1.png"), "Username", "Name"),
                        new User(Assets.getImage("avatar3.png"), "Username", "Name"),
                        new User(Assets.getImage("avatar4.png"), "Username", "Name"),
                        new User(Assets.getImage("avatar2.jpg"), "Username", "Name"),
                        new User(Assets.getImage("default_avatar.jpg"), "Username", "Name")
                )
        );

        TableColumn<Activity, Company> nameColumn = new TableColumn<>("Name");
        TableColumn<Activity, Type> typeColumn = new TableColumn<>("Type");
        TableColumn<Activity, Status> statusColumn = new TableColumn<>("Status");
        TableColumn<Activity, ObservableList<User>> usersColumn = new TableColumn<>("Users");
        TableColumn<Activity, BigDecimal> incomeColumn = new TableColumn<>("Income");

        nameColumn.setCellValueFactory(new PropertyValueFactory<>("company"));
        typeColumn.setCellValueFactory(new PropertyValueFactory<>("type"));
        statusColumn.setCellValueFactory(new PropertyValueFactory<>("status"));
        usersColumn.setCellValueFactory(new PropertyValueFactory<>("users"));
        incomeColumn.setCellValueFactory(new PropertyValueFactory<>("income"));

        nameColumn.setCellFactory(new TableCellCompanyFactory());
        typeColumn.setCellFactory(new TableCellTypeFactory());
        statusColumn.setCellFactory(new TableCellStatusFactory());
        usersColumn.setCellFactory(new TableCellUsersFactory());
        incomeColumn.setCellFactory(new MonetaryCellFactory<>());

        tableView.getColumns().addAll(nameColumn, typeColumn, statusColumn, usersColumn, incomeColumn);

        for (TableColumn column : tableView.getColumns()) {
            column.setMinWidth(100);
        }

        GridPane.setColumnSpan(tableView, GridPane.REMAINING);


        grid.getChildren().addAll(title, one, two, three, four, barChart, donutChart, boxAudience, boxTable, boxLineChart);
//        grid.getChildren().addAll(title, one, two, three, four);

        for (Node node : grid.getChildren()) {
            GridPane.setHgrow(node, Priority.ALWAYS);
            GridPane.setVgrow(node, Priority.ALWAYS);
        }

        ColumnConstraints col1 = new ColumnConstraints();
        col1.setPercentWidth(25);
        ColumnConstraints col2 = new ColumnConstraints();
        col2.setPercentWidth(25);
        ColumnConstraints col3 = new ColumnConstraints();
        col3.setPercentWidth(25);
        ColumnConstraints col4 = new ColumnConstraints();
        col4.setPercentWidth(25);
        grid.getColumnConstraints().setAll(col1, col2, col3, col4);
        grid.getColumnConstraints().forEach(el -> el.setMinWidth(0));
        RowConstraints rowOne = new RowConstraints();
        rowOne.setMaxHeight(90);
        grid.getRowConstraints().add(rowOne);


    }

    @Override
    public void onEnter(Root root) {
        root.addPoint(_ -> {
//            getColumnConstraints().clear();
            GridPane.setConstraints(title, 0, 0, 1,1);
            GridPane.setConstraints(one, 0, 1, 2,1);
            GridPane.setConstraints(two, 2, 1,2,1);
            GridPane.setConstraints(three, 0, 2,2,1);
            GridPane.setConstraints(four, 2, 2,2,1);

            GridPane.setConstraints(barChart, 0, 3,4,1);
            GridPane.setConstraints(donutChart, 0, 4, 4,1);

            GridPane.setConstraints(boxAudience, 0, 5, 4,1);
            GridPane.setConstraints(boxTable, 0, 6,4,1);
            GridPane.setConstraints(boxLineChart, 0, 7,4,1);

//            GridPane.setConstraints(boxLineChart, 0, 9,4,1);
        },  Break.MD, Break.SM, Break.MOBILE);

        root.addPoint(_ -> {
//            getRowConstraints().clear();
            GridPane.setConstraints(title, 0, 0, 1,1);
            GridPane.setConstraints(one, 0, 1, 2,1);
            GridPane.setConstraints(two, 2, 1,2,1);
            GridPane.setConstraints(three, 0, 2,2,1);
            GridPane.setConstraints(four, 2, 2,2,1);

            GridPane.setConstraints(barChart, 0, 3,4,1);
            GridPane.setConstraints(donutChart, 0, 4, 4,1);

            GridPane.setConstraints(boxAudience, 0, 5, 4,1);
            GridPane.setConstraints(boxTable, 0, 6,4,1);
            GridPane.setConstraints(boxLineChart, 0, 7,4,1);
        },  Break.LG);

        root.addPoint(_ -> {
//            getRowConstraints().clear();
            GridPane.setConstraints(title, 0, 0, 1,1);
            GridPane.setConstraints(one, 0, 1, 1,1);
            GridPane.setConstraints(two, 1, 1,1,1);
            GridPane.setConstraints(three, 2, 1,1,1);
            GridPane.setConstraints(four, 3, 1,1,1);

            GridPane.setConstraints(barChart, 0, 2,3,1);
            GridPane.setConstraints(donutChart, 3, 2, 1,1);

            GridPane.setConstraints(boxAudience, 0, 3, 1,1);
            GridPane.setConstraints(boxTable, 1, 3,2,1);
            GridPane.setConstraints(boxLineChart, 3, 3,2,1);
//
        }, Break.XL, Break.XXL, Break.WIDE);
    }

    private Node createLineChart() {
        NumberAxis xAxis = new NumberAxis();
        xAxis.setLabel("No of employees");

        NumberAxis yAxis = new NumberAxis();
        yAxis.setLabel("Revenue per employee");

        LineChart<Number, Number> lineChart = new LineChart<>(xAxis, yAxis);

        XYChart.Series<Number, Number> dataSeries1 = new XYChart.Series<>();
        dataSeries1.setName("2014");

        dataSeries1.getData().add(new XYChart.Data<>(1, 200));
        dataSeries1.getData().add(new XYChart.Data<>(5, 300));
        dataSeries1.getData().add(new XYChart.Data<>(10, 150));
        dataSeries1.getData().add(new XYChart.Data<>(20, 245));
        dataSeries1.getData().add(new XYChart.Data<>(40, 123));
        dataSeries1.getData().add(new XYChart.Data<>(80, 312));

        lineChart.getData().add(dataSeries1);

        XYChart.Series<Number, Number> dataSeries2 = new XYChart.Series<>();
        dataSeries2.setName("2014");

        dataSeries2.getData().add(new XYChart.Data<>(1, 425));
        dataSeries2.getData().add(new XYChart.Data<>(5, 523));
        dataSeries2.getData().add(new XYChart.Data<>(10, 756));
        dataSeries2.getData().add(new XYChart.Data<>(20, 664));
        dataSeries2.getData().add(new XYChart.Data<>(40, 786));
        dataSeries2.getData().add(new XYChart.Data<>(80, 810));

        lineChart.getData().add(dataSeries2);

        return lineChart;
    }

    public BarChart<String, Number> createBarchart() {
        CategoryAxis xAxis = new CategoryAxis();
        xAxis.setCategories(FXCollections.observableArrayList(
                Arrays.asList("1080 FPS", "1440 FPS", "4K FPS")));
        xAxis.setAnimated(true);
        xAxis.setStartMargin(10);
        xAxis.setEndMargin(10);
        xAxis.setGapStartAndEnd(true);

        NumberAxis yAxis = new NumberAxis(0, 1000, 100);
        yAxis.setLabel("Default FPS");
        yAxis.setAnimated(true);

//        NumberAxis xAxis = new NumberAxis(0, 1000, 100);
//        xAxis.setLabel("Population in Millions");
//        xAxis.setAnimated(true);

        BarChart<String, Number> barChart = new BarChart<>(xAxis, yAxis);
        barChart.setCategoryGap(50);
        barChart.setSnapToPixel(true);
        barChart.setMinHeight(300);
        barChart.setLegendVisible(true);
        barChart.setAnimated(true);
        barChart.setTitle("Graphic Cards Performance");
        barChart.getStyleClass().addAll("border-box", "border-1");
        barChart.getStyleClass().addAll("bg-white", "border-2", "border-light-gray-2", "radius-5");

        XYChart.Series<String, Number> rtx5090 = new XYChart.Series<>();
        rtx5090.setNode(new Label("LUAL"));
        rtx5090.setName("RTX 5090");
        rtx5090.getData().add(new XYChart.Data<>("1080 FPS", 409.7));
        rtx5090.getData().add(new XYChart.Data<>("1440 FPS", 309.2));
        rtx5090.getData().add(new XYChart.Data<>("4K FPS", 194.4));
        barChart.getData().add(rtx5090);

        XYChart.Series<String, Number> rtx4090 = new XYChart.Series<>();
        rtx4090.setName("RTX 4090");
        rtx4090.getData().add(new XYChart.Data<>("1080 FPS", 356.1));
        rtx4090.getData().add(new XYChart.Data<>("1440 FPS", 272.5));
        rtx4090.getData().add(new XYChart.Data<>("4K FPS", 172.7));
        barChart.getData().add(rtx4090);

        XYChart.Series<String, Number> rtx4080ti = new XYChart.Series<>();
        rtx4080ti.setName("RTX 4080 Ti");
        rtx4080ti.getData().add(new XYChart.Data<>("1080 FPS", 332.3));
        rtx4080ti.getData().add(new XYChart.Data<>("1440 FPS", 254.3));
        rtx4080ti.getData().add(new XYChart.Data<>("4K FPS", 161.2));
        barChart.getData().add(rtx4080ti);

        XYChart.Series<String, Number> rtx4080 = new XYChart.Series<>();
        rtx4080.setName("RTX 4080 Ti");
        rtx4080.getData().add(new XYChart.Data<>("1080 FPS", 308.6));
        rtx4080.getData().add(new XYChart.Data<>("1440 FPS", 236));
        rtx4080.getData().add(new XYChart.Data<>("4K FPS", 149.6));
        barChart.getData().add(rtx4080);

        return barChart;
    }

    private DonutChart createDonut() {
        DonutChart donutChart = new DonutChart();
        donutChart.getStyleClass().addAll("bg-white", "border-2", "border-light-gray-2", "radius-5");
        ObservableList<PieChart.Data> data = FXCollections.observableArrayList();
        donutChart.setTitle("Most Valuable Parts");
        donutChart.setAnimated(true);
        donutChart.setLabelsVisible(true);
        donutChart.setLabelLineLength(10);
        donutChart.setMinHeight(300);

        data.add(new PieChart.Data("Chip", 2_199.88));
        data.add(new PieChart.Data("Monitor 4k", 1_264.98));
        data.add(new PieChart.Data("Motherboard", 1_552.82));
        data.add(new PieChart.Data("Video Card", 3_800.55));

//        donutChart.setMaxWidth(325);

        donutChart.setData(data);
        return donutChart;
    }

    private int count = 0;

    private Node createBox(String _title, Node node) {

        VBox box = new VBox();
        box.getStyleClass().addAll("bg-white", "border-2", "border-light-gray-2", "radius-5");
        box.setPadding(new Insets(20));

        Text title = new Text(_title);
        title.setStyle("-fx-font-family: \"Instagram Sans\";");
        title.getStyleClass().add("h3");

        box.getChildren().addAll(title, new Separator(), node);
        box.setMinHeight(300);

        return box;
    }


    private Node createListView() {

        ListView<CountryBox> listView = new ListView<>();
        listView.setStyle("-fx-fixed-cell-size: 60px;");
        listView.setItems(FXCollections.observableArrayList(
                new CountryBox(Assets.getImage("united-states.png", 60), "United States", 0.9),
                new CountryBox(Assets.getImage("united-kingdom.png", 60), "United Kingdom", 0.85),
                new CountryBox(Assets.getImage("brazil-.png", 60), "Brazil", 0.65)
        ));
        count = 0;
        List<String> colors = List.of("accent", "danger", "success");
        listView.setCellFactory(_ -> new ListCell<>() {

            @Override
            protected void updateItem(CountryBox item, boolean empty) {
                if (item != null) {

                    GridPane grid = new GridPane();
                    grid.setHgap(10);
                    grid.setVgap(10);

                    Label text = new Label(item.getTitle());
                    text.getStyleClass().addAll("bold", "h5");
                    text.setGraphicTextGap(10);

                    text.setGraphic(new AvatarView(item.getAvatar(), 15));

                    ProgressBar progressBar = new ProgressBar();

                    progressBar.getStyleClass().addAll("bg-" + colors.get(getIndex()));
                    progressBar.progressProperty().bind(item.percentageProperty());

                    Text legend = new Text((item.getPercentage() * 100) + "%");
                    legend.getStyleClass().addAll("bold", "h5");

                    grid.add(text, 0, 0);
                    grid.add(progressBar, 0, 1);
                    grid.add(legend, 1, 0);

                    GridPane.setHgrow(text, Priority.ALWAYS);
                    GridPane.setHgrow(progressBar, Priority.ALWAYS);
                    GridPane.setVgrow(text, Priority.ALWAYS);

                    GridPane.setColumnSpan(progressBar, GridPane.REMAINING);
                    progressBar.setMaxWidth(Double.MAX_VALUE);

                    setGraphic(grid);
                    setText(null);
                } else {
                    setText(null);
                    setItem(null);
                }
            }
        });


        return listView;
    }


}


