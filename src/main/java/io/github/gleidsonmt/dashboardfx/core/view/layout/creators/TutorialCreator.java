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
import io.github.gleidsonmt.dashboardfx.core.view.layout.DrawerContainer;
import io.github.gleidsonmt.dashboardfx.core.view.util.Scroll;
import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.application.Platform;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.SVGPath;
import javafx.util.Duration;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Gleidson Neves da Silveira | gleidisonmt@gmail.com
 * Version 0.0.1
 * Create on  19/01/2023
 */
public class TutorialCreator extends PresentationCreator {

    private final ScrollPane scroll = new ScrollPane();
    private final HBox body = new HBox();
    private final VBox aside = new VBox();
    private final VBox menu = new VBox();
    private final VBox center = new VBox();
    private List<TreeTitle> data;
    private final ToggleGroup group = new ToggleGroup();
    private final GNButton btnTop = createButton();
    private final GNButton btnMap = createSideButton();
    private final List<TreeTitle> breaks = new ArrayList<>();
    private boolean rolling = true;
    int count = 1;

    public TutorialCreator(Context context) {
        super(context);
        body.setPadding(new Insets(20));
        aside.setPadding(new Insets(0, 20, 0, 20));
        btnTop.setVisible(false);

        root.widthProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue.doubleValue() < 736) {
                body.getChildren().remove(aside);
                StackPane.setMargin(btnTop, new Insets(10, 40, 10, 10));
                if (!root.getChildren().contains(btnMap))
                    root.getChildren().add(btnMap);
            } else {
                if (!body.getChildren().contains(aside)) {
                    body.getChildren().add(aside);
                    StackPane.setMargin(btnTop, new Insets(10, 40 + 250, 10, 10));
                    root.getChildren().remove(btnMap);
                }
            }
        });

        StackPane.setAlignment(btnMap, Pos.TOP_RIGHT);
        StackPane.setMargin(btnMap, new Insets(10, 0, 0,0));

    }

