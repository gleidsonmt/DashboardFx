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

import io.github.gleidsonmt.dashboardfx.core.app.services.Context;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

public abstract class Launcher extends Application {

//    private final IContext context = null;
//    private final IRoot root = null;

    public Launcher() {// before stage creating
//        context = new IContext(getHostServices());
//        root = new IRoot(context);
//        context.setRoutes(new IRoutes(root, context));
//        context.routes().registry("layout", (Parent) root.getLayout());
//        context.setRoot(root);
    }

    @Override
    public void start(Stage stage) throws Exception {
//        build(context);
//
//        buildFull(stage);
        // in main
//        buildDefault(stage);
        stage.setScene(new Scene(new StackPane(new Label("Welcome"))));
        stage.setMaximized(true);
        stage.show();
    }

//    private void buildDefault(Stage stage) {
//        Scene scene = new Scene(root);
//
//        for (String s : Material.stylesheets()) {
//            scene.getStylesheets().add(Objects.requireNonNull(getClass().getResource(s)).toExternalForm());
//        }
//
////        stage.setFullScreen(true);
//        stage.getIcons().setAll(context.getIcons());
//        stage.setScene(scene);
//        stage.setMinHeight(600);
//        stage.setMinWidth(500);
//        stage.setMaximized(true);
//        stage.show();
//
////        CSSFX.start(scene);
////        ScenicView.show(scene);
//
//    }
//
//    private void buildDecorator(Stage stage) {
////        GNDecorator decorator = new GNDecorator();
////        decorator.setContent(root);
////        decorator.fullBody();
//////        Scene scene = new Scene(root);
////
////        for (String s : Material.stylesheets()) {
////            decorator.getStylesheets().add(Objects.requireNonNull(getClass().getResource(s)).toExternalForm());
////        }
////
////        decorator.getIcons().setAll(context.getIcons());
////        decorator.show();
//
//    }

    public abstract void build(Context context);

}
