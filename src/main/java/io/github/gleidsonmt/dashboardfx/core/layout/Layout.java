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

package io.github.gleidsonmt.dashboardfx.core.layout;

import io.github.gleidsonmt.dashboardfx.core.app.interfaces.ILayout;
import io.github.gleidsonmt.dashboardfx.core.app.interfaces.IView;
import javafx.scene.Node;
import javafx.scene.layout.BorderPane;

/**
 * @author Gleidson Neves da Silveira | gleidisonmt@gmail.com
 * Create on  04/10/2022
 */
public final class Layout extends BorderPane implements ILayout {

    public Layout() {

        setId("layout");


    }

    @Override
    public void setDrawer(IView iView) {

    }

    @Override
    public void setAside(IView iView) {

    }

    @Override
    public void setNav(IView iView) {

    }

    @Override
    public void setFooter(IView iView) {

    }

    @Override
    public void setBody(Node iView) {
        setCenter(iView);
    }
}
