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
import io.github.gleidsonmt.dashboardfx.core.view.layout.*;
import io.github.gleidsonmt.dashboardfx.core.view.layout.creators.Author;
import io.github.gleidsonmt.dashboardfx.core.view.layout.creators.PresentationCreator;
import io.github.gleidsonmt.dashboardfx.core.view.layout.creators.TutorialCreator;
import io.github.gleidsonmt.dashboardfx.core.view.layout.options.ActionOptions;
import io.github.gleidsonmt.dashboardfx.core.view.layout.options.AlertType;
import io.github.gleidsonmt.dashboardfx.core.view.layout.options.DialogAction;
import javafx.geometry.HPos;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.layout.StackPane;

public class WrappersView extends StackPane {

    private final Context context;

    public WrappersView(Context context) {
        this.context = context;

        final ActionOptions customDialog = new ActionOptions();
        customDialog.setName("Around");
        customDialog.setAction(event -> {
            createAroundDilog(customDialog);
        });

//        TutorialUnderstanding h = new TutorialUnderstanding(context);
//        PresentationCreator creator = new PresentationCreator(context)
        TutorialCreator creator = (TutorialCreator) new TutorialCreator(context)
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
                context.getDecorator()
                        .getRoot()
                        .createSnackBar()
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
                context.getWrapper()
                    .getDialog()
                    .content(
                        new StackPane(
                            new Label("Custom WrapperContainer RWrapper"))
                        )
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
                      context.getWrapper()
                            .getAlert()
                            .title("Info Alert")
                            .text(\"\"\"
                                    Lorem ipsum dolor sit amet, consectetur adipiscing elit.
                                    Mauris volutpat mauris sit amet rhoncus tempor. Morbi in ex mattis,
                                    sagittis tellus et, accumsan magna.
                                    In quis purus sit amet odio fringilla commodo nec ut massa.\s
                                \"\"\"
                            )
                            .actions(
                                new DialogAction(
                                        "Ok", ButtonType.OK, event -> System.out.println("Button ok pressed!")
                                ),
                                new DialogAction(
                                    "Cancel", ButtonType.CANCEL, event -> System.out.println("Button cancel pressed!")
                                )
                            )
                            .type(_type)
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
                context.getWrapper()
                        .getDrawer()
                        .side(side)
                        .content(new StackPane(new Label("My Custom Drawer.")))
                        .style("-fx-background-color : white;")
                        .show();
                """)
        .footer(new Author("Gleidson Neves da Silveira",
                "https://github.com/gleidsonmt")
        );
        creator.build();
        this.getChildren().setAll(creator.getRoot());
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

    public void createAroundDilog(Node node) {
//        context.wrapper()
//                .content(
//                        new DialogContainer(
//                                new Label("Fuck RWrapper"))
//                )
//                .show();
    }

    private void createSnack() {
//        context.root()
//                .createSnackBar()
//                .color(SnackColors.SUCCESS)
//                .icon(new IconContainer(Icons.DONE))
//                .message("Your message")
//                .show();
    }

    private void createDialog(AlertType _type) {
        context.wrapper()
//                .getAlert()
//                .getFlow()
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

