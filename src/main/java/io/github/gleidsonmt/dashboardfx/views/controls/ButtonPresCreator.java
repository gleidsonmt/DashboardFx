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

import io.github.gleidsonmt.dashboardfx.core.app.services.Context;
import io.github.gleidsonmt.dashboardfx.core.layout.conteiners.creators.TutorialCreator;
import io.github.gleidsonmt.gncontrols.controls.GNButton;
import javafx.scene.control.Button;

import java.util.List;

/**
 * @author Gleidson Neves da Silveira | gleidisonmt@gmail.com
 * Version 0.0.1
 * Create on  21/01/2023
 */
public class ButtonPresCreator extends TutorialCreator {
    public ButtonPresCreator(String name, Context context) {
        super(name, context);
        this.title("Button")
                .text("""
                        A simple button control. The button control can contain text and/or a graphic. A button control has three different modes
                                                
                            Normal: A normal push button.
                            Default: The default button is rendered differently to make it apparent to users that it should be the default choice should they be unclear as to what should be selected. The behavior of the default button differs depending on the platform in which it is presented:
                                Windows / Linux: A default Button receives ENTER key presses when it has focus. When the default button does not have focus, and focus is on another Button control, the ENTER key press will be received by the other, non-default Button. When focus is elsewhere in the user interface, and not on any Button, the ENTER key press will be received by the default button, if one is specified, and if no other node in the scene consumes it first.
                                Mac OS X: A default Button is the only Button in the user interface that responds to the ENTER key press. If focus is on another non-default Button and ENTER is pressed, the event is only received by the default Button. On macOS, the only way to fire a non-default Button is through the SPACE key.
                            Cancel: A Cancel Button is the button that receives a keyboard VK_ESC press, if no other node in the scene consumes it.
                                                
                        When a button is pressed and released a ActionEvent is sent. Your application can perform some action based on this event by implementing an EventHandler to process the ActionEvent. Buttons can also respond to mouse events by implementing an EventHandler to process the MouseEvent
                                                
                        MnemonicParsing is enabled by default for Button.
                        """)
                .multCode(List.of(
                            createButton("Button"),
                            createCancel("Cancel"),
                            createBtnFlat("Flat"),
                            createGNButton("GNButton")
                        ),
                        """
                        // Default
                        Button button = new Button("Button");
                        // cancel
                        button.setCancelButton(true);
                        // Flat
                        buttonFlat.getStyleClass("btn-flat");
                        // GNButton
                        GNButton gnButton = new GNButton();
                        """,
                        """
                        """)
        ;
        build();


    }

    public Button createButton(String title) {
        return new Button(title);
    }

    public GNButton createGNButton(String title) {
        return new GNButton(title);
    }

    public Button createBtnFlat(String title) {
        Button button = new Button(title);
        button.getStyleClass().add("btn-flat");
        return button;
    }

    public Button createCancel(String title) {
        Button button = new Button(title);
        button.setCancelButton(true);
        return button;
    }
}
