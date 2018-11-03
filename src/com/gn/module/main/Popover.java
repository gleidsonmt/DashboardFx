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
package com.gn.module.main;

import com.gn.App;
import com.gn.ViewManager;
import com.gn.decorator.GNDecorator;
import com.jfoenix.controls.JFXButton;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;

import java.net.URL;
import java.util.ResourceBundle;

/**
 * @author Gleidson Neves da Silveira | gleidisonmt@gmail.com
 * Create on  27/10/2018
 * Version 1.0
 */
public class Popover implements Initializable {

    @FXML
    private JFXButton theme;

    @FXML
    public VBox options;

    public static Popover ctrl;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        ctrl = this;
    }

    private boolean invert = false;
    @FXML
    private void altTheme() {
//        App.decorator.getStage().close();
        invertTheme(!invert);

//        App.decorator.getStage().show();
    }

    private void invertTheme(boolean dark){
        String theme;
        App.stylesheets.clear();

        if(dark){

            App.decorator.initTheme(GNDecorator.Theme.DARKULA);
            theme = "dark.css";
            invert = true;
        } else {
            App.decorator.initTheme(GNDecorator.Theme.DEFAULT);
            theme = "light.css";
            invert = false;
        }

        String path = "/com/gn/theme/css/";

        ObservableList<String> stylesheets = App.decorator.getStage().getScene().getStylesheets();

        stylesheets.addAll(
                getClass().getResource(path + "fonts.css").toExternalForm(),
                getClass().getResource(path + "material-color.css").toExternalForm(),
                getClass().getResource(path + "skeleton.css").toExternalForm(),
                getClass().getResource(path + "" + theme).toExternalForm(),
                getClass().getResource(path + "bootstrap.css").toExternalForm(),
                getClass().getResource(path + "simple-green.css").toExternalForm(),
                getClass().getResource(path + "forms.css").toExternalForm(),
                getClass().getResource(path + "typographic.css").toExternalForm(),
                getClass().getResource(path + "helpers.css").toExternalForm(),
                getClass().getResource(path + "master.css").toExternalForm(),
                getClass().getResource(path + "poplight.css").toExternalForm()
        );

        for(Node node : ViewManager.getInstance().getAll()){
            ((StackPane)node).getStylesheets().setAll(stylesheets);
        }
    }
}
