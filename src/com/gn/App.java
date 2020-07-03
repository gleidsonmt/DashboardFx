/*
 * Copyright (C) Gleidson Neves da Silveira
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */

package com.gn;

import com.gn.global.plugin.LoadViews;
import com.gn.global.plugin.UserDetail;
import com.gn.global.plugin.GridFx;
import com.gn.decorator.GNDecorator;
import javafx.application.Application;
import javafx.application.HostServices;
import javafx.application.Platform;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TreeTableView;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import org.scenicview.ScenicView;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Properties;

/**
 * Init the app class.
 * @author Gleidson Neves da Silveira | gleidisonmt@gmail.com
 * Create on  07/10/2018
 * Version 1.0
 */
public class App extends Application {

    private float increment = 0;
    private float progress = 0;

    private static final GNDecorator decorator = new GNDecorator();
    private static final Scene scene = decorator.getScene();

    public static ObservableList<String> stylesheets;
    private static HostServices hostServices;
    private static UserDetail userDetail = null;

    private double minWidth = 350;
    private double minHeight = 500;

    public static GNDecorator getDecorator() {
        return decorator;
    }

    public static void openLink(String link) {
        hostServices.showDocument(link);
    }

    @Override
    public synchronized void init() {
        userDetail = new UserDetail("Gleidson", "Gleidson");

        Parent root = null;
        try {
            root = FXMLLoader.load(getClass().getResource("/com/gn/module/loader/loader.fxml"));
        } catch (IOException e) {
            e.printStackTrace();
        }

        decorator.getStage().setMinWidth(minWidth);
        decorator.getStage().setMinHeight(minHeight);
        decorator.setContent(root);
        decorator.getStage().getIcons().add(new Image("/com/gn/media/icon/logo2.png"));

    }

    @Override
    public void stop() {
        Platform.exit();
        System.exit(0);
    }

    private void initialScene() {
        decorator.setTitle(null);
        decorator.setIcon(null);
        decorator.setMaximized(true);
        decorator.fullBody();
    }

    @Override
    public void start(Stage primary) throws Exception {

        initialScene();
        hostServices = getHostServices();

        decorator.getScene().getStylesheets().addAll(
                getClass().getResource("/com/gn/theme/css/fonts.css").toExternalForm(),
                getClass().getResource("/com/gn/theme/css/material-color.css").toExternalForm(),
                getClass().getResource("/com/gn/theme/css/skeleton.css").toExternalForm(),
                getClass().getResource("/com/gn/theme/css/bootstrap.css").toExternalForm(),
                getClass().getResource("/com/gn/theme/css/shape.css").toExternalForm(),
                getClass().getResource("/com/gn/theme/css/typographic.css").toExternalForm(),
                getClass().getResource("/com/gn/theme/css/helpers.css").toExternalForm(),
                getClass().getResource("/com/gn/theme/css/media.css").toExternalForm(),
                getClass().getResource("/com/gn/theme/css/light-green.css").toExternalForm(),
                getClass().getResource("/com/gn/theme/css/master.css").toExternalForm()
        );



        decorator.show();
        GridFx.addStage(decorator.getStage());
        GridFx.log(true);
        LoadViews load = new LoadViews();
        load.start();

//        ScenicView.show(decorator.getScene());
    }

    public static void main(String[] args) {
        launch(args);
    }
}
