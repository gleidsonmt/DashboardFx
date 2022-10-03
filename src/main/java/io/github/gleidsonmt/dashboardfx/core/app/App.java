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
import javafx.application.HostServices;

import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Implementation app under the context approach.
 * @author Gleidson Neves da Silveira | gleidisonmt@gmail.com
 * Create on  03/10/2022
 */
public class App implements IContext {

    private HostServices    hostServices;   // Web Servieces too..
    private WindowDecorator window;        // Objects refer to window in application

    private final Logger logger = Logger.getLogger("app");

    private final Properties properties  = new Properties();

    public App() {
        logger.setLevel(Level.ALL);
        loadProperties();
        logger.log(Level.INFO, "Initializing App Class Context.");
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

    }

    @Override
    public IDecorator getDecorator() {
        return window;
    }

    private void loadProperties() {

    }

}
