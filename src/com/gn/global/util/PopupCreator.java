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

import animatefx.animation.AnimationFX;
import animatefx.animation.BounceIn;
import animatefx.animation.BounceOut;
import com.gn.DashApp;
import com.gn.global.plugin.GridFxOld;
import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Menu;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.scene.text.TextFlow;
import javafx.util.Duration;

/**
 * @author Gleidson Neves da Silveira | gleidisonmt@gmail.com
 * Create on  02/04/2020
 */
public enum PopupCreator {

    INSTANCE;

    PopupCreator() {
        defineContents();
    }

    private StackPane   foreground;
    private AnchorPane  foreContent;
    private StackPane   customDialog;

    private final Timeline open   = new Timeline();
    private final Timeline close  = new Timeline();

    private final int minDrawerSize = 250;
    private final int velocity      = 200;
    private final double barSize    = 40;

    public void createDrawerRight(VBox content){

        reset();

//        customDialog.setStyle("-fx-background-color : -foreground-color; -fx-background-radius :  0;");
//        foreground.getChildren().clear();
//        foreground.getChildren().add(foreContent);
//        foreContent.getChildren().clear();
//        foreContent.getChildren().add(customDialog);

        customDialog.setPrefWidth(minDrawerSize);

        customDialog.getChildren().setAll(content);

//        if(!customDialog.getChildren().contains(content)) {
//            customDialog.getChildren().clear();
//            customDialog.getChildren().add(content);
//        }

        organizeInRight();

        open.getKeyFrames().setAll(
                new KeyFrame(Duration.ZERO, new KeyValue(customDialog.translateXProperty(), minDrawerSize)),
                new KeyFrame(Duration.millis(velocity), new KeyValue(customDialog.translateXProperty(), 0))
        );

        close.getKeyFrames().setAll(
                new KeyFrame(Duration.ZERO, new KeyValue(customDialog.translateXProperty(), 0)),
                new KeyFrame(Duration.millis(velocity), new KeyValue(customDialog.translateXProperty(), minDrawerSize) )
        );

        foreground.toFront();
        open.play();

        foreContent.setOnMouseClicked(event -> {
            if (event.getTarget() instanceof AnchorPane){
                if(((AnchorPane) event.getTarget()).getId().equals("fore-content")) {
                    close.play();
                }
            }
        });

        close.setOnFinished(event -> {
            foreground.toBack();
            DashApp.decorator.showCustomControls();
            DashApp.decorator.unblockControls();
        });
        DashApp.decorator.blockControls();
        DashApp.decorator.hideCustomControls();

    }

    public void createDrawerLeft(Menu hamb, VBox content){
        createDrawerLeft(content);
        hamb.getGraphic().setVisible(false);
        close.setOnFinished(event -> {
            hamb.getGraphic().setVisible(true);
            foreground.toBack();
            DashApp.decorator.showCustomControls();
            DashApp.decorator.unblockControls();
        });
    }

    public void createDrawerLeft(VBox content){
        reset();
//        foreground.getChildren().clear();
//        foreground.getChildren().add(foreContent);
//        foreContent.getChildren().clear();
//        foreContent.getChildren().add(customDialog);

        customDialog.setPrefWidth(minDrawerSize);

        customDialog.getChildren().setAll(content);

//        if(!customDialog.getChildren().contains(content)) {
//            customDialog.getChildren().clear();
//            customDialog.getChildren().add(content);
//        }

        organizeInLeft();

        open.getKeyFrames().setAll(
                new KeyFrame(Duration.ZERO, new KeyValue(customDialog.translateXProperty(), minDrawerSize * -1)),
                new KeyFrame(Duration.millis(velocity), new KeyValue(customDialog.translateXProperty(), 0))
        );

        close.getKeyFrames().setAll(
                new KeyFrame(Duration.ZERO, new KeyValue(customDialog.translateXProperty(), 0)),
                new KeyFrame(Duration.millis(velocity), new KeyValue(customDialog.translateXProperty(), minDrawerSize * -1) )
        );

        foreground.toFront();
        open.play();

        foreContent.setOnMouseClicked(event -> {
            if (event.getTarget() instanceof AnchorPane){
                if(((AnchorPane) event.getTarget()).getId().equals("fore-content")) {
                    close.play();
                }
            }
        });

        close.setOnFinished(event -> {
            foreground.toBack();
            DashApp.decorator.showCustomControls();
            DashApp.decorator.unblockControls();
        });
//
        DashApp.decorator.unblockControls();
        DashApp.decorator.hideCustomControls();

    }

