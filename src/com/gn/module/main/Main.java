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
import com.gn.GNAvatarView;
import com.gn.global.UserDetail;
import com.gn.global.factory.ActionView;
import com.gn.global.factory.badges.BadgeMessages;
import com.gn.global.factory.badges.BadgeNotification;
import com.gn.global.factory.badges.BadgeSettings;
import com.gn.global.factory.badges.BadgeTasks;
import com.gn.global.plugin.GridFx;
import com.gn.global.plugin.ViewManager;
import com.gn.global.util.PopupCreator;
import com.jfoenix.controls.JFXBadge;
import com.jfoenix.controls.JFXButton;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.control.*;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.shape.Circle;
import javafx.scene.shape.SVGPath;

import java.net.URL;
import java.util.ResourceBundle;

/**
 * @author Gleidson Neves da Silveira | gleidisonmt@gmail.com
 * Create on  08/10/2018
 * Version 2.0
 */
public class Main implements Initializable, ActionView {

    @FXML public  ScrollPane body;
    @FXML public  Label title;
    @FXML private Button hamburger;
    @FXML private VBox drawer;
    @FXML private HBox barHeader;
    @FXML private HBox main;

    @FXML private RadioButton available;

    private final UserDetail        userDetail          = new UserDetail("Jane Doe", "Jane", "SubTitle");
    private final BadgeMessages     badgeMessages       = new BadgeMessages();
    private final BadgeNotification badgeNotification   = new BadgeNotification();
    private final BadgeTasks        badgeAlerts         = new BadgeTasks();
    private final BadgeSettings     badgeSettings       = new BadgeSettings("Text", "Subtitle");
    private final HBox              contentBadges       = new HBox();

    @Override
    public void initialize(URL location, ResourceBundle resources)  {

    }

    private void hideHamburger(){
        hamburger.setMaxWidth(0);
        hamburger.setPrefWidth(0);
        hamburger.setMinWidth(0);
        hamburger.setMinHeight(0);
        hamburger.setVisible(false);
        barHeader.setPadding(new Insets(0));
    }

    private void hideDrawer() {
        main.getChildren().remove(drawer);
        App.getDecorator().showCustoms();

        VBox info = (VBox) drawer.lookup("#info");
        if(!info.getChildren().contains(contentBadges)) info.getChildren().add(contentBadges);
            App.getDecorator().removeCustom(userDetail);
    }

    private void showHamburger(){
        hamburger.setMaxWidth(40);
        hamburger.setPrefWidth(40);
        hamburger.setMinWidth(40);
        hamburger.setMinHeight(40);
        hamburger.setVisible(true);
    }

    private void showDrawer(){
        if(!main.getChildren().contains(drawer)) {
            drawer.setPrefWidth(250D);
            main.getChildren().add(drawer);
            drawer.toBack();
        }
    }


    @FXML
    private void openDrawer(){
        PopupCreator.INSTANCE.createDrawerLeft(drawer);
        App.getDecorator().hideCustoms();
    }

    @Override
    public void enter() {
        App.getDecorator().getStage().widthProperty().addListener((observable, oldValue, newValue) -> {
            if(newValue.doubleValue() <= GridFx.Type.XS.getValue()) {
                hideDrawer();
                showHamburger();
                removeBadges();
            } else if (newValue.doubleValue() <= GridFx.Type.SM.getValue()){
                showHamburger();
                hideDrawer();
            } else {
                showDrawer();
                hideHamburger();
                PopupCreator.INSTANCE.closePopup();
                addBadges();
            }
        });

        StackPane body = (StackPane) ViewManager.INSTANCE.get("login").getRoot();
        PopupCreator.INSTANCE.createPopup(body);

        App.getDecorator().addCustom(userDetail);
        App.getDecorator().addCustom(badgeSettings);

        addBadges();
    }

    @Override
    public void exit() {
    }

    private void addBadges(){
        updateStyles(false);

        App.getDecorator().removeCustom(userDetail);
        App.getDecorator().removeCustom(badgeSettings);
        App.getDecorator().removeCustom(badgeMessages);
        App.getDecorator().removeCustom(badgeNotification);
        App.getDecorator().removeCustom(badgeAlerts);

        App.getDecorator().addCustom(userDetail);
        App.getDecorator().addCustom(badgeSettings);
        App.getDecorator().addCustom(badgeMessages);
        App.getDecorator().addCustom(badgeNotification);
        App.getDecorator().addCustom(badgeAlerts);

    }

    private void removeBadges(){
        App.getDecorator().removeCustom(badgeMessages);
        App.getDecorator().removeCustom(badgeNotification);
        App.getDecorator().removeCustom(badgeAlerts);
        contentBadges.getChildren().setAll(badgeAlerts, badgeMessages, badgeNotification);
        updateStyles(true);

    }

    private void updateStyles(boolean addStyle){
        contentBadges.getChildren().forEach(
                e -> {
                    if(addStyle) {
                        if (!e.getStyleClass().contains("badge-drawer")) {
                            e.getStyleClass().add("badge-drawer");
                        }
                    } else {
                        e.getStyleClass().remove("badge-drawer");
                    }
                }
        );
    }

}
