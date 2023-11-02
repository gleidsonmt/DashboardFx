package io.github.gleidsonmt.dashboardfx.factory.cells;

import io.github.gleidsonmt.dashboardfx.core.Context;
import io.github.gleidsonmt.dashboardfx.core.controls.GNAvatarStatus;
import io.github.gleidsonmt.dashboardfx.model.Developer;
import io.github.gleidsonmt.dashboardfx.model.Item;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.VPos;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.image.Image;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Priority;
import javafx.scene.text.Text;
import javafx.util.Callback;

import java.util.Random;

/**
 * @author Gleidson Neves da Silveira | gleidisonmt@gmail.com
 * Create on  18/09/2023
 */
public class ItemAvatarFactory<T extends Item> implements Callback<TableColumn<T, T>, TableCell<T, T>> {


    @Override
    public TableCell<T, T> call(TableColumn<T, T> param) {
        return new TableCell<>() {
            @Override
            protected void updateItem(T item, boolean empty) {
                super.updateItem(item, empty);
                if (item != null) {
                    setItem(item);
                    GNAvatarStatus status = new GNAvatarStatus(15);

                    status.setImage(new Image(item.getAvatar()));
                    GridPane grid = new GridPane();
                    grid.setHgap(10);

                    this.setPadding(new Insets(6));
                    Text name = new Text(item.getName());
                    name.setStyle("-fx-font-weight: bold;");
                    Text description = new Text();

                    if (item instanceof Developer engineer) {
                        description.setText(engineer.getEmail());
                    }


                    grid.getChildren().setAll(status, name, description );
                    GridPane.setConstraints(status, 0,0,1,2, HPos.LEFT, VPos.CENTER, Priority.SOMETIMES, Priority.SOMETIMES);
                    GridPane.setConstraints(name, 1,0,1,1, HPos.LEFT, VPos.CENTER, Priority.ALWAYS, Priority.ALWAYS);
                    GridPane.setConstraints(description, 1,1,1,1, HPos.LEFT, VPos.CENTER, Priority.ALWAYS, Priority.ALWAYS);
                    setGraphic(grid);
                } else {
                    setItem(null);
                    setText(null);
                    setGraphic(null);
                }
            }
        };
    }
}
