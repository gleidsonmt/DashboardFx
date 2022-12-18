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
package io.github.gleidsonmt.dashboardfx.core.app.material.color;

import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;

/**
 * @author Gleidson Neves da Silveira | gleidisonmt@gmail.com
 * Create on  29/11/2021
 */
public enum Colors {

    AQUA("#4FC1E9"),
    MINT("#48CFAD"),
    PURPLE("#AA66CC"),
    AMBER("#FFA000"),
    GRAPEFRUIT("#ED5565");

    private final String content;

    Colors(String content) {
        this.content = content;
    }

    private Paint getColor() {
        return Color.web(content);
    }

    public static Color get(String name) {
        Color color = null;
        for (Colors c : Colors.values()) {
            if (c.getName().equalsIgnoreCase(name)) {
                color = (Color) c.getColor();
            }
        }

        if (color == null) try {
            throw new Exception("Error custom color not found.");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return color;
    }



    @Contract(pure = true)
    public @NotNull String getName() {
        return super.name();
    }

    @Override
    public @NotNull String toString() {
        return this.content;
    }

}
