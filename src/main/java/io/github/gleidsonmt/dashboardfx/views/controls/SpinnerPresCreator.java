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
import javafx.scene.Node;
import javafx.scene.control.Spinner;
import javafx.scene.control.SpinnerValueFactory;

/**
 * @author Gleidson Neves da Silveira | gleidisonmt@gmail.com
 * Create on  24/06/2023
 */
public class SpinnerPresCreator extends TutorialCreator {
    public SpinnerPresCreator(Context context) {
        super(context);
        this
                .title("Intro")
                .text("A single line text field that lets the user select a number or an object value from an ordered sequence. Spinners typically provide a pair of tiny arrow buttons for stepping through the elements of the sequence. The keyboard up/down arrow keys also cycle through the elements. The user may also be allowed to type a (legal) value directly into the spinner.")
                .demonstration(createSpinner(), """
                        // Constructor
                        Spinner<Integer> spin = new Spinner<>();
                        // Defining value Factory, white min, max and initial value
                        spin.setValueFactory(new SpinnerValueFactory.IntegerSpinnerValueFactory(1, 5, 10));
                        """)
                .title("Links")
                .footer(createDefaultAuthor("Spinner"))
                .build();
    }

    private Node createSpinner() {
        Spinner<Integer> spin = new Spinner<>();
        spin.setValueFactory(new SpinnerValueFactory.IntegerSpinnerValueFactory(1, 5, 2));
        return spin;
    }
}
