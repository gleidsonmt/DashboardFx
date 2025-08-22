package io.github.gleidsonmt.dashboardfx.presentation.presentations.controls;

import io.github.gleidsonmt.dashboardfx.presentation.core.CustomizablePresentation;
import io.github.gleidsonmt.dashboardfx.presentation.internal.Tutorial;
import io.github.gleidsonmt.dashboardfx.utils.TutorialUtils;
import io.github.gleidsonmt.glad.controls.icon.Icon;
import io.github.gleidsonmt.glad.controls.icon.SVGIcon;
import io.github.gleidsonmt.glad.theme.Css;
import io.github.gleidsonmt.presentation.Row;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Region;
import javafx.scene.layout.StackPane;

/**
 * @author Gleidson Neves da Silveira | gleidisonmt@gmail.com
 * Create on  20/02/2025
 */
public class LabeledPres extends CustomizablePresentation {

    public LabeledPres() {
        super("Labeled");
    }

    public Tutorial create() {
        return new Tutorial()
                .indicators()
                .overview()
                .h3("Labeled", null)
                .text("""
                        A Labeled Control is one which has as part of its user interface a textual content associated with it.
                        For example, a Button displays text, as does a Label, a Tooltip, and many other controls."""
                )
                .h3("Label", "Labeled")
                .text("""
                        Label is a non-editable text control.
                        A Label is useful for displaying text that is required to fit within a specific space, and thus may need to use an ellipsis or truncation to size the string to fit. 
                        Labels also are useful in that they can have mnemonics which, if used, will send focus to the Control listed as the target of the labelFor property.""")
                .legend("javafx.scene.control.Label")
                .text("The label is a mix with Region + Text shape and you can additional add a graphic.")
//                        .legend("(The base font here is set to Poppins, by default, is not apply, but it's better to add all fonts before the app load. At the end you can see the code to add.)")
                // .link([link to explanation]) ir para temas

                .demo(new Label("Lorem ipsum dolor color"))
                .code("Label label = new Label(\"Lorem ipsum dolor color\");")

                .h3("Graphic", "Label")
                .legend("An optional icon for the Labeled.")
                .demo(new Node[]{
                        createDemo(new SVGIcon(Icon.MENU), "Menu"),
                        createDemo(new SVGIcon(Icon.ARROW_BACK), "Arrow"),
                        createDemo(new SVGIcon(Icon.LOCATION_ON), "Location")
                })
                .code("label.setGraphic(new SVGIcon(Icon.MENU));")
                .node(TutorialUtils.createLink("More options", "SVGIcon"))
                .h3("Display", "Label")
                .table(
                        new Row("display-top", " -fx-content-display: top;"),
                        new Row("display-right ", "-fx-content-display: right;"),
                        new Row("display-bottom", "-fx-content-display: bottom;"),
                        new Row("display-left", "-fx-content-display: left;"),
                        new Row("display-center", "-fx-content-display: center;"),
                        new Row("display-graphic", "-fx-content-display: graphic_only;"),
                        new Row("display-text", " -fx-content-display: text_only;")
                )
                .demo(new Node[]{
                        createDemo(new SVGIcon(Icon.ADD), "Label", "display-top", "border-2", "border-light-gray-2", "padding-5"),
                        createDemo(new SVGIcon(Icon.ADD), "Label", "display-right", "border-2", "border-light-gray-2", "padding-5"),
                        createDemo(new SVGIcon(Icon.ADD), "Label", "display-bottom", "border-2", "border-light-gray-2", "padding-5"),
                        createDemo(new SVGIcon(Icon.ADD), "Label", "display-left", "border-2", "border-light-gray-2", "padding-5"),
                        createDemo(new SVGIcon(Icon.ADD), "Label", "display-center", "border-2", "border-light-gray-2", "padding-5"),
                        createDemo(new SVGIcon(Icon.ADD), "Label", "display-graphic", "border-2", "border-light-gray-2", "padding-5"),
                        createDemo(new SVGIcon(Icon.ADD), "Label", "display-text", "border-2", "border-light-gray-2", "padding-5")
                })

                .h3("Button", "Labeled")
                .text("A simple button control. The button control can contain text and/or a graphic.")
                .legend("javafx.scene.control.Button")
                .demo(new Node[]{
                        createButtonDemo("Default", true, false),
                        createButtonDemo("Cancel", false, true)
                })
                .h4("Install", "Button")
                .code(TutorialUtils.installExample(Css.BUTTON, "Button") + """
                        
                        // Cancel button option
                        button.setCancelButton(true);
                        """)

                .h2("Hyperlink", "Button")
                .text("""
                        An HTML like label which can be a graphic and/or text which responds to rollovers and clicks. When a hyperlink is clicked/pressed isVisited becomes true.
                        A Hyperlink behaves just like a Button.
                        When a hyperlink is pressed and released a ActionEvent is sent, and your application can perform some action based on this event.
                        """)
                .legend("javafx.scene.control.Hyperlink")
                .demo(createHyperlink("google.com"))
                .h4("Install", "Hyperlink")
                .code(TutorialUtils.installExample(Css.HYPERLINK, "Hyperlink", "\"google.com\""))

//                        .legend("(The base font here is set to Poppins, by default is not apply, but it's better to add all fonts before the app load. At the end you can see the code to add.)")
                // .link([link to explanation]) ir para temas



                .h3("CheckBox", "Button")
                .text("""
                        A tri-state selection Control typically skinned as a box with a checkmark or tick mark when checked.""")
                .legend("javafx.scene.control.CheckBox")

                .demo(createCheckBox("Select me"))
                .h4("Install", "CheckBox")
                .code(TutorialUtils.installExample(Css.CHECK_BOX, "CheckBox", "\"Select me\""))


//                .code("CheckBox checkBox = new CheckBox(\"Select me\");")

                .h3("ToggleButton", "Button")
                .text("""
                        ToggleButton is a specialized control which has the ability to be selected.
                        Typically a ToggleButton is rendered similarly to a Button. 
                        However, they are two different types of Controls. 
                        A Button is a "command" button which invokes a function when clicked.""")
                .legend("javafx.scene.control.ToggleButton")

                .demo(
                        new ToggleButton("Toggle")
                )
                .h4("Install", "ToggleButton")
                .code(TutorialUtils.installExample(Css.TOGGLE_BUTTON, "ToggleButton", "\"Button\""))

                .h3("RadioButton", "Button")
                .text("""
                        RadioButtons create a series of items where only one item can be selected. RadioButtons are a specialized ToggleButton.""")
                .legend("javafx.scene.control.RadioButton")
                .demo(createDemoRadio("Select me"))
                .h4("Install", "RadioButton")
                .code(TutorialUtils.installExample(Css.RADIO_BUTTON, "RadioButton", "\"Select me\""))

                .h3("ToggleGroup", "Button")
                .text(""" 
                        A class which contains a reference to all Toggles whose selected variables should be managed such that only a single Toggle within the ToggleGroup may be selected at any one time.
                        Generally ToggleGroups are managed automatically simply by specifying the name of a ToggleGroup on the Toggle, but in some situations it is desirable to explicitly manage which ToggleGroup is used by Toggles.""")
                .legend("javafx.scene.control.ToggleGroup")
                .h4("ToggleButton", "ToggleGroup")
                .demo(createGroupButton())
                .code("""
                        //Create a group
                        ToggleGroup group = new ToggleGroup();
                        // Creating the children
                        ToggleButton optionOne = new ToggleButton("Option One");
                        ToggleButton optionTwo = new ToggleButton("Option Two");
                        // Create a layout
                        HBox box = new HBox(optionOne, optionTwo);
                        box.setSpacing(10);
                        // add them to the group
                        group.getToggles().addAll(optionOne, optionTwo);
                        """)
                .h4("RadioButton", "ToggleGroup")
                .demo(createGroupRadio())
                .code("""
                        //Create a group
                        ToggleGroup group = new ToggleGroup();
                        // Creating the children
                        RadioButton optionOne = new RadioButton("Option One");
                        RadioButton optionTwo = new RadioButton("Option Two");
                        // Create a layout
                        HBox box = new HBox(optionOne, optionTwo);
                        box.setSpacing(10);
                        // add them to the group
                        group.getToggles().addAll(optionOne, optionTwo);
                        """)

//                .node(new MenuButton("MenuButton"))
//                .node(new SplitMenuButton())
                ;
    }

