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
import io.github.gleidsonmt.dashboardfx.core.view.layout.creators.Author;
import io.github.gleidsonmt.dashboardfx.core.view.layout.creators.TutorialCreator;
import javafx.scene.control.ColorPicker;
import javafx.scene.web.HTMLEditor;

/**
 * @author Gleidson Neves da Silveira | gleidisonmt@gmail.com
 * Create on  27/06/2023
 */
public class HtmlEditorViewPresCreator extends TutorialCreator {
    public HtmlEditorViewPresCreator(Context context) {
        super(context);
        this
                .title("Intro")
                .text("A control that allows for users to edit text, and apply styling to this text. The underlying data model is HTML, although this is not shown visually to the end-user.")
                .demonstration(createHtmlEditor(), """
                        // Constructor
                        HTMLEditor htmlEditor = new HTMLEditor();
                        """)
                .title("Links")
                .footer(
                        new Author("OpenJFX",
                                "https://github.com/openjfx/openjfx.github.io",
                                "https://openjfx.io/javadoc/17/javafx.web/javafx/scene/web/HTMLEditor.html")
                )
                .build();

    }

    private HTMLEditor createHtmlEditor() {
        HTMLEditor htmlEditor = new HTMLEditor();
        htmlEditor.setPrefSize(650, 300);
        return htmlEditor;
    }
}
