package io.github.gleidsonmt.dashboardfx.dashboard;

import javafx.css.PseudoClass;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.ContentDisplay;
import javafx.scene.control.ToggleButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.*;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.StrokeType;
import javafx.scene.text.Text;

import java.util.Arrays;

/**
 * @author Gleidson Neves da Silveira | <a href="mailto:gleidisonmt@gmail.com">gleidisonmt@gmail.com</a> <br>
 * Created on 11/06/2026
 */
public class ThemeBlock extends FlowPane {

    private ToggleGroup group;

    public ThemeBlock(Theme theme) {
        this.group = new ToggleGroup();
        setHgap(10);
        setVgap(10);
        getChildren().setAll(
//                createBlock("Auto", "", "", "text-"),
//                createBlock("Light", "white", "light-gray-2", "-dark-gray"),
//                createBlock("Dark", "elegant", "dark-gray", "white")
                createAutoBlock(),
                createBlockLight(),
                createBlockDark()
        );
    }

    private Pane createAutoBlock() {
        var light = createBlock(80, 80, "Light", "white", "light-gray-2", "-dark-gray", false);
        var dark = createBlock(80, 80, "Dark", "elegant", "dark-gray", "white", false);

        light.setStyle(light.getStyle() + "-fx-border-radius: 0px 0px 0px 0px; -fx-background-radius: 10px 0px 0px 10px; ");
        dark.setStyle(dark.getStyle() + "-fx-border-radius: 0px 0px 0px 0px; -fx-background-radius: 0px 10px 10px 0px; ");

        HBox container = new HBox(
               light, dark
        );

        return createContainer("Auto", container);
    }

    private ToggleButton createToggleButton(double width, double height) {
        ToggleButton button = new ToggleButton();
        button.setMouseTransparent(true);
        button.setStyle("-fx-border-radius: 10px; -fx-background-radius: 10px; -fx-border-width: 2px; -fx-border-color: transparent; ");
        button.setMinSize(width, height);
        button.setContentDisplay(ContentDisplay.GRAPHIC_ONLY);
        return button;
    }

    private VBox createPane(double width, double height, String background, String foreground, String textColor) {
        VBox pane = new VBox();
        pane.setMouseTransparent(true);
        pane.getStyleClass().add("bg-" + background);
        pane.getStyleClass().addAll("min-w-80 min-h-80 padding-5".split(" "));
        pane.setStyle("-fx-border-radius: 10px 0px 0px 0px; -fx-background-radius: 10px 0px 0px 0px; -fx-border-color: -medium-gray-2; ");
        Text insideText = new Text("Aa");
        insideText.getStyleClass().addAll("text-" + textColor, "h4");
        pane.getChildren().add(insideText);
        return pane;
    }

    private VBox createContainer(String text, Node container) {

        ToggleButton button = new ToggleButton();
        group.getToggles().add(button);

        Text title = new Text(text);
        title.getStyleClass().addAll("h5", "bold");
        VBox box = new VBox(button);
        box.setStyle("-fx-border-radius: 10px; -fx-background-radius: 10px; -fx-border-width: 2px; -fx-border-color: transparent; ");

        box.setSpacing(10);
        box.setAlignment(Pos.BOTTOM_CENTER);
        box.getChildren().add(title);

        button.setGraphic(container);
        button.setContentDisplay(ContentDisplay.GRAPHIC_ONLY);
        button.getStyleClass().add("theme-container");

        return box;
    }

    private Node createBlockLight() {
        var wrapper = createContainer("Light",
                createBlock(80, 80, "Light", "white", "light-gray-2", "-dark-gray"));
        return wrapper;
    }

    private Node createBlockDark() {
        var wrapper = createContainer("Dark",
                createBlock(80, 80, "Dark", "elegant", "dark-gray", "white"));
        return wrapper;
    }

    private StackPane createBlock(double boxWidth, double boxHeight, String text, String background, String foreground, String textColor) {
        return createBlock(boxWidth, boxHeight, text, background, foreground, textColor, true);
    }

    private StackPane createBlock(double boxWidth, double boxHeight, String text, String background, String foreground, String textColor, boolean useArc) {
//        var boxHeight = 80;
//        var boxWidth = 120;

//        ToggleButton button = createToggleButton(boxWidth, boxHeight);
        Pane pane = createPane(boxWidth, boxHeight, background, foreground, textColor);

//        button.setGraphic(pane);
        Rectangle clip = new Rectangle();
        clip.setMouseTransparent(true);
        clip.setWidth(boxWidth + 4);
        clip.setHeight(boxHeight );
        if (useArc) {
            clip.setArcWidth(15);
            clip.setArcHeight(15);
        }

        pane.setTranslateX(20);
        pane.setTranslateY(20);
//        button.setClip(clip);

        StackPane container = new StackPane();

        container.getChildren().add(pane);
        container.setPrefSize(boxWidth +4, boxHeight+4);
        container.getStyleClass().addAll("bg-" + foreground, "border-2");

        container.setClip(clip);

        return container;
    }
}

enum Theme {
    AUTO, LIGHT, DARK
}