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

import com.gn.global.plugin.ActionView;
import com.gn.global.plugin.GridFx;
import javafx.fxml.FXML;
import javafx.scene.layout.VBox;

/**
 * @author Gleidson Neves da Silveira | gleidisonmt@gmail.com
 * Create on  29/04/2020
 */
public class TypographyController implements ActionView {

    @FXML private VBox bodyContent;

    @Override
    public void enter() {
        GridFx.defineMin("min-typography-controller", bodyContent, 850, 600, 600,600,600);
    }

    @Override
    public void exit() {
        GridFx.removeMin("min-typography-controller");
    }
}
