package io.github.gleidsonmt.dashboardfx.core.datatable;

import io.github.gleidsonmt.dashboardfx.model.Item;
import javafx.event.EventHandler;
import javafx.scene.control.TableRow;
import javafx.scene.control.TableView;
import javafx.scene.input.MouseEvent;
import javafx.util.Callback;



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
                } else {
                    setItem(null);
                }
            }
        };

    }
}
