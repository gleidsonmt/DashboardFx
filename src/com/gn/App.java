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

import com.gn.global.*;
import com.gn.global.factory.LoadViews;
import com.gn.global.plugin.SectionManager;
import com.gn.global.plugin.UserManager;
import com.gn.global.plugin.ViewManager;
import com.gn.decorator.GNDecorator;
import com.gn.decorator.options.ButtonType;
import com.gn.module.loader.Loader;
import com.gn.module.main.Main;
import com.sun.javafx.application.LauncherImpl;
import javafx.application.Application;
import javafx.application.HostServices;
import javafx.application.Platform;
import javafx.application.Preloader;
import javafx.collections.ObservableList;
import javafx.concurrent.WorkerStateEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
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

    private float  increment = 0;
    private float  progress = 0;
    private Section section;
    private User    user;

    private static final GNDecorator decorator  = new GNDecorator();
    private static final Scene       scene      = decorator.getScene();

    public static ObservableList<String>    stylesheets;
    private static HostServices              hostServices;
    private static UserDetail userDetail = null;

    private double minWidth = 350;
    private double minHeight = 500;

    public static GNDecorator getDecorator(){
        return decorator;
    }

    public static void openLink(String link){
        hostServices.showDocument(link);
    }

    @Override
    public synchronized void init(){
//        section = SectionManager.get();
//
//        if(section.isLogged()){
//            user = UserManager.get(section.getUserLogged());
            userDetail = new UserDetail("Gleidson", "Gleidson", "subtitle");
//        } else {
//            userDetail = new UserDetail();
//        }
        LoadViews load = new LoadViews();
        load.start();

        try {
            wait(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private void initialScene(){
        decorator.setTitle(null);
        decorator.setIcon(null);
        decorator.setMaximized(true);
        decorator.fullBody();



//            userDetail.setProfileAction(event -> {
//                Main.ctrl.title.setText("Profile");
//                Main.ctrl.body.setContent(ViewManager.getInstance().get("profile"));
//                userDetail.getPopOver().hide();
//            });
//
//            userDetail.setSignAction(event -> {
//                App.decorator.setContent(ViewManager.getInstance().get("login"));
//                section.setLogged(false);
//                SectionManager.save(section);
//                userDetail.getPopOver().hide();
//                if(Main.popConfig.isShowing()) Main.popConfig.hide();
//                if(Main.popup.isShowing()) Main.popup.hide();
//                App.decorator.removeCustom(userDetail);
//            });
//            decorator.setContent(ViewManager.getInstance().get("main"));
//        }

//        decorator.getStage().setOnCloseRequest(event -> {
//            App.getUserDetail().getPopOver().hide();
//            if(Main.popConfig.isShowing()) Main.popConfig.hide();
//            if(Main.popup.isShowing()) Main.popup.hide();
//            Platform.exit();
//        });
    }

    @Override
    public  void start(Stage primary) throws Exception {

        initialScene();
        hostServices = getHostServices();

        stylesheets = decorator.getScene().getStylesheets();
//
        stylesheets.addAll(
                getClass().getResource("/com/gn/theme/css/fonts.css").toExternalForm(),
                getClass().getResource("/com/gn/theme/css/material-color.css").toExternalForm(),
                getClass().getResource("/com/gn/theme/css/skeleton.css").toExternalForm(),
                getClass().getResource("/com/gn/theme/css/bootstrap.css").toExternalForm(),
                getClass().getResource("/com/gn/theme/css/shape.css").toExternalForm(),
                getClass().getResource("/com/gn/theme/css/typographic.css").toExternalForm(),
                getClass().getResource("/com/gn/theme/css/helpers.css").toExternalForm(),
                getClass().getResource("/com/gn/theme/css/light-green.css").toExternalForm(),
                getClass().getResource("/com/gn/theme/css/master.css").toExternalForm()
        );

//        System.out.println("main " + ViewManager.INSTANCE.get("main"));
//        decorator.setContent(ViewManager.INSTANCE.get("main").getRoot());
//        decorator.setMaximized(true);

//        decorator.show();

        Parent root = FXMLLoader.load(getClass().getResource("/com/gn/module/loader/loader.fxml"));
        decorator.getStage().setMinWidth(minWidth);
        decorator.getStage().setMinHeight(minHeight);
        decorator.setContent(root);
        decorator.getStage().getIcons().add(new Image("/com/gn/module/media/logo2.png"));
//        decorator.floatActions();
        decorator.show();

//        ScenicView.show(decorator.getScene());
    }

    public static void main(String[] args) {
        launch(args);
//        LauncherImpl.launchApplication(App.class, Loader.class, args);
    }

    private void load(String module, String name){
//        try {
////            ViewManager.INSTANCE.put(
////                    name,
////                    FXMLLoader.load(getClass().getResource("/com/gn/module/" + module + "/" + name + ".fxml"))
////            );
////
//            preloaderNotify();
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
    }

    private synchronized void preloaderNotify() {
        progress += increment;
        LauncherImpl.notifyPreloader(this, new Preloader.ProgressNotification(progress));
    }

    @SuppressWarnings("ResultOfMethodCallIgnored")
    private String logged(){
        try {
            File file = new File("dashboard.properties");
            Properties properties = new Properties();

            if(!file.exists()){
                file.createNewFile();
                return "account";
            } else {
                FileInputStream fileInputStream = new FileInputStream(file);
                properties.load(fileInputStream);
                properties.putIfAbsent("logged", "false");
                FileOutputStream fileOutputStream = new FileOutputStream(file);
                properties.store(fileOutputStream, "Dashboard properties");


                File directory = new File("user/");
                properties.load(fileInputStream);
                if(directory.exists()){
                    if(properties.getProperty("logged").equals("false"))
                        return "login";
                    else
                        return "main";
                } else
                    return "account";
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static UserDetail getUserDetail() {
        return userDetail;
    }
}
