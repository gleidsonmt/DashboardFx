package io.github.gleidsonmt.dashboardfx.dashboard;

import io.github.gleidsonmt.dashboardfx.Main;
import io.github.gleidsonmt.glad.base.Flow;
import io.github.gleidsonmt.glad.base.Root;
import io.github.gleidsonmt.glad.controls.icon.Icon;
import io.github.gleidsonmt.glad.controls.icon.SVGIcon;
import io.github.gleidsonmt.glad.controls.toggle_switch.ToggleSwitch;
import javafx.collections.FXCollections;
import javafx.geometry.Insets;
import javafx.geometry.Orientation;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.StrokeType;
import javafx.scene.text.Text;
import org.jetbrains.annotations.NotNull;

/**
 * @author Gleidson Neves da Silveira | gleidisonmt@gmail.com
 * Create on  28/03/2025
 */
public class Aside extends VBox {

    public Aside() {
        this.setMinWidth(250);
        ToggleSwitch _switch = new ToggleSwitch();
        sceneProperty().addListener((_, _, newVal) -> {
            // Updates toggle based on initial grid bar visibility
            if (newVal != null) {
                Root root = (Root) getScene().getRoot();
                _switch.setOn(((Main) root.getContent()).getTop() != null);
            }
        });

        // Toggles grid bar visibility based on the switch state
        _switch.onProperty().addListener((_, _, newVal) -> {
            Root root = (Root) getScene().getRoot();
            if (newVal) {
                ((Main) root.getContent()).setTop(new GridBarView());
            } else {
                ((Main) root.getContent()).setTop(null);
            }
        });

        getChildren().addAll(
                header(),
//                createBlock(),
                createBlock("Language", "Select the language of the platform", createCombo()),
                createBlock("Grid View", "Set the visible pane grid ", _switch),
                createBlock("Interface theme", "Customize your application appearance ", createThemeBlock(), Orientation.VERTICAL),
                createBlock("Accent color", "Pick your platform's main color ", createFlow(
                                createBox("Primary"),
                                createBox("Info"),
                                createBox("Success"),
                                createBox("Warning"),
                                createBox("Danger"),
                                createBox("Secondary"),
                                createBox("Unique"),
                                createBox("Elegant")
                        ), Orientation.VERTICAL
                ));
        setPadding(new Insets(20));
        getStyleClass().addAll("bg-white", "border-l-2", "border-light-gray-2");
    }

    private FlowPane createThemeBlock() {
        FlowPane flow = new FlowPane();
        flow.setHgap(5);
        flow.setVgap(5);
        flow.getChildren().addAll(
                createToggleButton("Auto", false),
                createToggleButton("Light", true),
                createToggleButton("Dark", false)
        );
        return flow;
    }

    private StackPane createToggleButton(String text, boolean selected) {

        ToggleButton button = new ToggleButton(text);

        button.getStyleClass().addAll("display-bottom".split(""));
        button.setStyle("-fx-border-radius: 10px; -fx-background-radius: 10px; -fx-border-width: 2px; -fx-border-color: transparent; -fx-background-color: transparent; -fx-border-color: transparent;");
        button.setMinSize(120, 120);
        Pane pane = new Pane();
        pane.getStyleClass().addAll("min-w-80 min-h-80  bg-light-gray border-2 border-white".split(" "));
        pane.setStyle("-fx-border-radius: 10px 0px 0px 0px; -fx-border-color: green;");
        button.setGraphic(pane);
        Rectangle clip = new Rectangle();
        clip.setStrokeType(StrokeType.INSIDE);
        clip.setStyle("-fx-background-color: transparent; -fx-border-width: 2px; ");
        clip.setWidth(122);
        clip.setHeight(122);
        clip.setArcWidth(20);
        clip.setArcHeight(20);

        pane.setTranslateX(30);
        pane.setTranslateY(10);
        button.setClip(clip);

        StackPane container = new StackPane(button);
        container.setPrefSize(122, 122);
        container.setStyle("-fx-border-radius: 10px; -fx-border-color: pink; -fx-border-width: 2px; -fx-padding: 3px;");

//        pane.setClip(clip);
//
//        button.getStyleClass().addAll("size-30", "border-light-gray-2", "text-elegant", "padding-5", "radius-5");
        button.setContentDisplay(ContentDisplay.BOTTOM);
//        Pane block;
//        if (text.toLowerCase().equals("light")) {
//            block  = createBlockLight();
//            button.getStyleClass().add("bg-light-gray");
//        } else if (text.toLowerCase().equals("dark")) {
//            button.getStyleClass().addAll("bg-dark-gray", "border-elegant", "border-2");
//            block = createBlockDark();
//        } else {
//            button.getStyleClass().add("bg-light-gray");
//            block = createBlockDark();
//        }
//        button.setGraphic(block);
//        button.setSelected(selected);
        return container;
    }

