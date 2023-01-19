/*
 *
 *    Copyright (C) Gleidson Neves da Silveira
 *
 *    This program is free software: you can redistribute it and/or modify
 *    it under the terms of the GNU General Public License as published by
 *    the Free Software Foundation, either version 3 of the License, or
 *   (at your option) any later version.
 *
 *    This program is distributed in the hope that it will be useful,
 *    but WITHOUT ANY WARRANTY; without even the implied warranty of
 *    MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *    GNU General Public License for more details.
 *
 *     You should have received a copy of the GNU General Public License
 *     along with this program.  If not, see <http://www.gnu.org/licenses/>.
 *
 */

package io.github.gleidsonmt.dashboardfx.core.app.services;

import java.io.FileOutputStream;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.logging.FileHandler;
import java.util.logging.Formatter;
import java.util.logging.LogRecord;
import java.util.logging.Logger;

@Deprecated(forRemoval = true)
public class AppLogger {

    private final Logger logger = Logger.getLogger("app");
    private FileHandler fileHandler;

    {
        try {
            fileHandler = new FileHandler("dash_logger.txt");
            fileHandler.setFormatter(new Formatter() {
                @Override
                public String format(LogRecord record) {

                    String _logger = record.getLoggerName();
                    String level = record.getLevel().getLocalizedName();
                    String message = record.getMessage();
                    String date = LocalDateTime.now().format(DateTimeFormatter.ofPattern("MMM dd, HH:mm"));

                    String _class = record.getSourceClassName();
                    String _method = record.getSourceMethodName();

                    return "\n" + "Logger { " + _logger + " }\n" +
                            "Date and Time " + date + " => Class [" + _class + "]\n" +
                            "Method -> " + _method + "\n" +
                            "["+ level +"] Message -> \"" + message +"\"\n";
                }
            });
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void close() {
        logger.info("Stopping Application");

        fileHandler.flush();
        fileHandler.close();

//        context.getProperties().stringPropertyNames().forEach(f -> context.getProperties().setProperty(f, context.getProperties().getProperty(f)));
//
//        try {
//            context.getProperties().store(new FileOutputStream("/app.properties" ), "Updating properties");
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
    }
}
