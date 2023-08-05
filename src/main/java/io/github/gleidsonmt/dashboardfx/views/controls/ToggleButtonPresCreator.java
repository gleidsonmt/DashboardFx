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
import javafx.geometry.HPos;
import javafx.geometry.VPos;
import javafx.scene.Node;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ContentDisplay;
import javafx.scene.control.ToggleButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;

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
                .demonstration(
                        List.of(
                                createCustom(),
                                createStorage()
                        )
                )
                .title("ToggleSwitch")
                .text("""
                        Toggle Switch and we are going to be model it based on iOS 8 style toggle switch.
                        """)
//                .textBlock("See", "radio_button")
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


    private ToggleGroup groupCustom = new ToggleGroup();

    private Node createCustom() {
        VBox box = new VBox();
        box.setSpacing(20);

        ToggleButton toggleOne = createToggle(
                "Hobby",
                "8GB / 4 CPUs * 160 GB SSD disk",
                "$40",
                "/mo");

        ToggleButton toggleTwo = createToggle(
                "Startup",
                "12GB / 6 CPUs · 256 GB SSD disk",
                "$80",
                "/mo");


        ToggleButton toggleThree = createToggle(
                "Business",
                "16GB / 8 CPUs · 512 GB SSD disk",
                "$160",
                "/mo");

        box.getChildren().setAll(toggleOne, toggleTwo, toggleThree);
        return box;
    }

    private Node createStorage() {
        HBox body = new HBox();
        VBox box = new VBox();

        Text title = new Text("Storage");
        title.getStyleClass().addAll("h5", "text-bold");
        Text legend = new Text("Transfer your balance to your bank account");
        legend.getStyleClass().addAll("h5");
        ToggleGroup group = new ToggleGroup();

        ToggleButton four = new ToggleButton("4 GB");
        ToggleButton eight = new ToggleButton("8 GB");
        ToggleButton sixteen = new ToggleButton("16 GB");
        ToggleButton thirtyTwo = new ToggleButton("32 GB");
        ToggleButton sixtyFour = new ToggleButton("64 GB");

        body.setSpacing(20);

        group.getToggles().setAll(four, eight, sixteen, thirtyTwo,  sixtyFour);
        body.getChildren().setAll(four, eight, sixteen, thirtyTwo, sixtyFour);
        box.getChildren().addAll(title, body);

        return box;
    }

    private ToggleButton createToggle(String text, String legend, String price, String legendt) {
        ToggleButton toggle = new ToggleButton(text);
        toggle.getStyleClass().add("inner-toggle");
        toggle.setContentDisplay(ContentDisplay.GRAPHIC_ONLY);
        GridPane grid = new GridPane();

        groupCustom.getToggles().add(toggle);

        Text one = new Text(text);
        Text two = new Text(legend);
        Text three = new Text(price);
        Text four = new Text(legendt);

        toggle.setGraphic(grid);

        grid.getChildren().addAll(one, two, three, four);

        GridPane.setConstraints(one, 0,0,1,1, HPos.LEFT, VPos.CENTER, Priority.ALWAYS, Priority.ALWAYS);
        GridPane.setConstraints(two, 0,1,1,1, HPos.LEFT, VPos.CENTER, Priority.ALWAYS, Priority.ALWAYS);
        GridPane.setConstraints(three, 1,0,1,1, HPos.RIGHT, VPos.CENTER, Priority.ALWAYS, Priority.ALWAYS);
        GridPane.setConstraints(four, 1,1,1,1, HPos.RIGHT, VPos.CENTER, Priority.ALWAYS, Priority.ALWAYS);

        return toggle;
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
