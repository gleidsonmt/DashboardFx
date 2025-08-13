package io.github.gleidsonmt.dashboardfx.presentation.core;

import io.github.gleidsonmt.dashboardfx.Main;
import io.github.gleidsonmt.dashboardfx.presentation.internal.Tutorial;
import io.github.gleidsonmt.dashboardfx.utils.TutorialUtils;
import io.github.gleidsonmt.glad.base.Layout;
import io.github.gleidsonmt.glad.base.Root;
import io.github.gleidsonmt.glad.base.WrapperEffect;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;

/**
 * @author Gleidson Neves da Silveira | gleidisonmt@gmail.com
 * Create on  21/03/2025
 */
public class Wrapper extends StackPane {
    public Wrapper() {
        getChildren().setAll(
                new Tutorial()
                        .h3("Wrapper")
                        .text("The wrapper interface disposes a region with color to focus on the element stacked.")
                        .legend("io.github.gleidsonmt.glad.base.Wrapper")
                        .legend("Click on it, to hide.")
                        .code("""
                                Root main = (Root) getScene().getRoot();
                                main.wrapper().show();
                                """)
                        .demo(TutorialUtils.createAction(_-> {
                            Root main = (Root) getScene().getRoot();
                            main.wrapper().show();
                        }))

                        .h3("Effect", "Wrapper")
                        .text("Add a bit effect.")
                        .code("""
                                Root main = (Root) getScene().getRoot();
                                main.wrapper().show(WrapperEffect.BLUR);
                                """)
                        .demo(TutorialUtils.createAction(_-> {
                            Root main = (Root) getScene().getRoot();
                            main.wrapper().show(WrapperEffect.BLUR);
                        }))
                        .h4("Closing", "Wrapper")
                        .code("""
                                Main main = (Main) getScene().getRoot();
                                main.wrapper().hide();
                                """)
                        .build()
                        .getRoot()
        );
    }

    private Node createDemo() {
        return createDemo(WrapperEffect.GRAY);
    }
    private Node createDemo(WrapperEffect wrapperEffect) {
        Button button = new Button("Click on me");
        button.setOnAction(e -> {
            Root main = (Root) getScene().getRoot();
            main.wrapper().show(wrapperEffect);
        });
        return button;
    }
}
