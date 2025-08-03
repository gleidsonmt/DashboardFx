package io.github.gleidsonmt.dashboardfx.dashboard.notifications.component;

import io.github.gleidsonmt.dashboardfx.dashboard.notifications.factory.Notification;
import io.github.gleidsonmt.dashboardfx.dashboard.notifications.factory.NotificationManager;
import javafx.collections.ListChangeListener;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;


/**
 * @author Gleidson Neves da Silveira | gleidisonmt@gmail.com
 * Create on  23/04/2025
 */
public class NotificationBody extends ScrollPane {

    public NotificationBody(NotificationManager manager) {
        getStyleClass().add("notification-body");
        VBox container = new VBox();
        setContent(container);

        setFitToWidth(true);
        setFitToHeight(true);

//        setMinWidth(600);
//        setPrefWidth(600);
//        setMaxWidth(600);

        container.setFillWidth(true);
        container.setSpacing(10);

        Node emptyLabel = createEmptyBody();

        container.getChildren().setAll(manager.getFilteredNotifications());

        manager.getFilteredNotifications().addListener((ListChangeListener<NotificationItem<Notification>>) change -> {
            if (change.next()) {

                if (change.wasReplaced()) {
                    container.getChildren().setAll(change.getAddedSubList());
                    return;
                }

                if (change.wasAdded()) {
                    container.getChildren().remove(emptyLabel);
                    container.getChildren().addAll(change.getAddedSubList());
                }

                if (change.wasRemoved()) {
                    container.getChildren().removeAll(change.getRemoved());
                }

                if (change.getList().isEmpty()) {
                    container.getChildren().setAll(emptyLabel);
                }
            }
        });

    }

    private Node createEmptyBody() {
        VBox box = new VBox(new Text("You don't have any notification."));
        box.setAlignment(Pos.CENTER);
        box.setMinHeight(60);
        return box;
    }

}
