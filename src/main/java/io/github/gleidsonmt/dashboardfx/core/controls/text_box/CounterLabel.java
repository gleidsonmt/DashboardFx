/*
 *    Copyright (C) Gleidson Neves da Silveira
 *
 *    This program is free software: you can redistribute it and/or modify
 *    it under the terms of the GNU General Public License as published by
 *    the Free Software Foundation, either version 3 of the License, or
 *    (at your option) any later version.
 *
 *    This program is distributed in the hope that it will be useful,
 *    but WITHOUT ANY WARRANTY; without even the implied warranty of
 *    MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *    GNU General Public License for more details.
 *
 *    You should have received a copy of the GNU General Public License
 *    along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */

package io.github.gleidsonmt.dashboardfx.core.controls.text_box;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.scene.control.Label;

/**
 * @author Gleidson Neves da Silveira | gleidisonmt@gmail.com
 * Create on  22/09/2022
 */
public class CounterLabel extends Label {

    private final IntegerProperty act = new SimpleIntegerProperty(0);
    private final IntegerProperty max = new SimpleIntegerProperty();

    public CounterLabel() {
        act.addListener((observable, oldValue, newValue) -> update());
        max.addListener((observable, oldValue, newValue) -> update());
    }

    private void update() {
        if (max.intValue() == -1) {
            setText(act.get() + "/..");
        } else setText(act.get() + "/" + max.get());
    }

    public int getCount() {
        return act.get();
    }

    public IntegerProperty actProperty() {
        return act;
    }

    public void setCount(int act) {
        this.act.set(act);
    }

    public int getMax() {
        return max.get();
    }

    public IntegerProperty maxProperty() {
        return max;
    }

    public void setMax(int max) {
        this.max.set(max);
    }
}
