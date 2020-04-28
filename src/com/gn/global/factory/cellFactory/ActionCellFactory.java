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
package com.gn.global.factory.cellFactory;

import com.gn.global.factory.cells.ActionCell;
import com.gn.global.model.Model;
import javafx.geometry.Pos;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.util.Callback;

/**
 * @author Gleidson Neves da Silveira | gleidisonmt@gmail.com
 * Create on  30/07/2019
 */
public class ActionCellFactory<E extends Model> implements Callback<TableColumn<E, E>, TableCell<E, E>> {

    @Override
    public TableCell<E, E> call(TableColumn<E, E> param) {
        return new TableCell<E, E>(){
            @Override
            protected void updateItem(E item, boolean empty) {
                super.updateItem(item, empty);

                if(item != null) {
                    ActionCell<E> cell = new ActionCell<>(item);
                    setAlignment(Pos.BASELINE_RIGHT);

//                        getTableRow().setOnMouseEntered(event -> {
//
//                            new Swing(this.getTableRow()).play();
////                            FadeIn fadeIn = new FadeIn(this.getTableRow());
////                            fadeIn.setSpeed(20);
////                            fadeIn.play();
////                            shake.play();
//                        });

                    cell.getChildren().get(0).setOnMousePressed(event -> {
                        getTableView().getSelectionModel().select(item);
//                            Views.sendRequisition(new Requisition<>(FormAction.VIEW, item));

                    });

                    cell.getChildren().get(1).setOnMousePressed(event -> {
                        getTableView().getSelectionModel().select(item);
//                            Views.sendRequisition(new Requisition<>(FormAction.UPDATE, item));
                    });

                    cell.getChildren().get(2).setOnMousePressed(evento -> {
                        getTableView().getSelectionModel().select(item);

//                        Button cancel = new Button(I18n.get("Button.cancel"));
//                        Button confirm = new Button(I18n.get("Button.confirm"));

//                        confirm.setOnAction(event -> {
//                            ClientDao.INSTANCE.getItems().remove(item);
//                        });
//                        Alerts.confirmation(I18n.get("Dialog.remove"), I18n.get("Dialog.remove.table"), confirm, cancel);
                    });

                    setGraphic(cell);
                    setItem(item);
                    setText(null);
                } else {
                    setItem(null);
                    setText(null);
                    setGraphic(null);
                }

            }
        };
    }
}