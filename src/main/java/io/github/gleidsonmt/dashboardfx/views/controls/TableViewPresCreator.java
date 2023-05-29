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

import io.github.gleidsonmt.dashboardfx.core.model.Person;
import io.github.gleidsonmt.dashboardfx.core.Context;
import io.github.gleidsonmt.dashboardfx.core.view.layout.creators.TutorialCreator;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.util.Callback;

import java.util.List;

/**
 * @author Gleidson Neves da Silveira | gleidisonmt@gmail.com
 * Create on  20/05/2023
 */
public class TableViewPresCreator extends TutorialCreator {
    public TableViewPresCreator(Context context) {
        super(context);
        this
                .title("Table View")
                .text("The TableView control is designed to visualize an unlimited number of rows of data, broken out into columns. A TableView is therefore very similar to the ListView control, with the addition of support for columns. For an example on how to create a TableView, refer to the 'Creating a TableView' control section below.")
                .demonstration(
                        List.of(
//                            createDefaultTable(TableView.UNCONSTRAINED_RESIZE_POLICY),
                            createDefaultTable(TableView.CONSTRAINED_RESIZE_POLICY)
                        ),
                        """
                                // Creating new table object
                                TableView<Person> tableView = new TableView<>();
                                // Creating a first column
                                TableColumn<Person, String> first = new TableColumn<>("First");
                                // Creating a second column
                                TableColumn<Person, String> second = new TableColumn<>("Second");
                                // Settings columns in table
                                tableView.getColumns().setAll(first, second);
                                // Defining where columns can get his values
                                first.setCellValueFactory(new PropertyValueFactory<>("name"));
                                second.setCellValueFactory(new PropertyValueFactory<>("sex"));
                                // Setting items in table
                                tableView.getItems().setAll(
                                   new Person("Jacob", "Smith", "jacob.smith@example.com"),
                                   new Person("Isabella", "Johnson", "isabella.johnson@example.com"),
                                   new Person("Ethan", "Williams", "ethan.williams@example.com"),
                                   new Person("Emma", "Jones", "emma.jones@example.com"),
                                   new Person("Michael", "Brown", "michael.brown@example.com")
                                );
                                """,
                        """
                                 <!-- Creating object table inside fxml file -->
                                 <TableView>
                                    <!-- Defining columns -->
                                     <columns>
                                        <!-- First Column -->
                                        <TableColumn text="First Name">
                                            <!-- Defining factory -->
                                           <cellValueFactory>
                                            <PropertyValueFactory property="firstName" />
                                           </cellValueFactory>
                                        </TableColumn>
                                        <TableColumn text="Last Name">
                                           <cellValueFactory><PropertyValueFactory property="lastName" />
                                           </cellValueFactory>
                                        </TableColumn>
                                        <TableColumn text="Email Address">
                                              <cellValueFactory><PropertyValueFactory property="email" />
                                              </cellValueFactory>
                                        </TableColumn>
                                     </columns>
                                     <!-- Defining items -->
                                     <items>
                                        <FXCollections fx:factory="observableArrayList">
                                           <Person firstName="Jacob" lastName="Smith"
                                                   email="jacob.smith@example.com"/>
                                           <Person firstName="Isabella" lastName="Johnson"
                                                   email="isabella.johnson@example.com"/>
                                           <Person firstName="Ethan" lastName="Williams"
                                                   email="ethan.williams@example.com"/>
                                           <Person firstName="Emma" lastName="Jones"
                                                   email="emma.jones@example.com"/>
                                           <Person firstName="Michael" lastName="Brown"
                                                   email="michael.brown@example.com"/>
                                        </FXCollections>
                                     </items>
                                  </TableView>
                                """
                )
                .title("Links")
                .footer(createDefaultControl())
                .build();
    }

    private TableView<Person> createDefaultTable(Callback<TableView.ResizeFeatures, Boolean> re) {
        TableColumn<Person, String> first = new TableColumn<>("First Name");
        TableColumn<Person, String> second = new TableColumn<>("Last Name");
        TableColumn<Person, String> third = new TableColumn<>("Email");
        TableView<Person> tableView = new TableView<>();
        tableView.getColumns().setAll(first, second, third);
        first.setCellValueFactory(new PropertyValueFactory<>("firstName"));
        second.setCellValueFactory(new PropertyValueFactory<>("lastName"));
        third.setCellValueFactory(new PropertyValueFactory<>("email"));
//        tableView.setMinWidth(400);
        tableView.setPrefHeight(300);
        tableView.setColumnResizePolicy(re);
        tableView.getItems().setAll(
                new Person("Jacob", "Smith", "jacob.smith@example.com"),
                new Person("Isabella", "Johnson", "isabella.johnson@example.com"),
                new Person("Ethan", "Williams", "ethan.williams@example.com"),
                new Person("Emma", "Jones", "emma.jones@example.com"),
                new Person("Michael", "Brown", "michael.brown@example.com")
        );

        return tableView;
    }

}
