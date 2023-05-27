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
import io.github.gleidsonmt.dashboardfx.model.Data;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

import java.util.Arrays;
import java.util.List;

/**
 * @author Gleidson Neves da Silveira | gleidisonmt@gmail.com
 * Create on  21/01/2023
 */
public class ButtonPresCreator extends TutorialCreator {

    public ButtonPresCreator(Context context) {
        super(context);
        this    .title("Button")
                .text("""
                        A simple button control. The button control can contain text and/or a graphic.
                        """)
                .demonstration(List.of(
                            createButton("Default"),
                            createCancel("Cancel"),
                            createBtnFlat("Flat"),
                            createIconized("Iconized")

                        ),
                        """
                        // Default
                        Button button = new Button("Button");
                        // cancel
                        button.setCancelButton(true);
                        // Flat
                        buttonFlat.getStyleClass("btn-flat");
                        // Icon
                        button.setGraphic(new IconContainer(Icons.ANALYTICS));
                       
                        """,
                        """
                                <!-- Default -->
                                <Button text="button"></Button>
                                <!-- Main button/gave action from enter -->
                                <Button text="button" defaultButton="true"></Button>
                                <!-- Cancel button/gave action using scape -->
                                <Button text="button" cancelButton="true"></Button>
                                <!-- Only add inline styleClass -->
                                <Button text="button" styleClass="btn-flat""></Button>
                                <!-- Using a graphic -->
                                <Button text="button">
                                    <graphic>
                                        <IconContainer icon="DISCOUNT">
                                    </graphic> 
                                </Button>
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
                            <GNButton text=\"button\"/>
                            <!-- Icon button -->
                            <GNIconButton icon="DISCOUNT"/>
                            """
                )
                .title("Attributes", "Designed Buttons")
                .table(
                    new TableCreator<Data>(context)
                            .column("name")
                            .column("name")
                            .data(
                                    new Data("-gn-circle-fill", "#fff")
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

    public Button createBtnFlat(String title) {
        Button button = new Button(title);
        button.getStyleClass().add("btn-flat");
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
}
