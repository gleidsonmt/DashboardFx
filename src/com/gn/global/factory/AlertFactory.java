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
package com.gn.global.factory;

import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.util.Callback;

/**
 * @author Gleidson Neves da Silveira | gleidisonmt@gmail.com
 * Create on  21/11/2018
 * Version 1.0
 */
public class AlertFactory<T> implements Callback<ListView<T>, ListCell<AlertCell>> {

    @Override
    public ListCell<AlertCell> call(ListView<T> param) {
        return new ListCell<AlertCell>(){
          @Override
          protected void updateItem(AlertCell item, boolean empty) {
              super.updateItem(item, empty);
              if(item == null || empty) {
                  setItem(null);
                  setGraphic(null);
                  setText(null);
              } else {
                  setItem(item);
//                  setText(item.get);
                  System.out.println(item.getIcon());
                  setGraphic(item.getIcon());
              }
          }
        };
    }
}
