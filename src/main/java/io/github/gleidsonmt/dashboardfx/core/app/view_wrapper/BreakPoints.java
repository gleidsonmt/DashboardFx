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

package io.github.gleidsonmt.dashboardfx.core.app.view_wrapper;

public class BreakPoints {

    public static int X_SMALL = 576;
    public static int SMALL = 576;
    public static  int MEDIUM = 768;
    public static  int LARGE = 992;
    public static  int X_LARGE = 1200;
    public static  int XX_LARGE = 1400;


    public static boolean isXSmall(Number number) {
        return number.doubleValue() < X_SMALL;
    }

    public static boolean isSmall(Number number) {
        return number.doubleValue() >= SMALL &&  number.doubleValue() < MEDIUM;
    }

    public static boolean isMedium(Number number) {
        return number.doubleValue() >= MEDIUM && number.doubleValue() < LARGE;
    }

    public static boolean isLarge(Number number) {
        return number.doubleValue() >= LARGE && number.doubleValue() < X_LARGE;
    }

    public static boolean isXLarge(Number number) {
        return number.doubleValue() >= X_LARGE && number.doubleValue() < XX_LARGE;
    }

    public static boolean isXXLarge(Number number) {
        return number.doubleValue() >= XX_LARGE;
    }
}
