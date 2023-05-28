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
import io.github.gleidsonmt.dashboardfx.core.controls.GNBadge;
import io.github.gleidsonmt.dashboardfx.core.controls.icon.Icons;
import io.github.gleidsonmt.dashboardfx.core.view.layout.creators.TutorialCreator;
import javafx.scene.control.Label;

import java.util.List;

/**
 * @author Gleidson Neves da Silveira | gleidisonmt@gmail.com
 * Create on  19/01/2023
 */
public class LabelPresCreator extends TutorialCreator {

    public LabelPresCreator(Context context) {
        super( context);

        this    .title("Starting")
                .text("""
                Label is a non-editable text control. 
                A Label is useful for displaying text that is required to fit within a specific space, and thus may need to use an ellipsis or truncation to size the string to fit. 
                Labels also are useful in that they can have mnemonics which, if used, will send focus to the Control listed as the target of the labelFor property.
                """)
                .demonstration(
                        new Label("Label"),
                        """
                        // Constructing label
                        Label label = new Label("Label");
                        """,
                        """
                        <!-- Building -->
                        <Label text="Label"/>
                        """)
                .title("""
                        GNBadge
                        """)
                .text("Custom icon label based in icon button.")
                .demonstration(
                        List.of(
                            createBadge("", Icons.BADGE),
                            createBadge("bd-danger", Icons.DELETE),
                            createBadge("bd-info", Icons.ANALYTICS)
                        ),
                        """
                               // Constructing GNBadge
                               GNBadge badge = new GNBadge(Icons.NOTIFICATIONS);
                               // Setting number of notifications
                               badge.setNumberOfNotifications(20);
                               // Danger
                               badge.getStyleClass().add("bd-danger");
                               // Info
                               badge.getStyleClass().add("bd-info");
                                """,
                        """
                               <!-- Building -->
                              <GNBadge numberOfNotifications="2"/>
                              <!-- Using item -->
                              <GNBadge numberOfNotifications="2" icon="ACCOUNT_CIRCLE"/>
                              <!-- Class -->
                              <GNBadge styleClass="bd-danger" icon="ACCOUNT_CIRCLE"/>
                              <!-- Class -->
                              <GNBadge styleClass="bd-info" icon="ACCOUNT_CIRCLE"/>
                                """
                )
                .title("Links")
                .footer(createDefaultControl())
                .build();

    }

    private GNBadge createBadge(String clazz, Icons icons) {
        GNBadge badge = new GNBadge(icons);
        badge.getStyleClass().add(clazz);
        return badge;
    }
}
