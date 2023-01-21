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

import io.github.gleidsonmt.dashboardfx.core.app.services.Context;
import io.github.gleidsonmt.dashboardfx.core.layout.conteiners.creators.Author;
import io.github.gleidsonmt.dashboardfx.core.layout.conteiners.creators.Container;
import io.github.gleidsonmt.dashboardfx.core.layout.conteiners.creators.PresentationCreator;
import io.github.gleidsonmt.dashboardfx.core.layout.conteiners.options.ActionOptions;
import io.github.gleidsonmt.dashboardfx.core.layout.conteiners.options.AlertType;
import io.github.gleidsonmt.dashboardfx.core.layout.conteiners.options.DialogAction;
import io.github.gleidsonmt.dashboardfx.core.layout.conteiners.options.SnackColors;
import io.github.gleidsonmt.gncontrols.material.icon.IconContainer;
import io.github.gleidsonmt.gncontrols.material.icon.Icons;
import javafx.geometry.HPos;
import javafx.scene.Node;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.layout.StackPane;

public class WrappersView extends Container {

    private Context context;

    public WrappersView(String _name, Context context) {
        super(_name);
        this.context = context;

        final ActionOptions customDialog = new ActionOptions();
        customDialog.setName("Around");
        customDialog.setAction(event -> {
            createAroundDilog(customDialog);
        });

        PresentationCreator creator = new PresentationCreator("wrappers_creator", context)
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
        .title("Dialog")
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
                            new Label("Custom Dialog Wrapper"))
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
        this.getChildren().setAll(creator);
    }



    private void createDrawer(HPos side) {

        context.getWrapper()
                .getDrawer()
                .side(side)
                .content(new StackPane(new Label("My Custom Drawer.")))
                .style("-fx-background-color : white;")
                .show();
    }

    public void createDialogPopup() {
        context.getWrapper()
                .getDialog()
                .size(350, 300)
                .content(
                    new StackPane(
                        new Label("Custom Dialog Wrapper"))
                    )
                .show();
    }

    public void createAroundDilog(Node node) {
        context.getWrapper()
                .getDialog()
                .content(
                        new StackPane(
                                new Label("Custom Dialog Wrapper"))
                )
                .show();
    }

    private void createSnack() {
        context.root()
                .createSnackBar()
                .color(SnackColors.SUCCESS)
                .icon(new IconContainer(Icons.DONE))
                .message("Your message")
                .show();
    }

    private void createDialog(AlertType _type) {
        context.getWrapper()
                .getAlert()
                .title("Info Alert")
                .text("""
                        Lorem ipsum dolor sit amet, consectetur adipiscing elit.
                        Mauris volutpat mauris sit amet rhoncus tempor. Morbi in ex mattis,
                        sagittis tellus et, accumsan magna.
                        In quis purus sit amet odio fringilla commodo nec ut massa.
                    """
                )
                .actions(
                    new DialogAction(
                            "Ok", ButtonType.OK, event -> System.out.println("Button ok pressed!")
                    ),
                    new DialogAction(
                        "Cancel", ButtonType.CANCEL, event -> System.out.println("Button cancel pressed!")
                    )
                ).type(_type)
                .show();
    }

}

