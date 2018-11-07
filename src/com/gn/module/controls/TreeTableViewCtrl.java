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
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.util.Callback;

import java.net.URL;
import java.util.Arrays;
import java.util.ResourceBundle;

/**
 * @author Gleidson Neves da Silveira | gleidisonmt@gmail.com
 * Create on  06/11/2018
 * Version 1.0
 */
public class TreeTableViewCtrl implements Initializable {

    @FXML private TreeTableView<Person> treeTableView;
    @FXML private TreeTableColumn<Person, String> c1;
    @FXML private TreeTableColumn<Person, String> c2;
    @FXML private TreeTableColumn<Person, String> c3;

    TreeItem<Person> p1 = new TreeItem<>(new Person("Gleidson", "Neves da Silveira", "gleidisonmt@gmail.com"));
    TreeItem<Person> p2 = new TreeItem<>(new Person("Daniel", "Neves da Silveira", "danielmt@outlook.com"));
    TreeItem<Person> p3 = new TreeItem<>(new Person("Ioneide", "Neves da Silva", "neidemt@outlook.com"));
    TreeItem<Person> p4 = new TreeItem<>(new Person("Deusdete", "José da Silveira", "silveirabiologo@outlook.com"));

    TreeItem<Person> root = new TreeItem<>(new Person());

    TreeItem<TreeItem> second = new TreeItem<>();

//
    @Override
    public void initialize(URL location, ResourceBundle resources) {
//        second.getChildren().add(p1)

//        root.getChildren().add(second);

        root.getChildren().addAll(p1, p2, p3, p4);

        c1.setCellValueFactory(new Callback<TreeTableColumn.CellDataFeatures<Person, String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(TreeTableColumn.CellDataFeatures<Person, String> param) {
                return param.getValue().getValue().firstNameProperty();
            }
        });

        c2.setCellValueFactory(new Callback<TreeTableColumn.CellDataFeatures<Person, String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(TreeTableColumn.CellDataFeatures<Person, String> param) {
                return param.getValue().getValue().lastNameProperty();
            }
        });


        c3.setCellValueFactory(new Callback<TreeTableColumn.CellDataFeatures<Person, String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(TreeTableColumn.CellDataFeatures<Person, String> param) {
                return param.getValue().getValue().emailProperty();
            }
        });



        treeTableView.setRoot(root);
    }
}
