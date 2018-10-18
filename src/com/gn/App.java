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

import com.gn.decorator.GNDecorator;
import com.gn.module.loader.Loader;
import com.sun.javafx.application.LauncherImpl;
import javafx.application.Application;
import javafx.application.Preloader;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.ColorPicker;
import javafx.stage.Stage;
import org.scenicview.ScenicView;

import java.io.IOException;

/**
 * @author Gleidson Neves da Silveira | gleidisonmt@gmail.com
 * Create on  07/10/2018
 * Version 1.0
 */
public class App extends Application {

    private float increment = 0;
    private byte   progress = 0;


    @SuppressWarnings("IntegerDivisionInFloatingPointContext")
    @Override
    public synchronized void init(){
        byte total = 15;
        increment = 100 / total;
        load("designer", "colors"); // 1
        load("designer", "cards"); // 2

        load("controls", "button");   // 3
        load("controls", "toggle");  // 4
        load("controls", "textfield");  //5
        load("controls", "datepicker");  //6
        load("controls", "checkbox");  //7
        load("controls", "radiobutton");  //8
        load("controls", "combobox");  //9
        load("controls", "choicebox");  //10
        load("controls", "splitmenubutton"); // 11
        load("controls", "menubutton");// 12
        load("controls", "menubar");// 13
        load("controls", "colorpicker");// 14
        load("controls", "slider");// 15
//        load("controls", "htmleditor");// 16
        load("controls", "progressbar");
        load("main",     "main");  // last

        new ColorPicker();
        // delay
        try {
            wait(300);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void stop(){

    }

    @Override
    public  void start(Stage primary) {
        GNDecorator decorator = new GNDecorator();
        decorator.setTitle("Dashboard JavaFx");
        decorator.setContent(ViewManager.getInstance().getCurrentView());

        ObservableList<String> stylesheets = decorator.getScene().getStylesheets();

        stylesheets.addAll(
                getClass().getResource("/com/gn/theme/css/fonts.css").toExternalForm(),
                getClass().getResource("/com/gn/theme/css/material.css").toExternalForm()
        );

        decorator.initTheme(GNDecorator.Theme.DEFAULT);

        decorator.getStage().show();


//        ScenicView.show(decorator.getScene());
    }

    public static void main(String[] args) {
        LauncherImpl.launchApplication(App.class, Loader.class, args);
    }

    private void load(String module, String name){
        try {
                ViewManager.getInstance().put(
                        name,
                        FXMLLoader.load(getClass().getResource("/com/gn/module/" + module + "/" + name + ".fxml"))
                );
                preloaderNotify();
            } catch (IOException e) {
                e.printStackTrace();
            }
    }

    private synchronized void preloaderNotify() {
        progress += increment;
        LauncherImpl.notifyPreloader(this, new Preloader.ProgressNotification(progress));
    }
}
