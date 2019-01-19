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

import javafx.application.Platform;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.EventHandler;
import javafx.scene.control.Spinner;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;

import java.util.Locale;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author Gleidson Neves da Silveira | gleidisonmt@gmail.com
 * Create on  24/11/2018
 * Version 1.0
 */
public class Mask {
    public static void noSpaces(TextField field){
        field.lengthProperty().addListener(new ChangeListener<Number>() {
            @Override
            public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {
                if (field.getText() != null) {
                    if (field.getText().length() > 0) {
                        String value = field.getText();
                        value = value.replaceFirst("[ ]", "");
                        field.setText(value);
                    }
                }
            }
        });
    }

    public static void maxField(TextField field, int length) {
        field.textProperty().addListener((observableValue, oldValue, newValue) -> {
            if (newValue == null || newValue.length() > length) {
                field.setText(oldValue);
            }
        });
    }

    public static void noInitSpace(TextField field) {
        field.lengthProperty().addListener((observable, oldValue, newValue) -> {

            if (field.getText() != null) {
                if (field.getText().length() > 0) {
                    String value = field.getText();
                    value = value.replaceFirst("[^a-zA-Z ][~´]", "");
                    value = value.replaceAll("^ ", "");
                    field.setText(value);

                }
            }
        });
    }

    public static void noSymbols(final TextField field) {

        ChangeListener listener = (ChangeListener<Number>) (observable, oldValue, newValue) -> {
            if (field.getText() != null) {
                if (field.getText().length() > 0) {
                    String value = field.getText();
                    value = value.replaceAll("[^a-zA-Z0-9 ]", "");
                    field.setText(value);
                }
            }
        };
        field.lengthProperty().addListener(listener);
    }

    public static void noSymbols(final TextField field, String exceptions) {

        ChangeListener listener = (ChangeListener<Number>) (observable, oldValue, newValue) -> {
            if (field.getText() != null) {
                if (field.getText().length() > 0) {
                    String value = field.getText();
                    value = value.replaceAll("[^a-zA-Z0-9 " + exceptions + "]", "");
                    field.setText(value);
                }
            }
        };
        field.lengthProperty().addListener(listener);
    }


    public static void nameField(final TextField field) {
        field.focusedProperty().addListener((observable, oldValue, newValue) -> {
            if(!newValue){
                String text = field.getText();
                String[] parts = text.split(" ");
                StringBuilder sb = new StringBuilder();
                for (String word : parts) {
                    if (word.length() > 2) {
                        word = word.substring(0, 1).toUpperCase() + word.substring(1);
                    }
                    sb.append(" ").append(word);
                }

                field.setText(sb.toString().replaceFirst(" ", ""));
                field.positionCaret(field.getText().length());
            }
        });
    }


    public static boolean isEmail(TextField field)  { // KeyPressed
        boolean is = false;
        if (!field.getText().isEmpty()) {
            if (field.getText().contains("@") && field.getText().contains(".") && !field.getText().contains(" ")) {

                String user = field.getText().substring(0, field.getText().lastIndexOf('@'));
                String domain = field.getText().substring(field.getText().lastIndexOf('@') + 1, field.getText().length());
                String subdomain = field.getText().substring(field.getText().indexOf(".") + 1, field.getText().length());

                if ((user.length() >= 1) && (!user.contains("@")) &&
                        (domain.contains(".")) && (!domain.contains("@"))
                        && (domain.indexOf(".") >= 1)
                        && (domain.lastIndexOf(".") < domain.length() - 1)
                        && subdomain.length() >= 2) {
                    is = true;
                }
            }
        }
        return is;
    }

    public static void emailField(TextField field){
        field.lengthProperty().addListener((observable, oldValue, newValue) -> {
            if (field.getText() != null) {
                if (field.getText().length() > 0) {
                    String value = field.getText();
                    value = value.replaceAll("[^a-zA-Z0-9@.]", "");
                    field.setText(value);

                }
            }
        });
    }

    public static void noLetters(final TextField textField) {

        textField.lengthProperty().addListener((observable, oldValue, newValue) -> {
            if (textField.getText() != null) {
                if (textField.getText().length() > 0) {
                    String value = textField.getText();
                    value = value.replaceAll("[a-zA-Zç]", "");
                    textField.setText(value);
                }
            }
        });
    }

    public static void monetaryField(final TextField field, Locale locale) {
        AtomicInteger position = new AtomicInteger(1);
        if (locale.toString().equals("pt_BR")) {
            field.lengthProperty().addListener((observable, oldValue, newValue) -> Platform.runLater(() -> {
                String value = field.getText();

                value = value.replaceAll("[^0-9]", "");
                value = value.replaceAll("([0-9])([0-9]{14})$", "$1.$2");
                value = value.replaceAll("([0-9])([0-9]{11})$", "$1.$2");
                value = value.replaceAll("([0-9])([0-9]{8})$", "$1.$2");
                value = value.replaceAll("([0-9])([0-9]{5})$", "$1.$2");
                value = value.replaceAll("([0-9])([0-9]{2})$", "$1,$2");

                field.setText(value);
                field.positionCaret(position.getAndIncrement());
            }));
        } else {
            field.lengthProperty().addListener((observable, oldValue, newValue) -> Platform.runLater(() -> {
                String value = field.getText();

                value = value.replaceAll("[^0-9]", "");
                value = value.replaceAll("([0-9])([0-9]{14})$", "$1,$2");
                value = value.replaceAll("([0-9])([0-9]{11})$", "$1,$2");
                value = value.replaceAll("([0-9])([0-9]{8})$", "$1,$2");
                value = value.replaceAll("([0-9])([0-9]{5})$", "$1,$2");
                value = value.replaceAll("([0-9])([0-9]{2})$", "$1.$2");

                field.setText(value);
                field.positionCaret(position.getAndIncrement());
            }));
        }
        field.addEventFilter(KeyEvent.KEY_PRESSED, event -> {
            switch (event.getCode()) {
                case BACK_SPACE:
                    field.positionCaret(position.decrementAndGet());
                    break;
            }
        });
    }
}
