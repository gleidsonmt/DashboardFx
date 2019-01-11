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
package com.gn.global.factory;

import com.gn.AvatarType;
import com.gn.GNAvatarView;
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIconView;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.geometry.VPos;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.SVGPath;
import javafx.scene.text.Text;
import javafx.scene.text.TextFlow;


/**
 * @author Gleidson Neves da Silveira | gleidisonmt@gmail.com
 * Create on  21/11/2018
 * Version 1.0
 */
public class AlertCell extends HBox {

    private FontAwesomeIconView fontIcon;
    private ImageView           imageView;
    private SVGPath             path;

    private VBox content = new VBox();

    private TextFlow textFlow = new TextFlow();
    private Text text = new Text();
    private Label title = new Label("tile");
    private Label time  = new Label("n minutes ago");

    public AlertCell(String title, String text, String time) {
        this.title.setText(title);
        this.time.setText(time);
        this.text.setText(text);
        config();
    }

    public AlertCell(GNAvatarView avatar, String title, String text, String time) {
        setIcon(avatar);
        this.title.setText(title);
        this.time.setText(time);
        this.text.setText(text);
        config();
    }

    public AlertCell(FontAwesomeIconView icon, String title, String time) {
        icon.setSize("22");
        this.setIcon(icon);
        this.title.setText(title);
        this.time.setText(time);
        config();
    }

    public AlertCell(SVGPath icon, String title, String time) {
        this.setIcon(icon);
        this.title.setText(title);
        this.time.setText(time);
        config();
    }

    public AlertCell(ImageView icon, String title, String time) {
        setIcon(icon);
        this.title.setText(title);
        this.time.setText(time);
        config();
    }

    private void config(){
        this.getStyleClass().add("alert-cell");
        this.setAlignment(Pos.CENTER_LEFT);
        this.setPrefHeight(40D);
        this.title.setStyle("-fx-font-size : 14;");
        this.text.getStyleClass().addAll("h6");
        this.text.setStyle("-fx-fill : -text-color;");
        this.time.setStyle("-fx-text-fill : -text-color; -fx-font-style : italic; ");
        textFlow.getChildren().addAll(text);
        this.content.getChildren().addAll(this.title, textFlow, this.time);
        this.content.setAlignment(Pos.CENTER_LEFT);
        this.getChildren().add(content);
        this.setAlignment(Pos.CENTER);
        HBox.setHgrow(content, Priority.ALWAYS);
        GridPane.setHalignment(this.time, HPos.RIGHT);
        GridPane.setValignment(this.time, VPos.CENTER);
        GridPane.setHgrow(this.time, Priority.ALWAYS);
        HBox.setMargin(this.content, new Insets(0,0,0,10));
    }

    public void setTitle(Label title) {
        title.setStyle("-fx-text-fill : -text-color;");
        this.title = title;
    }


    public void setTime(Label time) {
        time.setStyle("-fx-text-fill : -text-color;");
        this.time = time;
    }

    public void setIcon(FontAwesomeIconView icon){
        icon.setStyle("-fx-fill : -text-color;");
        this.getChildren().add(icon);
        icon.toBack();
    }

    public void setIcon(ImageView icon){
        this.getChildren().add(icon);
        icon.toBack();
    }

    public void setIcon(GNAvatarView icon){
        icon.setType(AvatarType.CIRCLE);
        icon.setStrokeWidth(0);
        icon.setStroke(Color.WHITE);
        icon.setPrefWidth(35);
        this.getChildren().add(icon);
        icon.toBack();
    }

    public void setIcon(SVGPath icon){
        this.getChildren().add(icon);
        icon.toBack();
    }

    public FontAwesomeIconView getIcon() {
        return fontIcon;
    }

    public Label getTitle() {
        return title;
    }
}
