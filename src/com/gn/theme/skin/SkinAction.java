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
package com.gn.theme.skin;

import com.sun.javafx.scene.control.skin.TextFieldSkin;
import javafx.event.EventHandler;
import javafx.geometry.HPos;
import javafx.geometry.VPos;
import javafx.scene.Cursor;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.layout.Region;
import javafx.scene.layout.StackPane;

/**
 * @author Gleidson Neves da Silveira | gleidisonmt@gmail.com
 * Create on  23/11/2018
 * Version 1.2
 */
public abstract class SkinAction extends TextFieldSkin {

    private StackPane       button;
    private Region          graphic;
    private TextField       textField;
    private PasswordField   passwordField;

    SkinAction(TextField textField) {
        super(textField);
        this.textField = textField;
        config();
        setupListeners();
    }

    SkinAction(PasswordField passwordField){
        super(passwordField);
        this.passwordField = passwordField;
        config();
        setupListeners();
    }

    private void config(){
        button = new StackPane();
        button.getStyleClass().setAll("action-button");
        button.setFocusTraversable(false);

        graphic = new Region();
        graphic.getStyleClass().setAll("action-button-graphic");
        graphic.setFocusTraversable(false);

        button.setMinWidth(0D);

        button.setVisible(false);

        button.getChildren().add(graphic);
        ((Pane) getChildren().get(0)).getChildren().add(button);
    }

    private void setupListeners() {

        final TextField textField = getSkinnable();

        button.setOnMouseReleased(event -> mouseReleased());
        button.setOnMousePressed(event -> mousePressed());
        button.addEventFilter(MouseEvent.MOUSE_CLICKED, event -> {
            if(graphic.isVisible())button.setCursor(Cursor.HAND);
            else button.setCursor(Cursor.DEFAULT);

        });
        button.setOnMouseMoved(event -> {
            if(graphic.isVisible())button.setCursor(Cursor.HAND);
            else button.setCursor(Cursor.DEFAULT);
        });
        textField.textProperty().addListener((observable, oldValue, newValue) -> textChanged());
        textField.focusedProperty().addListener((observable, oldValue, newValue) -> focusChanged());

        button.setMinWidth(10);
        button.setMinHeight(10);
        graphic.setMinWidth(10);
        graphic.setMinHeight(10);
    }


    @Override
    protected void layoutChildren(double x, double y, double w, double h) {
        super.layoutChildren(x, y, w, h);

        layoutInArea(button,
                (x + w) - (button.getWidth() * 2),h/2 - snappedTopInset(),
                10, 10, 0, HPos.RIGHT, VPos.CENTER);

    }

    public StackPane getButton() {
        return button;
    }

    public Region getGraphic(){
        return graphic;
    }

    TextField getTextField() {
        return textField;
    }

    PasswordField getPasswordField() {
        return passwordField;
    }

    abstract void mouseReleased();
    abstract void textChanged();
    abstract void focusChanged();
    abstract void mousePressed();

}
