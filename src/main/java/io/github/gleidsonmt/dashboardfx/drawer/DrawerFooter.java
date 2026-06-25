package io.github.gleidsonmt.dashboardfx.drawer;
//
//import io.github.gleidsonmt.dashboardfx.Main;
//import io.github.gleidsonmt.dashboardfx.presentation.internal.CardContainerLink;

import io.github.gleidsonmt.dashboardfx.presentation.internal.CardContainerLink;
import io.github.gleidsonmt.glad.base.Root;
import io.github.gleidsonmt.glad.base.dialog.WrapperEffect;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Hyperlink;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;
//
/**
 * @author Gleidson Neves da Silveira | gleidisonmt@gmail.com
 * Create on  20/03/2025
 */
public class DrawerFooter extends StackPane {
//
    private final String jenkovWebSite = "https://jenkov.com/tutorials/javafx/index.html";
    private Hyperlink link;

    public DrawerFooter() {
        Text text = new Text("Get deeper");
        text.getStyleClass().addAll("text-white h4 bold".split(" "));

        this.getChildren().addAll(text);
        StackPane.setAlignment(text, Pos.TOP_CENTER);
        StackPane.setMargin(text, new Insets(10));

        link = new Hyperlink("Learn More");
        link.getStyleClass().addAll("bg-white", "padding-10", "radius-5", "text-accent", "bold");
        this.getStyleClass().addAll("w-100", "bg-accent", "align-center");
        this.setMinHeight(120);
        this.getChildren().add(link);

        link.setOnAction(_ -> {
            Root root = (Root) getScene().getRoot();
            root.behavior().dialog()
                    .content(new CardContainerLink())
                    .width(500)
                    .with(WrapperEffect.GRAY)
                    .pos(Pos.CENTER)
                    .show();
        });

        addCircles();
        clip();
        VBox.setMargin(this, new Insets(40, 40, 0,40));
        setAlignment(Pos.BOTTOM_CENTER);
    }

    private void addCircles() {
        Circle circle = createCircle();
        circle.setTranslateX(10);
        circle.setTranslateY(-10);
        StackPane.setAlignment(circle, Pos.TOP_RIGHT);

        Circle c = createCircle();
        c.setTranslateX(-10);
        c.setTranslateY(10);
        StackPane.setAlignment(c, Pos.BOTTOM_LEFT);
        this.getChildren().addAll(circle, c);
    }

    private Circle createCircle() {
        Circle circle = new Circle();
        circle.setStyle("-fx-stroke: #ffffff10; -fx-fill: #ffffff40;");
        circle.setRadius(20);
        return circle;
    }

    private void clip() {
        Rectangle rectangle = new Rectangle();
        rectangle.setArcWidth(20);
        rectangle.setArcHeight(20);
        rectangle.widthProperty().bind(this.widthProperty());
        rectangle.heightProperty().bind(this.heightProperty());
        this.setClip(rectangle);
    }
}
