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

    @Override
    public void onEnter(Root root) {
        this.root = root;
        Presentation pres ;

        if (root.getPseudoClassStates().contains(PseudoClass.getPseudoClass("dark-theme"))) {
            pres = create().theme(io.github.gleidsonmt.blockcode.Theme.GITHUB_DARK).build();
        } else {
            pres = create().build();
        }

        Preferences prefs = Preferences.userNodeForPackage(MainScene.class);
        prefs.get("theme", "LIGHT");

        setContent(pres.getRoot());

        root.getPseudoClassStates().addListener((SetChangeListener<PseudoClass>) c -> {
           if (c.wasAdded()) {
               if (c.getElementAdded().equals(PseudoClass.getPseudoClass("dark-theme"))) {
                   System.out.println("dark-theme");
//                   setContent(null);

                   StackPane presentation = (StackPane) root.lookup(".presentation");
                   BorderPane border = (BorderPane) presentation.getChildren().get(0);
                   border.setCenter(null);
                   border.setCenter(create().theme(io.github.gleidsonmt.blockcode.Theme.GITHUB_DARK).build().getRoot());
               }
           } else if(c.wasRemoved())  {
               if (c.getElementRemoved().equals(PseudoClass.getPseudoClass("dark-theme"))) {
                   StackPane presentation = (StackPane) root.lookup(".presentation");
                   BorderPane border = (BorderPane) presentation.getChildren().get(0);
                   border.setCenter(null);
                   border.setCenter(create().theme(io.github.gleidsonmt.blockcode.Theme.GITHUB).build().getRoot());
//                   border.setCenter(create().theme(Theme.GITHUB).build().getRoot());
//                   setContent(create().theme(Theme.BESPIN).build().getRoot());
               }
           }
        });
        getContent().getStyleClass().addAll("font-instagram");
    }

    public Root getRoot() {
        return this.root;
    }
}
