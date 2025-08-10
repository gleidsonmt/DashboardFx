package io.github.gleidsonmt.dashboardfx.model;

import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.image.Image;

import java.math.BigDecimal;
import java.util.Arrays;

/**
 * @author Gleidson Neves da Silveira | gleidisonmt@gmail.com
 * Create on  14/03/2025
 */
public class Hardware extends Model {

    private ObjectProperty<Image> avatar = new SimpleObjectProperty<>();
    private StringProperty name = new SimpleStringProperty();
    private ObjectProperty<BigDecimal> value = new SimpleObjectProperty<>();

    public Hardware(Image image, String name, BigDecimal value) {
        this.avatar.set(image);
        this.name.set(name);
        this.value.set(value);

    }

    public Image getAvatar() {
        return avatar.get();
    }

    public ObjectProperty<Image> avatarProperty() {
        return avatar;
    }

    public void setAvatar(Image avatar) {
        this.avatar.set(avatar);
    }

    public String getName() {
        return name.get();
    }

    public StringProperty nameProperty() {
        return name;
    }

    public void setName(String name) {
        this.name.set(name);
    }

    public BigDecimal getValue() {
        return value.get();
    }

    public ObjectProperty<BigDecimal> valueProperty() {
        return value;
    }

    public void setValue(BigDecimal value) {
        this.value.set(value);
    }
}
