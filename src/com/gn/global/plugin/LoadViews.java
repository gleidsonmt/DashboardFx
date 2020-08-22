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

import com.gn.DashApp;
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
public class LoadViews extends Service<View> {

    private final List<View> views;
    private StringBuilder builder = new StringBuilder();

    public LoadViews() {
        Yaml yaml = new Yaml(new Constructor(List.class));
        views = yaml.load(LoadViews.class.getResourceAsStream("/com/gn/global/modules.yml"));
    }

    StringBuilder constructor = new StringBuilder();

    private ViewController createView(View view) throws LoadViewException {
        FXMLLoader loader = new FXMLLoader();
        String viewName = view.getName() ;

        URL location = null;

        String path = DashApp.getProperties().get("app.module").toString();
        String module = view.getModuleName();

//        System.out.println("view.getModuleName() = " + view.getModuleName());
//        System.out.println("views = " + view.getViews());
//        System.out.println("view = " + view.getFxmlFile());
//        System.out.println("view.getModuleName() = " + view.getFxmlFile());

        if(view.getName() == null && view.getFxmlFile() != null){
            view.setName( view.getFxmlFile().replaceAll(".fxml", ""));
        }

        if(view.getFxmlFile() != null || view.getModuleName() != null) {
            System.out.println("first condidtion");

            if (view.getViews() != null) {

                view.getViews().forEach(e -> {

                    System.out.println("view name = " + e.getName());

                    constructor.append("/").append(view.getModuleName());

                    try {
                        System.out.println("view3 = " + e.getName());
//                                e.setModuleName(view.getModuleName() + "/" + e.getModuleName() );
                        createView(e);
                        System.out.println("constructor = " + constructor);

                    } catch (LoadViewException loadViewException) {
                        loadViewException.printStackTrace();
                    }
                });
//                constructor.delete(constructor.lastIndexOf("/") +2, constructor.length());


//                location = getClass().getResource(path + "/" + constructor + "/" + view.getFxmlFile());
            } else if (view.getModuleName() == null && view.getFxmlFile() != null) {


                System.out.println("path = " + path + constructor + "/" + view.getFxmlFile());
                System.out.println("constructor = " + constructor);
//                System.out.println("path = " + path + "/" + view.getFxmlFile().replaceAll(".fxml", "") + "/" + view.getFxmlFile());

                if (constructor.length() == 0){
                    location = getClass().getResource(path + "/" + view.getFxmlFile().replaceAll(".fxml", "") + "/" + view.getFxmlFile());
                } else {
//                    constructor.delete(constructor.lastIndexOf("/") -1, constructor.length());

                    System.out.println("mix = " + constructor);

                    location = getClass().getResource(path + constructor + "/" + view.getFxmlFile());
                }


            } else {
                System.out.println("login " + path + "/" + view.getModuleName() + "/" + view.getFxmlFile());
                location = getClass().getResource(path + "/" + view.getModuleName() + "/" + view.getFxmlFile());
            }

            if(location != null) {
                loader.setLocation(location);
                try {
                    loader.load();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                return new ViewController(view, loader);
            }

        } else {

        }


//            if(view.getViews() != null){
//
////                StringBuilder finalConstructor = constructor;
//                view.getViews().forEach(e -> {
//                    try {
//                        constructor.append("/").append(view.getModuleName()).append("/");
////                                e.setModuleName(view.getModuleName() + "/" + e.getModuleName() );
//                        createView(e);
//                    } catch (LoadViewException loadViewException) {
//                        loadViewException.printStackTrace();
//                    }
//                });
//            } else {
//                constructor = new StringBuilder();
////                        throw new LoadViewException("LOAD_VIEW", String.format("No encountered fxml file for view %s.", view.getName()));
//            }

//            if(location != null) {
//                loader.setLocation(location);
//                try {
//                    loader.load();
//                } catch (IOException e) {
//                    e.printStackTrace();
//                }
//                return new ViewController(view.getTitle(), view.getName(), view, loader);
//            }
//        } else {
//            if (view.getName() == null|| view.getModuleName() == null) {
//                if (view.getTitle() != null)
//                    throw new LoadViewException("LOAD_VIEWS", String.format("The view with the title %s does not have a name, it's used to navigate.", view.getTitle()));
//                else
//                    throw new LoadViewException("LOAD_VIEWS", "The view does not have a name, it's used to navigate.");
//            } else {
//                if (view.getParentName() == null) {
//                    if(view.getViews() != null){
//
//                        StringBuilder finalConstructor = constructor;
//                        view.getViews().forEach(e -> {
//                            try {
//                                finalConstructor.append("/").append(view.getModuleName()).append("/");
////                                e.setModuleName(view.getModuleName() + "/" + e.getModuleName() );
//                                createView(e);
//                            } catch (LoadViewException loadViewException) {
//                                loadViewException.printStackTrace();
//                            }
//                        });
//                    } else {
//                        constructor = new StringBuilder();
////                        throw new LoadViewException("LOAD_VIEW", String.format("No encountered fxml file for view %s.", view.getName()));
//                    }
//                }
//                    if(view.getViews() != null) {
//
//                    }
//                        view.getViews().forEach(e -> {
////                            createView(e);
//                        });
//                    } else {
//                } else {
//                    throw new LoadViewException("LOAD_VIEW", String.format("Package '%s' does not contains the fxml file %s", view.getParentName(), view.getFxmlFile()));
//                }
//            }
//        }
        return null;
    }

    public void single(View view){

        FXMLLoader loader = new FXMLLoader();
        URL location = null;

        String path = DashApp.getProperties().get("app.module").toString();

        if(view.getModuleName() != null ) {
            builder.append("/").append(view.getModuleName());
        }

        if(view.getViews() != null) {

            for (View v : view.getViews()) {
                if (v.getFxmlFile() != null && v.getModuleName() != null) {
                    location = getClass().getResource(path + builder + "/" + v.getFxmlFile());
                }
                single(v);
            }
        }

        if(view.getModuleName() == null ){
            location = getClass().getResource(path + builder + "/"  + view.getFxmlFile());
        } else if(view.getFxmlFile() != null && view.getModuleName() != null){
            location = getClass().getResource(path + builder + "/" + view.getFxmlFile());
        }


        if(view.getModuleName() != null) {

            String act = builder.substring(builder.lastIndexOf("/") + 1 , builder.length());

            if (act.equals(view.getModuleName())) {
                builder.delete(builder.lastIndexOf("/"), builder.length());
            }
        }

        if (location != null){
            loader.setLocation(location);
            try {
                loader.load();
            } catch (IOException e) {
                e.printStackTrace();
            }

            ViewManager.INSTANCE.put(new ViewController(view, loader));
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
            ViewManager.INSTANCE.navigate(DashApp.decorator, "main");
            ScrollPane body = (ScrollPane) DashApp.decorator.lookup("#body");
            ViewManager.INSTANCE.openSubView(body, "dashboard");
        } catch (NavigationException e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void failed() {
        super.failed();
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
    protected Task<View> createTask() {

        return new Task<View>() {
            @Override
            protected View call()  {
                Label lbl = (Label) DashApp.decorator.lookup("#labelLoading");
                for (View view : views) {
//                    System.out.println("Loading... " + view);
//                    try {
//                        ViewManager.INSTANCE.put(createView(view));
                    single(view);
//                    } catch (LoadViewException e) {
//                        e.printStackTrace();
//                    }
                    Platform.runLater(() -> {
                        lbl.setText("Loading... [ " + (view.getName() + " ]/[ " + view.getName() + " ]"));
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
