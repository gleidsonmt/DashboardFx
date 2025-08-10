package io.github.gleidsonmt.dashboardfx.dashboard;

import io.github.gleidsonmt.dashboardfx.model.Hardware;
import io.github.gleidsonmt.dashboardfx.model.LanguageExperience;
import io.github.gleidsonmt.dashboardfx.utils.Assets;
import io.github.gleidsonmt.glad.base.Root;
import io.github.gleidsonmt.glad.base.responsive.Break;
import io.github.gleidsonmt.glad.charts.CurvedChart;
import io.github.gleidsonmt.glad.charts.DonutChart;
import io.github.gleidsonmt.glad.controls.avatar.AvatarView;
import io.github.gleidsonmt.glad.controls.avatar.StackedAvatar;
import io.github.gleidsonmt.glad.controls.icon.Icon;
import io.github.gleidsonmt.glad.controls.icon.SVGIcon;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.CacheHint;
import javafx.scene.Node;
import javafx.scene.chart.*;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.layout.*;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;

import java.math.BigDecimal;
import java.util.Arrays;

/**
 * @author Gleidson Neves da Silveira | gleidisonmt@gmail.com
 * Create on  12/03/2025
 */
public class Dashboard extends StackPane implements ActionableView {

    private final GridPane grid = new GridPane();
    private final ScrollPane scrollPane = new ScrollPane();
    private final Text title = new Text("Welcome, Jhon Doe!");

    private final Widget one = new Widget(Icon.CHAT, "39", "Messages", "-info");
    private final Widget two = new Widget(Icon.SHOPPING_CART, "125", "Sales", "-danger");
    private final Widget three = new Widget(Icon.NOTIFICATION_IMPORTANT, "4", "Notifications", "-warning");
    private final Widget four = new Widget(Icon.TODAY, "18", "Schedules", "-success");

    private final CurvedChart<Number, Number> curvedChart = new ContributionsChart();
    private final BarChart<String, Number> barChart = new VideoCardChart();
    private Node boxTechnologies = new Tile("Technologies", createListView());

    private final DonutChart donutChart = new HardwarePartsDonut();
    private final Node personalCard = new PersonalCard();


    TableView<Hardware> tableView = new TableView<>();
    Node boxTable = new Tile("Hardware", tableView);

    Node boxTeam = new Tile("Total Contributors", createBoxTeam());

    private Node createBoxTeam() {
        GridPane grid = new GridPane();

        Text title = new Text("2,335");
        grid.add(title, 0, 0);
        title.getStyleClass().addAll("h1 font-instagram".split(" "));

        Label percent = new Label("25%");
        percent.getStyleClass().addAll("h5 font-instagram text-success padding-5 border-2 border-success radius-10".split(" "));
        percent.setStyle("-fx-padding: 2px 10px 2px 10px; -fx-background-color: derive(-success, 100%);");
        var arrow = new SVGIcon(Icon.ARROW_RIGHT_ALT);
        arrow.setRotate(-90);
        percent.setGraphic(arrow);
        grid.add(percent, 1, 0);

        StackedAvatar stackedAvatar = new StackedAvatar();
        stackedAvatar.setAvatarSize(80);
        stackedAvatar.setAvatarRadius(80);
        stackedAvatar.setMax(3);
        stackedAvatar.getStyleClass().add("contributors-avatar-stacked");
        stackedAvatar.setSpacing(20);

        stackedAvatar.getAvatarViews().setAll(
                new AvatarView(Assets.getImage("default_avatar.jpg")),
                new AvatarView(Assets.getImage("default_avatar.jpg")),
                new AvatarView(Assets.getImage("default_avatar.jpg"))
        );

        grid.add(stackedAvatar, 0, 1);
        GridPane.setColumnSpan(stackedAvatar, GridPane.REMAINING);
        GridPane.setHgrow(stackedAvatar, Priority.ALWAYS);

        Hyperlink link = new Hyperlink("See all");
        link.setGraphic(new SVGIcon(Icon.ARROW_RIGHT_ALT));
        link.getStyleClass().addAll("h5".split(" "));
        grid.add(link, 0, 2);
        link.setContentDisplay(ContentDisplay.RIGHT);
        link.setAlignment(Pos.CENTER);
        link.setMaxWidth(Double.MAX_VALUE);
        GridPane.setColumnSpan(link, GridPane.REMAINING);

        grid.setPadding(new Insets(20));
        grid.setHgap(10);
        grid.setVgap(10);
        grid.setAlignment(Pos.CENTER);
        VBox.setVgrow(grid, Priority.ALWAYS);
        return grid;
    }




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

