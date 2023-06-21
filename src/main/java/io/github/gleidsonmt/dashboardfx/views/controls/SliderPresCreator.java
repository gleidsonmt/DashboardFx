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
import io.github.gleidsonmt.dashboardfx.core.view.layout.creators.TutorialCreator;
import javafx.geometry.Orientation;
import javafx.scene.control.Slider;
import org.jetbrains.annotations.NotNull;

import java.util.List;

/**
 * @author Gleidson Neves da Silveira | gleidisonmt@gmail.com
 * Create on  20/06/2023
 */
public class SliderPresCreator extends TutorialCreator {
    public SliderPresCreator(Context context) {
        super(context);

        this    .title("Intro")
                .text("""
                        The Slider Control is used to display a continuous or discrete range of valid numeric choices and allows the user to interact with the control. It is typically represented visually as having a "track" and a "knob" or "thumb" which is dragged within the track. The Slider can optionally show tick marks and labels indicating the different slider position values.
                        """)
                .demonstration(
                        List.of(
                                new Slider(),
                                crateSlider(Orientation.HORIZONTAL),
                                crateSlider(Orientation.VERTICAL)
                                ),
                        """
                                // Constructor
                                Slider slider = new Slider();
                                // Setting orientation
                                slider.setOrientation(Orientation.VERTICAL);
                                // show ticket marks
                                slider.setShowTickMarks(true);
                                // show numbers
                                slider.setShowTickLabels(true);
                                """)
                .title("Links")
                .footer(
                        createDefaultAuthor("Slider"),
                        createUserDefault()
                )
                .build();
    }

    private @NotNull Slider crateSlider(Orientation orientation) {
        Slider slider = new Slider();
        slider.setShowTickLabels(true);
        slider.setShowTickMarks(true);
        slider.setOrientation(orientation);
        return slider;
    }
}
