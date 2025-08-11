package io.github.gleidsonmt.dashboardfx.dashboard;

import io.github.gleidsonmt.dashboardfx.Main;
import io.github.gleidsonmt.glad.base.Root;
import io.github.gleidsonmt.glad.controls.icon.Icon;
import io.github.gleidsonmt.glad.controls.icon.SVGIcon;
import io.github.gleidsonmt.glad.controls.toggle_switch.ToggleSwitch;
import javafx.collections.FXCollections;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Separator;
import javafx.scene.layout.*;
import javafx.scene.text.Text;
import org.jetbrains.annotations.NotNull;

/**
 * @author Gleidson Neves da Silveira | gleidisonmt@gmail.com
 * Create on  28/03/2025
 */
public class Aside extends VBox {
//
    public Aside() {
        this.setMinWidth(250);
        ToggleSwitch _switch = new ToggleSwitch();
        sceneProperty().addListener((_, _, newVal) -> {
            if (newVal != null) {
                Root root = (Root) getScene().getRoot();
                _switch.setOn(((Main) root.getLayout()).getTop() != null);
            }
        });

        _switch.onProperty().addListener((_, _, newVal) -> {
            Root root = (Root) getScene().getRoot();
            if (newVal) {
                ((Main) root.getLayout()).setTop(new GridBarView());
            } else {
                ((Main) root.getLayout()).setTop(null);
            }
        });
        getChildren().addAll(
                header(),
                createBlock(),
                createBlock("Language", "Select the language of the platform", createCombo()),
                createBlock("Grid View", "Set the visible pane grid ", _switch)
        );
        setPadding(new Insets(20));
        getStyleClass().addAll("bg-white", "border-l-2", "border-light-gray-2");
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

    private @NotNull Node createBlock(String _title, String _legend, Node node) {
        Text title = new Text(_title);
        title.getStyleClass().addAll("h5", "bold");
        Text legend = new Text(_legend);
        legend.getStyleClass().addAll("h6");

        GridPane grid = new GridPane();
        Separator separator = new Separator();

        grid.add(title, 0,0);
        grid.add(legend, 0,1);
        grid.add(separator, 0,2, GridPane.REMAINING,1);

        GridPane.setHgrow(legend, Priority.ALWAYS);

        if (node != null) {
           grid.add(node, 1,1);
        }

        grid.setVgap(10);
        grid.setHgap(10);
        return grid;
    }

    private HBox header() {
        Button close = new Button();
        close.setGraphic(new SVGIcon(Icon.CLEAR));
        close.getStyleClass().addAll("size-30", "bg-light-gray", "round", "border-light-gray-2");
        close.setStyle("-icon-color: -dark-gray;");
        HBox box = new HBox(close);
        box.setAlignment(Pos.CENTER_RIGHT);
        close.setOnAction(_-> {
            Root main = (Root) getScene().getRoot();
            main.wrapper().hide();
            main.flow().remove(this);
            ((BorderPane) main.getLayout()).setRight(null);
        });
        return box;
    }

}
