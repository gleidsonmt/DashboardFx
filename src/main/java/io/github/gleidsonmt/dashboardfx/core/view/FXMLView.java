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

package io.github.gleidsonmt.dashboardfx.core.view;

import io.github.gleidsonmt.dashboardfx.core.interfaces.ActionView;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;

import java.net.URL;
import java.nio.charset.Charset;

/**
 * @author Gleidson Neves da Silveira | gleidisonmt@gmail.com
 * Version 0.0.1
 * Create on  13/05/2023
 */
public class FXMLView implements IFXMLView {

    private ViewComposer composer = null;
    private final FXMLLoader loader;
    private String id;

    public FXMLView(ViewComposer composer, FXMLLoader loader) {
        this.composer = composer;
        this.loader = loader;
    }

    @Override
    public ViewComposer getComposer() throws NullPointerException {
        return composer;
    }

    @Override
    public String getName() {
        return composer == null ? id : composer.getName() ;
    }

    @Override
    public Parent getRoot() {
        return loader.getRoot();
    }

    @Override
    public ActionView getController() {
        return loader.getController();
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
    public String toString() {
        return "ViewConstructor{" +
                "composer=" + composer +
                ", loader=" + loader +
                '}';
    }
}
