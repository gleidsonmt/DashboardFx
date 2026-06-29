package io.github.gleidsonmt.dashboardfx.dashboard.notifications.component;

import io.github.gleidsonmt.dashboardfx.dashboard.notifications.CommentNotification;
import io.github.gleidsonmt.dashboardfx.dashboard.notifications.factory.Notification;
import io.github.gleidsonmt.dashboardfx.dashboard.notifications.factory.NotificationType;
import io.github.gleidsonmt.dashboardfx.utils.StringUtils;
import io.github.gleidsonmt.glad.controls.avatar.AvatarView;
import io.github.gleidsonmt.glad.controls.button.Button;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.CacheHint;
import javafx.scene.Node;
import javafx.scene.control.ContentDisplay;
import javafx.scene.control.Label;
import javafx.scene.control.ToggleButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.*;
import javafx.scene.shape.Circle;
import javafx.scene.text.Text;
import javafx.scene.text.TextFlow;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import static javafx.scene.layout.GridPane.REMAINING;

/**
 * @author Gleidson Neves da Silveira | gleidisonmt@gmail.com
 * Create on  24/04/2025
 */
public class NotificationItem<T extends Notification> extends ToggleButton {

    private final ToggleGroup group = new ToggleGroup();
    private final T value;

    public NotificationItem(T value) {
        this.value = value;
        getStyleClass().add("notification-item");
        setContentDisplay(ContentDisplay.GRAPHIC_ONLY);
        setMaxWidth(Double.MAX_VALUE);
        setText(value.getType().name());
        create(value);
    }

    public T getValue() {
        return value;
    }

    @Override
    public String toString() {
        return String.valueOf(getValue().getType());
    }

    private void create(Notification notification) {
        group.getToggles().add(this);
        GridPane card = createCard();
        this.maxHeightProperty().bind(card.heightProperty());
        this.setGraphic(card);

        TextFlow title = new TextFlow();
        Text titleUsername = new Text(notification.getUser().getUsername());
        titleUsername.getStyleClass().addAll("h5", "bold");

        title.getChildren().add(titleUsername);

        String formated = DateTimeFormatter
                .ofPattern("EEEE hh:mm a")
                .format(notification.getDateTime());

        Text legend = new Text(StringUtils.capitalize(formated));
        legend.getStyleClass().addAll("h6");

        Node avatar =
                notification.getType() == NotificationType.LIKE ?
                        createLikedAvatar(new AvatarView(notification.getUser().getAvatar(), 50)) :
                        new AvatarView(notification.getUser().getAvatar(), 50);

        card.add(avatar, 0, 0);
        card.add(title, 1, 0);
        card.add(legend, 1, 1);

        Text passed =  new Text(StringUtils.formatDuration(notification.getDateTime(), LocalDateTime.now()));

        if (!notification.visualized()) {
            Circle circle = new Circle();
            circle.setStyle("-fx-fill: -fx-accent;");
            circle.setRadius(5);
            card.add(circle, 3, 0);
            GridPane.setHalignment(circle, HPos.RIGHT);
        }

        card.add(passed, 2, 1);
        GridPane.setHalignment(passed, HPos.RIGHT);
        GridPane.setColumnSpan(passed, REMAINING);
        GridPane.setRowSpan(avatar, 2);
        GridPane.setHgrow(title, Priority.ALWAYS);

        switch (notification.getType()) {
            case FOLLOW -> {
                Text textContent = new Text(" followed you");
                textContent.getStyleClass().addAll("h5");
                title.getChildren().add(new TextFlow(textContent));
            }
            case COMMENT -> {
                Text textContent = new Text(" commented on your post");
                textContent.getStyleClass().addAll("h5");

                title.getChildren().add(new TextFlow(textContent));

                if (notification instanceof CommentNotification commentNotification) {
                    Text comment = new Text(commentNotification.getPost());
                    TextFlow flow = new TextFlow(comment);
                    flow.getStyleClass().addAll("padding-2", "bg-light-gray", "rounded", "bold");
                    card.add(flow, 1, 2);
                    GridPane.setColumnSpan(flow, REMAINING);
                }

            }

            case LIKE -> {
                Text textContent = new Text(" liked your post");
                textContent.getStyleClass().addAll("h5");
                title.getChildren().add(textContent);
            }

            case INVITE -> {
                Text textContent = new Text(" invited you to join the DashboardFX community");
                textContent.getStyleClass().addAll("h5");
                title.getChildren().add(textContent);
                card.add(createActions(), 1, 2);
            }
        }

    }

    private Node createActions() {
        HBox box = new HBox();
        Button confirm = new Button("Accept");
        Button cancel = new Button("Decline");
        cancel.setCancelButton(true);
        box.getChildren().addAll(cancel,confirm);
        box.setSpacing(10);
        box.setPadding(new Insets(10));
        return box;
    }

    private Node createLikedAvatar(AvatarView avatarView) {
        Label label = new Label();
        label.setContentDisplay(ContentDisplay.GRAPHIC_ONLY);
        label.getStyleClass().addAll( "bg-accent", "heart", "padding-5", "border-1", "border-white", "rounded",  "text-white");
        double w = 15, h = 15;
        label.setCache(true);
        label.setCacheHint(CacheHint.QUALITY);
        label.setPrefSize(w, h);
        label.setMinSize(w, h);
        label.setMaxSize(w, h);
        StackPane.setAlignment(label, Pos.BOTTOM_RIGHT);
        return new StackPane(avatarView, label);
    }

    private GridPane createCard() {
        GridPane card = new GridPane();
        card.setHgap(15);
        card.setPadding(new Insets(5));

        VBox.setVgrow(card, Priority.ALWAYS);
//
//        ColumnConstraints one = new ColumnConstraints();
//        one.setPercentWidth(10);
//        ColumnConstraints two = new ColumnConstraints();
//        two.setPercentWidth(70);
//        ColumnConstraints three = new ColumnConstraints();
//        three.setPercentWidth(10);
//        ColumnConstraints four = new ColumnConstraints();
//        four.setPercentWidth(10);
//        card.getColumnConstraints().addAll(one, two, three, four);

        return card;
    }
}
