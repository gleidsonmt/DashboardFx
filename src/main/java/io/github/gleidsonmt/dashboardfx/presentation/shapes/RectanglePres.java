package io.github.gleidsonmt.dashboardfx.presentation.shapes;

import io.github.gleidsonmt.dashboardfx.presentation.CustomizablePresentation;
import io.github.gleidsonmt.dashboardfx.presentation.internal.Tutorial;
import io.github.gleidsonmt.presentation.Presentation;
import javafx.scene.Node;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class RectanglePres extends CustomizablePresentation {

    public RectanglePres() {
        super("Rectangle");
    }

    public Presentation create() {
        return new Tutorial()
                .indicators()
                .overview()
                .h2("Rectangle")
                .separator()

                .text("""
                        The Rectangle shape draws a four-sided node using width, height, fill color, optional stroke, and x/y coordinates.
                        Use x and y to place each rectangle freely inside a Pane.""")

                .legend("javafx.scene.shape.Rectangle")
                .h3("Install")
                .code("ThemeProvider.install(root, \n\t... \n\tCss.RECTANGLE);", "java")

                .separator()
                .h3("Size")
                .demo(createDemoSize())
                .code("""
                        Rectangle rectangle1 = new Rectangle(); // Create a rectangle object.
                        rectangle1.setWidth(150);               // Set the rectangle width.
                        rectangle1.setHeight(150);              // Set the rectangle height.
                        rectangle1.setFill(Color.GRAY);         // Set the fill color.
                        """)

                .separator()
                .h3("Color")
                .demo(createDemoColor())
                .code("""
                        Rectangle rectangle1 = new Rectangle();
                        rectangle1.setWidth(50);
                        rectangle1.setHeight(50);
                        rectangle1.setFill(Color.RED); // Change the rectangle fill color.
                        """)

                .separator()
                .h3("Stroke")
                .demo(createDemoStroke())
                .code("""
                        Rectangle rectangle1 = new Rectangle();
                        rectangle1.setWidth(50);
                        rectangle1.setHeight(50);
                        rectangle1.setFill(Color.RED);

                        rectangle1.setStroke(Color.BLACK); // Set the border color.
                        rectangle1.setStrokeWidth(8.0);    // Set the border thickness.
                        """)

                .separator()
                .h3("Rounded Corners")
                .demo(createDemoRoundedCorners())
                .code("""
                        Rectangle rectangle1 = new Rectangle();
                        rectangle1.setWidth(80);
                        rectangle1.setHeight(50);
                        rectangle1.setFill(Color.CORAL);

                        rectangle1.setArcWidth(20);  // Set the horizontal corner curve.
                        rectangle1.setArcHeight(20); // Set the vertical corner curve.
                        """)

                .separator()
                .h3("Coordinates")
                .demo(createDemoCoordinates())
                .code("""
                        Pane pane = new Pane();
                        pane.setPrefSize(360, 240);

                        Rectangle rectangle = new Rectangle();
                        rectangle.setX(35.0);          // Place the rectangle on the X axis.
                        rectangle.setY(40.0);          // Place the rectangle on the Y axis.
                        rectangle.setWidth(40.0);      // Set the rectangle width.
                        rectangle.setHeight(28.0);     // Set the rectangle height.
                        rectangle.setFill(Color.GOLD); // Set the fill color.

                        rectangle.setStroke(Color.BLACK); // Add a stroke when needed.
                        rectangle.setStrokeWidth(1.5);    // Set the stroke thickness.

                        pane.getChildren().add(rectangle);
                        """);
    }

    private Node createDemoSize() {
        Rectangle rectangle1 = new Rectangle();
        rectangle1.setWidth(150);
        rectangle1.setHeight(150);
        rectangle1.setFill(Color.GRAY);

        Rectangle rectangle2 = new Rectangle();
        rectangle2.setWidth(100);
        rectangle2.setHeight(100);
        rectangle2.setFill(Color.GRAY);

        Rectangle rectangle3 = new Rectangle();
        rectangle3.setWidth(75);
        rectangle3.setHeight(75);
        rectangle3.setFill(Color.GRAY);

        Rectangle rectangle4 = new Rectangle();
        rectangle4.setWidth(35);
        rectangle4.setHeight(35);
        rectangle4.setFill(Color.GRAY);

        HBox hBox = new HBox(20);
        hBox.getChildren().addAll(rectangle1, rectangle2, rectangle3, rectangle4);

        return hBox;
    }

    private Node createDemoColor() {
        Rectangle rectangle1 = new Rectangle();
        rectangle1.setWidth(50);
        rectangle1.setHeight(50);
        rectangle1.setFill(Color.RED);

        Rectangle rectangle2 = new Rectangle();
        rectangle2.setWidth(50);
        rectangle2.setHeight(50);
        rectangle2.setFill(Color.VIOLET);

        Rectangle rectangle3 = new Rectangle();
        rectangle3.setWidth(50);
        rectangle3.setHeight(50);
        rectangle3.setFill(Color.BLUE);

        Rectangle rectangle4 = new Rectangle();
        rectangle4.setWidth(50);
        rectangle4.setHeight(50);
        rectangle4.setFill(Color.BLACK);

        Rectangle rectangle5 = new Rectangle();
        rectangle5.setWidth(50);
        rectangle5.setHeight(50);
        rectangle5.setFill(Color.YELLOW);

        Rectangle rectangle6 = new Rectangle();
        rectangle6.setWidth(50);
        rectangle6.setHeight(50);
        rectangle6.setFill(Color.PURPLE);

        HBox hBox = new HBox(20);
        hBox.getChildren().addAll(rectangle1, rectangle2, rectangle3, rectangle4, rectangle5, rectangle6);

        return hBox;
    }

    private Node createDemoStroke() {
        Rectangle rectangle1 = new Rectangle();
        rectangle1.setWidth(50);
        rectangle1.setHeight(50);
        rectangle1.setFill(Color.RED);
        rectangle1.setStroke(Color.BLACK);
        rectangle1.setStrokeWidth(8.0);

        Rectangle rectangle2 = new Rectangle();
        rectangle2.setWidth(50);
        rectangle2.setHeight(50);
        rectangle2.setFill(Color.VIOLET);
        rectangle2.setStroke(Color.BROWN);
        rectangle2.setStrokeWidth(5.0);

        Rectangle rectangle3 = new Rectangle();
        rectangle3.setWidth(50);
        rectangle3.setHeight(50);
        rectangle3.setFill(Color.BLUE);
        rectangle3.setStroke(Color.RED);
        rectangle3.setStrokeWidth(6.0);

        Rectangle rectangle4 = new Rectangle();
        rectangle4.setWidth(50);
        rectangle4.setHeight(50);
        rectangle4.setFill(Color.BLACK);
        rectangle4.setStroke(Color.GREEN);
        rectangle4.setStrokeWidth(2.0);

        Rectangle rectangle5 = new Rectangle();
        rectangle5.setWidth(50);
        rectangle5.setHeight(50);
        rectangle5.setFill(Color.YELLOW);
        rectangle5.setStroke(Color.PURPLE);
        rectangle5.setStrokeWidth(7.0);

        Rectangle rectangle6 = new Rectangle();
        rectangle6.setWidth(50);
        rectangle6.setHeight(50);
        rectangle6.setFill(Color.PURPLE);
        rectangle6.setStroke(Color.SILVER);
        rectangle6.setStrokeWidth(3.0);

        HBox hBox = new HBox(20);
        hBox.getChildren().addAll(rectangle1, rectangle2, rectangle3, rectangle4, rectangle5, rectangle6);

        return hBox;
    }

    private Node createDemoRoundedCorners() {
        Rectangle rectangle1 = createRoundedRectangle(80, 50, 0, Color.CORAL, Color.BLACK, 1.5);
        Rectangle rectangle2 = createRoundedRectangle(80, 50, 10, Color.LIGHTBLUE, Color.DARKBLUE, 2);
        Rectangle rectangle3 = createRoundedRectangle(80, 50, 25, Color.GOLD, Color.DARKORANGE, 2);
        Rectangle rectangle4 = createRoundedRectangle(80, 50, 45, Color.LIGHTGREEN, null, 0);

        HBox hBox = new HBox(20);
        hBox.getChildren().addAll(rectangle1, rectangle2, rectangle3, rectangle4);

        return hBox;
    }

    private Node createDemoCoordinates() {
        Pane pane = new Pane();
        pane.setPrefSize(360, 240);
        pane.setMinSize(360, 240);
        pane.setMaxSize(360, 240);

        Rectangle rectangle1 = createRectangle(35, 40, 40, 28, Color.LIGHTYELLOW, Color.BLACK, 1.5);
        Rectangle rectangle2 = createRectangle(95, 70, 55, 35, Color.LIGHTBLUE, Color.BLACK, 2);
        Rectangle rectangle3 = createRectangle(175, 35, 32, 55, Color.LIGHTGREEN, null, 0);
        Rectangle rectangle4 = createRectangle(240, 85, 70, 38, Color.PINK, Color.BLACK, 2.5);
        Rectangle rectangle5 = createRectangle(305, 35, 35, 50, Color.ORANGE, Color.DARKORANGE, 1.5);

        Rectangle rectangle6 = createRectangle(45, 155, 65, 32, Color.CORAL, Color.BLACK, 3);
        Rectangle rectangle7 = createRectangle(135, 175, 42, 28, Color.VIOLET, null, 0);
        Rectangle rectangle8 = createRectangle(205, 145, 58, 45, Color.SKYBLUE, Color.BLUE, 2);
        Rectangle rectangle9 = createRectangle(285, 165, 48, 30, Color.LIGHTSEAGREEN, null, 0);

        pane.getChildren().addAll(
                rectangle1, rectangle2, rectangle3, rectangle4, rectangle5,
                rectangle6, rectangle7, rectangle8, rectangle9
        );

        return pane;
    }

    private Rectangle createRectangle(
            double x,
            double y,
            double width,
            double height,
            Color fill,
            Color stroke,
            double strokeWidth
    ) {
        Rectangle rectangle = new Rectangle();

        rectangle.setX(x);
        rectangle.setY(y);
        rectangle.setWidth(width);
        rectangle.setHeight(height);
        rectangle.setFill(fill);

        if (stroke == null || strokeWidth == 0) {
            rectangle.setStroke(Color.TRANSPARENT);
            rectangle.setStrokeWidth(0);
        } else {
            rectangle.setStroke(stroke);
            rectangle.setStrokeWidth(strokeWidth);
        }

        return rectangle;
    }

    private Rectangle createRoundedRectangle(
            double width,
            double height,
            double arc,
            Color fill,
            Color stroke,
            double strokeWidth
    ) {
        Rectangle rectangle = createRectangle(0, 0, width, height, fill, stroke, strokeWidth);
        rectangle.setArcWidth(arc);
        rectangle.setArcHeight(arc);

        return rectangle;
    }
}
