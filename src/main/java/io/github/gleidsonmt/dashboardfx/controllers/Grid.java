package io.github.gleidsonmt.dashboardfx.controllers;

import javafx.geometry.HPos;
import javafx.geometry.VPos;
import javafx.scene.Node;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Priority;

/**
 * @author Gleidson Neves da Silveira | gleidisonmt@gmail.com
 * Create on  03/02/2023
 */
public class Grid {

    public static void change(GridPane grid, int maxCols) {

        int cols = 0;
        int rows = 0;

        for (Node node : grid.getChildren()) {
            GridPane.setConstraints(node, cols, rows,
                    1, 1, HPos.CENTER, VPos.CENTER, Priority.ALWAYS, Priority.ALWAYS
            );

            cols++;
            if (cols == maxCols) {
                cols = 0;
                rows++;
            }

        }

    }
    public static void inLine(GridPane grid) {
        change(grid, grid.getChildren().size());
    }
}
