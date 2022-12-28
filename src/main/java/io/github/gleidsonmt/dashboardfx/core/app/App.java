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

package io.github.gleidsonmt.dashboardfx.core.app;

import fr.brouillard.oss.cssfx.CSSFX;
import io.github.gleidsonmt.dashboardfx.core.app.services.Context;
import io.github.gleidsonmt.dashboardfx.core.app.services.IContext;
import io.github.gleidsonmt.dashboardfx.core.app.services.IRoutes;
import io.github.gleidsonmt.dashboardfx.core.layout.IRoot;
import io.github.gleidsonmt.dashboardfx.core.layout.Material;
import javafx.application.Application;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import org.scenicview.ScenicView;

import java.util.Objects;

public abstract class App extends Application {

    private final IContext context;
    private final IRoot root;

    public App() {
        context = new IContext();
        root = new IRoot(context);
        context.setRoutes(new IRoutes(root, context));
        context.routes().registry("layout", (Parent) root.getLayout());
        context.setRoot(root);
    }

    @Override
    public void start(Stage stage) throws Exception {
        build(context);
        Scene scene = new Scene(root);

        for (String s : Material.stylesheets()) {
            scene.getStylesheets().add(Objects.requireNonNull(getClass().getResource(s)).toExternalForm());
        }

//        context.routes().registry("dash", new ILayout(context));

        CSSFX.start(scene);
        stage.getIcons().setAll(context.getIcons());
        stage.setScene(scene);
        stage.setMaximized(true);
        stage.show();
//        ScenicView.show(scene);
    }

    public abstract void build(Context context);

}
