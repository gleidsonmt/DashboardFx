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
import io.github.gleidsonmt.dashboardfx.core.controls.GNCarousel;
import io.github.gleidsonmt.dashboardfx.core.model.Data;
import io.github.gleidsonmt.dashboardfx.core.view.layout.creators.TableCreator;
import io.github.gleidsonmt.dashboardfx.core.view.layout.creators.TutorialCreator;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;

/**
 * @author Gleidson Neves da Silveira | gleidisonmt@gmail.com
 * Create on  24/05/2023
 */
public class CarouselViewPresCreator extends TutorialCreator {
    public CarouselViewPresCreator(Context context) {
        super(context);
        this
                .title("Carousel")
                .text("""
                        The carousel is a control of the overlapping layer, which navigates about her, showing one for once.
                        """)
                .demonstration(
                        createCarousel(createItems()),
//                        new Label(),
                                """
                                     //  Creating children
                                     GNCarousel carousel = new GNCarousel();
                                     // Passing children
                                     carousel.setItems(
                                         new Label("Label 1"),
                                         new Label("Label 2"),
                                         new Label("Label 3")
                                     );
                                     // Setting auto ride
                                     carousel.setAutoRide(false);
                                     // arrows
                                     carousel.setArrows(false);
                                    """,
                                """
                                     <!-- Building -->
                                     <GNCarousel >
                                        <!-- Defining children -->
                                             <items>
                                                 <FXCollections fx:factory="observableArrayList">
                                                     <Label text="First"/>
                                                     <Label text="Second"/>
                                                     <Label text="Third"/>
                                                 </FXCollections>
                                             </items>
                                         
                                         </GNCarousel>
                                         <!-- Without arrows -->
                                     <GNCarousel arrows="false"/>
                                        """
                )
                .title("Custom CSS")
                .table(
                        new TableCreator<Data>(context)
                                .column("name")
                                .column("value")
                                .data(
                                        new Data("-gn-auto-ride", "[<boolean>]"),
                                        new Data("-gn-transition-duration", "[<number>ms | <number>s]")
                                )
                                .build()
                )
                .title("Links")
                .footer(createDefaultControl())
                .build();
    }

    private GNCarousel<Node> createCarousel(ObservableList<Node> items) {
        return  new GNCarousel<>(items);
    }

    private ObservableList<Node> createItems(){

        Label lb1 = new Label("First");
        Label lb2 = new Label("Second");
        Label lb3 = new Label("Third");
        Label lb4 = new Label("Fourth");
        Label lb5 = new Label("Fifth");

        Button btn = new Button("Pedir agora");

        lb1.setStyle("-fx-text-fill : white; -fx-font-size : 24px;");
        lb2.setStyle("-fx-text-fill : white; -fx-font-size : 24px;");
        lb3.setStyle("-fx-text-fill : white; -fx-font-size : 24px;");
        lb4.setStyle("-fx-text-fill : white; -fx-font-size : 24px;");
        lb5.setStyle("-fx-text-fill : white; -fx-font-size : 24px;");

        VBox v1 = new VBox(lb1, btn);
        VBox v2 = new VBox(lb2);
        VBox v3 = new VBox(lb3);
        VBox v4 = new VBox(lb4);
        VBox v5 = new VBox(lb5);

        v1.setAlignment(Pos.CENTER);
        v2.setAlignment(Pos.CENTER);
        v3.setAlignment(Pos.CENTER);
        v4.setAlignment(Pos.CENTER);
        v5.setAlignment(Pos.CENTER);

        v1.setStyle("-fx-background-color : #FF3547;");
        v2.setStyle("-fx-background-color : #512DA8;");
        v3.setStyle("-fx-background-color : #48CFAD;");
        v4.setStyle("-fx-background-color : #02C852;");
        v5.setStyle("-fx-background-color : #EC407A;");

        return FXCollections.observableArrayList(v1, v2, v3, v4, v5);
    }

}
