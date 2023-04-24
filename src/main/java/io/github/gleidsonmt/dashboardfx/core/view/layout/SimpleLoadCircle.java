package io.github.gleidsonmt.dashboardfx.core.view.layout;

import javafx.animation.RotateTransition;
import javafx.geometry.Pos;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.util.Duration;

/**
 * @author Gleidson Neves da Silveira | gleidisonmt@gmail.com
 * Create on  20/02/2023
 */
public class SimpleLoadCircle extends StackPane {

    private final VBox root = new VBox();
    private final StackPane body = new StackPane();
    private final Circle one = new Circle();
    private final Circle two = new Circle();

    public SimpleLoadCircle() {
        rotate(one, 360, 10);
        rotate(two, 180, 18);

        one.setRadius(50);
        two.setRadius(18);

        one.setStyle("-fx-stroke-dash-offset : 30; -fx-stroke-dash-array : 50; -fx-stroke :  derive(-aqua, 50%);");
        two.setStyle("-fx-stroke-dash-array : 30; -fx-stroke : derive(-aqua, 30%);");

        config(one, two);

        root.setAlignment(Pos.CENTER);
        this.setAlignment(Pos.CENTER);

        this.getChildren().add(root);
        root.getChildren().setAll(body);
        body.getChildren().setAll(one, two);

    }

    private void config(Circle... circles) {
        for (Circle circle : circles) {
            circle.setStrokeWidth(2);
            circle.setFill(Color.TRANSPARENT);
        }
    }

    private void rotate(Circle circle, int angle, int duration) {
        RotateTransition rotate = new RotateTransition(Duration.seconds(duration), circle);

        rotate.setAutoReverse(true);

        rotate.setByAngle(angle);
        rotate.setDelay(Duration.seconds(0));
        rotate.setRate(3);
        rotate.setCycleCount(-1);
        rotate.play();
    }


}
