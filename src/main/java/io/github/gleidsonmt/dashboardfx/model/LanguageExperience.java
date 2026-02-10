package io.github.gleidsonmt.dashboardfx.model;

import javafx.beans.property.*;
import javafx.scene.image.Image;

/**
 * @author Gleidson Neves da Silveira | gleidisonmt@gmail.com
 * Create on  17/03/2025
 */
@SuppressWarnings("unused")
public class LanguageExperience extends Model {

    private final StringProperty title = new SimpleStringProperty();
    private final StringProperty legend = new SimpleStringProperty();
    private final DoubleProperty percentage = new SimpleDoubleProperty();
    private final StringProperty color = new SimpleStringProperty();

    public LanguageExperience( String title, String legend, double percentage, String color) {
        this.title.set(title);
        this.legend.set(legend);
        this.setPercentage(percentage);
        this.setColor(color);
    }

    public String getLegend() {
        return legend.get();
    }

    public StringProperty legendProperty() {
        return legend;
    }

    public void setLegend(String legend) {
        this.legend.set(legend);
    }

    public String getColor() {
        return color.get();
    }

    public StringProperty colorProperty() {
        return color;
    }

    public void setColor(String color) {
        this.color.set(color);
    }

    public String getTitle() {
        return title.get();
    }

    public StringProperty titleProperty() {
        return title;
    }

    public void setTitle(String title) {
        this.title.set(title);
    }

    public double getPercentage() {
        return percentage.get();
    }

    public DoubleProperty percentageProperty() {
        return percentage;
    }

    public void setPercentage(double percentage) {
        this.percentage.set(percentage);
    }
}
