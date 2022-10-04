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

package io.github.gleidsonmt.dashboardfx.core.app.interfaces;

import io.github.gleidsonmt.dashboardfx.core.app.exceptions.NavigationException;

/**
 * @author Gleidson Neves da Silveira | gleidisonmt@gmail.com
 * Create on  20/08/2022
 */
public interface IRotes {

    void setContent(String view) throws NavigationException;

    void setView(String view) throws NavigationException;

    void addView(IView iView);

    void goHome();

    IView getView(String view);

    IView getCurrent();

    IView getPrevious();

    IView load(String folder, String title, String name);


    // void loadView(String path) // para implementar se quiser q a view seja carregad na hora

}
