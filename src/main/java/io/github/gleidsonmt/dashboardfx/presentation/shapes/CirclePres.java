package io.github.gleidsonmt.dashboardfx.presentation.shapes;

import io.github.gleidsonmt.dashboardfx.presentation.CustomizablePresentation;
import io.github.gleidsonmt.dashboardfx.presentation.internal.Tutorial;
import io.github.gleidsonmt.presentation.Presentation;
import javafx.scene.Node;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;

public class CirclePres extends CustomizablePresentation {

    public CirclePres() {
        super("Circle");
    }

    public Presentation create() {
        return new Tutorial()
                .indicators()
                .overview()
                .h2("Circle")
                .separator()

                .text("""
                        The Circle shape draws a round node using a radius, fill color, optional stroke, and center coordinates.
                        Use centerX and centerY to place each circle freely inside a Pane.""")

                .legend("javafx.scene.shape.Circle")
                .h3("Install")
                .code("ThemeProvider.install(root, \n\t... \n\tCss.CIRCLE);", "java")

                .separator()
                .h3("Size")
                .demo(createDemoRadius())
                .code("""
                               Circle circle = new Circle();  // Create a circle object.

                               circle.setRadius(100.0);       // Set the circle radius.
                        """)

                .separator()
                .h3("Color")
                .demo(createDemoColor())
                .code("""
                                Circle circle1 = new Circle(); // Create a circle object.
                                circle1.setRadius(30.0);       // Set the circle radius.

                                circle1.setFill(Color.RED);    // Set the fill color.
                        """)

                .separator()
                .h3("Stroke")
                .demo(createDemoStroke())
                .code("""
                                Circle circle1 = new Circle();  // Create a circle object.
                                circle1.setRadius(30.0);        // Set the circle radius.
                                circle1.setFill(Color.RED);     // Set the fill color.

                                circle1.setStroke(Color.BLACK); // Set the stroke color.
                                circle1.setStrokeWidth(8.0);    // Set the stroke thickness.
                        """)

                .separator()
                .h3("Coordinates")
                .demo(createDemoStarSky())
                .code("""
                                Pane pane = new Pane();
                                pane.setPrefSize(360, 240);

                                Circle circle1 = new Circle();
                                circle1.setCenterX(35.0);       // Place the circle on the X axis.
                                circle1.setCenterY(40.0);       // Place the circle on the Y axis.
                                circle1.setRadius(6.0);         // Use a small radius.
                                circle1.setFill(Color.GOLD);    // Use a different fill color.
                                circle1.setStroke(Color.BLACK); // Add a stroke when needed.
                                circle1.setStrokeWidth(1.5);    // Keep the stroke inside the pane bounds.

                                Circle circle2 = new Circle();
                                circle2.setCenterX(145.0);
                                circle2.setCenterY(35.0);
                                circle2.setRadius(4.0);
                                circle2.setFill(Color.LIGHTGREEN);
                                circle2.setStroke(Color.TRANSPARENT); // Use no visible stroke.
                                circle2.setStrokeWidth(0);

                                pane.getChildren().addAll(circle1, circle2);
                        """);
    }

    private Node createDemoRadius() {
        Circle circle1 = new Circle();
        circle1.setRadius(100.0);
        circle1.setFill(Color.GRAY);

        Circle circle2 = new Circle();
        circle2.setRadius(75.0);
        circle2.setFill(Color.GRAY);

        Circle circle3 = new Circle();
        circle3.setRadius(50.0);
        circle3.setFill(Color.GRAY);

        Circle circle4 = new Circle();
        circle4.setRadius(35.0);
        circle4.setFill(Color.GRAY);

        HBox hBox = new HBox(10, circle1, circle2, circle3, circle4);
        return hBox;
    }

