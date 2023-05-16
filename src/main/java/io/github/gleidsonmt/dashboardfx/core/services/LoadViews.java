package io.github.gleidsonmt.dashboardfx.core.services;

import io.github.gleidsonmt.dashboardfx.core.Context;
import io.github.gleidsonmt.dashboardfx.core.interfaces.Loader;
import io.github.gleidsonmt.dashboardfx.core.view.*;
import javafx.application.Platform;
import javafx.concurrent.Task;
import javafx.fxml.FXMLLoader;
import org.yaml.snakeyaml.Yaml;
import org.yaml.snakeyaml.constructor.Constructor;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.nio.charset.StandardCharsets;

/**
 * @author Gleidson Neves da Silveira | gleidisonmt@gmail.com
 * Version 0.0.1
 * Create on  02/04/2023
 */
public class LoadViews extends Task<View> {

    private ViewMap data;
    private final StringBuilder builder = new StringBuilder();
    private final Context context;
    private final Loader loader;

    public LoadViews(Context context, Loader loader) {
        this.context = context;
        this.loader = loader;

        InputStream inputStream = getClass().getResourceAsStream("/io.github.gleidsonmt.dashboardfx/views.yml");
        Yaml yaml = new Yaml(new Constructor(ViewMap.class));
        data = yaml.load(inputStream);
    }

    @Override
    protected View call() throws Exception {

        for (ViewComposer viewComposer : data.getViews()) {
            Thread.sleep(1000);

            Platform.runLater(() -> {
                loader.updateLegend("Loading view... " + viewComposer.getName());
                loadView(viewComposer);
            });

            Thread.sleep(1000);
        }
        return null;
    }

    private void loadView(ViewComposer view) {

        FXMLLoader loader = new FXMLLoader();
        URL location = null;

        String path = "/io.github.gleidsonmt.dashboardfx/views";

        if (view.getFolder() != null) {
            builder.append("/").append(view.getFolder());
        }

//        if (view.getViews() != null) {
//            for (ViewComposer v : view.getViews()) {
//                if (v.getFxml() != null) {
//
//                    location = getClass().getResource(path + builder + "/"
//                            + v.getFxml());
//                }
//                v.setRoot(view);
//                loadView(v);
//            }
//        }

        if (view.getFolder() == null) location = LoadViews.class.getResource(path + builder + "/"
                + view.getFxml());
        else if (view.getFxml() != null && view.getFolder() != null)
            location = getClass().getResource(path + builder + "/"
                    + view.getFxml());

        if (view.getFolder() != null) {
            String act = builder.substring(builder.lastIndexOf("/") + 1, builder.length());
            if (act.equals(view.getFolder())) builder.delete(builder.lastIndexOf("/"), builder.length());
        }

        if (location != null && view.getFxml() != null) {

            loader.setLocation(location);
            loader.setCharset(StandardCharsets.UTF_8);
//            loader.setResources(App2.INSTANCE.getResourceBundle());

            try {
                loader.load();
            } catch (IOException e) {
                e.printStackTrace();
            }

//            context.getRoutes().addView(new View(view, loader));
            context.routes().put(new FXMLView(view, loader));

        } else if (view.getFxml() != null) {
            IOException io = new IOException("The fxml with ["
                    + view.getName() + "]" + " doesn't correspond.");
            io.printStackTrace();
        }
    }
}
