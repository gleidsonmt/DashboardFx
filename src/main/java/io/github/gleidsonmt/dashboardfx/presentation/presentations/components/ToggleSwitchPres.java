package io.github.gleidsonmt.dashboardfx.presentation.presentations.components;

import io.github.gleidsonmt.dashboardfx.presentation.core.CustomizablePresentation;
import io.github.gleidsonmt.dashboardfx.presentation.internal.Tutorial;
import io.github.gleidsonmt.glad.controls.toggle_switch.ToggleSwitch;
import io.github.gleidsonmt.presentation.Row;
import javafx.scene.Node;

/**
 * @author Gleidson Neves da Silveira | gleidisonmt@gmail.com
 * Create on  21/03/2025
 */
public class ToggleSwitchPres extends CustomizablePresentation {

    public ToggleSwitchPres() {
        super("ToggleSwitch");
    }

    public Tutorial create() {
        return new Tutorial()
                .h3("Toggle Switch")
                .text("""
                        A toggle specialized in turning off and on action.""")
                .legend("io.github.gleidsonmt.glad.controls.toggle_switch.ToggleSwitch")
                .h4("Demo", "Toggle Switch")
                .demo(
                        new ToggleSwitch()
                )
                .code("""
                        // Constructors
                        ToggleSwitch toggleSwitch = new ToggleSwitch();
                        // Set on
                        toggleSwitch.setOn(true);
                        """)

                .table(
                        "Property", "Value",
                        new Row("-fx-arc-size", "<number>"),
                        new Row("-fx-color-animation", "<paint>"),
                        new Row("-fx-track-size", "<paint>"),
                        new Row("-fx-track-color", "<paint>")
                )

                .h3("Custom", "Toggle Switch")
                .demo(
                        new Node[]{
                                createDemo("-fx-arc-size: 0px;"),
                                createDemo("-fx-track-size: 5px; -fx-arc-size: 0px;"),
                                createDemo("-fx-track-size: 5px; -fx-arc-size: 5px;"),

                        }
                )

                .code("""
                        // Example 01
                        ToggleSwitch toggleSwitch = new ToggleSwitch();
                        toggleSwitch.setStyle("-fx-arc-size: 0px;");
                        
                        // Example 02
                        ToggleSwitch toggleSwitch = new ToggleSwitch();
                        toggleSwitch.setStyle("-fx-track-size: 5px; -fx-arc-size: 0px;");
                        
                        // Example 03
                        ToggleSwitch toggleSwitch = new ToggleSwitch();
                        toggleSwitch.setStyle("-fx-track-size: 5px; -fx-arc-size: 5px;");
                        """);
    }

    private Node createDemo(String style) {
        ToggleSwitch toggleSwitch = new ToggleSwitch();
        toggleSwitch.setStyle(style);
        return toggleSwitch;
    }

}
