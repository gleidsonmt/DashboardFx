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

package io.github.gleidsonmt.dashboardfx.controllers;

import io.github.gleidsonmt.dashboardfx.core.Context;
import io.github.gleidsonmt.dashboardfx.core.interfaces.ActionView;
import io.github.gleidsonmt.dashboardfx.core.picture_selector.PictureSelectorController;
import io.github.gleidsonmt.dashboardfx.core.view.View;
import io.github.gleidsonmt.dashboardfx.core.view.layout.DialogContainer;
import javafx.fxml.FXML;
import javafx.geometry.HPos;
import javafx.geometry.VPos;
import javafx.scene.Node;
import javafx.scene.image.Image;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Priority;
import javafx.scene.layout.StackPane;

/**
 * @author Gleidson Neves da Silveira | gleidisonmt@gmail.com
 * Create on  29/06/2023
 */
public class SettingsController extends ActionView {

    @FXML private StackPane root;
    @FXML private GridPane tileOne;
    @FXML private GridPane tileTwo;
    @FXML private GridPane tileThree;

    @FXML
    private void changeAvatar() {
        View view = context.routes().getView("picture");
        Image image = new Image(context.getResource("style/img/avatar.png").toExternalForm());

        ((PictureSelectorController) view.getController())
                .setImage(image);

        context.wrapper()
                .content(
                        new DialogContainer(view.getRoot())
                                .size(600, 650
//                                        image.getWidth() + 50,
//                                        image.getHeight() + 100
                                        )
                )
                .show();
    }

    @Override
    public void onInit(Context context) {
        super.onInit(context);
        root.widthProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue.doubleValue() < 800) {
                org(tileOne.getChildren().get(0), tileOne.getChildren().get(1), false);
                org(tileTwo.getChildren().get(0), tileTwo.getChildren().get(1), false);
                org(tileThree.getChildren().get(0), tileThree.getChildren().get(1), false);
            } else {
                org(tileOne.getChildren().get(0), tileOne.getChildren().get(1), true);
                org(tileTwo.getChildren().get(0), tileTwo.getChildren().get(1), true);
                org(tileThree.getChildren().get(0), tileThree.getChildren().get(1), true);
            }
        });
    }

    private void org(Node one, Node two, boolean line) {
        if (!line) {
            GridPane.setConstraints(one, 0, 0, 1, 1, HPos.LEFT, VPos.CENTER, Priority.ALWAYS, Priority.ALWAYS);
            GridPane.setConstraints(two, 0, 1, 1, 1, HPos.LEFT, VPos.CENTER, Priority.ALWAYS, Priority.SOMETIMES);
        } else {
            GridPane.setConstraints(two, 0, 0, 1, 1, HPos.LEFT, VPos.CENTER, Priority.ALWAYS, Priority.ALWAYS);
            GridPane.setConstraints(one, 1, 0, 1, 1, HPos.LEFT, VPos.CENTER, Priority.ALWAYS, Priority.ALWAYS);
        }
    }
}