        tableView.getItems().addAll(
                new Hardware(
                        Assets.getImage("technology/chip.png", 40), "Intel Core i7-10700F", new BigDecimal("2199.88")
                ),
                new Hardware(
                       Assets.getImage("technology/motherboard.png", 70), "Asus TUF Gaming Z490-Plus (Wi-Fi)", new BigDecimal("1552.82")
                ),
                new Hardware(
                        Assets.getImage("technology/computer.png", 90), "Gamer Sharkoon Pure Steel White RGB", new BigDecimal("670.47")
                ),
                new Hardware(
                        Assets.getImage("technology/ram-memory.png", 80), "Patriot Viper Steel 16GB", new BigDecimal("588.12")
                )
        );

        tableView.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY_ALL_COLUMNS);

        TableColumn<Hardware, Image> avatarColumn = new TableColumn<>("#");
        avatarColumn.setMaxWidth(100);
        TableColumn<Hardware, String> nameColumn = new TableColumn<>("Name");
//        TableColumn<Hardware, Type> typeColumn = new TableColumn<>("Type");
//        TableColumn<Hardware, Status> statusColumn = new TableColumn<>("Status");
//        TableColumn<Hardware, ObservableList<User>> usersColumn = new TableColumn<>("Users");
        TableColumn<Hardware, BigDecimal> incomeColumn = new TableColumn<>("Value");
        incomeColumn.setMaxWidth(100);
        nameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
        avatarColumn.setCellValueFactory(new PropertyValueFactory<>("avatar"));
//        typeColumn.setCellValueFactory(new PropertyValueFactory<>("type"));
//        statusColumn.setCellValueFactory(new PropertyValueFactory<>("status"));
//        usersColumn.setCellValueFactory(new PropertyValueFactory<>("users"));
        incomeColumn.setCellValueFactory(new PropertyValueFactory<>("value"));

        nameColumn.setCellFactory(new TableCellCompanyFactory());
        avatarColumn.setCellFactory(_ -> new TableCell<>() {
            @Override
            protected void updateItem(Image item, boolean empty) {
                if (item != null && !empty) {
                    setGraphic(new AvatarView(item, 0, 40));
                } else {
                    setItem(null);
                    setText(null);
                    setGraphic(null);
                }
            }
        });
//        typeColumn.setCellFactory(new TableCellTypeFactory());
//        statusColumn.setCellFactory(new TableCellStatusFactory());
//        usersColumn.setCellFactory(new TableCellUsersFactory());
        incomeColumn.setCellFactory(new MonetaryCellFactory<>());

        //noinspection unchecked
        tableView.getColumns().addAll(avatarColumn, nameColumn, incomeColumn);

        for (TableColumn<Hardware, ?> column : tableView.getColumns()) {
            column.setMinWidth(100);
        }

        GridPane.setColumnSpan(tableView, GridPane.REMAINING);


//        grid.getChildren().addAll(title, one, two, three, four, barChart, curvedChart, donutChart, boxTechnologies, boxTable, viewBox, boxTeam);
        grid.getChildren().addAll(title, one, two, three, four, curvedChart, barChart, boxTechnologies, donutChart, personalCard);
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
//            grid.getColumnConstraints().clear();
            GridPane.setConstraints(title, 0, 0, GridPane.REMAINING,1);
            GridPane.setConstraints(one, 0, 1, GridPane.REMAINING,1);
            GridPane.setConstraints(two, 0, 2,GridPane.REMAINING,1);
            GridPane.setConstraints(three, 0, 3,GridPane.REMAINING,1);
            GridPane.setConstraints(four, 0, 4,GridPane.REMAINING,1);

            GridPane.setConstraints(curvedChart, 0, 5,GridPane.REMAINING,1);
            GridPane.setConstraints(barChart, 0, 6,GridPane.REMAINING,1);
//            GridPane.setConstraints(donutChart, 0, 4, 4,1);
//
//            GridPane.setConstraints(boxTechnologies, 0, 5, 4,1);
//            GridPane.setConstraints(boxTable, 0, 6,4,1);
//            GridPane.setConstraints(boxLineChart, 0, 7,4,1);

