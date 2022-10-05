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
import io.github.gleidsonmt.dashboardfx.core.app.exceptions.NavigationException;
import io.github.gleidsonmt.dashboardfx.core.app.interfaces.*;
import io.github.gleidsonmt.dashboardfx.core.app.services.LoadViews;
import io.github.gleidsonmt.dashboardfx.core.layout.Root;
import io.github.gleidsonmt.gncontrols.Material;
import io.github.gleidsonmt.gncontrols.Theme;
import io.github.gleidsonmt.gndecorator.GNDecorator;
import javafx.application.HostServices;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import org.jetbrains.annotations.NotNull;

import java.io.IOException;
import java.util.Properties;
import java.util.logging.Logger;

/**
 * This class represents the whole window that appear in application.
 * When this is starting all view receives the threads already.
 * @author Gleidson Neves da Silveira | gleidisonmt@gmail.com
 * Create on  03/10/2022
 */
public final class WindowDecorator extends GNDecorator implements IDecorator, Context {

    private final PathView pathView;
    private LoaderController loaderController;

    private IRotes routes;
    private final IRoot root;

    public WindowDecorator(@NotNull Properties _properties, @NotNull PathView _path) throws IOException {
        // setTheme and logo here
        this.pathView = _path;

        root = new Root();

        // Theming by controls lib
        new Material(this.getWindow().getScene(), Theme.SIMPLE_INFO);

//        fullBody();

        // Getting default parameters for window
        setWidth(Integer.parseInt(_properties.getProperty("app.width")));
        setHeight(Integer.parseInt(_properties.getProperty("app.height")));

        setMinWidth(Integer.parseInt(_properties.getProperty("app.min.width")));
        setMinHeight(Integer.parseInt(_properties.getProperty("app.min.height")));

    }

    @Override
    public IRoot getRoot() {
        return root;
    }

    @Override
    public void show(HostServices hostServices) {

        initPreLoader();

        LoadViews loadViews = new LoadViews();

        loadViews.setOnReady(event -> {

            loaderController.info("Reading application..");

        });

        loadViews.setOnFailed(event -> {

            Logger.getLogger("app").severe("Error on loading preloader");

        });

        loadViews.setOnCancelled(event -> {
            Logger.getLogger("app").severe("Error on loading preloader");
        });

        loadViews.setOnRunning(event -> {
            loaderController.info("Reading application..");
        });

        loadViews.setOnSucceeded(event -> {
            initLayout();

            try {
                context.getRoutes().setContent("dash");
            } catch (NavigationException e) {
                e.printStackTrace();
            }

        });

        loadViews.start();
        show();

//        ScenicView.show(this.getWindow().getScene());

    }


    private void initPreLoader() {

        try {

            Logger.getLogger("app").info("Intializing Pre Loader Application");

            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource(pathView.getFromCore("/loader.fxml")));
            loader.load();

            loaderController = loader.getController();

            setContent(loader.getRoot());

        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    private void initLayout() {

        setContent((Parent) root);

    }

}
