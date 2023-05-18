/*
 *
 *    Copyright (C) Gleidson Neves da Silveira
 *
 *    This program is free software: you can redistribute it and/or modify
 *    it under the terms of the GNU General Public License as published by
 *    the Free Software Foundation, either version 3 of the License, or
 *   (at your option) any later version.
 *
 *    This program is distributed in the hope that it will be useful,
 *    but WITHOUT ANY WARRANTY; without even the implied warranty of
 *    MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *    GNU General Public License for more details.
 *
 *     You should have received a copy of the GNU General Public License
 *     along with this program.  If not, see <http://www.gnu.org/licenses/>.
 *
 */

package io.github.gleidsonmt.dashboardfx.core.view.layout.creators;

import io.github.gleidsonmt.dashboardfx.core.Context;
import io.github.gleidsonmt.dashboardfx.core.controls.GNButton;
import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.application.Platform;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.ToggleButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.SVGPath;
import javafx.util.Duration;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicBoolean;

/**
 * @author Gleidson Neves da Silveira | gleidisonmt@gmail.com
 * Version 0.0.1
 * Create on  19/01/2023
 */
public class TutorialCreator extends PresentationCreator {

    private final ScrollPane scroll = new ScrollPane();
    private final HBox body = new HBox();
    private final VBox aside = new VBox();
    private final VBox center = new VBox();
    private final ToggleGroup group = new ToggleGroup();
    private final GNButton btnFloat = createButton();

