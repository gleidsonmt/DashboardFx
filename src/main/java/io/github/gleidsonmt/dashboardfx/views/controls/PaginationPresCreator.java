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

package io.github.gleidsonmt.dashboardfx.views.controls;

import io.github.gleidsonmt.dashboardfx.core.Context;
import io.github.gleidsonmt.dashboardfx.core.view.layout.creators.TutorialCreator;
import javafx.scene.Node;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.Pagination;
import javafx.util.Callback;

import java.util.List;

/**
 * @author Gleidson Neves da Silveira | gleidisonmt@gmail.com
 * Create on  27/06/2023
 */
public class PaginationPresCreator extends TutorialCreator {
    public PaginationPresCreator(Context context) {
        super(context);

        this
                .title("Intro")
                .text("A Pagination control is used for navigation between pages of a single content, which has been divided into smaller parts.")
                .demonstration(List.of(
                        createPagination(""),
                        createPagination("pag-round")
                ), """
                        // Constructor
                        // first page count, second act index
                        Pagination pagination = new Pagination(10, 0);
                        """)
                .title("Links")
                .footer(createDefaultAuthor("Pagination"))
                .build();
    }

    private Node createPagination(String clas) {
        Pagination pagination = new Pagination(5, 0);
        pagination.setPageFactory(pageIndex ->
                new Label(pageIndex + 1 + ". Lorem ipsum dolor sit amet,\n"
                        + "consectetur adipiscing elit,\n"
                        + "sed do eiusmod tempor incididunt ut\n"
                        + "labore et dolore magna aliqua.")
        );
        pagination.getStyleClass().addAll(clas);
        return pagination;

    }
}
