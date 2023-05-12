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
import javafx.scene.control.TextField;

public class TextFieldPresCreator extends TutorialCreator {

    public TextFieldPresCreator(Context context) {
        super(context);
        this    .title("TextField")
                .text("""
                        Text input component that allows a user to enter a single line of unformatted text. 
                        Unlike in previous releases of JavaFX, support for multi-line input is not available as part of the TextField control, 
                        however this is the sole-purpose of the TextArea control.
                        """)
                .multCode(
                        new TextField(),
                        """
                                TextField field = new TextField();
                                """, ""
                )
                .build();
    }
}
