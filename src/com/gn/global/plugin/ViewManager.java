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

import com.gn.decorator.GNDecorator;
import com.gn.global.exceptions.LoadViewException;
import com.gn.global.exceptions.NavitagionException;
import com.gn.global.factory.ActionView;
import com.gn.global.factory.View;
import com.gn.global.factory.View;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Node;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.StackPane;

import java.util.HashMap;

/**
 * @author Gleidson Neves da Silveira | gleidisonmt@gmail.com
 * Create on  07/10/2018
 */
public enum  ViewManager {

    INSTANCE;

    private final HashMap<String, View> SCREENS = new HashMap<>();

    private String current;
    private String previous;

//
    public void put(View view) throws LoadViewException {

        if(view != null){
        // testing
            previous = current;
            current = view.getModule().getName();

            SCREENS.put(view.getModule().getName(), view);
        } else {
            throw new LoadViewException("Code", String.format("The view %s was not found.", view.getTitle()));
        }
    }

    private View getWithUpdate(String view){
        previous = current;
        current = view;
        return SCREENS.get(view);
    }

    private View get(String view){
        return SCREENS.get(view);
    }

    public void navigate(GNDecorator decorator, String name) throws NavitagionException {
        if(get(name) == null)
            throw new NavitagionException("NAVIGATION", String.format("The view '%s' was not found.", name));
        else
            decorator.setContent(get(name).getRoot());
    }

    public String openSubView(ScrollPane body, String name){
        View view = getWithUpdate(name);

        if(get(previous).getController() instanceof ActionView)
            ((ActionView) get(previous).getController()).exit();

        body.setContent(view.getRoot());

        if(view.getController() instanceof ActionView){
            ((ActionView) view.getController()).enter();
        }

        return view.getTitle();
    }

}
