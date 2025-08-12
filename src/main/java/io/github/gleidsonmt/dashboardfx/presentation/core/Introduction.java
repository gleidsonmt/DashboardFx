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
                            .h3("🙋🏼‍ Introduction")
                            .text("""
                                    Hi, my name is Gleidson. I’m a developer from Brazil.
                                    I’ll tutor you around this ecosystem I built.
                                    The main idea here, it’s creating examples to show the power of JavaFX.
                                    There’s a lot of techniques to do that, but it’s not a normal thing to see in JavaFX.
                                    On a normal day, you need ways to get fast to get a system design, repeating tasks is the most boring thing daily.
                                    This project expects to add some layers to production by using known patterns.
                                    And I know there’s a curve to learn. But I hope to make it easier.
                                    """)

                            .text("(Colocar aqui a card para o projeto de skeleton)")

                            .h3("🔎Understanding", "Introduction")
                            .text("Let's start seeing our base class.")
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

                            .node(createStep5())


                            .h4("See more", "Introduction")
                            .node(TutorialUtils.createLink("Drawer", "Drawer"))
                            .node(TutorialUtils.createLink("Wrapper", "Wrapper"))
                            .node(TutorialUtils.createLink("Flow", "Flow"))
                            .node(TutorialUtils.createLink("Behavior", "Behavior"))

                            .h3("Structure of this dash", "Introduction")
                            .text("""
                                    This is a Gradle multi-project, which means it contains multiple projects within it.
                                    The first one is blockcode, an adaption to view highlighted code. (java, css, xml)
                                    The second one is presentation, a base class to create presentations.
                                    The third one is glad, the core that contains customizable controls and themes.
                                    All projects has its repositories and you can see it in the bootom of this presentation.
                                    """)

                            .h3("Dependencies", "Introduction")
                            .h3("Block Code", "Dependencies")
                            .text("This libs creates a block of codes using syntax highlight.")
                            .node(TutorialUtils.createCardLink("BlockCode Project", new URI("https://github.com/gleidsonmt/blockcode")))

                            .h3("Presentation", "Dependencies")
                            .text("The main base class to create presentations.")
                            .node(TutorialUtils.createCardLink("Presentation Project", new URI("https://github.com/gleidsonmt/presentation")))

                            .h3("Glad", "Dependencies")
                            .text("The core that contains customizable controls and themes.")
                            .node(TutorialUtils.createCardLink("Glad Project", new URI("https://github.com/gleidsonmt/glad")))

                            .h3("Testing", "Dependencies")
                            .text("Tools used to build and test this project.")
                            .text("(This libs it's only used in runtime do not create an jar file, or an .exe with this, they will probably crash)")
                            .text("ScenicView can get information about layout, css and transform nodes in runtime.")
                            .node(TutorialUtils.createCardLink("Scenic View Project", new URI("https://github.com/JonathanGiles/scenic-view")))
                            .text("Special lib to visualize and update css when app is running.")
                            .node(TutorialUtils.createCardLink("CSSFX", new URI("https://github.com/McFoggy/cssfx")))
                            .text("That's the end. But it's also the beginning to this project. Most things it's experimental, but the time will make consistence.")

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

    private Node createStep8() {
        return createButton(_ -> {
            Stage stage = new Stage();
            VBox body = new VBox(new Text("You Do it!"), new Button("Welcome!"));
            body.setAlignment(Pos.CENTER);

            Lay layout = new Lay(body);
            Root root = new Root(layout);
            SimpleDrawer drawer = new SimpleDrawer();

            NavBar nav = new NavBar();

            IconButton hamb = new IconButton(new SVGIcon(Icon.MENU));
            hamb.getStyleClass().add("flat");
            Text title = new Text("Dashboard");
            title.getStyleClass().addAll("text-accent", "h3", "bold");

            hamb.setOnMouseClicked(_ -> root.behavior().openDrawer());

            nav.add(hamb, 0, 0);
            nav.add(title, 1, 0);

            layout.centerProperty().bind(Bindings.select(drawer.selectedProperty(), "content"));

            root.addPoint(_ -> {
                layout.setLeft(null);
                layout.setTop(nav);
            }, Break.MOBILE);

            root.addPoint(_ -> {
                layout.setLeft(drawer);
                layout.setTop(null);
            }, Break.values());

            Scene scene = new Scene(root, 800, 600);
            ThemeProvider.install(scene, Font.POPPINS);
            ThemeProvider.install(scene, Css.DEFAULT, Css.BUTTON, Css.LIST_VIEW);
            stage.setScene(scene);
            stage.show();
        });
    }

    private Node createStep6() {
        return createButton(_ -> {
            Stage stage = new Stage();
            VBox body = new VBox(new Text("You Do it!"), new Button("Welcome!"));
            body.setAlignment(Pos.CENTER);

            Lay layout = new Lay(body);
            Root root = new Root(layout);
            SimpleDrawer drawer = new SimpleDrawer();

            NavBar nav = new NavBar();

            IconButton hamb = new IconButton(new SVGIcon(Icon.MENU));
            hamb.getStyleClass().add("flat");
            Text title = new Text("Dashboard");
            title.getStyleClass().addAll("text-accent", "h3", "bold");

            nav.add(hamb, 0, 0);
            nav.add(title, 1, 0);

            layout.centerProperty().bind(Bindings.select(drawer.selectedProperty(), "content"));

            root.addPoint(_ -> {
                layout.setLeft(null);
                layout.setTop(nav);
            }, Break.MOBILE);

            root.addPoint(_ -> {
                layout.setLeft(drawer);
                layout.setTop(null);
            }, Break.values());

            Scene scene = new Scene(root, 800, 600);
            ThemeProvider.install(scene, Font.POPPINS);
            ThemeProvider.install(scene, Css.DEFAULT, Css.BUTTON, Css.LIST_VIEW);
            stage.setScene(scene);
            stage.show();
        });
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
                    .openByCursor(button, e);
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
