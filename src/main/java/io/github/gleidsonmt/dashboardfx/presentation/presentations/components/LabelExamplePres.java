package io.github.gleidsonmt.dashboardfx.presentation.presentations.components;

import io.github.gleidsonmt.dashboardfx.presentation.core.CustomizablePresentation;
import io.github.gleidsonmt.dashboardfx.presentation.internal.Tutorial;
import io.github.gleidsonmt.glad.controls.badge.Badge;
import io.github.gleidsonmt.glad.controls.icon.Icon;
import io.github.gleidsonmt.glad.controls.icon.SVGIcon;
import io.github.gleidsonmt.presentation.Row;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.layout.Region;
import javafx.scene.layout.StackPane;

import java.util.Random;

/**
 * @author Gleidson Neves da Silveira | gleidisonmt@gmail.com
 * Create on  10/04/2025
 */
public class LabelExamplePres extends CustomizablePresentation {

    public LabelExamplePres() {
        super("Label Example");
    }

    public Tutorial create() {
        return new Tutorial()
                .h3("Label")
                .separator()
                .text("Label style examples.")
                .demo(new Node[]{
                        createCustom("Danger", "danger"),
                        createCustom("Warning", "warning"),
                        createCustom("success", "success"),
                        createCustom("info", "info")
                })
                .code("""
                        Region region = new Region();
                        region.setMinSize(20, 20);
                        region.getStyleClass().addAll("bg-primary" , "round", "bg-insets-5", "border-5");
                        
                        region.getStyleClass().addAll("bg-primary");
                        region.setStyle("-fx-border-color: derive(-primary, 90%);");
                        
                        Label label = new Label(text);
                        label.setGraphic(region);
                        """)
                .demo(new Node[]{
                        createCustom2("Danger", "danger"),
                        createCustom2("Warning", "warning"),
                        createCustom2("success", "success"),
                        createCustom2("info", "info")
                })
                .code("""
                        Label label = new Label(text);
                        label.getStyleClass().addAll( "bg-primary", "text-white", "padding-5", "rounded", "min-size-20", "align-center");
                        """)

                .demo(new Node[]{
                        createDemo("", "min-size-20", "size-20", "bg-danger", "heart", "padding-5"),
                        createDemo("", "min-size-20", "size-20", "bg-warning", "star", "padding-5"),
                        createDemo("", "min-size-20", "size-20", "bg-amber", "star-2", "padding-5"),
                        createDemo("", "min-size-20", "size-20", "bg-yellow", "pac", "padding-5"),
                        createDemo("", "min-size-20", "size-20", "bg-green", "leaf", "padding-5"),
                        createDemo("", "min-size-20", "size-20", "bg-purple", "jelly", "padding-5"),
                        createDemo("", "min-size-20", "size-20", "bg-success", "lbl", "padding-5")
                })
                .code("""
                        label.getStyleClass().addAll("min-size-20", "size-20",  "bg-danger", "heart", "padding-5");
                        label.getStyleClass().addAll("min-size-20", "size-20",  "bg-warning", "star", "padding-5");
                        label.getStyleClass().addAll("min-size-20", "size-20",  "bg-amber",  "star-2", "padding-5");
                        label.getStyleClass().addAll("min-size-20", "size-20",  "bg-yellow", "pac", "padding-5");
                        label.getStyleClass().addAll("min-size-20", "size-20",  "bg-green",  "leaf", "padding-5");
                        label.getStyleClass().addAll("min-size-20", "size-20",  "bg-purple", "jelly", "padding-5");
                        label.getStyleClass().addAll("min-size-20", "size-20",  "bg-success", "lbl", "padding-5");
                        """)

                .demo(createDemo(createDemo("", "min-size-20", "size-20", "bg-warning", "star", "padding-5"), "4.7", "bold"));

    }


    private Node createCustom2(String text, String color) {
        Label label = new Label(text);
        label.getStyleClass().addAll("bg-" + color, "text-white", "padding-5", "rounded", "min-size-20", "align-center");
        return label;
    }

    private Node createCustom(String text, String color) {

        Region region = new Region();
        region.setMinSize(20, 20);
        region.getStyleClass().addAll("bg-" + color, "round", "bg-insets-5", "border-5");

        region.getStyleClass().addAll("bg-" + color);
        region.setStyle("-fx-border-color: derive(-" + color + ", 90%);");

        Label label = new Label(text);
        label.setGraphic(region);

        return label;
    }

    private Label createDemo(String _text, String... _classes) {
        return createDemo(null, _text, _classes);
    }

    private Label createDemo(Node graphic, String _text, String... _classes) {
        Label text = new Label(_text);
        text.setGraphic(graphic);
        text.getStyleClass().addAll(_classes);
        return text;
    }

}
