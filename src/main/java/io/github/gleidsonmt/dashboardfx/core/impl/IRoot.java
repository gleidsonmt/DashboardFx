package io.github.gleidsonmt.dashboardfx.core.impl;

import io.github.gleidsonmt.dashboardfx.core.interfaces.Root;
import io.github.gleidsonmt.dashboardfx.core.view.layout.Wrapper;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.layout.StackPane;

/**
 * @author Gleidson Neves da Silveira | gleidisonmt@gmail.com
 * Version 0.0.1
 * Create on  02/04/2023
 */
public class IRoot extends StackPane implements Root {

    private final IBody body = new IBody();
//    private final IWrapper wrapper = new IWrapper();
    private final Wrapper wrapper;

    public IRoot() {
        setId("root");
        wrapper = new Wrapper();
        getChildren().setAll(wrapper, body);
        setAlignment(Pos.TOP_LEFT);
    }

    public IBody getBody() {
        return body;
    }

    @Deprecated(forRemoval = true)
    @Override
    public void setTitle(String title) {

    }

    public Wrapper getWrapper() {
        return wrapper;
    }
}
