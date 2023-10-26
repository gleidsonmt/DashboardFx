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

package io.github.gleidsonmt.dashboardfx.factory.cells;

import io.github.gleidsonmt.dashboardfx.core.util.I18n;
import io.github.gleidsonmt.dashboardfx.model.Item;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.util.Callback;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.Currency;
import java.util.Locale;

/**
 * @author Gleidson Neves da Silveira | gleidisonmt@gmail.com
 * Create on  26/10/2023
 */
public class MonetaryCellFactory<T extends Item> implements Callback<TableColumn<T, BigDecimal>, TableCell<T, BigDecimal>> {
    @Override
    public TableCell<T, BigDecimal> call(TableColumn<T, BigDecimal> param) {
        return new TableCell<>() {
            @Override
            protected void updateItem(BigDecimal item, boolean empty) {
                super.updateItem(item, empty);
                if (item != null) {
                    Currency currency = Currency.getInstance(I18n.getLocale());

                    DecimalFormat formatter = (DecimalFormat) NumberFormat.getCurrencyInstance();

                    setText(formatter.format(item));
                } else {
                    setText(null);
                }
            }
        };
    }
}
