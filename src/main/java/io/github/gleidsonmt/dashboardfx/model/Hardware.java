package io.github.gleidsonmt.dashboardfx.model;

import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.scene.image.Image;

import java.math.BigDecimal;

/**
 * @author Gleidson Neves da Silveira | gleidisonmt@gmail.com
 * Create on  14/03/2025
 */
public class Hardware extends Model {

    private final ObjectProperty<Image> avatar = new SimpleObjectProperty<>();
    private final StringProperty name = new SimpleStringProperty();
    private final ObjectProperty<BigDecimal> value = new SimpleObjectProperty<>();

    public Hardware(Image image, String name, BigDecimal value) {
        this.avatar.set(image);
        this.name.set(name);
        this.value.set(value);
    }

    public Image getAvatar() {
        return avatar.get();
    }

    public void setAvatar(Image avatar) {
        this.avatar.set(avatar);
    }

    public String getName() {
        return name.get();
    }

    public void setName(String name) {
        this.name.set(name);
    }

    public BigDecimal getValue() {
        return value.get();
    }

    public void setValue(BigDecimal value) {
        this.value.set(value);
    }
}
