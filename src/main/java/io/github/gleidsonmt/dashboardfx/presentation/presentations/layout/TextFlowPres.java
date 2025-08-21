package io.github.gleidsonmt.dashboardfx.presentation.presentations.layout;

import io.github.gleidsonmt.dashboardfx.ResizablePane;
import io.github.gleidsonmt.dashboardfx.presentation.core.CustomizablePresentation;
import io.github.gleidsonmt.dashboardfx.presentation.internal.Tutorial;
import javafx.scene.Node;
import javafx.scene.text.Text;
import javafx.scene.text.TextFlow;

/**
 * @author Gleidson Neves da Silveira | gleidisonmt@gmail.com
 * Create on  22/03/2025
 */
public class TextFlowPres extends CustomizablePresentation {

    public TextFlowPres() {
        super("TextFlow");
    }

    public Tutorial create() {
        return new Tutorial()
                .h3("Text Flow")
                .text("""
                        A specialized layout for rich text. It can be used to lay out several Text nodes in a single text flow.
                        TextFlow uses the text and the font of each Text node inside of it, plus its own width and text
                        alignment to determine the location for each child. A single Text node can span over several lines
                        due to wrapping, and the visual location of the Text node can differ from the logical location due to bidi reordering.
                        Any Node other than Text will be treated as an embedded object in the text layout. It will be inserted in the content using its preferred width, height, and baseline offset""")
                .legend("javafx.scene.text.TextFlow")
                .legend("Try to resize the pane.")
                .h4("Demo", "Text Flow")
                .node(createDemo())
                .code("TextFlow flow = new TextFlow(new Text(text));")
                .h3("Customizing", "Text Flow")
                .nodes(
                        createDemo("""
                                        Lorem ipsum dolor sit amet, consectetur adipiscing elit.
                                        Vestibulum quis velit ut dolor vestibulum aliquet in non sem.
                                        """,
                                "-fx-background-color: derive(-warning, 96%);",
                                "border-l-2", "border-warning", "padding-5", "text-warning"),
                        createDemo("""
                                        Lorem ipsum dolor sit amet, consectetur adipiscing elit.
                                        Vestibulum quis velit ut dolor vestibulum aliquet in non sem.
                                        """,
                                "-fx-background-color: derive(-danger, 110%);",
                                "border-l-2", "border-danger", "padding-5", "text-danger"),
                        createDemo("""
                                        Lorem ipsum dolor sit amet, consectetur adipiscing elit.
                                        Vestibulum quis velit ut dolor vestibulum aliquet in non sem.
                                        """,
                                "-fx-background-color: derive(-info, 110%);",
                                "border-l-2", "border-info", "padding-5", "text-info"),
                        createDemo("""
                                        Lorem ipsum dolor sit amet, consectetur adipiscing elit.
                                        Vestibulum quis velit ut dolor vestibulum aliquet in non sem.
                                        """,
                                "-fx-background-color: derive(-success, 110%);",
                                "border-l-2", "border-success", "padding-5", "text-success")
                )

                .code("""
                        // Example
                        flow.getStyleClass().addAll("border-l-2", "border-warning", "padding-5");
                        flow.setStyle("-fx-background-color: derive(-warning, 96%);");
                        """);
    }

    private Node createDemo() {
        return createDemo("""
                Lorem ipsum dolor sit amet, consectetur adipiscing elit. 
                Vestibulum quis velit ut dolor vestibulum aliquet in non sem. 
                """, "", "");
    }

    private Node createDemo(String text, String style, String... classes) {
        TextFlow flow = new TextFlow(new Text(text));
        flow.setStyle(style);
        flow.getStyleClass().addAll(classes);
        return new ResizablePane(flow);
    }

}
