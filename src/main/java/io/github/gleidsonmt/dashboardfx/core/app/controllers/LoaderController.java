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

package io.github.gleidsonmt.dashboardfx.core.app.controllers;

import io.github.gleidsonmt.dashboardfx.controllers.DrawerController;
import io.github.gleidsonmt.dashboardfx.core.app.exceptions.NavigationException;
import io.github.gleidsonmt.dashboardfx.core.app.services.Context;
import io.github.gleidsonmt.dashboardfx.core.app.services.LoadViews;
import io.github.gleidsonmt.dashboardfx.core.layout.IWrapper;
import javafx.animation.RotateTransition;
import javafx.concurrent.Task;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.shape.Circle;
import javafx.util.Duration;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

/**
 * @author Gleidson Neves da Silveira | gleidisonmt@gmail.com
 * Create on  15/02/2022
 */
public class LoaderController implements Initializable {

    @FXML private Circle one;
    @FXML private Circle two;
    @FXML private Circle three;
    @FXML private Label lblInfo;
    @FXML private Label lblTitle;

    private Context context;
    public LoaderController(Context context) {
        this.context = context;
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        rotate(one, 360, 10);
        rotate(two, 180, 18);
        rotate(three, 60, 22);

        Task<String> loadViews = new LoadViews(context, lblInfo);

        loadViews.setOnSucceeded(event -> {
            lblTitle.setText("Done!");
            context.routes().navigate("layout");

            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("/views/drawer.fxml"));
//            loader.setController(new DrawerController((IWrapper) context.getWrapper()));

            try {
                loader.load();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }

            context.routes().registry("drawer", loader);

            try {
                context.routes().setView("dash");
            } catch (NavigationException e) {
                throw new RuntimeException(e);
            }

            context.layout().setDrawer(
                    context.routes().getView("drawer")
            );
        });

        Thread tLoadViews = new Thread(loadViews);
        tLoadViews.setDaemon(true);
        tLoadViews.start();
    }

    private void rotate(Circle circle, int angle, int duration) {
        RotateTransition rotate = new RotateTransition(Duration.seconds(duration), circle);

        rotate.setAutoReverse(true);

        rotate.setByAngle(angle);
        rotate.setDelay(Duration.seconds(0));
        rotate.setRate(3);
        rotate.setCycleCount(-1);
        rotate.play();

    }
}
