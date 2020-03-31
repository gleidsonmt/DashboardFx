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
package com.gn.global.factory;

import com.gn.App;
import com.gn.global.plugin.ViewManager;
import javafx.application.Platform;
import javafx.concurrent.Service;
import javafx.concurrent.Task;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import org.yaml.snakeyaml.Yaml;
import org.yaml.snakeyaml.constructor.Constructor;

import java.io.IOException;
import java.util.List;

/**
 * @author Gleidson Neves da Silveira | gleidisonmt@gmail.com
 * Create on  29/03/2020
 */
public class LoadViews extends Service {

    private List<Module> modules;

    public LoadViews() {
        Yaml yaml = new Yaml(new Constructor(List.class));
        modules = yaml.load(App.class.getResourceAsStream("/com/gn/global/modules.yml"));
    }

    private View createView(Module module) {
        FXMLLoader loader = new FXMLLoader();
        String moduleName = module.getParentName() == null ? module.getName() : module.getParentName();
        loader.setLocation(getClass().getResource("/com/gn/module/" + moduleName + "/" + module.getFxmlFile()));
        try {
            loader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return new View(module.getTitle(), module.getName(), module, loader);
    }
//
    @Override
    protected synchronized void succeeded() {
        ViewManager.INSTANCE.navigate(App.decorator, "main");
        super.succeeded();
    }

    @Override
    protected void failed() {
        super.failed();
        super.getException().printStackTrace();
    }

    @Override
    public void start() {
        super.start();
    }

    @Override
    protected Task createTask() {

        return new Task() {
            @Override
            protected Object call() throws Exception {
                for (Module module : modules) {
                    System.out.println(module);
                    ViewManager.INSTANCE.put(createView(module));
                }
                return null;
            }
        };
    }

////        float size = modules.size(); // the difference represents the views not loaded
////        float increment = 100f / size;
////        float progress = 0f;
//
//        for (Module module : modules) {
//            try {
//                System.err.println(module.getTitle());
////                ActionViewComposite actionViewComposite = loadViewComposite(module);
////                ViewManager.INSTANCE.put(createView(module));
////                if (module.getSubModules() != null) {
////                    for (Module subModule : module.getSubModules()) {
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
//        System.out.println(modules);

}
