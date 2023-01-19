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

import io.github.gleidsonmt.dashboardfx.core.app.controllers.LoaderController;
import io.github.gleidsonmt.dashboardfx.core.app.services.Context;
import javafx.fxml.FXMLLoader;
import javafx.scene.image.Image;

import java.io.IOException;
import java.util.Objects;

/**
 * Class that provides logger and states.
 * @author Gleidson Neves da Silveira | gleidisonmt@gmail.com
 * Create on  02/10/2022
 */
public class Main extends App {

    @Override
    public void build(Context context) {
        // Loader view
        FXMLLoader loader = new FXMLLoader();
        try {
            loader.setController(new LoaderController(context));
            loader.setLocation(getClass().getResource("/core.app/loader.fxml"));
            loader.load();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        context.icons(
                new Image(Objects.requireNonNull(getClass().getResource("/core.app/img/logo_64.png")).toExternalForm()),
                new Image(Objects.requireNonNull(getClass().getResource("/core.app/img/logo_32.png")).toExternalForm()),
                new Image(Objects.requireNonNull(getClass().getResource("/core.app/img/logo_48.png")).toExternalForm()),
                new Image(Objects.requireNonNull(getClass().getResource("/core.app/img/logo_16.png")).toExternalForm())
        );

        context.routes().navigate("loader", loader);

    }
}
