package io.github.gleidsonmt.dashboardfx.presentation.presentations.controls;

import io.github.gleidsonmt.dashboardfx.presentation.core.CustomizablePresentation;
import io.github.gleidsonmt.dashboardfx.presentation.internal.Tutorial;
import javafx.collections.FXCollections;
import javafx.scene.Node;
import javafx.scene.control.ChoiceBox;

/**
 * @author Gleidson Neves da Silveira | gleidisonmt@gmail.com
 * Create on  25/08/2025
 */
public class ChoiceBoxPres extends CustomizablePresentation {

    public ChoiceBoxPres() {
        super("ChoiceBoxPres");
    }

    @Override
    public Tutorial create() {
        return new Tutorial()
                .h3("ChoiceBoxPres")
                .node(createChoiceBox())
                ;
    }
    private Node createChoiceBox() {
        ChoiceBox<String> box = new ChoiceBox<>(
                FXCollections.observableArrayList("Item 01", "Item 02")
        );
        return box;
    }
}
