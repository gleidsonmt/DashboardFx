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
package com.gn.global.factory.badges;

import com.gn.GNAvatarView;
import com.gn.decorator.component.GNControl;
import com.jfoenix.controls.JFXBadge;
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIconView;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Cursor;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.TextAlignment;
import org.controlsfx.control.PopOver;

/**
 * @author Gleidson Neves da Silveira | gleidisonmt@gmail.com
 * Create on  01/04/2020
 */
public class BadgeTasks extends GNControl {

    private PopOver pop = new PopOver();

    public BadgeTasks(){
        this(null, null);
    }

    public BadgeTasks(String text, String subtitle) {
        super(text, subtitle);
    }

    @Override
    public Node icon() {
        return null;
    }

    @Override
    public Node status() {
        return null;
    }

    @Override
    public Node action() {

        JFXBadge badgeM = new JFXBadge();
        badgeM.setPrefSize(35, 20);
        badgeM.setPosition(Pos.TOP_RIGHT);
        badgeM.getStyleClass().addAll("icon", "icon-danger");
        badgeM.setText("12");

        StackPane control = new StackPane();
        control.setAlignment(Pos.CENTER);
        control.getStyleClass().add("icon-alert");
        control.setStyle("-fx-padding : 0px;");
        control.setPrefSize(30,20);
        FontAwesomeIconView icon = new FontAwesomeIconView();
        icon.getStyleClass().add("icon");
        icon.setGlyphName("FLAG");
        icon.setSize("16");
        control.getChildren().add(icon);

        badgeM.setControl(control);

        badgeM.setOnMouseClicked(event -> {
            opeTasks();
        });

        return badgeM;
    }

    private void opeTasks() {
        if (!pop.isShowing()) {

            ObservableList<BadgeCellTask> list = FXCollections.observableArrayList(
                    new BadgeCellTask("Designed some buttons", 30, "progress-bar-danger"),
                    new BadgeCellTask("Create tasks", 80, "progress-bar-info"),
                    new BadgeCellTask("Create PopOvers", 60, "progress-bar-success")
            );

            list.forEach(e -> {
                e.setOnMouseClicked( event -> pop.hide() );
                e.setCursor(Cursor.HAND);
            } );

            Separator top = new Separator();
            Separator bottom = new Separator();

            Label message = new Label("You have " + list.size() + " tasks");
            GridPane title = new GridPane();
            title.setMinHeight(40D);

            title.setAlignment(Pos.CENTER);
            title.add(message, 0, 0);

            ListView<BadgeCellTask> listView = new ListView<>();
            int fixedCell = 50;
            listView.setStyle("-fx-fixed-cell-size : " + fixedCell + "px;");

            listView.getItems().addAll(list);
            listView.getStyleClass().add("border-0");

            Hyperlink btn = new Hyperlink("View all tasks");
            btn.setAlignment(Pos.CENTER);

            VBox root = new VBox(title, top, listView, bottom, btn);
            root.setAlignment(Pos.CENTER);

            double height = list.size() > 4 ? 300 : (list.size() * fixedCell) + title.getMinHeight() + 35; // 35 is equal min height in all app more padding

            root.setPrefSize(300, height);
            title.setPrefWidth(root.getPrefWidth());
            message.setPrefWidth(root.getPrefWidth());
            title.setPadding(new Insets(0, 25, 0, 25));
            btn.setPrefWidth(root.getPrefWidth());

            listView.getStylesheets().add(getClass().getResource("/com/gn/theme/css/custom-scroll.css").toExternalForm());
            pop.getRoot().getStylesheets().add(getClass().getResource("/com/gn/theme/css/poplight.css").toExternalForm());
            pop.setContentNode(root);
            pop.setArrowLocation(PopOver.ArrowLocation.TOP_RIGHT);
            pop.setArrowIndent(0);
            pop.setArrowSize(0);
            pop.setCloseButtonEnabled(false);
            pop.setHeaderAlwaysVisible(false);
            pop.setCornerRadius(0);

            // tests
//            pop.setAutoHide(false);
            pop.setAnimated(false);

            pop.show(this);

        } else
            pop.hide();
    }
}