    private Node createCheckBox(String text) {
        CheckBox container = new CheckBox(text);
        container.setIndeterminate(true);
        container.setAllowIndeterminate(true);
        return container;
    }

    private Hyperlink createHyperlink(String _text, String... _classes) {
        return createHyperlink(null, _text, _classes);
    }

    private Hyperlink createHyperlink(Node graphic, String _text, String... _classes) {
        Hyperlink text = new Hyperlink(_text);
        text.setGraphic(graphic);
        text.getStyleClass().addAll(_classes);
        return text;
    }

    private Node createDemoRadio(String text) {
        RadioButton container = new RadioButton(text);
        return container;
    }

    private Node createGroupRadio() {
        ToggleGroup group = new ToggleGroup();
        RadioButton optionOne = new RadioButton("Option One");
        RadioButton optionTwo = new RadioButton("Option Two");
        HBox box = new HBox(optionOne, optionTwo);
        box.setSpacing(10);
        group.getToggles().addAll(optionOne, optionTwo);
        return box;
    }

    private Node createGroupButton() {
        ToggleGroup group = new ToggleGroup();
        ToggleButton optionOne = new ToggleButton("Option One");
        ToggleButton optionTwo = new ToggleButton("Option Two");
        HBox box = new HBox(optionOne, optionTwo);
        box.setSpacing(10);
        group.getToggles().addAll(optionOne, optionTwo);
        return box;
    }

