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
import javafx.animation.RotateTransition;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.util.Duration;

import java.net.URL;
import java.util.ResourceBundle;

/**
 * @author Gleidson Neves da Silveira | gleidisonmt@gmail.com
 * Create on  22/11/2018
 * Version 1.0.1
 */
public class Account implements Initializable {

    @FXML private GNAvatar avatar;

    @FXML private HBox box_fullname;
    @FXML private HBox box_username;
    @FXML private HBox box_email;
    @FXML private HBox box_conEmail;
    @FXML private HBox box_password;

    @FXML private TextField fullname;
    @FXML private TextField username;
    @FXML private TextField email;
    @FXML private TextField conEmail;
    @FXML private TextField password;

    private RotateTransition rotateTransition = new RotateTransition();

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        rotateTransition.setNode(avatar);
        rotateTransition.setByAngle(360);
        rotateTransition.setDuration(Duration.seconds(1));
        rotateTransition.setAutoReverse(true);

        addEffect(email);
        addEffect(conEmail);
        addEffect(fullname);
        addEffect(username);
        addEffect(password);

    }

    @FXML
    private void back(){
        App.decorator.setContent(ViewManager.getInstance().get("login"));
    }

    private void addEffect(Node node){
        node.addEventFilter(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                rotateTransition.play();
                Pulse pulse = new Pulse(node.getParent());
                pulse.setDelay(Duration.millis(100));
                pulse.setSpeed(5);
                pulse.play();
                node.getParent().setStyle("-icon-color : -success; -fx-border-color : -success");
            }
        });

        node.focusedProperty().addListener((observable, oldValue, newValue) -> node.getParent().setStyle("-icon-color : -dark-gray; -fx-border-color : transparent"));

    }
}
