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

package io.github.gleidsonmt.dashboardfx.core.layout.conteiners.creators;

import io.github.gleidsonmt.dashboardfx.core.app.services.Context;
import io.github.gleidsonmt.dashboardfx.core.app.interfaces.View;
import io.github.gleidsonmt.dashboardfx.core.app.services.ViewComposer;
import io.github.gleidsonmt.dashboardfx.core.app.view_wrapper.ActionView;
import javafx.scene.Parent;
import javafx.scene.layout.StackPane;

import java.net.URL;
import java.nio.charset.Charset;

public class Container extends StackPane implements ActionView, View {

    private String name;
//    Context context;


    public Container(String name) {
        this.name = name;
    }

    @Override
    public void onEnter(Context context) {
    }

    @Override
    public void onExit(Context context) {
    }

    @Override
    public void onInit(Context context) {

    }

    @Override
    public String getName() {
        return this.name;
    }

    private ViewComposer composer;

    @Override
    public ViewComposer getComposer() {
        if (composer == null) {
            composer = new ViewComposer();
            composer.setTitle(
                    getName().substring(0,1).toUpperCase() +
                            getName().substring(1)
            );
        }
        return composer;
    }

    @Override
    public ActionView getController() {
        return this;
    }

    @Override
    public Parent getRoot() {
        return this;
    }

    @Override
    public Charset getCharset() {
        return null;
    }

    @Override
    public URL getLocation() {
        return null;
    }
}
