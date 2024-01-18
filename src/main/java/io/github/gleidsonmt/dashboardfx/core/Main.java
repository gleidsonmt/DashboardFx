package io.github.gleidsonmt.dashboardfx.core;

import io.github.gleidsonmt.dashboardfx.core.controls.icon.IconContainer;
import io.github.gleidsonmt.dashboardfx.core.exceptions.NavigationException;
import io.github.gleidsonmt.dashboardfx.core.impl.Layout;
import io.github.gleidsonmt.dashboardfx.core.interfaces.Loader;
import io.github.gleidsonmt.dashboardfx.core.services.LoadViews;
import io.github.gleidsonmt.dashboardfx.core.view.SimpleView;
import io.github.gleidsonmt.dashboardfx.core.view.View;
import io.github.gleidsonmt.dashboardfx.core.view.layout.LoadCircle;
import javafx.concurrent.Task;
import javafx.scene.Node;
import javafx.scene.image.Image;

/**
 * @author Gleidson Neves da Silveira | gleidisonmt@gmail.com
 * Create on  22/04/2023
 */
public class Main extends Launcher {

    @Override
    public void build(Context context) {

        Layout layout = new Layout(context);
        context.setLayout(layout);

        Loader loadCircle = new LoadCircle("Starting..", "");
        Task<View> loadViews = new LoadViews(context, loadCircle); // Load View task

        Thread tLoadViews = new Thread(loadViews);
        tLoadViews.setDaemon(true);
        tLoadViews.start();

        layout.setContent((Node) loadCircle);

        loadViews.setOnSucceeded(event -> {
            layout.setNav(context.routes().getView("drawer"));
            context.routes().nav("dash");
        });

        icons.add(new Image(context.getResource("style/logo/logo_256.png").toExternalForm(),128,128,true, true));

//        layout.setContent((Node) loadCircle);
    }

}
