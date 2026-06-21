package io.github.gleidsonmt.dashboardfx.presentation.core;

import io.github.gleidsonmt.dashboardfx.HotTools;
import io.github.gleidsonmt.dashboardfx.presentation.CustomizablePresentation;
import io.github.gleidsonmt.dashboardfx.presentation.internal.Tutorial;
import io.github.gleidsonmt.dashboardfx.utils.Assets;
import io.github.gleidsonmt.dashboardfx.utils.TutorialUtils;
import io.github.gleidsonmt.glad.base.*;
import io.github.gleidsonmt.glad.base.drawer.Module;
import io.github.gleidsonmt.glad.base.drawer.SideNav;
import io.github.gleidsonmt.glad.base.drawer.View;
import io.github.gleidsonmt.glad.controls.icon.Icon;
import io.github.gleidsonmt.glad.controls.icon.SVGIcon;
import io.github.gleidsonmt.glad.theme.Css;
import io.github.gleidsonmt.glad.theme.Font;
import io.github.gleidsonmt.glad.theme.ThemeProvider;
import io.github.gleidsonmt.presentation.Presentation;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
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
 * Create on  02/10/2025
 */
public class BuildingPres extends CustomizablePresentation {

    public BuildingPres() {
        super("Building");
    }

    @Override
    public Presentation create() {
//        try {
        return new Tutorial()
                .overview()
                .h1("Getting")
                .text("First I like to say that this is a library, so you can use it in your project.")
                .text("And I have intentions to make it available on MavenCentral soon.")
                .text("But for now, you can use it by cloning the project and importing it in your IDE.")

                .text("You can clone your project and use as a submodule.")
                .text("Run the command below on your project root.")
                .code("""
                        git submodule add https://github.com/gleidsonmt/glad.git
                        """)
                .text("After that you can import the glad classes in your project.")

                .text("Make sure your using gradle ^8.14 or java ^23 and javafx ^21")

                .h3("Gradle")
                .h4("IntelliJ 2026.1.3")
                .text("Create your project as you do using gradle as an option.")
                .text("Usually you can see a file called settings.gradle.kts")
                .text("This file is used to define the modules of your project.")
                .text("Add this line of code.")
                .code("""
                        rootProject.name = "my-project"
                        include("glad")
                        """)
                .text("As well you have a build.gradlew.kts file.")
                .text("In the block dependencies you can add the glad dependency.")
                .code("""
                        ...
                        implementation(project(":glad"))
                        """)
                .text("In module-info add the glad module.")
                .code("""
                        ...
                        requires io.github.gleidsonmt.glad;
                        """)
                .text("Now you can import the glad classes in your project.")
//                    .image(Assets.getImage("tutorials/intellij_gradle_start.png"))


                .h2("Using")
                .text("With you project created, you can start by creating a new class that extends Application.")
                .code("""
                        public class App extends Application {
                            ...
                            @Override
                            public void start(Stage stage) {
                                StackPane root = new StackPane();
                                Scene scene = new Scene(root);
                                ThemeProvider.install(scene, Css.ALL, Font.INSTAGRAM);
                                stage.show();
                            }
                        }
                        """)

                .h3("Layout")
                .text("Main page, layout page, body page anything can be called but layout is the most important.")
                .text("Everything starts here.")
                .text("In JavaFx we have layout panes, you can chose one and implement using Layout interface.")
                .code("""
                        public class Main extends BorderPane {
                        
                              private final ObjectProperty<Module> currentModuleProperty = new SimpleObjectProperty<>();
                        
                              public Main() {
                                  bind();
                              }
                        
                              private void bind() {
                                  currentModuleProperty.addListener((_, oldValue, newValue) -> {
                                      Logger.getGlobal().info("You switched between [" + oldValue + "] to [" + newValue + "]");
                                      setCenter(newValue.getContent());
                                  });
                              }
                          }
                        """)
                .text("There's two methods bind and getDrawer")
                .text("The bind method is set and listener that updates when the currentModuleProperty changes.")
                .text("The currentModuleProperty is a property that represents the current module.")
                .text("Module is an interface that represents a part of the app.")
                .text("The getDrawer method returns the drawer region.")
                .text("As I decide to use a border pane as the layout, I set my left side as my drawer.")

                .h3("Drawer")
                .code("""
                        public class SideNav extends Nav {
                        
                            public SideNav() {
                                super(
                                        new View("Orders", new SVGIcon(Icon.ORDERS), new Text("Orders View")),
                                        new View("Apps", new SVGIcon(Icon.APPS), new Text("Apps View")),
                                        new View("Products", new SVGIcon(Icon.LOCAL_MALL), new Text("Products View"))
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

                .h3("Main")
                .code("""
                        public class App extends Application {
                        
                            @Override
                            public void start(Stage stage) throws Exception {
                        
                            // Root is the base of the scene, it's the point to get environment around.
                            // The Main class is a layout, it's a abstractContainer of other views.
                            // The View class it's a module representation.
                            var layout = new Main();
                            var nav = new Nav();
                            layout.setLeft(nav);
                            layout.currentModuleProperty.bind(nav.currentModuleProperty());
                            Root root = new Root(layout);
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
//                    .node(TutorialUtils.createCardLink("Skeleton", new URI("https://github.com/gleidsonmt/dash-skeleton")))
                ;
//        } catch (URISyntaxException e) {
//            throw new RuntimeException(e);
//        }
    }

    private Node createExample() {
        return TutorialUtils.createAction(_ -> {

            class Main extends BorderPane {

                private final ObjectProperty<Module> currentModuleProperty = new SimpleObjectProperty<>();

                public Main() {
                    bind();
                }

                private void bind() {
                    currentModuleProperty.addListener((_, oldValue, newValue) -> {
                        Logger.getGlobal().info("You switched between [" + oldValue + "] to [" + newValue + "]");
                        setCenter(newValue.getContent());
                    });
                }
            }

            // Root is the base of the scene, it's the point to get the environment around.
            // The Main class is a layout, it's an abstractContainer of other views.
            // The View class it's a module representation.
            var layout = new Main();
            var nav = new Nav();
            layout.setLeft(nav);
            layout.currentModuleProperty.bind(nav.currentModuleProperty());
            Root root = new Root(layout);

            Scene scene = new Scene(root, 800, 600);
            // Install the custom css styles, colors and typographic.
            // ThemeProvider is a class that install css, colors and typographic.
            ThemeProvider.install(scene, Css.ALL, Font.INSTAGRAM, Font.POPPINS);

            // Also can be include fonts.
            Stage stage = new Stage();
            stage.setScene(scene);
            stage.show();

//            HotTools.debug(scene);

        });
    }
}

class Nav extends SideNav {

    public Nav() {
        super(
                new View("Orders", new SVGIcon(Icon.ORDERS), new Text("Orders View")),
                new View("Apps", new SVGIcon(Icon.APPS), new Text("Apps View")),
                new View("Products", new SVGIcon(Icon.LOCAL_MALL), new Text("Products View"))
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
