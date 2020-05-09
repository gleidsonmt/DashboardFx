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
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Cursor;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import org.controlsfx.control.PopOver;

/**
 * @author Gleidson Neves da Silveira | gleidisonmt@gmail.com
 * Create on  01/04/2020
 */
public class BadgeNotification extends GNControl {

    private PopOver pop = new PopOver();

    public BadgeNotification() {
        this(null, null);
    }

    public BadgeNotification(String text, String subtitle) {
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
        badgeM.getStyleClass().addAll("icon", "icon-warning");

        badgeM.setText("3");

        StackPane control = new StackPane();
        control.setAlignment(Pos.CENTER);
        control.getStyleClass().add("icon-notification");
        control.setStyle("-fx-padding : 0px;");
        control.setPrefSize(30, 20);
        FontAwesomeIconView icon = new FontAwesomeIconView();
        icon.getStyleClass().add("icon");
        icon.setGlyphName("BELL");
        icon.setSize("16");
        control.getChildren().add(icon);

        badgeM.setControl(control);

        badgeM.setOnMouseClicked(event -> showPopup());
        Platform.runLater(this::configLayout);

        return badgeM;
    }

    private void showPopup() {
        if (!pop.isShowing())
            pop.show(this);
        else pop.hide();
    }

    private void configLayout() {
        String icon1 = "M1 21h22L12 2 1 21zm12-3h-2v-2h2v2zm0-4h-2v-4h2v4z";
        String icon2 = "M12 2C6.48 2 2 6.48 2 12s4.48 10 10 10 10-4.48 10-10S17.52 2 12 2zm1 15h-2v-6h2v6zm0-8h-2V7h2v2z";
        String icon3 = "M15.73 3H8.27L3 8.27v7.46L8.27 21h7.46L21 15.73V8.27L15.73 3zM12 17.3c-.72 0-1.3-.58-1.3-1.3 0-.72.58-1.3 1.3-1.3.72 0 1.3.58 1.3 1.3 0 .72-.58 1.3-1.3 1.3zm1-4.3h-2V7h2v6z";

        ObservableList<BadgeCellNotification> list = FXCollections.observableArrayList(
                new BadgeCellNotification(icon1, "Leak memory.", "icon-warning"),
                new BadgeCellNotification(icon2, "New 5 members.", "icon-info"),
                new BadgeCellNotification(icon3, "Broken tasks.", "icon-danger")
        );

        list.forEach(e -> {
            e.setOnMouseClicked(event -> pop.hide());
            e.setCursor(Cursor.HAND);
        });

        Separator top = new Separator();
        Separator bottom = new Separator();

        Label message = new Label("You have " + list.size() + " notifications");
        GridPane title = new GridPane();

        title.setAlignment(Pos.CENTER);
        title.add(message, 0, 0);
        title.setMinHeight(40);

        ListView<BadgeCellNotification> listView = new ListView<>();
        int fixedCell = 50;
        listView.setStyle("-fx-fixed-cell-size : " + fixedCell + "px;");

        listView.setFixedCellSize(50);

        listView.getItems().addAll(list);
        listView.getStyleClass().add("border-0");

        Hyperlink btn = new Hyperlink("Read all messages");
        btn.setAlignment(Pos.CENTER);
        btn.addEventFilter(MouseEvent.MOUSE_CLICKED, event -> {
            pop.hide();
        });

        VBox root = new VBox(title, top, listView, bottom, btn);
        root.setAlignment(Pos.CENTER);

        double height = list.size() > 4 ? 300 : (list.size() * fixedCell) + title.getMinHeight() + 35; // 35 is equal min height in all app more padding

        root.setPrefSize(300, height);
        title.setPrefWidth(root.getPrefWidth());
        message.setPrefWidth(root.getPrefWidth());
        title.setPadding(new Insets(0, 25, 0, 25));
        btn.setPrefWidth(root.getPrefWidth());

        listView.getStylesheets().add(getClass().getResource("/com/gn/theme/css/custom-scroll.css").toExternalForm());

        pop.setContentNode(root);
        pop.setArrowLocation(PopOver.ArrowLocation.TOP_RIGHT);
        pop.setArrowIndent(0);
        pop.setArrowSize(0);
        pop.setCloseButtonEnabled(false);
        pop.setHeaderAlwaysVisible(false);
        pop.setCornerRadius(0);

        pop.getRoot().getStylesheets().add(getClass().getResource("/com/gn/theme/css/poplight.css").toExternalForm());

        // tests

        pop.setAnimated(false);
    }
}
