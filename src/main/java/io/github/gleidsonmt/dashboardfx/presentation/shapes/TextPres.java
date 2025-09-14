package io.github.gleidsonmt.dashboardfx.presentation.shapes;

import io.github.gleidsonmt.dashboardfx.presentation.CustomizablePresentation;
import io.github.gleidsonmt.dashboardfx.presentation.internal.Tutorial;
import io.github.gleidsonmt.presentation.Row;
import javafx.scene.Node;
import javafx.scene.text.Text;

/**
 * @author Gleidson Neves da Silveira | gleidisonmt@gmail.com
 * Create on  20/02/2025
 */
public class TextPres extends CustomizablePresentation {

    public TextPres() {
        super("Text");
    }

    public Tutorial create() {
        return new Tutorial()
                .indicators()
                .overview()
                .h2("Text")
                .separator()
                .text("The Text class defines a node that displays a text. Paragraphs are separated by \n and the text is wrapped on paragraph boundaries.")
                .legend("javafx.scene.text.Text")
                .legend("(The base font here is set to Poppins, by default is not apply, but it's better to add all fonts before the app load. At the end you can see the code to add.)")
                // .link([link to explanation]) ir para temas
                .demo(new Text("Lorem ipsum dolor color"))
                .code("Text text = new Text(\"Lorem ipsum dolor color\");")
//                        .demonstration(List.of(
//                                new Text("Lorem ipsum dolor color")
//                        ), "Text text = new Text(\"Lorem ipsum dolor color\");", "")

                .h3("Install", "Text")
                .code("ThemeProvider.install(root, \n\t... \n\tCss.TYPOGRAPHIC);", "java")

                .demo(new Node[]{
                        createDemo("h1", "h1"),
                        createDemo("h2", "h2"),
                        createDemo("h3", "h3"),
                        createDemo("h4", "h4"),
                        createDemo("h5", "h5"),
                        createDemo("h6", "h6")
                })
                .table(
                        new Row("h1", "-fx-font-size: 36px;"),
                        new Row("h2", "-fx-font-size: 30px;"),
                        new Row("h3", "-fx-font-size: 24px;"),
                        new Row("h4", "-fx-font-size: 18px;"),
                        new Row("h5", "-fx-font-size: 14px;"),
                        new Row("h6", "-fx-font-size: 12px;")
                )
                .code(" text.getStyleClass().add(\"h-[1|2|3|4|5|6]\"")
                .h3("Colors", "Text")
                .demo(new Node[]{
                        createDemo("Text Accent", "text-accent"),
                        createDemo("Text Info", "text-info"),
                        createDemo("Text Success", "text-success"),
                        createDemo("Text Warning", "text-warning"),
                        createDemo("Text Danger", "text-danger"),
                        createDemo("Text Secondary", "text-secondary"),
                        createDemo("Text Unique", "text-unique"),
                        createDemo("Text Elegant", "text-elegant")

                })
                .table(
                        new Row("text-accent", "-text-color: -fx-accent;"),
                        new Row("text-info", "-text-color: -info;"),
                        new Row("text-elegant", "-text-color: -elegant;")
                )
                .code("text.getStyleClass().add(\"text-[info|elegant|...]\");")

                .h3("Sizes", "Text")
                .demo(new Node[]{
                        createDemo("text-10", "text-10"),
                        createDemo("text-12", "text-12"),
                        createDemo("text-14", "text-14"),
                        createDemo("text-18", "text-18"),
                        createDemo("text-20", "text-20"),
                        createDemo("text-22", "text-22"),
                        createDemo("text-24", "text-24"),
                        createDemo("text-30", "text-30"),
                        createDemo("text-34", "text-34"),
                        createDemo("text-36", "text-36"),
                        createDemo("text-48", "text-48")
                })

                .code("text.getStyleClass().add(\"text-[*number*]\");")
                .h3("Fonts", "Text")
                .demo(new Node[]{
                        createDemo("Normal"),
                        createDemo("Instagram", "font-instagram", "h5"),
                        createDemo("Instagram Headline", "font-instagram-headline", "h5"),
                })
                .code("ThemeProvider.install(root, Css.POPPINS, Css.INSTAGRAM);");
    }

    private Text createDemo(String _text, String... _classes) {
        Text text = new Text(_text);
        text.getStyleClass().addAll(_classes);
        return text;
    }
}
