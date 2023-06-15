package io.github.gleidsonmt.dashboardfx.core.controls;

import javafx.css.PseudoClass;
import javafx.geometry.HPos;
import javafx.geometry.VPos;
import javafx.scene.control.SkinBase;
import javafx.scene.layout.TilePane;

/**
 * @author Gleidson Neves da Silveira | gleidisonmt@gmail.com
 * Create on  12/06/2023
 */
public class RatingSkin extends SkinBase<Rating> {

    private final TilePane body;
    
    protected RatingSkin(Rating control) {
        super(control);
        
        body = createTitledPane();
        
        getChildren().add(body);
        body.getStyleClass().add("box");
        createStars(control.getNumberOfStars());

        registerChangeListener(control.numberOfStarsProperty(), c -> {
            if (c.getValue() != null) {
                body.getChildren().clear();
                createStars((int) c.getValue());
            }
        });

        registerChangeListener(control.rangeProperty(), c -> {
            if (c.getValue() != null) {

                int value = (int) c.getValue();

                if (value > 0) {
                    if (value <= control.getNumberOfStars() ) {
                        reset(false);
                        for (int i = (int) c.getValue() - 1; i > -1; i--) {
                            body.getChildren().get(i).pseudoClassStateChanged(PseudoClass.getPseudoClass("activate"), true);
                        }
                    } else {
                        reset(true);
                    }
                }
            }
        });

    }

    private void reset(boolean state) {
        body.getChildren()
                .stream()
                .map(m -> (Star)m)
                .forEach(e ->
                        e.pseudoClassStateChanged(PseudoClass.getPseudoClass("activate"), state));
    }

    @Override
    protected void layoutChildren(double x, double y, double w, double h) {
        super.layoutChildren(x, y, w, h);
        layoutInArea(body, x,y,w, h, -1, HPos.RIGHT, VPos.BOTTOM);
    }
    
    private TilePane createTitledPane() {
        return new TilePane();
    }
    
    private void createStars(double num) {

        for (int i = 0; i < num; i++) {
            Star star = new Star();
            star.setOnMouseClicked(event -> {
                body.getChildren()
                        .stream()
                        .map(mapped -> (Star) mapped)
                        .forEach(s -> {

                            int index = body.getChildren().indexOf(s);
                            int ac = body.getChildren().indexOf(star);

                            s.pseudoClassStateChanged(PseudoClass.getPseudoClass("activate"), index <= ac);

                        });
            });
            body.getChildren().add(i, star);
        }
    }
}
