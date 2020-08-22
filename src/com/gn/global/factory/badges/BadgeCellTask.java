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
package com.gn.global.factory.badges;

import javafx.geometry.HPos;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.scene.layout.GridPane;


/**
 * @author Gleidson Neves da Silveira | gleidisonmt@gmail.com
 * Create on  21/11/2018
 * Version 1.0
 */
public class BadgeCellTask extends GridPane {

    private final Label title = new Label("title");
    private final Label percent = new Label("80%");
    private final ProgressBar progressBar = new ProgressBar();

    public BadgeCellTask(String title, double percent, String styleClass){
        this.percent.setText(percent + "%");
        this.progressBar.setProgress( percent > 1 ? percent / 100 : percent );
        this.title.setText(title);
        progressBar.getStyleClass().add(styleClass);
        config();
    }

    private void config(){
        this.getStyleClass().add("badge-cell");

        this.add(this.title, 0,0);
        this.add(this.percent, 1 , 0);
        this.add(this.progressBar, 0,1);

        GridPane.setHalignment( this.percent, HPos.RIGHT );
        GridPane.setColumnSpan( this.progressBar, GridPane.REMAINING );

        this.setVgap(15D);

        this.progressBar.setPrefWidth(250);
    }
}
