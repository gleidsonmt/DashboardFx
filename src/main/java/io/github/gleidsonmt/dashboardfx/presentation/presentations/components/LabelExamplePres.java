package io.github.gleidsonmt.dashboardfx.presentation.presentations.components;

import io.github.gleidsonmt.dashboardfx.presentation.CustomizablePresentation;
import io.github.gleidsonmt.dashboardfx.presentation.internal.Tutorial;
import io.github.gleidsonmt.presentation.Presentation;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.layout.Region;

/**
 * @author Gleidson Neves da Silveira | gleidisonmt@gmail.com
 * Create on  10/04/2025
 */
public class LabelExamplePres extends CustomizablePresentation {

    public LabelExamplePres() {
        super("Label Example");
    }

    public Presentation create() {
        return new Tutorial()
                .h3("Label")
                .separator()
                .text("Label style examples.")
                .demo(new Node[]{
                        firstDemo( "danger"),
                        firstDemo( "warning"),
                        firstDemo( "success"),
                        firstDemo( "info")
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
                        secondDemo("Danger", "bg-danger", "bold", "stroke-white", "rounded", "text-white"),
                        secondDemo("Warning", "bg-warning", "bold", "stroke-white", "rounded", "text-white"),
                        secondDemo("Success", "bg-success", "bold", "stroke-white", "rounded", "text-white"),
                        secondDemo("Info", "bg-info", "bold", "stroke-white", "rounded", "text-white")
                })
                .code("""
                        Label label = new Label(text);
                        // Try to update bg-primary for your color like bg-[color_name]
                        label.getStyleClass().addAll("bg-primary", "text-white", "padding-5", "rounded", "min-size-20", "align-center");
                        """)

                .demo(new Node[]{
                        thirdDemo( "heart", "bg-blue", "size-20", "display-graphic"),
                        thirdDemo( "star", "bg-warning", "size-20", "display-graphic"),
                        thirdDemo( "star-2", "bg-amber", "size-20", "display-graphic"),
                        thirdDemo( "pac", "bg-yellow", "size-20", "display-graphic"),
                        thirdDemo( "leaf", "bg-green", "size-20", "display-graphic"),
                        thirdDemo( "jelly", "bg-purple", "size-20", "display-graphic"),
                        thirdDemo( "parallelogram-r", "bg-purple", "size-20", "display-graphic"),
                        thirdDemo( "parallelogram-l", "bg-mint", "size-20", "display-graphic"),
                        thirdDemo( "comb-left", "bg-red-700", "size-20", "display-graphic"),
                        thirdDemo( "comb-right", "bg-red-700", "size-20", "display-graphic"),
                        thirdDemo( "comb-open", "bg-red-700", "size-20", "display-graphic"),
                        thirdDemo( "hexagon", "bg-red-700", "size-20", "display-graphic"),
                        thirdDemo( "round-bl", "bg-red-700", "size-20", "display-graphic"),
                        thirdDemo( "lbl", "bg-success", "size-20", "display-graphic")
                })
                .code("""
                        // Possibles names
                        // heart, star, star-2, pac, leaf, jelly, lbl
                        // replace by [name] for one of the above.
                        label.getStyleClass().addAll("[name]", min-size-20", "size-20",  "bg-danger", "padding-5");
                        """)

                .demo(createDemo(createDemo("min-size-20", "size-20", "bg-warning", "star", "padding-5"), "bold"));

    }

    private Label createDemo(String... _classes) {
        return createDemo(null, _classes);
    }

    private Label createDemo(Node graphic, String... _classes) {
        Label text = new Label("4.7");
        text.setGraphic(graphic);
        text.getStyleClass().addAll(_classes);
        text.getStyleClass().addAll("min-size-20 padding-5 bold".split(" "));
        return text;
    }

    private Region createCircleGraphic(String color) {
        Region region = new Region();
        region.setMinSize(20, 20);
        region.getStyleClass().addAll("bg-" + color, "round", "bg-insets-5", "border-5");

        region.setStyle("-fx-border-color: derive(-" + color + ", 90%);");
        return region;
    }

    private Label firstDemo(String color) {
        return demo(color, createCircleGraphic(color));
    }

    private Label secondDemo(String color, String... _classes) {
        return demo(color, null, _classes);
    }

    private Label thirdDemo(String... _classes) {
        return demo("Third", null, _classes);
    }

    private Label demo(String _text, Node _graphic, String... _classes) {
        Label label = new Label(_text);
        label.setGraphic(_graphic);
        label.getStyleClass().addAll(_classes);
        label.getStyleClass().addAll("min-size-20", "padding-5");
        return label;
    }

}
