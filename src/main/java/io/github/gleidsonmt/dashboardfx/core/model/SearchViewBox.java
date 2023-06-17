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

package io.github.gleidsonmt.dashboardfx.core.model;

import io.github.gleidsonmt.dashboardfx.core.Context;
import io.github.gleidsonmt.dashboardfx.core.controls.GNTextBox;
import io.github.gleidsonmt.dashboardfx.core.controls.icon.IconContainer;
import io.github.gleidsonmt.dashboardfx.core.controls.icon.Icons;
import javafx.collections.ListChangeListener;
import javafx.collections.transformation.FilteredList;
import javafx.event.ActionEvent;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.util.Callback;

/**
 * @author Gleidson Neves da Silveira | gleidisonmt@gmail.com
 * Create on  27/05/2023
 */
public class SearchViewBox extends VBox {

    private final ListView<SearchItem> listView = new ListView<>();
    private final HBox searchBox = new HBox();
    private final TextField  textField = new TextField();

    @SuppressWarnings("unchecked")
    public SearchViewBox(Context context) {

        FilteredList<SearchItem> filteredList = new FilteredList<>(context.searchItems());
        textField.setPromptText("Search");

        filteredList.addListener((ListChangeListener<SearchItem>) c -> {
            if (c.next()) {
                listView.setMaxHeight(filteredList.size() * 48);
            }
        });

//        textField.setIcon(Icons.SEARCH);
//        textField.setAction(true);
        textField.setPromptText("Search");
        textField.setId("tf-search");

//        this.maxWidthProperty().bind(textField.widthProperty());
//        this.prefWidthProperty().bind(textField.widthProperty());

        this.setMaxHeight(filteredList.size() * 48);
        listView.setItems(filteredList);

        textField.textProperty().addListener((observable, oldValue, newValue) -> {
            filteredList.setPredicate(searchItem -> searchItem.getName().contains(newValue));

        });

        listView.setCellFactory(new Callback<ListView<SearchItem>, ListCell<SearchItem>>() {
            @Override
            public ListCell<SearchItem> call(ListView<SearchItem> param) {
                return new ListCell<>() {
                    @Override
                    protected void updateItem(SearchItem item, boolean empty) {
                        super.updateItem(item, empty);
                        if (item != null) {
                            setText(item.getName());
                            setOnMouseClicked(event -> {
                                item.getAction().handle(new ActionEvent());
                                context.wrapper().close();
                            });
                        } else {
                            setText(null);
                        }
                    }
                };
            }
        });

        IconContainer icon = new IconContainer(Icons.SEARCH);
        searchBox.getChildren().addAll(icon, textField);
        searchBox.setMinHeight(50);
        searchBox.setAlignment(Pos.CENTER_LEFT);
        searchBox.setStyle("-fx-border-color: -medium-gray; -fx-border-width: 0px 0px 1px 0px");

        this.getChildren().addAll(searchBox, listView);
        this.setSpacing(20);
        this.setPadding(new Insets(5));
    }
}
