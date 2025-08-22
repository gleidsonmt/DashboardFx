package io.github.gleidsonmt.dashboardfx.presentation.presentations.controls;

import io.github.gleidsonmt.dashboardfx.presentation.core.CustomizablePresentation;
import io.github.gleidsonmt.dashboardfx.presentation.internal.Tutorial;
import io.github.gleidsonmt.dashboardfx.utils.TutorialUtils;
import io.github.gleidsonmt.glad.theme.Css;
import javafx.collections.FXCollections;
import javafx.scene.Node;
import javafx.scene.control.*;

import java.time.LocalDate;

/**
 * @author Gleidson Neves da Silveira | gleidisonmt@gmail.com
 * Create on  22/08/2025
 */
public class ComboBoxBasePres extends CustomizablePresentation {

    public ComboBoxBasePres() {
        super("ComboBox");
    }

    @Override
    public Tutorial create() {
        return new Tutorial()
                .h3("ComboBoxBase")
                .overview()
                .indicators()
                .text("""
                        Abstract base class for ComboBox-like controls. 
                        Because of that all class bellow are descendants of ComboBoxBase.
                        """)
                .legend("javafx.scene.control.ComboBoxBase")
                .h3("ComboBox", "ComboBoxBase")
                .text("""
                        An implementation of the ComboBoxBase abstract class for the most common form of ComboBox, where a popup list is shown to users providing them with a choice that they may select from. 
                        For more information around the general concepts and API of ComboBox, refer to the ComboBoxBase class documentation.
                        """)
                .legend("javafx.scene.control.ComboBox")
                .node(createDefaultComboBox())
                .h4("Install", "ComboBox")
                .code(TutorialUtils.installExample(Css.COMBO_BOX, "ComboBox"))
                .h3("DatePicker", "ComboBoxBase")
                .legend("javafx.scene.control.DatePicker")
                .text("The DatePicker control allows the user to enter a date as text or to select a date from a calendar popup.")
                .node(createDefaultDatePicker())
                .h4("Install", "DatePicker")
                .code(TutorialUtils.installExample(Css.DATE_PICKER, "DatePicker"))
//                .node(new ColorPicker())
//                .node(new DatePicker())
//                .node(new DatePicker())
                ;
    }

    private Node createDefaultDatePicker() {
        return new DatePicker(LocalDate.now());
    }

    private Node createDefaultComboBox() {
        var combo =  new ComboBox<>(FXCollections.observableArrayList("Item 1", "Item 2", "Item 3"));
        combo.getSelectionModel().selectFirst();
        return combo;
    }
}
