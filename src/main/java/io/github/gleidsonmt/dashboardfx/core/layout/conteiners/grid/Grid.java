package io.github.gleidsonmt.dashboardfx.core.layout.conteiners.grid;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.scene.layout.GridPane;

/**
 * @author Gleidson Neves da Silveira | gleidisonmt@gmail.com
 * Create on  02/02/2023
 */
public class Grid {

    private GridPane gridPane;

    public static void add(GridPane gridPane) {
        gridPane.widthProperty().addListener((observable, oldValue, newValue) -> {
            System.out.println("newValue = " + newValue);
        });
    }

    public void grid(GridPane gri) {
        this.gridPane = gri;
    }

    public void on(String size, int rows, int cols) {

    }

    public void colsOn(String size, int cols) {

    }

    public void register() {

    }

    // context.grid
    //      .rowsOn("middle", 2)

}
