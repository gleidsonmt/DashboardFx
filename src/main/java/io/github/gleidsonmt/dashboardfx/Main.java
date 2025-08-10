package io.github.gleidsonmt.dashboardfx;

import io.github.gleidsonmt.dashboardfx.breadcrumb.BreadCrumbBar;
import io.github.gleidsonmt.dashboardfx.dashboard.ActionableView;
import io.github.gleidsonmt.dashboardfx.dashboard.notifications.factory.NotificationManager;
import io.github.gleidsonmt.dashboardfx.drawer.CardUserOptions;
import io.github.gleidsonmt.dashboardfx.drawer.Drawer;
import io.github.gleidsonmt.dashboardfx.model.User;
import io.github.gleidsonmt.dashboardfx.presentation.ProfileView;
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
import javafx.scene.layout.*;
import javafx.scene.web.WebView;


/**
 * @author Gleidson Neves da Silveira | gleidisonmt@gmail.com
 * Create on  10/06/2025
 */
public class Main extends BorderPane implements Layout {

    private VBox wrapper;
    private ScrollPane container;
    private final NavBar navBar = new NavBar(60);
    private Drawer drawer;
    private final ObjectProperty<Module> currentModule = new SimpleObjectProperty<>();

    public Main() {

        init();
        configLayout();
        bind();

        Platform.runLater(() -> {
            Root root = (Root) this.getScene().getRoot();
            root.addPoint(_ -> {
                setLeft(null);
            }, Break.MOBILE);

            root.addPoint(_ -> {
                setLeft(drawer);
            }, Break.SM, Break.MD, Break.LG, Break.XL, Break.XXL, Break.WIDE);
        });
    }

    @Override
    public Node getDrawer() {
        return this.drawer;
    }

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
        currentModule.addListener((_, oldValue, newValue) -> {
            if (newValue != null) {
                updateView(oldValue, newValue);
            }

        });

        currentModule.bind(drawer.currentModuleProperty());
        navBar.currentModuleProperty().bind(currentModule);
    }

    @Override
    public void updateView(Module oldVal, Module newVal) {

        if (newVal instanceof View view) {
            this.container.setContent(view.getContent());

            if (view.getContent() instanceof ActionableView actionableView) {
                Platform.runLater(() -> {
                    Root root = (Root) this.getScene().getRoot();
                    actionableView.onEnter(root);
                });

            }
            if (oldVal != null) {
                if (oldVal instanceof ActionableView actionableView) {
                    Platform.runLater(() -> {
                        Root root = (Root) this.getScene().getRoot();
                        actionableView.onExit(root);
                    });
                }
            }

        }


    }

}