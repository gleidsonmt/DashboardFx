package io.github.gleidsonmt.dashboardfx.presentation.presentations.controls;

import io.github.gleidsonmt.dashboardfx.presentation.CustomizablePresentation;
import io.github.gleidsonmt.dashboardfx.presentation.internal.Tutorial;
import io.github.gleidsonmt.dashboardfx.utils.TutorialUtils;
import io.github.gleidsonmt.glad.theme.Css;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;

public class ScrollPane extends CustomizablePresentation {

    public ScrollPane() {
        super("ScrollPane");
    }

    public Tutorial create() {
        return new Tutorial()
                .h3("ScrollPane")
                .text("""
                        The ScrollPane control shows one content node inside a scrollable viewport.
                        Put multiple controls inside a layout container, then set that container as the ScrollPane content.""")
                .legend("javafx.scene.control.ScrollPane")
                .demo(createDemo())
                .h4("Install", "ScrollPane")
                .code(TutorialUtils.installExample(Css.IMMERSIVE_SCROLL) + """
                       
                        VBox contentBox = new VBox(15);
                        for (int i = 1; i <= 30; i++) {
                            contentBox.getChildren().add(new Label("Item number " + i));
                        }

                        ScrollPane scrollPane = new ScrollPane();
                        scrollPane.setContent(contentBox);
                        scrollPane.setFitToWidth(true);
                      
                        """);
    }


    private Node createDemo() {
        VBox contentBox = new VBox(15);

        for (int i = 1; i <= 30; i++) {
            contentBox.getChildren().add(new Label("Item number " + i));
        }

        javafx.scene.control.ScrollPane scrollPane = new javafx.scene.control.ScrollPane();
        scrollPane.setContent(contentBox);
        scrollPane.setFitToWidth(true);
        scrollPane.setPrefViewportHeight(220);
        scrollPane.setPrefViewportWidth(300);
        return scrollPane;
    }
}
