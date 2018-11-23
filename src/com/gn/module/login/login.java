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
package com.gn.module.login;

import animatefx.animation.Pulse;
import com.gn.App;
import com.gn.ViewManager;
import com.gn.control.GNAvatar;
import com.gn.control.skin.ClearableSkin;
import javafx.animation.RotateTransition;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.util.Duration;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

/**
 * @author Gleidson Neves da Silveira | gleidisonmt@gmail.com
 * Create on  22/11/2018
 * Version 1.0.2
 */
public class login implements Initializable {

    @FXML private GNAvatar avatar;
    @FXML private HBox box_username;
    @FXML private HBox box_password;
    @FXML private TextField username;
    @FXML private TextField password;
    @FXML private Button login;

    @Override
    public void initialize(URL location, ResourceBundle resources) {

//        new ClearableSkin(username);

        RotateTransition rotateTransition = new RotateTransition();
        rotateTransition.setNode(avatar);
        rotateTransition.setByAngle(360);
        rotateTransition.setDuration(Duration.seconds(1));
        rotateTransition.setAutoReverse(true);

        username.setOnMouseClicked(event -> {
            rotateTransition.play();
            Pulse pulse = new Pulse(box_username);
            pulse.setDelay(Duration.millis(100));
            pulse.setSpeed(5);
            pulse.play();
            box_username.setStyle("-icon-color : -success; -fx-border-color : -success");
        });

        username.focusedProperty().addListener((observable, oldValue, newValue) -> box_username.setStyle("-icon-color : -dark-gray; -fx-border-color : transparent"));

      password.setOnMouseClicked(event -> {
            rotateTransition.play();
            Pulse pulse = new Pulse(box_password);
            pulse.setDelay(Duration.millis(100));
            pulse.setSpeed(5);
            pulse.play();
            box_password.setStyle("-icon-color : -success; -fx-border-color : -success");
        });

        password.focusedProperty().addListener((observable, oldValue, newValue) -> box_password.setStyle("-icon-color : -dark-gray; -fx-border-color : transparent"));

    }

    @FXML
    private void loginAction(){
        Pulse pulse = new Pulse(login);
        pulse.setDelay(Duration.millis(20));
        pulse.play();
        enter();
    }

    private void enter() {

    }

    private void createAccount(){

    }

    @FXML
    private void switchCreate(){
        App.decorator.setContent(ViewManager.getInstance().get("account"));
    }
}
