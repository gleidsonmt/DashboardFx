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

package io.github.gleidsonmt.dashboardfx.core.app.services;

import io.github.gleidsonmt.dashboardfx.core.app.interfaces.IView;
import io.github.gleidsonmt.dashboardfx.core.app.interfaces.IViewManager;

import java.util.HashMap;

/**
 * @author Gleidson Neves da Silveira | gleidisonmt@gmail.com
 * Create on  15/02/2022
 */
public class ViewManager implements IViewManager {

    private final HashMap<String, IView> views = new HashMap<>();

    private IView current;
    private IView previous;

    @Override
    public void add(IView view) {
        views.put(view.getName(), view);
    }

    @Override
    public IView get(String name)  {
        return views.get(name);
    }

    public IView getCurrent() {
        return current;
    }

    public IView getPrevious() {
        return previous;
    }

    public void setCurrent(IView view) {
        this.previous = current;
        this.current = view;
    }

    public void setPrevious(IView previous) {
        this.previous = previous;
    }

    public boolean contains(String nameView) {
        return views.containsKey(nameView);
    }
}
