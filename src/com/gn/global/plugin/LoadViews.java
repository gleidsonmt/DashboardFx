/*
 * Copyright (C) Gleidson Neves da Silveira
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package com.gn.global.plugin;

import com.gn.App;
import com.gn.global.exceptions.LoadViewException;
import com.gn.global.exceptions.NavigationException;
import javafx.application.Platform;
import javafx.concurrent.Service;
import javafx.concurrent.Task;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import org.yaml.snakeyaml.Yaml;
import org.yaml.snakeyaml.constructor.Constructor;

import java.io.IOException;
import java.net.URL;
import java.util.List;

/**
 * @author Gleidson Neves da Silveira | gleidisonmt@gmail.com
 * Create on  29/03/2020
 */
public class LoadViews extends Service {

    private List<View> views;

    public LoadViews() {
        Yaml yaml = new Yaml(new Constructor(List.class));
        views = yaml.load(App.class.getResourceAsStream("/com/gn/global/modules.yml"));
    }

    private ViewController createView(View view) throws LoadViewException {
        FXMLLoader loader = new FXMLLoader();
        String moduleName = view.getParentName() == null ? view.getName() : view.getParentName();
        URL location = getClass().getResource("/com/gn/module/" + moduleName + "/" + view.getFxmlFile());

        if(location != null){
            loader.setLocation(location);
            try {
                loader.load();
            } catch (IOException e) {
                e.printStackTrace();
            }
            return new ViewController(view.getTitle(), view.getName(), view, loader);
        } else {
            if(view.getName() == null){
                if (view.getTitle() != null)
                throw new LoadViewException("LOAD_VIEWS", String.format("The view with the title %s does not have a name, it's used to navigate.", view.getTitle()));
                else
                throw new LoadViewException("LOAD_VIEWS", "The view does not have a name, it's used to navigate.");
            } else {
                if (view.getParentName() == null)
                    throw new LoadViewException("LOAD_VIEW", String.format("No encountered fxml file for view %s.", view.getName()));
                else
                    throw new LoadViewException("LOAD_VIEW", String.format("Package '%s' does not contains the fxml file %s", view.getParentName(), view.getFxmlFile()));
            }
        }
    }
//
    @Override
    protected synchronized void succeeded() {
        super.succeeded();
//        System.out.println("+----------------------------------+");
//        System.out.println("|        LoadViews Succeeded       |");
//        System.out.println("+----------------------------------+");

        try {
            ViewManager.INSTANCE.navigate(App.getDecorator(), "main");
            ScrollPane body = (ScrollPane) App.getDecorator().getScene().lookup("#body");
            ViewManager.INSTANCE.openSubView(body, "dashboard");
        } catch (NavigationException e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void failed() {
        super.failed();
        System.out.println("failed");
        super.getException().printStackTrace();
    }

    @Override
    public void start() {
        super.start();
//        System.out.println("+----------------------------------+");
//        System.out.println("|        Starting Load Views       |");
//        System.out.println("+----------------------------------+");
    }

    @Override
    protected Task createTask() {

        return new Task() {
            @Override
            protected Object call()  {
                Label lbl = (Label) App.getDecorator().getScene().lookup("#labelLoading");
                for (View view : views) {
//                    System.out.println(view);
                    try {
                        ViewManager.INSTANCE.put(createView(view));
                    } catch (LoadViewException e) {
                        e.printStackTrace();
                    }
                    Platform.runLater(() -> {
                        lbl.setText("Loading... [ " + (view.getParentName()== null ? view.getName() : view.getParentName()) + " ]/[ " + view.getName() + " ]");
                    });
                }

                Platform.runLater(() -> lbl.setText("Going to Main :D"));
                return null;
            }
        };
    }

////        float size = views.size(); // the difference represents the views not loaded
////        float increment = 100f / size;
////        float progress = 0f;
//
//        for (View module : views) {
//            try {
//                System.err.println(module.getTitle());
////                ActionViewComposite actionViewComposite = loadViewComposite(module);
////                ViewManager.INSTANCE.put(createView(module));
////                if (module.getSubModules() != null) {
////                    for (View subModule : module.getSubModules()) {
////                        viewManager.put(subModule.getName(), loadViewComposite(subModule));
////                    }
////                }
////                progress += increment;
//            } catch (Exception e) {
//
////                e.printStackTrace(); // Colocar e criar erro de load de views
////                log.error(String.format("Error loading module: %s, view: %s", module.getName(), module.getFxmlFile()),e);
//            }
//
//        }

}
