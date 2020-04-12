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

import com.gn.GNAvatarView;
import javafx.geometry.HPos;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import org.controlsfx.control.spreadsheet.Grid;


/**
 * @author Gleidson Neves da Silveira | gleidisonmt@gmail.com
 * Create on  21/11/2018
 * Version 1.0
 */
public class BadgeCellMessage extends GridPane {

    private VBox content = new VBox();

    private Label title = new Label("title");
    private Label time = new Label("5 hours ago");
    private Label message = new Label("message");
    private GNAvatarView avatarView = new GNAvatarView();

    private ProgressBar progressBar = new ProgressBar();

    public BadgeCellMessage(String title, String message, String time, Image image){
        this.title.setText(title);
        this.message.setText(message);
        this.time.setText(time);
        this.avatarView.setImage(image);
        config();
    }

    private void config(){
        this.getStyleClass().add("badge-cell");

        this.avatarView.setMaxWidth(40D);
        this.time.setStyle("-fx-font-size : 8pt;");

        this.add(avatarView, 0,0);
        this.add(title, 1,0 );
        this.add(time, 2,0);
        this.add(message, 1,1);

        GridPane.setRowSpan(avatarView, GridPane.REMAINING);
        GridPane.setHalignment(time, HPos.RIGHT);

        this.setHgap(2D);

    }
}