    public enum AlertType {
        DONE, INFO, WARNING, ERROR
    }

    public void createDialog(String title, String message, Button... actions){
        reset();
        VBox body = new VBox();
        Label _title = new Label(title);
        _title.setWrapText(true);
        Text _message = new Text(message);
        TextFlow textFlow = new TextFlow(_message);
        HBox _actions = new HBox(actions);

        _title.getStyleClass().add("h3");
        _message.getStyleClass().add("h4");

        body.getChildren().setAll(_title, textFlow, _actions);

        body.setSpacing(5D);
        body.setPadding(new Insets(20D, 5, 20,20));

        VBox.setVgrow(textFlow, Priority.ALWAYS);
        VBox.setMargin(textFlow, new Insets(10, 0, 0, 10));
        _actions.setSpacing(5D);
        _actions.setAlignment(Pos.CENTER_RIGHT);

        BounceOut bounceOut = new BounceOut(customDialog);
        bounceOut.getTimeline().setOnFinished(event1 -> foreground.toBack());

        _actions.getChildren().stream().map(e -> (Button) e).forEach(c -> {
            c.setPrefWidth(100);
            c.addEventFilter(ActionEvent.ACTION, event -> bounceOut.play());
        });

        foreground.setOnMouseClicked(null);

        customDialog.getChildren().setAll(body);
        customDialog.setPrefSize(400, 200);

        organizeInCenter();

        BounceIn bounceIn = new BounceIn(customDialog);
        foreground.toFront();
        bounceIn.play();

    }

    public void createAlert(AlertType alertType, String title, String message, Button... actions){
        reset();
        VBox content = new VBox();
        content.setPrefSize(500,300);
//        content.setMaxWidth(200);
        content.setAlignment(Pos.CENTER);

        content.getChildren().setAll(
                createAlertHeader(alertType),
                createContent(title, message),
                createActions(actions)
        );

        customDialog.setStyle("-fx-background-color : -foreground-color; -fx-background-radius : 10 10 10 10;");

        customDialog.setPrefSize(content.getPrefWidth(), content.getPrefHeight());
//        customDialog.setMinSize(content.getPrefWidth(), content.getPrefHeight());
//        customDialog.setMaxSize(content.getPrefWidth(), content.getPrefHeight());

        customDialog.getChildren().setAll(content);

        organizeInCenter();

        BounceIn bounceIn = new BounceIn(customDialog);
        foreground.toFront();
        bounceIn.play();

        foreground.setOnMouseClicked(null);

        foreground.addEventFilter(MouseEvent.MOUSE_CLICKED, event ->{
            System.out.println("clicked");
        });
    }

    private VBox createAlertHeader(AlertType type){
//        App.getDecorator().block();
        VBox header = new VBox();
//        header.minWidthProperty().bind(customDialog.minWidthProperty());
        header.setMinHeight(120);
        header.setAlignment(Pos.CENTER);
//        VBox.setVgrow(header, Priority.ALWAYS);

        ImageView icon = null;
        Color color = null;

        switch (type){
            case INFO:
                color = Color.web("#33B5E5");
                icon = new ImageView(new Image("/com/gn/module/media/img/info_48dp.png"));
                break;
            case WARNING:
                color = Color.web("#FC6E51");
                icon = new ImageView(new Image("/com/gn/module/media/img/warning_48dp.png"));
                break;
            case ERROR:
                color = Color.web("#ED5565");
                icon = new ImageView(new Image("/com/gn/module/media/img/error_48dp.png"));
                break;
            case DONE:
                color = Color.web("#02C852");
                icon = new ImageView(new Image("/com/gn/module/media/img/done_48dp.png"));
                break;
        }
        header.setBackground(new Background(new BackgroundFill(color, new CornerRadii(
                10,false), Insets.EMPTY)));

        icon.setPreserveRatio(true);
        icon.setSmooth(true);
        icon.setFitWidth(151);
        icon.setFitHeight(78);

        header.getChildren().add(icon);
        return header;
    }

