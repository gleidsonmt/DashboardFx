package io.github.gleidsonmt.dashboardfx.presentation.presentations.containers;

import io.github.gleidsonmt.dashboardfx.presentation.CustomizablePresentation;
import io.github.gleidsonmt.dashboardfx.presentation.internal.Tutorial;
import io.github.gleidsonmt.dashboardfx.utils.TutorialUtils;
import io.github.gleidsonmt.glad.theme.Css;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.control.TitledPane;
import javafx.scene.layout.StackPane;

/**
 * @author Gleidson Neves da Silveira | gleidisonmt@gmail.com
 * Create on  07/03/2025
 */
public class TitledPanePres extends CustomizablePresentation {

    public TitledPanePres() {
        super("TitledPane");
    }

    public Tutorial create() {
        return new Tutorial()
                .indicators()
                .overview()
                .h3("TitledPane")
                .separator()
                .text("A control that allows switching between a group of Tabs. Only one tab is visible at a time. Tabs are added to the TabPanePres by using the getTabs.")
                // .link([link to explanation]) ir para temas
                .demo(createExample())
                .h4("Install", "TitledPane")
                .code(TutorialUtils.installExample(Css.TITLED_PANE, "TabPane") + "\npane.setContent(new StackPane(new Label(\"TitledPane Content\")));")
            ;
    }

    private Node createExample() {
        TitledPane pane = new TitledPane();
        pane.setText("TitledPane");
        pane.setContent(new StackPane(new Label("TitledPane Content")));
        return pane;
    }

}
