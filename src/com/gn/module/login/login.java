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

import animatefx.animation.*;
import com.gn.App;
import com.gn.ViewManager;
import com.gn.control.GNAvatar;
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIcon;
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIconView;
import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.RotateTransition;
import javafx.animation.Timeline;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.util.Duration;

import java.net.URL;
import java.util.ResourceBundle;

/**
 * @author Gleidson Neves da Silveira | gleidisonmt@gmail.com
 * Create on  22/11/2018
 * Version 1.0
 */
public class login implements Initializable {

    @FXML private GNAvatar avatar;
    @FXML private HBox box_email;
    @FXML private HBox box_password;
    @FXML private TextField email;
    @FXML private TextField password;
    @FXML private Button login;

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        RotateTransition rotateTransition = new RotateTransition();
        rotateTransition.setNode(avatar);
        rotateTransition.setByAngle(360);
        rotateTransition.setDuration(Duration.seconds(1));
        rotateTransition.setAutoReverse(true);

        email.setOnMouseClicked(event -> {
            rotateTransition.play();
            Pulse pulse = new Pulse(box_email);
            pulse.setDelay(Duration.millis(100));
            pulse.setSpeed(5);
            pulse.play();
            box_email.setStyle("-icon-color : -success; -fx-border-color : -success");
        });

        email.focusedProperty().addListener((observable, oldValue, newValue) -> box_email.setStyle("-icon-color : -dark-gray; -fx-border-color : transparent"));

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

    }
}
