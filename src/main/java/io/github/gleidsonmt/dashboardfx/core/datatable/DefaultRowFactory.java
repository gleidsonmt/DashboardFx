package io.github.gleidsonmt.dashboardfx.core.datatable;

import io.github.gleidsonmt.dashboardfx.model.Item;
import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.event.EventHandler;
import javafx.scene.control.TableRow;
import javafx.scene.control.TableView;
import javafx.scene.input.MouseEvent;
import javafx.util.Callback;
import javafx.util.Duration;


/**
 * @author Gleidson Neves da Silveira | gleidisonmt@gmail.com
 * Create on  19/09/2023
 */
public class DefaultRowFactory<T extends Item> implements Callback<TableView<T>, TableRow<T>> {

    @Override
    public TableRow<T> call(TableView<T> param) {
        return new TableRow<>() {
            @Override
            protected void updateItem(T item, boolean empty) {
                super.updateItem(item, empty);

                if (item != null && !empty) {
                    setItem(item);
                    item.checkedProperty().bind(selectedProperty());
                    this.setOpacity(0);
                    Timeline timeline = new Timeline();
                    timeline.getKeyFrames().setAll(
                            new KeyFrame(Duration.ZERO, new KeyValue(
                                    this.opacityProperty(), 0
                            )),
                            new KeyFrame(Duration.millis(400), new KeyValue(
                                    this.opacityProperty(), 1
                            ))
                    );
                    timeline.play();
                } else {
                    setItem(null);
                }
            }
        };

    }
}
