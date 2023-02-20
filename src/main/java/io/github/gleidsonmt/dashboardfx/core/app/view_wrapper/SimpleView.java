package io.github.gleidsonmt.dashboardfx.core.app.view_wrapper;

import io.github.gleidsonmt.dashboardfx.core.app.interfaces.View;
import io.github.gleidsonmt.dashboardfx.core.app.services.ViewComposer;
import javafx.scene.Node;
import javafx.scene.Parent;

import java.net.URL;
import java.nio.charset.Charset;

/**
 * @author Gleidson Neves da Silveira | gleidisonmt@gmail.com
 * Create on  20/02/2023
 */
public class SimpleView implements View {

    private String name;
    private Parent node;

    public SimpleView(Node node, String name) {
        this.name = name;
        this.node = (Parent) node;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public ViewComposer getComposer() throws NullPointerException {
        ViewComposer viewComposer = new ViewComposer();
        viewComposer.setName(name);
        viewComposer.setTitle(
                name.substring(0,1).toUpperCase() +
                        name.substring(1)

        );
        return viewComposer;
    }

    @Override
    public ActionView getController() {
        return null;
    }

    @Override
    public Parent getRoot() {
        return this.node;
    }

    @Override
    public Charset getCharset() {
        return null;
    }

    @Override
    public URL getLocation() {
        return null;
    }
}
