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
package com.gn.global.plugin;

import animatefx.animation.BounceIn;
import com.gn.global.util.PopupCreator;
import io.github.gleidson28.GNAvatarView;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.event.EventHandler;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.geometry.VPos;
import javafx.scene.control.Button;
import javafx.scene.control.ContentDisplay;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import org.controlsfx.control.PopOver;

/**
 * @author Gleidson Neves da Silveira | gleidisonmt@gmail.com
 * Create on  19/10/2018
 * Version 2.0
 */
@SuppressWarnings("unused")
public class UserDetail extends Button {

    private final Button            signOut     = new Button("Sign Out");
    private final Button            profile     = new Button("Profile");
    private final PopOver           popOver     = new PopOver();
    private final StringProperty    header      = new SimpleStringProperty();
    private final StringProperty    name        = new SimpleStringProperty();

    public UserDetail(String name, String header){

        config();

        this.header.set(header);
        this.name.set(name);

        popOver.setArrowLocation(PopOver.ArrowLocation.TOP_CENTER);
        popOver.setArrowIndent(0);
        popOver.setArrowSize(0);
        popOver.setCornerRadius(0);
        popOver.setContentNode(configLayout());
    }

    public void config() {

        Image image = new Image("/com/gn/module/media/jessy.png");
        ImageView imageView = new ImageView(image);
        imageView.setFitHeight(30);
        imageView.setFitWidth(30);

        Circle circle = new Circle(12);
        circle.setStroke(Color.WHITE);
        circle.setStrokeWidth(5);
        circle.setCenterX(imageView.getFitWidth() / 2);
        circle.setCenterY(imageView.getFitHeight() / 2);
        imageView.setClip(circle);

        Hyperlink link = new Hyperlink();
        link.setContentDisplay(ContentDisplay.GRAPHIC_ONLY);
        link.textProperty().bind(super.textProperty());
        link.setMinHeight(30);
        link.setGraphic(imageView);
        link.setOnMouseClicked(event -> popOver.show(link, 0));

        this.setPrefWidth(30);
        this.setGraphic(link);
        this.setContentDisplay(ContentDisplay.GRAPHIC_ONLY);

        this.setPrefHeight(30D);
        this.setMaxHeight(30D);
        this.setPadding(new Insets(0D));
    }

    private VBox configLayout() {

        VBox box = new VBox();
        VBox background = new VBox();
        Label header = new Label();
        header.textProperty().bind(headerProperty());
        Label subTitle = new Label();
        HBox content = new HBox();
        GridPane layoutContent = new GridPane();

        header.getStyleClass().add("h4");

        header.textProperty().bind(headerProperty());
        subTitle.setText("Member since 2018");

        GNAvatarView gnAvatar = new GNAvatarView();
//        gnAvatar.setType(AvatarType.CIRCLE);
        gnAvatar.setImage(new Image("/com/gn/module/media/jessy.png"));

        background.setPrefHeight(500);
        box.setPrefWidth(387);
        box.setPrefHeight(300);

        signOut.getStyleClass().addAll("btn-profile");
        profile.getStyleClass().addAll("btn-profile");

        signOut.setPrefWidth(100);
        profile.setPrefWidth(100);

        signOut.setMinHeight(40);
        profile.setMinHeight(40);

        signOut.setOnAction(event -> {
            StackPane body = (StackPane) ViewManager.INSTANCE.get("login").getRoot();
            PopupCreator.INSTANCE.createPopup(body, new BounceIn(body));
            popOver.hide();
        });

        profile.setOnAction(event -> {
//            ScrollPane scrollPane = (ScrollPane) App.getDecorator().getScene().lookup("#body");
//            try {
//                ViewManager.INSTANCE.openSubView( scrollPane,"profile");
//                popOver.hide();
//            } catch (NavigationException e) {
//                e.printStackTrace();
//            }
        });

        layoutContent.add(signOut, 0, 0);
        layoutContent.add(profile, 1, 0);

        ColumnConstraints column1 = new ColumnConstraints(100, 100, Double.MAX_VALUE);
        ColumnConstraints column2 = new ColumnConstraints(100, 100, Double.MAX_VALUE);

        RowConstraints row = new RowConstraints(100, 100, Double.MAX_VALUE);

        column1.setHalignment(HPos.CENTER);
        column2.setHalignment(HPos.CENTER);

        column2.setHgrow(Priority.ALWAYS);
        column1.setHgrow(Priority.ALWAYS);

        row.setValignment(VPos.CENTER);

        layoutContent.getColumnConstraints().addAll(column1, column2);
        layoutContent.getRowConstraints().addAll(row);

        background.setAlignment(Pos.CENTER);
        background.getChildren().addAll(gnAvatar, header, subTitle);

        content.setPrefHeight(300);
        content.setAlignment(Pos.CENTER);

        content.getChildren().add(layoutContent);

        VBox.setVgrow(content, Priority.ALWAYS);
        HBox.setHgrow(layoutContent, Priority.ALWAYS);

        box.getChildren().addAll(background, content);

        popOver.getRoot().getStylesheets().add(getClass().getResource("/com/gn/theme/css/poplight.css").toExternalForm());

        return box;
    }

    public String getName(){
        return name.get();
    }

    public PopOver getPopOver() {
        return popOver;
    }

    public void setSignAction(EventHandler<MouseEvent> event) {
        this.signOut.setOnMouseClicked(event);
    }

    public void setProfileAction(EventHandler<MouseEvent> event) {
        this.profile.setOnMouseClicked(event);
    }

    public void setHeader(String header){
        headerProperty().setValue(header);
    }

    public String getHeader() {
        return header.get();
    }

    public StringProperty headerProperty() {
        return header;
    }
}