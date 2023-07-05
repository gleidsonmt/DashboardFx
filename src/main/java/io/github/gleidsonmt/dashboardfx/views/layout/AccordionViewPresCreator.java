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

package io.github.gleidsonmt.dashboardfx.views.layout;

import io.github.gleidsonmt.dashboardfx.core.Context;
import io.github.gleidsonmt.dashboardfx.core.controls.GNTitledPane;
import io.github.gleidsonmt.dashboardfx.core.view.layout.creators.TutorialCreator;
import javafx.scene.Node;
import javafx.scene.control.Accordion;
import javafx.scene.control.Label;
import javafx.scene.control.TitledPane;

import java.util.List;

/**
 * @author Gleidson Neves da Silveira | gleidisonmt@gmail.com
 * Create on  03/07/2023
 */
public class AccordionViewPresCreator extends TutorialCreator {
    public AccordionViewPresCreator(Context context) {
        super(context);
        this
                .title("Intro")
                .text("An accordion is a group of TitlePanes. Only one TitledPane can be opened at a time.\n" +
                        "The TitledPane content in an accordion can be any Node such as UI controls or groups of nodes added to a layout container.")
                .demonstration(
                        List.of(
                                createAccordion(),
                                createCustomAccordion()
                        ), """
                                // Constructor
                                Accordion accordion = new Accordion();
                                // Adding panes
                                for (int i = 1; i < 6; i++ ) {
                                    accordion.getPanes().add(
                                            new TitledPane("Default Titled " + i, new Label("Label " + i))
                                    );
                                }
                                """)
                .title("Links")
                .footer(createDefaultAuthor("Accordion"))
                .build();
    }

    private Node createAccordion() {
        Accordion accordion = new Accordion();
        accordion.setMinWidth(100);
        for (int i = 1; i < 6; i++ ) {
            accordion.getPanes().add(
                    new TitledPane("Default Titled " + i, new Label("Label " + i))
            );
        }
        return accordion;
    }

    private Node createCustomAccordion() {
        Accordion accordion = new Accordion();
        accordion.setMinWidth(100);
        for (int i = 1; i < 6; i++ ) {
            accordion.getPanes().add(
                    new GNTitledPane("Custom Titled " + i, new Label("Label " + i))
            );
        }
        return accordion;
    }
}
