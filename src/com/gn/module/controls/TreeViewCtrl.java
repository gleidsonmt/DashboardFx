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
package com.gn.module.controls;

import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.util.Callback;

import java.net.URL;
import java.util.ResourceBundle;

/**
 * @author Gleidson Neves da Silveira | gleidisonmt@gmail.com
 * Create on  06/11/2018
 * Version 1.0
 */
public class TreeViewCtrl implements Initializable {

    @FXML private TreeView<String> treeView;

    TreeItem<String> p1 = new TreeItem<>("Gleidson Neves da Silveira");
    TreeItem<String> p2 = new TreeItem<>("Daniel Neves da Silveira");
    TreeItem<String> p3 = new TreeItem<>("Ioneide Neves da Silva");
    TreeItem<String> p4 = new TreeItem<>("Deusdete José da Silveira");

    TreeItem<String> root = new TreeItem<>("People");
    TreeItem<String> sons = new TreeItem<>("Sons");
    TreeItem<String> parents = new TreeItem<>("Parents");

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        sons.getChildren().addAll(p1, p2);
        parents.getChildren().addAll(p3, p4);
        root.getChildren().addAll(parents, sons);


        treeView.setRoot(root);
    }
}
