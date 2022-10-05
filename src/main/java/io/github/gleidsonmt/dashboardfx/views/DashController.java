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

package io.github.gleidsonmt.dashboardfx.views;

import io.github.gleidsonmt.dashboardfx.core.app.interfaces.ActionView;
import io.github.gleidsonmt.dashboardfx.core.app.interfaces.Context;
import io.github.gleidsonmt.dashboardfx.core.layout.conteiners.SnackBar;
import javafx.fxml.FXML;

/**
 * @author Gleidson Neves da Silveira | gleidisonmt@gmail.com
 * Create on  04/10/2022
 */
public final class DashController implements ActionView, Context {


    @FXML
    private void bottonClick() {

        context.getDecorator()
                .getRoot()
                .createSnackBar()
                .message("My Message")
                .color(SnackBar.Colors.SUCCESS)
                .show();

        System.out.println("button clicked");

    }

    @Override
    public void onEnter() {

    }

    @Override
    public void onExit() {

    }
}
