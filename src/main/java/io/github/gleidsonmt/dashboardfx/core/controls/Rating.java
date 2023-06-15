package io.github.gleidsonmt.dashboardfx.core.controls;

import javafx.beans.DefaultProperty;
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
            new SimpleStyleableIntegerProperty(RANGE, this, "range", 0);

    public Rating() {
        this(5);
    }

    public Rating(int num) {
        this(num, 0);
    }

    public Rating(int num, int range) {
        setNumberOfStars(num);
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

    public StyleableIntegerProperty rangeProperty() {
        return range;
    }

    public void setRange(int range) {
        this.range.set(range);
    }

}
