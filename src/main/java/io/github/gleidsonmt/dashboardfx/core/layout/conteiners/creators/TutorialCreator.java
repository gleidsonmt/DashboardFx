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
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
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

    public TutorialCreator(String name, Context context) {
        super(name, context);
        body.setPadding(new Insets(20));
        aside.setPadding(new Insets(20));
    }

    public void createNavHeader(String text, Node node) {
        Label lbl = new Label(text);
        lbl.addEventHandler(MouseEvent.MOUSE_CLICKED, event -> {
            Scroll.scrollTo(scroll, node);
        });
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
}
