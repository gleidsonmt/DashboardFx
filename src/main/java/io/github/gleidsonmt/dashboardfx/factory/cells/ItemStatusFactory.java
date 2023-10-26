package io.github.gleidsonmt.dashboardfx.factory.cells;

import io.github.gleidsonmt.dashboardfx.core.Context;
import io.github.gleidsonmt.dashboardfx.model.Item;
import io.github.gleidsonmt.dashboardfx.model.Status;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.util.Callback;

/**
 * @author Gleidson Neves da Silveira | gleidisonmt@gmail.com
 * Create on  18/09/2023
 */
public class ItemStatusFactory <T extends Item> implements Callback<TableColumn<T, Status>, TableCell<T, Status>> {

    private final Context context;

    public ItemStatusFactory(Context context) {
        this.context = context;
    }

    @Override
    public TableCell<T, Status> call(TableColumn<T, Status> param) {
        return new TableCell<>() {
            @Override
            protected void updateItem(Status item, boolean empty) {
                super.updateItem(item, empty);
                if (item != null) {
                    setItem(item);
                    setGraphic(new StatusLabel(item));
                } else {
                    setItem(null);
                    setText(null);
                    setGraphic(null);
                }
            }
        };
    }
}
