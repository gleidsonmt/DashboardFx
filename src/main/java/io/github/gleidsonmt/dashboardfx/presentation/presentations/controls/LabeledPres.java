package io.github.gleidsonmt.dashboardfx.presentation.presentations.controls;

import io.github.gleidsonmt.dashboardfx.presentation.CustomizablePresentation;
import io.github.gleidsonmt.dashboardfx.presentation.internal.Tutorial;
import io.github.gleidsonmt.dashboardfx.utils.TutorialUtils;
import io.github.gleidsonmt.glad.controls.icon.Icon;
import io.github.gleidsonmt.glad.controls.icon.SVGIcon;
import io.github.gleidsonmt.presentation.Presentation;
import io.github.gleidsonmt.presentation.Row;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.layout.HBox;

/**
 * @author Gleidson Neves da Silveira | gleidisonmt@gmail.com
 * Create on  20/02/2025
 */
public class LabeledPres extends CustomizablePresentation {

    public LabeledPres() {
        super("Labeled");
    }

    public Presentation create() {
        return new Tutorial()
                .indicators()
                .overview()
                .h1("Labeled")
                .separator()
                .text("""
                        A Labeled Control is one which has as part of its user interface a textual content associated with it.
                        For example, a Button displays text, as does a Label, a Tooltip, and many other controls."""
                )
                .code("""
                        // Install theme on scene
                        // put the name of the component in uppercase ex.
                        ThemeProvider.install(scene,
                        		...
                        		Css.LABEL);""")
                .h2("Label")
                .separator()
                .text("""
                        Label is a non-editable text control.
                        A Label is useful for displaying text that is required to fit within a specific space, and thus may need to use an ellipsis or truncation to size the string to fit.
                        Labels also are useful in that they can have mnemonics which, if used, will send focus to the Control listed as the target of the labelFor property.""")
                .legend("javafx.scene.control.Label")
                .text("The label is a mix with Region + Text shape and you can additional add a graphic.")

                .demo(new Label("Lorem ipsum dolor color"))
                .code("Label label = new Label(\"Lorem ipsum dolor color\");")

                .h3("Graphic")
                .legend("An optional icon for the Labeled.")
                .demo(new Node[]{
                        createDemo(new SVGIcon(Icon.MENU), "Menu"),
                        createDemo(new SVGIcon(Icon.ARROW_BACK), "Arrow"),
                        createDemo(new SVGIcon(Icon.LOCATION_ON), "Location")
                })
                .code("label.setGraphic(new SVGIcon(Icon.MENU));")
                .node(TutorialUtils.createLink("More options", "SVGIcon"))
                .h3("Display")
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

                .h2("Button")
                .separator()
                .text("A simple button control. The button control can contain text and/or a graphic.")
                .legend("javafx.scene.control.Button")
                .demo(new Node[]{
                        createButtonDemo("Default", true, false),
                        createButtonDemo("Cancel", false, true)
                })

                .demo(createCheckBox())
                .h3("Hyperlink")
                .text("""
                        An HTML like label which can be a graphic and/or text which responds to rollovers and clicks. When a hyperlink is clicked/pressed isVisited becomes true.
                        A Hyperlink behaves just like a Button.
                        When a hyperlink is pressed and released a ActionEvent is sent, and your application can perform some action based on this event.
                        """)
                .legend("javafx.scene.control.Hyperlink")
                .demo(createHyperlink("google.com"))

                .h3("CheckBox")
                .text("""
                        A tri-state selection Control typically skinned as a box with a checkmark or tick mark when checked.""")
                .legend("javafx.scene.control.CheckBox")

                .demo(
                        new CheckBox("Select me!")
                )

                .h3("ToggleGroup")
                .text(""" 
                        A class which contains a reference to all Toggles whose selected variables should be managed such that only a single Toggle within the ToggleGroup may be selected at any one time.
                        Generally ToggleGroups are managed automatically simply by specifying the name of a ToggleGroup on the Toggle, but in some situations it is desirable to explicitly manage which ToggleGroup is used by Toggles.""")
                .legend("javafx.scene.control.ToggleGroup")
                .code("""
                        ToggleGroup group = new ToggleGroup();
                        // Create your children children
                        group.getToggles().addAll(optionOne, optionTwo);
                        """)
                .h4("ToggleButton")
                .text("""
                        ToggleButton is a specialized control which has the ability to be selected.
                        Typically a ToggleButton is rendered similarly to a Button.
                        However, they are two different types of Controls.
                        A Button is a "command" button which invokes a function when clicked.""")
                .legend("javafx.scene.control.ToggleButton")
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
                .h4("RadioButton")
                .text("""
                        RadioButtons create a series of items where only one item can be selected. RadioButtons are a specialized ToggleButton.""")
                .legend("javafx.scene.control.RadioButton")

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


                .h3("MenuButton")
                .text("MenuButton is a specialized control which has a popup menu.")

                .demo(new Node[]{
                        createMenuButton(),
                        createMenuButton("menu-outlined")
                })
                .code("""
                        //Constructor
                        MenuButton menu_button = new MenuButton();
                        menu_button.setText("Menu Button");
                        menu_button.getItems().setAll(
                            new MenuItem("Item 01"),
                            new MenuItem("Item 02"),
                            new MenuItem("Item 03")
                        );
                        // Add second option design
                        menu_button.getStyleClass().add("menu-outlined");
                        """)
                .text("You can see more options of actions using popup bellow.")

                .h3("SplitMenuButton")
                .text("SplitMenuButton is a specialized control which has a popup menu and a button to open the menu.")

                .demo(new Node[]{
                        createSplitMenuButton(),
                        createSplitMenuButton("split-outlined")
                })
                .code("""
                        // Constructor
                        SplitMenuButton split_menu_button = new SplitMenuButton();
                        split_menu_button.setText("Split Menu Button");
                        split_menu_button.getItems().setAll(
                            new MenuItem("Item 01"),
                            new MenuItem("Item 02"),
                            new MenuItem("Item 03")
                        );
                        // Add second option design
                        menu_button.getStyleClass().add("split-outlined");
                        """)
                .text("You can see more options of actions using popup bellow.")
                .node(TutorialUtils.createLink("ChoiceBox", "ChoiceBoxPres"))

//                .node(new MenuButton("MenuButton"))
//                .node(new SplitMenuButton())
                ;
    }

