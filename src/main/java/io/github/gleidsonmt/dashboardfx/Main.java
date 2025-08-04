package io.github.gleidsonmt.dashboardfx;

import io.github.gleidsonmt.dashboardfx.breadcrumb.BreadCrumbBar;
import io.github.gleidsonmt.dashboardfx.dashboard.ActionableView;
import io.github.gleidsonmt.dashboardfx.dashboard.notifications.factory.NotificationManager;
import io.github.gleidsonmt.dashboardfx.drawer.CardUserOptions;
import io.github.gleidsonmt.dashboardfx.drawer.Drawer;
import io.github.gleidsonmt.dashboardfx.model.User;
import io.github.gleidsonmt.dashboardfx.utils.Assets;
import io.github.gleidsonmt.glad.base.Layout;
import io.github.gleidsonmt.glad.base.Root;
import io.github.gleidsonmt.glad.base.internal.Module;
import io.github.gleidsonmt.glad.base.internal.View;
import io.github.gleidsonmt.glad.base.responsive.Break;
import io.github.gleidsonmt.glad.controls.badge.Badge;
import io.github.gleidsonmt.glad.controls.button.IconButton;
import io.github.gleidsonmt.glad.controls.icon.Icon;
import io.github.gleidsonmt.glad.controls.icon.SVGIcon;
import javafx.application.Platform;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.geometry.HPos;
import javafx.geometry.VPos;
import javafx.scene.Node;
import javafx.scene.control.ContentDisplay;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;


/**
 * @author Gleidson Neves da Silveira | gleidisonmt@gmail.com
 * Create on  10/06/2025
 */
public class Main extends BorderPane implements Layout {

    private VBox wrapper;
    private ScrollPane container;

    private IconButton hamb = new IconButton(new SVGIcon(Icon.MENU));
    private NavBar navBar = new NavBar();
    private BreadCrumbBar crumb = new BreadCrumbBar();

    private Drawer drawer;
    private CardUserOptions card;

    private ObjectProperty<Module> currentModule = new SimpleObjectProperty<>();

    public Main() {
        card = new CardUserOptions(new User(Assets.getImage("default_avatar.jpg", 80), "johndoe54@gmail.com", "Jhon Doe"));

        navBar.setMinHeight(60);
        navBar.add(crumb, 0, 0);
        Badge badgeMessage = new Badge(Icon.CHAT, 8, 10);
        badgeMessage.setStyle("-fx-box-color: -red-500;");
//
        Badge badgeNotification = new Badge(Icon.NOTIFICATION_IMPORTANT, 5, 10);
        badgeNotification.setStyle("-fx-box-color: -info;");

        NotificationManager notificationManager = new NotificationManager();
//        badgeNotification.setOnMouseClicked(e -> notificationManager.show(getScene(), e, navBar.getHeight() / 2));
        badgeNotification.setOnMouseClicked(e -> notificationManager.show(getScene(), navBar));

        navBar.add(badgeNotification, 2, 0);
        navBar.add(badgeMessage, 1, 0);
        navBar.add(card, 3, 0);

        GridPane.setValignment(crumb, VPos.CENTER);
        GridPane.setValignment(badgeNotification, VPos.CENTER);
        GridPane.setValignment(badgeMessage, VPos.CENTER);
        GridPane.setHalignment(card, HPos.RIGHT);
        GridPane.setValignment(card, VPos.CENTER);
        GridPane.setHgrow(crumb, Priority.ALWAYS);

        navBar.setHgap(10);
        navBar.getStyleClass().addAll("border-light-gray-2");
        navBar.setStyle("-fx-border-width: 0px 0px 2px 0px;");

        hamb.setCancelButton(true);

        hamb.setOnAction(e -> {
            Root root = (Root) this.getScene().getRoot();
            root.behavior().setDrawer(drawer);

            root.behavior().openDrawer();
        });


//        drawer.currentModuleProperty().addListener((_, oldValue, newValue) -> {
//            if (newValue != null) {
//                if (newValue instanceof View view) {
//                    if (view.getOnEnter() != null) view.getOnEnter().handle(new ActionEvent());
//                    updateView(view.getContent());
//                }
//            } else if (oldValue != null) {
//                if (oldValue instanceof View view) {
//                    if (view.getOnExit() != null) view.getOnExit().handle(new ActionEvent());
//                }
//            }
//        });
        init();
        configLayout();
        bind();

        Platform.runLater(() -> {
            Root root = (Root) this.getScene().getRoot();
            root.addPoint(_ -> {
                setLeft(null);
                navBar.getChildren().add(0, hamb);
                GridPane.setColumnIndex(crumb, 1);
                GridPane.setColumnIndex(badgeMessage, 2);
                GridPane.setColumnIndex(badgeNotification, 3);
                GridPane.setColumnIndex(card, 4);
            }, Break.MOBILE);

            root.addPoint(_ -> {
//            if (behavior().isDrawerAbsolute()) {
//                wrapper().hide();
//            }
//            getContainer().setLeft(drawer);
                setLeft(drawer);
                navBar.getChildren().remove(hamb);

                GridPane.setColumnIndex(crumb, 0);
                GridPane.setColumnIndex(badgeMessage, 1);
                GridPane.setColumnIndex(badgeNotification, 2);
                GridPane.setColumnIndex(card, 3);
            }, Break.SM, Break.MD, Break.LG, Break.XL, Break.XXL, Break.WIDE);
        });
    }

    @Override
    public Node getDrawer() {
        return this.getLeft();
    }

//    public Main updateContent(View view) {
//        super.setContent(view.getContent());
//        return this;
//    }

    private void init() {
        this.wrapper = new VBox();
        this.container = new ScrollPane();
        this.drawer = new Drawer();
        setLeft(drawer);
        setCenter(this.wrapper);

    }

    private void configLayout() {
        container.getStyleClass().addAll("fit-width fit-height".split(" "));
        this.wrapper.getChildren().setAll(navBar, container);
        VBox.setVgrow(container, Priority.ALWAYS);
    }

    private void bind() {
        currentModule.addListener((observable, oldValue, newValue) -> {
            if (newValue != null) {
                updateView(newValue);
            }
        });

        currentModule.bind(drawer.currentModuleProperty());
        crumb.currentModuleProperty().bind(currentModule);
    }

    private void updateView(Module node) {

        if (node instanceof View view) {
            this.container.setContent(view.getContent());

            if (view.getContent() instanceof ActionableView actionableView) {
                Platform.runLater(() -> {
                    Root root = (Root) this.getScene().getRoot();
                    actionableView.onEnter(root);
                });
            }

        }


    }

}