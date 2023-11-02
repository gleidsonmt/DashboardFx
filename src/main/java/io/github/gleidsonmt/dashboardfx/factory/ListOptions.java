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

package io.github.gleidsonmt.dashboardfx.factory;

import animatefx.animation.*;
import io.github.gleidsonmt.dashboardfx.core.Context;
import io.github.gleidsonmt.dashboardfx.core.controls.icon.IconContainer;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.input.MouseEvent;
import javafx.util.Callback;

import java.awt.*;

/**
 * @author Gleidson Neves da Silveira | gleidisonmt@gmail.com
 * Create on  28/10/2023
 */
public class ListOptions {

    private final ListView<Option> listView;
    private final Context context;

    public ListOptions(Context context) {
        listView = new ListView<>();
        options = FXCollections.observableArrayList();
        listView.getStyleClass().add("selected-list");
        this.context = context;
    }

    private ObservableList<Option> options;

    public ListOptions items(ObservableList<Option> options) {
        this.options = options;
        return this;
    }

    public ListOptions items(Option... options) {
        this.options.addAll(options);
        return this;
    }

    public ListView<Option> build() {
        listView.setCellFactory(new Callback<>() {
            @Override
            public ListCell<Option> call(ListView<Option> param) {
                return new ListCell<>() {
                    @Override
                    protected void updateItem(Option item, boolean empty) {
                        super.updateItem(item, empty);
                        if (item != null) {
                            IconContainer iconContainer = new IconContainer(item.icon());
                            iconContainer.setScaleX(0.75);
                            iconContainer.setScaleY(0.75);
                            setGraphic(iconContainer);
                            setText(item.text());
                            this.getStyleClass().add("h5");
                            setGraphicTextGap(10);

                            this.addEventFilter(MouseEvent.MOUSE_CLICKED, event -> {
                                AnimationFX animation = new SlideOutRight(this);
                                animation.setSpeed(5);
                                animation.getTimeline().setOnFinished(e -> {
                                    context.flow().close();
                                });
                                animation.play();
                            });

                            this.setOnMouseClicked(item.event());

//                            this.setStyle("-fx-font-weight: bold");
//                            this.setLineSpacing(10);
                        } else {
                            setGraphic(null);
                            setText(null);
                        }
                    }
                };
            }
        });
        listView.setItems(options);
        return listView;
    }
}
