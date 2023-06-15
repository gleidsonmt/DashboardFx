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

package io.github.gleidsonmt.dashboardfx.views.controls;

import io.github.gleidsonmt.dashboardfx.core.Context;
import io.github.gleidsonmt.dashboardfx.core.controls.GNBadge;
import io.github.gleidsonmt.dashboardfx.core.controls.Rating;
import io.github.gleidsonmt.dashboardfx.core.controls.icon.Icons;
import io.github.gleidsonmt.dashboardfx.core.model.Data;
import io.github.gleidsonmt.dashboardfx.core.view.layout.creators.TableCreator;
import io.github.gleidsonmt.dashboardfx.core.view.layout.creators.TutorialCreator;
import javafx.scene.control.Label;

import java.util.List;

/**
 * @author Gleidson Neves da Silveira | gleidisonmt@gmail.com
 * Create on  19/01/2023
 */
public class RatingPresCreator extends TutorialCreator {

    public RatingPresCreator(Context context) {
        super( context);
        this    .title("Intro")
                .text("""
                The component allows users to give something a rating between 1 and more stars with a single mouse click.
                """)
                .demonstration(
                        new Rating(5, 2),
                        """
                        // Constructing label
                        Rating rating = new Rating();
                        // Setting a number of stars
                        rating.setNumberOfStars(5);
                        // Setting rang
                        rating.setRange(3);
                        """,
                        """
                        <!-- Building -->
                        <Rating range="3" numberOfStars="5"/>
                        """)
                .title("Custom CSS")
                .text("StyleClass => .rating")
                .text("Children => .star")
                .table(
                        new TableCreator<>(context)
                                .columns("name", "value")
                                .data(
                                        new Data("-gn-number-of-stars", "[<number>]"),
                                        new Data("-gn-range", "[<number>]")
                                )
                                .build()
                )
                .title("Links")
                .footer(createDefaultControl())
                .build();
    }
}
