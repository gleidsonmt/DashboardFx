package io.github.gleidsonmt.dashboardfx.core.impl;

import io.github.gleidsonmt.dashboardfx.core.view.View;
import javafx.geometry.Pos;
import javafx.scene.Node;
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
        getChildren().setAll( view.getContent());
        setAlignment(Pos.CENTER);
    }

    public void setContent(View view) {
        getChildren().setAll( view.getRoot());
        setAlignment(Pos.CENTER);
    }

    public void reset() {
        getChildren().setAll(layout.getContent());
    }

    public Layout getLayout() {
        return layout;
    }

}
