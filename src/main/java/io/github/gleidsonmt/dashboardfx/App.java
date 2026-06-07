package io.github.gleidsonmt.dashboardfx;

import io.github.gleidsonmt.dashboardfx.utils.Assets;
import io.github.gleidsonmt.glad.base.Root;
import io.github.gleidsonmt.glad.theme.Css;
import io.github.gleidsonmt.glad.theme.Font;
import io.github.gleidsonmt.glad.theme.ThemeProvider;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 * @author Gleidson Neves da Silveira | gleidisonmt@gmail.com
 * Create on  10/06/2025
 */
public class App extends Application {

    @Override
    public void start(Stage stage) throws Exception {
        Root root = new Root(new Main());
        Scene scene = new Scene(root, 1200, 800);

        stage.setScene(scene);
        stage.setMinWidth(400);
        stage.setMinHeight(600);
        stage.setMaximized(true);
        stage.setTitle("Blue Galaxy");

        ThemeProvider.install(scene, Css.ALL, Font.INSTAGRAM);

        scene.getStylesheets().add(Assets.getCss("master.css"));
        stage.getIcons().add(Assets.getImage("logo_128.png"));
        stage.show();

        LibrariesTools.addTools(stage.getScene());

    }
}
