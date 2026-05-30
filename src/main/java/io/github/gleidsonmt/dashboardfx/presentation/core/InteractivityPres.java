package io.github.gleidsonmt.dashboardfx.presentation.core;

import io.github.gleidsonmt.dashboardfx.presentation.CustomizablePresentation;
import io.github.gleidsonmt.dashboardfx.presentation.internal.Tutorial;
import io.github.gleidsonmt.dashboardfx.utils.Assets;
import io.github.gleidsonmt.dashboardfx.utils.TutorialUtils;
import io.github.gleidsonmt.glad.base.Anchor;
import io.github.gleidsonmt.glad.base.Root;
import io.github.gleidsonmt.glad.base.dialog.WrapperEffect;
import io.github.gleidsonmt.glad.base.dialog.alert.AlertType;
import io.github.gleidsonmt.glad.base.dialog.snack.SnackOption;
import io.github.gleidsonmt.glad.controls.button.Button;
import io.github.gleidsonmt.glad.controls.button.IconButton;
import io.github.gleidsonmt.glad.controls.icon.Icon;
import io.github.gleidsonmt.glad.controls.icon.SVGIcon;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;
import javafx.scene.text.TextFlow;

/**
 * @author Gleidson Neves da Silveira | gleidisonmt@gmail.com
 * Create on  19/08/2025
 */
public class InteractivityPres extends CustomizablePresentation {

    public InteractivityPres() {
        super("Interactivity");
    }

