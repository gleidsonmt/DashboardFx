package io.github.gleidsonmt.dashboardfx.presentation.presentations.controls;

import io.github.gleidsonmt.dashboardfx.model.Person;
import io.github.gleidsonmt.dashboardfx.presentation.core.CustomizablePresentation;
import io.github.gleidsonmt.dashboardfx.presentation.internal.Tutorial;
import io.github.gleidsonmt.dashboardfx.utils.TutorialUtils;
import io.github.gleidsonmt.glad.theme.Css;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Node;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.StackPane;

import java.util.List;

/**
 * @author Gleidson Neves da Silveira | gleidisonmt@gmail.com
 * Create on  17/03/2025
 */
@SuppressWarnings("unchecked")
public class TableViewPres extends CustomizablePresentation {

    public TableViewPres() {
        super("TableView");
    }

    public Tutorial create() {
        return new Tutorial()
                .h3("TableView")
                .text("""
                        The TableView control is designed to visualize an unlimited number of rows of data, broken out into columns.
                        A TableView is therefore very similar to the ListView control, with the addition of support for columns.""")
                .legend("javafx.scene.control.TableView")
                .demo(createDemo())
                .h4("Install", "TableView")
                .code(TutorialUtils.installExample(Css.TABLE_VIEW) + """
                       
                        // Creating the data
                        List<Person> members = List.of(
                                new Person("Gleidson", "Neves da Silveira"),
                                new Person("Daniel", "Neves da Silveira"),
                                new Person("Deusdete", "José da Silveira"),
                                new Person("Ioneide", "Neves da Silva"));
                
                        // Setting the items
                        TableView<Person> table = new TableView<>();
                        table.setItems(FXCollections.observableArrayList(members));
                
                        // Creating columns
                        TableColumn<Person, String> firstNameCol = new TableColumn<>("First Name");
                        TableColumn<Person, String> lastNameCol = new TableColumn<>("Last Name");
                        // Setting the cell value factory 
                        firstNameCol.setCellValueFactory(new PropertyValueFactory<>("firstName"));
                        lastNameCol.setCellValueFactory(new PropertyValueFactory<>("lastName"));
                        // Add the columns to the table
                        table.getColumns().setAll(firstNameCol, lastNameCol);
                        """)

                ;
    }

    private Node createDemo() {
        List<Person> members = List.of(
                new Person("Gleidson", "Neves da Silveira"),
                new Person("Daniel", "Neves da Silveira"),
                new Person("Deusdete", "José da Silveira"),
                new Person("Ioneide", "Neves da Silva"));

        TableView<Person> table = new TableView<>();
        table.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY_ALL_COLUMNS);
        table.setItems(FXCollections.observableArrayList(members));

        TableColumn<Person, String> firstNameCol = new TableColumn<>("First Name");
        firstNameCol.setCellValueFactory(new PropertyValueFactory<>("firstName"));
        TableColumn<Person, String> lastNameCol = new TableColumn<>("Last Name");
        lastNameCol.setCellValueFactory(new PropertyValueFactory<>("lastName"));

        table.getColumns().setAll(firstNameCol, lastNameCol);
        return table;
    }
}

