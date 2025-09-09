package io.github.gleidsonmt.dashboardfx.drawer;

import io.github.gleidsonmt.glad.drawer.DrawerItem;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Node;
import javafx.scene.control.ListView;
import javafx.scene.layout.VBox;

/**
 * @author Gleidson Neves da Silveira | gleidisonmt@gmail.com
 * Create on  08/09/2025
 */
public class DrawerImpl implements DrawerI {

    private Node header;
    private Node footer;
    private ObservableList<DrawerItem> modules;
    private VBox body;
    private ListView<DrawerItem> drawerItems;

    public DrawerImpl() {

    }

    @Override
    public DrawerI header(Node node) {
        this.header = node;
        return this;
    }

    @Override
    public DrawerI footer(Node node) {
        this.footer = node;
        return this;
    }

    @Override
    public DrawerI modules(ObservableList<DrawerItem> modules) {
        this.modules = modules;
        return this;
    }

    @Override
    public DrawerI modules(DrawerItem... modules) {
        this.modules = FXCollections.observableArrayList(modules);
        return this;
    }

    @Override
    public void build() {
        body = new VBox();
        body.getChildren().setAll(header, drawerItems, footer);
    }


}
