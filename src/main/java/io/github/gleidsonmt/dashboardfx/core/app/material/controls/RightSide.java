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

package io.github.gleidsonmt.dashboardfx.core.app.material.controls;

import io.github.gleidsonmt.dashboardfx.core.app.services.Context;
import io.github.gleidsonmt.gncontrols.material.icon.IconContainer;
import io.github.gleidsonmt.gncontrols.material.icon.Icons;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.VPos;
import javafx.scene.Node;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.Label;
import javafx.scene.control.Separator;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.scene.text.TextFlow;

public class RightSide extends VBox {

    private final Context context;

    public RightSide(Context context) {
        this.context = context;
        setPadding( new Insets(20));
        setPrefWidth(250);

        getStyleClass().addAll("border-l-1", "border-box");
        this.setSpacing(10);

        this.getChildren().setAll(
                createHeaderBox(),
                new Separator(),
                createSummaryLink(Icons.TOPIC),
                createControlLink(Icons.TOPIC, "Label"),
                new Separator(),
                createContent("""
                        Label is a non-editable text control. A Label is useful for displaying text that is required to fit within a specific space, and thus may need to use an ellipsis or truncation to size the string to fit. Labels also are useful in that they can have mnemonics which, if used, will send focus to the Control listed as the target of the labelFor property.
                        """),
                new Separator()

        );
    }

    private Node createHeaderBox() {
        Label title = createTitle("About");

        GridPane gridPane = new GridPane();
        gridPane.setHgap(10);
        IconContainer icon = new IconContainer(Icons.LAB_RESEARCH);
        gridPane.getChildren().setAll(title, icon);

        GridPane.setConstraints(icon, 0, 0, 1,1, HPos.LEFT, VPos.CENTER, Priority.SOMETIMES, Priority.ALWAYS);
        GridPane.setConstraints(title, 1, 0, 1,1, HPos.LEFT, VPos.CENTER, Priority.ALWAYS, Priority.ALWAYS);
        return gridPane;
    }

    private Label createTitle(String title) {
        Label label = new Label(title);
        label.getStyleClass().addAll("title");
        return label;
    }

    private Node createSummaryLink(Icons icon) {
        return createLink(icon, "javafx.scene.control", "https://openjfx.io/javadoc/17/javafx.controls/javafx/scene/control/package-summary.html");
    }

    private Hyperlink createLink(Icons icon, String title, String url) {
        Hyperlink hyperlink = new Hyperlink(title);
        hyperlink.setGraphic(new IconContainer(icon));
        hyperlink.getStyleClass().addAll("text-12");
        hyperlink.setOnAction(event -> {
            context.openLink(url);
        });
        return hyperlink;
    }

    private Node createControlLink(Icons icon, String name) {
        return createLink(icon, "javafx.scene.control." + name, "https://openjfx.io/javadoc/17/javafx.controls/javafx/scene/control/" + name + ".html");
    }

    private TextFlow createContent(String title) {
        TextFlow label = new TextFlow(new Text(title));
        label.getStyleClass().addAll("text-12");
        return label;
    }

}
