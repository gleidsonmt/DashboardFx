package io.github.gleidsonmt.dashboardfx;

import io.github.gleidsonmt.dashboardfx.breadcrumb.BreadCrumbBar;
import io.github.gleidsonmt.dashboardfx.dashboard.notifications.factory.NotificationManager;
import io.github.gleidsonmt.dashboardfx.dashboard.NavUserSection;
import io.github.gleidsonmt.dashboardfx.model.User;
import io.github.gleidsonmt.dashboardfx.utils.Assets;
import io.github.gleidsonmt.glad.base.Root;
import io.github.gleidsonmt.glad.base.Module;
import io.github.gleidsonmt.glad.base.responsive.DefaultBreak;
import io.github.gleidsonmt.glad.controls.badge.Badge;
import io.github.gleidsonmt.glad.controls.button.IconButton;
import io.github.gleidsonmt.glad.controls.icon.Icon;
import io.github.gleidsonmt.glad.controls.icon.SVGIcon;
import javafx.application.Platform;
import javafx.beans.property.ObjectProperty;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.layout.*;

/**
 * @author Gleidson Neves da Silveira | gleidisonmt@gmail.com
 * Create on  10/06/2025
 */
public class NavBar extends GridPane {

    private final IconButton hamb = new IconButton(new SVGIcon(Icon.MENU));

    private final Badge badgeMessage = new Badge(Icon.CHAT, 8, 10);
    private final Badge badgeNotification = new Badge(Icon.NOTIFICATION_IMPORTANT, 5, 10);

    private final BreadCrumbBar crumb = new BreadCrumbBar();
    private final NavUserSection card = new NavUserSection(
            new User(Assets.getImage("default_avatar.jpg", 80), "johndoe54@gmail.com", "Jhon Doe")
    );

    private final double maxHeight;
    private final double height;

    private final HBox left = new HBox();
    private final HBox right = new HBox();

    public NavBar(double height) {
        setPadding(new Insets(10));
        maxHeight = height * 2;
        this.height = height;
        updateHeight(height);
        setAlignment(Pos.CENTER_LEFT);
        setHgap(10);
        getStyleClass().addAll("border-light-gray-2");
        setStyle("-fx-border-width: 0px 0px 2px 0px;");
        init();
        configLayout();
    }

    private void updateHeight(double height) {
        setMinHeight(height);
        setMaxHeight(height);
        setPrefHeight(height);
    }

    private void init() {
        hamb.setCancelButton(true);

        hamb.setOnAction(_ -> {
            Root root = (Root) this.getScene().getRoot();
            root.behavior().openDrawer();
        });

        badgeMessage.setStyle("-fx-box-color: -red-500;");
        badgeNotification.setStyle("-fx-box-color: -info;");

        NotificationManager notificationManager = new NotificationManager();
        badgeNotification.setOnMouseClicked(_ -> notificationManager.show(getScene(), badgeNotification));


        Platform.runLater(() -> {
            Root root = (Root) this.getScene().getRoot();

            root.addBreakpoint(_ -> {
                getColumnConstraints().clear();
                getRowConstraints().clear();
                GridPane.setConstraints(left, 0, 0, 1, 1);
                GridPane.setConstraints(right, 0, 1, 1, 1);
                left.getChildren().addFirst(hamb);
                updateHeight(maxHeight);
                right.setAlignment(Pos.CENTER);

            }, DefaultBreak.SM);

            root.addBreakpoint(_ -> {
                left.getChildren().removeAll(hamb);
                getColumnConstraints().clear();
                getRowConstraints().clear();
                updateHeight(height);
                GridPane.setConstraints(left, 0, 0, 1, 1);
                GridPane.setConstraints(right, 1, 0, 1, 1);
                right.setAlignment(Pos.CENTER_RIGHT);
            }, DefaultBreak.SM, DefaultBreak.MD, DefaultBreak.LG, DefaultBreak.XL, DefaultBreak.XXL, DefaultBreak.WIDE);
        });
    }

    private void configLayout() {
        add(left, 0, 0);
        add(right, 1, 0);

        right.setSpacing(10);
        left.getChildren().add(crumb);
        right.getChildren().addAll(badgeMessage, badgeNotification, card);
        left.setAlignment(Pos.CENTER_LEFT);
        right.setAlignment(Pos.CENTER_RIGHT);
        GridPane.setHgrow(left, Priority.ALWAYS);
        GridPane.setHgrow(right, Priority.ALWAYS);
    }

    public ObjectProperty<Module> currentModuleProperty() {
        return crumb.currentModuleProperty();
    }
}
