package io.github.gleidsonmt.dashboardfx.presentation;

import io.github.gleidsonmt.dashboardfx.MainScene;
import io.github.gleidsonmt.dashboardfx.dashboard.ActionableView;
import io.github.gleidsonmt.dashboardfx.dashboard.Theme;
import io.github.gleidsonmt.glad.base.Root;
import io.github.gleidsonmt.glad.base.drawer.View;
import io.github.gleidsonmt.presentation.Presentation;
import javafx.collections.SetChangeListener;
import javafx.css.PseudoClass;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.StackPane;

import java.util.prefs.Preferences;

/**
 * @author Gleidson Neves da Silveira | gleidisonmt@gmail.com
 * Create on  19/08/2025
 */
public abstract class CustomizablePresentation extends View implements ActionableView {

    private Theme theme;

    public CustomizablePresentation(String name) {
        this(name, Theme.LIGHT);
    }

    public CustomizablePresentation(String name, Theme theme) {
        super(name);
    }

    public abstract Presentation create();

    private Root root;

    SetChangeListener<PseudoClass> listenerThemeChange  = new SetChangeListener<>() {

        @Override
        public void onChanged(Change<? extends PseudoClass> c) {
            Preferences prefs = Preferences.userNodeForPackage(MainScene.class);

            if (c.wasAdded()) {
                if (c.getElementAdded().equals(PseudoClass.getPseudoClass("dark-theme"))) {

                    prefs.put("theme", "DARK");
                    ScrollPane scroll = (ScrollPane) root.getScene().lookup(".container");
                    scroll.setContent(create().build().getRoot());
                }
            } else if (c.wasRemoved()) {
                if (c.getElementRemoved().equals(PseudoClass.getPseudoClass("dark-theme"))) {
                    prefs.put("theme", "LIGHT");
                    ScrollPane scroll = (ScrollPane) root.getScene().lookup(".container");
                    scroll.setContent(create().build().getRoot());
                }
            }
        }
    };

    @Override
    public void onEnter(Root root) {
        this.root = root;

        setContent(create().build().getRoot());

        root.getPseudoClassStates().addListener(listenerThemeChange);
        getContent().getStyleClass().addAll("font-instagram");
    }

    @Override
    public void onExit(Root root) {
        root.getPseudoClassStates().removeListener(listenerThemeChange);
    }

    public Root getRoot() {
        return this.root;
    }
}
