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

package io.github.gleidsonmt.dashboardfx.core.view.util;

import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.scene.Node;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.ToggleButton;
import javafx.util.Duration;

/**
 * @author Gleidson Neves da Silveira | gleidisonmt@gmail.com
 * Create on  20/04/2022
 */
public class Scroll {

    public static void scrollTo(ScrollPane scrollPane, Node node, ToggleButton target) {
        double heightViewPort = scrollPane.getViewportBounds().getHeight();
        double heightScrollPane = scrollPane.getContent().getBoundsInLocal().getHeight();
        double y = node.getBoundsInParent().getMaxY();

        Timeline timeline = new Timeline();
        double yEnd = 0;

        if ( y < (heightViewPort / 2) ){ // set y for menor do que a metade
            // below 0 of scrollpane
            yEnd = 0;
        } else if ( ( y >= (heightViewPort / 2) ) & ( y<= (heightScrollPane - heightViewPort/2)) ){
            // between 0 and 1 of scrollpane
            yEnd = (y-(heightViewPort/2))/(heightScrollPane-heightViewPort);
        }
        else if(y>= (heightScrollPane-(heightViewPort/2))){
            // above 1 of scrollpane
            yEnd = 1;
        }

        timeline.getKeyFrames().addAll(
                new KeyFrame(Duration.ZERO, new KeyValue(
                        scrollPane.vvalueProperty(),  scrollPane.getVvalue()
                )),
                new KeyFrame(Duration.millis(200), new KeyValue(
                        scrollPane.vvalueProperty(), yEnd
                ))
        );

        timeline.play();
    }
    public static Timeline scrollTo(ScrollPane scrollPane, Node node) {

        double heightViewPort = scrollPane.getViewportBounds().getHeight();
        double heightScrollPane = scrollPane.getContent().getBoundsInLocal().getHeight();
        double y = node.getBoundsInParent().getMaxY();

        Timeline timeline = new Timeline();
        double yEnd = 0;

        if ( y < (heightViewPort / 2) ){ // set y for menor do que a metade
            // below 0 of scrollpane
            yEnd = 0;
        } else if ( ( y >= (heightViewPort / 2) ) & ( y<= (heightScrollPane - heightViewPort/2)) ){

            // between 0 and 1 of scrollpane
            yEnd = (y-(heightViewPort/2))/(heightScrollPane-heightViewPort);


        }
        else if(y>= (heightScrollPane-(heightViewPort/2))){
            // above 1 of scrollpane
            yEnd = 1;
        }


        timeline.getKeyFrames().addAll(
                new KeyFrame(Duration.ZERO, new KeyValue(
                        scrollPane.vvalueProperty(),  scrollPane.getVvalue()
                )),
                new KeyFrame(Duration.millis(200), new KeyValue(
                        scrollPane.vvalueProperty(), yEnd
                ))
        );

        timeline.play();
        return timeline;
//        timeline.setOnFinished(event -> rolling = true);
    }
    public static double getY(ScrollPane scrollPane, Node node) {

        double heightViewPort = scrollPane.getViewportBounds().getHeight();
        double heightScrollPane = scrollPane.getContent().getBoundsInLocal().getHeight();
//        double y = node.getBoundsInParent().getMaxY();
        double y = node.getBoundsInParent().getMaxY() + 200;

        double yEnd = 0;

        if ( y < (heightViewPort / 2) ){ // set y for menor do que a metade
            // below 0 of scrollpane
            yEnd = 0;
        } else if ( ( y >= (heightViewPort / 2) ) & ( y<= (heightScrollPane - heightViewPort/2)) ){

            // between 0 and 1 of scrollpane
            yEnd = (y-(heightViewPort/2))/(heightScrollPane-heightViewPort);


        }
        else if(y>= (heightScrollPane-(heightViewPort/2))){
            // above 1 of scrollpane
            yEnd = 1;
        }

        return yEnd;

    }
}
