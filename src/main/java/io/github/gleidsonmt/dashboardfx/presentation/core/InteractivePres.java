package io.github.gleidsonmt.dashboardfx.presentation.core;

import io.github.gleidsonmt.dashboardfx.dashboard.ActionableView;
import io.github.gleidsonmt.dashboardfx.presentation.internal.Tutorial;
import io.github.gleidsonmt.dashboardfx.utils.TutorialUtils;
import io.github.gleidsonmt.glad.base.Root;
import io.github.gleidsonmt.glad.base.internal.Anchor;
import io.github.gleidsonmt.glad.base.internal.View;
import io.github.gleidsonmt.glad.controls.button.Button;
import io.github.gleidsonmt.glad.controls.button.IconButton;
import io.github.gleidsonmt.glad.controls.icon.Icon;
import io.github.gleidsonmt.glad.controls.icon.SVGIcon;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;
import javafx.scene.text.TextFlow;

/**
 * @author Gleidson Neves da Silveira | gleidisonmt@gmail.com
 * Create on  19/08/2025
 */
public class InteractivePres extends View implements ActionableView {

    private Tutorial tutorial;

    public InteractivePres() {
        super("InteractiveTest");

        Button node = new Button("Click on!");
        node.setPadding(new Insets(10));
        node.setOnAction(_ -> {
            Root root = (Root) tutorial.getRoot().getScene().getRoot();
            root.flow()
                    .content(createButton())
                    .show(node);
        });

        tutorial = new Tutorial()

//                .h3("Introduction")
//                .h3("Flow", "Introduction")
//                .h3("Show", "Flow")
//                .h3("Mouse", "Show")
//                .h3("Node", "Show")
//                .h3("Wrapper", "Introduction")
//                .h3("Show", "Wrapper")
//                .h3("Combine", "Wrapper")
//                .h3("Action", "Wrapper")
//                .h3("Hide", "Wrapper")
                .indicators()
                .h3("Introduction")
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
                .h3("Mouse", "Show")
                .text("Showing using a mouse position.")
                .code("""
                        Root root = (Root) getScene().getRoot();
                        root.flow()
                                .content(createButton())
                                .show(e); // The e represents the mouse event.
                        """)
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

                .h3("Close", "Flow")
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
                .text("Suppose you need a region on the top, that is fixed on top and the sides.")
                .text("The max of its width it's set.")
                .code("""
                        Root root = (Root) getScene().getRoot();
                        root.flow()
                                .pos(Pos.TOP_CENTER)
                                .anchor(Anchor.TOP) // That can be TOP, BOTTOM, RIGHT, LEFT, CENTER, NONE -> the last anchor is default
                                .content(createTerms())
                                .show();
                        """)
                .node(TutorialUtils.createAction(_ ->
                        getRoot().flow()
                                .pos(Pos.TOP_CENTER)
                                .anchor(Anchor.TOP)
                                .content(createTerms())
                                .show()))
                .text("You can anchor any direction (Top, Bottom, Left, Right) direction or none.")
//                        .text("This can be not to useful without an gray background. But it's the start of how drawer works.")
//                        .text("And next let's see Wrapper class.")

                .h3("Wrapper")
                .text("The wrapper interface disposes a region with color to focus on the element stacked.")
                .legend("io.github.gleidsonmt.glad.base.Wrapper")
                .legend("Click on it, to hide.")

                .h3("Show", "Wrapper")
                .code("""
                        ...
                        root.wrapper().show();
                        """)
                .node(TutorialUtils.createAction(_ ->
                        getRoot().wrapper()
                                .onClick(_ -> getRoot().wrapper().hide())
                                .show()))
                .h3("Combining", "Wrapper")
                .text("The best use is combining with another node.")
                .code("""
                        ...
                        root.wrapper()
                            .with(createButton())
                            .show();
                        """)
                .node(TutorialUtils.createAction(_ ->
                        getRoot().wrapper()
                                .with(createButton())
                                .onClick(_ -> getRoot().wrapper().hide())
                                .show()))
                .h3("Action", "Wrapper")
                .code("""
                        ...
                        root.wrapper()
                            .with(createButton())
                            .onClick(_ -> root.wrapper().hide())
                            .show();
                        """)
                .h3("Hide", "Wrapper")
                .code("""
                        ...
                        root.wrapper().hide();
                        """);
        ;
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
        VBox box = new VBox(
                new Text("Lorem"),
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

    @Override
    public void onEnter(Root root) {
        setContent(tutorial.build().getRoot());
    }
}
