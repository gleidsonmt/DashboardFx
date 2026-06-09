package io.github.gleidsonmt.dashboardfx.presentation.presentations.controls;

import io.github.gleidsonmt.dashboardfx.presentation.internal.Tutorial;
import io.github.gleidsonmt.dashboardfx.utils.TutorialUtils;
import io.github.gleidsonmt.glad.controls.icon.Icon;
import io.github.gleidsonmt.glad.controls.icon.SVGIcon;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Labeled;
import javafx.scene.layout.StackPane;

/**
 * @author Gleidson Neves da Silveira | gleidisonmt@gmail.com
 * Create on  21/03/2025
 */
public class ButtonPres extends StackPane {
    public ButtonPres() {
        getChildren().setAll(
                new Tutorial()
                        .h3("Button")
                        .text("A simple button control. The button control can contain text and/or a graphic.")
                        .legend("javafx.scene.control.Button")
                        .text("All classes in (Text) works in the text inside button.")
                        .text("All classes in (Region, Label) can by apply.")
                        .h3("Theme" )
                        .h4("Install" )
                        .code("ThemeProvider.install(scene, ... \n\tCss.BUTTON);", "java")
                        .h4("Demo")
                        .demo(createDemo())
                        .code("""
                                Button btn = new Button("Button");
                                """)

                        .h4("Cancel")
                        .demo(createDemo("Cancel", false, true))
                        .code("btn.setCancelButton(true);")
                        .h3("Graphic")
                        .text("An optional icon for the Labeled.")
                        .legend("Every node object can be an icon.")
                        .node(TutorialUtils.createLink("See more icons in SVGIcon", "SVGIcon"))
                        .demo(createDemo("Label", new SVGIcon(Icon.TODAY), "padding-5"))
                        .code("""
                                Button btn = new Button("Button");
                                btn.setGraphic(new SVGIcon(Icon.ADD));
                                """)
                        .h3("Content Display")
                        .node(TutorialUtils.createLink("See more displays in Label", "Label", "Display"))
                        .demo(createDemo("Button", new SVGIcon(Icon.HOME), "display-bottom", "padding-10"))
                        .code("""
                                Button btn = new Button("Button");
                                btn.getStyleClass().addAll("display-bottom");
                                """)
                        .h3("Flat")
                        .demo(createDemo("Button", new SVGIcon(Icon.HOME), "flat"))
                        .code("""
                                btn.getStyleClass().addAll("flat");
                                """)
                        .h3("Customizing")
                        .demo(
                                createDemo("Round Button", new SVGIcon(Icon.ADD), "size-50", "round", "display-graphic", "raised")
                        )
                        .code("""
                                // Floating button
                                Button btn = new Button(text);
                                btn.setGraphic(new SVGIcon(Icon.ADD));
                                btn.getStyleClass().addAll("size-50", "round", "display-graphic", "raised");
                                """)
                        .build()
                        .getRoot()
        );
    }

    private Node createDemo( String... classes) {
        return createDemo("Button", false, false, classes);
    }

    private Button createDemo(String text,  String... classes) {
        return createDemo(text, false, false, classes);
    }

    private Button createDemo(String text, boolean _default, String... classes) {
        return createDemo(text, _default, false, classes);
    }
    private Button createDemo(String text, Node graphic, String... classes) {
        Button button = createDemo(text, classes);
        button.setGraphic(graphic);
        return button;
    }

    private Button createDemo(String text, boolean _default, boolean _cancel, String... classes) {
        Button btn = new Button(text);
        btn.setDefaultButton(_default);
        btn.setCancelButton(_cancel);
        btn.getStyleClass().addAll(classes);
        return btn;
    }

}

