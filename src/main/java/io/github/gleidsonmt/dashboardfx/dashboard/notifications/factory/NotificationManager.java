package io.github.gleidsonmt.dashboardfx.dashboard.notifications.factory;

import io.github.gleidsonmt.dashboardfx.dashboard.CloseButton;
import io.github.gleidsonmt.dashboardfx.dashboard.notifications.CommentNotification;
import io.github.gleidsonmt.dashboardfx.dashboard.notifications.FollowNotification;
import io.github.gleidsonmt.dashboardfx.dashboard.notifications.InviteNotification;
import io.github.gleidsonmt.dashboardfx.dashboard.notifications.component.*;
import io.github.gleidsonmt.dashboardfx.model.User;
import io.github.gleidsonmt.dashboardfx.utils.Assets;
import io.github.gleidsonmt.glad.base.Root;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Separator;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Region;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

/**
 * @author Gleidson Neves da Silveira | gleidisonmt@gmail.com
 * Create on  23/04/2025
 */
@SuppressWarnings("unused")
public class NotificationManager {

    private final NotificationPane pane;
    private final ObservableList<NotificationItem<Notification>> notifications;
    private final FilteredList<NotificationItem<Notification>> filteredNotifications;

    public NotificationManager() {
        this(
                new NotificationItem<>(
                        new FollowNotification(
                                new User(
                                        Assets.getImage("default_avatar.jpg", 80),
                                        "@gleidsonmt", "Gleidson Neves"
                                ),
                                LocalDateTime.of(LocalDate.of(2024, 11, 2), LocalTime.of(14, 23)),
                                false
                        )
                ),
                new NotificationItem<>(
                        new FollowNotification(new User(Assets.getImage("avatar1.png", 70), "@noelly", "Noelly Richards"), LocalDateTime.of(LocalDate.of(2025, 2, 22), LocalTime.of(12, 12)), false)
                ),
                new NotificationItem<>(
                        new CommentNotification(new User(Assets.getImage("avatar2.jpg", 70), "@noelly", "Noelly Richards"), LocalDateTime.of(LocalDate.of(2025, 2, 22), LocalTime.of(12, 12)), false,
                                "Love the background on this! Wold love to learn how to create the mesh gradient effect.")
                ),
                new NotificationItem<>(
                        new InviteNotification(new User(Assets.getImage("avatar4.png", 70), "@noelly", "Noelly Richards"), LocalDateTime.of(LocalDate.of(2025, 3, 05), LocalTime.of(12, 12)), false)
                ),
                new NotificationItem<>(
                        new FollowNotification(new User(Assets.getImage("default_avatar.jpg", 70), "@noelly", "Noelly Richards"), LocalDateTime.of(LocalDate.of(2025, 2, 22), LocalTime.of(12, 12)), false)
                )

        );
    }

    public void show(Scene scene, Region target) {
        Root root = (Root) scene.getRoot();

        Region old = (Region) root.getChildren().getFirst();

        if (!root.flow().fits(pane)) {
            root.getChildren().clear();
            root.getChildren().add(pane);
            root.flow().clearConstraints(pane);
            useHeader(_ -> root.getChildren().setAll(old));
            root.widthProperty().addListener(new ChangeListener<>() {
                @Override
                public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {
                    root.getChildren().setAll(old);
                    root.widthProperty().removeListener(this);
                    removeHeader();
                }
            });
        } else {

            pane.setOnMouseExited(e -> root.flow().remove(pane));
            root.flow()
                    .content(pane)
                    .width(500)
//                    .height(500)
                    .pos(Pos.BOTTOM_CENTER)
                    .insets(new Insets(0))
                    .show(target);
            pane.requestFocus();
        }
    }


    public void show(Scene scene, MouseEvent e, double y) {
        Root root = (Root) scene.getRoot();

        Region old = (Region) root.getChildren().getFirst();

        if (!root.flow().fits(pane)) {
            root.flow().clearConstraints(pane);
            root.flow().remove(pane);
            root.getChildren().clear();
            root.getChildren().add(pane);
            useHeader(_ -> root.getChildren().setAll(old));
            root.widthProperty().addListener(new ChangeListener<>() {
                @Override
                public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {
                    root.getChildren().setAll(old);
                    root.widthProperty().removeListener(this);
                    removeHeader();
                }
            });
        } else {
            root.wrapper()
                    .onClick(_ -> {
                        root.wrapper().hide();
                        root.flow().remove(pane);
                    })
                    .show();
            root.flow()
                    .content(pane)
                    .pos(Pos.BOTTOM_CENTER)
                    .insets(new Insets(y,0,0,0))
                    .show();
        }

    }

    @SafeVarargs
    public NotificationManager(NotificationItem<Notification>... _notifications) {
        notifications = FXCollections.observableArrayList(_notifications);
        filteredNotifications = new FilteredList<>(notifications, _ -> true);

        pane = new NotificationPane(
                new NotificationHeader(this),
                new Separator(),
                new NotificationBody(this),
                new Separator(),
                new NotificationFooter()
        );
    }

    public void useHeader(EventHandler<ActionEvent> event) {
        pane.getChildren().addFirst(new CloseButton(event));
    }

    public void removeHeader() {
        pane.getChildren().removeFirst();
    }

    public void add(NotificationItem<Notification> notification) {
        notifications.add(notification);
    }

    @SafeVarargs
    public final void addAll(NotificationItem<Notification>... notification) {
        notifications.addAll(notification);
    }

    @SafeVarargs
    public final void removeAll(NotificationItem<Notification>... notification) {
        notifications.removeAll(notification);
    }

    public void remove(NotificationItem<Notification> notification) {
        notifications.remove(notification);
    }

    public void remove(int from, int to) {
        notifications.remove(from, to);
    }

    public void remove(int index) {
        notifications.remove(index);
    }

    public void clear() {
        notifications.clear();
    }

    public Region getRoot() {
        return pane;
    }

    public ObservableList<NotificationItem<Notification>> getNotifications() {
        return notifications;
    }

    public FilteredList<NotificationItem<Notification>> getFilteredNotifications() {
        return filteredNotifications;
    }


}
