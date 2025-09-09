package io.github.gleidsonmt.dashboardfx.drawer;

import io.github.gleidsonmt.glad.drawer.DrawerItem;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Node;

/**
 * @author Gleidson Neves da Silveira | gleidisonmt@gmail.com
 * Create on  08/09/2025
 */
public interface DrawerI {

    DrawerI header(Node node);

    DrawerI footer(Node node);

    DrawerI modules(ObservableList<DrawerItem> modules);

    DrawerI modules(DrawerItem... modules);

    void build();
}
