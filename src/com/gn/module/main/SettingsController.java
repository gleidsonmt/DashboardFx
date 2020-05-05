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
import com.gn.decorator.GNDecorator;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Toggle;
import javafx.scene.control.ToggleButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;

import java.net.URL;
import java.util.ResourceBundle;

/**
 * @author Gleidson Neves da Silveira | gleidisonmt@gmail.com
 * Create on  30/04/2020
 */
public class SettingsController implements Initializable {

    @FXML private ToggleGroup group;
    @FXML private GridPane gridPrimary;
    @FXML private GridPane gridSecondary;
    @FXML private GridPane gridDrawer;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        group.selectedToggleProperty().addListener((observable, oldValue, newValue) -> {
            if(newValue != null){
                if(newValue instanceof ToggleButton){
                    changeTheme(((ToggleButton) newValue).getText().equals("Off"));
                }
            }
        });

        gridPrimary.getChildren().forEach(e -> {

            e.setOnMouseClicked( mouseEvent -> {
                String color = e.getStyle().substring(e.getStyle().indexOf(":") + 1);
                App.getDecorator().getScene().getRoot().setStyle("-base : " + color);
            });

        });

        gridDrawer.getChildren().stream().filter(e -> e instanceof ToggleButton).map(e -> (ToggleButton) e).forEach(e ->{
            e.setOnMouseClicked(mouseEvent -> {
                VBox drawer = (VBox) App.getDecorator().getScene().lookup("#drawer");

                if(drawer.getStylesheets().size() < 2 && !e.getText().equals("Default")){
                    drawer.getStylesheets().add(getClass().getResource("/com/gn/module/main/style/" + e.getText().toLowerCase().replaceAll(" ", "-") + ".css").toExternalForm());
                } else if( drawer.getStylesheets().size() > 1){
                    drawer.getStylesheets().remove(1 );
                };

            });
        });

//        gridSecondary.getChildren().forEach(e ->{
//            e.setOnMouseClicked(mouseEvent -> {
//                String secondaryColor = e.getStyle().substring(e.getStyle().indexOf(":") + 1);
//                App.getDecorator().getScene().getRoot().setStyle("-secondary-color : " + secondaryColor);
//            });
//        });
    }

    private void changeTheme(boolean light){
        if(light){
            App.getDecorator().initTheme(GNDecorator.Theme.DEFAULT);
            App.getDecorator().getScene().getStylesheets().removeAll(getClass().getResource("/com/gn/theme/css/dark.css").toExternalForm());
        }
        else {
            App.getDecorator().initTheme(GNDecorator.Theme.DARKULA);
            App.getDecorator().getScene().getStylesheets().addAll(getClass().getResource("/com/gn/theme/css/dark.css").toExternalForm());
        }
    }
}
