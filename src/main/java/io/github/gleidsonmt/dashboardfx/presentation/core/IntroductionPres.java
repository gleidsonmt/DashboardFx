package io.github.gleidsonmt.dashboardfx.presentation.core;

import io.github.gleidsonmt.dashboardfx.LibrariesTools;
import io.github.gleidsonmt.dashboardfx.presentation.CustomizablePresentation;
import io.github.gleidsonmt.dashboardfx.presentation.internal.Tutorial;
import io.github.gleidsonmt.dashboardfx.utils.Assets;
import io.github.gleidsonmt.dashboardfx.utils.TutorialUtils;
import io.github.gleidsonmt.glad.base.*;
import io.github.gleidsonmt.glad.base.Module;
import io.github.gleidsonmt.glad.base.drawer.Drawer;
import io.github.gleidsonmt.glad.controls.icon.Icon;
import io.github.gleidsonmt.glad.controls.icon.SVGIcon;
import io.github.gleidsonmt.glad.theme.Css;
import io.github.gleidsonmt.glad.theme.Font;
import io.github.gleidsonmt.glad.theme.ThemeProvider;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.ContentDisplay;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Region;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.logging.Logger;

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
                    .overview()
                    .h3("Introduction")
                    .separator()
                    .text("""
                            I’ll tutor you around this ecosystem I built.
                            
                            The main idea here, it’s creating examples to show the power of JavaFX.
                            There’s a lot of techniques to do that, but it’s not a normal thing to see in JavaFX.
                            On a normal day, you need ways to get fast to get a system design, repeating tasks is the most boring thing daily.
                            This project expects to add some layers to production by using known patterns.
                            And I know there’s a curve to learn. But I hope to make it easier.
                            
                            """)
                    .separator()
                    .h3("Layout", "Introduction")
                    .text("""
                            As the most dashboards the drawer is on the left, with the logo and a search field
                            As suggested the search view is used to filter by modules. When you typed inside it.
                            """)
                    .image(Assets.getImage("part3.png"))
                    .separator()
                    .h3("Modules", "Introduction")
                    .text("""
                            The modules is the view or a group of views.
                            If the it's a view is represented by a ToggleButton.
                            If it's a group of views is represented by a TilePane.
                            
                            When a view (ToggleButton) is clicked the content is updated to the content of this view.
                            The modules are separated by sections, project, theme, example and extras.
                            All modules follow a top and dow overview.. so you can actually go deeper and learn more getting the sequence of the modules.
                            """)

                    .h3("Core", "Introduction")
                    .text("""
                            The core section involves getting things by context and interactions by the root node.
                            """)
                    .separator()
                    .h3("Theme", "Introduction")
                    .text("""
                            The theme section contains examples using default javafx + a custom theme.
                            The custom theme is provided by a class called ThemeProvider.
                            On the each view you can see how to install the custom css.
                            """)
                    .separator()
                    .h3("Example", "Introduction")
                    .text("""
                            The example section has examples and customizable controls and how to work with it.
                            """)
                    .separator()
                    .h3("Extras", "Introduction")
                    .text("""
                            The extras section represents tips with javafx and additional resource.
                            """)
                    .separator()
                    .text("""
                            Now if you want a point of start..
                            I've been working and idealizing a skeleton with the minimal to create a project and start.
                            (I'm still working on it, all help is welcome.).
                            """)

                    .h3("Starting")
                    .h3("Layout", "Starting")
                    .code("""
                            class MyCustomLayout extends BorderPane implements Layout {
                            
                                 protected final ObjectProperty<Module> currentModule = new SimpleObjectProperty<>();
                            
                                 CustomLayout() {
                                     bind();
                                 }
                            
                                 private void bind() {
                                     this.currentModule.addListener((_, oldValue, newValue) -> {
                                         Logger.getGlobal().info("You switched between [" + oldValue + "] to [" + newValue + "]");
                                         setCenter(((View) newValue).getContent());
                                     });
                                 }
                            
                                 @Override
                                 public Region getDrawer() {
                                     return (Region) getLeft();
                                 }
                            
                                 @Override
                                 public ObjectProperty<Module> currentModuleProperty() {
                                     return currentModule;
                                 }
                             }
                            """)
                    .h3("Drawer", "Starting")

                    .code("""
                            class SideNav extends Drawer {
                            
                                public SideNav() {
                                    super(
                                            new View("Orders", new SVGIcon(Icon.ORDERS), new Text("Orders View")),
                                            new View("Apps", new SVGIcon(Icon.APPS), new Text("Apps View")),
                                            new View("Products", new SVGIcon(Icon.LOCAL_MALL), new Text("Products View")),
                                            new ModuleView("View", new View("View 2"), new View("View 3"))
                                    );
                                    setHeader(createDrawerHeader());
                                }
                            
                                public Node createDrawerHeader() {
                                    GridPane drawerHeader = new GridPane();
                                    drawerHeader.setPadding(new Insets(0, 0, 20, 0));
                                    Label logo = new Label("Drawer, CO");
                                    logo.getStyleClass().addAll("h3", "font-instagram", "bold");
                                    SVGIcon icon = new SVGIcon(Icon.HUB);
                                    Label iconContainer = new Label();
                                    iconContainer.getStyleClass().addAll("rounded", "border-2", "border-light-gray", "padding-5");
                            
                                    iconContainer.setContentDisplay(ContentDisplay.GRAPHIC_ONLY);
                                    iconContainer.setGraphic(icon);
                                    logo.setGraphic(iconContainer);
                                    drawerHeader.add(logo, 0, 0);
                                    return drawerHeader;
                                }
                            }
                            """)

                    .h3("Main", "Starting")
                    .code("""
                            public class App extends Application {
                            
                                @Override
                                public void start(Stage stage) throws Exception {
                            
                                // Root is the base of the scene, it's the point to get environment around.
                                // The Main class is a layout, it's a container of other views.
                                // The View class it's a module representation.
                                Root root = new Root(new Main());
                                Scene scene = new Scene(root, 800, 600);
                                // Install the custom css styles, colors and typographic.
                                // ThemeProvider is a class that install css, colors and typographic.
                                // Also can be include fonts.
                                ThemeProvider.install(scene, Css.ALL, Font.INSTAGRAM);
                                Stage stage = new Stage();
                                stage.setScene(scene);
                                stage.show();
                            
                                }
                            }
                            
                            """)
                    .node(createExample())
                    .text("Try clone the project skeleton, and start by yourself with minimal settings.")
                    .node(TutorialUtils.createCardLink("Skeleton", new URI("https://github.com/gleidsonmt/dash-skeleton")))

                    ;
        } catch (URISyntaxException e) {
            throw new RuntimeException(e);
        }
    }

    private Node createExample() {
        return TutorialUtils.createAction(_ -> {

            class CustomLayout extends BorderPane implements Layout {

                protected final ObjectProperty<Module> currentModule = new SimpleObjectProperty<>();

                CustomLayout() {
                    bind();
                }

                private void bind() {
                    this.currentModule.addListener((_, oldValue, newValue) -> {
                        Logger.getGlobal().info("You switched between [" + oldValue + "] to [" + newValue + "]");
                        setCenter(((View) newValue).getContent());
                    });
                }

                @Override
                public Region getDrawer() {
                    return (Region) getLeft();
                }

                @Override
                public ObjectProperty<Module> currentModuleProperty() {
                    return currentModule;
                }

            }

            class Main extends CustomLayout {

                public Main() {

//                    Drawer drawer = new Drawer(modules);

//                    var searchBox = new DrawerSearchBox();
//                    drawer.setSearchable(searchBox.textProperty(), name -> name.toLowerCase().contains(searchBox.getText().toLowerCase()));

//                    drawer.getChildren().add(1, searchBox);

                    var nav = new SideNav();
                    this.setLeft(new SideNav());
                    // With this bind I can change the node in the center with a selected drawer item.
                    this.currentModule.bind(nav.currentModuleProperty());
                }
            }

            // Root is the base of the scene, it's the point to get environment around.
            // The Main class is a layout, it's a container of other views.
            // The View class it's a module representation.
            Root root = new Root(new Main());
            Scene scene = new Scene(root, 800, 600);
            // Install the custom css styles, colors and typographic.
            // ThemeProvider is a class that install css, colors and typographic.
            ThemeProvider.install(scene, Css.ALL, Font.INSTAGRAM);

            // Also can be include fonts.
            Stage stage = new Stage();
            stage.setScene(scene);
            stage.show();

//            LibrariesTools.addTools(scene);

        });
    }

}

class SideNav extends Drawer {

    public SideNav() {
        super(
                new View("Orders", new SVGIcon(Icon.ORDERS), new Text("Orders View")),
                new View("Apps", new SVGIcon(Icon.APPS), new Text("Apps View")),
                new View("Products", new SVGIcon(Icon.LOCAL_MALL), new Text("Products View")),
                new ModuleView("View", new View("View 2"), new View("View 3"))
        );
        setHeader(createDrawerHeader());
    }

    public Node createDrawerHeader() {
        GridPane drawerHeader = new GridPane();
        drawerHeader.setPadding(new Insets(0, 0, 20, 0));
        Label logo = new Label("Drawer, CO");
        logo.getStyleClass().addAll("h3", "font-instagram", "bold");
        SVGIcon icon = new SVGIcon(Icon.HUB);
        Label iconContainer = new Label();
        iconContainer.getStyleClass().addAll("rounded", "border-2", "border-light-gray", "padding-5");

        iconContainer.setContentDisplay(ContentDisplay.GRAPHIC_ONLY);
        iconContainer.setGraphic(icon);
        logo.setGraphic(iconContainer);
        drawerHeader.add(logo, 0, 0);
        return drawerHeader;
    }
}