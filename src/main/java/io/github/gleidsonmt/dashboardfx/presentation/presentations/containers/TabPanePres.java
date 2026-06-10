package io.github.gleidsonmt.dashboardfx.presentation.presentations.containers;

import io.github.gleidsonmt.dashboardfx.presentation.CustomizablePresentation;
import io.github.gleidsonmt.dashboardfx.presentation.internal.Tutorial;
import io.github.gleidsonmt.dashboardfx.utils.TutorialUtils;
import io.github.gleidsonmt.glad.theme.Css;
import io.github.gleidsonmt.presentation.Presentation;
import javafx.geometry.Insets;
import javafx.geometry.Side;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;

/**
 * @author Gleidson Neves da Silveira | gleidisonmt@gmail.com
 * Create on  07/03/2025
 */
public class TabPanePres extends CustomizablePresentation {

    public TabPanePres() {
        super("TabPane");
    }

    public Presentation create() {
        TabPane demo = createDemo();
        return new Tutorial()
                .indicators()
                .overview()
                .h3("TabPane")
                .separator()
                .text("A control that allows switching between a group of Tabs. Only one tab is visible at a time. Tabs are added to the TabPanePres by using the getTabs.")
                // .link([link to explanation]) ir para temas
                .demo(demo)
                .h4("Install")
                .code(TutorialUtils.installExample(Css.TAB_PANE, "TabPane"))
                .code("""
                        TabPane tabPane = new TabPane();
                        Tab one = new Tab("Example 01", new Label("Tab Content 01"));
                        Tab two = new Tab("Example 02", new Label("Tab Content 02"));
                        tabPane.getTabs().addAll(one, two);
                        """)
                .h3("Sides")
                .text("That side property controls the position of the tabs above.")
                .demo(
                        createGroupRadio(demo)
                )
                .code("""
                        tabPane.setSide(Side.TOP) // default;
                        tabPane.setSide(Side.BOTTOM);
                        tabPane.setSide(Side.LEFT);
                        tabPane.setSide(Side.RIGHT);
                        """);
    }

    private Node createGroupRadio(TabPane pane) {
        ToggleGroup group = new ToggleGroup();
        RadioButton top = new RadioButton(Side.TOP.name());
        RadioButton right = new RadioButton(Side.RIGHT.name());
        RadioButton bottom = new RadioButton(Side.BOTTOM.name());
        RadioButton left = new RadioButton(Side.LEFT.name());
        HBox box = new HBox(top, right, bottom, left);
        box.setSpacing(10);
        group.getToggles().addAll(top, right, bottom, left);
        group.selectedToggleProperty().addListener((_, _, newVal) -> {
            if (newVal != null) {
                if (newVal instanceof RadioButton radio) {
                    pane.setSide(Side.valueOf(radio.getText()));
                }
            }
        });
        group.selectToggle(group.getToggles().getFirst());
        return box;
    }

    private TabPane createDemo() {
        TabPane tabPane = new TabPane();
        tabPane.setSide(Side.TOP);
        Tab one = new Tab("Example 01");
        Tab two = new Tab("Example 02");
        one.setContent(container(new Label("Tab Content 01")));
        two.setContent(container(new Label("Tab Content 02")));
        tabPane.getTabs().addAll(one, two);
        tabPane.setMinHeight(300);
        return tabPane;
    }

    private StackPane container(Node node) {
        StackPane container = new StackPane(node);
        container.setPadding(new Insets(20));
        return container;
    }
}
