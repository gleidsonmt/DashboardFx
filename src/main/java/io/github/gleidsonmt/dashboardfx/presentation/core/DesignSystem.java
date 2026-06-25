package io.github.gleidsonmt.dashboardfx.presentation.core;

import io.github.gleidsonmt.dashboardfx.presentation.CustomizablePresentation;
import io.github.gleidsonmt.dashboardfx.presentation.internal.Tutorial;
import io.github.gleidsonmt.dashboardfx.utils.TutorialUtils;
import io.github.gleidsonmt.glad.controls.icon.Icon;
import io.github.gleidsonmt.glad.controls.icon.SVGIcon;
import io.github.gleidsonmt.presentation.Presentation;
import io.github.gleidsonmt.presentation.Row;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Region;
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
    new Text();
        return new Tutorial()
                .overview()
                .indicators()
                .h1(new SVGIcon(Icon.DESIGN_SERVICES, 1.8), "Design System")
                .separator()

                .text("The most important thing to understand is that the design system here is a set of css files, essentially.")
                .text("The CSS files are structured to simplify understanding and modifying the application.")
                .text("This is achieved by organizing CSS files into categories and utilizing variables for colors, fonts, and other properties..\n")
                .text("Every css file can clash with another, so it's important to use the same order.")
                .text("The css files are organized in the following order:")

                // Here using a table of content
                .list(new String[]{"colors.css", "typographic.css", "properties.css", "shapes.css", "immersive_scroll.css"})

                .text("Why am I using this design system? well, sometimes it's a hell to combine css files, and it's not easy to understand what's happening.")
                .h2("Injecting")
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
                .text("That works like")
                .code("""
                        scene.getStylesheets().addAll(
                            getClass().getResource("/css/colors.css").toExternalForm(),
                            getClass().getResource("/css/typographic.css").toExternalForm(),
                            getClass().getResource("/css/properties.css").toExternalForm(),
                            getClass().getResource("/css/shapes.css").toExternalForm(),
                            getClass().getResource("/css/immersive_scroll.css").toExternalForm()
                        );""")
                .text("It's not the intention to make short but fast to use.")
                .h4("Types")
                .text("The css files are divided into 3 types:")
//                .text("CSS, FONT and THEME")
                .list(new String[]{"CSS", "FONT", "THEME"})
                .text("Every these ones are a css, but they have different purposes.")
                .text("CSS is used to style the components. FONT is used to style the text. THEME is used to style the theme.")
                .h5("Css")
                .text("Normally used to add a component like ~accent{Css.BUTTON}~, but are also the special ones.")
                .text("The ~accent{Css.ALL}~ is a enum with all mentioned classes above including all components.")
                .text("The ~accent{Css.DEFAULT}~ is a enum with the minimum style to start.")
                .h5("Fonts")
                .text("Used to load optionally custom fonts. By default, the font Poppins is loaded.")
                .h5("Theme")
                .text("Used to choose the theme.")
                .text("It's possible to pass all together.")
                .code("""
                        ..
                        ThemeProvider.install(scene, Css.DEFAULT, Font.INSTAGRAM, Theme.DARK);
                        ..
                        stage.show();
                        """)
                .separator()
                .h3("Stylesheet")
                .text("That's a possibility as well replace the default theme.")
                .text("You can change component as you wish. But you can follow the design system as well.")
                .text("For that you can use the css global variables.")

                .text("The css global variables are:")
                .table(
                        new Row("-fx-background", "Region background color"),
                        new Row("-fx-foreground", "Secondary Color for background"),
                        new Row("-fx-accent", "Highlighted colors"),
                        new Row("-text-color", "Default Color for texts")
                )

                .text("The css global variables are managed by theme so if you're in dark theme and change to light, the color will be update as well.")
                .text("Suppose you added a custom variable called ~accent{main.css}~ and inside you have region and you want a color for that.")
                .code("""
                        .my-custom-region {
                            -fx-background-color: -fx-background; 
                        }
                        """, "CSS")
                .text("Instead you can also use a class ~secondary{.bg-theme}~")
                .code("""
                        myComponent.getStyleClass().add("bg-theme"); //
                        """)

                .text("Class ended in theme uses global variables.")


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
                .node(TutorialUtils.createLink("Go To Text", "Text"))

                .h2("Region")

                .separator()
                .text("""
                        Region is the base class for all JavaFX Node controls and all layout containers.
                        So manipulating its properties can be applied to another control everywhere.
                        For example, the background color of a button can be changed to the primary color as well, or a Label background.""")
                .code("""
                        var region = new Region();
                        // will have the same effect
                        region.getStyleClass().add("bg-primary");""")

                .h2("Default Colors")
                .demo(new Node[]{
                        createBox("Primary", "#4285f4"),
                        createBox("Info", "#33B5E5"),
                        createBox("Success", "#02C852"),
                        createBox("Warning", "#FF8800"),
                        createBox("Danger", "#FF3547"),
                        createBox("Secondary", "#AA66CC"),
                        createBox("Unique", "#880E4F"),
                        createBox("Elegant", "#2E2E2E")
                })


                .node(TutorialUtils.createLink("Go To Region", "Region"))
                ;
    }

    private VBox createLayer(String color, String text, String... properties) {
        VBox box = new VBox(createLabel(color));
        box.getStyleClass().addAll("bg-" + color.toLowerCase());
        box.getStyleClass().addAll(properties);
        box.getChildren().get(0).getStyleClass().addAll(properties);

        box.getStyleClass().addAll("padding-10 w-200 h-100 radius-10 h6 bold".split(" "));
        return box;
    }

    private VBox createBox(String color, String hex) {
        VBox box = new VBox(createLabel(color), createLabel(hex), createLabel("-" + color.toLowerCase()));
        box.getStyleClass().add("bg-" + color.toLowerCase());
        box.getStyleClass().addAll("padding-10 w-100 h-100 radius-10 h6 bold".split(" "));
        return box;
    }

    private Label createLabel(String text) {
        var label = new Label(text);
        label.getStyleClass().addAll("text-white".split(" "));
        return label;
    }


    private HBox createFontContainer(String example) {
        HBox box = new HBox(
                createRightContainer(example.toLowerCase()),
                createLeftContainer(example, example.equalsIgnoreCase("poppins") ? " (Tile/Headings) " : " (Body/Text) ")
        );
        box.setSpacing(20);
        box.getStyleClass().addAll("padding-10  radius-10".split(" "));
        return box;
    }

    private Label createRightContainer(String font) {
        Label label = new Label("Aa");
        label.getStyleClass().addAll("font-" + font, "text-48");
        return label;
    }

    private Node createLeftContainer(String val, String desc) {
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
