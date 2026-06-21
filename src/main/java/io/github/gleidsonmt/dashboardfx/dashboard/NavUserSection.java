package io.github.gleidsonmt.dashboardfx.dashboard;

import io.github.gleidsonmt.dashboardfx.Main;
import io.github.gleidsonmt.dashboardfx.model.User;
import io.github.gleidsonmt.dashboardfx.utils.pages.ProfilePage;
import io.github.gleidsonmt.glad.base.Anchor;
import io.github.gleidsonmt.glad.base.Root;
import io.github.gleidsonmt.glad.base.dialog.WrapperEffect;
import io.github.gleidsonmt.glad.controls.avatar.AvatarStatus;
import io.github.gleidsonmt.glad.controls.icon.Icon;
import io.github.gleidsonmt.glad.controls.icon.SVGIcon;
import javafx.animation.TranslateTransition;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.geometry.Side;
import javafx.geometry.VPos;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.MenuItem;
import javafx.scene.control.SeparatorMenuItem;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.util.Duration;
import org.jetbrains.annotations.NotNull;

/**
 * @author Gleidson Neves da Silveira | gleidisonmt@gmail.com
 * Create on  16/10/2024
 */
public class NavUserSection extends HBox {

    private final GridPane content;
    private final Text email;
    private final Text name;
    private final SVGIcon icon;

    public NavUserSection(@NotNull User user) {
        AvatarStatus avatarView = new AvatarStatus(user.getAvatar());
        avatarView.setPrefWidth(40);
        avatarView.setMaxHeight(40);
        avatarView.setMinHeight(40);
        avatarView.setRadius(20);

        this.content = new GridPane();
        this.setSpacing(10);

        this.getChildren().addAll(avatarView, content);
        this.setId("header");
        this.name = createNameComponent(user.getName());
        this.email = createEmailComponent(user.getUsername());
        this.icon = createIcon();
        createOptions();
        init();
    }

    public void init() {
//        this.getChildren().setAll(avatarView, email, name);

        this.content.getChildren().addAll(name, email, icon);

        GridPane.setColumnIndex(name, 0);
        GridPane.setRowIndex(name, 0);

        GridPane.setColumnIndex(email, 0);
        GridPane.setRowIndex(email, 1);

        GridPane.setColumnIndex(icon, 1);
        GridPane.setRowIndex(icon, 1);

        GridPane.setValignment(name, VPos.BOTTOM);
        GridPane.setValignment(email, VPos.TOP);

        this.content.setHgap(10);
        VBox.setMargin(this, new Insets(0, 5, 20, 5));
    }

    private Text createNameComponent(String _text) {
        Text text = new Text(_text);
        text.getStyleClass().addAll("h4", "font-instagram", "bold");
        return text;
    }

    private SVGIcon createIcon() {
        SVGIcon svgIcon = new SVGIcon(Icon.EXPAND_ALL);
        svgIcon.setScale(0.8);
        return svgIcon;
    }

    private Text createEmailComponent(String _text) {
        Text text = new Text(_text);
        text.getStyleClass().addAll("h5", "font-instagram-headline");
        return text;
    }

    private void createOptions() {
        ContextMenu options = new ContextMenu();

        MenuItem menuSettings = new MenuItem("Settings");
        menuSettings.getStyleClass().add("menu-item-first");
        menuSettings.setGraphic(new SVGIcon(Icon.SETTINGS));

        menuSettings.setOnAction(_ -> {

            Root root = (Root) this.getScene().getRoot();
            var main = (Main) root.getContent();

            root.behavior()
                    .dialog()
                    .block()
                    .pos(Pos.CENTER_RIGHT)
                    .with(WrapperEffect.GRAY)
                    .content(main.getAside())
                    .anchor(Anchor.LEFT)
                    .insets(Insets.EMPTY)
                    .width(450)
                    .show();

            TranslateTransition transition = new TranslateTransition(Duration.millis(200), main.getAside().getParent());
            transition.setFromX(450);
            transition.setToX(0);
            transition.play();
        });

        MenuItem menuManageAccount = new MenuItem("Manage Account");
        menuManageAccount.setOnAction(_ -> {
            Root root = (Root) this.getScene().getRoot();
            var profile = new ProfilePage();
            profile.onEnter(root);
            ((Main) root.getContent()).setCenter(profile.getContent());
            root.update();
        });
        menuManageAccount.getStyleClass().add("menu-item-last");
        menuManageAccount.setGraphic(new SVGIcon(Icon.MANAGE_ACCOUNTS));

        options.getItems().addAll(menuManageAccount, new SeparatorMenuItem(), menuSettings);
        options.getStyleClass().add("drawer-context-menu");

        this.content.setOnMouseClicked(_ -> {
            if (options.isShowing()) return;
            options.show(this, Side.BOTTOM, 0, 0);
        });
    }
}
