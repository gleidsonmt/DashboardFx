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

package io.github.gleidsonmt.dashboardfx.core.model;

import javafx.scene.Node;
import javafx.scene.control.ButtonBase;
import javafx.scene.control.Labeled;

/**
 * @author Gleidson Neves da Silveira | gleidisonmt@gmail.com
 * Create on  27/05/2023
 */
public class SearchItemAdapter {

    private final SearchItem searchItem = new SearchItem();
    public SearchItemAdapter(Node node) {
        if (node instanceof Labeled labeled) {
            searchItem.nameProperty().bind(labeled.textProperty());
        }
    }
    public static SearchItem adapter(Node node) {
        final SearchItem searchItem = new SearchItem();
        if (node instanceof ButtonBase labeled) {
            searchItem.setName(labeled.getText());
            searchItem.setAction(labeled.getOnAction());
        }
        return searchItem;
    }

    public SearchItem getSearchItem() {
        return searchItem;
    }
}
