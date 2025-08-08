package io.github.gleidsonmt.dashboardfx;

import io.github.gleidsonmt.glad.base.Root;
import io.github.gleidsonmt.glad.theme.Css;
import io.github.gleidsonmt.glad.theme.Font;
import io.github.gleidsonmt.glad.theme.ThemeProvider;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;
import io.github.gleidsonmt.dashboardfx.utils.Assets;
import org.scenicview.ScenicView;

/**
 * @author Gleidson Neves da Silveira | gleidisonmt@gmail.com
 * Create on  10/06/2025
 */
public class App extends Application {
    @Override
    public void start(Stage stage) throws Exception {
        Root root = new Root(new Main());
        Scene scene = new Scene(root, 1200,800);
        stage.setScene(scene);
        stage.setMinWidth(400);
        stage.setMinHeight(600);
        stage.setMaximized(true);
        ThemeProvider.install(scene, Font.POPPINS, Font.INSTAGRAM);
        ThemeProvider.install(scene, Css.DEFAULT, Css.BUTTON,
                Css.SHAPES,  Css.CONTEXT_MENU,
                Css.HYPERLINK,   Css.COMBO_BOX,
                Css.RADIO_BUTTON, Css.CHECK_BOX,
                Css.TREE_VIEW, Css.TOGGLE_BUTTON,
                Css.PROGRESS_BAR, Css.LIST_VIEW, Css.TABLE_VIEW,
                Css.TAB_PANE, Css.IMMERSIVE_SCROLL,

                Css.TEXT_FIELD, Css.TEXT_BOX);

        scene.getStylesheets().add(Assets.getCss("drawer.css"));
        scene.getStylesheets().add(Assets.getCss("master.css"));
        stage.getIcons().add(Assets.getImage("logo_128.png"));
        stage.show();

//        ScenicView.show(scene);
    }

//    @Override
//    protected void build(Layout layout) {
//        Drawer drawer = new Drawer(layout);
//
//        layout.setDrawer(drawer);
//
//        layout.viewProperty().bind(drawer.currentModuleProperty());
//
//        layout.getRoot().addPoint(_ -> {
//            layout.setDrawer(null);
//        }, Break.MOBILE);
//
//        layout.getRoot().addPoint(_ -> {
//            layout.setDrawer(drawer);
//        }, Break.SM, Break.MD, Break.WIDE);
////
//        addFonts(Font.POPPINS, Font.INSTAGRAM);
//        addStyleSheets(Css.DEFAULT, Css.BUTTON,
//                Css.HYPERLINK, Css.CONTEXT_MENU,
//                Css.PROGRESS_BAR, Css.LIST_VIEW, Css.TABLE_VIEW,
//                Css.TEXT_FIELD, Css.TEXT_BOX);

////
//        ScenicView.show(getScene());
//    }
}
