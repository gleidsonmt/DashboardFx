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
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyCodeCombination;
import javafx.scene.input.KeyCombination;

import static javafx.scene.input.KeyCode.CONTROL;

/**
 * @author Gleidson Neves da Silveira | gleidisonmt@gmail.com
 * Create on  21/06/2023
 */
public class MenuBarPresCreator extends TutorialCreator {
    public MenuBarPresCreator(Context context) {
        super(context);
        this
                .title("Intro")
                .text("A MenuBar control traditionally is placed at the very top of the user interface, and embedded within it are Menus. To add a menu to a menubar, you add it to the menus ObservableList. By default, for each menu added to the menubar, it will be represented as a button with the Menu text value displayed.")
                .demonstration(createMenuBar())
                .title("Links")
                .footer(createDefaultAuthor("MenuBar"))
                .build();
    }

    private Node createMenuBar() {
        MenuBar menuBar = new MenuBar();
        Menu file = new Menu("File");
        MenuItem _new = new MenuItem("New");
        _new.setMnemonicParsing(true);
        _new.setAccelerator(
                new KeyCodeCombination(KeyCode.N, KeyCombination.CONTROL_DOWN));

        MenuItem template = new MenuItem("New from Template");
        SeparatorMenuItem sep1 = new SeparatorMenuItem();
        MenuItem open = new MenuItem("Open...");
        open.setAccelerator(new KeyCodeCombination(KeyCode.O, KeyCombination.CONTROL_DOWN));

        Menu openRecent = new Menu("Open Recent");
        MenuItem item1 = new MenuItem("dot.fxml");
        MenuItem item2 = new MenuItem("dot2.fxml");
        MenuItem item3 = new MenuItem("dot3.fxml");
        MenuItem item4 = new MenuItem("dot4.fxml");
        openRecent.getItems().addAll(item1, item2, item3, item4);
        file.getItems().addAll(_new, template, sep1, open, openRecent);

        Menu edit = new Menu("Edit");
        RadioMenuItem radioMenuItem = new RadioMenuItem("RadioMenuItem");
        CheckMenuItem checkMenuItem = new CheckMenuItem("CheckMenuItem");
        edit.getItems().addAll(radioMenuItem, checkMenuItem);
        Menu help = new Menu("Help");

        menuBar.getMenus().addAll(file, edit, help);
        return menuBar;
    }
}
