package io.github.gleidsonmt.dashboardfx;

import io.github.gleidsonmt.dashboardfx.dashboard.ActionableView;
import io.github.gleidsonmt.dashboardfx.dashboard.Aside;
import io.github.gleidsonmt.dashboardfx.drawer.Drawer;
import io.github.gleidsonmt.glad.base.Layout;
import io.github.gleidsonmt.glad.base.Root;
import io.github.gleidsonmt.glad.base.internal.Module;
import io.github.gleidsonmt.glad.base.internal.View;
import io.github.gleidsonmt.glad.base.responsive.Break;
import javafx.application.Platform;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.scene.Node;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Priority;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;


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
    private Aside aside;

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
    public Region getDrawer() {
        return this.drawer;
    }

    @Override
    public Region getAside() {
        return this.aside;
    }

    private void init() {
        this.wrapper = new VBox();
        this.container = new ScrollPane();
        this.drawer = new Drawer();
        this.aside = new Aside();

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

        currentModule.bindBidirectional(drawer.currentModuleProperty());
        navBar.currentModuleProperty().bind(currentModule);
    }

    @Override
    public void updateView(Module oldVal, Module newVal) {
        if (newVal instanceof View view) {
            if (view instanceof ActionableView actionableView) {
                Platform.runLater(() -> {
                    Root root = (Root) this.getScene().getRoot();
                    root.init();
                    actionableView.onEnter(root);
                    this.container.setContent(view.getContent());
                });
            } else {
                this.container.setContent(view.getContent());
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

    @Override
    public ObjectProperty<Module> currentModuleProperty() {
        return this.currentModule;
    }

    @Override
    public Module getCurrentModule() {
        return this.currentModule.get();
    }

    @Override
    public void setCurrentModule(Module module) {
        this.currentModule.set(module);
    }
}