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
        button.getStyleClass().setAll("right-button");
        button.setFocusTraversable(false);

        graphic = new Region();
        graphic.getStyleClass().setAll("right-button-graphic");
        graphic.setFocusTraversable(false);

        graphic.setMaxWidth(Region.USE_PREF_SIZE);
        graphic.setMaxHeight(Region.USE_PREF_SIZE);

        button.setVisible(false);
        graphic.setVisible(false);

        button.getChildren().add(graphic);
        getChildren().add(button);
    }

    private void setupListeners() {

        final TextField textField = getSkinnable();

        button.setOnMouseReleased(event -> mouseReleased());
        button.setOnMousePressed(event -> mousePressed());
        button.addEventFilter(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                if(graphic.isVisible())button.setCursor(Cursor.HAND);
                else button.setCursor(Cursor.DEFAULT);

            }
        });
        button.setOnMouseMoved(event -> {
            if(graphic.isVisible())button.setCursor(Cursor.HAND);
            else button.setCursor(Cursor.DEFAULT);
        });
        textField.textProperty().addListener((observable, oldValue, newValue) -> textChanged());
        textField.focusedProperty().addListener((observable, oldValue, newValue) -> focusChanged());
    }

    @Override
    protected void layoutChildren(double x, double y, double w, double h) {
        super.layoutChildren(x, y, w, h);

        final double clearGraphicWidth = snapSize(graphic.prefWidth(-1));
        final double clearButtonWidth = button.snappedLeftInset() + clearGraphicWidth + button.snappedRightInset();

        button.resize(clearButtonWidth, h);
        positionInArea(button,
                (x + w) - clearButtonWidth, y,
                clearButtonWidth, h, 0, HPos.LEFT, VPos.CENTER);
    }

    abstract void mouseReleased();
    abstract void textChanged();
    abstract void focusChanged();
    abstract void mousePressed();

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

}