    private Node createDemoColor() {
        Circle circle1 = new Circle();
        circle1.setRadius(30.0);
        circle1.setFill(Color.RED);

        Circle circle2 = new Circle();
        circle2.setRadius(30.0);
        circle2.setFill(Color.VIOLET);

        Circle circle3 = new Circle();
        circle3.setRadius(30.0);
        circle3.setFill(Color.BLUE);

        Circle circle4 = new Circle();
        circle4.setRadius(30.0);
        circle4.setFill(Color.BLACK);

        Circle circle5 = new Circle();
        circle5.setRadius(30.0);
        circle5.setFill(Color.YELLOW);

        Circle circle6 = new Circle();
        circle6.setRadius(30.0);
        circle6.setFill(Color.PURPLE);

        HBox hBox = new HBox(10, circle1, circle2, circle3, circle4, circle5, circle6);
        return hBox;
    }

    private Node createDemoStroke() {
        Circle circle1 = new Circle();
        circle1.setRadius(30.0);
        circle1.setFill(Color.RED);
        circle1.setStroke(Color.BLACK);
        circle1.setStrokeWidth(8.0);

        Circle circle2 = new Circle();
        circle2.setRadius(30.0);
        circle2.setFill(Color.VIOLET);
        circle2.setStroke(Color.BROWN);
        circle2.setStrokeWidth(5.0);

        Circle circle3 = new Circle();
        circle3.setRadius(30.0);
        circle3.setFill(Color.BLUE);
        circle3.setStroke(Color.RED);
        circle3.setStrokeWidth(6.0);

        Circle circle4 = new Circle();
        circle4.setRadius(30.0);
        circle4.setFill(Color.PURPLE);
        circle4.setStroke(Color.GREEN);
        circle4.setStrokeWidth(2.0);

        Circle circle5 = new Circle();
        circle5.setRadius(30.0);
        circle5.setFill(Color.FIREBRICK);
        circle5.setStroke(Color.PURPLE);
        circle5.setStrokeWidth(7.0);

        Circle circle6 = new Circle();
        circle6.setRadius(30.0);
        circle6.setFill(Color.HOTPINK);
        circle6.setStroke(Color.SILVER);
        circle6.setStrokeWidth(3.0);

        HBox hBox = new HBox(10, circle1, circle2, circle3, circle4, circle5, circle6);
        return hBox;
    }

    private Node createDemoStarSky() {
        Pane pane = new Pane();
        pane.setPrefSize(360, 240);
        pane.setMinSize(360, 240);
        pane.setMaxSize(360, 240);

        Circle circle1 = createCircle(35, 40, 6, Color.LIGHTYELLOW, Color.BLACK, 1.5);
        Circle circle2 = createCircle(85, 75, 10, Color.LIGHTBLUE, Color.BLACK, 2);
        Circle circle3 = createCircle(145, 35, 4, Color.LIGHTGREEN, null, 0);
        Circle circle4 = createCircle(210, 90, 14, Color.PINK, Color.BLACK, 2.5);
        Circle circle5 = createCircle(300, 45, 8, Color.ORANGE, Color.DARKORANGE, 1.5);

        Circle circle6 = createCircle(55, 155, 13, Color.CORAL, Color.BLACK, 3);
        Circle circle7 = createCircle(125, 185, 5, Color.VIOLET, null, 0);
        Circle circle8 = createCircle(185, 145, 9, Color.SKYBLUE, Color.BLUE, 2);
        Circle circle9 = createCircle(260, 175, 6, Color.LIGHTSEAGREEN, null, 0);
        Circle circle10 = createCircle(330, 210, 7, Color.GOLD, Color.BLACK, 1);

        pane.getChildren().addAll(
                circle1, circle2, circle3, circle4, circle5,
                circle6, circle7, circle8, circle9, circle10
        );

        return pane;
    }

    private Circle createCircle(
            double x,
            double y,
            double radius,
            Color fill,
            Color stroke,
            double strokeWidth
    ) {
        Circle circle = new Circle();

        circle.setCenterX(x);
        circle.setCenterY(y);
        circle.setRadius(radius);
        circle.setFill(fill);

        if (stroke == null || strokeWidth == 0) {
            circle.setStroke(Color.TRANSPARENT);
            circle.setStrokeWidth(0);
        } else {
            circle.setStroke(stroke);
            circle.setStrokeWidth(strokeWidth);
        }

        return circle;
    }
}
