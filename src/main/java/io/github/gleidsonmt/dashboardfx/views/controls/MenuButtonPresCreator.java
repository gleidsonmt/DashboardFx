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
import javafx.geometry.Side;
import javafx.scene.Node;
import javafx.scene.control.*;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Gleidson Neves da Silveira | gleidisonmt@gmail.com
 * Create on  24/05/2023
 */
public class MenuButtonPresCreator extends TutorialCreator {
    public MenuButtonPresCreator(Context context) {
        super(context);
        this
                .title("Intro")
                .text("""
                        MenuButtonPresCreator is a button which, when clicked or pressed, will show a ContextMenu. A MenuButtonPresCreator shares a very similar API to the Menu control, insofar that you set the items that should be shown in the items ObservableList, and there is a text property to specify the label shown within the MenuButtonPresCreator.
                        """)
                .demonstration(createMenuItems(), """
                        // Constructor
                        MenuButton menuButton = new MenuButton();
                        menuButton.setText("MenuButton");
                        // Set items
                        menuButton.getItems().add(new MenuItem("Item"));
                        """, """
                        <MenuButton text="MenuButton">
                          <items>
                            <MenuItem text="Action 1" />
                            <MenuItem text="Action 2" />
                          </items>
                        </MenuButton>
                        """, """
                        .menu-button .label .text {
                        }
                        
                        .menu-button .arrow-button .arrow {
                        }
                        
                        """)
                .title("SplitMenuButton")
                .text("The SplitMenuButton, like the MenuButtonPresCreator is closely associated with the concept of selecting a MenuItem from a menu. Unlike MenuButtonPresCreator, the SplitMenuButton is broken into two pieces, the \"action\" area and the \"menu open\" area.")
                .demonstration(createSplitMenuItems(), """
                        // Constructor
                        SplitMenuButton menuButton = new SplitMenuButton();
                        menuButton.setText("MenuButton");
                        // Set items
                        menuButton.getItems().add(new MenuItem("Item"));
                        """, """
                        <SplitMenuButton text="SplitMenuButton">
                          <items>
                            <MenuItem text="Action 1" />
                            <MenuItem text="Action 2" />
                          </items>
                        </SplitMenuButton>
                        """, """
                        .split-menu-button .label .text {
                        }
                        
                        .split-menu-button .arrow-button .arrow {
                        }
                        """)
                .title("Links")
                .footer(
                        createDefaultAuthor("MenuButtonPresCreator"),
                        createDefaultAuthor("SplitMenuButton")
                )
                .build();
    }

    private void createMenuItems(MenuButton menuButton) {
        for (int i = 0; i < 3; i++) {
            menuButton.getItems().add(new MenuItem(i + " Item"));
        }
    }

    private List<Node> createMenuItems() {
        List<Node> list = new ArrayList<>();
        for (Side side : Side.values()) {
            MenuButton menuButton = new MenuButton();
            menuButton.setText(side.name());
            list.add(menuButton);
            menuButton.setPopupSide(side);
            createMenuItems(menuButton);
        }
        return list;
    }
    private List<Node> createSplitMenuItems() {
        List<Node> list = new ArrayList<>();
        for (Side side : Side.values()) {
            SplitMenuButton menuButton = new SplitMenuButton();
            menuButton.setText(side.name());
            list.add(menuButton);
            menuButton.setPopupSide(side);
            createMenuItems(menuButton);
        }
        return list;
    }
}
