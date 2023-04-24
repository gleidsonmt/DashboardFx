package io.github.gleidsonmt.dashboardfx.core.impl;

import javafx.scene.layout.StackPane;

/**
 * @author Gleidson Neves da Silveira | gleidisonmt@gmail.com
 * Version 0.0.1
 * Create on  02/04/2023
 */
public class IRoot extends StackPane {

    private final IBody body = new IBody();
    private final IWrapper wrapper = new IWrapper();

    public IRoot() {
        setId("root");
        getChildren().setAll(wrapper, body);
    }

    public IBody getBody() {
        return body;
    }
}
