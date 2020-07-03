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
import com.gn.global.factory.badges.*;
import com.gn.global.plugin.*;
import com.gn.global.util.PopupCreator;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
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
    @FXML private VBox drawer;
    @FXML private HBox barHeader;
    @FXML private HBox main;

    @FXML private RadioButton available;

    private UserDetail        userDetail;
    private BadgeMessages     badgeMessages;
    private BadgeNotification badgeNotification;
    private BadgeTasks        badgeTasks;
    private BadgeSettings     badgeSettings;
    private HBox              contentBadges;


    private final Menu hamburger = new Menu();

    @Override
    public void initialize(URL location, ResourceBundle resources)  {

        userDetail          = new UserDetail("Jane Doe", "Jane");
        badgeMessages       = new BadgeMessages();
        badgeNotification   = new BadgeNotification();
        badgeTasks          = new BadgeTasks();
        badgeSettings       = new BadgeSettings();
        contentBadges       = new HBox();

//        hamburger.setStyle("-fx-background-color : transparent;");

        SVGPath icon = new SVGPath();
        icon.setContent("M2 15.5v2h20v-2H2zm0-5v2h20v-2H2zm0-5v2h20v-2H2z");
        icon.setStyle("-fx-fill : -text-color;");
        hamburger.getStyleClass().add("hamburger");

        Button btnHamb = new Button();
        btnHamb.setGraphic(icon);
        btnHamb.setMaxWidth(20D);
        btnHamb.setMinWidth(25D);
        hamburger.setGraphic(btnHamb);

        btnHamb.setOnAction(event -> {
            PopupCreator.INSTANCE.createDrawerLeft(hamburger, drawer);
            hamburger.setVisible(false);
            App.getDecorator().hideControls();
        });
//        hamburger.addEve(ActionEvent.ACTION, event ->{
//            PopupCreator.INSTANCE.createDrawerLeft(hamburger, drawer);
//            hamburger.setVisible(false);
//            App.getDecorator().hideControls();
//        });

    }

    private void hideHamburger(){
        App.getDecorator().getMenus().remove(hamburger);
        HBox.setMargin(title, new Insets(0D,0D,10,20D));
    }

    private void showHamburger(){
        if(!App.getDecorator().getMenus().contains(hamburger)) {
            App.getDecorator().addMenu(0, hamburger);
        }

        HBox.setMargin(title, new Insets(0,0,10, 60D));
    }


    private void hideDrawer() {
        main.getChildren().remove(drawer);
//        App.getDecorator().showCustoms();

        VBox info = (VBox) drawer.lookup("#info");
        if(!info.getChildren().contains(contentBadges)) info.getChildren().add(contentBadges);
        App.getDecorator().removeControl(userDetail);
    }

    private void showDrawer(){
        if(!main.getChildren().contains(drawer)) {
            drawer.setPrefWidth(250D);
            main.getChildren().add(drawer);
            drawer.toBack();
        }
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
            } else if (newValue.doubleValue() > GridFx.Type.SM.getValue() ){
                showDrawer();
                hideHamburger();
                PopupCreator.INSTANCE.closePopup();
                addBadges();
            }
        });

        StackPane body = (StackPane) ViewManager.INSTANCE.get("login").getRoot();
        PopupCreator.INSTANCE.createPopup(body);

        App.getDecorator().addControl(3, userDetail);
        App.getDecorator().addControl(4, badgeSettings);
        App.getDecorator().addControl(5, badgeNotification);
        App.getDecorator().addControl(6, badgeTasks);
        App.getDecorator().addControl(7, badgeMessages);

        addBadges();
        App.getDecorator().block();
    }

    @Override
    public void exit() {
    }

    private void addBadges(){
        updateStyles(false);
        // Repopulate
        App.getDecorator().removeControl(badgeMessages);
        App.getDecorator().removeControl(badgeSettings);
        App.getDecorator().removeControl(badgeNotification);
        App.getDecorator().removeControl(badgeTasks);
        App.getDecorator().removeControl(userDetail);
//
        App.getDecorator().addControl(3, userDetail);
        App.getDecorator().addControl(4, badgeSettings);
        App.getDecorator().addControl(5, badgeNotification);
        App.getDecorator().addControl(6, badgeTasks);
        App.getDecorator().addControl(7, badgeMessages);
    }

    private void removeBadges(){

        App.getDecorator().removeControl(badgeMessages);
        App.getDecorator().removeControl(badgeNotification);
        App.getDecorator().removeControl(badgeTasks);
//
//        contentBadges.getChildren().setAll(badgeAlerts, badgeMessages, badgeNotification);
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
