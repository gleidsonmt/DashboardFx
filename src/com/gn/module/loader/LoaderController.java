/*
 * Copyright (C) Gleidson Neves da Silveira
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package com.gn.module.loader;

import com.gn.App;
import javafx.animation.RotateTransition;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.shape.Circle;
import javafx.util.Duration;

import java.net.URL;
import java.util.ResourceBundle;

/**
 * @author Gleidson Neves da Silveira | gleidisonmt@gmail.com
 * Create on  28/06/2020
 */
public class LoaderController implements Initializable {

    @FXML private Circle one;
    @FXML private Circle two;
    @FXML private Circle three;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        rotate(one, 360, 10);
        rotate(two, 180, 18);
        rotate(three, 145, 24);
    }

    private void rotate(Circle circle, int angle, int duration){
        RotateTransition rotate = new RotateTransition(Duration.seconds(duration), circle);

        rotate.setAutoReverse(true);

        rotate.setByAngle(angle);
        rotate.setDelay(Duration.seconds(0));
        rotate.setRate(3);
        rotate.setCycleCount(18);
        rotate.play();
    }

    @FXML
    private void goTutorial(){
        App.openLink("https://www.youtube.com/watch?v=Fy0ZVF7EPC4&list=PLCgHdMGvMMH5skagmen7plm5D5x8OYXU8&index=3");
    }
}
