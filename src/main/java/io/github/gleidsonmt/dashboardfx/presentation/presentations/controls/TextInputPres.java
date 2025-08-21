package io.github.gleidsonmt.dashboardfx.presentation.presentations.controls;

import io.github.gleidsonmt.dashboardfx.presentation.core.CustomizablePresentation;
import io.github.gleidsonmt.dashboardfx.presentation.internal.Tutorial;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.StackPane;

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
                .h3("Text Input")
                .text("Abstract base class for text input controls.")

                .h3("Text Field", "Text Input")
                .text("""
                        Text input component that allows a user to enter a single line of unformatted text.""")
                .legend("javafx.scene.control.TextField")
                .demo(
                        new TextField("Placeholder")
                )

                .h3("Password", "Text Field")
                .text("Text field that masks entered characters.")
                .legend("javafx.scene.control.PasswordField")
                .demo(
                        createPassword()
                );
    }

    private PasswordField createPassword() {
        PasswordField password = new PasswordField();
        password.setPromptText("Placeholder");
        return password;
    }
}
