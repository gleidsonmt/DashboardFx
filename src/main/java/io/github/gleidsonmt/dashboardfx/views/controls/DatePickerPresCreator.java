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
import javafx.scene.control.DatePicker;

/**
 * @author Gleidson Neves da Silveira | gleidisonmt@gmail.com
 * Create on  27/06/2023
 */
public class DatePickerPresCreator extends TutorialCreator {
    public DatePickerPresCreator(Context context) {
        super(context);

        this
                .title("Intro")
                .text("The DatePicker control allows the user to enter a date as text or to select a date from a calendar popup. The calendar is based on either the standard ISO-8601 chronology or any of the other chronology classes defined in the java.time.chrono package.")
                .demonstration(new DatePicker(), """
                        // Constructor
                        DatePicker datePicker = new DatePicker();
                        // Get value
                        datePicker.getValue();
                        """, """
                        <!-- Building -->
                        <DatePicker/>
                        """)
                .title("Links")
                .footer(createDefaultAuthor("DatePicker"))
                .build();
    }
}
