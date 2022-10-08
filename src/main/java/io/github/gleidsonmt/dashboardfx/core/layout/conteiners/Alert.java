/*
 *    Copyright (C) Gleidson Neves da Silveira
 *
 *    This program is free software: you can redistribute it and/or modify
 *    it under the terms of the GNU General Public License as published by
 *    the Free Software Foundation, either version 3 of the License, or
 *    (at your option) any later version.
 *
 *    This program is distributed in the hope that it will be useful,
 *    but WITHOUT ANY WARRANTY; without even the implied warranty of
 *    MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *    GNU General Public License for more details.
 *
 *    You should have received a copy of the GNU General Public License
 *    along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */

package io.github.gleidsonmt.dashboardfx.core.layout.conteiners;

import animatefx.animation.Pulse;
import io.github.gleidsonmt.dashboardfx.core.layout.Wrapper;
import io.github.gleidsonmt.gncontrols.controls.GNButton;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.ButtonBar;
import javafx.scene.control.Label;
import javafx.scene.layout.Priority;
import javafx.scene.layout.Region;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;
import javafx.scene.text.TextFlow;
import org.jetbrains.annotations.NotNull;
import org.kordamp.ikonli.javafx.FontIcon;
import org.kordamp.ikonli.material.Material;

/**
 * @author Gleidson Neves da Silveira | gleidisonmt@gmail.com
 * Create on  05/10/2022
 */
public class Alert {

    private StackPane container;
    private VBox body;
    private Wrapper wrapper;

    private AlertType alertType = AlertType.WARNING;
    private String title;
    private String text;

    public Alert(Wrapper _wrapper) {
        this.wrapper = _wrapper;

        this.container = new StackPane();
        this.body = new VBox();
        this.body.setAlignment(Pos.TOP_CENTER);

        this.container.setPrefSize(300, 300);
        this.container.setMaxWidth(Region.USE_PREF_SIZE);
        this.container.setMaxHeight(Region.USE_PREF_SIZE);
        container.getStyleClass().add("alert-container");

        body.getStyleClass().add("alert-body");


        this.container.getChildren().setAll(body);
    }

    public Alert type(AlertType _alertType) {
        this.alertType = _alertType;
        return this;
    }

    public Alert title(String _title) {
        this.title = _title;
        return this;
    }

    public Alert text(String _text) {
        this.text = _text;
        return this;
    }

    public void show() {

        if (text == null || text.isEmpty()) {
            try {
                throw new Exception("Error : Alert needs a text information.");
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        if (title == null || title.isEmpty()) {
            title = alertType.name();
        }

        switch (alertType) {
            case WARNING -> {
                body.getChildren().addAll(
                        createHeader("-sunflower", Material.WARNING, title),
                        createContent(text),
                        createFooter("-sunflower")
                );

            }
            case INFO -> {
                body.getChildren().addAll(
                        createHeader("-info", Material.INFO, title),
                        createContent(text)
                );
            }
            case ERROR -> {
                body.getChildren().addAll(
                        createHeader("-grapefruit", Material.ERROR, title),
                        createContent(text)
                );
            }

            case SUCCESS -> {
                body.getChildren().addAll(
                        createHeader("-mint", Material.DONE, title),
                        createContent(text)
                );

            }
        }



        this.wrapper.getChildren().setAll(container);
        Pulse animatin = new Pulse(container);
        animatin.play();
        wrapper.toFront();
    }

    public void close() {
        wrapper.toFront();
    }

    private @NotNull Node createContent(String _text) {
        TextFlow content = new TextFlow();
        content.setTextAlignment(TextAlignment.CENTER);

        content.setPadding(new Insets(20));

        Text text = new Text(_text);
        content.getChildren().add(text);
        text.getStyleClass().add("text-12");

        text.setTextAlignment(TextAlignment.CENTER);


        return content;
    }

    private VBox createHeader(String color, Material material, String _title) {

        VBox header = new VBox();
        header.setSpacing(20);
        header.getStyleClass().add("alert-header");

        header.setAlignment(Pos.CENTER);

        FontIcon fontIcon = new FontIcon(material);
        fontIcon.setIconSize(58);

        fontIcon.getStyleClass().add("ikon" + color);
        container.getStyleClass().add( color.replace("-", ""));

        Label title = new Label(_title);
        title.getStyleClass().addAll("h2", "sub");


        header.getChildren().addAll(fontIcon, title);

        VBox.setVgrow(header, Priority.ALWAYS);
        return header;
    }

    private ButtonBar createFooter(String _color) {


        ButtonBar buttonBar = new ButtonBar();
        buttonBar.setPadding(new Insets(10));

        // Create the ButtonBar instance

        // Create the buttons to go into the ButtonBar
        GNButton yesButton = new GNButton("Yes");
        ButtonBar.setButtonData(yesButton, ButtonBar.ButtonData.YES);
        yesButton.getStyleClass().add("btn-sunflower");

        GNButton noButton = new GNButton("No");
        ButtonBar.setButtonData(noButton, ButtonBar.ButtonData.NO);
        noButton.setCancelButton(true);

        // Add buttons to the ButtonBar
        buttonBar.getButtons().addAll(yesButton, noButton);


        return buttonBar;
    }



}
