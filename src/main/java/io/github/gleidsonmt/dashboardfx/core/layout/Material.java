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

package io.github.gleidsonmt.dashboardfx.core.layout;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class Material {

    public static ObservableList<String> stylesheets() {
        return FXCollections.observableArrayList(
                "/core.app/css/colors.css",
                "/core.app/css/skeleton.css",
                "/core.app/css/theme/simple_info.css",
                "/core.app/css/bootstrap.css",
                "/core.app/css/typographic.css",
                "/core.app/css/imersive_scroll.css",
                "/core.app/css/shapes.css",
                "/core.app/css/helpers.css",
                "/dash.css"
        );
    }
}
