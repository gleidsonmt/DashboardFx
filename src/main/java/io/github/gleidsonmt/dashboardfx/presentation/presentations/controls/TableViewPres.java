package io.github.gleidsonmt.dashboardfx.presentation.presentations.controls;

import io.github.gleidsonmt.dashboardfx.model.Person;
import io.github.gleidsonmt.dashboardfx.presentation.core.CustomizablePresentation;
import io.github.gleidsonmt.dashboardfx.presentation.internal.Tutorial;
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
        return   new Tutorial()
                        .h3("Table View")
                        .text("""
                                The TableView control is designed to visualize an unlimited number of rows of data, broken out into columns.
                                A TableView is therefore very similar to the ListView control, with the addition of support for columns.""")
                        .legend("javafx.scene.control.TableView")
                        .h5("Demo", "Table View")
                        .demo(createDemo())

                        .h3("Creating a Table View", "Table View")


                        .text("""
                                Creating a TableView is a multi-step process, and also depends on the underlying data model needing to be represented. 
                                For this example we'll use an ObservableList<Person>, as it is the simplest way of showing data in a TableView. 
                                The Person class will consist of a first name and last name properties. That is:
                                """)

                        .code("""
                                public class Person {
                                    private StringProperty firstName;
                                    public void setFirstName(String value) { firstNameProperty().set(value); }
                                    public String getFirstName() { return firstNameProperty().get(); }
                                    public StringProperty firstNameProperty() {
                                        if (firstName == null) firstName = new SimpleStringProperty(this, "firstName");
                                        return firstName;
                                    }
                                
                                    private StringProperty lastName;
                                    public void setLastName(String value) { lastNameProperty().set(value); }
                                    public String getLastName() { return lastNameProperty().get(); }
                                    public StringProperty lastNameProperty() {
                                        if (lastName == null) lastName = new SimpleStringProperty(this, "lastName");
                                        return lastName;
                                    }
                                
                                    public Person(String firstName, String lastName) {
                                        setFirstName(firstName);
                                        setLastName(lastName);
                                    }
                                }
                                """)

                        .text("The data we will use for this example is:")

                        .code("""
                                List<Person> members = List.of(
                                    new Person("William", "Reed"),
                                    new Person("James", "Michaelson"),
                                    new Person("Julius", "Dean"));""")

                        .text("""
                                Firstly, we need to create a data model. As mentioned, for this example, we'll be using an ObservableList<Person>:""")
                        .code("""
                                ObservableList<Person> teamMembers = FXCollections.observableArrayList(members);""")
                        .text("""
                                Then we create a TableView instance:""")
                        .code("""
                                TableView<Person> table = new TableView<>();
                                table.setItems(teamMembers);""")
                        .text("""
                                With the items set as such, TableView will automatically update whenever the teamMembers list changes. 
                                If the items list is available before the TableView is instantiated, it is possible to pass it directly into the constructor:""")
                        .code("""
                                TableView<Person> table = new TableView<>(teamMembers);""")
                        .text("""
                                At this point we now have a TableView hooked up to observe the teamMembers observableList. 
                                The missing ingredient now is the means of splitting out the data contained within the model and representing it in one or more TableColumn instances. 
                                To create a two-column TableView to show the firstName and lastName properties, we extend the last code sample as follows:""")
                        .code("""
                                TableColumn<Person, String> firstNameCol = new TableColumn<>("First Name");
                                firstNameCol.setCellValueFactory(new PropertyValueFactory<>("firstName"));
                                TableColumn<Person, String> lastNameCol = new TableColumn<>("Last Name");
                                lastNameCol.setCellValueFactory(new PropertyValueFactory<>("lastName"));
                                table.getColumns().setAll(firstNameCol, lastNameCol);""")

                        .text("""
                                With the code shown above we have fully defined the minimum properties required to create a TableView instance. 
                                Running this code will result in the TableView being shown with two columns for firstName and lastName. 
                                Any other properties of the Person class will not be shown, as no TableColumns are defined.""");
    }

    private Node createDemo() {
        List<Person> members = List.of(
                new Person("William", "Reed"),
                new Person("James", "Michaelson"),
                new Person("Julius", "Dean"));

        ObservableList<Person> teamMembers = FXCollections.observableArrayList(members);
        TableView<Person> table = new TableView<>();
        table.setItems(teamMembers);

        TableColumn<Person, String> firstNameCol = new TableColumn<>("First Name");
        firstNameCol.setCellValueFactory(new PropertyValueFactory<>("firstName"));
        TableColumn<Person, String> lastNameCol = new TableColumn<>("Last Name");
        lastNameCol.setCellValueFactory(new PropertyValueFactory<>("lastName"));

        table.getColumns().setAll(firstNameCol, lastNameCol);
        return table;
    }
}

