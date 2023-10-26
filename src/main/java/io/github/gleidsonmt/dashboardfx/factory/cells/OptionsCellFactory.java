package io.github.gleidsonmt.dashboardfx.factory.cells;

import io.github.gleidsonmt.dashboardfx.core.controls.Rating;
import io.github.gleidsonmt.dashboardfx.core.controls.icon.IconContainer;
import io.github.gleidsonmt.dashboardfx.core.controls.icon.Icons;
import io.github.gleidsonmt.dashboardfx.model.Entity;
import javafx.scene.control.Button;
import javafx.scene.control.ContentDisplay;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.util.Callback;

/**
 * @author Gleidson Neves da Silveira | gleidisonmt@gmail.com
 * Create on  20/09/2023
 */
public class OptionsCellFactory<T extends Entity> implements Callback<TableColumn<T, T>, TableCell<T, T>> {
    @Override
    public TableCell<T, T> call(TableColumn<T, T> param) {
        return new TableCell<>() {
            @Override
            protected void updateItem(T item, boolean empty) {
                super.updateItem(item, empty);
                if (item != null) {
                    setItem(item);
                    Button button = new Button();
                    button.setGraphic(new IconContainer(Icons.MORE_HOR, true));
                    button.setContentDisplay(ContentDisplay.GRAPHIC_ONLY);
                    button.getStyleClass().add("btn-flat");
                    setGraphic(button);
                } else {
                    setGraphic(null);
                    setItem(null);
                    setText(null);
                }
            }
        };
    }
}
