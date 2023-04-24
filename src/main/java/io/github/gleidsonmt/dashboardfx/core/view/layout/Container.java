package io.github.gleidsonmt.dashboardfx.core.view.layout;

import javafx.scene.Node;
import javafx.scene.layout.StackPane;

/**
 * @author Gleidson Neves da Silveira | gleidisonmt@gmail.com
 * Version 0.0.1
 * Create on  02/04/2023
 */
public class Container extends StackPane {

//    public Container() {
//        this(null);
//    }

    public Container(Node node) {
        this.getChildren().add(node);
    }

}