    public TutorialCreator(Context context) {
        super(context);
        body.setPadding(new Insets(20));
        aside.setPadding(new Insets(20));
        btnFloat.setVisible(false);

        widthProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue.doubleValue() < 736) {
                body.getChildren().remove(aside);
                StackPane.setMargin(btnFloat, new Insets(10, 40, 10, 10));
            } else {
                if (!body.getChildren().contains(aside)) {
                    body.getChildren().add(aside);
                    StackPane.setMargin(btnFloat, new Insets(10, 40 + 250, 10, 10));
                }
            }
        });

    }

    private List<LabelPosition> breaks = new ArrayList<>();
    private AtomicBoolean rolling = new AtomicBoolean(true);

    public void createNavHeader(String name, LabelPosition node) {

        ToggleButton toggle = new ToggleButton(name);
        node.setLabelFor(toggle);
        toggle.setUserData(node);
        breaks.add(node);
        toggle.addEventHandler(MouseEvent.MOUSE_CLICKED, event -> {
//            Scroll.scrollTo(scroll, node);

            LabelPosition l = (LabelPosition) toggle.getUserData();
            rolling.set(false);
            scrollTo(scroll, l);

        });

        Platform.runLater(() -> node.setPosition(getY(scroll, node)));

        toggle.addEventHandler(MouseEvent.MOUSE_PRESSED, event -> {
            if (toggle.isSelected()) {
                event.consume();
            }
        });


        toggle.setToggleGroup(group);
        toggle.getStyleClass().addAll("overview-item");

        aside.getChildren().add(toggle);


    }

    public double getY (ScrollPane scrollPane, Node node) {

        double heightViewPort = scrollPane.getViewportBounds().getHeight();
        double heightScrollPane = scrollPane.getContent().getBoundsInLocal().getHeight();
//        double y = node.getBoundsInParent().getMaxY();
        double y = node.getBoundsInParent().getMaxY() + 200;

        Timeline timeline = new Timeline();
        double yEnd = 0;

        if ( y < (heightViewPort / 2) ){ // set y for menor do que a metade
            // below 0 of scrollpane
            yEnd = 0;
        } else if ( ( y >= (heightViewPort / 2) ) & ( y<= (heightScrollPane - heightViewPort/2)) ){

            // between 0 and 1 of scrollpane
            yEnd = (y-(heightViewPort/2))/(heightScrollPane-heightViewPort);


        }
        else if(y>= (heightScrollPane-(heightViewPort/2))){
            // above 1 of scrollpane
            yEnd = 1;
        }

//        System.out.println("y = " + yEnd);
        return yEnd;

    }

    public void scrollTo (ScrollPane scrollPane, Node node) {

        double heightViewPort = scrollPane.getViewportBounds().getHeight();
        double heightScrollPane = scrollPane.getContent().getBoundsInLocal().getHeight();
        double y = node.getBoundsInParent().getMaxY();

//        System.out.println("heightViewPort = " + heightViewPort);
//        System.out.println("heightScrollPane = " + heightScrollPane);
//        System.out.println("y = " + y);

        Timeline timeline = new Timeline();
        double yEnd = 0;

        if ( y < (heightViewPort / 2) ){ // set y for menor do que a metade
            // below 0 of scrollpane
            yEnd = 0;
        } else if ( ( y >= (heightViewPort / 2) ) & ( y<= (heightScrollPane - heightViewPort/2)) ){

            // between 0 and 1 of scrollpane
            yEnd = (y-(heightViewPort/2))/(heightScrollPane-heightViewPort);


        }
        else if(y>= (heightScrollPane-(heightViewPort/2))){
            // above 1 of scrollpane
            yEnd = 1;
        }


        timeline.getKeyFrames().addAll(
                new KeyFrame(Duration.ZERO, new KeyValue(
                        scrollPane.vvalueProperty(),  scrollPane.getVvalue()
                )),
                new KeyFrame(Duration.millis(200), new KeyValue(
                        scrollPane.vvalueProperty(), yEnd
                ))
        );

        timeline.play();
        timeline.setOnFinished(event -> {
            rolling.set(true);

        });
    }

    @Override
    public Node build() {

        Label title = new Label("Overview Content");
        title.getStyleClass().addAll("text-bold", "h4");
        SVGPath icon = new SVGPath();
        icon.setContent("M480 882.92 152.31 628.306l37.307-28.153L480 825.383l290.383-225.23 37.307 28.153L480 882.92Zm0-140.845L152.31 487.461 480 232.848l327.69 254.613L480 742.075Zm0-269.844Zm0 212.306 253.538-197.076L480 290.769 226.462 487.461 480 684.537Z");
        icon.setScaleX(0.03);
        icon.setScaleY(0.03);
        title.setGraphic(new Group(icon));
        aside.getChildren().add(title);

        items.forEach(each -> {
            if (each instanceof LabelPosition lbl) {
                if (lbl.getStyleClass().contains("title")) {
                    createNavHeader(lbl.getText(), lbl);
                }
            }
        });

//        ((ToggleButton)aside.getChildren().get(1)).setSelected(true);

        getChildren().setAll(body);
        body.getChildren().setAll(scroll, aside);
        scroll.setPadding(new Insets(100, 0, 0,0));
        scroll.setContent(center);

        this.getChildren().add(btnFloat);
        this.setAlignment(Pos.BOTTOM_RIGHT);

        StackPane.setMargin(btnFloat, new Insets(10, 40 + 250, 10, 10));

        aside.setMinWidth(250);
        body.setSpacing(10);
        aside.getStyleClass().addAll("border-1", "border-l-1", "border-medium-gray");

        scroll.setFitToHeight(true);
        scroll.setFitToWidth(true);

        HBox.setHgrow(scroll, Priority.ALWAYS);

        center.getChildren().setAll(items);

        center.setAlignment(Pos.TOP_LEFT);

//        WebView webView = new WebView();
//        webView.getEngine().setJavaScriptEnabled(true);
//        webView.getEngine().loadContent("""
//                <iframe width="400" height="480" src="https://www.youtube.com/embed/hZsYU7UbWmU" title="Decorator JavaFx 8" frameborder="0" allow="accelerometer; autoplay; clipboard-write; encrypted-media; gyroscope; picture-in-picture; web-share" allowfullscreen></iframe>
//                """);
//        aside.getChildren().add(
//                webView
//        );

        scroll.vvalueProperty().addListener((observable, oldValue, newValue) -> {

            if (newValue.doubleValue() > 0.5) {
                btnFloat.setVisible(true);
            } else {
                btnFloat.setVisible(false);
            }

            if (rolling.get()) {
                for (int i = 1; i < breaks.size(); i++) {
                    LabelPosition la = breaks.get(i);
                    ToggleButton t = (ToggleButton) la.getLabelFor();
                    t.setSelected(false);

//                    System.out.println("l.getPosition() = " + la.getPosition());

                    if (newValue.doubleValue() == 0) {
                        ((ToggleButton) breaks.get(0).getLabelFor()).setSelected(true);

                    }
//                else
                    if (la.getPosition() >= newValue.doubleValue() &&
                            newValue.doubleValue() >= breaks.get(i - 1).getPosition()) {
                        t.setSelected(true);
//                        ((ToggleButton) breaks.get(0).getLabelFor()).setSelected(false);
                    } else {
                        t.setSelected(false);
                    }
                }

            }
        });

        return this;
    }

    private GNButton createButton() {

        GNButton button = new GNButton();
        button.getStyleClass().addAll("btn-float", "depth-1");
        SVGPath icon = new SVGPath();
        icon.setFill(Color.WHITE);
        icon.setContent("M11 20V7.825l-5.6 5.6L4 12l8-8 8 8-1.4 1.425-5.6-5.6V20Z");
        button.setGraphic(icon);
        icon.setStyle("-fx-fill: white");

        button.setOnAction(event ->
                scroll.setVvalue(0));

        return button;

    }
}