package io.github.gleidsonmt.dashboardfx.presentation.presentations.controls;

import io.github.gleidsonmt.dashboardfx.presentation.CustomizablePresentation;
import io.github.gleidsonmt.dashboardfx.presentation.internal.Tutorial;
import io.github.gleidsonmt.dashboardfx.utils.TutorialUtils;
import io.github.gleidsonmt.glad.Resources;
import io.github.gleidsonmt.glad.base.Anchor;
import io.github.gleidsonmt.glad.theme.Css;
import io.github.gleidsonmt.presentation.Presentation;
import javafx.beans.binding.Bindings;
import javafx.beans.binding.DoubleBinding;
import javafx.beans.property.DoubleProperty;
import javafx.geometry.Orientation;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollBar;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;

import java.util.concurrent.Callable;

public class ScrollBarPres extends CustomizablePresentation {

    public ScrollBarPres() {
        super("ScrollBar");
    }

    public Presentation create() {
        return new Tutorial()
                .h3("ScrollBar")
                .text("""
                        The ScrollBar control lets users select a numeric value by dragging a thumb along a horizontal or vertical track.
                        It is useful when you need direct control over a value, or when you are building a custom scrollable component.""")
                .legend("javafx.scene.control.ScrollBar")
                .demo(createDemo())
                .h4("Install")
                .code(TutorialUtils.installExample(Css.SCROLLBAR) + """ 
                        
                               ScrollBar scrollBarV = new ScrollBar();
                        
                               scrollBarV.setMin(0);
                               scrollBarV.setMax(100);
                               scrollBarV.setVisibleAmount(40);
                               scrollBarV.setOrientation(Orientation.VERTICAL);
                               scrollBarV.setPrefHeight(160);
                        
                               ScrollBar scrollBarH = new ScrollBar();
                               scrollBarH.setMin(0);
                               scrollBarH.setMax(100);
                               scrollBarH.setVisibleAmount(40);
                               scrollBarH.setOrientation(Orientation.HORIZONTAL);
                               scrollBarH.setPrefWidth(240);
                        
                               Label labelV = new Label("Amount: 0.0 ");
                               Label labelH = new Label("Amount: 0.0 ");
                               // Read the scrollbar value and show it in a label.
                               scrollBarH.valueProperty().addListener((observable, oldValue, newValue) -> {
                                   labelV.setText("Amount: " + String.format("%.1f", newValue));
                               });
                        
                               scrollBarV.valueProperty().addListener((observable, oldValue, newValue) -> {
                                   labelH.setText("Amount: " + String.format("%.1f", newValue));
                               });
                        
                               VBox root = new VBox(10, scrollBarV, scrollBarH, labelV, labelH);
                               // Add the scrollbar stylesheet to the demo container.
                               root.getStylesheets().add(Resources.getCss("scroll-bar.css"));
                      
                        """);
    }


    private Node createDemo() {
        ScrollBar scrollBarV = new ScrollBar();
        scrollBarV.setMin(0);
        scrollBarV.setMax(100);
        scrollBarV.setVisibleAmount(40);
        scrollBarV.setOrientation(Orientation.VERTICAL);
        scrollBarV.setPrefHeight(160);

        ScrollBar scrollBarH = new ScrollBar();
        scrollBarH.setMin(0);
        scrollBarH.setMax(100);
        scrollBarH.setVisibleAmount(40);
        scrollBarH.setOrientation(Orientation.HORIZONTAL);
        scrollBarH.setPrefWidth(240);

        Label labelV = new Label("Amount: 0.0 ");
        Label labelH = new Label("Amount: 0.0 ");

        scrollBarH.valueProperty().addListener((observable, oldValue, newValue) -> {
            labelV.setText("Y: " + String.format("%.1f", newValue));
        });

        scrollBarV.valueProperty().addListener((observable, oldValue, newValue) -> {
            labelH.setText("X: " + String.format("%.1f", newValue));
        });

        VBox root = new VBox(10, scrollBarV, scrollBarH, labelV, labelH);
        root.getStylesheets().add(Resources.getCss("scroll-bar.css"));

        return root;
    }
}