    private Pane createBlockDark() {
        Pane pane = new Pane();
        pane.getStyleClass().addAll("min-w-100 min-h-100 radius-2 bg-dark-gray".split(" "));
        Pane foreground = new Pane();
        foreground.setLayoutX(20);
        foreground.setLayoutY(20);
        foreground.getStyleClass().addAll("bg-elegant", "radius-2", "min-w-100", "min-h-100", "radius-10");
        pane.getChildren().addAll(foreground);
        Text text = new Text("Aa");
        text.getStyleClass().addAll("text-white");
        text.setLayoutX(30);
        text.setLayoutY(50);
        pane.getChildren().add(text);

        return pane;
    }

    private Pane createBlockLight() {
        Pane pane = new Pane();
        pane.getStyleClass().addAll("min-w-100 min-h-100 radius-2 bg-light-gray".split(" "));
        Pane foreground = new Pane();
        foreground.setLayoutX(20);
        foreground.setLayoutY(20);
        foreground.getStyleClass().addAll("bg-white", "radius-2", "bg-white", "min-w-100", "min-h-100", "radius-10");
        pane.getChildren().addAll(foreground);
        Text text = new Text("Aa");
        text.setLayoutX(30);
        text.setLayoutY(50);
        pane.getChildren().add(text);

        return pane;
    }

    private FlowPane createFlow(Node... node) {
        FlowPane flow = new FlowPane();
        flow.setHgap(5);
        flow.setVgap(5);
        flow.getChildren().addAll(node);
        return flow;
    }

    private @NotNull ComboBox<String> createCombo() {
        ComboBox<String> comboBox = new ComboBox<>();
        comboBox.setItems(FXCollections.observableArrayList("English"));
        comboBox.getSelectionModel().select(0);
        return comboBox;
    }

    private @NotNull Node createBlock() {
        return createBlock("Appearance", "Set or customize your preferences for the system", null);
    }

    private VBox createBox(String color) {
        VBox box = new VBox();
        box.getStyleClass().add("bg-" + color.toLowerCase());
        box.getStyleClass().addAll("min-w-50 min-h-50 radius-2 h6 bold".split(" "));
        return box;
    }

    private Label createLabel(String text) {
        var label = new Label(text);
        label.getStyleClass().addAll("text-white".split(" "));
        return label;
    }


    /**
     * Creates a titled, legend ‑ described, node‑containing grid block
     */
    private @NotNull Node createBlock(String _title, String _legend, Node node) {
        return createBlock(_title, _legend, node, Orientation.HORIZONTAL);
    }
    private @NotNull Node createBlock(String _title, String _legend, Node node, Orientation orientation) {
        Text title = new Text(_title);
        title.getStyleClass().addAll("h4", "bold");
        Text legend = new Text(_legend);
        legend.getStyleClass().addAll("h6");

        GridPane grid = new GridPane();
        Separator separator = new Separator();

        grid.add(title, 0, 0);
        grid.add(legend, 0, 1);

        GridPane.setHgrow(legend, Priority.ALWAYS);

        if (node != null) {
            if (orientation == Orientation.HORIZONTAL) {
                grid.add(node, 1, 1);
            } else {
                grid.add(node, 0, 2);
            }
            grid.add(separator, 0, 3, GridPane.REMAINING, 1);
        } else {
            grid.add(separator, 0, 2, GridPane.REMAINING, 1);

        }

        grid.setVgap(10);
        grid.setHgap(10);
        return grid;
    }

    /**
     * Builds header with a close button; defines close action
     */
    private GridPane header() {

        Text title = new Text("Appearance");
        title.getStyleClass().addAll("h4", "bold");

        Button close = new Button();
        close.setGraphic(new SVGIcon(Icon.CLEAR));
        close.getStyleClass().addAll("size-30", "bg-light-gray", "round", "border-light-gray-2");
        close.setStyle("-icon-color: -dark-gray;");
        GridPane box = new GridPane();
        box.add(title, 0, 0);
        box.add(close, 1, 0);
        Text legend = new Text("Set or customize your preferences for the system");
        legend.getStyleClass().addAll("h6");
        box.add(legend, 0, 1);
        legend.getStyleClass().addAll("h6");
        var separator = new Separator();
        box.add(separator, 0, 2);
        GridPane.setColumnSpan(separator, 2);
        box.setVgap(10);

        GridPane.setHgrow(title, Priority.ALWAYS);

        box.setAlignment(Pos.CENTER_LEFT);
        close.setOnAction(_ -> {
            Root main = (Root) getScene().getRoot();
            main.behavior().dialog().hide();
        });
        return box;
    }

}
