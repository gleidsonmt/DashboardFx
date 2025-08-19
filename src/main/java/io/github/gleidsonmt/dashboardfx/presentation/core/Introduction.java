package io.github.gleidsonmt.dashboardfx.presentation.core;

import io.github.gleidsonmt.dashboardfx.presentation.internal.Tutorial;
import io.github.gleidsonmt.dashboardfx.utils.Assets;
import io.github.gleidsonmt.dashboardfx.utils.TutorialUtils;
import io.github.gleidsonmt.glad.base.Layout;
import io.github.gleidsonmt.glad.base.Root;
import io.github.gleidsonmt.glad.base.internal.Module;
import io.github.gleidsonmt.glad.base.internal.View;
import io.github.gleidsonmt.glad.base.responsive.Break;
import io.github.gleidsonmt.glad.controls.button.IconButton;
import io.github.gleidsonmt.glad.controls.icon.Icon;
import io.github.gleidsonmt.glad.controls.icon.SVGIcon;
import io.github.gleidsonmt.glad.drawer.SimpleDrawer;
import io.github.gleidsonmt.glad.theme.Css;
import io.github.gleidsonmt.glad.theme.Font;
import io.github.gleidsonmt.glad.theme.ThemeProvider;
import javafx.beans.binding.Bindings;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.net.URI;
import java.net.URISyntaxException;

/**
 * @author Gleidson Neves da Silveira | gleidisonmt@gmail.com
 * Create on  20/03/2025
 */
public class Introduction extends StackPane {