//    StackPane.setMargin(btnTop, new Insets(10, 40, 10, 10));
//
    private  void createTree(List<TreeTitle> data, VBox nav) {
        List<TreeTitle> firstLevel = data.stream()
                .filter(p -> p.getRelated() == null)
                .peek(c -> c.setIndex(String.valueOf(count++)))
                .toList();
        count = 1;
        List<VBox> firstList = firstLevel.stream().map(this::buildTree).toList();
        ((ToggleButton)firstList.get(0).getChildren().get(0)).setSelected(true);
        firstList.forEach(c -> menu.getChildren().add(c));
        nav.getChildren().add(menu);

    }

    private double space = 0;
    private VBox buildTree(TreeTitle item) {
        VBox parent = createMenu(item);
        List<TreeTitle> children =
                data.stream().filter(child -> child.getRelated() != null
                        && child.getRelated().getText().equals(item.getText()))
                        .toList();

        if (children.size() > 0) {
            children.forEach(c -> {
                c.setIndex(item.getIndex() + "." + count++);
            });
            count = 1;
        }

        VBox subMenu = new VBox();
        for (TreeTitle child : children) {
            VBox i = buildTree(child);
            subMenu.getChildren().add(i);
            VBox.setMargin(subMenu, new Insets(0, 0, 0, space += 10));
        }

        parent.getChildren().add(subMenu);
        return parent;
    }

    private VBox createMenu(TreeTitle label) {
        VBox root = new VBox();
        ToggleButton toggle = createToggle(label);
        root.getChildren().add(toggle);
        return root;
    }

    private ToggleButton createToggle(TreeTitle label) {
        ToggleButton toggle = new ToggleButton(label.getIndex() + ". " +label.getText());
        toggle.setUserData(label);
        toggle.getStyleClass().addAll("overview-item");
        group.getToggles().add(toggle);

        toggle.addEventHandler(MouseEvent.MOUSE_CLICKED, event -> {
            TreeTitle l = (TreeTitle) toggle.getUserData();
            rolling = false;
            Scroll.scrollTo(scroll, l)
                    .setOnFinished(e -> rolling = true);
        });

        label.setLabelFor(toggle);
        breaks.add(label);

        Platform.runLater(() ->
                label.setPosition(Scroll.getY(scroll, label)));

        toggle.addEventHandler(MouseEvent.MOUSE_PRESSED, event -> {
            if (toggle.isSelected()) {
                event.consume();
            }
        });
        Circle dot = new Circle();
        dot.setRadius(3);
        toggle.setGraphic(dot);
        return toggle;
    }

    @Override
    public TutorialCreator build() {

        Label title = new Label("Overview Content");
        title.getStyleClass().addAll("text-bold", "h4");
        SVGPath icon = new SVGPath();
        icon.setContent("M480 882.92 152.31 628.306l37.307-28.153L480 825.383l290.383-225.23 37.307 28.153L480 882.92Zm0-140.845L152.31 487.461 480 232.848l327.69 254.613L480 742.075Zm0-269.844Zm0 212.306 253.538-197.076L480 290.769 226.462 487.461 480 684.537Z");
        icon.setScaleX(0.03);
        icon.setScaleY(0.03);
        title.setGraphic(new Group(icon));
        aside.getChildren().add(title);

        // pegando todos os items q são labelposition e titulos

        data = items.stream()
                .filter(filter -> filter instanceof TreeTitle
                        && filter.getStyleClass().contains("title"))
                .map(mapped -> (TreeTitle) mapped).toList();

        // Criando a tree
        createTree(data, aside);
//        System.out.println("data = " + data);
//        ((ToggleButton)aside.getChildren().get(1)).setSelected(true);

        root.getChildren().setAll(body);
        body.getChildren().setAll(scroll, aside);
        scroll.setPadding(new Insets(100, 0, 0,0));
        scroll.setContent(center);

        root.getChildren().add(btnTop);
        root.setAlignment(Pos.BOTTOM_RIGHT);

        StackPane.setMargin(btnTop, new Insets(10, 40 + 250, 10, 10));

        aside.setMinWidth(250);
        body.setSpacing(10);
        aside.getStyleClass().addAll("border-1", "border-l-1", "border-medium-gray");

        scroll.setFitToHeight(true);
        scroll.setFitToWidth(true);

        HBox.setHgrow(scroll, Priority.ALWAYS);

//        Platform.runLater(() -> {

        for (Node node : items) {
//            TimerTask timerTask = new TimerTask() {
//                @Override
//                public void run() {
//                    Platform.runLater(() -> {
                        center.getChildren().add(node);
//                        try {
//                            Thread.sleep(1000);
//                        } catch (InterruptedException e) {
//                            throw new RuntimeException(e);
//                        }
//                    });
//                }
//            };
//            Timer timer = new Timer();
//            timer.schedule(timerTask, 1000);
        }

//        });

        center.setAlignment(Pos.TOP_LEFT);

        scroll.vvalueProperty().addListener((observable, oldValue, newValue) -> {

            btnTop.setVisible(newValue.doubleValue() > 0.5);

            if (rolling) {
                for (int i = 1; i < breaks.size(); i++) {
                    TreeTitle la = breaks.get(i);
                    ToggleButton t = (ToggleButton) la.getLabelFor();
                    t.setSelected(false);

//                    System.out.println("l.getPosition() = " + la.getPosition());

                    if (newValue.doubleValue() == 0) {
                        ((ToggleButton) breaks.get(0).getLabelFor()).setSelected(true);

                    }

                    t.setSelected(la.getPosition() >= newValue.doubleValue() &&
                            newValue.doubleValue() >= breaks.get(i - 1).getPosition());
                }

            }
        });

        return this;
    }

    private GNButton createSideButton() {

        GNButton button = new GNButton();
        button.getStyleClass().addAll("btn-option", "depth-2");
        SVGPath icon = new SVGPath();
        icon.setFill(Color.WHITE);
        icon.setContent("""
                M450.001-100.001v-160h-205.77l-109.999-110L244.231-480h205.77v-80h-280v-219.999h280v-80h59.998v80h205.77l109.999 110L715.769-560h-205.77v80h280v219.999h-280v160h-59.998ZM229.999-619.999h461.232l50.001-50L691.231-720H229.999v100.001ZM268.769-320h461.232v-100.001H268.769l-50.001 50L268.769-320Zm-38.77-299.999V-720v100.001ZM730.001-320v-100.001V-320Z
                """);
        icon.setScaleX(0.03);
        icon.setScaleY(0.03);
        Group group1 = new Group(icon);
        icon.setMouseTransparent(true);
        group1.setMouseTransparent(true);
        button.setGraphic(group1);
        button.setContentDisplay(ContentDisplay.GRAPHIC_ONLY);
        button.setAlignment(Pos.CENTER);
        button.setMaxWidth(30);

        button.setOnMouseClicked(event -> {
            context.wrapper()
                    .drawer()
                    .content(new DrawerContainer(
                            aside, 250
                        )
                    )
                    .side(HPos.RIGHT)
                    .show();
        });

        button.setOnMouseEntered(event -> {
            Timeline timeline = new Timeline();
            timeline.getKeyFrames().setAll(
                    new KeyFrame(Duration.ZERO, new KeyValue(
                            button.maxWidthProperty(), 30)),
                    new KeyFrame(Duration.millis(200), new KeyValue(
                            button.maxWidthProperty(), 50
                    ))
            );
            timeline.play();
        });

        button.setOnMouseExited(event -> {
            Timeline timeline = new Timeline();
            timeline.getKeyFrames().setAll(
                    new KeyFrame(Duration.ZERO, new KeyValue(
                            button.maxWidthProperty(), 50)),
                    new KeyFrame(Duration.millis(200), new KeyValue(
                            button.maxWidthProperty(), 30
                    ))
            );
            timeline.play();
        });

        return button;
    }

    private GNButton createButton() {
        GNButton button = new GNButton();
        button.getStyleClass().addAll("btn-float", "depth-1");
        SVGPath icon = new SVGPath();
        icon.setFill(Color.WHITE);
        icon.setContent("M11 20V7.825l-5.6 5.6L4 12l8-8 8 8-1.4 1.425-5.6-5.6V20Z");
        button.setGraphic(icon);
        icon.setStyle("-fx-fill: white");
        button.setContentDisplay(ContentDisplay.GRAPHIC_ONLY);
        button.setAlignment(Pos.CENTER);
        button.setOnAction(event ->
                scroll.setVvalue(0));

        return button;
    }




}