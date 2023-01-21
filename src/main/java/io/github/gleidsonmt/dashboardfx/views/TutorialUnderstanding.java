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
                .text("In views package create a file named HelloView.java ", "text-12", "text-bold")
                .code("""
                        public class HelloView extends Container {
                           \s
                            public HelloView(String name) {
                                super(name);
                                getChildren().setAll(new Label("Hello View");
                            }
                           \s
                        }
                        """)
                .text("In DrawerController create one action..", "text-12", "text-bold")
                .code("""
                        public class DrawerController {
                        \s
                            public DrawerController(IWrapper _wrapper) {
                                super(_wrapper);
                            }
                                                
                            @FXML
                            private void goHello() {
                                context.routes().registryAndGo(new HelloView("hello_view"));
                            }
                        \s
                        }
                        """)

                .text("Now in your drawer.fxml set the one toggle button the action and you go to the HelloView!", "text-12", "text-bold")
                ;

        setAlignment(Pos.CENTER);

        build();

    }
}
