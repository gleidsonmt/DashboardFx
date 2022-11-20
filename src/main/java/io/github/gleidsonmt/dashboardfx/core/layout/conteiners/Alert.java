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

package io.github.gleidsonmt.dashboardfx.core.layout.conteiners;

import io.github.gleidsonmt.dashboardfx.core.layout.Wrapper;
import javafx.geometry.Pos;
import javafx.scene.layout.Region;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;

public class Alert extends Dialog implements IAlert {

    private String title;
    private String text;
    private Wrapper wrapper;
    private StackPane container;
    private VBox body;
    private AlertType alertType = AlertType.WARNING;

    public Alert(Wrapper wrapper) {
        super(wrapper);

        this.container = new StackPane();
        this.body = new VBox();
        this.body.setAlignment(Pos.TOP_CENTER);

        this.container.setPrefSize(350, 300);
        this.container.setMaxWidth(Region.USE_PREF_SIZE);
        this.container.setMaxHeight(Region.USE_PREF_SIZE);
        container.getStyleClass().add("alert-container");

        body.getStyleClass().add("alert-body");


        this.container.getChildren().setAll(body);
    }

    @Override
    public Alert title(String _title) {
        this.title = _title;
        return this;
    }

    @Override
    public Alert text(String _text) {
        this.text = _text;
        return this;
    }

    @Override
    public void show() {
        if (text == null || text.isEmpty()) {
            try {
                throw new Exception("Error : Alert needs a text information.");
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        if (title == null || title.isEmpty()) {
            title = alertType.name();
        }


        super.show();
    }

}
