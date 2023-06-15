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

package io.github.gleidsonmt.dashboardfx.core.view.layout.creators;

import javafx.scene.control.Label;
import org.jetbrains.annotations.ApiStatus;

/**
 * This is a custom label used to create a link with the tree.
 * Using presentations creators needs a component that
 * store its position in document and if is related with another
 * title.
 * @see TutorialCreator
 * @author Gleidson Neves da Silveira | gleidisonmt@gmail.com
 * Create on  17/05/2023
 */
@ApiStatus.Internal
@ApiStatus.AvailableSince("1.0")
public class TreeTitle extends Label {

    private double position;
    private TreeTitle related;
    private String index;

    public TreeTitle(String text) {
        super(text);
    }

    /**
     * Get the position in root.
     * This position is used for scroller actions.
     * @return Position in root.
     */
    public double getPosition() {
        return position;
    }

    public void setPosition(double position) {
        this.position = position;
    }

    /**
     * Get the parent in hierarchy in the tree.
     * @return the first parent.
     */
    public TreeTitle getRelated() {
        return related;
    }

    public void setRelated(TreeTitle related) {
        this.related = related;
    }

    /**
     * Get the index in tree.
     * @return The index in tree.
     */
    public String getIndex() {
        return index;
    }

    public void setIndex(String index) {
        this.index = index;
    }

//    @Override
//    public String toString() {
//        return ", parent = " + getRelated();
//    }
}
