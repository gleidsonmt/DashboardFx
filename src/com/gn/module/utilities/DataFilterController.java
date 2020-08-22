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
package com.gn.module.utilities;

import com.gn.global.factory.DefaultFactory;
import com.gn.global.factory.cellFactory.ActionCellFactory;
import com.gn.global.plugin.ActionView;
import com.gn.global.model.Country;
import com.gn.global.plugin.DataFilterHandler;
import com.gn.global.plugin.GridFxOld;
import javafx.collections.FXCollections;
import javafx.collections.transformation.FilteredList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;

import java.net.URL;
import java.util.ResourceBundle;

/**
 * @author Gleidson Neves da Silveira | gleidisonmt@gmail.com
 * Create on  27/04/2020
 */
public class DataFilterController implements Initializable, ActionView {

    @FXML private Button                btnSearch;
    @FXML private Button                btnAdd;
    @FXML private TextField             tfSearch;
    @FXML private ComboBox<Integer>     cmbEntries;
    @FXML private TableView<Country>    tvCountries;
    @FXML private TableColumn<Country, String> colName;
    @FXML private TableColumn<Country, String> colCode;
    @FXML private TableColumn<Country, String> colAbv;
    @FXML private TableColumn<Country, String> colAbv3;
    @FXML private TableColumn<Country, String> colAbv3Alt;
    @FXML private TableColumn<Country, String> colSlug;

    @FXML private TableColumn<Country, Country> colActions;

    @FXML private Label                 lblLegend;
    @FXML private Hyperlink             hlFirst;
    @FXML private Pagination            pagination;
    @FXML private Hyperlink             hlLast;
    
    @FXML private GridPane gridTitle;
    @FXML private GridPane gridLegend;

    private final Country country = new Country();

    private DataFilterHandler<Country> dataFilter;
    private FilteredList<Country> filteredList;

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        filteredList = new FilteredList<>( FXCollections.observableArrayList( country.getAllinList() ) , e -> true );

        dataFilter = new DataFilterHandler<>(
                tvCountries, hlFirst, hlLast, pagination, cmbEntries, lblLegend, filteredList
        );

        configFactories();
    }

    private void configFactories(){
        colName.setCellValueFactory(cell -> cell.getValue().nameProperty());
        colCode.setCellValueFactory(cell -> cell.getValue().codeProperty());
        colAbv.setCellValueFactory(cell -> cell.getValue().abvProperty());
        colAbv3.setCellValueFactory(cell -> cell.getValue().abv3Property());
        colAbv3Alt.setCellValueFactory(cell -> cell.getValue().abv3_altProperty());
        colSlug.setCellValueFactory(cell -> cell.getValue().slugProperty());

        colActions.setCellValueFactory(new DefaultFactory<>());
        colActions.setCellFactory(new ActionCellFactory<>());
    }

    @Override
    public void enter() {
        GridFxOld.add(gridTitle, "grid-table-title");
        GridFxOld.add(gridLegend, "grid-table-legend");
    }

    @Override
    public void exit() {
        GridFxOld.remove("grid-table-title");
        GridFxOld.remove("grid-table-legend");
    }
}
