package io.github.gleidsonmt.dashboardfx.core.view.layout;

import io.github.gleidsonmt.dashboardfx.core.interfaces.ActionView;
import io.github.gleidsonmt.dashboardfx.core.view.IView;
import io.github.gleidsonmt.dashboardfx.core.view.ViewComposer;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;

import java.io.IOException;
import java.net.URL;
import java.nio.charset.Charset;

/**
 * @author Gleidson Neves da Silveira | gleidisonmt@gmail.com
 * Version 0.0.1
 * Create on  02/04/2023
 */
public class SimpleView implements IView {

    private final String name;
    private final Parent container;
    private ActionView controller;

    public SimpleView(String name, Node node) {
        this.name = name;
        container = new Container(node);
    }

    public SimpleView(String name, String location) {
        this.name = name;
        FXMLLoader loader = new FXMLLoader();
        try {
            loader.setLocation(getClass().getResource(location));
            loader.load();

            controller = loader.getController();
            container = loader.getRoot();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public ViewComposer getComposer() throws NullPointerException {
        return null;
    }

    @Override
    public Parent getRoot() {
        return container;
    }

    @Override
    public Charset getCharset() {
        return null;
    }

    @Override
    public URL getLocation() {
        return null;
    }

    @Override
    public ActionView getController() {
        return controller;
    }
}
