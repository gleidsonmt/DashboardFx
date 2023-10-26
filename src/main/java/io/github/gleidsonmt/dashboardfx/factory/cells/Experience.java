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

/**
 * @author Gleidson Neves da Silveira | gleidisonmt@gmail.com
 * Create on  24/10/2023
 */
public enum Experience {

    BASIC("Basic", 1),
    MEDIUM("Medium", 2),
    ADVANCED("Advanced", 3),
    SPECIALIST("Specialist", 4);

    private final String experience;
    private int range;
    Experience(String experience, int range) {
        this.experience = experience;
        this.range = range;
    }

    public int getRange() {
        return range;
    }

    @Override
    public String toString() {
        return experience;
    }
}
