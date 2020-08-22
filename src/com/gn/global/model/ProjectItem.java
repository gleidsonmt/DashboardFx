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
package com.gn.global.model;

import javafx.scene.layout.StackPane;

/**
 * @author Gleidson Neves da Silveira | gleidisonmt@gmail.com
 * Create on  27/05/2020
 */
public class ProjectItem<T extends Project> extends StackPane {


    public ProjectItem(T project, String img) {

        this.setPrefSize(300,300);

//        this.getStylesheets().add(getClass().getResource("/com/gn/media/img/projects/style.css").toExternalForm());
//        this.getStyleClass().add("img1");
//
        this.setStyle("-fx-background-image : url(\"/com/gn/media/img/" + img + ".png\");" +
                "-fx-background-size : contain; -fx-background-position : center; -fx-background-repeat : no-repeat;");

//        -fx-background-image : url("../../media/img/follow.jpg'");
//
//        GNAvatarView imageView = new GNAvatarView(new Image("/com/gn/media/img/" + img + ".jpg"));
//        imageView.setType(AvatarType.RECT);
////        imageView.setImage();
//
//        imageView.setPrefSize(1280, 720);
////        imageView.setFitHeight(720);
//
//        this.getChildren().add(imageView);

        this.setUserData(project);
    }
}
