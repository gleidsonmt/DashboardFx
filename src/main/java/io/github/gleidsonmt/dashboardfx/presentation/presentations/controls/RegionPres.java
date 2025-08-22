package io.github.gleidsonmt.dashboardfx.presentation.presentations.controls;

import io.github.gleidsonmt.dashboardfx.presentation.core.CustomizablePresentation;
import io.github.gleidsonmt.dashboardfx.presentation.internal.Tutorial;
import io.github.gleidsonmt.dashboardfx.utils.TutorialUtils;
import io.github.gleidsonmt.glad.theme.Css;
import io.github.gleidsonmt.presentation.Row;
import javafx.scene.Node;
import javafx.scene.control.TreeView;
import javafx.scene.layout.Region;
import javafx.scene.layout.StackPane;

/**
 * @author Gleidson Neves da Silveira | gleidisonmt@gmail.com
 * Create on  07/03/2025
 */
public class RegionPres extends CustomizablePresentation {

    public RegionPres() {
        super("Region");
    }

    public Tutorial create() {
        return new Tutorial()
                .indicators()
                .overview()
                .h3("Region")
                .separator()
                .text("""
                        Region is the base class for all JavaFX Node-based UI Controls, and all layout containers. 
                        It is a resizable Parent node which can be styled from CSS. It can have multiple backgrounds and borders. 
                        It is designed to support as much of the CSS3 specification for backgrounds and borders as is relevant to JavaFX. 
                        The full specification is available at the W3C.
                        By default a Region inherits the layout behavior of its superclass, Parent, which means that it will resize any resizable child nodes to their preferred size, but will not reposition them. 
                        If an application needs more specific layout behavior, then it should use one of the Region subclasses: 
                        StackPane, HBox, VBox, TilePane, FlowPane, BorderPane, GridPane, or AnchorPane.
                        """)

                .text("\nRegion doesn't fit by default, if you do not apply some color and set the sizes, it continues hided")
                .legend("javafx.scene.layout.Region")
                .legend("\nEvery control descends by Region so every style in region is applicable to all controls.")
                .demo(createDemo("size-50", "bg-white", "depth-1", "radius-5"))
                .h4("Install", "Region")
                .code(
                        TutorialUtils.installExample(Css.COLORS, Css.SHAPES, Css.PROPERTIES) +
                        """
                        
                        // Try this
                        Region region = new Region();
                        region.getStyleClass().addAll("size-50 bg-white depth-1 radius-5".split(" "));
                        """
                )

                .h3("Size", "Region")
                .demo(new Node[]{
                        createDemo("size-10", "max-size-10", "bg-white", "depth-3", "radius-5", "border-light-gray-2"),
                        createDemo("size-50", "max-size-50", "bg-white", "depth-3", "radius-5", "border-light-gray-2"),
                        createDemo("size-100", "max-size-100", "bg-white", "depth-3", "radius-5", "border-light-gray-2")
                })
                .table(
                        new Row("w-2", "-fx-pref-width: 2px; ;"),
                        new Row("w-5", "-fx-pref-width: 5px;"),
                        new Row("w-10", "-fx-pref-width: 10px;"),
                        new Row("w-100", "-fx-pref-width: 100px;"),

                        new Row("h-2", "-fx-pref-height: 2px;"),
                        new Row("h-5", "-fx-pref-height: 5px;"),
                        new Row("h-10", "-fx-pref-height: 10px;"),

                        new Row("size-2", "-fx-pref-width: 2px; -fx-pref-height: 2px;"),
                        new Row("size-5", "-fx-pref-width: 5px; -fx-pref-height: 5px;"),
                        new Row("size-20", "-fx-pref-width: 20px; -fx-pref-height: 20px;"),
                        new Row("size-30", "-fx-pref-width: 30px; -fx-pref-height: 30px;"),
                        new Row("size-50", "-fx-pref-width: 50px; -fx-pref-height: 50px;"),
                        new Row("size-100", "-fx-pref-width: 100px; -fx-pref-height: 100px;"),

                        new Row("min-size-2", "-fx-min-width: 2px; -fx-min-height: 2px;"),
                        new Row("min-size-5", "-fx-min-width: 5px; -fx-min-height: 5px;"),
                        new Row("min-size-20", "-fx-min-width: 20px; -fx-min-height: 20px;"),
                        new Row("min-size-30", "-fx-min-width: 30px; -fx-min-height: 30px;"),
                        new Row("min-size-50", "-fx-min-width: 50px; -fx-min-height: 50px;"),
                        new Row("min-size-100", "-fx-min-width: 100px; -fx-min-height: 100px;"),

                        new Row("max-size-2", "-fx-max-width: 2px; -fx-max-height: 2px;"),
                        new Row("max-size-5", "-fx-max-width: 5px; -fx-max-height: 5px;"),
                        new Row("max-size-20", "-fx-max-width: 20px; -fx-max-height: 20px;"),
                        new Row("max-size-50", "-fx-max-width: 50px; -fx-max-height: 50px;"),
                        new Row("max-size-100", "-fx-max-width: 100px; -fx-max-height: 100px;")
                )

                .h4("Background", "Region")


                .table(
                        new Row("bg-primary", "-fx-background-color: -primary;"),
                        new Row("bg-info", "-fx-background-color: -info;"),
                        new Row("bg-success", "-fx-background-color: -success;"),
                        new Row("bg-warning", "-fx-background-color: -warning;"),
                        new Row("bg-danger", "-fx-background-color: -danger;"),
                        new Row("bg-secondary", "-fx-background-color: -secondary;"),
                        new Row("bg-unique", "-fx-background-color: -unique;"),
                        new Row("bg-elegant", "-fx-background-color: -elegant;"),
                        new Row("bg-white", "-fx-background-color: -white;"),
                        new Row("bg-light-gray", "-fx-background-color: -light-gray;"),
                        new Row("bg-light-gray-2", "-fx-background-color: -light-gray-2;"),
                        new Row("bg-medium-gray", "-fx-background-color: -light-medium;"),
                        new Row("bg-medium-gray-2", "-fx-background-color: -medium-gray-2;"),
                        new Row("bg-dark-gray", "-fx-background-color: -dark-gray;"),
                        new Row("bg-dark-gray-2", "-fx-background-color: -dark-gray-2;"),
                        new Row("bg-accent", "-fx-background-color: -fx-accent;")
                )
                .demo(new Node[]{
                        createDemo("size-50", "bg-insets-1", "bg-primary", "border-2", "border-white", "depth-1", "radius-5"),
                        createDemo("size-50", "bg-insets-1", "bg-info", "border-2", "border-white", "depth-1", "radius-5"),
                        createDemo("size-50", "bg-insets-1", "bg-success", "border-2", "border-white", "depth-1", "radius-5"),
                        createDemo("size-50", "bg-insets-1", "bg-warning", "border-2", "border-white", "depth-1", "radius-5"),
                        createDemo("size-50", "bg-insets-1", "bg-danger", "border-2", "border-white", "depth-1", "radius-5"),
                        createDemo("size-50", "bg-insets-1", "bg-secondary", "border-2", "border-white", "depth-1", "radius-5"),
                        createDemo("size-50", "bg-insets-1", "bg-unique", "border-2", "border-white", "depth-1", "radius-5"),
                        createDemo("size-50", "bg-insets-1", "bg-elegant", "border-2", "border-white", "depth-1", "radius-5"),
                        createDemo("size-50", "bg-insets-1", "bg-white", "border-2", "border-white", "depth-1", "radius-5"),
                        createDemo("size-50", "bg-insets-1", "bg-light-gray", "border-2", "border-white", "depth-1", "radius-5"),
                        createDemo("size-50", "bg-insets-1", "bg-light-gray-2", "border-2", "border-white", "depth-1", "radius-5"),
                        createDemo("size-50", "bg-insets-1", "bg-medium-gray", "border-2", "border-white", "depth-1", "radius-5"),
                        createDemo("size-50", "bg-insets-1", "bg-medium-gray-2", "border-2", "border-white", "depth-1", "radius-5"),
                        createDemo("size-50", "bg-insets-1", "bg-dark-gray-2", "border-2", "border-white", "depth-1", "radius-5"),
                        createDemo("size-50", "bg-insets-1", "bg-dark-gray-2", "border-2", "border-white", "depth-1", "radius-5"),
                        createDemo("size-50", "bg-insets-1", "bg-accent", "border-2", "border-white", "depth-1", "radius-5")
                })
                .code("""
                        region.getStyleClass().add("bg-[primary|info...]");
                        """)
                .node(TutorialUtils.createLink("See more color options", "Colors"))
                .h4("Border", "Region")
                .h4("Size", "Border")
                .table(
                        new Row("border-1", "-fx-border-width: 1px;"),
                        new Row("border-2", "-fx-border-width: 2px;"),
                        new Row("border-3", "-fx-border-width: 3px;"),
                        new Row("border-4", "-fx-border-width: 4px;"),
                        new Row("border-5", "-fx-border-width: 5px;")
                )
                .demo(new Node[]{
                        createDemo("size-50", "border-1", "border-medium-gray", "radius-5"),
                        createDemo("size-50", "border-2", "border-medium-gray", "radius-5"),
                        createDemo("size-50", "border-3", "border-medium-gray", "radius-5"),
                        createDemo("size-50", "border-4", "border-medium-gray", "radius-5"),
                        createDemo("size-50", "border-5", "border-medium-gray", "radius-5")
                })
                .code("""
                        region.getStyleClass().add("border-[1|2...5]");
                        """)
                .h4("Position", "Border")
                .table(
                        new Row("border-t-1", "-fx-border-width : 1px 0px 0px 0px; "),
                        new Row("border-r-1", "-fx-border-width : px 1px 0px 0px; "),
                        new Row("border-b-1", "-fx-border-width : 00px 0px 1px 0px; "),
                        new Row("border-l-1", "-fx-border-width : 0px 0px 0px 1px; "),
                        new Row("border-l-2", "-fx-border-width : 0px 0px 0px 2px; "),
                        new Row("border-l-3", "-fx-border-width : 0px 0px 0px 3px; "),
                        new Row("border-l-4", "-fx-border-width : 0px 0px 0px 4px; ")
                )
                .demo(new Node[]{
                        createDemo("size-50", "border-t-1", "border-dark-gray", "bg-light-gray"),
                        createDemo("size-50", "border-r-1", "border-dark-gray", "bg-light-gray"),
                        createDemo("size-50", "border-b-1", "border-dark-gray", "bg-light-gray"),
                        createDemo("size-50", "border-l-1", "border-dark-gray", "bg-light-gray")
                })
                .code("""
                        region.getStyleClass().add("border-[t|l|b|r]-[1|2..]");
                        """)
                .h4("Color", "Border")
                .demo(new Node[]{
                        createDemo("size-50", "border-2", "border-primary", "bg-light-gray"),
                        createDemo("size-50", "border-2", "border-info", "bg-light-gray"),
                        createDemo("size-50", "border-2", "border-success", "bg-light-gray")
                })
                .code("""
                        region.getStyleClass().add("border-[primary|info...]");
                        """)
                .h4("Radius", "Region")
                .table(
                        new Row("radius-2", "-fx-background-radius: 2px; -fx-border-radius: 2px;"),
                        new Row("radius-5", "-fx-background-radius: 5px; -fx-border-radius: 5px;"),
                        new Row("radius-6", "-fx-background-radius: 6px; -fx-border-radius: 6px;"),
                        new Row("radius-7", "-fx-background-radius: 7px; -fx-border-radius: 7px;"),
                        new Row("radius-8", "-fx-background-radius: 8px; -fx-border-radius: 8px;"),
                        new Row("radius-10", "-fx-background-radius: 10px; -fx-border-radius: 10px;")
                )
                .demo(new Node[]{
                        createDemo("min-size-50", "border-bottom-1", "border-primary", "bg-primary", "radius-2"),
                        createDemo("min-size-50", "border-top-1", "border-primary", "bg-primary", "radius-5"),
                        createDemo("min-size-50", "border-top-1", "border-primary", "bg-primary", "radius-6"),
                        createDemo("min-size-50", "border-top-1", "border-primary", "bg-primary", "radius-7"),
                        createDemo("min-size-50", "border-top-1", "border-primary", "bg-primary", "radius-8"),
                        createDemo("min-size-50", "border-top-1", "border-primary", "bg-primary", "radius-10")
                })
                .code("""
                        region.getStyleClass().add("radius-[2|5...]");
                        """)
                .h4("Padding", "Region")
                .table(
                        new Row("padding-2", "-fx-padding: 2px;"),
                        new Row("padding-5", "-fx-padding: 5px;"),
                        new Row("padding-10", "-fx-padding: 10px;"),
                        new Row("padding-20", "-fx-padding: 20px;")
                )
                .demo(new Node[]{
                        createPadding("min-size-50", "size-10", "bg-primary", "padding-2"),
                        createPadding("min-size-50", "size-10", "bg-primary", "padding-5"),
                        createPadding("min-size-50", "size-10", "bg-primary", "padding-10"),
                        createPadding("min-size-50", "size-10", "bg-primary", "padding-20")
                })
                .code("""
                        region.getStyleClass().add("padding-[2|5|10|20]");
                        """)
                .h4("Insets", "Region")
                .text("The insets doesn't affect its children.")
                .h4("Insets Background", "Insets")
                .table(
                        new Row("bg-insets-1", "-fx-background-insets: 1px;"),
                        new Row("bg-insets-5", "-fx-background-insets: 5px;"),
                        new Row("bg-insets-10", "-fx-background-insets: 10px;")
                )
                .demo(new Node[]{
                        createDemo("min-size-50", "size-10", "border-2", "border-primary", "bg-medium-gray", "bg-insets-1"),
                        createDemo("min-size-50", "size-10", "border-2", "border-primary", "bg-medium-gray", "bg-insets-5"),
                        createDemo("min-size-50", "size-10", "border-2", "border-primary", "bg-medium-gray", "bg-insets-10")
                })
                .h4("Insets Border", "Insets")
                .table(
                        new Row("border-insets-1", "-fx-border-insets: 1px;"),
                        new Row("border-insets-5", "-fx-border-insets: 5px;"),
                        new Row("border-insets-10", "-fx-border-insets: 10px;")
                )
                .demo(new Node[]{
                        createDemo("min-size-50", "border-2", "border-primary", "bg-medium-gray", "border-insets-1"),
                        createDemo("min-size-50", "size-50", "border-2", "border-primary", "bg-medium-gray", "border-insets-5"),
                        createDemo("min-size-50", "size-50", "border-2", "border-primary", "bg-medium-gray", "border-insets-10")
                })
                .h3("Shapes", "Region")
                .table(
                        new Row("rectangle", "-fx-border-radius: 0px;"),
                        new Row("rounded", "-fx-background-radius : 5px;"),
                        new Row("semi-round", "-fx-background-radius : 20px;"),
                        new Row("round", "-fx-border-radius: 100px;"),
                        new Row("semi-leaf", "  -fx-background-radius : 20px 0px 20px 0px;"),
                        new Row("leaf", "-fx-background-radius: 100px 0px 100px 0px;"),
                        new Row("mountain", "-fx-background-radius: 100px 100px 0px 0px;")
                )

                .h3("Demo", "Shapes")
                .demo(new Node[]{
                        createDemo("min-size-50", "bg-primary", "rectangle"),
                        createDemo("min-size-50", "bg-primary", "rounded"),
                        createDemo("min-size-50", "bg-primary", "round"),
                        createDemo("min-size-50", "bg-primary", "semi-leaf"),
                        createDemo("min-size-50", "bg-primary", "leaf"),
                        createDemo("min-size-50", "bg-primary", "mountain"),
                        createDemo("min-size-50", "bg-primary", "reverse-mountain"),
                        createDemo("min-size-50", "bg-primary", "round-tl"),
                        createDemo("min-size-50", "bg-primary", "round-tr"),
                        createDemo("min-size-50", "bg-primary", "round-br"),
                        createDemo("min-size-50", "bg-primary", "round-bl"),
                        createDemo("min-size-50", "bg-primary", "pill-left"),
                        createDemo("min-size-50", "bg-primary", "pill-right"),
                        createDemo("min-size-50", "bg-primary", "jelly"),
                        createDemo("min-size-50", "bg-primary", "info"),
                        createDemo("min-size-50", "bg-primary", "parallelogram-l"),
                        createDemo("min-size-50", "bg-primary", "parallelogram-r"),
                        createDemo("min-size-50", "bg-primary", "trapeze"),
                        createDemo("min-size-50", "bg-primary", "trapeze-inverse"),
                        createDemo("min-size-50", "bg-primary", "comb-close"),
                        createDemo("min-size-50", "bg-primary", "comb-open"),
                        createDemo("min-size-50", "bg-primary", "comb-right"),
                        createDemo("min-size-50", "bg-primary", "comb-left"),
                        createDemo("min-size-50", "bg-primary", "hexagon"),
                        createDemo("min-size-50", "bg-primary", "arrow"),
                        createDemo("min-size-50", "bg-primary", "star"),
                        createDemo("min-size-50", "bg-primary", "star-2"),
                        createDemo("min-size-50", "bg-primary", "pac"),
                        createDemo("min-size-50", "bg-primary", "heart"),
                        createDemo("min-size-50", "bg-primary", "tool-tip"),
                        createDemo("min-size-50", "bg-primary", "lbl")
                })
                .code("""
                        region.getStyleClass().add("radius-[rectangle|rounded...]");
                        """)

                .h3("Cursors", "Shapes")
                .table(
                        new Row("cursor-default", "-fx-cursor: default;"),
                        new Row("cursor-close-hand", "-fx-cursor: close_hand;"),
                        new Row("cursor-open-hand", "-fx-cursor: open_hand;"),
                        new Row("cursor-hand", "-fx-cursor: hand;"),
                        new Row("cursor-move", "-fx-cursor: move;"),
                        new Row("cursor-wait", "-fx-cursor: wait;"),
                        new Row("cursor-text", "-fx-cursor: text;"),
                        new Row("cursor-v-resize", "-fx-cursor: v_resize;"),
                        new Row("cursor-h-resize", "-fx-cursor: h_resize;"),
                        new Row("cursor-n-resize", "-fx-cursor: n_resize;"),
                        new Row("cursor-ne-resize", "-fx-cursor: ne_resize;"),
                        new Row("cursor-e-resize", "-fx-cursor: e_resize;"),
                        new Row("cursor-se-resize", "-fx-cursor: se_resize;"),
                        new Row("cursor-s-resize", "-fx-cursor: s_resize;"),
                        new Row("cursor-sw-resize", "-fx-cursor: sw_resize;"),
                        new Row("cursor-w-resize", "-fx-cursor: w_resize;"),
                        new Row("cursor-nw-resize", "-fx-cursor: nw_resize;"),
                        new Row("cursor-crosshair", "-fx-cursor: crosshair;"),
                        new Row("cursor-none", "-fx-cursor: none;"),
                        new Row("cursor-disappear", "-fx-cursor: disappear;")
                )
                .demo(new Node[]{
                        createDemo("min-size-50", "border-2", "border-primary", "bg-medium-gray", "border-insets-1", "cursor-default"),
                        createDemo("min-size-50", "border-2", "border-primary", "bg-medium-gray", "border-insets-1", "cursor-close-hand"),
                        createDemo("min-size-50", "border-2", "border-primary", "bg-medium-gray", "border-insets-1", "cursor-open-hand"),
                        createDemo("min-size-50", "border-2", "border-primary", "bg-medium-gray", "border-insets-1", "cursor-hand"),
                        createDemo("min-size-50", "border-2", "border-primary", "bg-medium-gray", "border-insets-1", "cursor-move"),
                        createDemo("min-size-50", "border-2", "border-primary", "bg-medium-gray", "border-insets-1", "cursor-wait"),
                        createDemo("min-size-50", "border-2", "border-primary", "bg-medium-gray", "border-insets-1", "cursor-text"),
                        createDemo("min-size-50", "border-2", "border-primary", "bg-medium-gray", "border-insets-1", "cursor-v-resize"),
                        createDemo("min-size-50", "border-2", "border-primary", "bg-medium-gray", "border-insets-1", "cursor-h-resize"),
                        createDemo("min-size-50", "border-2", "border-primary", "bg-medium-gray", "border-insets-1", "cursor-n-resize"),
                        createDemo("min-size-50", "border-2", "border-primary", "bg-medium-gray", "border-insets-1", "cursor-ne-resize"),
                        createDemo("min-size-50", "border-2", "border-primary", "bg-medium-gray", "border-insets-1", "cursor-e-resize"),
                        createDemo("min-size-50", "border-2", "border-primary", "bg-medium-gray", "border-insets-1", "cursor-se-resize"),
                        createDemo("min-size-50", "border-2", "border-primary", "bg-medium-gray", "border-insets-1", "cursor-s-resize"),
                        createDemo("min-size-50", "border-2", "border-primary", "bg-medium-gray", "border-insets-1", "cursor-sw-resize"),
                        createDemo("min-size-50", "border-2", "border-primary", "bg-medium-gray", "border-insets-1", "cursor-w-resize"),
                        createDemo("min-size-50", "border-2", "border-primary", "bg-medium-gray", "border-insets-1", "cursor-nw-resize"),
                        createDemo("min-size-50", "border-2", "border-primary", "bg-medium-gray", "border-insets-1", "cursor-crosshair"),
                        createDemo("min-size-50", "border-2", "border-primary", "bg-medium-gray", "border-insets-1", "cursor-none"),
                        createDemo("min-size-50", "border-2", "border-primary", "bg-medium-gray", "border-insets-1", "cursor-disappear")
                })
                ;
    }


    private Region createPadding(String... classes) {
        StackPane body = new StackPane();

        body.getStyleClass().addAll(classes);
        Region region = new Region();
        region.getStyleClass().addAll("bg-medium-gray");
//        region.setPrefSize(50, 50);
//        body.setPrefSize(50, 50);
        body.getChildren().add(region);
        return body;
    }

    private Region createDemo(String... classes) {
        Region region = new Region();
        region.getStyleClass().addAll(classes);
//        region.setPrefSize(50, 50);
        return region;
    }
}
