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

package io.github.gleidsonmt.dashboardfx.core.app;

import io.github.gleidsonmt.dashboardfx.core.app.interfaces.IDecorator;
import io.github.gleidsonmt.dashboardfx.core.app.interfaces.PathView;
import io.github.gleidsonmt.gncontrols.Material;
import io.github.gleidsonmt.gncontrols.Theme;
import io.github.gleidsonmt.gndecorator.GNDecorator;
import javafx.application.HostServices;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import org.jetbrains.annotations.NotNull;

import java.io.IOException;
import java.util.Objects;
import java.util.Properties;

/**
 * This class represents the whole window that appear in application.
 * When this is starting all view receives the threads already.
 * @author Gleidson Neves da Silveira | gleidisonmt@gmail.com
 * Create on  03/10/2022
 */
public class WindowDecorator extends GNDecorator implements IDecorator {


    public WindowDecorator(@NotNull Properties _properties, @NotNull PathView _path) throws IOException {
        // setTheme and logo here

        // Theming by controls lib
        new Material(
                this.getWindow().getScene(),
                Theme.SIMPLE_INFO
        );

        fullBody();

        // Getting default parameters for window
        setWidth(Integer.parseInt(_properties.getProperty("app.width")));
        setHeight(Integer.parseInt(_properties.getProperty("app.height")));

        setMinWidth(Integer.parseInt(_properties.getProperty("app.min.width")));
        setMinHeight(Integer.parseInt(_properties.getProperty("app.min.height")));
    }

    @Override
    public void show(HostServices hostServices) {
        initPreLoader();
        show();
    }


    private void initPreLoader() {
        try {
            Parent loader = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/io.github.gleidsonmt.dashboardfx/core.app/loader.fxml")));
            setContent(loader);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }


}
