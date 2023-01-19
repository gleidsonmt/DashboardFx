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
import io.github.gleidsonmt.gncontrols.material.icon.IconContainer;
import io.github.gleidsonmt.gncontrols.material.icon.Icons;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.scene.text.TextFlow;

/**
 * @author Gleidson Neves da Silveira | gleidisonmt@gmail.com
 * Version 0.0.1
 * Create on  18/01/2023
 */
public class TutorialCreator1 extends Container implements BuildCreator {

    private final ScrollPane scroll = new ScrollPane();
    private final HBox body = new HBox();
    private final VBox aside = new VBox();
    private final VBox center = new VBox();

    private final ObservableList<Node> items = FXCollections.observableArrayList();

    public TutorialCreator1(String name) {
        super(name);
        body.setPadding(new Insets(20));
        aside.setPadding(new Insets(20));
    }

    public TutorialCreator1 header(String name) {
        items.add(createHeader(null, name));
        return this;
    }

    public TutorialCreator1 header(Icons icon, String name) {
        items.add(createHeader(new IconContainer(icon), name));
        return this;
    }

    public TutorialCreator1 text(String text) {
        TextFlow textFlow = new TextFlow(new Text(text));
        VBox.setMargin(textFlow, new Insets(10, 0,0,0));
        items.add(textFlow);
        return this;
    }

    public Node createHeader(Node node, String _text) {
        if (node == null) {
            Text text = new Text(_text);
            configHeader(text);
            createNavHeader(text.getText(), text);
            return text;
        } else {
            Label label = new Label(_text);
            createNavHeader(label.getText(), label);
            label.setGraphic(node);
            configHeader(label);
            return label;
        }
    }

    public void configHeader(Node node) {
        node.getStyleClass().addAll("h3", "title");
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

        getChildren().setAll(body);
        body.getChildren().setAll(scroll, aside);
        scroll.setContent(center);

        aside.setMinWidth(250);

        scroll.setFitToHeight(true);
        scroll.setFitToWidth(true);

        HBox.setHgrow(center, Priority.ALWAYS);

        center.getChildren().setAll(items);

        center.setAlignment(Pos.TOP_LEFT);

        center.getChildren().forEach(c -> {
//            if (c instanceof Label item || c instanceof Text ) {
//                c.getStyleClass().addAll("text-12");

//            }
        });

        return this;
    }
}
