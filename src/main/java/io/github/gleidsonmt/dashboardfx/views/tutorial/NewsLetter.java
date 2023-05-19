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

package io.github.gleidsonmt.dashboardfx.views.tutorial;

import io.github.gleidsonmt.dashboardfx.core.Context;
import io.github.gleidsonmt.dashboardfx.core.view.layout.creators.TutorialCreator;

import java.time.LocalDate;

public class NewsLetter extends TutorialCreator {

    public NewsLetter(Context context) {
        super(context);

        this
                .title(LocalDate.of(2023, 5, 18).toString())
                .text("Add flow to open custom dialog.")
                .title(LocalDate.of(2023, 5, 18).toString())
                .text("Add hamburger when the size was updated.")
                .title(LocalDate.of(2023, 5, 17).toString())
                .text("Update module tutorial, understanding view, add a navigation view.")
                .title(LocalDate.of(2023, 5, 13).toString())
                .text("Presenting newsletter I hope can write at least once per week here!")
                .build();

    }

}
