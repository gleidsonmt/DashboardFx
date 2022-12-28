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

import fr.brouillard.oss.cssfx.CSSFX;
import io.github.gleidsonmt.dashboardfx.core.app.controllers.LoaderController;
import io.github.gleidsonmt.dashboardfx.core.app.interfaces.IDecorator;
import io.github.gleidsonmt.dashboardfx.core.app.interfaces.Root;
import io.github.gleidsonmt.dashboardfx.core.app.interfaces.PathView;
import io.github.gleidsonmt.dashboardfx.core.app.interfaces.Routes;
import io.github.gleidsonmt.dashboardfx.core.app.services.LoadViews1;
import io.github.gleidsonmt.dashboardfx.core.app.services.IView;
import io.github.gleidsonmt.dashboardfx.core.app.services.ViewComposer;
import io.github.gleidsonmt.gndecorator.core.GNDecorator;
import javafx.application.HostServices;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import org.jetbrains.annotations.NotNull;

import java.io.IOException;
import java.util.Objects;
import java.util.Properties;
import java.util.logging.Logger;

/**
 * This class represents the whole window that appear in application.
 * When this is starting all view receives the threads already.
 * @author Gleidson Neves da Silveira | gleidisonmt@gmail.com
 * Create on  03/10/2022
 */

@Deprecated(since = "1.0", forRemoval = true)
public final class WindowDecorator extends GNDecorator implements IDecorator {

    private final PathView pathView;
    private LoaderController loaderController;

    private Routes routes;

    private final Root root = null;

    public WindowDecorator(@NotNull Properties _properties, @NotNull PathView _path) throws IOException {
        // setTheme and logo here
        this.pathView = _path;

        this.getIcons().add(new Image(getClass().getResource("/core.app/logo/logo_32.png").toExternalForm()));

//        root = new IRoot(this);

        Scene scene = this.getWindow().getScene();

        // Theming by controls lib

//        scene.getStylesheets().add(Objects.requireNonNull(getClass().getResource("/fonts/fonts.css")).toExternalForm());
//        scene.getStylesheets().add("/fonts/fonts.css");
//        scene.getStylesheets().addAll("/core.app/css/colors.css");
//        scene.getStylesheets().addAll("/core.app/css/skeleton.css");
//        scene.getStylesheets().addAll("/core.app/css/bootstrap.css");
//        scene.getStylesheets().addAll("/core.app/css/imersive_scroll.css");
//        scene.getStylesheets().addAll("/core.app/css/typographic.css");
//        scene.getStylesheets().addAll("/core.app/css/theme/simple_info.css");

        scene.getStylesheets().addAll(
                Objects.requireNonNull(getClass().getResource("/core.app/css/colors.css")).toExternalForm(),
                Objects.requireNonNull(getClass().getResource("/core.app/css/skeleton.css")).toExternalForm(),
                Objects.requireNonNull(getClass().getResource("/core.app/css/bootstrap.css")).toExternalForm(),
                Objects.requireNonNull(getClass().getResource("/core.app/css/imersive_scroll.css")).toExternalForm(),
                Objects.requireNonNull(getClass().getResource("/core.app/css/typographic.css")).toExternalForm(),
                Objects.requireNonNull(getClass().getResource("/core.app/css/helpers.css")).toExternalForm(),
                Objects.requireNonNull(getClass().getResource("/core.app/css/theme/simple_info.css")).toExternalForm()
        );

        scene.getStylesheets().add("/dash.css");

        fullBody();

        // Getting default parameters for window
        setWidth(Integer.parseInt(_properties.getProperty("app.width")));
        setHeight(Integer.parseInt(_properties.getProperty("app.height")));

        setMinWidth(Integer.parseInt(_properties.getProperty("app.min.width")));
        setMinHeight(Integer.parseInt(_properties.getProperty("app.min.height")));


        setMaximized(true);

    }

    @Override
    public Root getRoot() {
        return root;
    }

    @Override
    public ObservableList<Node> controls() {
        return this.getCustomControls();
    }

    @Override
    public void show(HostServices hostServices) {

        initPreLoader();

        show();

//        ScenicView.show(this.getWindow().getScene());
        CSSFX.start(this.getWindow());

    }

    private void initPreLoader() {

        try {

            Logger.getLogger("app").info("Initializing Pre Loader Application");

            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("/core.app/loader.fxml"));
            loader.load();

            loaderController = loader.getController();

            setContent(loader.getRoot());

        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    private void initLayout() {
        setContent((Parent) root);

        FXMLLoader loader = new FXMLLoader();

        loader.setLocation(getClass().getResource("/views/drawer.fxml"));

//        Drawer drawer = context.getDecorator().getRoot().getWrapper().getDrawer();
//        loader.setController(drawer);

        try {
            loader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }

        ViewComposer viewComposer = new ViewComposer();
        viewComposer.setName("drawer");
        viewComposer.setFxml("drawer.fxml");

        IView IView = new IView(viewComposer, loader);

        getRoot().getLayout().setDrawer(
                IView
        );
    }

}
