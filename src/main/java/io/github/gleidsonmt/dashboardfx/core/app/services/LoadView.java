/*
 *    Copyright (C) Gleidson Neves da Silveira
 *
 *    This program is free software: you can redistribute it and/or modify
 *    it under the terms of the GNU General Public License as published by
 *    the Free Software Foundation, either version 3 of the License, or
 *    (at your option) any later version.
 *
 *    This program is distributed in the hope that it will be useful,
 *    but WITHOUT ANY WARRANTY; without even the implied warranty of
 *    MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *    GNU General Public License for more details.
 *
 *    You should have received a copy of the GNU General Public License
 *    along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */

package io.github.gleidsonmt.dashboardfx.core.app.services;

import io.github.gleidsonmt.dashboardfx.core.app.interfaces.PathView;
import javafx.concurrent.Service;
import javafx.concurrent.Task;
import org.yaml.snakeyaml.Yaml;
import org.yaml.snakeyaml.constructor.Constructor;

import java.util.List;
import java.util.logging.Logger;

/**
 * @author Gleidson Neves da Silveira | gleidisonmt@gmail.com
 * Create on  04/10/2022
 */
public class LoadView extends Service<ViewComposer>  {

    private final   StringBuilder       builder = new StringBuilder();
    private List<ViewComposer> yamlViews = null;

    public LoadView(PathView pathView) {

        Yaml yaml = new Yaml(new Constructor(List.class));

        yamlViews = yaml.load(getClass().getResourceAsStream(
                "/views.yml"));
    }


    @Override
    protected void succeeded() {
        Logger.getLogger("app").info("All pre views are loaded..");
    }

    @Override
    protected Task<ViewComposer> createTask() {
        return new Task<ViewComposer>() {
            @Override
            protected ViewComposer call() throws Exception {
                return null;
            }
        };
    }
}

