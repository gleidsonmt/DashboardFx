package io.github.gleidsonmt.dashboardfx.presentation.util;

import eu.hansolo.colors.MaterialDesign;
import io.github.gleidsonmt.dashboardfx.material.MaterialColumn;
import io.github.gleidsonmt.dashboardfx.material.MaterialGrid;
import io.github.gleidsonmt.dashboardfx.material.MaterialItem;
import io.github.gleidsonmt.dashboardfx.presentation.internal.Tutorial;
import io.github.gleidsonmt.dashboardfx.utils.HoverAnimation;
import io.github.gleidsonmt.dashboardfx.utils.TutorialUtils;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Orientation;
import javafx.scene.Node;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.Separator;
import javafx.scene.control.Tooltip;
import javafx.scene.input.Clipboard;
import javafx.scene.input.ClipboardContent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.shape.SVGPath;
import javafx.scene.text.Text;

import java.net.URI;
import java.net.URISyntaxException;

/**
 * @author Gleidson Neves da Silveira | gleidisonmt@gmail.com
 * Create on  25/03/2025
 */
public class ColorsPres extends StackPane {
    public ColorsPres() {
        try {
            getChildren().setAll(
                    new Tutorial()
                            .h3("Colors")
                            .h4("Install", "Colors")
                            .code("ThemeProvider.install(scene, \n\t... \n\tCss.COLORS);", "java")

                            .h4("Grid Colors", "Colors")
                            //                        .link("Original","https://github.com/HanSolo/Colors")
                            //                        .node(createCard())
                            .node(TutorialUtils.createCardLink("HanSolo Colors", new URI("https://github.com/HanSolo/Colors")))
                            .node(createGrid())

                            .h4("Bootstrap", "Colors")
                            .text("Common colors.")
                            .demo(new Node[]{
                                    createDemo("-primary"),
                                    createDemo("-info"),
                                    createDemo("-success"),
                                    createDemo("-warning"),
                                    createDemo("-danger"),
                                    createDemo("-secondary"),
                                    createDemo("-unique"),
                                    createDemo("-elegant")
                            })
                            .h4("Theme", "Colors")
                            .text("Named colors")
                            .demo(new Node[]{
                                    createDemo("-brown"),
                                    createDemo("-red"),
                                    createDemo("-grapefruit"),
                                    createDemo("-grapefruit-2"),
                                    createDemo("-bittersweet"),
                                    createDemo("-bittersweet-2"),
                                    createDemo("-deep-orange"),
                                    createDemo("-orange"),
                                    createDemo("-amber"),
                                    createDemo("-sunflower"),
                                    createDemo("-yellow"),
                                    createDemo("-lime"),
                                    createDemo("-grass"),
                                    createDemo("-grass-2"),
                                    createDemo("-green"),
                                    createDemo("-mint"),
                                    createDemo("-mint-2"),
                                    createDemo("-teal"),
                                    createDemo("-cyan"),
                                    createDemo("-aqua"),
                                    createDemo("-aqua-2"),
                                    createDemo("-blue-jeans"),
                                    createDemo("-blue-jeans-2"),
                                    createDemo("-blue"),
                                    createDemo("-indigo"),
                                    createDemo("-purple"),
                                    createDemo("-deep-purple"),
                                    createDemo("-lavander"),
                                    createDemo("-lavander-2"),
                                    createDemo("-pink"),
                                    createDemo("-pink-rose"),
                                    createDemo("-pink-rose-2"),
                                    createDemo("-light-gray"),
                                    createDemo("-light-gray-2"),
                                    createDemo("-medium-gray"),
                                    createDemo("-medium-gray-2"),
                                    createDemo("-dark-gray"),
                                    createDemo("-dark-gray-2"),
                                    createDemo("-blue-gray"),
                                    createDemo("-light-green"),
                                    createDemo("-light-blue"),
                                    createDemo("-gray"),
                                    createDemo("-white"),
                                    createDemo("-black")
                            })

                            .build()
                            .getRoot()
            );
        } catch (URISyntaxException e) {
            throw new RuntimeException(e);
        }
    }

