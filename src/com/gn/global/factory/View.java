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

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;

import java.util.List;
import java.util.StringJoiner;

/**
 * @author Gleidson Neves da Silveira | gleidisonmt@gmail.com
 * Create on  29/03/2020
 */
public class View {

    private final String      title;
    private final String      name;
    private final Module      module;
    private final FXMLLoader  loader;

    View(String title, String name, Module module, FXMLLoader loader){
        this.title  = title;
        this.name   = name;
        this.module = module;
        this.loader = loader;
    }

    public String getTitle() {
        return title;
    }

    public String getName(){
        return name;
    }

    public Module getModule() {
        return module;
    }

    public Object getController() {
        return loader.getController();
    }

    public Parent getRoot(){
        return loader.getRoot();
    }
}
