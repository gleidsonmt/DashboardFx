package io.github.gleidsonmt.dashboardfx.core;

import fr.brouillard.oss.cssfx.CSSFX;
import io.github.gleidsonmt.dashboardfx.core.impl.IContext;
import io.github.gleidsonmt.dashboardfx.core.impl.IRoot;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.scenicview.ScenicView;
//import org.scenicview.ScenicView;


public abstract class Launcher extends Application {

    private final IRoot root = new IRoot();
    private final Context context = new IContext(root, getHostServices());

    @Override
    public void start(Stage stage) {
        build(context);
        Scene scene = new Scene(root, 320, 240);
        stage.setTitle("DashboardFx App!");
        stage.setScene(scene);
        stage.setMaximized(true);

        scene.getStylesheets().addAll(
                clean("style/css/colors.css"),
                clean("style/css/skeleton.css"),
                clean("style/css/bootstrap.css"),
                clean("style/css/theme/simple_info.css"),
                clean("style/css/typographic.css"),
                clean("style/css/immersive_scroll.css"),
                clean("style/css/shapes.css"),
                clean("style/css/helpers.css"),
                clean("style/css/dash.css")
        );

        stage.show();
        CSSFX.start(stage);
        ScenicView.show(scene);

    }

    private String clean(String c) {
        return context.getResource(c).toExternalForm();
    }

    public abstract void build(Context context);

}