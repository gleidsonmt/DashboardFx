package io.github.gleidsonmt.dashboardfx.presentation.presentations.controls;

import io.github.gleidsonmt.dashboardfx.presentation.CustomizablePresentation;
import io.github.gleidsonmt.dashboardfx.presentation.internal.Tutorial;
import io.github.gleidsonmt.dashboardfx.utils.TutorialUtils;
import io.github.gleidsonmt.glad.theme.Css;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

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
                ;
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
}
