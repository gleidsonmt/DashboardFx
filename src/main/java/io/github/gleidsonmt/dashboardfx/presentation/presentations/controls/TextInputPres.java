package io.github.gleidsonmt.dashboardfx.presentation.presentations.controls;

import io.github.gleidsonmt.dashboardfx.presentation.CustomizablePresentation;
import io.github.gleidsonmt.dashboardfx.presentation.internal.Tutorial;
import io.github.gleidsonmt.dashboardfx.utils.TutorialUtils;
import io.github.gleidsonmt.glad.controls.icon.Icon;
import io.github.gleidsonmt.glad.controls.skin.TextBoxBase;
import io.github.gleidsonmt.glad.controls.text_box.PasswordBox;
import io.github.gleidsonmt.glad.controls.text_box.SearchBox;
import io.github.gleidsonmt.glad.controls.text_box.TextBox;
import io.github.gleidsonmt.glad.theme.Css;
import io.github.gleidsonmt.presentation.Presentation;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.control.TextArea;

/**
 * @author Gleidson Neves da Silveira | gleidisonmt@gmail.com
 * Create on  11/04/2025
 */
public class TextInputPres extends CustomizablePresentation {

    public TextInputPres() {
        super("TextInput");
    }

    public Presentation create() {
        return new Tutorial()
                .overview()
                .indicators()
                .h1("TextInputControl")
                .separator()
                .text("Abstract base class for text input controls.")

                .code("""
                        // Install theme on scene
                        // put the name of the component in uppercase ex.
                        ThemeProvider.install(scene,
                        		...
                        		Css.[CONTROL_NAME]);""")
                .h2("TextField")
                .separator()
                .text("""
                        Text input component that allows a user to enter a single line of unformatted text.""")
                .legend("javafx.scene.control.TextField")
                .demo(new TextField("TextField"))

                .h3("PasswordField")
                .text("Text field that masks entered characters.")
                .legend("javafx.scene.control.PasswordField")
                .demo(createPassword())


                .h2("TextBox")
                .separator()
                .text("A TextBox is a complex TextField, allowing to add a graphic a an action.")
                .text("The graphic can be a Icon or a Node.")
                .text("The default action clear the text and only appears when the text is not empty.")
                .legend("io.github.gleidsonmt.glad.controls.text_box.TextBox")
                .demo(createTextBox(Icon.LOCATION_ON, "Location", true))
                .code("""
                        TextBox textBox = new TextBox();
                        textBox.setIcon(new SVGIcon(Icon.LOCATION_ON)); // sets the icon
                        textBox.setAction(true); // sets the action
                        """)

                .h3("PasswordBox")
                .text("The same as TextBox, but with a mask fort text.")
                .text("The default action is to show the mask.")
                .legend("io.github.gleidsonmt.glad.controls.text_box.PasswordBox")

                .demo(createPasswordBox())
                .code("""
                        PasswordBox passwordBox = new PasswordBox();
                        passwordBox.setIcon(new SVGIcon(Icon.VPN_KEY_FILLED)); // sets the icon
                        passwordBox.setAction(true)); // sets the action
                        """)

                .h3("TextArea")
                .text("Text input component for entering and editing multiple lines of text.")
                .legend("io.github.gleidsonmt.glad.controls.text_box.TextArea")

                .demo(createTextArea())
                .code("""
                         TextArea textArea = new TextArea();
                         textArea.setPromptText("TextArea");
                        """)

                ;
    }

    private TextBoxBase createCustomTextBox(Icon icon, String text, boolean action) {
        TextBoxBase textBoxBase = new TextBoxBase() {
            {
                setStyle("-fx-border-radius: 0px;");
                var left = new Label("https://");
                left.getStyleClass().addAll("border-2 border-light-gray-2 bg-light-gray-2".split(" "));
                left.setStyle("-fx-border-width: 2px 0px 2px 2px; -fx-border-radius: 10px;");
                left.setMinHeight(40);
                setLeftNode(left);
                setRightNode(new Label(".com"));
//                getStyleClass().addAll("text-box");
            }

        };
        return textBoxBase;
    }

    private PasswordBox createPasswordBox() {
        PasswordBox password = new PasswordBox(Icon.VPN_KEY_FILLED, "pass", true);
        password.setPromptText("PasswordBox");
        return password;
    }

    private TextBox createTextBox(Icon icon, String text, boolean action) {
        TextBox password = new TextBox(icon, text, action);
        password.setPromptText(text);
        return password;
    }

    private TextField createTextField() {
        PasswordField password = new PasswordField();
        password.setPromptText("TextField");
        return password;
    }

    private TextField createPassword() {
        PasswordField password = new PasswordField();
        password.setPromptText("PasswordField");
        return password;
    }

    private TextArea createTextArea() {
        TextArea textArea = new TextArea();
        textArea.setPromptText("TextArea");
        return textArea;
    }
}
