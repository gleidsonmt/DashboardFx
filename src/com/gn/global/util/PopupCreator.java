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
package com.gn.global.util;

import animatefx.animation.*;
import com.gn.App;
import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.scene.Parent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.util.Duration;

/**
 * @author Gleidson Neves da Silveira | gleidisonmt@gmail.com
 * Create on  02/04/2020
 */
public enum PopupCreator {

    INSTANCE;

    PopupCreator() {
    System.out.println("Popup Creator");
        defineContents();
    }

    private StackPane foreground;
    private AnchorPane foreContent;
    private StackPane customDialog;

    private Timeline openFromLeft = new Timeline();
    private Timeline closeFromLeft = new Timeline();
    int maxLeftAndRight = 250;
    int velocity = 200;

    public void createDrawerLeft(VBox content){
        foreground.getChildren().clear();
        foreground.getChildren().add(foreContent);
        foreContent.getChildren().clear();
        foreContent.getChildren().add(customDialog);

        customDialog.setPrefWidth(250);

        if(!customDialog.getChildren().contains(content)) {
            customDialog.getChildren().clear();
            customDialog.getChildren().add(content);
        }

        openFromLeft.getKeyFrames().addAll(
                new KeyFrame(Duration.ZERO, new KeyValue(customDialog.translateXProperty(), maxLeftAndRight * -1)),
                new KeyFrame(Duration.millis(velocity), new KeyValue(customDialog.translateXProperty(), 0))
        );

        closeFromLeft.getKeyFrames().addAll(
                new KeyFrame(Duration.ZERO, new KeyValue(customDialog.translateXProperty(), 0)),
                new KeyFrame(Duration.millis(velocity), new KeyValue(customDialog.translateXProperty(), maxLeftAndRight * -1) )
        );
        foreground.toFront();

        openFromLeft.play();

        foreContent.setOnMouseClicked(event -> {
            if (event.getTarget() instanceof AnchorPane){
                if(((AnchorPane) event.getTarget()).getId().equals("fore-content")) {
                    closeFromLeft.play();
                }
            }
        });
        closeFromLeft.setOnFinished(event -> {
            foreground.toBack();
            App.getDecorator().showCustoms();
        });

        organizeInLeft();
    }

    public void closePopup(){
        foreground.toBack();
    }

    public void closePopup(AnimationFX animationFX){
        animationFX.play();
        animationFX.getTimeline().setOnFinished(event -> {
            foreground.toBack();
        });

    }

    public void createPopup(StackPane content){
        customDialog.getChildren().clear();
        customDialog.getChildren().add(content);
        organizeInCenter(content);
        foreground.getChildren().clear();
        foreground.getChildren().add(content);

        foreground.toFront();

    }

    public void createPopup(StackPane content, AnimationFX animationFX){
        customDialog.getChildren().clear();
        customDialog.getChildren().add(content);
        organizeInCenter(content);
        foreground.getChildren().clear();
        foreground.getChildren().add(content);

        foreground.toFront();

        animationFX.play();
    }

    private void organizeInCenter(StackPane content){
       double x = ( App.getDecorator().getScene().getWindow().getWidth() / 2) - (content.getPrefWidth() / 2);
       double y = ( App.getDecorator().getScene().getWindow().getHeight() / 2 ) - (content.getPrefHeight() / 2);

       AnchorPane.setTopAnchor(customDialog, y);
       AnchorPane.setLeftAnchor(customDialog, x);

       AnchorPane.clearConstraints(customDialog);
       AnchorPane.setRightAnchor(customDialog, 0D);
       AnchorPane.setBottomAnchor(customDialog, 0D);
    }

    private void organizeInLeft(){
        AnchorPane.clearConstraints(customDialog);
        AnchorPane.setTopAnchor(customDialog, 0D);
        AnchorPane.setLeftAnchor(customDialog, 0D);
        AnchorPane.setBottomAnchor(customDialog, 0D);
    }

    private void defineContents(){
        foreground = (StackPane) App.getDecorator().getScene().lookup("#foreground");
        foreContent = (AnchorPane) foreground.lookup("#fore-content");
        customDialog = (StackPane) foreground.lookup("#custom-dialog");
    }
}
