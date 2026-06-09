package io.github.gleidsonmt.dashboardfx.presentation.core;

import io.github.gleidsonmt.dashboardfx.presentation.CustomizablePresentation;
import io.github.gleidsonmt.dashboardfx.presentation.internal.Tutorial;
import io.github.gleidsonmt.dashboardfx.utils.TutorialUtils;
import io.github.gleidsonmt.glad.controls.icon.Icon;
import io.github.gleidsonmt.glad.controls.icon.SVGIcon;
import io.github.gleidsonmt.presentation.Presentation;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.scene.text.TextFlow;

/**
 * Description:
 *
 * @author Gleidson Neves da Silveira | <a href="mailto:gleidisonmt@gmail.com">gleidisonmt@gmail.com</a> <br>
 * Create on 05/06/2026
 */
public class DesignSystem extends CustomizablePresentation {
    public DesignSystem() {
        super("Design System");
    }

    @Override
    public Presentation create() {

        return new Tutorial()
                .overview()
                .indicators()
                .h1(new SVGIcon(Icon.DESIGN_SERVICES, 2),"Design System")
                .separator()

                .text("The most important thing to understand is that the design system here is a set of css files essentially.")
                .text("The css files are organized in a way that makes it easy to understand and modify the application.")
                .text("To do that the css files are divided into categories and variables are used to define the colors, fonts, and other properties.\n")
                .text("Every css file can clash with another, so it's important to use the same order.")
                .text("The css files are organized in the following order:")
                .text("colors.css, typographic.css, properties.css, shapes.css, skin.css, bootstrap.css, immersive_scroll.css")
                .text("Some variables are used in the whole project design, so it's important to use the same values.")
                .h2("Theme Provider")
                .separator()
                .text("""
                        Instead of passing a stylesheet to a scene, this lib uses a different approach.
                        He manages every stylesheet as a enum to injet it in the scene.
                        The class to do that is a static ThemeProvider see a call.
                        """)
                .code("""
                        ...
                        ThemeProvider.install(scene, Css.DEFAULT);
                        // The Css.DEFAULT is a enum with all mentioned classes above""")
                .text("That is works like")
                .code("""
                        scene.getStylesheets().addAll(
                            getClass().getResource("/css/colors.css").toExternalForm(),
                            getClass().getResource("/css/typographic.css").toExternalForm(),
                            getClass().getResource("/css/properties.css").toExternalForm(),
                            getClass().getResource("/css/shapes.css").toExternalForm(),
                            getClass().getResource("/css/skin.css").toExternalForm(),
                            getClass().getResource("/css/bootstrap.css").toExternalForm(),
                            getClass().getResource("/css/immersive_scroll.css").toExternalForm()
                        );""")
                .text("It's not the intention to make short but fast to use.")
                .separator()
                .h2("Primary Colors")
                .demo(new Node[] {
                        createBox("Primary", "#4285f4"),
                        createBox("Info", "#33B5E5"),
                        createBox("Success", "#02C852"),
                        createBox("Warning", "#FF8800"),
                        createBox("Danger", "#FF3547"),
                        createBox("Secondary", "#AA66CC"),
                        createBox("Unique", "#880E4F"),
                        createBox("Elegant", "#2E2E2E")
                })

                .separator()
                .text("""
                        Region is the base class for all JavaFX Node controls and all layout containers.
                        So manipulating its properties can be applied to another control everywhere.
                        For example, the background color of a button can be changed to the primary color as well, or a Label background.""")
                .code("""
                        var region = new Region();
                        // will have the same effect
                        region.getStyleClass().add("bg-primary");""")
                .node(TutorialUtils.createLink("See more", "Region"))

                .h2("Typographic")
                .separator()
                .text("The typographic uses only two fonts. Can be called using the same method above.")
                .code("ThemeProvider.install(scene, Font.Poppins, Font.INSTAGRAM);")
                .demo(new Node[]{
                        createFontContainer("Instagram"),
                        createFontContainer("Poppins")
                })
                .text("Add a font to a text.")
                .code("""
                        var text = new Text();
                        //text.getStyleClass().add("font-[font_name]")
                        text.getStyleClass().add("font-instagram");""")
//        ThemeProvider
//        Instead of passing a stylesheet to a scene, this lib uses a different approach.
//                He manages every stylesheet as a enum to injet it in the scene.
//        The class to do that is a static ThemeProvider see a call.
                ;
    }

    private VBox createBox(String color, String hex) {
        VBox box = new VBox(createLabel(color), createLabel(hex), createLabel("-" + color.toLowerCase()));
        box.getStyleClass().add("bg-" + color.toLowerCase());
        box.getStyleClass().addAll("padding-10 w-100 h-100 radius-10".split(" "));
        return box;
    }

    private Label createLabel(String text) {
        var label = new Label(text);
        label.getStyleClass().addAll("text-white".split(" "));
        return label;
    }


    private HBox createFontContainer(String example) {
        HBox box = new HBox(
                createRightContainer("Aa", example.toLowerCase()),
                createLeftContainer(example, example.toLowerCase().equals("poppins") ? " (Tile/Headings) " : " (Body/Text) ")
        );
        box.setSpacing(20);
        box.getStyleClass().addAll("padding-10  radius-10".split(" "));
        return box;
    }

    private Label createRightContainer(String val, String font) {
        Label label = new Label(val);
        label.getStyleClass().addAll("font-" + font, "text-48");
        return label;
    }

    private Node createLeftContainer(String val, String desc) {
        System.out.println("val.toLowerCase() = " + val.toLowerCase());
        var title = new Label(val);
        var description = new Label(desc);
        HBox box = new HBox(title, description);
        box.getStyleClass().addAll(("padding-10  h-100 radius-10 h3 font-" + val.toLowerCase()).split(" "));
        var textContainer = new TextFlow();
        for (char letter = 'A'; letter <= 'Z'; letter++) {
            var text = new Text(letter + "");
            textContainer.getChildren().add(text);
            text.getStyleClass().addAll("h5", "font-" + val.toLowerCase());
        }
        textContainer.getChildren().add(new Text("\n"));
        for (char letter = 'A'; letter <= 'Z'; letter++) {
            var text = new Text((letter + "").toLowerCase());
            textContainer.getChildren().add(text);
            text.getStyleClass().add("font-" + val.toLowerCase());
        }
        textContainer.getChildren().add(new Text("\n"));
        for (int number = 0; number <= 9; number++) {
            var text = new Text((number + ""));
            textContainer.getChildren().add(text);
            text.getStyleClass().add("font-" + val.toLowerCase());
        }

        VBox container = new VBox(box, textContainer);
        container.getStyleClass().addAll(("padding-10  h-100 radius-10 font-" + val.toLowerCase()).split(" "));

        return container;
    }

}
