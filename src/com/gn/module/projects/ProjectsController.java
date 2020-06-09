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
package com.gn.module.projects;

import com.gn.GNCarousel;
import com.gn.global.model.Project;
import com.gn.global.model.ProjectItem;
import com.gn.global.plugin.ProjectsAccessor;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;

import java.net.URL;
import java.util.ResourceBundle;

/**
 * @author Gleidson Neves da Silveira | gleidisonmt@gmail.com
 * Create on  27/05/2020
 */
public class ProjectsController implements Initializable {

    @FXML private GNCarousel carousel;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        carousel.getItems().addAll(new ProjectsAccessor().getProjects());
//        carousel.getItems().addAll(
//
//        );

//        System.out.println(carousel.getItems().get(0).getUserData());
    }

    @FXML
    private void openProject(){

    }
}
