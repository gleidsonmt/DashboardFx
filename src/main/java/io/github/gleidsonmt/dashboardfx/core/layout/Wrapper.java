/*
 *    Copyright (C) Gleidson Neves da Silveira
 *
 *    This program is free software: you can redistribute it and/or modify
 *    it under the terms of the GNU General Public License as published by
 *    the Free Software Foundation, either version 3 of the License, or
 *    (at your option) any later version.
 *
 *    This program is distributed in the hope that it will be useful,
 *    but WITHOUT ANY WARRANTY; without even the implied warranty of
 *    MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *    GNU General Public License for more details.
 *
 *    You should have received a copy of the GNU General Public License
 *    along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */

package io.github.gleidsonmt.dashboardfx.core.layout;

import io.github.gleidsonmt.dashboardfx.core.app.interfaces.IWrapper;
import io.github.gleidsonmt.dashboardfx.core.layout.conteiners.Alert;
import javafx.geometry.Insets;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;

/**
 * @author Gleidson Neves da Silveira | gleidisonmt@gmail.com
 * Create on  05/10/2022
 */
public class Wrapper extends StackPane implements IWrapper {

    public Wrapper() {

        setBackground(
                new Background(
                        new BackgroundFill(
                                Color.gray(0.5, 0.3),
//                                Color.RED,
                                CornerRadii.EMPTY,
                                Insets.EMPTY)
                )
        );

        setId("wrapper");

    }

    private Alert alert;

    @Override
    public Alert getAlert() {
        if (alert == null) alert = new Alert(this);
        return alert;
    }


}