    public Introduction() {
        try {
            getChildren().setAll(
                    new Tutorial()
                            .h3("Introduction")
                            .text("""
                                    Hi, my name is Gleidson. I’m a developer from Brazil.
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


                            .h3("🔎Understanding", "Introduction")
                            .text("You cloned the project, so you'll see the default javafx project except for the main class and a one jar.")
                            .text("The jar is a core of the project.")
                            .text("And the class App is the point of start.")
                            .text("Let's start seeing this App class.")
                            .code("""
                                    /**
                                     * @author Gleidson Neves da Silveira | gleidisonmt@gmail.com
                                     * Create on  20/02/2025
                                     */
                                    public class App extends Application {
                                    
                                        @Override
                                        public void start(Stage stage) {
                                              // here it's the part of code that define the root and its layout
                                              // Root is the base of the scene, it's the point to get environment around.
                                              // The Main class is a layout, it's a container of other views.
                                              // The View class it's a module representation.
                                              Root root = new Root(new Main(
                                                       new View("Orders", new SVGIcon(Icon.ORDERS), new Text("Orders View")),
                                                       new View("Apps",  new SVGIcon(Icon.APPS), new Text("Apps View")),
                                                       new View("Products",  new SVGIcon(Icon.LOCAL_MALL), new Text("Products View"))
                                               ));
                                               Scene scene = new Scene(root, 800, 600);
                                               // Install the custom css styles, colors and typographic.
                                               // ThemeProvider is a class that install css, colors and typographic.
                                               ThemeProvider.install(scene, Css.DEFAULT, Css.LIST_VIEW);
                                               // Also can be include fonts.
                                               ThemeProvider.install(root, Font.POPPINS);
                                               stage.setScene(scene);
                                               stage.show();
                                        }
                                        
                                        // Here we have the layout of the app, based on border pane.
                                        // BorderPane is a layout that uses four regions, top, left, right and bottom.
                                        // The SimpleDrawer class is just like the name.
                                        // It's a drawer that can have multiple modules. The modules are views.
                                        // The Main class is a layout, it's a container of other views.
                                        // The View class it's a module representation.
                                        static class Main extends BorderPane implements Layout  {
                                
                                            private final SimpleDrawer drawer;
                                
                                            public Main(Module... modules) {
                                                this.drawer = new SimpleDrawer(modules);
                                                this.setLeft(this.drawer);
                                                // With this bind I can change the node in the center with a selected drawer item.
                                                this.centerProperty().bind(Bindings.select(drawer.selectedProperty(), "content"));
                                            }
                                        }
                                    }
                                    """)

                            .text("Now you can click on the button below to see a new stage.")

                            .node(createStep5())

                            .text("I'm very active in this project, because it's a hobby I really like (theming things) you always can talk with me if you want.")


                            .h4("See more", "Introduction")
                            .node(TutorialUtils.createLink("Drawer", "Drawer"))
                            .node(TutorialUtils.createLink("Interactivity", "Interactivity"))
//                            .node(TutorialUtils.createLink("Flow", "Flow"))
//                            .node(TutorialUtils.createLink("BehaviorPres", "BehaviorPres"))




                            .build()
                            .getRoot()
            );
        } catch (URISyntaxException e) {
            throw new RuntimeException(e);
        }
    }

    private Node createWarning() {
        GridPane grid = new GridPane();
        grid.setHgap(10);
        grid.setStyle("-fx-background-color: derive(-amber, 90%); " +
                      "-fx-background-radius: 10px; " +
                      "-fx-border-width: 0px 0px 0px 2px;" +
                      "-fx-border-color: -amber;"
        );
        grid.setPadding(new Insets(20));
        SVGIcon icon = new SVGIcon(Icon.NOTIFICATION_IMPORTANT);
        icon.setScale(2);
        icon.getPath().setStyle("-fx-fill: -amber;");
        icon.getStyleClass().add("warning");

        Label flow = new Label("This libs it's only used in runtime do not create an jar file, or an .exe with this, they will probably crash.");
        flow.getStyleClass().addAll("text-amber", "h5");
        flow.setWrapText(true);

        grid.setAlignment(Pos.CENTER_LEFT);
        grid.add(icon, 0, 0);
        grid.add(flow, 1, 0);
        GridPane.setVgrow(flow, Priority.ALWAYS);
        return grid;
    }


    private Node createStep7() {
        return createButton(e -> {
            Stage stage = new Stage();
            VBox body = new VBox(new Text("You Do it!"), new Button("Welcome!"));
            body.setAlignment(Pos.CENTER);

            Lay layout = new Lay(body);
            Root root = new Root(layout);
            SimpleDrawer drawer = new SimpleDrawer();

            layout.centerProperty().bind(Bindings.select(drawer.selectedProperty(), "content"));

            root.addPoint(_ -> {
                layout.setLeft(null);
            }, Break.MOBILE);

            root.addPoint(_ -> {
                layout.setLeft(drawer);
            }, Break.values());

            Scene scene = new Scene(root, 800, 600);
            ThemeProvider.install(scene, Font.POPPINS);
            ThemeProvider.install(scene, Css.DEFAULT, Css.BUTTON, Css.LIST_VIEW);
            stage.setScene(scene);
            stage.show();
        });
    }

    private Node createStep5() {
        return createButton(e -> {

            class Main extends BorderPane implements Layout  {

                private final SimpleDrawer drawer;

                public Main(Module... modules) {
                    this.drawer = new SimpleDrawer(modules);
                    this.setLeft(this.drawer);
                    this.centerProperty().bind(Bindings.select(drawer.selectedProperty(), "content"));
                }
//
            }

            // Root is the base of the scene, it's the point to get environment around.
            // The Main class is a layout, it's a container of other views.
            // The View class it's a module representation.
            Root root = new Root(new Main(
                    new View("Orders", new SVGIcon(Icon.ORDERS), new Text("Orders View")),
                    new View("Apps",  new SVGIcon(Icon.APPS), new Text("Apps View")),
                    new View("Products",  new SVGIcon(Icon.LOCAL_MALL), new Text("Products View"))
            ));
            Scene scene = new Scene(root, 800, 600);
            // Install the custom css styles, colors and typographic.
            // ThemeProvider is a class that install css, colors and typographic.
            ThemeProvider.install(scene, Css.DEFAULT, Css.LIST_VIEW);
            // Also can be include fonts.
            ThemeProvider.install(root, Font.POPPINS);
            Stage stage = new Stage();
            stage.setScene(scene);
            stage.show();


        });
    }

    private Node createRootExample() {
        return createButton(e -> {
            Root main = (Root) getScene().getRoot();
            IconButton button = new IconButton(new SVGIcon(Icon.APPS));
            button.getStyleClass().addAll("padding-20", "raised");
            button.setOnAction(ev -> {
                main.flow().clear();
            });
            main.flow()
                    .content(button)
                    .show(e);
        });
    }

    private Node createStep3() {
        return createButton(e -> {
            Stage stage = new Stage();
            VBox body = new VBox(new Text("You Do it!"), new Button("Welcome!"));
            body.setAlignment(Pos.CENTER);
            Root root = new Root(new Lay(body));
            Scene scene = new Scene(root, 800, 600);
            ThemeProvider.install(scene, Font.POPPINS);
            ThemeProvider.install(scene, Css.COLORS, Css.TYPOGRAPHIC, Css.BUTTON);
            stage.setScene(scene);
            stage.show();
        });
    }

    private Node createStep4() {
        return createButton(e -> {
            Stage stage = new Stage();
            VBox body = new VBox(new Text("You Do it!"), new Button("Welcome!"));
            body.setAlignment(Pos.CENTER);
            Lay lay = new Lay();
            lay.setLeft(new SimpleDrawer());
            Root root = new Root(lay);
            Scene scene = new Scene(root, 800, 600);
            ThemeProvider.install(scene, Font.POPPINS);
            ThemeProvider.install(scene,
                    Css.DEFAULT,
                    Css.BUTTON,
                    Css.IMMERSIVE_SCROLL,
                    Css.SHAPES,
                    Css.LIST_VIEW);

            stage.setScene(scene);
            stage.show();
//
        });
    }

    class Lay extends BorderPane implements Layout {
        public Lay() {
            setCenter(new Text("You do it!"));
        }

        public Lay(Node node) {
            getChildren().setAll(node);
        }

        @Override
        public void updateView(Module oldVal, Module newVal) {
            throw new RuntimeException("Not implemented");
        }
    }

    private Node createActionStart() {
        return createButton(e -> {
            Stage stage = new Stage();
            Root root = new Root(new Lay());
            Scene scene = new Scene(root, 800, 600);
            stage.setScene(scene);
            stage.show();
        });
    }

    private Button createButton(EventHandler<MouseEvent> event) {
        Button button = new Button("Try on!");
        button.setPadding(new Insets(10));
        VBox.setMargin(button, new Insets(20));
        button.setOnMouseClicked(event);
        return button;
    }

    private Node createStep2() {
        return createButton(e -> {
            Stage stage = new Stage();
            Root root = new Root(new Lay(new Text("You do it!")));
            Scene scene = new Scene(root, 800, 600);
            ThemeProvider.install(scene, Font.POPPINS);
            ThemeProvider.install(scene, Css.COLORS, Css.TYPOGRAPHIC);
            stage.setScene(scene);
            stage.show();
//
        });
    }


}