    @Override
    public Tutorial create() {
        Button node = new Button("Click on!");
        node.setPadding(new Insets(10));
        node.setOnAction(_ -> {
            Root root = (Root) getRoot().getScene().getRoot();
            root.flow()
                    .content(createButton())
                    .show(node);
        });

        return new Tutorial()
                .indicators()
                .overview()
                .h3("Introduction")
                .separator()
                .text("I've been really interested in JavaFX interactivity.")
                .text("I'm pro imperative style programming, I think is really cute, organized and readable.")
                .text("The interactivity quoted here, it's about creating and putting absolute nodes in root.")
                .text("For example alerts, fav button, snacks, dialogs etc.")
                .text("By that I've create a method from root that you can call this 'api'.")
                .code("""
                        Root root = (Root) getScene().getRoot();
                        root.flow();
                        """)
                .text("Of course you can't get root directly, you need to get from event. (MouseEvent, KeyEvent, etc.)")
                .text("The method flow() returns a Flow object, that you can use to create absolute nodes.")
                .h3("Flow")
                .text("""
                        Using stack pane you can add nodes in the main positions, and translate this
                        nodes using insets.
                        """)
                .legend("io.github.gleidsonmt.glad.base.Flow")
                .h4("Show", "Flow")
                .text("Create a floating button.")
                .code("""
                        Root root = (Root) getScene().getRoot();
                        root.flow()
                                .content(createButton())
                                .show();
                        """)
                .node(TutorialUtils.createAction(_ ->
                        getRoot().flow()
                                .content(createButton())
                                .show()))
                .text("Now you see a button on the center of the screen.")
                .text("I'm calling a method content(Node node), that pass a node to flow.")
                .text("The createButton() method returns an IconButton with an SVGIcon as a graphic.\n")
                .legend("See more in")
                .node(TutorialUtils.createLink("Button", "Button"))
                .node(TutorialUtils.createLink("SVGIcon", "SVGIcon"))

                .h3("Size", "Show")
                .text("Set the size using width() and height().")
                .code("""
                        Root root = (Root) getScene().getRoot();
                        root.flow()
                                .content(createButton())
                                .width(300)
                                .height(300)
                                .show();
                        """)
                .text("If width or height isn't set, the pref sizes will be used.")

                .h3("Mouse", "Show")
                .text("Showing using a mouse position.")
                .code("""
                        Root root = (Root) getScene().getRoot();
                        root.flow()
                                .content(createButton())
                                .show(e); // The e represents the mouse event.
                        """)
                .text("The center of cursor node in the mouse position is default. But you can also use insets and pos to modify.")
                .node(TutorialUtils.createAction("Activate action", _ ->
                        getRoot().setOnMouseClicked(event ->
                                getRoot().flow()
                                        .content(createButton())
                                        .show(event))))
                .legend("After activated, can you click and buttons will be show.")
                .h3("Node", "Show")
                .text("You can also show based on the node position.")
                .code("""
                        Root root = (Root) getScene().getRoot();
                        root.flow()
                                .content(createButton())
                                .show(target); // The target represents the node.
                        """)
                .node(node)

                .h3("Hide", "Flow")
                .text("But you can't close it, right?")
                .text("No, the button has a method set to close the flow.")
                .text("Like this one.")
                .code("""
                        root.flow().hide();
                        root.flow().remove(node); // This one for specific nodes
                        """)
                .h3("Position", "Flow")
                .text("By default the button only appears on the center, but let's change it.")
                .code("""
                        ...
                        root.flow()
                                .content(createButton())
                                .pos(Pos.CENTER_RIGHT) // Of course you can use all of positions.
                                .show();
                        """)
                .node(TutorialUtils.createAction(_ ->
                        getRoot().flow()
                                .content(createButton())
                                .pos(Pos.CENTER_RIGHT)
                                .show()))
                .text("Now you see the button en right of the screen.")
                .text("The pos() method receives a Pos, it's a default enum used by javafx to position nodes.")
                .h3("Insets", "Flow")
                .text("But you actually needs to add some insets")
                .code("""
                        root.flow()
                                .content(createButton())
                                .pos(Pos.CENTER_RIGHT)
                                .insets(new Insets(0, 20, 0, 20))
                                .show();
                        """)
                .node(TutorialUtils.createAction(_ ->
                        getRoot().flow()
                                .content(createButton())
                                .pos(Pos.CENTER_RIGHT)
                                .insets(new Insets(0, 20, 0, 20))
                                .show()))
                .text("Now you see the button with some insets.")
                .text("The button is now more 20px to the right.")
                .h3("Anchor", "Flow")
                .text("That one can be a little confuse. The nodes side can be anchor on sides, center and all.")
                .text("Suppose you need a region on the top, that is fixed on top and the sides.")
                .text("The max of its width it's set.")
                .code("""
                        Root root = (Root) getScene().getRoot();
                        root.flow()
                                .pos(Pos.TOP_CENTER)
                                .anchor(Anchor.TOP) // That can be TOP, BOTTOM, RIGHT, LEFT, CENTER, FULL -> the full anchor gets all windows size.
                                .content(createTerms())
                                .show();
                        """)
                .text("See examples.")
                .demo(new Node[]{
                                TutorialUtils.createAction("TOP", _ ->
                                        getRoot().flow()
                                                .pos(Pos.TOP_CENTER)
                                                .anchor(Anchor.TOP)
                                                .height(300)
                                                .content(createTerms())
                                                .show()),
                                TutorialUtils.createAction("BOTTOM", _ ->
                                        getRoot().flow()
                                                .pos(Pos.BOTTOM_CENTER)
                                                .anchor(Anchor.BOTTOM)
                                                .height(300)
                                                .content(createTerms())
                                                .show()),
                                TutorialUtils.createAction("LEFT", _ ->
                                        getRoot().flow()
                                                .pos(Pos.CENTER_LEFT)
                                                .anchor(Anchor.LEFT)
                                                .height(300)
                                                .content(createTerms())
                                                .show()),
                                TutorialUtils.createAction("RIGHT", _ ->
                                        getRoot().flow()
                                                .pos(Pos.CENTER_RIGHT)
                                                .anchor(Anchor.RIGHT)
                                                .height(300)
                                                .content(createTerms())
                                                .show()),
                                TutorialUtils.createAction("FULL", _ ->
                                        getRoot().flow()
                                                .anchor(Anchor.FULL)
                                                .height(300)
                                                .content(createTerms())
                                                .show())
                        }
                )

                .text("You can anchor any direction (Top, Bottom, Left, Right) direction or none.")
//                        .text("This can be not to useful without an gray background. But it's the start of how drawer works.")
//                        .text("And next let's see Wrapper class.")


                .h3("Behavior")
                .h3("Dialog", "Behavior")
                .text("The base for creating flown containers.")
                .demo(TutorialUtils.createAction(_ -> getRoot()
                        .behavior()
                        .dialog()
                        .content(createWelcome())
                        .show()))
                .code("""
                        getRoot()
                            .behavior()
                            .dialog()
                            .content(createWelcome())
                            .show();
                        """)
                .h3("Effect", "Dialog")
                .demo(new Node[]{
                        TutorialUtils.createAction(_ -> getRoot()
                                .behavior()
                                .dialog()
                                .content(createWelcome())
                                .effect()
                                .show()),
                        TutorialUtils.createAction(_ -> getRoot()
                                .behavior()
                                .dialog()
                                .content(createWelcome())
                                .with(WrapperEffect.BLUR)
                                .show())
                })
                .code("""
                        getRoot()
                            .behavior()
                            .dialog()
                            .content(createWelcome())
                            .effect() // Active effect if you want to change it pass like effect(WrapperEffect.BLUR)
                            .show();
                        """)

                .h3("Alerts", "Behavior")
                .text("Built-in alerts")
                .demo(TutorialUtils.createAction(_ ->
                        getRoot()
                                .behavior()
                                .alert()
                                .title("You are about to delete task") // The main text of the alert
                                .content( // Set a node as your content
                                        new TextFlow(new Text(
                                                "Are you sure you want to delete this post? This action cannot be undone"
                                        ))
                                )
                                .show()))

                .code("""
                        root.behavior()
                            .alert()
                            .title("You are about to delete task")
                            .content(
                                    new TextFlow(new Text(
                                            "Are you sure you want to delete this post? This action cannot be undone"
                                    ))
                            )
                            .show();
                        """)

                .h3("Buttons", "Alerts")
                .text("The alerts can use buttons like(ok, cancel, yes, no)")
                .demo(TutorialUtils.createAction(_ ->
                        getRoot()
                                .behavior()
                                .alert()
                                .title("You are about to delete task") // The main text of the alert
                                .content( // Set a node as your content
                                        new TextFlow(new Text(
                                                "Are you sure you want to delete this post? This action cannot be undone"
                                        ))
                                )
                                .buttons(new Button("cancel"), new Button("ok")) // These are actions
                                .show()))
                .code("""
                        root.behavior()
                            .alert()
                            .title("You are about to delete task") // The main text of the alert
                            .content( // Set a node as your content
                                    new TextFlow(new Text(
                                            "Are you sure you want to delete this post? This action cannot be undone"
                                    ))
                            )
                            .buttons(new Button("cancel"), new Button("ok")) // These are actions
                            .show();
                        """)

                .text("The buttons with the name cancel and ok, is set by default as a default and cancel button respectively.")
                .text("The buttons also have an event default that close its alert.")
                .h3("Types", "Alerts")
                .text("The alerts can use types (info, success, error, warning)")
                .demo(new Node[]{
                                TutorialUtils.createAction(_ -> getRoot()
                                        .behavior()
                                        .alert()
                                        .title("You are about to delete task")

                                        .content(
                                                new TextFlow(new Text(
                                                        "Are you sure you want to delete this post? This action cannot be undone"
                                                ))
                                        )
                                        .buttons(new Button("cancel"), new Button("ok")) // These are actions
                                        .type("ERROR")
                                        .show()),
                                TutorialUtils.createAction(_ -> getRoot()
                                        .behavior()
                                        .alert()
                                        .title("You are about to delete task")
                                        .content(
                                                new TextFlow(new Text(
                                                        "Are you sure you want to delete this post? This action cannot be undone"
                                                )))
                                        .buttons(new Button("cancel"), new Button("ok")) // These are actions
                                        .type(AlertType.WARNING)
                                        .show()),
                                TutorialUtils.createAction(_ -> getRoot()
                                        .behavior()
                                        .alert()
                                        .title("You are about to delete task")
                                        .buttons(new Button("cancel"), new Button("ok")) // These are actions
                                        .content(
                                                new TextFlow(new Text(
                                                        "Are you sure you want to delete this post? This action cannot be undone"
                                                )))
                                        .type(AlertType.SUCCESS)
                                        .show()),
                                TutorialUtils.createAction(_ -> getRoot()
                                        .behavior()
                                        .alert()
                                        .title("You are about to delete task")
                                        .buttons(new Button("cancel"), new Button("ok")) // These are actions
                                        .content(
                                                new TextFlow(new Text(
                                                        "Are you sure you want to delete this post? This action cannot be undone"
                                                )))
                                        .type(AlertType.INFO)
                                        .show())
                        }
                )
                .code("""
                        root.behavior()
                            .alert()
                            .title("You are about to delete task") // The main text of the alert
                            .content( // Set a node as your content
                                    new TextFlow(new Text(
                                            "Are you sure you want to delete this post? This action cannot be undone"
                                    ))
                            )
                            .buttons(new Button("cancel"), new Button("ok")) // These are actions
                            .type(AlertType.ERROR) // The type of alert (WARNING, INFO, SUCCESS, ERROR),
                            // you can also use .type("error")// this works as well
                            .show();
                        """)

                .h3("Snack", "Behavior")
                .text("'Snack' is a component that displays a message at the bottom of the screen.")

                .demo(TutorialUtils.createAction(_ -> getRoot()
                        .behavior()
                        .snack()
                        .message("It's a message I write here.")
                        .show()))
                .code("""
                        root.behavior()
                            .snack()
                            .message("Tracking your location.")
                            .show();
                        """)
                .h3("Graphic", "Snack")
                .text("Passing a graphic (only left)")
                .demo(TutorialUtils.createAction(_ -> getRoot()
                        .behavior()
                        .snack()
                        .message("Tracking your location.")
                        .graphic(new SVGIcon(Icon.LOCATION_ON))
                        .show()))
                .code("""
                        root.behavior()
                            .snack()
                            .message("Tracking your location.")
                            .graphic(new SVGIcon(Icon.LOCATION_ON))
                            .show();
                        """)
                .h3("Actions", "Snack")
                .text("Adding actions (buttons)")
                .demo(TutorialUtils.createAction(_ -> getRoot()
                        .behavior()
                        .snack()
                        .message("Tracking your location.")
                        .graphic(new SVGIcon(Icon.LOCATION_ON))
                        .action(
                                new SnackOption("Confirm", _ -> System.out.println("Action Confirm!")),
                                new SnackOption("Cancel", _ -> System.out.println("Action Cancel!"))
                        )
                        .show()))
                .code("""
                        root.behavior()
                            .snack()
                            .message("Tracking your location.")
                            .graphic(new SVGIcon(Icon.LOCATION_ON))
                            .action(
                                    new SnackOption("Confirm", _ -> System.out.println("Action Confirm!")),
                                    new SnackOption("Cancel", _ -> System.out.println("Action Cancel!"))
                            )
                            .show();
                        """)

                ;
    }

