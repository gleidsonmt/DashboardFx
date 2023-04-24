package io.github.gleidsonmt.dashboardfx.core.impl;

import javafx.geometry.Pos;
import javafx.scene.layout.StackPane;

/**
 * @author Gleidson Neves da Silveira | gleidisonmt@gmail.com
 * Version 0.0.1
 * Create on  02/04/2023
 */
public class IBody extends StackPane {

    private Layout layout = null;

    public void setContent(Layout view) {
        this.layout = view;
        getChildren().add(0, view.getContent());
        setAlignment(Pos.CENTER);
    }

    public Layout getLayout() {
        return layout;
    }

}