//            GridPane.setConstraints(boxLineChart, 0, 9,4,1);
        },  Break.MD, Break.SM, Break.MOBILE);

        root.addPoint(_ -> {
//            getRowConstraints().clear();
            GridPane.setConstraints(title, 0, 0, 1,1);
            GridPane.setConstraints(one, 0, 1, 2,1);
            GridPane.setConstraints(two, 2, 1,2,1);
            GridPane.setConstraints(three, 0, 2,2,1);
            GridPane.setConstraints(four, 2, 2,2,1);

            GridPane.setConstraints(curvedChart, 0, 3,2,1);
            GridPane.setConstraints(barChart, 2, 3,1,1);
            GridPane.setConstraints(boxTechnologies, 3, 3,1,1);

//            GridPane.setConstraints(donutChart, 0, 4, 4,1);
//
//            GridPane.setConstraints(boxTechnologies, 0, 5, 4,1);
//            GridPane.setConstraints(boxTable, 0, 6,4,1);
//            GridPane.setConstraints(boxLineChart, 0, 7,4,1);
        },  Break.LG);

        root.addPoint(_ -> {
//            getRowConstraints().clear();
            GridPane.setConstraints(title, 0, 0, 1,1);
            GridPane.setConstraints(one, 0, 1, 1,1);
            GridPane.setConstraints(two, 1, 1,1,1);
            GridPane.setConstraints(three, 2, 1,1,1);
            GridPane.setConstraints(four, 3, 1,1,1);

            GridPane.setConstraints(curvedChart, 0, 2,2,1);
            GridPane.setConstraints(barChart, 2, 2,1,1);
            GridPane.setConstraints(boxTechnologies, 3, 2,1,1);

            GridPane.setConstraints(donutChart, 0, 3, 1,1);
            GridPane.setConstraints(personalCard, 1, 3, 1,1);


            GridPane.setConstraints(boxTable, 2, 3,1,1);
            GridPane.setConstraints(boxTeam, 3, 3,1,1);
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

        NumberAxis yAxis = new NumberAxis(0, 500, 100);
        yAxis.setLabel("Default FPS");
        yAxis.setAnimated(true);

        BarChart<String, Number> barChart = new BarChart<>(xAxis, yAxis);
        barChart.setCategoryGap(50);
        barChart.setSnapToPixel(true);
        barChart.setMinHeight(300);
        barChart.setLegendVisible(true);
        barChart.setAnimated(true);
        barChart.setTitle("Graphic Cards Performance");
        barChart.getStyleClass().addAll("border-box", "border-1");
        barChart.getStyleClass().addAll("bg-white border-2 border-light-gray-2 radius-5 font-poppins".split(" "));

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

    private Node createBox(String _title, Node node) {

        VBox box = new VBox();
        box.getStyleClass().addAll("bg-white border-2 border-light-gray-2 radius-5 align-top-center".split(" "));
        box.setPadding(new Insets(5));

        Text title = new Text(_title);
        title.setCacheHint(CacheHint.QUALITY);
//        title.setStyle("-fx-font-family: \"Instagram Sans\";");
        title.getStyleClass().add("h4");

        box.getChildren().addAll(title, node);
        box.setMinHeight(300);

        return box;
    }


    private Node createListView() {

        ListView<LanguageExperience> listView = new ListView<>();
        listView.setStyle("-fx-fixed-cell-size: 80px;");
        listView.setItems(FXCollections.observableArrayList(
                new LanguageExperience("Java", "Focus on learn and teach (java 8, 20+)", 0.96, "info"),
                new LanguageExperience( "React", "Focus on learn and web", 0.65, "secondary"),
                new LanguageExperience( "Javascript", "Focus on learn and web", 0.72, "warning")
        ));
        listView.setCellFactory(_ -> new ListCell<>() {

            @Override
            protected void updateItem(LanguageExperience item, boolean empty) {
                if (item != null) {

                    GridPane grid = new GridPane();
                    grid.setHgap(10);
                    grid.setVgap(10);

                    Text text = new Text(item.getTitle());
                    text.getStyleClass().addAll("h5");
                    ProgressBar progressBar = new ProgressBar();

                    progressBar.getStyleClass().addAll("bg-" + item.getColor());
                    progressBar.progressProperty().bind(item.percentageProperty());

                    Text percent = new Text((item.getPercentage() * 100) + "%");
                    percent.getStyleClass().addAll("bold", "h5");

                    Text legend = new Text(item.getLegend());
                    legend.getStyleClass().addAll( "h6");

                    grid.add(text, 0, 0);
                    grid.add(legend, 0, 1);
                    grid.add(progressBar, 0, 2);

                    grid.add(percent, 1, 0);

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


