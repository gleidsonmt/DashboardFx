package io.github.gleidsonmt.dashboardfx.presentation.core;

import io.github.gleidsonmt.dashboardfx.presentation.CustomizablePresentation;
import io.github.gleidsonmt.dashboardfx.presentation.internal.Tutorial;
import io.github.gleidsonmt.glad.controls.carousel.Carousel;
import io.github.gleidsonmt.presentation.Row;
import javafx.scene.Node;
import javafx.scene.layout.Region;
import javafx.scene.layout.StackPane;
import javafx.scene.text.Text;

/**
 * @author Gleidson Neves da Silveira | gleidisonmt@gmail.com
 * Create on  21/08/2025
 */
public class CarouselPres extends CustomizablePresentation {

    public CarouselPres() {
        super("Carousel");
    }

    @Override
    public Tutorial create() {
        return new Tutorial()
                .h3("Carousel")
                .text("Carousel it's a region that can slide nodes with indicators and arrows to move through the children.")
                .legend("io.github.gleidsonmt.glad.controls.carousel.Carousel")
                .demo(createDemo())
                .code("""
                        Carousel carousel = new Carousel();
                        carousel.setItems(createItems);
                        """)
                .table(
                        "Css Property", "Type",
                        new Row("-fx-auto-ride", "[boolean]"),
                        new Row("-fx-transition-delay", "[duration]"),
                        new Row("-fx-transition-velocity", "[duration]")
                )
                ;
    }

    private Carousel<Node> createDemo() {
        var carousel =  new Carousel<Node>(
                createPanel("warning", "First Panel"),
                createPanel("info", "Second Panel"),
                createPanel("success", "Third Panel")
        );
        carousel.setPrefSize(300, 300);
        return carousel;
    }

    private Region createPanel(String color, String text) {
        var title = new Text(text);
        title.getStyleClass().addAll("text-white h3".split(" "));
        var region = new StackPane(title);
        region.getStyleClass().addAll(("w-200 h-200 bg-" + color ).split(" "));
        return region;
    }
}
