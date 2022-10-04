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

import io.github.gleidsonmt.dashboardfx.core.app.interfaces.IContext;
import io.github.gleidsonmt.dashboardfx.core.app.interfaces.IDecorator;
import io.github.gleidsonmt.dashboardfx.core.app.interfaces.IRotes;
import io.github.gleidsonmt.dashboardfx.core.app.interfaces.PathView;
import io.github.gleidsonmt.dashboardfx.core.app.services.Routes;
import javafx.application.HostServices;
import org.jetbrains.annotations.NotNull;

import java.io.IOException;
import java.util.Properties;
import java.util.logging.Logger;

/**
 * Implementation app under the context approach.
 * @author Gleidson Neves da Silveira | gleidisonmt@gmail.com
 * Create on  03/10/2022
 */
public class App implements IContext, PathView {

    private final String module;
    private final String core;
    private String views;

    private HostServices    hostServices;   // Web Servieces too..
    private WindowDecorator window;        // Objects refer to window in application
    private IRotes          routes;

    private final Logger        logger      = Logger.getLogger("app");
    private final Properties    properties  = new Properties();

    public App() {

        loadProperties();
        logger.info("Initializing App Class Context.");

        this.module = "/" + App.class.getModule().toString().replaceAll("module ", "");
        this.core = module.concat("/core.app");
        this.views = module.concat(".views");

        try {
            window = new WindowDecorator(properties, this);
        } catch (IOException e) {
            logger.severe(e.getMessage());
            e.printStackTrace();
        }

        routes = new Routes(window, this);
    }

    @Override
    public Properties getProperties() {
        return properties;
    }

    @Override
    public Logger getLogger() {
        return logger;
    }

    @Override
    public void startApp(HostServices _hostServices) {
        logger.info("Starting Main Core -- DashboardFx");
        this.hostServices = _hostServices;
        getDecorator().show(_hostServices);
    }

    @Override
    public IDecorator getDecorator() {
        return window;
    }

    @Override
    public IRotes getRoutes() {
        return routes;
    }

    @Override
    public PathView getPaths() {
        return this;
    }

    private void loadProperties() {

        try {
            properties.load(getClass().getResourceAsStream("/app.properties"));
        } catch (IOException e) {
            logger.severe(e.getMessage() );
            e.printStackTrace();
        }

        properties.putIfAbsent("app.name", "DashboardFx");
        properties.putIfAbsent("app.width", "1280");
        properties.putIfAbsent("app.height", "800");
        properties.putIfAbsent("app.min.width", "450");
        properties.putIfAbsent("app.min.height", "720");
        properties.putIfAbsent("user.logged", "false");
        properties.putIfAbsent("user.registered", "false");

    }

    @Override
    public String getFromCore(String fileOrPath) {
        return core.concat(formatFile(fileOrPath));
    }

    @Override
    public String getViews() {
        return views;
    }

    private @NotNull String formatFile(@NotNull String value) {

        if (value.contains("/")) {
            return value.indexOf('/') == 0 ? value : "/" + value;
        } else {
            return "/" + value;
        }

    }

}