    private Region createWelcome() {
        VBox box = new VBox();
        box.setAlignment(Pos.CENTER);
        box.setSpacing(40);
        box.setPadding(new Insets(20));

        ImageView imageView = new ImageView();
        imageView.setImage(Assets.getImage("novice_256.png"));
        imageView.setFitWidth(100);
        imageView.setFitHeight(100);
        Text title = new Text("Hey, you dropped this");
        title.getStyleClass().add("h3");
        Text text = new Text("Thank you for going premium --- Enjoy unlimited access to all features.");
        TextFlow textFlow = new TextFlow(text);
        textFlow.setTextAlignment(TextAlignment.CENTER);
        Button button = new Button("Let's GOoooo!");
        button.setCancelButton(true);
        box.getChildren().addAll(imageView, title, textFlow, button);
        button.setOnAction(_ -> getRoot()
                .behavior()
                .dialog()
                .hide());
        return box;
    }


    private IconButton createButton() {
        IconButton iconButton = new IconButton(new SVGIcon(Icon.ADD), false,
                "min-h-50 min-w-50 round".split(" ")
        );
        iconButton.setOnAction(_ -> {
            Root root = getRoot();
            root.flow().hide();
        });
        return iconButton;
    }

    private Region createTerms() {
        Button button = new Button("Accept terms!");
        button.setOnAction(_ -> getRoot().flow().hide());
        TextFlow textFlow = new TextFlow(new Text("Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, \nquis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur. Excepteur sint occaecat cupidatat non proident, sunt in culpa qui officia deserunt mollit anim id est laborum."));
        Text title = new Text("Here put your title"); // hehe
        title.getStyleClass().add("h3");
        VBox box = new VBox(
                title,
                textFlow,
                button);
        textFlow.setTextAlignment(TextAlignment.CENTER);
        textFlow.setPadding(new Insets(20));
//        textFlow.setMaxWidth(800);

        box.getStyleClass().addAll("bg-white", "border-2", "border-light-gray-2");
        box.setAlignment(Pos.CENTER);
        box.setMinHeight(300);
        return box;
    }

}
