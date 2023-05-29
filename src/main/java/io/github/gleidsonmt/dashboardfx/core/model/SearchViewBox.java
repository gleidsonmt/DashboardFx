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
import javafx.collections.ListChangeListener;
import javafx.collections.transformation.FilteredList;
import javafx.event.ActionEvent;
import javafx.geometry.Insets;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.util.Callback;

/**
 * @author Gleidson Neves da Silveira | gleidisonmt@gmail.com
 * Create on  27/05/2023
 */
public class SearchViewBox extends VBox {

    private ListView<SearchItem> listView = new ListView<>();
    private TextField textField = new TextField();

    public SearchViewBox(Context context) {

        FilteredList<SearchItem> filteredList = new FilteredList<>(context.searchItems());
        textField.setPromptText("Search");

        filteredList.addListener((ListChangeListener<SearchItem>) c -> {
            if (c.next()) {
                listView.setMaxHeight(filteredList.size() * 48);
            }
        });

//        this.maxWidthProperty().bind(textField.widthProperty());
//        this.prefWidthProperty().bind(textField.widthProperty());
        this.setMaxHeight(filteredList.size() * 48);
        listView.setItems(filteredList);

        this.textField.setStyle("-fx-border-color: -light-gray-2;" +
                "-fx-border-radius: 20px;" +
                "-fx-min-height: 50px; -fx-border-width: 1px; ");

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

        this.getChildren().addAll(textField, listView);
        this.setSpacing(20);
        this.setPadding(new Insets(5));
    }
}