    private Node createCard() {
        SVGPath path = new SVGPath();
        path.setContent("M12 0c-6.626 0-12 5.373-12 12 0 5.302 3.438 9.8 8.207 11.387.599.111.793-.261.793-.577v-2.234c-3.338.726-4.033-1.416-4.033-1.416-.546-1.387-1.333-1.756-1.333-1.756-1.089-.745.083-.729.083-.729 1.205.084 1.839 1.237 1.839 1.237 1.07 1.834 2.807 1.304 3.492.997.107-.775.418-1.305.762-1.604-2.665-.305-5.467-1.334-5.467-5.931 0-1.311.469-2.381 1.236-3.221-.124-.303-.535-1.524.117-3.176 0 0 1.008-.322 3.301 1.23.957-.266 1.983-.399 3.003-.404 1.02.005 2.047.138 3.006.404 2.291-1.552 3.297-1.23 3.297-1.23.653 1.653.242 2.874.118 3.176.77.84 1.235 1.911 1.235 3.221 0 4.609-2.807 5.624-5.479 5.921.43.372.823 1.102.823 2.222v3.293c0 .319.192.694.801.576 4.765-1.589 8.199-6.086 8.199-11.386 0-6.627-5.373-12-12-12z");
        path.setStyle("-fx-fill: -text-color;");
        HBox box = new HBox();
        box.getStyleClass().addAll("border-2", "border-light-gray-2", "padding-10", "radius-5");
        Hyperlink link = TutorialUtils.createLink("Hansolo", "https://github.com/HanSolo/Colors");
        box.getChildren().addAll(path, new Separator(Orientation.VERTICAL), link);
        box.setSpacing(10);
        return box;
    }


    private Node createGrid() {
        MaterialGrid materialGrid = new MaterialGrid();
        ObservableList<MaterialColumn> columns = FXCollections.observableArrayList();
        String actual = "";

        for (MaterialDesign color : MaterialDesign.values()) {
            if (actual.isEmpty()) {
                actual = color.name();
                columns.add(new MaterialColumn());
                columns.getLast().addColor(new MaterialItem(color));
            } else {
                String temp = "";
                if (color.name().matches("\\w+[0-9]")) {
                    temp = color.name().substring(0, color.name().lastIndexOf("_"));
                } else {
                    temp = color.name();
                    columns.add(new MaterialColumn());
                }
                if (!temp.equals(actual)) {
                    columns.getLast().addColor(new MaterialItem(color));
                    actual = temp;
                } else {
                    columns.getLast().addColor(new MaterialItem(color));
                }
            }
        }
//        columns.forEach(System.out::println);
        materialGrid.setColumns(columns);
        return materialGrid;
    }

    private Node createDemo(String name) {
        Text content = new Text(name);

        Tooltip tooltip = new Tooltip(name);

        if (name.equals("-white") || name.equals("-light-gray") || name.equals("-light-gray-2")) {
            content.getStyleClass().addAll("bold", "h5", "cursor-hand");
        } else {
            content.getStyleClass().addAll("text-white", "bold", "h5", "cursor-hand");
        }

        StackPane stackPane = new StackPane();
        Tooltip.install(stackPane, tooltip);
        HoverAnimation.install(stackPane);

        stackPane.setMinSize(50, 50);
//        stackPane.setMaxWidth(150);
        stackPane.getStyleClass().addAll("padding-10", "bg-insets-1",
                name.equals("white") || name.equals("black") ? name.replace("-", "") : ("bg" + name),
                "border-2", "border-white", "depth-1", "round", "cursor-hand");

        stackPane.setOnMouseClicked(e -> {
            Clipboard clipboard = Clipboard.getSystemClipboard();
            ClipboardContent _content = new ClipboardContent();
            _content.putString(name);
            clipboard.setContent(_content);
        });
        return stackPane;
    }
}
