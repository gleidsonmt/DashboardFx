package io.github.gleidsonmt.dashboardfx;

import io.github.gleidsonmt.dashboardfx.dashboard.ActionableView;
import io.github.gleidsonmt.dashboardfx.dashboard.Aside;
import io.github.gleidsonmt.dashboardfx.drawer.SideNav;
import io.github.gleidsonmt.glad.base.*;
import io.github.gleidsonmt.glad.base.Module;
import io.github.gleidsonmt.glad.base.drawer.Drawer;
import io.github.gleidsonmt.glad.base.drawer.ModuleSeparator;
import io.github.gleidsonmt.glad.base.responsive.DefaultBreak;
import io.github.gleidsonmt.glad.drawer.DrawerItem;
import io.github.gleidsonmt.glad.drawer.DrawerMenu;
import io.github.gleidsonmt.glad.drawer.DrawerSeparator;
import javafx.application.Platform;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
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
    private Aside aside;

    private final ObjectProperty<Module> currentModule = new SimpleObjectProperty<>();

    public Main() {

        init();
        configLayout();
        bind();

        Platform.runLater(() -> {
            Root root = (Root) this.getScene().getRoot();
            root.addBreakpoint(_ -> setLeft(null), DefaultBreak.SM);
            root.addBreakpoint(_ -> setLeft(drawer),  ">MD");
            
        });
    }

    public Region getDrawer() {
        return this.drawer;
    }

    public Region getAside() {
        return this.aside;
    }

    private void init() {
        this.wrapper = new VBox();
        this.container = new ScrollPane();
        this.aside = new Aside();
        this.drawer = new SideNav();

        drawer.setCellFactory(param -> {
            switch (param) {
                case View view -> {
                    return new DrawerItem(view);
                }
                case ModuleSeparator separator -> {
                    return new DrawerSeparator(separator);
                }
                case ModuleView menu -> {
                    return new DrawerMenu(menu);
                }
                case null, default -> {
                    assert param != null;
                    return new DrawerItem(param);
                }
            }
        });

        setLeft(getDrawer());
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
                    root.update();
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
    public Module getModule() {
        return this.currentModule.get();
    }

    @Override
    public void setModule(Module module) {
        this.currentModule.set(module);
    }
}