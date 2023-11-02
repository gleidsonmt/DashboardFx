package io.github.gleidsonmt.dashboardfx.factory.cells;

import animatefx.animation.AnimationFX;
import animatefx.animation.Jello;
import animatefx.animation.Pulse;
import io.github.gleidsonmt.dashboardfx.core.Context;
import io.github.gleidsonmt.dashboardfx.core.controls.Rating;
import io.github.gleidsonmt.dashboardfx.core.controls.icon.IconContainer;
import io.github.gleidsonmt.dashboardfx.core.controls.icon.Icons;
import io.github.gleidsonmt.dashboardfx.core.view.layout.DialogContainer;
import io.github.gleidsonmt.dashboardfx.factory.ListOptions;
import io.github.gleidsonmt.dashboardfx.factory.Option;
import io.github.gleidsonmt.dashboardfx.model.Entity;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.util.Callback;

/**
 * @author Gleidson Neves da Silveira | gleidisonmt@gmail.com
 * Create on  20/09/2023
 */
public class OptionsCellFactory<T extends Entity> implements Callback<TableColumn<T, T>, TableCell<T, T>> {

    private Context context;

    public OptionsCellFactory(Context context) {
        this.context = context;
    }

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

                    button.setOnMouseEntered(event -> {
                        AnimationFX animation = new Pulse(this);
                        animation.play();
                    });

                    ListOptions listOptions = new ListOptions(context)
                            .items(
                                    new Option("Edit", Icons.EDIT, event -> {}),
                                    new Option("View", Icons.ANALYTICS, event -> {}),
                                    new Option("Delete", Icons.DELETE, event -> {})
                            );

                    button.setOnAction(event -> {
                        context.flow()
                                .content(
                                        new DialogContainer(listOptions.build())
                                                .size(180, 140)
                                )
                                .show(Pos.CENTER_LEFT, button, 0, 80);
                    });

                } else {
                    setGraphic(null);
                    setItem(null);
                    setText(null);
                }
            }
        };
    }
}
