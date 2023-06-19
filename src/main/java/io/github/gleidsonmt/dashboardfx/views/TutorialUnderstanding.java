/*
 *
 *    Copyright (C) Gleidson Neves da Silveira
 *
 *    This program is free software: you can redistribute it and/or modify
 *    it under the terms of the GNU General Public License as published by
 *    the Free Software Foundation, either version 3 of the License, or
 *   (at your option) any later version.
 *
 *    This program is distributed in the hope that it will be useful,
 *    but WITHOUT ANY WARRANTY; without even the implied warranty of
 *    MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *    GNU General Public License for more details.
 *
 *     You should have received a copy of the GNU General Public License
 *     along with this program.  If not, see <http://www.gnu.org/licenses/>.
 *
 */

package io.github.gleidsonmt.dashboardfx.views;

import io.github.gleidsonmt.dashboardfx.core.Context;
import io.github.gleidsonmt.dashboardfx.core.view.layout.AlertContainer;
import io.github.gleidsonmt.dashboardfx.core.view.layout.DialogContainer;
import io.github.gleidsonmt.dashboardfx.core.view.layout.DrawerContainer;
import io.github.gleidsonmt.dashboardfx.core.view.layout.creators.Author;
import io.github.gleidsonmt.dashboardfx.core.view.layout.creators.TutorialCreator;
import io.github.gleidsonmt.dashboardfx.core.view.layout.options.ActionOptions;
import io.github.gleidsonmt.dashboardfx.core.view.layout.options.AlertType;
import io.github.gleidsonmt.dashboardfx.core.view.layout.options.DialogAction;
import javafx.geometry.HPos;
import javafx.geometry.Pos;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.image.Image;

/**
 * @author Gleidson Neves da Silveira | gleidisonmt@gmail.com
 * Version 0.0.1
 * Create on  19/01/2023
 */
public class TutorialUnderstanding extends TutorialCreator {

