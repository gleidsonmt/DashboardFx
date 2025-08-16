package io.github.gleidsonmt.dashboardfx.presentation.core;

import io.github.gleidsonmt.dashboardfx.presentation.internal.Tutorial;
import io.github.gleidsonmt.dashboardfx.utils.TutorialUtils;
import io.github.gleidsonmt.glad.base.Root;
import io.github.gleidsonmt.glad.base.WrapperEffect;
import io.github.gleidsonmt.glad.base.internal.Anchor;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;

/**
 * @author Gleidson Neves da Silveira | gleidisonmt@gmail.com
 * Create on  21/03/2025
 */
public class WrapperPres extends StackPane {
    public WrapperPres() {
        getChildren().setAll(
                new Tutorial()
                        .h3("Wrapper")
                        .text("The wrapper interface disposes a region with color to focus on the element stacked.")
                        .legend("io.github.gleidsonmt.glad.base.Wrapper")
                        .legend("Click on it, to hide.")

                        .h3("Getting", "Wrapper")
                        .text("Get the wrapper from the root. Like all that stuff in JavaFX, you need to get from a scene.")
                        .text("You can get root from event. (MouseEvent, KeyEvent, etc.)")
                        .code("""
                                customButton.setOnMouseClicked(e -> {
                                    Root root = (Root) getScene().getRoot();
                                };
                                """)

                        .h3("Opening", "Wrapper")
                        .code("""
                                ...
                                main.wrapper().show();
                                """)
                        .demo(TutorialUtils.createAction(_-> {
                            Root main = (Root) getScene().getRoot();
                            main.wrapper()
                                    .pos(Pos.CENTER_RIGHT)
                                    .anchor(Anchor.RIGHT)
                                    .onClick(_ -> main.wrapper().hide())
                                    .with(new Button("Click me!"))
                                    .show();
                        }))

                        .h3("Effect", "Wrapper")
                        .text("Add a bit effect.")
                        .code("""
                                ...
                                root.wrapper().show(WrapperEffect.BLUR);
                                """)
                        .demo(TutorialUtils.createAction(_-> {
                            Root main = (Root) getScene().getRoot();
                            main.wrapper().onClick(_ -> main.wrapper().hide()).show(WrapperEffect.BLUR);
                        }))
                        .h4("Closing", "Wrapper")
                        .code("""
                                ...
                                root.wrapper().hide();
                                """)
                        .h3("Action. Click on it, to hide.", "Wrapper")
                        .code("""
                                root.wrapper()
                                    .onClick(e -> main.wrapper().hide())
                                    .show();
                                """)
                        .build()
                        .getRoot()
        );
    }
}
