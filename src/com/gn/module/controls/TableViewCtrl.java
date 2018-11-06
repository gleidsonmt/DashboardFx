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
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.util.Callback;

import java.net.URL;
import java.util.ResourceBundle;

/**
 * @author Gleidson Neves da Silveira | gleidisonmt@gmail.com
 * Create on  06/11/2018
 * Version 1.0
 */
public class TableViewCtrl implements Initializable {

    @FXML private TableView<Person> tableView;
    @FXML private TableColumn<String, Person> c1;
    @FXML private TableColumn<String, Person> c2;
    @FXML private TableColumn<String, Person> c3;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        ObservableList<Person> persons = FXCollections.observableArrayList();
        persons.add(new Person("Andre", "Jorge", "jorgeandre@gmail.com"));
        persons.add(new Person("Ana", "Jorge", "jorgeandre@gmail.com"));
        persons.add(new Person("Bob", "Jorge", "jorgeandre@gmail.com"));
        persons.add(new Person("Tine", "Jorge", "jorgeandre@gmail.com"));
        c1.setCellValueFactory(new PropertyValueFactory<>("firstName"));
        c2.setCellValueFactory(new PropertyValueFactory<>("lastName"));
        c3.setCellValueFactory(new PropertyValueFactory<>("email"));
        tableView.setItems(persons);
    }
}
