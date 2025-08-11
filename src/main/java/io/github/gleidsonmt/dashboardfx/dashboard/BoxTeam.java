package io.github.gleidsonmt.dashboardfx.dashboard;

import io.github.gleidsonmt.dashboardfx.utils.Assets;
import io.github.gleidsonmt.glad.controls.avatar.AvatarView;
import io.github.gleidsonmt.glad.controls.avatar.StackedAvatar;
import io.github.gleidsonmt.glad.controls.icon.Icon;
import io.github.gleidsonmt.glad.controls.icon.SVGIcon;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.ContentDisplay;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;

/**
 * @author Gleidson Neves da Silveira | gleidisonmt@gmail.com
 * Create on  11/08/2025
 */
public class BoxTeam extends GridPane {
    public BoxTeam() {

        Text title = new Text("2,335");
        this.add(title, 0, 0);
        title.getStyleClass().addAll("h1 font-instagram".split(" "));

        Label percent = new Label("25%");
        percent.getStyleClass().addAll("h5 font-instagram text-success padding-5 border-2 border-success radius-10".split(" "));
        percent.setStyle("-fx-padding: 2px 10px 2px 10px; -fx-background-color: derive(-success, 100%);");
        var arrow = new SVGIcon(Icon.ARROW_RIGHT_ALT);
        arrow.setRotate(-90);
        percent.setGraphic(arrow);
        this.add(percent, 1, 0);

        StackedAvatar stackedAvatar = new StackedAvatar();
        stackedAvatar.setAvatarSize(80);
        stackedAvatar.setAvatarRadius(80);
        stackedAvatar.setMax(3);
        stackedAvatar.getStyleClass().add("contributors-avatar-stacked");
        stackedAvatar.setSpacing(20);

        stackedAvatar.getAvatarViews().setAll(
                new AvatarView(Assets.getImage("default_avatar.jpg")),
                new AvatarView(Assets.getImage("default_avatar.jpg")),
                new AvatarView(Assets.getImage("default_avatar.jpg"))
        );

        this.add(stackedAvatar, 0, 1);
        GridPane.setColumnSpan(stackedAvatar, GridPane.REMAINING);
        GridPane.setHgrow(stackedAvatar, Priority.ALWAYS);

        Hyperlink link = new Hyperlink("See all");
        link.setGraphic(new SVGIcon(Icon.ARROW_RIGHT_ALT));
        link.getStyleClass().addAll("h5".split(" "));
        this.add(link, 0, 2);
        link.setContentDisplay(ContentDisplay.RIGHT);
        link.setAlignment(Pos.CENTER);
        link.setMaxWidth(Double.MAX_VALUE);
        GridPane.setColumnSpan(link, GridPane.REMAINING);

        this.setPadding(new Insets(20));
        this.setHgap(10);
        this.setVgap(10);
        this.setAlignment(Pos.CENTER);
        VBox.setVgrow(this, Priority.ALWAYS);
    }
}
