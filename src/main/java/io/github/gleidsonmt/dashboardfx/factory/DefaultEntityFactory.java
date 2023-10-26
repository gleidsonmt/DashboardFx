package io.github.gleidsonmt.dashboardfx.factory;

import io.github.gleidsonmt.dashboardfx.model.Entity;
import javafx.beans.property.ReadOnlyObjectWrapper;
import javafx.beans.value.ObservableValue;
import javafx.scene.control.TableColumn;
import javafx.util.Callback;

/**
 * @author Gleidson Neves da Silveira | gleidisonmt@gmail.com
 * Create on  18/09/2023
 */
public class DefaultEntityFactory <E extends Entity> implements Callback<TableColumn.CellDataFeatures<E, E>, ObservableValue<E>> {
    @Override
    public ObservableValue<E> call(TableColumn.CellDataFeatures<E, E> param) {
        return new ReadOnlyObjectWrapper<>(param.getValue());
    }
}