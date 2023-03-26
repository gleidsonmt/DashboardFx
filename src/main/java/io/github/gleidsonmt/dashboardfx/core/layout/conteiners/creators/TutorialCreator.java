/*
 *
 *    Copyright (C) Gleidson Neves da Silveira
 *
 *    This program is free software: you can redistribute it and/or modify
 *    it under the terms of the GNU General Public License as published by
 *    the Free Software Foundation, either version 3 of the License, or
 *   (at your option) any later version.
 *
 *    This program is distributed in the hope that it will be useful,
 *    but WITHOUT ANY WARRANTY; without even the implied warranty of
 *    MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *    GNU General Public License for more details.
 *
 *     You should have received a copy of the GNU General Public License
 *     along with this program.  If not, see <http://www.gnu.org/licenses/>.
 *
 */

package io.github.gleidsonmt.dashboardfx.core.layout.conteiners.creators;

import io.github.gleidsonmt.dashboardfx.core.app.interfaces.View;
import io.github.gleidsonmt.dashboardfx.core.app.material.controls.BuildCreator;
import io.github.gleidsonmt.dashboardfx.core.app.services.Context;
import io.github.gleidsonmt.gncontrols.controls.GNButton;
import io.github.gleidsonmt.gncontrols.material.icon.IconContainer;
import io.github.gleidsonmt.gncontrols.material.icon.Icons;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.ToggleButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.SVGPath;
import javafx.scene.web.WebView;

/**
 * @author Gleidson Neves da Silveira | gleidisonmt@gmail.com
 * Version 0.0.1
 * Create on  19/01/2023
 */
public class TutorialCreator extends PresentationCreator {

    private final ScrollPane scroll = new ScrollPane();
    private final HBox body = new HBox();
    private final VBox aside = new VBox();
    private final VBox center = new VBox();
    private final ToggleGroup group = new ToggleGroup();
    private final GNButton btnFloat = createButton();

    public TutorialCreator(String name, Context context) {
        super(name, context);
        body.setPadding(new Insets(20));
        aside.setPadding(new Insets(20));

        widthProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue.doubleValue() < 736) {
                body.getChildren().remove(aside);
                StackPane.setMargin(btnFloat, new Insets(10, 40, 10, 10));
            } else {
                if (!body.getChildren().contains(aside)) {
                    body.getChildren().add(aside);
                    StackPane.setMargin(btnFloat, new Insets(10, 40 + 250, 10, 10));
                }
            }
        });

    }

    public void createNavHeader(String text, Node node) {
        ToggleButton lbl = new ToggleButton(text);
        lbl.addEventHandler(MouseEvent.MOUSE_CLICKED, event -> {
            Scroll.scrollTo(scroll, node);
        });
        lbl.addEventHandler(MouseEvent.MOUSE_PRESSED, event -> {
            if (lbl.isSelected()){
                event.consume();
            }
        });
        lbl.setToggleGroup(group);
        lbl.getStyleClass().add("overview-item");

        aside.getChildren().add(lbl);
    }

    @Override
    public View build() {
        items.forEach(each -> {
            if (each instanceof Label lbl) {
                if (lbl.getStyleClass().contains("title")) {
                    createNavHeader(lbl.getText(), each);
                }
            }
        });

        getChildren().setAll(body);
        body.getChildren().setAll(scroll, aside);
        scroll.setContent(center);

        this.getChildren().add(btnFloat);
        this.setAlignment(Pos.BOTTOM_RIGHT);

        StackPane.setMargin(btnFloat, new Insets(10, 40 + 250, 10, 10));

        aside.setMinWidth(250);
        body.setSpacing(10);
        aside.getStyleClass().addAll("border-1", "border-l-1", "border-medium-gray");

        scroll.setFitToHeight(true);
        scroll.setFitToWidth(true);

        HBox.setHgrow(scroll, Priority.ALWAYS);

        center.getChildren().setAll(items);

        center.setAlignment(Pos.TOP_LEFT);

//        WebView webView = new WebView();
//        webView.getEngine().setJavaScriptEnabled(true);
//        webView.getEngine().loadContent("""
//                <iframe width="400" height="480" src="https://www.youtube.com/embed/hZsYU7UbWmU" title="Decorator JavaFx 8" frameborder="0" allow="accelerometer; autoplay; clipboard-write; encrypted-media; gyroscope; picture-in-picture; web-share" allowfullscreen></iframe>
//                """);
//        aside.getChildren().add(
//                webView
//        );

        return this;
    }

    private GNButton createButton() {
        GNButton button = new GNButton();
        button.getStyleClass().addAll("btn-float", "depth-1");
        SVGPath icon = new SVGPath();
        icon.setFill(Color.WHITE);
        icon.setContent("M11 20V7.825l-5.6 5.6L4 12l8-8 8 8-1.4 1.425-5.6-5.6V20Z");
        button.setGraphic(icon);
        icon.setStyle("-fx-fill: white");
        button.setOnAction(event -> {
            scroll.setVvalue(0);
        });
        return button;
    }
}
