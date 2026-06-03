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
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.Skin;
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

    public Tutorial create() {
        return new Tutorial()
                .overview()
                .indicators()
                .h3("TextInputControl")
                .text("Abstract base class for text input controls.")

                .h3("TextField", "TextInputControl")
                .text("""
                        Text input component that allows a user to enter a single line of unformatted text.""")
                .legend("javafx.scene.control.TextField")
                .demo(createTextField())
                .h4("Install", "TextField")
                .code(TutorialUtils.installExample(Css.TEXT_FIELD, "TextField"))

                .h3("PasswordField", "TextField")
                .text("Text field that masks entered characters.")
                .legend("javafx.scene.control.PasswordField")
                .demo(createPassword())
                .h4("Install", "PasswordField")
                .code(TutorialUtils.installExample(Css.TEXT_FIELD, "PasswordField"))

                .h3("TextBox", "TextInputControl")
                .text("A TextBox is a complex TextField, allowing to add a graphic a an action.")
                .legend("io.github.gleidsonmt.glad.controls.text_box.TextBox")
                .demo(createTextBox(Icon.LOCATION_ON, "Location", true))
                .code(TutorialUtils.installExample(Css.TEXT_BOX, "TextBox"))

                .h3("PasswordBox", "TextBox")
                .text("The same as TextBox, but with a mask.")
                .legend("io.github.gleidsonmt.glad.controls.text_box.PasswordBox")

                .demo(createPasswordBox(Icon.VPN_KEY_FILLED,"PasswordBox", true))
                .code(TutorialUtils.installExample(Css.TEXT_BOX, "PasswordBox"))
                .demo(new SearchBox())


                .h3("TextArea", "TextInputControl")
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
        TextBoxBase textBoxBase= new TextBoxBase() {
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

    private PasswordBox createPasswordBox(Icon icon, String text, boolean action) {
        PasswordBox password = new PasswordBox(icon, text, action);
        password.setPromptText(text);
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
