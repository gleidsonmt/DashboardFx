package io.github.gleidsonmt.dashboardfx.presentation.presentations.components;

import io.github.gleidsonmt.dashboardfx.presentation.internal.Tutorial;
import io.github.gleidsonmt.dashboardfx.utils.TutorialUtils;
import io.github.gleidsonmt.glad.base.Root;
import io.github.gleidsonmt.presentation.Presentation;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;

/**
 * @author Gleidson Neves da Silveira | gleidisonmt@gmail.com
 * Create on  12/08/2025
 */
public class SnackBarPres extends StackPane {
    public SnackBarPres() {
        getChildren().setAll(
                new Tutorial()
                        .h3("SnackBar")
                        .node(TutorialUtils.createAction(e -> {
                            Root root = (Root) getScene().getRoot();
                            root.behavior()
                                    .alert()
                                    .snack("Hello SnackBar")
                                    .show();
                        }))
//                        .node(createButton())
                        .build()
                        .getRoot()
        );
    }


}
