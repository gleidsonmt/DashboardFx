package io.github.gleidsonmt.dashboardfx.dashboard;

import javafx.scene.control.ContentDisplay;
import javafx.scene.control.ToggleButton;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.StrokeType;

/**
 * @author Gleidson Neves da Silveira | <a href="mailto:gleidisonmt@gmail.com">gleidisonmt@gmail.com</a> <br>
 * Created on 11/06/2026
 */
public class ThemeBlock extends FlowPane {

    public ThemeBlock(Theme theme) {
        setHgap(5);
        setVgap(5);
        getChildren().setAll(
                createBlock("Auto"),
                createToggleButton("Light", true),
                createToggleButton("Dark", false)
        );
    }

    private StackPane createBlock(String text) {
        ToggleButton button = new ToggleButton(text);

        button.getStyleClass().addAll("display-bottom".split(""));
        button.setStyle("-fx-border-radius: 10px; -fx-background-radius: 10px; -fx-border-width: 2px; -fx-border-color: transparent; ");
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

        return container;
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


}

enum Theme {
    AUTO, LIGHT, DARK
}