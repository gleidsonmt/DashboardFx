package io.github.gleidsonmt.dashboardfx;

import io.github.gleidsonmt.dashboardfx.utils.Assets;
import io.github.gleidsonmt.glad.base.Root;
import io.github.gleidsonmt.glad.theme.Css;
import io.github.gleidsonmt.glad.theme.Drawer;
import io.github.gleidsonmt.glad.theme.Font;
import io.github.gleidsonmt.glad.theme.ThemeProvider;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.stage.Stage;
import javafx.util.Callback;

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

        ThemeProvider.install(scene,
                Font.POPPINS, Font.INSTAGRAM,
                Css.DEFAULT, Css.BUTTON,
                Css.SHAPES, Css.CONTEXT_MENU,
                Css.HYPERLINK, Css.COMBO_BOX,
                Css.RADIO_BUTTON, Css.CHECK_BOX,
                Css.TREE_VIEW, Css.TOGGLE_BUTTON,
                Css.PROGRESS_BAR, Css.LIST_VIEW, Css.TABLE_VIEW,
                Css.TAB_PANE, Css.IMMERSIVE_SCROLL,
                Css.DATE_PICKER, Css.MENU_BUTTON,
                Css.SPLIT_MENU_BUTTON, Css.CHOICE_BOX,
                Css.TEXT_FIELD, Css.TEXT_BOX, Css.TITLED_PANE,
                Drawer.DEFAULT
        );


//        scene.getStylesheets().add(Assets.getCss("drawer.css"));
        scene.getStylesheets().add(Assets.getCss("master.css"));
        stage.getIcons().add(Assets.getImage("logo_128.png"));
        stage.show();

        LibrariesTools.addTools(stage.getScene());

    }
}
