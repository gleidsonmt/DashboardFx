package io.github.gleidsonmt.dashboardfx.dashboard.notifications.component;

import javafx.scene.Node;
import javafx.scene.layout.VBox;

/**
 * @author Gleidson Neves da Silveira | gleidisonmt@gmail.com
 * Create on  25/04/2025
 */
public class NotificationPane extends VBox {

    public NotificationPane(Node... children) {
        super(children);
        getStyleClass().addAll("rounded", "padding-10", "depth-1", "notification-pane", "bg-theme");
        setSpacing(10);
//        setPrefSize(500, 300);
//        setMinHeight(400);
//        setPrefWidth(93);
//        setMaxWidth(93);
    }
}
