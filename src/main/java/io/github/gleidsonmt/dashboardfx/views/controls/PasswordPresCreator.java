/*
 *
 *    Copyright (C) Gleidson Neves da Silveira
 *
 *    This program is free software: you can redistribute it and/or modify
 *    it under the terms of the GNU General Public License as published by
 *    the Free Software Foundation, either version 3 of the License, or
 *   (at your option) any later version.
 *
 *    This program is distributed in the hope that it will be useful,
 *    but WITHOUT ANY WARRANTY; without even the implied warranty of
 *    MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *    GNU General Public License for more details.
 *
 *     You should have received a copy of the GNU General Public License
 *     along with this program.  If not, see <http://www.gnu.org/licenses/>.
 *
 */

package io.github.gleidsonmt.dashboardfx.views.controls;

import io.github.gleidsonmt.dashboardfx.core.Context;
import io.github.gleidsonmt.dashboardfx.core.view.layout.creators.TutorialCreator;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

/**
 * @author Gleidson Neves da Silveira | gleidisonmt@gmail.com
 * Create on  22/01/2023
 */
public class PasswordPresCreator extends TutorialCreator {

    public PasswordPresCreator(Context context) {
        super(context);
        this    .title("PasswordField")
                .text("""
                        Text field that masks entered characters.
                        """)
                .demonstration(
                        createDefault("PasswordField"),
                        """
                                // Constructor
                                PasswordField label = new PasswordField();
                                """,
                                """
                                <!-- Building -->
                                <PasswordField />
                                """
                )
                .title("Links")
                .footer(createDefaultControl())
                .build();
    }
    private PasswordField createDefault(String prompt) {
        PasswordField textField = new PasswordField();
        textField.setPromptText(prompt);
        return textField;
    }

}
