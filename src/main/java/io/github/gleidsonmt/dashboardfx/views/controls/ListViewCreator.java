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
import javafx.scene.control.ListView;

import java.util.List;

/**
 * @author Gleidson Neves da Silveira | gleidisonmt@gmail.com
 * Create on  22/01/2023
 */
public class ListViewCreator extends TutorialCreator {

    public ListViewCreator(Context context) {
        super(context);
        this    .title("ListView")
                .text("A ListView displays a horizontal or vertical list of items from which the user may select, or with which the user may interact. A ListView is able to have its generic type set to represent the type of data in the backing model. Doing this has the benefit of making various methods in the ListView, as well as the supporting classes (mentioned below), type-safe. In addition, making use of the generic type supports substantially simplified development of applications making use of ListView, as all modern IDEs are able to auto-complete far more successfully with the additional type information.")
                .demonstration(List.of(
                                createListView(),
                                createBorderList(),
                                createRectList()
                        ),
                        """
                                // Constructor
                                ListView<String> listView = new ListView<>();
                                // Setting items
                                listView.getItems().setAll(
                                        "First", "Second", "Third", "Fourth"
                                );
                                """,
                        """
                             <!-- Declaring -->
                             <ListView>
                                <!-- Populating views -->
                                 <items>
                                    <FXCollections fx:factory="observableArrayList">
                                        <!-- Passing the items -->
                                       <Label text="First"/>
                                       <Label text="Second"/>
                                       <Label text="Third"/>
                                    </FXCollections>
                                 </items>
                              </ListView>
                                """)
                .title("Populating a ListView")
                .text("A simple example of how to create and populate a ListView of names (Strings) is shown here: ")
                .code("""
                        ObservableList<String> names = FXCollections.observableArrayList(
                                  "Julia", "Ian", "Sue", "Matthew", "Hannah", "Stephan", "Denise");
                         ListView<String> listView = new ListView<String>(names);
                        """)
                .text("""
                            The elements of the ListView are contained within the items ObservableList. This ObservableList is automatically observed by the ListView, such that any changes that occur inside the ObservableList will be automatically shown in the ListView itself. If passing the ObservableList in to the ListView constructor is not feasible, the recommended approach for setting the items is to simply call:
                            """
                       , "-fx-padding: 10px 0px 10px 0px;"
                )
                .title("Links")
                .footer(createDefaultControl())

        ;
        build();
    }

    private ListView<String> createListView() {
        ListView<String> listView = new ListView<>();
        listView.getItems().addAll(
                "First", "Second", "Third", "Fourth"
        );
        listView.setPrefHeight(300);
        return listView;
    }

    private ListView<String> createBorderList() {
        ListView<String> listView = new ListView<>();
        listView.getItems().addAll(
                "First", "Second", "Third", "Fourth"
        );
        listView.setPrefHeight(300);
        listView.getStyleClass().add("border-list");
        return listView;
    }

    private ListView<String> createRectList() {
        ListView<String> listView = new ListView<>();
        listView.getItems().addAll(
                "First", "Second", "Third", "Fourth"
        );
        listView.setPrefHeight(300);
        listView.getStyleClass().add("rect-list");
        return listView;
    }
}
