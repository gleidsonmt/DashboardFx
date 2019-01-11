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

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Node;

import java.util.HashMap;

/**
 * @author Gleidson Neves da Silveira | gleidisonmt@gmail.com
 * Create on  07/10/2018
 */
public class ViewManager {

    private static ViewManager instance;
    private static final HashMap<String, Node> SCREENS = new HashMap<>();
    private static String nameView;

    private ViewManager(){}

    public static ViewManager getInstance() {
        if(instance == null){
            instance =  new ViewManager();
        }
        return instance;
    }

    public void put(String name, Node node){
        nameView = name;
        SCREENS.put(name, node);
    }

    public Node get(String view){
        return SCREENS.get(view);
    }

    public int getSize(){
        return SCREENS.size();
    }

    Node getCurrentView(){
        return SCREENS.get(nameView);
    }

    public ObservableList<Node> getAll(){
        return FXCollections.observableArrayList(SCREENS.values());
    }
}
