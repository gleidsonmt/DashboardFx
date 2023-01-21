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

package io.github.gleidsonmt.dashboardfx.core.app.services;

import io.github.gleidsonmt.dashboardfx.core.app.interfaces.*;
import io.github.gleidsonmt.dashboardfx.core.layout.Bar;
import io.github.gleidsonmt.dashboardfx.core.layout.IRoot;
import javafx.application.HostServices;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.image.Image;
import org.jetbrains.annotations.ApiStatus;

import java.util.logging.Logger;

@ApiStatus.Internal
public class IContext implements Context {

    private Routes routes;
    private IRoot root;
    private final HostServices hostServices;

    private ObservableList<Image> icons;

    public IContext(HostServices hostServices) {
        this.hostServices = hostServices;
    }

    public void setRoutes(Routes _routes) {
        this.routes = _routes;
    }

    public void setRoot(Root root) {
        this.root = (IRoot) root;
    }

    @Override
    public Root root() {
        return root;
    }

    @Override
    public Bar bar() {
        return root.bar();
    }

    @Override
    public Routes routes() {
        return routes;
    }

    @Override
    public Layout layout() {
        return root.getLayout();
    }

    @Override
    public Logger getLogger() {
        return null;
    }

    @Override
    public Wrapper getWrapper() {
        return root.getWrapper();
    }

    @Override
    public Wrapper wrapper() {
        return root.getWrapper();
    }


    @Override
    public void openLink(String uri) {
        this.hostServices.showDocument(uri);
    }

    @Override
    public void icons(Image... _icons) {
        if (icons == null) icons = FXCollections.observableArrayList(_icons);
    }

    public ObservableList<Image> getIcons() {
        return icons;
    }


}
