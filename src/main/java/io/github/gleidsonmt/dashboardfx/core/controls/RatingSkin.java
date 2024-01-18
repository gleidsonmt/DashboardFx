package io.github.gleidsonmt.dashboardfx.core.controls;

import javafx.beans.binding.Bindings;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
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
    private final ObservableList<Star> stars;
    private final IntegerProperty selectedStars = new SimpleIntegerProperty();

    protected RatingSkin(Rating control) {
        super(control);
        stars = FXCollections.observableArrayList();

        body = createTitledPane();

        getChildren().add(body);
        body.getStyleClass().add("box");

        createStars(control.getNumberOfStars());

        setRange(Math.min(control.getNumberOfStars(), control.getRange()));

        if (control.isEditable()) {
            setOnMouseClicked();
        }

        registerChangeListener(control.numberOfStarsProperty(), c -> {
            if (c.getValue() != null) {
                body.getChildren().clear();
                createStars((int) c.getValue());
                setOnMouseClicked();
            }
        });

        control.rangeProperty().bind(selectedStars);

//        registerChangeListener(control.rangeProperty(), c -> {
//            if (c.getValue() != null) {
//
//                int value = (int) c.getValue();
//
//                if (value > 0) {
//                    if (value <= control.getNumberOfStars() ) {
//                        reset(false);
//                        var count = 0;
//                        for (int i = (int) c.getValue() - 1; i > -1; i--) {
//                            body.getChildren().get(i).pseudoClassStateChanged(PseudoClass.getPseudoClass("activate"), true);
//                            count++;
//                        }
//                        System.out.println("count = " + count);
//                    } else {
//                        reset(true);
//                    }
//                }
//            }
//        });




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

    private void setRange(int num) {
        for (int i = 0; i < num; i++) {
            stars.get(i).pseudoClassStateChanged(PseudoClass.getPseudoClass("activate"), true);
            selectedStars.set(i);
        }
    }

    private void createStars(double num) {
        for (int i = 0; i < num; i++) {
            stars.add(new Star());
        }
        body.getChildren().setAll(stars);
    }

    private void setOnMouseClicked() {

        stars.forEach(c ->  {
            c.setOnMouseClicked(e -> {
                body.getChildren()
                        .stream()
                        .map(mapped -> (Star) mapped)
                        .forEach(s -> {

                            int index = body.getChildren().indexOf(s);
                            int ac = body.getChildren().indexOf(c);

                            s.pseudoClassStateChanged(PseudoClass.getPseudoClass("activate"), index <= ac);

                            selectedStars.set(ac + 1);
                        });

            });
        });
    }
}
