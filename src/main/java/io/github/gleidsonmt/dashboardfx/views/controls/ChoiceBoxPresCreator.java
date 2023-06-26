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
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Node;
import javafx.scene.control.ChoiceBox;

/**
 * @author Gleidson Neves da Silveira | gleidisonmt@gmail.com
 * Create on  24/06/2023
 */
public class ChoiceBoxPresCreator extends TutorialCreator {
    public ChoiceBoxPresCreator(Context context) {
        super(context);
        this    .title("Intro")
                .text("The ChoiceBox is used for presenting the user with a relatively small set of predefined choices from which they may choose. The ChoiceBox, when \"showing\", will display to the user these choices and allow them to pick exactly one choice. When not showing, the current choice is displayed.")
                .demonstration(createChoice(new ChoiceBox<>()), """
                        // Constructor
                        ChoiceBox choiceBox = new ChoiceBox();
                        // Adding items                 
                        choiceBox.getItems().addAll("First", "Second", "Third");
                        // Predefine one item
                        choiceBox.setValue("First");
                        """, """
                        <ChoiceBox prefWidth="150.0" value="First">
                            <items>
                                <FXCollections fx:factory="observableArrayList">
                                    <String fx:value="First"/>
                                    <String fx:value="Second"/>
                                    <String fx:value="Third"/>
                                </FXCollections>
                            </items>
                        </ChoiceBox>
                        """)
                .title("Links")
                .footer(createDefaultAuthor("ChoiceBox"))
                .build();
    }

    private Node createChoice(ChoiceBox<String> choiceBox) {
        choiceBox.getItems().addAll("First", "Second", "Third");
        choiceBox.setValue("First");
        return choiceBox;
    }
}
