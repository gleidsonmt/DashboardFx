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
import javafx.scene.control.TreeCell;
import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeView;

/**
 * @author Gleidson Neves da Silveira | gleidisonmt@gmail.com
 * Create on  24/05/2023
 */
public class TreeViewPresCreator extends TutorialCreator {
    public TreeViewPresCreator(Context context) {
        super(context);
        this
                .title("TreeView")
                .demonstration(
                        createTreeView(), "", ""
                )
                .title("Links")
                .footer(createDefaultControl())
                .build();
    }

    private TreeView<String> createTreeView() {
        TreeItem<String> root = new TreeItem<>("Extra Button");
        root.setExpanded(true);
        TreeItem<String> option = new TreeItem<>("Option");

        root.getChildren().addAll(
                new TreeItem<>("Attributes")
        );
        option.getChildren().addAll(
                new TreeItem<>("Item 01"),
                new TreeItem<>("Item 02"),
                new TreeItem<>("Item 03")
        );
        TreeView<String> treeView = new TreeView<>(root);
        treeView.setEditable(false);
        return treeView;
    }
}
