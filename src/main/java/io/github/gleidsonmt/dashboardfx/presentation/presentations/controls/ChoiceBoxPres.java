package io.github.gleidsonmt.dashboardfx.presentation.presentations.controls;

import io.github.gleidsonmt.dashboardfx.presentation.CustomizablePresentation;
import io.github.gleidsonmt.dashboardfx.presentation.internal.Tutorial;
import io.github.gleidsonmt.dashboardfx.utils.TutorialUtils;
import io.github.gleidsonmt.glad.theme.Css;
import io.github.gleidsonmt.presentation.Presentation;
import javafx.scene.Node;
import javafx.scene.control.ChoiceBox;

/**
 * @author Gleidson Neves da Silveira | gleidisonmt@gmail.com
 * Create on  25/08/2025
 */
public class ChoiceBoxPres extends CustomizablePresentation {

    public ChoiceBoxPres() {
        super("ChoiceBox");
    }

    @Override
    public Presentation create() {
        return new Tutorial()
                .h3("ChoiceBox")
                .separator()
                .text("ChoiceBox is a control that provides a drop-down list of choices for the user to select from.")
                .legend("javafx.scene.control.ChoiceBox")
                .demo(new Node[]{
                        createChoiceBox(),
                        createChoiceBox("choice-outlined")
                })
                .h4("Install")
                .code(TutorialUtils.installExample(Css.CHOICE_BOX, "ChoiceBox"))
                ;
    }
    private Node createChoiceBox(String... clas) {
        ChoiceBox<String> box = new ChoiceBox<>();
        box.getStyleClass().addAll(clas);
        box.getItems().setAll("Item 01", "Item 02");
        box.getSelectionModel().selectFirst();
        return box;
    }
}
