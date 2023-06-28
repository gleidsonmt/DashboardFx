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
import javafx.scene.control.ColorPicker;

/**
 * @author Gleidson Neves da Silveira | gleidisonmt@gmail.com
 * Create on  27/06/2023
 */
public class ColorPickerPresCreator extends TutorialCreator {
    public ColorPickerPresCreator(Context context) {
        super(context);

        this
                .title("Intro")
                .text("ColorPicker control allows the user to select a color from either a standard palette of colors with a simple one click selection OR define their own custom color.")
                .demonstration(new ColorPicker(), """
                        // Constructor
                        ColorPicker colorPicker = new ColorPicker();
                        // Get value
                        colorPicker.getValue();
                        """, """
                        <!-- Building -->
                        <ColorPicker/>
                        """)
                .title("Links")
                .footer(createDefaultAuthor("ColorPicker"))
                .build();
    }
}
