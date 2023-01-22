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
import io.github.gleidsonmt.dashboardfx.core.controls.GNBadge;
import io.github.gleidsonmt.dashboardfx.core.layout.conteiners.creators.TutorialCreator;
import io.github.gleidsonmt.gncontrols.material.icon.Icons;
import javafx.scene.control.Label;

/**
 * @author Gleidson Neves da Silveira | gleidisonmt@gmail.com
 * Version 0.0.1
 * Create on  19/01/2023
 */
public class LabelPresCreator extends TutorialCreator {

    public LabelPresCreator(String name, Context context) {
        super(name, context);
        this    .title("Label")
                .text("""
                Label is a non-editable text control. 
                A Label is useful for displaying text that is required to fit within a specific space, and thus may need to use an ellipsis or truncation to size the string to fit. 
                Labels also are useful in that they can have mnemonics which, if used, will send focus to the Control listed as the target of the labelFor property.
                """)
                .multCode(
                        new Label("Label"),
                        """
                        import javafx.scene.control.Label;
                        //...
                        Label label = new Label("Label");
                        """,
                        """
                        <?import javafx.scene.control.Label?>
                        //...
                        <Label text="Label"/>
                        """)
                .title("""
                        GNBadge
                        """)
                .text("Custom icon label based in icon button.")
                .multCode(
                        new GNBadge(Icons.NOTIFICATIONS),
                        """
                               import io.github.gleidsonmt.dashboardfx.core.controls.GNBadge;
                               //...
                               GNBadge badge = new GNBadge(Icons.NOTIFICATIONS);
                                """,
                        """
                               <?import io.github.gleidsonmt.dashboardfx.core.controls.GNBadge?>;
                               //...
                               <GNBadge />
                                """
                )
        ;
        build();

    }
}
