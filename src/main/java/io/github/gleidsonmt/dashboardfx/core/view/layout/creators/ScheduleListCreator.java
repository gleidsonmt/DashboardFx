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

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.VPos;
import javafx.scene.Node;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Priority;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.shape.SVGPath;

/**
 * Precisa refatorar
 */
public class ScheduleListCreator
        implements BuildCreator
{
    private StackPane root;
    private GridPane header;
    private ScrollPane scroll;
    private VBox container;
    private VBox body;
    private Hyperlink seeMore;

    private String title;
    private String seeMoreTitle = "See More";
    private ObservableList<ScheduleListItem> items;
    private EventHandler<MouseEvent> onSeeMore;

    public ScheduleListCreator() {
    }

    public ScheduleListCreator title(String _title) {
        this.title = _title;
        return this;
    }

    public ScheduleListCreator seeMore(String _title, EventHandler<MouseEvent> onClick) {
        this.seeMoreTitle = _title;
        this.onSeeMore = onClick;
        return this;
    }

    public ScheduleListCreator seeMore(String _title) {
        this.seeMoreTitle = _title;
        return this;
    }

    public ScheduleListCreator items(ScheduleListItem... items) {
        this.items = FXCollections.observableArrayList(items);
        return this;
    }

    @Override
    public ScheduleListCreator build() {
        construct();
        return this;
    }

    @Override
    public StackPane getRoot() {
        return root;
    }

    private void construct() {

        this.header = new GridPane();
        this.root = new StackPane();

        Label title = new Label(this.title);
        header.getChildren().add(title);
        title.setStyle("-fx-font-size : 14pt;" +
                "-fx-font-weight : bold;");
        SVGPath path = new SVGPath();
        path.setContent("M17 22q-2.075 0-3.537-1.462Q12 19.075 12 17q0-2.075 1.463-3.538Q14.925 12 17 12t3.538 1.462Q22 14.925 22 17q0 2.075-1.462 3.538Q19.075 22 17 22Zm1.675-2.625.7-.7L17.5 16.8V14h-1v3.2ZM5 21q-.825 0-1.413-.587Q3 19.825 3 19V5q0-.825.587-1.413Q4.175 3 5 3h4.175q.275-.875 1.075-1.438Q11.05 1 12 1q1 0 1.788.562.787.563 1.062 1.438H19q.825 0 1.413.587Q21 4.175 21 5v6.25q-.45-.325-.95-.55-.5-.225-1.05-.4V5h-2v3H7V5H5v14h5.3q.175.55.4 1.05.225.5.55.95Zm7-16q.425 0 .713-.288Q13 4.425 13 4t-.287-.713Q12.425 3 12 3t-.712.287Q11 3.575 11 4t.288.712Q11.575 5 12 5Z");
        title.setGraphic(path);
        title.setGraphicTextGap(10);

        seeMore = new Hyperlink(seeMoreTitle);
        if (onSeeMore != null) seeMore.setOnMouseClicked(onSeeMore);
        header.getChildren().add(seeMore);
        GridPane.setConstraints(seeMore, 1, 0, 1,1, HPos.RIGHT, VPos.CENTER, Priority.ALWAYS, Priority.ALWAYS);
        VBox.setMargin(header, new Insets(10, 10, 10, 0));

        this.body = new VBox();
        this.container = new VBox();
        this.container.getChildren().addAll(header, body);
        this.scroll = new ScrollPane(container);
        this.root.getChildren().add(scroll);

        scroll.setFitToWidth(true);
        scroll.setFitToHeight(true);

        body.getChildren().setAll(items);
        body.setSpacing(10D);
        this.root.setStyle("-fx-border-color : -light-gray-2;");

        this.root.setPadding(new Insets(10));
    }

    private void configLayout() {

    }
}
