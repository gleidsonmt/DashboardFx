package io.github.gleidsonmt.dashboardfx.core.controls;

import io.github.gleidsonmt.dashboardfx.core.controls.skin.GNCarouselSkin;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.AccessibleRole;
import javafx.scene.Node;
import javafx.scene.control.Skin;

/**
 * @author Gleidson Neves da Silveira | gleidisonmt@gmail.com
 * Create on  06/06/2023
 */
public class GNCarousel<T extends Node> extends GNCarouselBase {


    private static final String DEFAULT_STYLE_CLASS = "gn-carousel";

    private ObjectProperty<ObservableList<T>> items;

    private final BooleanProperty arrows = new SimpleBooleanProperty(this, "arrows", true);

    public GNCarousel(){
        this(FXCollections.observableArrayList());
    }

    public GNCarousel(ObservableList<T> items) {
        initialize();
        setAccessibleRole(AccessibleRole.NODE);
        setItems(items);
    }

    @Override
    protected Skin<?> createDefaultSkin() {
        return new GNCarouselSkin(this);
    }
//
    private void initialize(){
        this.getStyleClass().add(DEFAULT_STYLE_CLASS);
    }


    public final ObservableList<T> getItems() {
        return items == null ? null : items.get();
    }

    public void setItems(ObservableList<T> items) {
        this.itemsProperty().set(items);
    }

    public final ObjectProperty<ObservableList<T>> itemsProperty() {
        if (items == null) {
            items = new SimpleObjectProperty<>(this, "items");
        }
        return items;
    }

    public boolean isArrows() {
        return arrows.get();
    }

    public BooleanProperty arrowsProperty() {
        return arrows;
    }

    public void setArrows(boolean arrows) {
        this.arrows.set(arrows);
    }
}
