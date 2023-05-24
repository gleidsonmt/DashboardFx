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
import javafx.scene.control.CheckBox;

import java.util.List;

/**
 * @author Gleidson Neve    s da Silveira | gleidisonmt@gmail.com
 * Create on  21/01/2023
 */
public class CheckBoxPresCreator extends TutorialCreator {

    public CheckBoxPresCreator(Context context) {
        super(context);
        this    .title("CheckBox")
                .text("""
                      A tri-state selection Control typically skinned as a box with a checkmark or tick mark when checked.
                        """)
                .demonstration(
                        List.of(
                            createCheckBox("Normal", ""),
                            createCheckBox("Indeterminate", "i"),
                            createCheckBox("Selected", "s")
                        ),
                        """
                                // Constructor
                                CheckBox checkBox = new CheckBox("Check");
                                // Sets the checkbox to state indeterminate  
                                checkBox.setIndeterminate(true);
                                // Sets the checkbox to use sub state after select
                                checkBox.setAllowIndeterminate(true);
                                // CheckBox selected state is activated
                                checkBox.setSelected(true);
                                """,
                            """
                                <!-- Constructor -->
                                  <CheckBox text="Check"\s
                                        allowIndeterminate="true"
                                        selected="true"
                                        indeterminate="true"
                                  />
                            """
                )
                .title("States")
                .text("""
                        •   checked: indeterminate == false, checked == true
                        •   unchecked: indeterminate == false, checked == false
                        •   undefined: indeterminate == true
                        """, "-fx-padding: 10px 20px 10px 20px;")
                .text("""
                        If a CheckBox is checked, then it is also by definition defined. When checked the CheckBox is typically rendered with a "check" or "tick" mark. A CheckBox is in this state if selected is true and indeterminate is false.
                        A CheckBox is unchecked if selected is false and indeterminate is false.
                                                
                        A CheckBox is undefined if indeterminate is true, regardless of the state of selected. A typical rendering would be with a minus or dash, to indicate an undefined or indeterminate state of the CheckBox. This is convenient for constructing tri-state checkbox based trees, for example, where undefined check boxes typically mean "inherit settings from the parent".
                                                
                        The allowIndeterminate variable, if true, allows the user to cycle through the undefined state. If false, the CheckBox is not in the indeterminate state, and the user is allowed to change only the checked state.
                        """)
                .title("Links")
                .footer(createDefaultControl())
                .build();
    }

    private CheckBox createCheckBox(String title, String type) {
        CheckBox checkBox = new CheckBox(title);
        if (type.equals("i")) {
            checkBox.setIndeterminate(true);
            checkBox.setAllowIndeterminate(true);
        } else if(type.equals("s")) {
            checkBox.setSelected(true);
        } else {

        }
        return checkBox;
    }
}
