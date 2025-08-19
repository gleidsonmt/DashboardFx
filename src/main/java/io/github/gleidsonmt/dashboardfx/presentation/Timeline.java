package io.github.gleidsonmt.dashboardfx.presentation;

import io.github.gleidsonmt.glad.controls.icon.Icon;
import io.github.gleidsonmt.glad.controls.icon.SVGIcon;
import io.github.gleidsonmt.presentation.Presentation;
import io.github.gleidsonmt.presentation.PresentationCreator;
import io.github.gleidsonmt.presentation.TreeTitle;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.shape.Circle;
import javafx.scene.text.Text;
import javafx.scene.text.TextFlow;
import org.jetbrains.annotations.ApiStatus;
import org.jetbrains.annotations.NotNull;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.stream.Collectors;

/**
 * @author Gleidson Neves da Silveira | gleidisonmt@gmail.com
 * Create on  28/02/2025
 */
public class Timeline extends Presentation<Timeline> {

    private final ScrollPane scroll = new ScrollPane();
    private StackPane body = new StackPane();
    private VBox box = new VBox();

    private String title = null;

    public Timeline() {

    }

    public Timeline title(String title) {
//        TreeTitle treeTitle = new TreeTitle(title);
//        treeTitle.getStyleClass().add("h3");
//        items.add(treeTitle);
        return this;
    }



    public Timeline text(String text) {
        this.items.add(this.createText(text, ""));
        return this;
    }

    private @NotNull TextFlow createText(String _text, String... options) {
        Text text = new Text(_text);
        text.getStyleClass().add("text-14");
        TextFlow flow = new TextFlow(new Node[]{text});
        StringBuilder builder = new StringBuilder();

        for(String c : options) {
            if (c.startsWith("-fx-")) {
                builder.append(c);
            } else {
                flow.getStyleClass().add(c);
            }
        }

        flow.setStyle(builder.toString());
        return flow;
    }

    public Timeline date(LocalDate date) {
        SimpleDateFormat date_format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String formated = DateTimeFormatter
                .ofPattern("EEEE, d 'de' MMMM")
                .format(date)
                .replaceFirst("\\.", ",")

                .replace(".", "")
                ;

        String collect =
                Arrays.stream(formated.split(" ")).map(el -> el = el.substring(0, 1).toUpperCase() + el.substring(1) + " ").collect(Collectors.joining());

        if (this.title == null) {
            this.title = collect;
        }

        this.items.add(this.createDate(this.title, null, "title",  "h5"));
        return this;
    }

    private @NotNull Label createDate(String title, String related, String... styles) {
//        TreeTitle label = new TreeTitle(title, "title-" + idCount++);
//        Circle circle = new Circle();
//        circle.setStyle("-fx-fill: -medium-gray-2;");
//        circle.setRadius(4);
//        label.setGraphic(circle);
//        if (related != null) {
////            label.setRelated(new TreeTitle(related));
//        }
//
//        StringBuilder builder = new StringBuilder();
//        if (styles != null) {
//            for(String c : styles) {
//                if (c.startsWith("-fx-")) {
//                    builder.append(c);
//                } else {
//                    label.getStyleClass().add(c);
//                }
//            }
//        }
//
//        label.setStyle(builder.toString() + "-text-color: -medium-gray-2; -fx-translate-x: -5px;");
//        label.toFront();
//
//        VBox.setMargin(label, new Insets(10, 0, 10,0));

        return null;
    }


    @Override
    public Node getRoot() {
        return scroll;
    }
    //
    @Override
    public Timeline build() {
//        body.getChildren().addAll(this.items);
        box.setStyle("-fx-border-color: -medium-gray; -fx-border-width: 0px 0px 0px 2px;");
        scroll.setStyle("-fx-background-insets: 20px; -fx-border-insets: 20px;");

        box.getChildren().setAll(items);

        body.getChildren().setAll(box);

        scroll.setContent(body);
        scroll.setFitToHeight(true);
        scroll.setFitToWidth(true);
        body.setPadding(new Insets(10));
        box.setPadding(new Insets(10));
//        ScrollPane.setMargin(box, new Insets(20));
//        BorderPane.setMargin(box, new Insets(20));

//        items.forEach(Node::toFront);
        return this;
    }
}
