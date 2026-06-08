package io.github.gleidsonmt.dashboardfx.presentation.presentations.components;

import io.github.gleidsonmt.dashboardfx.presentation.internal.Tutorial;
import io.github.gleidsonmt.glad.controls.button.*;
import io.github.gleidsonmt.glad.controls.icon.Icon;
import io.github.gleidsonmt.glad.controls.icon.SVGIcon;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.layout.StackPane;

/**
 * @author Gleidson Neves da Silveira | gleidisonmt@gmail.com
 * Create on  10/04/2025
 */
public class ButtonExample extends StackPane {

    public ButtonExample() {
        getChildren().setAll(
                new Tutorial()
                        .h3("Button")
                        .text("Button style examples.")
                        .legend("io.github.gleidsonmt.glad.controls.button.Button")
                        .demo(createButtonDemo("Button", true))

                        .h3("Flat")
                        .demo(createButtonDemo("Button", true,"flat"))
                        .code("""
                                btn.getStyleClass().addAll("flat");
                                """)
                        .h3("IconButton")
                        .text("Icon Button is specialized button with display graphic_only")
                        .demo(createIconButton(Icon.APPS))
                        .code("""
                                // Constructor
                                // Pass a node in the constructor
                               IconButton button = new IconButton(new SVGIcon(Icon.APPS));
                                """)
                        .h3("Fab Button")
                        .demo(createIconButton(Icon.ADD,"raised", "size-50", "round"))
                        .code("""
                                // Constructor
                               IconButton button = new IconButton(new SVGIcon(Icon.APPS));
                               button.getStyleClass().add("raised");
                                """)

                        .h3("Animated")
                        .legend("io.github.gleidsonmt.glad.controls.button.AnimatedButton")
//                        .demo(new Node[]{
//                                new SwipeDiagonal(),
//                                new AlternateButton(),
//                                new SmoothButton(),
//                                new CornerButton(),
//                                new CentralizeButton(),
//                        })
                        .build()
                        .getRoot()
        );
    }

    private IconButton createIconButton(Icon icon, String... classes) {
        IconButton button = new IconButton(new SVGIcon(icon));
        button.setPadding(new Insets(10));
        button.getStyleClass().addAll(classes);
        return button;
    }

    private Button createButtonDemo(String... classes) {
        return createButtonDemo("Button", false, false, classes);
    }

    private Button createButtonDemo(String text, String... classes) {
        return createButtonDemo(text, false, false, classes);
    }

    private Button createButtonDemo(String text, boolean _default, String... classes) {
        return createButtonDemo(text, _default, false, classes);
    }

    private Button createButtonDemo(String text, Node graphic, String... classes) {
        Button button = createButtonDemo(text, classes);
        button.setGraphic(graphic);
        return button;
    }

    private Button createButtonDemo(String text, boolean _default, boolean _cancel, String... classes) {
        Button btn = new Button(text);
        btn.setDefaultButton(_default);
        btn.setCancelButton(_cancel);
        btn.getStyleClass().addAll(classes);
        return btn;
    }
}
