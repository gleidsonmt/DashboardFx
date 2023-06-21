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
import javafx.scene.control.ToggleButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.HBox;

import java.util.List;

/**
 * @author Gleidson Neve    s da Silveira | gleidisonmt@gmail.com
 * Create on  21/01/2023
 */
public class ToggleButtonPresCreator extends TutorialCreator {

    public ToggleButtonPresCreator(Context context) {
        super(context);
        this    .title("ToggleButton")
                .text("A ToggleButton is a specialized control which has the ability to be selected. Typically a ToggleButton is rendered similarly to a Button. However, they are two different types of Controls. A Button is a \"command\" button which invokes a function when clicked. A ToggleButton on the other hand is simply a control with a Boolean indicating whether it has been selected.")
                .demonstration(
                        List.of(
                                new ToggleButton("Toggle"),
                                createGroup()
                        ),
                        """
                            // Creating a single ToggleButton
                            ToggleButton on = new ToggleButton("Toggle");
                            // Creating a toggle group
                            HBox body = new HBox();
                            ToggleButton on = new ToggleButton("ON");
                            on.getStyleClass().add("toggle-left");
                            ToggleButton off = new ToggleButton("OFF");
                            off.getStyleClass().add("toggle-right");
                            ToggleGroup group = new ToggleGroup();
                            group.getToggles().addAll(on, off);
                            body.getChildren().addAll(on, off);
                            """
                )
                .title("ToggleGroup")
                .text("A class which contains a reference to all Toggles whose selected variables should be managed such that only a single Toggle within the ToggleGroup may be selected at any one time.")
                .title("ToggleSwitch")
                .text("""
                        Toggle Switch and we are going to be model it based on iOS 8 style toggle switch.
                        """)
                .demonstration(
                        new ToggleSwitch(),
                        """
                                // Constructor
                                ToggleSwitch toggleSwitch = new ToggleSwitch();
                                """)
                .title("Watch")
                .youTube("", context.getResource("style/img/transparent-logo.png"))
                .title("Links")
                .footer(
                        createDefaultAuthor("ToggleButton"),
                        new Author("Almas", "https://github.com/AlmasB/FXTutorials/tree/master",
                                "https://www.youtube.com/watch?v=maX5ymmQixM"),
                        createUserDefault()
                )
                .build();
    }

    private HBox createGroup() {
        HBox body = new HBox();
        ToggleButton on = new ToggleButton("ON");
        on.getStyleClass().add("toggle-left");
        ToggleButton off = new ToggleButton("OFF");
        off.getStyleClass().add("toggle-right");
        ToggleGroup group = new ToggleGroup();
        group.getToggles().addAll(on, off);
        body.getChildren().addAll(on, off);
        return body;
    }
}
