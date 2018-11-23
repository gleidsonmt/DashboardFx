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
package com.gn.control;

import com.gn.decorator.component.GNControl;
import javafx.event.EventHandler;
import javafx.geometry.HPos;
import javafx.geometry.Pos;
import javafx.geometry.VPos;
import javafx.scene.Node;
import javafx.scene.control.Button;
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
public class UserDetail extends GNControl {

    private PopOver popOver = new PopOver();
    public static PopOver root;

    public UserDetail() {
        UserDetail.root = popOver;
        popOver.setArrowLocation(PopOver.ArrowLocation.TOP_CENTER);
        popOver.setArrowIndent(0);
        popOver.setArrowSize(0);
        popOver.setCornerRadius(0);
        popOver.setContentNode(configLayout());
    }

    public PopOver getPopOver() {
        return popOver;
    }

    @Override
    protected Node icon() {
        Image image = new Image(getClass().getResource("/com/gn/module/media/avatar.jpg").toExternalForm());
        ImageView imageView = new ImageView(image);
        imageView.setFitHeight(30);
        imageView.setFitWidth(30);

        Circle circle = new Circle(12);
        circle.setStroke(Color.WHITE);
        circle.setStrokeWidth(5);
        circle.setCenterX(imageView.getFitWidth() / 2);
        circle.setCenterY(imageView.getFitHeight() / 2);
        imageView.setClip(circle);

        return imageView;
    }

    @Override
    protected Node status() {
        return null;
    }

    @Override
    protected Node action() {

        Hyperlink link = new Hyperlink("Gleidson Neves da Silveira");
        link.setMinHeight(30);

        link.setOnMouseClicked(event -> {
            popOver.show(link, 0);
//            popOver.show(link);
        });
        return link;
    }

    private Button signOut = new Button("Sign Out");
    private Button profile = new Button("Profile");

    public void setSignAction(EventHandler<MouseEvent> event) {
        this.signOut.setOnMouseClicked(event);
    }

    public void setProfileAction(EventHandler<MouseEvent> event) {
        this.profile.setOnMouseClicked(event);
    }

    private VBox configLayout() {

        VBox box = new VBox();
        VBox background = new VBox();
        ImageView avatar = new ImageView();
        Label header = new Label();
        Label subTitle = new Label();
        HBox content = new HBox();
        GridPane layoutContent = new GridPane();

        header.getStyleClass().add("h4");

        header.setText("Gleidson Neves da Silveira");
        subTitle.setText("Member since 2018");
        avatar.setImage(new Image(getClass().getResource("/com/gn/module/media/avatar.jpg").toExternalForm()));

        background.setPrefHeight(500);

        box.setPrefWidth(387);
        box.setPrefHeight(300);
        avatar.setFitWidth(139);
        avatar.setFitHeight(136D);


        signOut.getStyleClass().addAll("outlined", "out-primary", "signout");
        profile.getStyleClass().addAll("outlined", "out-primary", "profile");

        signOut.setPrefWidth(100);
        profile.setPrefWidth(100);

        signOut.setMinHeight(40);
        profile.setMinHeight(40);

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
        background.getChildren().addAll(avatar, header, subTitle);

        content.setPrefHeight(300);
        content.setAlignment(Pos.CENTER);

        content.getChildren().add(layoutContent);

        VBox.setVgrow(content, Priority.ALWAYS);
        HBox.setHgrow(layoutContent, Priority.ALWAYS);

        box.getChildren().addAll(background, content);

        Circle circle = new Circle(60);
        circle.setStroke(Color.WHITE);
        circle.setStrokeWidth(5);
        circle.setCenterX(avatar.getFitWidth() / 2);
        circle.setCenterY(avatar.getFitHeight() / 2);
        avatar.setClip(circle);

        popOver.getRoot().getStylesheets().add(getClass().getResource("/com/gn/theme/css/poplight.css").toExternalForm());

        return box;
    }

}