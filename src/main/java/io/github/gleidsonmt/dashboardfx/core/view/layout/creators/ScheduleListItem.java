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

package io.github.gleidsonmt.dashboardfx.core.view.layout.creators;

import io.github.gleidsonmt.dashboardfx.core.controls.GNIconButton;
import io.github.gleidsonmt.dashboardfx.core.controls.icon.Icons;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.HPos;
import javafx.geometry.Pos;
import javafx.geometry.VPos;
import javafx.scene.control.ContentDisplay;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Priority;
import javafx.scene.shape.SVGPath;
import javafx.scene.text.Text;

import java.time.LocalTime;
import java.util.List;
import java.util.Random;

public class ScheduleListItem extends GridPane {

    private final List<String> colors = List.of("-info", "-mint", "-amber", "-grapefruit", "-secondary");
    private final Random random = new Random();

    private Label num;
    private Text title;
    private Text legend;
    private Text author;
    private EventHandler<ActionEvent> event;
    private Label time;

    public ScheduleListItem(int _num, String _title, String _legend, String _author, LocalTime _time) {
        this(_num, _title, _legend, _author, _time, null);
    }
    public ScheduleListItem(int _num, String _title, String _legend, String _author, LocalTime _time, EventHandler<ActionEvent> _event) {
        this.event = _event;
        this.setGridLinesVisible(true);
        this.getStyleClass().add("schedule-item");

        num = createIcon(_num);
        GridPane.setConstraints(num, 0,0,1,2, HPos.LEFT, VPos.CENTER, Priority.ALWAYS, Priority.ALWAYS);
        this.title = createTitle(_title);
        GridPane.setConstraints(this.title, 1,0,1,1, HPos.LEFT, VPos.CENTER, Priority.ALWAYS, Priority.ALWAYS);
        this.legend = new Text(_legend);
        GridPane.setConstraints(this.legend, 1,1,1,1, HPos.LEFT, VPos.CENTER, Priority.ALWAYS, Priority.ALWAYS);
//        this.author = new Text(_author);
//        GridPane.setConstraints(this.author, 1,1,1,1, HPos.CENTER, VPos.CENTER, Priority.ALWAYS, Priority.ALWAYS);
        this.time = new Label(_time.toString());
        this.time.setGraphic(createTime());
        GridPane.setConstraints(this.time, 2,0,1,2, HPos.RIGHT, VPos.CENTER, Priority.ALWAYS, Priority.ALWAYS);
        this.getChildren().setAll(num, title, legend, this.time);

        GNIconButton iconButton = new GNIconButton();
        iconButton.getStyleClass().addAll("btn-flat");
        iconButton.setIcon(Icons.CHEVRON_RIGHT);
        this.getChildren().add(iconButton);
        GridPane.setConstraints(iconButton, 3,0,1,2, HPos.RIGHT, VPos.CENTER, Priority.NEVER, Priority.NEVER);

        this.setVgap(5);
        this.setHgap(5);
    }

    private Label createIcon(int num) {
        Label lbl = new Label(String.valueOf(num));

        String color = colors.get(random.nextInt(4));
        lbl.setStyle("-fx-border-color :" + color + ";" );
        lbl.setMinSize(30, 30);
        lbl.getStyleClass().add("icon");
        lbl.setContentDisplay(ContentDisplay.CENTER);
        lbl.setAlignment(Pos.CENTER);

        return lbl;
    }

    private Text createTitle(String title) {
        Text lbl = new Text(title);

        lbl.getStyleClass().add("title");

        return lbl;
    }

    private SVGPath createTime() {
        String content = "m12.792 13.542 1.062-1.063-3.104-3.104V5h-1.5v5ZM10 18q-1.646 0-3.104-.625-1.458-.625-2.552-1.719t-1.719-2.552Q2 11.646 2 10q0-1.667.625-3.125t1.719-2.542Q5.438 3.25 6.896 2.625T10 2q1.667 0 3.125.625t2.542 1.708q1.083 1.084 1.708 2.542Q18 8.333 18 10q0 1.646-.625 3.104-.625 1.458-1.708 2.552-1.084 1.094-2.542 1.719Q11.667 18 10 18Zm0-8Zm0 6.5q2.708 0 4.604-1.906T16.5 10q0-2.708-1.896-4.604T10 3.5q-2.688 0-4.594 1.896Q3.5 7.292 3.5 10q0 2.688 1.906 4.594Q7.312 16.5 10 16.5Z";
        SVGPath path = new SVGPath();
        path.setContent(content);
        return path;
    }
}
