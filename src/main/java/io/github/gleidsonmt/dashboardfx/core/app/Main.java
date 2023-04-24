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

package io.github.gleidsonmt.dashboardfx.core.app;

import io.github.gleidsonmt.dashboardfx.core.app.services.Context;

/**
 * Class that provides logger and states.
 * @author Gleidson Neves da Silveira | gleidisonmt@gmail.com
 * Create on  02/10/2022
 */
public class Main extends Launcher {

    @Override
    public void build(Context context) {
//        Loader loadCircle = new LoadCircle("Starting..", "");
//
//        Task<String> loadViews = new LoadViews(context, loadCircle); // Load View task
//
//        Thread tLoadViews = new Thread(loadViews);
//        tLoadViews.setDaemon(true);
//        tLoadViews.start();
//
//        context.icons(
//                new Image(Objects.requireNonNull(getClass().getResource("/core.app/img/logo_64.png")).toExternalForm()),
//                new Image(Objects.requireNonNull(getClass().getResource("/core.app/img/logo_32.png")).toExternalForm()),
//                new Image(Objects.requireNonNull(getClass().getResource("/core.app/img/logo_48.png")).toExternalForm()),
//                new Image(Objects.requireNonNull(getClass().getResource("/core.app/img/logo_16.png")).toExternalForm())
//        );
//
//        context.routes().registryAndGo(new SimpleView((Node) loadCircle, "loadCircle"));

    }

    public static void main(String[] args) {
        launch(args);
    }
}
