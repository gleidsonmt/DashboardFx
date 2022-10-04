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

import io.github.gleidsonmt.dashboardfx.core.app.exceptions.ControllerCastException;
import io.github.gleidsonmt.dashboardfx.core.app.interfaces.ActionView;
import io.github.gleidsonmt.dashboardfx.core.app.interfaces.IView;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;

import java.net.URL;
import java.nio.charset.Charset;

/**
 * @author Gleidson Neves da Silveira | gleidisonmt@gmail.com
 * Create on  29/03/2020
 */
@SuppressWarnings("unused")
public class View implements IView {

    private final ViewComposer  composer;
    private final FXMLLoader    loader;

    View(ViewComposer composer) {
        this(composer, null);
    }

    public View(ViewComposer composer, FXMLLoader loader) {
        this.composer = composer;
        this.loader = loader;
    }

    @Override
    public ViewComposer getComposer() {
        return composer;
    }

    @Override
    public ActionView getController() {

        if (loader.getController() != null) {

            if (!(loader.getController() instanceof ActionView)) {

                try {
                    throw new ControllerCastException("CAST", "Error on controller " + loader.getController() + " does not extends action view.");
                } catch (ControllerCastException e) {
                    e.printStackTrace();
                }

            }
        }

        return loader.getController();
    }

    @Override
    public Parent getRoot() {
        return loader.getRoot();
    }

    @Override
    public Charset getCharset() {
        return loader.getCharset();
    }

    @Override
    public URL getLocation() {
        return loader.getLocation();
    }

    @Override
    public String getName() {
        return composer.getName();
    }

    @Override
    public String toString() {
        return "ViewConstructor{" +
                "composer=" + composer +
                ", loader=" + loader +
                '}';
    }
}
