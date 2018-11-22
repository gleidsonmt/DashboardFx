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
package com.gn.module.designer;

import com.jfoenix.controls.JFXPopup;
import eu.hansolo.colors.ColorHelper;
import eu.hansolo.colors.MaterialDesign;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.css.PseudoClass;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Cursor;
import javafx.scene.control.Label;
import javafx.scene.control.ToggleButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.control.Tooltip;
import javafx.scene.input.Clipboard;
import javafx.scene.input.ClipboardContent;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.TextAlignment;

import java.net.URL;
import java.util.Locale;
import java.util.ResourceBundle;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author Gleidson Neves da Silveira | gleidisonmt@gmail.com
 * Create on  08/10/2018
 * Version 1.0
 */
public class Colors implements Initializable {

    private static final Pattern PATTERN    = Pattern.compile("(_[A]?[0-9]{2,3})");
    private static final Matcher MATCHER    = PATTERN.matcher("");
    private static final int     BOX_WIDTH  = 60;
    private static final int     BOX_HEIGHT = 60;

    private ToggleGroup group = new ToggleGroup();

    @FXML private GridPane grid;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        int  col  = 0;
        int  row  = 1;

        for (MaterialDesign color : MaterialDesign.values()) {
            String name     = color.name().replace("_", " ");
            String strWeb   = ColorHelper.web(color.get());
            String strRgb   = ColorHelper.rgb(color.get());
            String text     = String.join("", name, "\n", strWeb, "\n", strRgb);

            MATCHER.reset(color.name());
            String brightness = "";
            while (MATCHER.find()) {
                brightness = MATCHER.group(1).replace("_", " ");
            }

            ToggleButton toggle = new ToggleButton(
                    color.name().contains("0") ? "" : color.name() + "\n" + brightness);
            toggle.setToggleGroup(group);
            toggle.setAlignment(Pos.CENTER);
            toggle.setTextFill(Color.WHITE);
            toggle.setCursor(Cursor.HAND);
            toggle.setAlignment(Pos.CENTER);
            toggle.setPrefSize(BOX_WIDTH, BOX_HEIGHT);

            boolean isBox = false;

            if(!color.name().contains("0")){
                toggle.setMouseTransparent(true);
                toggle.setStyle("-fx-background-color : white; -fx-text-fill : -gray;" +
                        "-fx-border-color : transparent transparent -light-gray transparent;" +
                        "-fx-background-radius : 0");
                toggle.setMinWidth(100D);
            } else {
                toggle.setStyle("-fx-background-color : " +  formatHexString(color.get()) + ";" +
                        "-fx-background-radius : 0");
                isBox = true;
            }

            toggle.setOnMousePressed(event -> {
                String clipboardContent = "Color.web(\"" +
                        strWeb +
                        "\");\n" +
                        "Color." +
                        strRgb +
                        ";";

                Clipboard clipboard  = Clipboard.getSystemClipboard();
                ClipboardContent content    = new ClipboardContent();
                content.putString(clipboardContent);
                clipboard.setContent(content);
            });

            boolean finalIsBox = isBox;
            toggle.setOnMouseEntered(event -> {
                if(finalIsBox){
                toggle.setBackground(new Background(new BackgroundFill(color.get(), new CornerRadii(10), Insets.EMPTY)));
                toggle.setStyle("-fx-background-radius : 10px; -fx-background-color : " + formatHexString(color.get())
                        + ";");
                }
            });

            toggle.setOnMouseExited(event -> {
                if(finalIsBox){
                    toggle.setStyle("-fx-background-radius : 0px; -fx-background-color : " + formatHexString(color.get()) + ";");
                }
            });



            Tooltip tooltip = new Tooltip(text);
            Tooltip.install(toggle, tooltip);
            grid.add(toggle, col, row);
            col++;

            if (row > 16) {
                if (col == 11) {
                    col = 0;
                    row++;
                }
            } else {
                if (col == 15) {
                    col = 0;
                    row++;
                }
            }
        }
        grid.setHgap(1);
        grid.setVgap(1);
        grid.getRowConstraints().clear();
        grid.getColumnConstraints().clear();

    }

    private static String formatHexString(Color c) {
        if (c != null) {
            return String.format((Locale) null, "#%02x%02x%02x",
                    Math.round(c.getRed() * 255),
                    Math.round(c.getGreen() * 255),
                    Math.round(c.getBlue() * 255));
        } else {
            return null;
        }
    }
}
