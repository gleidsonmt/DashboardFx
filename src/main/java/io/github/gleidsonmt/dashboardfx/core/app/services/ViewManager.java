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

import io.github.gleidsonmt.dashboardfx.core.app.interfaces.View;
import io.github.gleidsonmt.dashboardfx.core.app.interfaces.IViewManager;
import io.github.gleidsonmt.dashboardfx.core.app.view_wrapper.ActionView;
import javafx.scene.Parent;

import java.net.URL;
import java.nio.charset.Charset;
import java.util.HashMap;

/**
 * @author Gleidson Neves da Silveira | gleidisonmt@gmail.com
 * Create on  15/02/2022
 */
public class ViewManager implements IViewManager {

    private final HashMap<String, View> views = new HashMap<>();

    private View current;
    private View previous;

    ViewManager() {

    }

    @Override
    public void add(View view) {
        views.put(view.getName(), view);
    }

    @Override
    public View get(String name)  {
        return views.get(name);
    }

    public View getCur() {
        return current;
    }

    public View getPre() {
        return previous;
    }

    public void setCurrent(View view) {
        this.previous = current;
        this.current = view;
    }

    public void setPrevious(View previous) {
        this.previous = previous;
    }

    @Deprecated
    public boolean contains(String nameView) {
        return views.containsKey(nameView);
    }

    // Pointer methods

    public ActionView controllerOf(String nameView) {
        return get(nameView).getController();
    }

    public ViewComposer composerOf(String name) {
        return get(name).getComposer();
    }

    public URL locationOf(String name) {
        return get(name).getLocation();
    }

    public Charset charsetOf(String name) {
        return get(name).getCharset();
    }

    public Parent rootOf(String name) {
        return get(name).getRoot();
    }

}
