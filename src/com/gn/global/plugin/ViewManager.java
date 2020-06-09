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
import com.gn.global.exceptions.NavigationException;
import javafx.scene.control.ScrollPane;

import java.util.HashMap;

/**
 * @author Gleidson Neves da Silveira | gleidisonmt@gmail.com
 * Create on  07/10/2018
 */
public enum  ViewManager {

    INSTANCE;

    private String current;
    private String previous;

    private final HashMap<String, ViewController> SCREENS = new HashMap<>();

    public void put(ViewController viewController) {
        // testing
        previous = current;
        current = viewController.getView().getName();
        SCREENS.put(viewController.getView().getName(), viewController);
    }

    private ViewController getWithUpdate(String view){
        previous = current;
        current = view;
        return SCREENS.get(view);
    }

    public ViewController get(String view){
        return SCREENS.get(view);
    }

    public void navigate(GNDecorator decorator, String name) throws NavigationException {
        if(get(name) == null)
            throw new NavigationException("NAVIGATION", String.format("The view '%s' was not encountered.", name));
        else {
            decorator.setContent(get(name).getRoot());
            if (get(name).getController() instanceof ActionView){
                ( (ActionView) get(name).getController()).enter();
            }
        }

    }

    public String openSubView(ScrollPane body, String name) throws NavigationException {
        ViewController viewController = getWithUpdate(name);
        if(viewController != null) {

            if (get(previous).getController() instanceof ActionView)
                ((ActionView) get(previous).getController()).exit();

            body.setContent(viewController.getRoot());

            if (viewController.getController() instanceof ActionView) {
                ((ActionView) viewController.getController()).enter();
            }
//
            return viewController.getTitle();
        } else {
            throw new NavigationException("NAVIGATION", String.format("The viewController '%s' was not encountered.", name));
        }
    }

    public int size(){
        return SCREENS.size();
    }
}
