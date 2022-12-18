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

package io.github.gleidsonmt.dashboardfx.core.layout.conteiners.layout;

import io.github.gleidsonmt.dashboardfx.core.layout.conteiners.interfaces.AbsoluteWrapperContainer;
import javafx.geometry.Pos;

public class FloatContainer implements AbsoluteWrapperContainer {

    private Pos pos;

    @Override
    public FloatContainer pos(Pos _pos) {
        this.pos = _pos;
        return this;
    }

    @Override
    public void show() {

        if (pos == null) {
            pos = Pos.CENTER;
        }

    }
}
