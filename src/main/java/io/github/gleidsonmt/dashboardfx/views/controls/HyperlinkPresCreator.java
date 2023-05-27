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
import io.github.gleidsonmt.dashboardfx.core.view.layout.creators.TutorialCreator;
import javafx.scene.control.Hyperlink;

/**
 * @author Gleidson Neves da Silveira | gleidisonmt@gmail.com
 * Version 0.0.1
 * Create on  22/01/2023
 */
public class HyperlinkPresCreator extends TutorialCreator {

    public HyperlinkPresCreator(Context context) {
        super(context);

        this    .title("Hyperlink")
                .text("An HTML like label which can be a graphic and/or text which responds to rollovers and clicks. When a hyperlink is clicked/pressed isVisited() becomes true. A Hyperlink behaves just like a Button. When a hyperlink is pressed and released a ActionEvent is sent, and your application can perform some action based on this event.")
                .demonstration(new Hyperlink("Hyperlink"),
                        """
                                // Constructor
                                Hyperlink link = new Hyperlink("Hyperlink");
                                """,
                        """
                                <!-- Building -->
                                <Hyperlink text="My Hyperlink">
                                """)
                .title("Links")
                .footer(createDefaultControl())
                .build();
    }

}
