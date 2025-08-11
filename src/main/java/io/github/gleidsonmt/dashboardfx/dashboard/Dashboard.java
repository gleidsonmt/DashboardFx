package io.github.gleidsonmt.dashboardfx.dashboard;

import io.github.gleidsonmt.dashboardfx.model.LanguageExperience;
import io.github.gleidsonmt.glad.base.Root;
import io.github.gleidsonmt.glad.base.responsive.Break;
import io.github.gleidsonmt.glad.charts.CurvedChart;
import io.github.gleidsonmt.glad.charts.DonutChart;
import io.github.gleidsonmt.glad.controls.icon.Icon;
import javafx.collections.FXCollections;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.chart.BarChart;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.control.ProgressBar;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.*;
import javafx.scene.text.Text;

/**
 * @author Gleidson Neves da Silveira | gleidisonmt@gmail.com
 * Create on  12/03/2025
 */
public class Dashboard extends StackPane implements ActionableView {

    private final GridPane grid = new GridPane();
    private final Text title = new Text("Welcome, Jhon Doe!");

    private final Widget one = new Widget(Icon.CHAT, "39", "Messages", "-info");
    private final Widget two = new Widget(Icon.SHOPPING_CART, "125", "Sales", "-danger");
    private final Widget three = new Widget(Icon.NOTIFICATION_IMPORTANT, "4", "Notifications", "-warning");
    private final Widget four = new Widget(Icon.TODAY, "18", "Schedules", "-success");

    private final CurvedChart<Number, Number> curvedChart = new ContributionsChart();
    private final BarChart<String, Number> barChart = new VideoCardChart();
    private final Node boxTechnologies = new Tile("Technologies", createListView());

    private final DonutChart donutChart = new HardwarePartsDonut();
    private final Node personalCard = new PersonalCard();
    private final Node boxTable = new Tile("Hardware", new TableHardware());
    private final Node boxTeam = new Tile("Total Contributors", new BoxTeam());

    public Dashboard() {
        ScrollPane scrollPane = new ScrollPane();
        scrollPane.setFitToHeight(true);
        scrollPane.setFitToWidth(true);
        scrollPane.setContent(grid);
        grid.setPadding(new Insets(20));
        getChildren().add(scrollPane);

        grid.setHgap(10);
        grid.setVgap(10);

        title.getStyleClass().addAll("h3", "font-instagram");
        title.minHeight(180);

        grid.getChildren().addAll(title, one, two, three, four, curvedChart, barChart, boxTechnologies, donutChart, personalCard, boxTable, boxTeam);

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
            int row = 0;
            for (Node child : grid.getChildren()) {
                GridPane.setConstraints(child, 0, row++, GridPane.REMAINING, 1);
            }

        }, Break.MD, Break.SM, Break.MOBILE);

        root.addPoint(_ -> {
            GridPane.setConstraints(title, 0, 0, 1, 1);
            GridPane.setConstraints(one, 0, 1, 2, 1);
            GridPane.setConstraints(two, 2, 1, 2, 1);
            GridPane.setConstraints(three, 0, 2, 2, 1);
            GridPane.setConstraints(four, 2, 2, 2, 1);

            GridPane.setConstraints(curvedChart, 0, 3, 2, 1);
            GridPane.setConstraints(barChart, 2, 3, 2, 1);

            GridPane.setConstraints(boxTechnologies, 0, 4, 2, 1);
            GridPane.setConstraints(donutChart, 2, 4, 2, 1);

            GridPane.setConstraints(personalCard, 0, 5, 2, 1);
            GridPane.setConstraints(boxTeam, 2, 5, 2, 1);

            GridPane.setConstraints(boxTable, 0, 6, 4, 1);
        }, Break.LG, Break.XL, Break.XXL);

        root.addPoint(_ -> {
            GridPane.setConstraints(title, 0, 0, 1, 1);
            GridPane.setConstraints(one, 0, 1, 1, 1);
            GridPane.setConstraints(two, 1, 1, 1, 1);
            GridPane.setConstraints(three, 2, 1, 1, 1);
            GridPane.setConstraints(four, 3, 1, 1, 1);

            GridPane.setConstraints(curvedChart, 0, 2, 2, 1);
            GridPane.setConstraints(barChart, 2, 2, 1, 1);
            GridPane.setConstraints(boxTechnologies, 3, 2, 1, 1);

            GridPane.setConstraints(donutChart, 0, 3, 1, 1);
            GridPane.setConstraints(personalCard, 1, 3, 1, 1);
            GridPane.setConstraints(boxTable, 2, 3, 1, 1);
            GridPane.setConstraints(boxTeam, 3, 3, 1, 1);
//
        }, Break.WIDE);
    }

    private Node createListView() {

        ListView<LanguageExperience> listView = new ListView<>();
        listView.setStyle("-fx-fixed-cell-size: 80px;");
        listView.setItems(FXCollections.observableArrayList(
                new LanguageExperience("Java", "Focus on learn and teach (java 8, 20+)", 0.96, "info"),
                new LanguageExperience("React", "Focus on learn and web", 0.65, "secondary"),
                new LanguageExperience("Javascript", "Focus on learn and web", 0.72, "warning")
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
                    legend.getStyleClass().addAll("h6");

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