    private Node createSplitMenuButton(String... cls) {
        SplitMenuButton menuButton = new SplitMenuButton(new MenuItem("Item 01"), new MenuItem("Item 02"), new MenuItem("Item 03"));
        menuButton.setText("Split Menu Button");
        menuButton.getStyleClass().addAll(cls);
        return menuButton;
    }

    private Node createMenuButton(String... cls) {
        MenuButton menuButton = new MenuButton("Menu Button");
        menuButton.getItems().setAll(new MenuItem("Item 01"), new MenuItem("Item 02"), new MenuItem("Item 03"));
        menuButton.getStyleClass().addAll(cls);
        return menuButton;
    }

    private Node createCheckBox() {
        CheckBox container = new CheckBox("Select me");
        container.setIndeterminate(true);
        container.setAllowIndeterminate(true);
        return container;
    }

    private Hyperlink createHyperlink(String _text, String... _classes) {
        Hyperlink text = new Hyperlink(_text);
        text.getStyleClass().addAll(_classes);
        return text;
    }

    private Node createGroupRadio() {
        ToggleGroup group = new ToggleGroup();
        RadioButton optionOne = new RadioButton("Option One");
        optionOne.setSelected(true);
        RadioButton optionTwo = new RadioButton("Option Two");
        HBox box = new HBox(optionOne, optionTwo);
        box.setSpacing(10);
        group.getToggles().addAll(optionOne, optionTwo);
        return box;
    }

    private Node createGroupButton() {
        ToggleGroup group = new ToggleGroup();
        ToggleButton optionOne = new ToggleButton("Option One");
        optionOne.setSelected(true);
        ToggleButton optionTwo = new ToggleButton("Option Two");
        HBox box = new HBox(optionOne, optionTwo);
        box.getStyleClass().addAll("bg-light-gray".split(" "));
        box.setStyle("-fx-background-radius: 11.5; -fx-border-radius: 11.5; -fx-padding: 3px");
        box.setSpacing(10);
        group.getToggles().addAll(optionOne, optionTwo);
        return box;
    }

    private Button createButtonDemo(String text, boolean _default, boolean _cancel, String... classes) {
        Button btn = new Button(text);
        btn.setDefaultButton(_default);
        btn.setCancelButton(_cancel);
        btn.getStyleClass().addAll(classes);
        return btn;
    }

    private Label createDemo(Node graphic, String _text, String... _classes) {
        Label text = new Label(_text);
        text.setGraphic(graphic);
        text.getStyleClass().addAll(_classes);
        return text;
    }
}
