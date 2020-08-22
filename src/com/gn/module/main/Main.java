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

import com.gn.DashApp;
import com.gn.global.factory.badges.*;
import com.gn.global.plugin.*;
import com.gn.global.util.PopupCreator;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.control.*;
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

        hamburger.setStyle("-fx-background-color : transparent; " +
                "-fx-border-width : 0px;" +
                "-fx-padding : 8px; -fx-max-height : 30px; -fx-min-height : 30px; ");


        SVGPath icon = new SVGPath();
        icon.setContent("M2 15.5v2h20v-2H2zm0-5v2h20v-2H2zm0-5v2h20v-2H2z");
        icon.setStyle("-fx-fill : -text-color;");
        hamburger.getStyleClass().add("hamburger");

        Button btnHamb = new Button();
        btnHamb.setPadding(new Insets(0));
        btnHamb.setContentDisplay(ContentDisplay.GRAPHIC_ONLY);
        btnHamb.setGraphic(icon);
        btnHamb.setMaxWidth(30D);
        btnHamb.setMinWidth(30D);
        btnHamb.getStyleClass().add("hamburger");

//        btnHamb.setMaxHeight(25);

        hamburger.setGraphic(btnHamb);

        btnHamb.setOnAction(event -> {
            PopupCreator.INSTANCE.createDrawerLeft(hamburger, drawer);
//            hamburger.setVisible(false);
//            App.getDecorator().hideControls();
        });

    }

    private void hideHamburger(){
        DashApp.decorator.getMenus().remove(hamburger);
        HBox.setMargin(title, new Insets(0D,0D,0,20D));
    }

    private void showHamburger(){
        if(!DashApp.decorator.getMenus().contains(hamburger)) {
            DashApp.decorator.addMenu(0, hamburger);
        }

        HBox.setMargin(title, new Insets(0,0,0, 60D));
    }


    private void hideDrawer() {
        main.getChildren().remove(drawer);
        DashApp.decorator.showCustomControls();

        VBox info = (VBox) drawer.lookup("#info");
//        VBox info = (VBox) drawer.lookup("#box");
        if(!info.getChildren().contains(contentBadges)) info.getChildren().add(contentBadges);
        DashApp.decorator.removeControl(userDetail);
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
        DashApp.decorator.widthProperty().addListener((observable, oldValue, newValue) -> {
            if(newValue.doubleValue() <= GridFxOld.Type.XS.getValue()) {
                hideDrawer();
                showHamburger();
                removeBadges();
            } else if (newValue.doubleValue() <= GridFxOld.Type.SM.getValue()){
                showHamburger();
                hideDrawer();


            } else if (newValue.doubleValue() > GridFxOld.Type.SM.getValue() ){
                showDrawer();
                hideHamburger();
                PopupCreator.INSTANCE.closePopup();
                addBadges();

            }
        });

        StackPane body = (StackPane) ViewManager.INSTANCE.get("login").getRoot();
        PopupCreator.INSTANCE.createPopup(body);

        DashApp.decorator.addControl(userDetail);
        DashApp.decorator.addControl(badgeSettings);
        DashApp.decorator.addControl(badgeNotification);
        DashApp.decorator.addControl(badgeTasks);
        DashApp.decorator.addControl(badgeMessages);

        userDetail.getStyleClass().add("badge-drawer");
        badgeSettings.getStyleClass().add("badge-drawer");
        badgeNotification.getStyleClass().add("badge-drawer");
        badgeTasks.getStyleClass().add("badge-drawer");
        badgeMessages.getStyleClass().add("badge-drawer");

//        addBadges();
        DashApp.decorator.blockControls();
    }

    @Override
    public void exit() {
    }

    private void addBadges(){
        updateStyles(true);

        DashApp.decorator.getCustomControls().clear();

        DashApp.decorator.addControl(0, userDetail);
        DashApp.decorator.addControl(1, badgeSettings);
        DashApp.decorator.addControl(2, badgeNotification);
        DashApp.decorator.addControl(3, badgeTasks);
        DashApp.decorator.addControl(4, badgeMessages);

    }

    private void removeBadges(){

        DashApp.decorator.removeControl(badgeMessages);
        DashApp.decorator.removeControl(badgeNotification);
        DashApp.decorator.removeControl(badgeTasks);

        contentBadges.getChildren().setAll(badgeTasks, badgeMessages, badgeNotification);
//        updateStyles(false);

    }

    private void updateStyles(boolean value){


        System.out.println("values = " + value);

        contentBadges.getChildren().forEach(
                e -> {
                    if(value) {
                        System.out.println("style =  " + true);

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
