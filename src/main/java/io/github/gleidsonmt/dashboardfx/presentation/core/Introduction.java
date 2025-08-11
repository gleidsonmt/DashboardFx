package io.github.gleidsonmt.dashboardfx.presentation.core;

import io.github.gleidsonmt.dashboardfx.presentation.drawer.SimpleDrawer;
import io.github.gleidsonmt.dashboardfx.presentation.internal.Tutorial;
import io.github.gleidsonmt.dashboardfx.utils.Assets;
import io.github.gleidsonmt.dashboardfx.utils.TutorialUtils;
import io.github.gleidsonmt.glad.base.Layout;
import io.github.gleidsonmt.glad.base.Root;
import io.github.gleidsonmt.glad.base.internal.Module;
import io.github.gleidsonmt.glad.base.responsive.Break;
import io.github.gleidsonmt.glad.controls.button.IconButton;
import io.github.gleidsonmt.glad.controls.icon.Icon;
import io.github.gleidsonmt.glad.controls.icon.SVGIcon;
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
                                    The main idea is to create a fast way to create apps, of course there's
                                    a lot of techniques to do that, but it's not a normal thing to see in javafx.
                                    In a normal day you need ways to get fast to get your design, repeating tasks is the most hating thing daily.
                                    This projects expects to add some layers to production by using known patterns.""")
                            .text("""
                                    The jar dependencies are located in vendor folder.""")

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
//
                            .h3("Start an App", "Introduction")
                            .code("""
                                    /**
                                     * @author Gleidson Neves da Silveira | gleidisonmt@gmail.com
                                     * Create on  20/02/2025
                                     */
                                    public class AppOld extends Application {
                                        @Override
                                        public void start(Stage stage) {
                                            Root root = new Root(new Layout(new Button("Welcome")));
                                            Scene scene = new Scene(root, 800, 600);
                                            stage.setScene(scene);
                                            stage.show();
                                        }
                                    }
                                    """)

                            .node(createActionStart())
                            .h4("Theme", "Start an AppOld")
                            .text("Let's add some milk with a different font")
                            .code("""
                                    ....
                                    ThemeProvider.install(scene, Font.POPPINS);
                                    ....
                                    """)
                            .text("This isn't work very well, because this code install font, but doesn't use it.")
                            .text("And we have a css problem with typographic and colors, let's solve.")
                            .code("""
                                    ....
                                    ThemeProvider.install(scene,
                                            Css.COLORS,
                                            Css.TYPOGRAPHIC
                                    );
                                    ....
                                    """)
                            .text("Now theme colors and typographic is settled.")
                            .node(createStep2())
                            .node(TutorialUtils.createTextWithLink("You can see more about text styling right here", "Text", "Text"))
                            .text("Let's add a button.")
                            .text("First add the button in scene")
                            .code("""
                                    ...
                                    VBox body = new VBox(new Text("You Do it!"), new Button("Welcome!"));
                                    body.setAlignment(Pos.CENTER); // align in center
                                    Root root = new Root(new Layout(body));
                                    ...
                                    """)
                            .text("Now add theme")
                            .code("""
                                    ...
                                    ThemeProvider.install(scene,
                                            Css.COLORS,
                                            Css.TYPOGRAPHIC,
                                            Css.BUTTON
                                    );
                                    ...
                                    """)
                            .node(createStep3())
                            .node(TutorialUtils.createTextWithLink("You can see more about button styling right here", "Button", "Labeled", "Button"))
//

                            .h4("Root", "Start an AppOld")
                            .legend("io.gleidsonmt.glad.base.Root")
                            .text("""
                                    The Root class is an ideia to have stacked components, actual apps has flow components,
                                    usually stacked, like snacks, alerts etc. So the root class is wrapper to a StackPane.
                                    These methods can be called as behavior.
                                    This app actually uses a root class.
                                    Try to get a button in root scene.
                                    """)

                            .node(createRootExample())
                            .code("""

                                    IconButton button = new IconButton(new SVGIcon(Icon.APPS));
                                    button.getStyleClass().addAll("padding-20", "raised");

                                    button.setOnMouseClicked(event -> {
                                        Root main = (Root) getScene().getRoot();
                                        main.flow() // call global method
                                                .openByCursor(button, event);
                                    });""")

                            .h4("Global Methods", "Root")
                            .text("""
                                    These global methods are called when application needs to update the root.
                                    Calling notifications and custom alerts.
                                    """)
                            .node(TutorialUtils.createLink("Wrapper", "Wrapper"))
                            .node(TutorialUtils.createLink("Flow", "Flow"))
                            .node(TutorialUtils.createLink("Behavior", "Behavior"))
//
                            .h4("Layout", "Start an App")
                            .text("""
                                    The Layout class is the global parts of the application.
                                    The combinations of these two layouts, creates an empty region with five separating blocks.
                                    """)
                            .legend("io.gleidsonmt.glad.base.Layout")
                            .code("Root root = new Root(new Layout());")
                            .image(Assets.getImage("part3.png"))
                            .text("""
                                    As knowing to much nodes cause leaks of memory, using BorderPane or a
                                    complex layout can be combined with putting things without modifying the actual view,
                                    example dashboards with drawer, aside, navs, footers.. and so on.
                                    """)
                            .text("Working with layout")
                            .code("""
                                    ...
                                    Layout layout = new Layout(body);

                                    Root root = new Root(layout);
                                    ...
                                    """)
                            .text("Let's add list view css")
                            .code("""
                                    ThemeProvider.install(scene,
                                            Css.COLORS,
                                            Css.TYPOGRAPHIC,
                                            Css.BUTTON,
                                            Css.LIST_VIEW,
                                            Css.IMMERSIVE_SCROLL
                                    );
                                    """)
                            .text("List View and layouts that use scroll bars need to add immersive scroll as a theme, it can style the scroll pane")
                            .text("Lets add a short version.")
                            .code("""
                                    ThemeProvider.install(scene,
                                        Css.DEFAULT,
                                        Css.BUTTON,
                                        Css.LIST_VIEW
                                    );
                                    """)
                            .text("Now only focus on component css.")
                            .demo(createStep4())

                            .h3("Responsive", "Layout")

                            .text("Let's get out of this static layout.")
                            .text("Try to resize this new stage.")
                            .demo(createStep5())
                            .text("To get that result layout has a component called Sizer.")
                            .text("It's a class designed to response when scene is resized.")
                            .text("Sizer uses points in the width of scene, this points change the view, in order to get the expect result.")
                            .text("Instead of set left node, will use a response action.")
                            .text("Add our first point")
                            .code("""
                                     layout.setLeft(new SimpleDrawer()); // remove this line
                                     // and add this
                                     SimpleDrawer drawer = new SimpleDrawer();
                                     ...
                                     layout.addPoint(_ -> {
                                        layout.setLeft(drawer);
                                    }, Break.values());""")
                            .node(TutorialUtils.createTextWithLink("SimpleDrawer Class here is used as an example, to view more about click ", "here", "Drawer"))
                            .text("Nothing change, that's because only set one point to all widths.")
                            .text("To mobile width.")
                            .code("""
                                    root.addPoint(_ -> {
                                         layout.setLeft(null);
                                     }, Break.MOBILE);""")
                            .text("Now when the size of scene is small the drawer is not showed.")
                            .text("Because add point it's only really called after scene is settled, it's possible to call getScene() adn getRoot() methods.")
//
                            .demo(createStep6())
                            .text("It's not possible to invoke the drawer right now.")
                            .code("""
                                    ...
                                    NavBar nav = new NavBar();
                                    IconButton hamb = new IconButton(new SVGIcon(Icon.MENU));
                                    hamb.getStyleClass().add("flat");
                                    Text title = new Text("Dashboard");
                                    title.getStyleClass().addAll("text-accent", "h3", "bold");
                                    ....
                                    """)

                            .text("With this when size changes the hamb appears.")
                            .code("""
                                    ...
                                    nav.add(hamb, 0,0);
                                    nav.add(title, 1,0);
                                    ...
                                    root.addPoint(_ -> {
                                        layout.setLeft(null);
                                        layout.setTop(nav);
                                    }, Break.MOBILE);

                                    root.addPoint(_ -> {
                                        layout.setLeft(drawer);
                                        layout.setTop(null);
                                    }, Break.values());
                                    """)
                            .text("But hamb doesn't have any action. Let's fix.")
                            .code("""
                                    hamb.setOnMouseClicked(_ -> root.behavior().openDrawer());
                                    """)
                            .demo(createStep8())
//
                            .text("To change the view here, it's used a bind with two nodes, this nodes are encapsulated by ObjectProperty")
                            .text("And then bind the drawer node selecetd and layout center")
                            .code("""
                                    ...
                                    layout.centerProperty().bind(Bindings.select(drawer.selectedProperty(), "content"));
                                    ...
                                    """)

                            .text("That's the end. But it's also the beginning to this project. Most things it's experimental, but the time will make consistence.")

                            .h3("Testing", "Introduction")
                            .legend("This libs it's only used in runtime do not create an jar file, or an .exe with this, they will probably crash.")
                            .text("Tools used to build and test this project.")
                            .node(TutorialUtils.createCardLink("Scenic View Project", new URI("https://github.com/JonathanGiles/scenic-view")))
                            .text("ScenicView can get information about layout, css and transform nodes in runtime.")
                            .node(TutorialUtils.createCardLink("CSSFX", new URI("https://github.com/McFoggy/cssfx")))
                            .text("Special lib to visualize and update css when app is running.")
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
        flow.getStyleClass().addAll("text-amber","h5");
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

            nav.add(hamb, 0,0);
            nav.add(title, 1,0);

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

            nav.add(hamb, 0,0);
            nav.add(title, 1,0);

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
            Stage stage = new Stage();
            VBox body = new VBox(new Text("You Do it!"), new Button("Welcome!"));
            body.setAlignment(Pos.CENTER);
            Lay layout = new Lay(body);
            Root root = new Root(layout);
            SimpleDrawer drawer = new SimpleDrawer();
//
            root.addPoint(_ -> {
                layout.setLeft(null);
            }, Break.MOBILE);
//
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
