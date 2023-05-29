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
import javafx.scene.control.ComboBox;
import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeView;

import java.util.List;

/**
 * @author Gleidson Neves da Silveira | gleidisonmt@gmail.com
 * Create on  24/05/2023
 */
public class ComboBoxPresCreator extends TutorialCreator {
    public ComboBoxPresCreator(Context context) {
        super(context);
        this
                .title("ComboBox")
                .text("""
                        A combo box is a text box with a list box attached. This type of control enables users to select a predefined value in a list or type their own value in the text box portion of the control.
                        """)
                .demonstration(
                        List.of(
                                createComboBox(false),
                                createComboBox(false, true),
                                createComboBox(true)

                        ),
                                """
                                        // Constructor
                                        ComboBox<String> comboBox = new ComboBox<>();
                                        // Passing children
                                        comboBox.getItems().addAll(
                                             "First Item, Second Item", "First Item"
                                        );
                                        // Editable
                                        comboBox.setEditable(true);
                                        //Disable
                                        comboBox.setDisable(true);
                                        """,
                                """
                                         <!-- Building -->
                                        <ComboBox value="Root">
                                           <!-- Defining values -->
                                           <items>
                                              <FXCollections fx:factory="observableArrayList">
                                                 <String fx:value="First"/>
                                                 <String fx:value="Second"/>
                                                 <String fx:value="Third"/>
                                              </FXCollections>
                                           </items>
                                        </ComboBox>
                                        """
                )
                .title("Links")
                .footer(createDefaultControl())
                .build();
    }

    private ComboBox<String> createComboBox(boolean disable) {
        return createComboBox(disable, false);
    }
    private ComboBox<String> createComboBox(boolean disable, boolean editable) {
        ComboBox<String> comboBox = new ComboBox<>();
        comboBox.setDisable(disable);
        comboBox.setEditable(editable);
        comboBox.getItems().addAll(
                "First Item", "Second Item", "First Item"
        );
        comboBox.setValue("First Item");
        return comboBox;
    }


}
