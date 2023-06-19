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

package io.github.gleidsonmt.dashboardfx.views.controls;

import io.github.gleidsonmt.dashboardfx.core.Context;
import io.github.gleidsonmt.dashboardfx.core.controls.GNButton;
import io.github.gleidsonmt.dashboardfx.core.controls.GNIconButton;
import io.github.gleidsonmt.dashboardfx.core.controls.icon.IconContainer;
import io.github.gleidsonmt.dashboardfx.core.controls.icon.Icons;
import io.github.gleidsonmt.dashboardfx.core.view.layout.creators.TableCreator;
import io.github.gleidsonmt.dashboardfx.core.view.layout.creators.TutorialCreator;
import io.github.gleidsonmt.dashboardfx.core.model.Data;
import javafx.scene.control.Button;

import java.util.List;

/**
 * @author Gleidson Neves da Silveira | gleidisonmt@gmail.com
 * Create on  21/01/2023
 */
public class ButtonPresCreator extends TutorialCreator {

    public ButtonPresCreator(Context context) {
        super(context);
        this    .title("Intro")
                .text("""
                        A simple button control. The button control can contain text and/or a graphic.
                        """)
                .demonstration(List.of(
                                createButton("Default"),
                                createCancel("Cancel"),
                                createDisable("Disable"),
                                createIconized("Iconized"),
                                createClassButton("Flat", "btn-flat"),
                                createClassButton("Warning", "btn-warning"),
                                createClassButton("Success", "btn-success"),
                                createClassButton("Danger", "btn-danger")
                        ),
                        """
                        // Default/Constructor
                        Button button = new Button("Button");
                        // cancel
                        button.setCancelButton(true);
                        // Icon
                        button.setGraphic(new IconContainer(Icons.ANALYTICS));
                        // Flat
                        buttonFlat.getStyleClass("btn-flat");
                        // danger
                        buttonFlat.getStyleClass("btn-danger");
                        """,
                        """
                        <!-- Default -->
                        <Button text="button"/>
                        <!-- Main button/gave action from enter -->
                        <Button text="button" defaultButton="true"/>
                        <!-- Cancel button/gave action using scape -->
                        <Button text="button" cancelButton="true"/>
                        <!-- Only add inline styleClass -->
                        <Button text="button" styleClass="btn-flat"/>
                        <!-- Using a graphic -->
                        <Button text="button">
                        <graphic>
                           <IconContainer icon="DISCOUNT"/>
                        </graphic>
                        </Button>
                                """,
                        """
                        .button {
                            /* You need a different color.. but you don't want to change all states */
                            -fx-accent: [<paint>];
                        }
                        
                        .button [<graphic>] {
                        }
                                """)
                .title("States")
                .text("""
                        • Normal: A normal push button.
                        
                        • Default: The default button is rendered differently to make it apparent to users that it should be the default choice should they be unclear as to what should be selected. The behavior of the default button differs depending on the platform in which it is presented:
                        
                        • Windows / Linux: A default Button receives ENTER key presses when it has focus. When the default button does not have focus, and focus is on another Button control, the ENTER key press will be received by the other, non-default Button. When focus is elsewhere in the user interface, and not on any Button, the ENTER key press will be received by the default button, if one is specified, and if no other node in the scene consumes it first.
                        
                        • Mac OS X: A default Button is the only Button in the user interface that responds to the ENTER key press. If focus is on another non-default Button and ENTER is pressed, the event is only received by the default Button. On macOS, the only way to fire a non-default Button is through the SPACE key.
                        
                        • Cancel: A Cancel Button is the button that receives a keyboard VK_ESC press, if no other node in the scene consumes it.
                        """, "-fx-padding: 20px 10px 20px 10px;")
                .title("Designed Buttons")
                .text("Extensible button with an press effect action.")
                .demonstration(List.of(
                        createGNButton("GNButton"),
                        createFloatingButton()
                        ),
                        """
                            // GNButton
                            GNButton gnButton = new GNButton();
                            // GNbutton with icon
                            GNButton gnButton = new GNIconButton();
                                """,
                            """
                            <GNButton text="button"/>
                            <!-- Icon button -->
                            <GNIconButton icon="DISCOUNT"/>
                            """,
                        """
                            .gn-button {
                      
                            }
                            
                            .gn-icon-button {
                            }
                            """
                )
                .title("Custom CSS", "Designed Buttons")
                .table(
                    new TableCreator<Data>(context)
                            .columns("name", "value")
                            .data(
                            new Data("-gn-circle-fill", "[<paint>]")
                            )
                            .build()
                )
                .title("Links")
                .footer(createDefaultControl());
        build();

    }

    public Button createButton(String title) {
        return new Button(title);
    }

    public GNButton createGNButton(String title) {
        return new GNButton(title);
    }

    public GNIconButton createFloatingButton() {
        GNIconButton button = new GNIconButton(Icons.ADD);
        button.getStyleClass().addAll("btn-float", "depth-1");
//        button.setGraphic(new IconContainer(Icons.ADD));
        return button;
    }

    public Button createClassButton(String title, String style) {
        Button button = new Button(title);
        button.getStyleClass().add(style);
        return button;
    }

    public Button createIconized(String title) {
        Button button = new Button(title);
        button.setGraphic(new IconContainer(Icons.ANALYTICS));
        return button;
    }

    public Button createCancel(String title) {
        Button button = new Button(title);
        button.setCancelButton(true);
        return button;
    }

    public Button createDisable(String title) {
        Button button = new Button(title);
        button.setDisable(true);
        return button;
    }
}