    private static VBox  createContent(String title, String message){
        VBox container = new VBox();
        container.setAlignment(Pos.TOP_CENTER);
        container.setSpacing(20D);

        VBox.setVgrow(container, Priority.ALWAYS);

        VBox.setMargin(container, new Insets(10,0,0,0));

        Label lblTitle = new Label(title);
        lblTitle.getStyleClass().add("h2");

        Label text = new Label();
        text.setWrapText(true);
        text.setText(message);
        text.setAlignment(Pos.CENTER);

        container.getChildren().addAll(lblTitle, text);

        return container;
    }

    private HBox createActions(Button... actions){
        HBox _actions = new HBox();
        _actions.setMinSize(50, 73);
        _actions.setAlignment(Pos.CENTER);
        VBox.setMargin(_actions, new Insets(10, 10, 10, 10));
        _actions.setSpacing(5D);

        _actions.getChildren().setAll(actions);
        _actions.getChildren().forEach( e -> e.addEventFilter(ActionEvent.ACTION, event -> {
            BounceOut bounceOut = new BounceOut(customDialog);
            bounceOut.getTimeline().setOnFinished(ev -> {
                foreground.toBack();
                DashApp.decorator.unblockControls();
            });
            bounceOut.play();

        }));

        return _actions;
    }

    public void createPopup(StackPane content){
        customDialog.getChildren().setAll(content);
        customDialog.setPrefSize(content.getPrefWidth(), content.getPrefHeight());
        organizeInCenter();
        foreground.toFront();

    }

    public void createPopup(StackPane content, AnimationFX animationFX){
        customDialog.getChildren().clear();
        customDialog.getChildren().add(content);
        organizeInCenter();

        foreground.toFront();

        animationFX.play();
    }

    private void organizeInCenter(){

        double width = DashApp.decorator.getWidth();
        double height = DashApp.decorator.getHeight();

        if(width < GridFxOld.Type.XS.getValue()){
//            customDialog.setMaxWidth(250);
            customDialog.setPrefWidth(250);
//            customDialog.setMinWidth(250);
        }

        double x = ( width / 2) - ( customDialog.getPrefWidth() / 2);
        double y = ( height / 2 ) - (customDialog.getPrefHeight() / 2);

        AnchorPane.clearConstraints(customDialog);

        customDialog.setTranslateX(0D);
        customDialog.setTranslateY(0D);

        AnchorPane.setTopAnchor(customDialog, y);
        AnchorPane.setLeftAnchor(customDialog, x);
    }

    private void organizeInRight(){
        AnchorPane.clearConstraints(customDialog);
        AnchorPane.setTopAnchor(customDialog, 0D);
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
        foreground = (StackPane) DashApp.decorator.lookup("#foreground");
        foreContent = (AnchorPane) foreground.lookup("#fore-content");
        customDialog = (StackPane) foreground.lookup("#custom-dialog");
    }

    public void closePopup(){
        DashApp.decorator.unblockControls();
        close.play();
    }

    public void closePopup(AnimationFX animationFX){
        DashApp.decorator.unblockControls();
        animationFX.play();
        close.play();
        animationFX.getTimeline().setOnFinished(event -> foreground.toBack());
    }

    /**
     * Some animations change the custom dialog format.
     */
    private void reset(){

        foreground.setOnMouseClicked(null);

        customDialog.setTranslateX(0D);
        customDialog.setTranslateY(0D);
        customDialog.setScaleX(1);
        customDialog.setScaleY(1);

        customDialog.setVisible(true);
        customDialog.setOpacity(1);
        customDialog.setPrefSize(Region.USE_COMPUTED_SIZE, Region.USE_COMPUTED_SIZE);
        customDialog.setMinSize(Region.USE_COMPUTED_SIZE, Region.USE_COMPUTED_SIZE);
        customDialog.setMaxSize(Region.USE_COMPUTED_SIZE, Region.USE_COMPUTED_SIZE);

        customDialog.setStyle("-fx-background-color : -foreground-color; -fx-background-radius :  0;");

    }
}
