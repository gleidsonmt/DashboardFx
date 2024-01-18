package io.github.gleidsonmt.dashboardfx.core.controls;

import javafx.beans.DefaultProperty;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.css.*;
import javafx.scene.control.Control;
import javafx.scene.control.Skin;
import javafx.scene.paint.Color;

import java.util.List;
import java.util.Objects;

/**
 * @author Gleidson Neves da Silveira | gleidisonmt@gmail.com
 * Create on  12/06/2023
 */
@DefaultProperty("control")
public class Rating extends Control {

    private static final StyleablePropertyFactory<Rating> FACTORY =
            new StyleablePropertyFactory<>(Control.getClassCssMetaData());

    private static final CssMetaData<Rating, Number> NUMBER_OF_STARS =
            FACTORY.createSizeCssMetaData( "-gn-number-of-stars", Rating::numberOfStarsProperty,
                    1, false);

    private final StyleableIntegerProperty numberOfStars =
            new SimpleStyleableIntegerProperty(NUMBER_OF_STARS, this, "numberOfStars", 5);

    private static final CssMetaData<Rating, Number> RANGE =
            FACTORY.createSizeCssMetaData( "-gn-range", Rating::rangeProperty,
                    1, true);

    private final StyleableIntegerProperty range =
            new SimpleStyleableIntegerProperty(RANGE, this, "range", 2);

    private final BooleanProperty editable = new SimpleBooleanProperty(true);

    public Rating() {
        this(5);
    }

    public Rating(int numberOfStars) {
        this(0, numberOfStars);
    }

    public Rating(int range, int numberOfStars) {
        setNumberOfStars(numberOfStars);
        setRange(range);
        getStyleClass().add("rating");

    }

    @Override
    protected Skin<Rating> createDefaultSkin() {
        return new RatingSkin(this);
    }

    public int getNumberOfStars() {
        return numberOfStars.get();
    }

    public StyleableIntegerProperty numberOfStarsProperty() {
        return numberOfStars;
    }

    public void setNumberOfStars(int numberOfStars) {
        this.numberOfStars.set(numberOfStars);
    }

    @Override
    public List<CssMetaData<? extends Styleable, ?>> getControlCssMetaData() {
        return FACTORY.getCssMetaData();
    }

//    @Override
//    public String getUserAgentStylesheet() {
//        return Objects.requireNonNull(Star.class.getResource("/io.github.gleidsonmt.dashboardfx/controls/rating.css")).toExternalForm();
//    }

    public int getRange() {
        return range.get();
    }

    public void setRange(int range) {
        this.range.set(range);
    }

    public boolean isEditable() {
        return editable.get();
    }

    public BooleanProperty editableProperty() {
        return editable;
    }

    public void setEditable(boolean editable) {
        this.editable.set(editable);
    }

    public StyleableIntegerProperty rangeProperty() {
        return range;
    }
}