    private Node createSimpleGroup() {
        ToggleGroup group = new ToggleGroup();
        ToggleButton left = new ToggleButton("Left");
        ToggleButton middle = new ToggleButton("Middle");
        ToggleButton right = new ToggleButton("Right");
        left.getStyleClass().addAll("pill-left");
        right.getStyleClass().addAll("pill-right");
        middle.getStyleClass().addAll("radius-0");
        group.getToggles().addAll(left, middle, right);
        return new HBox(left, middle, right);
    }


    private Node createButtonDemo(String... classes) {
        return createButtonDemo("Button", false, false, classes);
    }

    private Button createButtonDemo(String text, String... classes) {
        return createButtonDemo(text, false, false, classes);
    }

    private Button createButtonDemo(String text, boolean _default, String... classes) {
        return createButtonDemo(text, _default, false, classes);
    }

    private Button createButtonDemo(String text, Node graphic, String... classes) {
        Button button = createButtonDemo(text, classes);
        button.setGraphic(graphic);
        return button;
    }

    private Button createButtonDemo(String text, boolean _default, boolean _cancel, String... classes) {
        Button btn = new Button(text);
        btn.setDefaultButton(_default);
        btn.setCancelButton(_cancel);
        btn.getStyleClass().addAll(classes);
        return btn;
    }


    private Node createCustom(String text, String color) {

        Region region = new Region();
        region.setMinSize(20, 20);
        region.getStyleClass().addAll("bg-" + color, "round", "bg-insets-5", "border-5");

        region.getStyleClass().addAll("bg-" + color);
        region.setStyle("-fx-border-color: derive(-" + color + ", 90%);");

        Label label = new Label(text);
        label.setGraphic(region);

        return label;
    }

    private Label createDemo(String _text, String... _classes) {
        return createDemo(null, _text, _classes);
    }

    private Label createDemo(Node graphic, String _text, String... _classes) {
        Label text = new Label(_text);
        text.setGraphic(graphic);
        text.getStyleClass().addAll(_classes);
        return text;
    }
}
