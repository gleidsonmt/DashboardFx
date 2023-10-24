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
import io.github.gleidsonmt.dashboardfx.model.Person;
import io.github.gleidsonmt.dashboardfx.core.view.layout.creators.TutorialCreator;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TreeItemPropertyValueFactory;
import javafx.util.Callback;

/**
 * @author Gleidson Neves da Silveira | gleidisonmt@gmail.com
 * Create on  24/06/2023
 */
@SuppressWarnings("ALL")
public class TreeTableViewPresCreator extends TutorialCreator {
    public TreeTableViewPresCreator(Context context) {
        super(context);
        this
                .title("Intro")
                .text("The TreeTableView control is designed to visualize an unlimited number of rows of data, broken out into columns. The TreeTableView control is conceptually very similar to the TreeView and TableView controls, and as you read on you'll come to see the APIs are largely the same. However, to give a high-level overview, you'll note that the TreeTableView uses the same TreeItem API as TreeView, and that you therefore are required to simply set the root node in the TreeTableView")
                .demonstration(createTreeTableView(), """
                       // Constructor
                       TreeTableView<Person> treeTableView = new TreeTableView<>();
                       // Table Columns                                                                                                         \s
                       TreeTableColumn<Person, String> first = new TreeTableColumn<>("First Name");
                       TreeTableColumn<Person, String> second = new TreeTableColumn<>("Last Name");
                       TreeTableColumn<Person, String> third = new TreeTableColumn<>("Email");
                       // Set table columns in table
                       treeTableView.getColumns().addAll(first, second, third);
                       // Creatte a root
                       TreeItem<Person> root = new TreeItem<>(
                               new Person("Gleidson",
                                       "Neves da Silveira",
                                       "gleidisonmt@gmail.com"));
                       // Add in root all children
                       root.getChildren().addAll(
                               new TreeItem<>(new Person("Jacob", "Smith", "jacob.smith@example.com")),
                               new TreeItem<>(new Person("Isabella", "Johnson", "isabella.johnson@example.com")),
                               new TreeItem<>(new Person("Ethan", "Williams", "ethan.williams@example.com")),
                               new TreeItem<>(new Person("Michael", "Brown", "michael.brown@example.com"))
                       );
                       // Set root in table
                       treeTableView.setRoot(root);
                       // Defining value factory for table columns
                       first.setCellValueFactory(new TreeItemPropertyValueFactory<>("firstName"));
                       second.setCellValueFactory(new TreeItemPropertyValueFactory<>("lastName"));
                       third.setCellValueFactory(new TreeItemPropertyValueFactory<>("email"));
                        """, """
                        <TreeTableView>
                         <!-- Defining columns -->
                         <columns>
                             <!-- First Column -->
                             <TreeTableColumn text="First Name">
                                 <!-- Defining factory -->
                                 <cellValueFactory>
                                     <TreeItemPropertyValueFactory property="firstName"/>
                                 </cellValueFactory>
                             </TreeTableColumn>
                             <TreeTableColumn text="Last Name">
                                 <cellValueFactory>
                                     <TreeItemPropertyValueFactory property="lastName"/>
                                 </cellValueFactory>
                             </TreeTableColumn>
                             <TreeTableColumn text="Email Address">
                                 <cellValueFactory>
                                     <TreeItemPropertyValueFactory property="email"/>
                                 </cellValueFactory>
                             </TreeTableColumn>
                         </columns>
                         <!-- Defining items -->
                         <TreeItem>
                             <value>
                                 <Person firstName="Gleidson" lastName="Neves da Silveira"
                                         email="gleidisonmt@gmail.com"/>
                             </value>
                             <children>
                                 <TreeItem>
                                     <value>
                                         <Person firstName="Jacob" lastName="Smith"
                                                 email="jacob.smith@example.com"/>
                                     </value>
                                 </TreeItem>
                                 <TreeItem>
                                     <value>
                                         <Person firstName="Isabella" lastName="Johnson"
                                                 email="isabella.johnson@example.com"/>
                                     </value>
                                 </TreeItem>
                                 <TreeItem>
                                     <value>
                                         <Person firstName="Ethan" lastName="Williams"
                                                 email="ethan.williams@example.com"/>
                                     </value>
                                 </TreeItem>
                                 <TreeItem>
                                     <value>
                                         <Person firstName="Emma" lastName="Jones"
                                                 email="emma.jones@example.com"/>
                                     </value>
                                 </TreeItem>
                                 <TreeItem>
                                     <value>
                                         <Person firstName="Michael" lastName="Brown"
                                                 email="michael.brown@example.com"/>
                                     </value>
                                 </TreeItem>
                             </children>
                         </TreeItem>
                        </TreeTableView>
                        """)
                .title("Links")
                .footer(createDefaultAuthor("TreeTableView"))
                .build();
    }

    private Node createTreeTableView() {
        TreeTableView<Person> treeTableView = new TreeTableView<>();

        TreeTableColumn<Person, String> first = new TreeTableColumn<>("First Name");
        TreeTableColumn<Person, String> second = new TreeTableColumn<>("Last Name");
        TreeTableColumn<Person, String> third = new TreeTableColumn<>("Email");

        treeTableView.getColumns().addAll(first, second, third);

        TreeItem<Person> root = new TreeItem<>(
                new Person("Gleidson",
                        "Neves da Silveira",
                        "gleidisonmt@gmail.com"));

        root.getChildren().addAll(
                new TreeItem<>(new Person("Jacob", "Smith", "jacob.smith@example.com")),
                new TreeItem<>(new Person("Isabella", "Johnson", "isabella.johnson@example.com")),
                new TreeItem<>(new Person("Ethan", "Williams", "ethan.williams@example.com")),
                new TreeItem<>(new Person("Michael", "Brown", "michael.brown@example.com"))
        );

        treeTableView.setRoot(root);
        first.setCellValueFactory(new TreeItemPropertyValueFactory<>("firstName"));
        second.setCellValueFactory(new TreeItemPropertyValueFactory<>("lastName"));
        third.setCellValueFactory(new TreeItemPropertyValueFactory<>("email"));

        treeTableView.setPrefHeight(300);

        return treeTableView;
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
