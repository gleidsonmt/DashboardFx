package io.github.gleidsonmt.dashboardfx.presentation.core;

import io.github.gleidsonmt.dashboardfx.drawer.Drawer;
import io.github.gleidsonmt.dashboardfx.drawer.DrawerI;
import io.github.gleidsonmt.dashboardfx.presentation.internal.Tutorial;
import io.github.gleidsonmt.dashboardfx.utils.TutorialUtils;
import io.github.gleidsonmt.glad.base.Layout;
import io.github.gleidsonmt.glad.base.Root;
import io.github.gleidsonmt.glad.base.Module;
import io.github.gleidsonmt.glad.base.View;
import io.github.gleidsonmt.glad.controls.icon.Icon;
import io.github.gleidsonmt.glad.controls.icon.SVGIcon;
import io.github.gleidsonmt.glad.drawer.DrawerCell;
import io.github.gleidsonmt.glad.drawer.DrawerItem;
import io.github.gleidsonmt.glad.drawer.SimpleDrawer;
import io.github.gleidsonmt.glad.theme.Css;
import io.github.gleidsonmt.glad.theme.Font;
import io.github.gleidsonmt.glad.theme.ThemeProvider;
import javafx.beans.binding.Bindings;
import javafx.beans.property.ObjectProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.control.TitledPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.util.Callback;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.Arrays;
import java.util.function.Predicate;

/**
 * @author Gleidson Neves da Silveira | gleidisonmt@gmail.com
 * Create on  20/03/2025
 */
public class IntroductionPres extends CustomizablePresentation {

    public IntroductionPres() {
        super("Introduction");
    }

    @Override
    public Tutorial create() {
        try {
            return new Tutorial()
                    .h3("Introduction")
                    .text("""
                            I’ll tutor you around this ecosystem I built.
                            
                            The main idea here, it’s creating examples to show the power of JavaFX.
                            There’s a lot of techniques to do that, but it’s not a normal thing to see in JavaFX.
                            On a normal day, you need ways to get fast to get a system design, repeating tasks is the most boring thing daily.
                            This project expects to add some layers to production by using known patterns.
                            And I know there’s a curve to learn. But I hope to make it easier.
                            
                            As the most dashboards the drawer is on the left, with the logo and a search field
                            As suggested the search view is used to filter by modules. When you typed inside it.
                            
                            The modules is the view or a group of views.
                            If the it's a view is represented by a ToggleButton.
                            If it's a group of views is represented by a TilePane.
                            
                            When a view (ToggleButton) is clicked the content is updated to the content of this view.
                            
                            The modules are separated by sections, project, theme, example and extras.
                            All modules follow a top and dow overview.. so you can actually go deeper and learn more getting the sequence of the modules.
                            
                            The core section involves getting things by context and interactions by the root node.
                            
                            The theme section contains examples using default javafx + a custom theme.
                            The custom theme is provided by a class called ThemeProvider.
                            On the each view you can see how to install the custom css.
                            
                            The example section has examples and customizable controls and how to work with it.
                            
                            The extras section represents tips with javafx and additional resource.
                            
                            Now if you want a point of start..
                            I've been working and idealizing a skeleton with the minimal to create a project and start.
                            (I'm still working on it, all help is welcome.).
                            """)
                    .text("Try clone the project skeleton, and start by yourself with minimal settings.")
                    .node(TutorialUtils.createCardLink("Skeleton", new URI("https://github.com/gleidsonmt/dash-skeleton")))

                    .text("When cloned the project and run, you can see the same stage as the example bellow.")
                    .node(createExample())

                    .text("See more")
//                    .node(TutorialUtils.createLink("Drawer", "Drawer"))
                    .node(TutorialUtils.createLink("Interactivity", "Interactivity"))
                    ;
        } catch (URISyntaxException e) {
            throw new RuntimeException(e);
        }
    }

    private Node createExample() {
        return TutorialUtils.createAction(_ -> {

            class Main extends BorderPane implements Layout {

                public Main(Module... modules) {
                    SimpleDrawer drawer = new SimpleDrawer();
                    Predicate<Module> predicate = module -> module.getName().matches("view");
                    FilteredList<Module> filteredList = new FilteredList<>(FXCollections.observableArrayList(modules), predicate);
                    drawer.getDrawerItems().setAll(filteredList);

                    TitledPane titledPane = new TitledPane();
                    ListView<Module> sub = new ListView<>();
                    FilteredList<Module> subFiltered = new FilteredList<Module>(FXCollections.observableArrayList(Arrays.stream(modules).limit(2).toList()), _-> true);
                    subFiltered.setPredicate(predicate);
                    titledPane.setContent(sub);

                    drawer.setCellFactory(new Callback<>() {
                        @Override
                        public ListCell<Module> call(ListView<Module> param) {
                            return new ListCell<>() {
                                @Override
                                protected void updateItem(Module item, boolean empty) {
                                    super.updateItem(item, empty);
                                    if (item != null && !empty) {
                                        DrawerCell cell = new DrawerCell(item);
                                        setGraphic(cell);
                                        setText(null);
                                        setMouseTransparent(false);
                                    } else {
                                        setItem(null);
                                        setText(null);
                                    }
                                }
                            };
                        }
                    });

                    this.setLeft(drawer);
                    this.centerProperty().bind(Bindings.select(drawer.selectedProperty(), "content"));
                }

                @Override
                public ObjectProperty<Module> currentModuleProperty() {
                    return null;
                }

                @Override
                public Module getCurrentModule() {
                    return null;
                }

                @Override
                public void setCurrentModule(Module module) {

                }
//
            }

            // Root is the base of the scene, it's the point to get environment around.
            // The Main class is a layout, it's a container of other views.
            // The View class it's a module representation.
            Root root = new Root(new Main(
                    new View("Orders", new SVGIcon(Icon.ORDERS), new Text("Orders View")),
                    new View("Apps", new SVGIcon(Icon.APPS), new Text("Apps View")),
                    new View("Products", new SVGIcon(Icon.LOCAL_MALL), new Text("Products View"))
            ));
            Scene scene = new Scene(root, 800, 600);
            // Install the custom css styles, colors and typographic.
            // ThemeProvider is a class that install css, colors and typographic.
            ThemeProvider.install(scene, Css.DEFAULT, Css.LIST_VIEW);
            // Also can be include fonts.
            ThemeProvider.install(scene, Font.POPPINS);
            Stage stage = new Stage();
            stage.setScene(scene);
            stage.show();


        });
    }
}
