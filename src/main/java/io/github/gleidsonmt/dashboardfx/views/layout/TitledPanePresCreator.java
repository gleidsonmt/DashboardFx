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
import io.github.gleidsonmt.dashboardfx.core.controls.icon.IconContainer;
import io.github.gleidsonmt.dashboardfx.core.controls.icon.Icons;
import io.github.gleidsonmt.dashboardfx.core.view.layout.creators.TutorialCreator;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.control.TitledPane;
import javafx.scene.layout.StackPane;
import javafx.scene.text.Text;
import javafx.scene.text.TextFlow;

import java.util.List;

/**
 * @author Gleidson Neves da Silveira | gleidisonmt@gmail.com
 * Create on  01/07/2023
 */
public class TitledPanePresCreator extends TutorialCreator {
    public TitledPanePresCreator(Context context) {
        super(context);

        this
                .title("Intro")
                .text("A TitledPane is a panel with a title that can be opened and closed.\n" +
                        "The panel in a TitledPane can be any Node such as UI controls or groups of nodes added to a layout container.")
                .demonstration(createTitledPane(), """
                        // Constructor
                        TitledPane titledPane = new TitledPane();
                        // Set title
                        titledPane.setText("Titled Pane");
                        // Set content
                        titledPane.setContent(new StackPane());
                        """)
                .title("GNTitledPane")
                .text("Extensible TitledPane with inverse icon.")
                .demonstration(createGNTitledPane(), """
                           // Constructor
                        GNTitledPane titledPane = new GNTitledPane();
                        // Set title
                        titledPane.setText("Titled Pane");
                        // Set content
                        titledPane.setContent(new StackPane());
                        """)
                .title("Links")
                .footer(createDefaultAuthor("TitledPane"))
                .build();
    }

    private Node createGNTitledPane() {
        GNTitledPane titledPane = new GNTitledPane("TitledPane",
                new TextFlow(new Text("You boil the hell out of it. Lorem ipsum dolor sit amet consectetur adipisicing elit. Magnam aut tempora vitae odio inventore fuga aliquam nostrum quod porro. Delectus quia facere id sequi expedita natus.")));
        titledPane.setPrefWidth(300);
        titledPane.getStyleClass().add("gn-titled-pane");

        titledPane.setGraphic(new IconContainer(Icons.ACCOUNT_CIRCLE));

        return titledPane;
    }

    private Node createTitledPane() {
        TitledPane titledPane = new TitledPane("TitledPane",
                new TextFlow(new Text("You boil the hell out of it. Lorem ipsum dolor sit amet consectetur adipisicing elit. Magnam aut tempora vitae odio inventore fuga aliquam nostrum quod porro. Delectus quia facere id sequi expedita natus.")));
        titledPane.setPrefWidth(300);

        return titledPane;
    }
}
