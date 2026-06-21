package io.github.gleidsonmt.dashboardfx;

import io.github.gleidsonmt.dashboardfx.dashboard.Theme;
import io.github.gleidsonmt.dashboardfx.events.ThemeChangeEvent;
import javafx.application.Application;
import javafx.application.ColorScheme;
import javafx.application.Platform;
import javafx.css.PseudoClass;
import javafx.event.Event;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.util.prefs.Preferences;

/**
 * @author Gleidson Neves da Silveira | <a href="mailto:gleidisonmt@gmail.com">gleidisonmt@gmail.com</a> <br>
 * Created on 17/06/2026
 */
public class MainScene extends Scene {

    public MainScene(Parent root, double width, double height) {
        super(root, width, height);

        Preferences prefs = Preferences.userNodeForPackage(MainScene.class);


        if (prefs.get("theme", "LIGHT").equals("DARK")) {
            root.pseudoClassStateChanged(PseudoClass.getPseudoClass("dark-theme"), true);
        }

        root.addEventFilter(ThemeChangeEvent.ANY, event -> {

            if (event.getTheme() == Theme.AUTO) { // if auto get from system
                Platform.Preferences platPreferences = Platform.getPreferences();
                ColorScheme colorScheme = platPreferences.getColorScheme();
                root.pseudoClassStateChanged(PseudoClass.getPseudoClass("dark-theme"), colorScheme == ColorScheme.DARK);
                prefs.put("theme", colorScheme == ColorScheme.DARK ? "DARK" : "LIGHT");
            } else {
                root.pseudoClassStateChanged(PseudoClass.getPseudoClass("dark-theme"), event.getTheme() == Theme.DARK);
                prefs.put("theme", event.getTheme() == Theme.DARK ? "DARK" : "LIGHT");
            }

        });

        Platform.getPreferences().colorSchemeProperty().addListener((_, _, newValue) -> {
            Event.fireEvent(root, new ThemeChangeEvent(ThemeChangeEvent.ANY, Theme.valueOf(newValue.name().toUpperCase())));
        });
    }
}
