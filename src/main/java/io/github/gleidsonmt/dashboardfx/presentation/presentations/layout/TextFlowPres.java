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
                .h3("TextFlow")
                .text("""
                        A specialized layout for rich text. It can be used to lay out several Text nodes in a single text flow.
                        TextFlow uses the text and the font of each Text node inside of it, plus its own width and text
                        alignment to determine the location for each child. A single Text node can span over several lines
                        due to wrapping, and the visual location of the Text node can differ from the logical location due to bidi reordering.
                        Any Node other than Text will be treated as an embedded object in the text layout. It will be inserted in the content using its preferred width, height, and baseline offset""")
                .legend("javafx.scene.text.TextFlow")
                .legend("Try to resize the pane.")
                .node(createDemo())
                .h4("Install", "TextFlow")
                .code("TextFlow flow = new TextFlow(new Text(text));")

          ;
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
