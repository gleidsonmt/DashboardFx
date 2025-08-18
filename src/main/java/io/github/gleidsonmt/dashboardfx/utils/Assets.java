package io.github.gleidsonmt.dashboardfx.utils;

import io.github.gleidsonmt.dashboardfx.App;
import javafx.scene.image.Image;

import java.util.Objects;

/**
 * @author Gleidson Neves da Silveira | gleidisonmt@gmail.com
 * Create on  19/02/2025
 */
public class Assets {

    public static String getCss(String name) {
        return Objects.requireNonNull(App.class.getResource("css/" + name)).toExternalForm();
    }

    public static String getFont(String name) {
        return Objects.requireNonNull(App.class.getResource("fonts/" + name)).toExternalForm();
    }

//    public static String getImage(String name) {
//        return Objects.requireNonNull(App.class.getResource("img/" + name)).toExternalForm();
//    }

    public static Image getImage(String name) {
        return new Image(Objects.requireNonNull(App.class.getResource("img/" + name)).toExternalForm());
    }

    public static Image getImage(String name, int size) {
        return new Image(Objects.requireNonNull(App.class.getResource("img/" + name)).toExternalForm(), size, size, true, true);
    }


}
