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
import io.github.gleidsonmt.dashboardfx.core.view.layout.creators.TutorialCreator;
import javafx.scene.control.Label;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.layout.VBox;

import java.util.List;

/**
 * @author Gleidson Neves da Silveira | gleidisonmt@gmail.com
 * Create on  30/06/2023
 */
public class TabPanePresCreator extends TutorialCreator {
    public TabPanePresCreator(Context context) {
        super(context);
        this
                .title("Intro")
                .text("A control that allows switching between a group of Tabs. Only one tab is visible at a time. Tabs are added to the TabPane by using the getTabs().")
                .demonstration(List.of(
                            createTab()
//                            createTabIcons()
                        ),
                        """
                        // Constructor
                        TabPane pane = new TabPane();
                        // tabs
                        Tab one = new Tab("Tab 01");
                        Tab two = new Tab("Tab 02");
                        // add tabs
                        pane.getTabs().addAll(one, two);
                        // setting tabs
                        one.setContent(new VBox(new Label("Tab one")));
                        two.setContent(new VBox(new Label("Tab two")));
                        """)
                .title("Links")
                .footer(createDefaultAuthor("TabPane"))
                .build();
    }

    private TabPane createTab() {
        TabPane pane = new TabPane();
        Tab one = new Tab("Tab 01");
        Tab two = new Tab("Tab 02");
        pane.getTabs().addAll(one, two);
        one.setContent(new VBox(new Label("Tab one")));
        two.setContent(new VBox(new Label("Tab two")));
        return pane;
    }

}
