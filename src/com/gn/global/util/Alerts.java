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
package com.gn.global.util;

import javafx.event.EventHandler;
import javafx.scene.input.MouseEvent;

/**
 * @author Gleidson Neves da Silveira | gleidisonmt@gmail.com
 * Create on  30/01/2019
 */
public class Alerts {

    public static void warning(String title, String content){
        Dialog.createAlert(Dialog.Type.WARNING, title, content);
    }

    @SafeVarargs
    public static void warning(String title, String content, EventHandler<MouseEvent>... confirm){
        Dialog.createAlert(Dialog.Type.WARNING, title, content, confirm);
    }

    public static void error(String title, String content){
        Dialog.createAlert(Dialog.Type.ERROR, title, content);
    }

    @SafeVarargs
    public static void error(String title, String content, EventHandler<MouseEvent>... confirm){
        Dialog.createAlert(Dialog.Type.ERROR, title, content, confirm);
    }

    public static void info(String title, String content){
        Dialog.createAlert(Dialog.Type.INFO, title, content);
    }

    @SafeVarargs
    public static void info(String title, String content, EventHandler<MouseEvent>... confirm){
        Dialog.createAlert(Dialog.Type.INFO, title, content, confirm);
    }

    public static void success(String title, String content){
        Dialog.createAlert(Dialog.Type.SUCCESS, title, content);
    }

    @SafeVarargs
    public static void success(String title, String content, EventHandler<MouseEvent>... confirm){
        Dialog.createAlert(Dialog.Type.SUCCESS, title, content, confirm);
    }
}
