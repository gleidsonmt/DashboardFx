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
import javafx.application.Application;
import javafx.collections.ObservableList;
import javafx.stage.Stage;

/**
 * @author Gleidson Neves da Silveira | gleidisonmt@gmail.com
 * Create on  07/10/2018
 * Version 1.0
 */
public class App extends Application {

    @Override
    public void init(){
        ViewManager.getInstance().load("button");
        ViewManager.getInstance().load("designer");
        ViewManager.getInstance().load("main");
    }

    @Override
    public void stop(){

    }

    @Override
    public void start(Stage primary) {
        GNDecorator decorator = new GNDecorator();
        decorator.setTitle("Dashboard JavaFx");
        decorator.setContent(ViewManager.getInstance().getCurrentView());

        ObservableList<String> stylesheets = decorator.getScene().getStylesheets();

        stylesheets.addAll(
                getClass().getResource("/com/gn/theme/css/fonts.css").toExternalForm(),
                getClass().getResource("/com/gn/theme/css/material.css").toExternalForm()
        );

        decorator.initTheme(GNDecorator.Theme.DEFAULT);
        decorator.show();
//        ScenicView.show(decorator.getScene());
    }

    public static void main(String[] args) {
        launch(args);
    }
}
