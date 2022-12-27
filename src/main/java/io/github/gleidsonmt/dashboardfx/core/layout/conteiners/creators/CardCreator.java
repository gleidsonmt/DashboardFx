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

package io.github.gleidsonmt.dashboardfx.core.layout.conteiners.creators;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.image.Image;
import javafx.scene.layout.*;
import javafx.scene.paint.ImagePattern;
import javafx.scene.text.Text;
import org.jetbrains.annotations.ApiStatus;


@ApiStatus.Experimental
public class CardCreator extends VBox {

    public CardCreator(Image _image, String _title, String text, EventHandler<ActionEvent>... events) {

        setStyle("-fx-border-color : -light-gray-2; ");

        StackPane pane = new StackPane();
        pane.setPrefSize(200, 200);
        pane.setBackground(
                new Background(
                        new BackgroundFill(new ImagePattern(_image),
                                CornerRadii.EMPTY, Insets.EMPTY
                        )
                )
        );

        Text title = new Text(_title);
        title.getStyleClass().addAll("text-14", "title");
        title.setStyle("-fx-font-weigth : bold;");
        VBox.setMargin(title, new Insets(10));

        Text text1 = new Text(text);

        this.setSpacing(10);
        this.getChildren().setAll(
            pane, title, text1
        );

        VBox.setMargin(text1, new Insets(0,0,0,10));

    }

}
