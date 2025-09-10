package io.github.gleidsonmt.dashboardfx.presentation.util;

import eu.hansolo.colors.MaterialDesign;
import io.github.gleidsonmt.dashboardfx.material.MaterialColumn;
import io.github.gleidsonmt.dashboardfx.material.MaterialGrid;
import io.github.gleidsonmt.dashboardfx.material.MaterialItem;
import io.github.gleidsonmt.dashboardfx.presentation.CustomizablePresentation;
import io.github.gleidsonmt.dashboardfx.presentation.internal.Tutorial;
import io.github.gleidsonmt.dashboardfx.utils.HoverAnimation;
import io.github.gleidsonmt.dashboardfx.utils.TutorialUtils;
import io.github.gleidsonmt.glad.controls.icon.Icon;
import io.github.gleidsonmt.glad.controls.icon.SVGIcon;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Node;
import javafx.scene.control.Tooltip;
import javafx.scene.layout.StackPane;
import javafx.scene.text.Text;

import java.net.URI;
import java.net.URISyntaxException;

/**
 * @author Gleidson Neves da Silveira | gleidisonmt@gmail.com
 * Create on  25/03/2025
 */
public class ColorsPres extends CustomizablePresentation {

    public ColorsPres() {
        super("Colors");
    }

    @Override
    public Tutorial create() {
        try {
            return new Tutorial()
                    .overview()
                    .h3("Colors")
                    .text("Sample of colors, you can copy and use.")
                    .h4("Install", "Colors")
                    .code("ThemeProvider.install(scene, \n\t... \n\tCss.COLORS);", "java")

                    .h4("Grid Colors", "Colors")
                    .text("Common colors.")
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
                            createDemo("-blue-gray"),
                            createDemo("-black")
                    });
        } catch (URISyntaxException e) {
            throw new RuntimeException(e);
        }

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
                String temp;
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

        stackPane.setOnMouseClicked(_ -> {
            TutorialUtils.putTextOnClipboard(name);
            getRoot()
                    .behavior()
                    .snack()
                    .graphic(new SVGIcon(Icon.NOTIFICATION_IMPORTANT_FILLED) )
//                    .message("You copied '" + name + "' to clipboard.")
                    .message("Copied to clipboard.")
                    .show();
        });
        return stackPane;
    }
}
