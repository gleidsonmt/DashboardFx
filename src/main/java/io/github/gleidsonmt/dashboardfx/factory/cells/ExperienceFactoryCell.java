package io.github.gleidsonmt.dashboardfx.factory.cells;

import io.github.gleidsonmt.dashboardfx.core.controls.Rating;
import io.github.gleidsonmt.dashboardfx.model.Entity;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.util.Callback;

/**
 * @author Gleidson Neves da Silveira | gleidisonmt@gmail.com
 * Create on  20/09/2023
 */
public class ExperienceFactoryCell<T extends Entity> implements Callback<TableColumn<T, Experience>, TableCell<T, Experience>> {
    @Override
    public TableCell<T, Experience> call(TableColumn<T, Experience> param) {
        return new TableCell<>() {
            @Override
            protected void updateItem(Experience item, boolean empty) {
                super.updateItem(item, empty);
                if(item != null) {
                    VBox box = new VBox();
                    Text title = new Text(item.toString());
//                    Rating rating = new Rating(Experience.values().length);
                    Rating rating = new Rating(Experience.values().length);
                    rating.setMouseTransparent(true);
//                    rating.setEditable(false);
                    rating.setRange(item.getRange());
//                    rating.setStyle("-gn-range:" + item.getRange() + ";");

//                    rating.setNumberOfStars(item.getRange());

                    box.getChildren().setAll(title, rating);
//                    title.setT
                    setItem(item);
                    setGraphic(box);
                } else {
                    setItem(null);
                    setText(null);
                    setGraphic(null);
                }
            }
        };
    }
}
