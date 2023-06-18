package io.github.gleidsonmt.dashboardfx.core.view.layout;

import io.github.gleidsonmt.dashboardfx.core.Context;
import io.github.gleidsonmt.dashboardfx.core.interfaces.ActionView;
import io.github.gleidsonmt.dashboardfx.core.interfaces.NestedWrapperContainer;
import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.event.EventHandler;
import javafx.geometry.HPos;
import javafx.geometry.Pos;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.StackPane;
import javafx.util.Duration;

/**
 * @author Gleidson Neves da Silveira | gleidisonmt@gmail.com
 * Create on  02/02/2023
 */
public class Drawer extends ActionView implements NestedWrapperContainer {

    private HPos side = HPos.LEFT;

    private StackPane content;
    private final Timeline timeline = new Timeline();

    protected Wrapper wrapper;

    private final double maxSize = 250;
    protected Context context;

    private final EventHandler<MouseEvent> closeEvent = event -> {
        if (event.getTarget() == wrapper) hide();
    };

    public Drawer(Wrapper wrapper) {
        this.wrapper = wrapper;
    }

    @Override
    public void onInit(Context context) {
        this.wrapper = context.wrapper();
        this.context = context;
    }

    @Override
    public Drawer side(HPos _side) {
        this.side = _side;
        return this;
    }

    public Drawer content(StackPane _content) {
        this.content = _content;
        _content.setMaxWidth(maxSize);
        return this;
    }


    public void show() {
//        setOnMouseClicked(event -> {
//            if (event.getTarget() == this)
//                close();
//        });
        wrapper.setAlignment(
                side == HPos.LEFT ?
                        Pos.CENTER_LEFT : Pos.CENTER_RIGHT
        );

        final double tx = side == HPos.LEFT ?
                -content.getMaxWidth() :
                250;


        if (!this.wrapper.getChildren().contains(content))
            this.wrapper.getChildren().setAll(content);

        timeline.getKeyFrames().setAll(
                new KeyFrame(Duration.ZERO, new KeyValue(
                    content.translateXProperty(), tx
                )),
                new KeyFrame(Duration.millis(100), new KeyValue(
                        content.translateXProperty(), 0
                ))
        );

        this.timeline.setOnFinished(null);

        this.wrapper.setOnMouseClicked(closeEvent);

        this.wrapper.toFront();
        timeline.play();
    }

    public void hide() {


        final double tx = side == HPos.LEFT ?
                -content.getMaxWidth() :
                250;

        timeline.getKeyFrames().setAll(
                new KeyFrame(Duration.ZERO, new KeyValue(
                        content.translateXProperty(), 0
                )),
                new KeyFrame(Duration.millis(200), new KeyValue(
                        content.translateXProperty(), tx
                ))
        );

        timeline.setOnFinished(event -> {
            wrapper.getChildren().clear();
            content.setTranslateX(0);
            wrapper.toBack();
        });

        timeline.play();
    }
}
