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

package io.github.gleidsonmt.dashboardfx.views;

import io.github.gleidsonmt.dashboardfx.core.app.services.Context;
import io.github.gleidsonmt.dashboardfx.core.layout.conteiners.creators.TutorialCreator;
import javafx.geometry.Pos;
import javafx.scene.image.Image;

import java.util.Objects;

/**
 * @author Gleidson Neves da Silveira | gleidisonmt@gmail.com
 * Version 0.0.1
 * Create on  19/01/2023
 */
public class TutorialUnderstanding extends TutorialCreator {

    public TutorialUnderstanding(String name, Context context) {
        super(name, context);

        this    .title("""
                        Point of Start
                        """)
                .text("""
                        If you like me and passionate about java, you will see that javafx is 
                        the best way to build apps for desktops. I spend too much time searching good 
                        tutorials and I found, but when I had this solutions in my hand, 
                        I saw yes it's time to create my way to get there!
                        """)
                .text("""
                        Good Frameworks has a big ecosystem, so I was 
                        start ways to get that, because the first problem 
                        I have with javafx is getting one controller from other view..
                        and so I just build this dash -_(^-^-_
                        """)
                .text("""
                        First thing I believe it's way to get views from controllers.. navigate for the other views.. and the menu to get ther! 
                        """)
                .title("""
                        Hello World View!
                        """)
                .subTitle("Look at this tree.")
                .image(new Image(
                        Objects.requireNonNull(getClass().getResource(
                                "/core.app/img/light_tree.jpg"))
                                .toExternalForm()
                ))
                .text("")
                ;

        setAlignment(Pos.CENTER);

        build();

    }
}
