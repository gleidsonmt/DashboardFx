package io.github.gleidsonmt.dashboardfx.views;

import io.github.gleidsonmt.dashboardfx.core.app.view_wrapper.Loader;
import javafx.animation.RotateTransition;
import javafx.beans.property.StringProperty;
import javafx.beans.value.ObservableValue;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.util.Duration;

/**
 * @author Gleidson Neves da Silveira | gleidisonmt@gmail.com
 * Create on  20/02/2023
 */
public class LoadCircle extends StackPane implements Loader {
    private VBox root = new VBox();
    private StackPane body = new StackPane();
    private final Circle one = new Circle();
    private final Circle two = new Circle();
    private final Circle three = new Circle();
    private final Label title = new Label();
    private final Label legend = new Label();

    public LoadCircle() {
        this(null, null);
    }

    public LoadCircle(String legend) {
        this(null, legend);
    }

    public LoadCircle(String _title, String _legend) {
        rotate(one, 360, 10);
        rotate(two, 180, 18);
        rotate(three, 60, 22);

        one.setRadius(120);
        two.setRadius(105);
        three.setRadius(85);

        one.setStyle("-fx-stroke-dash-offset : 30; -fx-stroke-dash-array : 200; -fx-stroke :  derive(-aqua, 50%);");
        two.setStyle("-fx-stroke-dash-array : 150; -fx-stroke : derive(-aqua, 30%);");
        three.setStyle("-fx-stroke-dash-array : 20; -fx-stroke : -aqua;");

        config(one, two, three);

        root.setAlignment(Pos.CENTER);
        this.setAlignment(Pos.CENTER);

        this.getChildren().add(root);
        root.getChildren().setAll(body);
        body.getChildren().setAll(one, two, three);

        title.setStyle("-fx-font-size : 20; -fx-text-fill : -dark-gray;");
        legend.setStyle("-fx-font-size : 14pt");

        title.textProperty().addListener(this::changed);
        legend.textProperty().addListener(this::changed1);

        if (_legend != null) legend.setText(_legend);
        if (_title != null) title.setText(_title);

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

    private void changed1(ObservableValue<? extends String> observable, String oldValue, String newValue) {
        if (newValue != null) {
            if (!root.getChildren().contains(legend))
                root.getChildren().add(legend);
        } else {
            root.getChildren().remove(legend);
        }
    }

    private void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
        if (newValue != null) {
            if (!body.getChildren().contains(title))
                body.getChildren().add(title);
        } else {
            body.getChildren().remove(title);
        }
    }

    public StringProperty legendProperty() {
        return legend.textProperty();
    }

    public StringProperty titleProperty() {
        return title.textProperty();
    }

    @Override
    public void updateTitle(String _title) {
        title.setText(_title);
    }

    @Override
    public void updateLegend(String _legend) {
        legend.setText(_legend);
    }
}
