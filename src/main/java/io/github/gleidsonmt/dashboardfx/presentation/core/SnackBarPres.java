package io.github.gleidsonmt.dashboardfx.presentation.core;

import io.github.gleidsonmt.dashboardfx.presentation.internal.Tutorial;
import io.github.gleidsonmt.dashboardfx.utils.TutorialUtils;
import io.github.gleidsonmt.glad.base.Root;
import javafx.scene.layout.StackPane;

/**
 * @author Gleidson Neves da Silveira | gleidisonmt@gmail.com
 * Create on  12/08/2025
 */
public class SnackBarPres extends StackPane {
    public SnackBarPres() {
        getChildren().setAll(
                new Tutorial()
                        .h3("SnackBar")
                        .text("SnackBar is a component that displays a message at the bottom of the screen.")
                        .legend("")
                        .node(TutorialUtils.createAction(e -> {
                            Root root = (Root) getScene().getRoot();
                            root.behavior()
                                    .snack()
                                    .message("Hello SnackBar")
                                    .show();
                        }))
//                        .node(createButton())
                        .build()
                        .getRoot()
        );
    }


}
