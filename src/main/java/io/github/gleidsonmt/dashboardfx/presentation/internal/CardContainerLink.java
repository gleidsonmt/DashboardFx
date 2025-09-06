package io.github.gleidsonmt.dashboardfx.presentation.internal;

import io.github.gleidsonmt.dashboardfx.Main;
import io.github.gleidsonmt.glad.base.Layout;
import io.github.gleidsonmt.glad.base.Root;
import io.github.gleidsonmt.glad.controls.icon.Icon;
import io.github.gleidsonmt.glad.controls.icon.SVGIcon;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.Label;
import javafx.scene.control.Separator;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;

import java.awt.*;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;

/**
 * @author Gleidson Neves da Silveira | gleidisonmt@gmail.com
 * Create on  23/03/2025
 */
public class CardContainerLink extends VBox {

    public CardContainerLink() {
        GridPane grid = new GridPane();
        Label title = new Label("Popular Tutoriais");
        title.getStyleClass().addAll("h4");
        grid.add(title, 0,0);
        grid.setHgap(20);
        Button close = new Button();
        close.setOnAction(e -> {
            Root main = (Root) getScene().getRoot();
//            main.behavior().dialog().close();
        });
        close.setGraphic(new SVGIcon(Icon.CLEAR));
        close.getStyleClass().addAll("size-30", "bg-light-gray", "round", "border-light-gray-2");
        close.setStyle("-icon-color: -dark-gray;");
        grid.add(close, 1,0);

        Hyperlink tutorialJenkov = new Hyperlink("Tutorial Jenkov");
        Hyperlink fxDocs = new Hyperlink("JavaFx Docs");
        Hyperlink cssReference = new Hyperlink("Css Reference");
        Hyperlink tutorialPoint = new Hyperlink("Tutorial Point");

        setLink(tutorialJenkov, "https://jenkov.com/tutorials/javafx");
        setLink(cssReference, "https://docs.oracle.com/javafx/2/api/javafx/scene/doc-files/cssref.html");
        setLink(tutorialPoint, "https://www.tutorialspoint.com/javafx/index.html");
        setLink(fxDocs, "https://fxdocs.github.io/docs/html5/");
        getChildren().addAll(grid, new Separator(), cssReference, fxDocs, tutorialJenkov, tutorialPoint);
        setSpacing(10);
        setPadding(new Insets(10));
    }

    public void setLink(Hyperlink link, String url) {
        link.getStyleClass().addAll("h5");
        link.setOnAction((e) -> {
            try {

                Desktop.getDesktop().browse(new URI(url.startsWith("https") ? url : "https://" + url));
            } catch (URISyntaxException | IOException ex) {
                throw new RuntimeException(ex);
            }
        });
    }
}
