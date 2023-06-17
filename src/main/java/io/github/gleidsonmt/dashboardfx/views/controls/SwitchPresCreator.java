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
import io.github.gleidsonmt.dashboardfx.core.controls.ToggleSwitch;
import io.github.gleidsonmt.dashboardfx.core.view.layout.creators.Author;
import io.github.gleidsonmt.dashboardfx.core.view.layout.creators.TutorialCreator;
import javafx.scene.control.CheckBox;
import javafx.scene.image.Image;

import java.util.List;

/**
 * @author Gleidson Neve    s da Silveira | gleidisonmt@gmail.com
 * Create on  21/01/2023
 */
public class SwitchPresCreator extends TutorialCreator {

    public SwitchPresCreator(Context context) {
        super(context);
        this    .title("ToggleSwitch")
                .text("""
                        Toggle Switch and we are going to be model it based on iOS 8 style toggle switch.
                                                                                                                            
                        """)
                .demonstration(
                        new ToggleSwitch(),
                        """
                                // Constructor
                                ToggleSwitch toggleSwitch = new ToggleSwitch("Check");
                                """,
                            """
                                <!-- Constructor -->
                                  <CheckBox text="Check"\s
                                        allowIndeterminate="true"
                                        selected="true"
                                        indeterminate="true"
                                  />
                            """)
                .title("Links")
                .title("Watch")
                .youTube("", context.getResource("style/img/transparent-logo.png"))
                .footer(
                        createDefaultAuthor(),
                        new Author("Almas", "https://github.com/AlmasB/FXTutorials/tree/master",
                                "https://www.youtube.com/watch?v=maX5ymmQixM")
                )
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
