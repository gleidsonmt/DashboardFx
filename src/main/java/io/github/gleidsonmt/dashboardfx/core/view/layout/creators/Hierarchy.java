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

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author Gleidson Neves da Silveira | gleidisonmt@gmail.com
 * Version 0.0.1
 * Create on  14/05/2023
 */
public class Hierarchy extends Item {
    private final List<Item> items = new ArrayList<>();

    public Hierarchy(String name, Item... items) {
        this(name, null, items);
    }

    public Hierarchy(String name, String text, Item... items) {
        super(name, text);
        this.items.addAll(Arrays.asList(items));
    }



    public List<Item> getItems() {
        return items;
    }


    @Override
    public String toString() {
        return getName() + getItems();
    }
}