    public TutorialUnderstanding( Context context) {
        super(context);
        this
                .title("Introduction")
                .text("""
                        If you like me and passionate about java, you will see that javafx is
                        the best way to build apps for desktops. I spend too much time searching good
                        tutorials and I found, but when I had this solutions in my hand,
                        I saw yes it's time to create my way to get there!
                        """)
                .text("""
                        Good Frameworks has a big ecosystem, so I was
                        start ways to get that, because the first problem
                        I have with javafx is getting one controller from other View..
                        and so I just build this dash -_(^-^)-_
                        """)
                .text("""
                        First thing I believe it's way to get views from controllers.. navigate for the other views.. and the menu to get there!
                        """)
                .title("Context")
                .text("""
                        A context is a point to get things inside the project, making a global way to get resources around whole project.
                        """)
                .image(new Image(context.getResource("style/img/tree_context.png").toExternalForm()))
                .title("View", "Context")
                .text("""
                        The base interface to create views, get controllers, navigate between views. It's divided in two types, the simple using java to build, and the other using fxml to build.
                        Normally using SimpleView class it's fast to put and navigate in the ecosystem, but isn't for a large view with many nodes, there's a second way, configuring one file and put his resources here.
                        Now this dash uses yaml file to configure location for fxml files and load its after initialization.
                        
                        """)
                .image(new Image(context.getResource("style/img/View.png").toExternalForm()))
                .legend("Tree View the main point")
                .title("Simple View", "View")
                .text("It's only a default java class as self-view. They are our own controllers.")
                .code("""
                        SimpleView myView = new SimpleView("My View", new StackPane());
                            // the name is used to navigate
                            // the node acts as a container
                        """)



                .title("Loading Views", "View")
                .text("It's a view that is loaded by yml file, this wrapper class wrappers the composer, location, charsets.. so you can get in the future or load when application starts.")
                .legend("View of the tree")
                .image(new Image(context.getResource("style/img/img_tree.png").toExternalForm()))
                .text("In views.yml file add you will see.(If your controller class doesn't extend ActionView, so you can't access the context)", "text-12", "text-bold")
                .code("""
                        !!io.github.gleidsonmt.dashboardfx.core.view.ViewMap
                         views:
                         - {
                           name: myView,
                           title: My View,
                           folder: app,
                           fxml: myView.fxml
                         }
                        """, "yaml")
                .title("Controllers")
                .text("The SimpleView descends a ActionView, a typical controller class has the method initialize, but isn't only the actions you need, when you enter a view.")
                .text("See in code (The same methods are applicative in SimpleView as well).", "text-12", "text-bold")
                .code("""
                        public class MyView extends ActionView {
                            @Override
                            public void OnEnter() { // When you use context.routes().nav("view");
                                                    // then this method is trigger
                            }

                            @Override
                            public void OnExit() {  // When you use context.routes().nav("to another view");
                                                    // then this method is trigger, end close the view
                            }

                            @Override
                            public void onInit(Context context) {   // When this method is called, you can give access
                                                                    // of context object
                            }
                        }
                        """)
                .title("Navigating")
                .title("Routes", "Navigating")
                .text("""
                        Use routes to access views and controllers directly.
                        """)
                .code("""
                        // nav betweeen views
                        context.routes().nav("Your view name");
                        // Putting views in routes
                        context.routes().put(new SimpleView("name", node));
                        // add and nav a simple view
                        context.routes().putAndGo(new SimpleView("name", node)));
                        // Getting a view
                        context.routes().getView(viewName);
                        // Getting a controller from a view
                        context.routes().getView(viewName).getController();
                        // Util to get directly controller class
                        context.controllerOf(viewName);
                        """)

                .title("Popups and wrappers.")
                .text("""
            App ecosystem needs a way to create modals, alerts to talk his users. Getting the context to use an accessor class for creating dialogs,
            snackbars, alerts and side drawers.
            """)
                .separator()
                .title("Snack Bar")
                .text("""
            Creating a snack! I think that snack is more helpful in most cases. You can get the code in block to your clip content.
            Create a snack using the button on the top right of the code box
            """)
                .code("""
                context .createSnackBar()
                        .color(SnackColors.SUCCESS)
                        .icon(new IconContainer(Icons.DONE))
                        .message("Your message")
                        .show();
                """)
                .separator()
                .title("WrapperContainer")
                .options(
                        new ActionOptions(
                                "Open", event -> createDialogPopup()
                        )
                )
                .code("""
                      context.wrapper()
                            .content(new DialogContainer()
                                    .content(new Label("My custom dialog"))
                                    .size(400, 300))
                            .show();
                        """)
                .separator()
                .title("Alerts")
                .text("""
                Default alerts
                """)
                .options(
                        new ActionOptions(
                                "Info", event -> createDialog(AlertType.INFO)
                        ).style("-fx-accent : -info;"),
                        new ActionOptions(
                                "Warning", event -> createDialog(AlertType.WARNING)
                        ).style("-fx-accent : -amber;"),
                        new ActionOptions(
                                "Success", event -> createDialog(AlertType.SUCCESS)
                        ).style("-fx-accent : -mint;"),
                        new ActionOptions(
                                "Danger", event -> createDialog(AlertType.ERROR)
                        ).style("-fx-accent : -grapefruit;"))
                .code(
                        """
                                context.wrapper()
                                .content(
                                    new AlertContainer(context)
                                            .title("Info Alert")
                                            .text(""\"
                                                    Lorem ipsum dolor sit amet, consectetur adipiscing elit.
                                                    Mauris volutpat mauris sit amet rhoncus tempor. Morbi in ex mattis,
                                                    sagittis tellus et, accumsan magna.
                                                    In quis purus sit amet odio fringilla commodo nec ut massa.
                                                    ""\")
                                            .actions(
                                                    new DialogAction(
                                                            "Ok", ButtonType.OK, event -> System.out.println("Button ok pressed!")
                                                    ),
                                                    new DialogAction(
                                                            "Cancel", ButtonType.CANCEL, event -> System.out.println("Button cancel pressed!")
                                                    )
                                            )
                                            .type(_type)
                                            .build())
                                .show();
                                """)
                .separator()
                .title("Drawers")
                .text("Create you drawer left or right using this.")
                .options(
                        new ActionOptions(
                                "Left", event -> createDrawer(HPos.LEFT)
                        ),
                        new ActionOptions(
                                "Right", event -> createDrawer(HPos.RIGHT)
                        )
                )
                .code("""
                    context.wrapper()
                        .drawer()
                        .side(side)
                        .content(
                                new DrawerContainer(new Label("My Custom Drawer."), 250)
                        )
                        .show();
                """)
                .footer(new Author("Gleidson Neves da Silveira",
                        "https://github.com/gleidsonmt")
                )

                ;

//        setAlignment(Pos.CENTER_LEFT);

        build();

    }

    private void createDrawer(HPos side) {

        context.wrapper()
                .drawer()
                .side(side)
                .content(
                        new DrawerContainer(new Label("My Custom Drawer."), 250)
                )
//                .style("-fx-background-color : white;")
                .show();

    }

    public void createDialogPopup() {
        context.wrapper()
                .content(new DialogContainer()
                        .content(new Label("My custom dialog"))
                        .size(400, 300))
                .show();
    }

    private void createDialog(AlertType _type) {
        context.wrapper()
                .content(
                        new AlertContainer(context)
                                .title("Info Alert")
                                .text("""
                            Lorem ipsum dolor sit amet, consectetur adipiscing elit.
                            Mauris volutpat mauris sit amet rhoncus tempor. Morbi in ex mattis,
                            sagittis tellus et, accumsan magna.
                            In quis purus sit amet odio fringilla commodo nec ut massa.
                            """)
                                .actions(
                                        new DialogAction(
                                                "Ok", ButtonType.OK, event -> System.out.println("Button ok pressed!")
                                        ),
                                        new DialogAction(
                                                "Cancel", ButtonType.CANCEL, event -> System.out.println("Button cancel pressed!")
                                        )
                                )
                                .type(_type)
                                .build())
                .show();
    }
}
