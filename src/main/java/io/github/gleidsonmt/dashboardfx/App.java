package io.github.gleidsonmt.dashboardfx;

import io.github.gleidsonmt.dashboardfx.events.ThemeChangeEvent;
import io.github.gleidsonmt.dashboardfx.utils.Assets;
import io.github.gleidsonmt.glad.base.Root;
import io.github.gleidsonmt.glad.theme.Css;
import io.github.gleidsonmt.glad.theme.Font;
import io.github.gleidsonmt.glad.theme.ThemeProvider;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.util.prefs.Preferences;

/**
 * @author Gleidson Neves da Silveira | gleidisonmt@gmail.com
 * Create on  10/06/2025
 */
public class App extends Application {

    // Chaves para identificar os valores no registro/arquivo
    private static final String WIDTH_KEY = "window_width";
    private static final String HEIGHT_KEY = "window_height";

    // Valores padrão caso seja a primeira vez que o app abre
    private static final double DEFAULT_WIDTH = 1200;
    private static final double DEFAULT_HEIGHT = 728;

    @Override
    public void start(Stage stage) throws Exception {

        setUserAgentStylesheet("");

        Preferences prefs = Preferences.userNodeForPackage(App.class);
        double width = prefs.getDouble(WIDTH_KEY, DEFAULT_WIDTH);
        double height = prefs.getDouble(HEIGHT_KEY, DEFAULT_HEIGHT);

        Root root = new Root(new Main());

        Scene scene = new MainScene(root, width, height);

        stage.setScene(scene);
        stage.setMinWidth(400);
        stage.setMinHeight(600);
        stage.setMaximized(true);
        stage.setTitle("Blue Galaxy");

        ThemeProvider.install(scene, Css.ALL, Font.INSTAGRAM, Font.POPPINS);

        scene.getStylesheets().add(Assets.getCss("master.css"));
        scene.getStylesheets().add(Assets.getCss("drawer.css"));
        stage.getIcons().add(Assets.getImage("logo_128.png"));
        stage.show();

        if (Launcher.mode == Mode.DEBUG) {
            HotTools.analyzeNodes(scene);
            HotTools.listenCss(scene);
        }

    }
}
