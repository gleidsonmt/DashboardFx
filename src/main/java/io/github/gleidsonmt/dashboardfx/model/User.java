package io.github.gleidsonmt.dashboardfx.model;

import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.scene.image.Image;

/**
 * @author Gleidson Neves da Silveira | gleidisonmt@gmail.com
 * Create on  16/10/2024
 */
@SuppressWarnings("unused")
public class User extends Entity {

    private final StringProperty name = new SimpleStringProperty();
    private final StringProperty username = new SimpleStringProperty();
    private final ObjectProperty<Image> avatar = new SimpleObjectProperty<>();
    private final StringProperty password = new SimpleStringProperty();

    public User(String username, String name) {
        this.username.set(username);
        this.name.set(name);
    }

    public User(Image image, String username, String name) {
        this.username.set(username);
        this.name.set(name);
        this.avatar.set(image);
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

    @Override
    public String getName() {
        return name.get();
    }

    @Override
    public StringProperty nameProperty() {
        return name;
    }

    public void setName(String name) {
        this.name.set(name);
    }

    public String getPassword() {
        return password.get();
    }

    public StringProperty passwordProperty() {
        return password;
    }

    public void setPassword(String password) {
        this.password.set(password);
    }

    public String getUsername() {
        return username.get();
    }

    public StringProperty usernameProperty() {
        return username;
    }

    public void setUsername(String username) {
        this.username.set(username);
    }
}