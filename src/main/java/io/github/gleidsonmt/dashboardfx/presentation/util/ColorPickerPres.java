package io.github.gleidsonmt.dashboardfx.presentation.util;
import io.github.gleidsonmt.dashboardfx.presentation.CustomizablePresentation;
import io.github.gleidsonmt.dashboardfx.presentation.internal.Tutorial;
import io.github.gleidsonmt.presentation.Presentation;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.ColorPicker;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;


public class ColorPickerPres  extends CustomizablePresentation {

    public ColorPickerPres() {
        super("ColorPicker");
    }

    @Override
    public Presentation create() {
        return new Tutorial()
                .overview()
                .h1("ColorPicker")
                .separator()
                .text("Select a color and preview how it looks when applied to text and different shapes")
                .demo(createDemoV())
                .h2("Install")
                .code("ThemeProvider.install(scene, \n\t... \n\tCss.COLOR_PICKER);", "java");
    }

    private Node createDemoV(){
        ColorPicker colorPicker = new ColorPicker(Color.BLUE);

        Text text = new Text("TEXT");
        Circle circle = new Circle(35);
        Rectangle rectangle = new Rectangle(100, 70);

        circle.fillProperty().bind(colorPicker.valueProperty());
        text.fillProperty().bind(colorPicker.valueProperty());
        rectangle.fillProperty().bind(colorPicker.valueProperty());

        VBox vBox = new VBox(colorPicker);
        HBox preview = new HBox(20, vBox, text, circle, rectangle);
        preview.setAlignment(Pos.CENTER);
        return preview;

    }


}
