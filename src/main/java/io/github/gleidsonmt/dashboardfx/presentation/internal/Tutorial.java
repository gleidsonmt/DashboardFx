package io.github.gleidsonmt.dashboardfx.presentation.internal;


import io.github.gleidsonmt.blockcode.BlockCode;
import io.github.gleidsonmt.dashboardfx.utils.Scroll;
import io.github.gleidsonmt.glad.base.Root;
import io.github.gleidsonmt.glad.base.responsive.DefaultBreak;
import io.github.gleidsonmt.glad.controls.icon.Icon;
import io.github.gleidsonmt.glad.controls.icon.SVGIcon;
import io.github.gleidsonmt.presentation.Presentation;
import io.github.gleidsonmt.presentation.TreeTitle;
import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.application.Platform;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.shape.SVGPath;
import javafx.util.Duration;
import org.jetbrains.annotations.ApiStatus;

import java.util.*;


/**
 * @author Gleidson Neves da Silveira | gleidisonmt@gmail.com
 * Create on  19/01/2023
 */
public class Tutorial extends Presentation {

    private final ScrollPane scroll = new ScrollPane();
    private final BorderPane body = new BorderPane();
    private final VBox aside = new VBox();
    private final VBox menu = new VBox();
    private final VBox center = new VBox();
    private List<TreeTitle> data;
    private final ToggleGroup group = new ToggleGroup();
    private final Button btnTop = createButton();
    private final List<TreeTitle> breaks = new ArrayList<>();
    private boolean rolling = true;
    int count = 1;

    private boolean indicators = false;
    private boolean overview = false;

    public Tutorial() {
        body.setUserData(this);
        aside.setPadding(new Insets(0, 20, 0, 20));
//        aside.setPrefWidth(250);
        btnTop.getStyleClass().addAll("btn-directions padding-20 round".split(" "));
        center.setId("tutorial-center-body");
        scroll.setId("tutorial-scroll");
        body.setId("tutorial-body");
        scroll.setMinHeight(500);

        getRoot().sceneProperty().addListener((_, _, newValue) -> {
            if (!overview) return;
            group.selectToggle(group.getToggles().getFirst());
            ((ToggleButton) group.getToggles().getFirst()).requestFocus();

            if (newValue != null) {
                Root main = (Root) newValue.getRoot();
                main.addBreakpoint(_ -> {
                    body.setRight(null);
                    if (overview) body.setTop(aside);
                    StackPane.setMargin(btnTop, new Insets(10, 40, 10, 10));
                    aside.setPadding(new Insets(0));

                    aside.setMaxHeight(100);
                    aside.setPrefHeight(100);

                }, DefaultBreak.SM, DefaultBreak.MD);

                main.addBreakpoint(_ -> {
                    menu.setMaxHeight(-1);
                    body.getChildren().remove(aside);
                    if (overview) body.setRight(aside);
                    aside.setPadding(new Insets(0, 20, 0, 20));
                    StackPane.setMargin(btnTop, new Insets(10, 40 + 250, 10, 10));
                }, DefaultBreak.WIDE, DefaultBreak.XL, DefaultBreak.XXL);
            }
        });
//        body.addPoint(_ -> {
//            body.getChildren().remove(aside);
//            body.setTop(aside);
//            StackPane.setMargin(btnTop, new Insets(10, 40, 10, 10));
//            aside.setPadding(new Insets(0));
//
//            aside.setMaxHeight(100);
//            aside.setPrefHeight(100);
//
//        }, DefaultBreak.MOBILE, DefaultBreak.SM, DefaultBreak.MD);

//        body.addPoint(_ -> {
//            menu.setMaxHeight(-1);
//            if (body.getChildren().contains(aside)) {
//                body.getChildren().remove(aside);
//                body.setRight(aside);
//            } else {
//                body.setRight(aside);
//            }
//            aside.setPadding(new Insets(0, 20, 0, 20));
//            StackPane.setMargin(btnTop, new Insets(10, 40 + 250, 10, 10));
//        }, DefaultBreak.values());

        StackPane.setAlignment(btnTop, Pos.BOTTOM_RIGHT);
    }

    /**
     * Add numbers to the TreeTiles
     */
    public Tutorial indicators() {
        this.indicators = true;
        return this;
    }

    /**
     * Add a side nav with that indicates and rolls by TreeTiles
     */
    public Tutorial overview() {
        this.overview = true;
        return this;
    }

    private void createTree(List<TreeTitle> data, VBox nav) {

        List<TreeTitle> firstLevel = data.stream()
                .filter(p -> p.getRelated() == null)
                .peek(c -> c.setIndex(String.valueOf(count++)))
                .toList();

        count = 1;
        List<VBox> firstList = firstLevel.stream().map(this::buildTree).toList();

        firstList.forEach(c -> menu.getChildren().add(c));

        nav.getChildren().add(menu);
        menu.getStyleClass().addAll("menu-content", "padding-20");
        VBox.setVgrow(menu, Priority.ALWAYS);
//        menu.setPadding(new Insets(0, 0, 20, 0));

        this.getRoot().sceneProperty().addListener((_, _, newValue) -> {
            if (newValue != null) {
                ToggleButton first = firstList.getFirst().getChildren()
                        .stream()
                        .filter(e -> e instanceof ToggleButton)
                        .map(e -> (ToggleButton) e)

                        .findFirst().get();
//                first.setSelected(true);
//                group.selectToggle(first);
//                first.getParent().requestFocus();
            }
        });
    }

    private VBox buildTree(TreeTitle item) {
        VBox parent = createMenu(item);
        parent.getStyleClass().add("menu");

        List<TreeTitle> children =
                data.stream().filter(child -> child.getRelated() != null
                                              &&
                                              child.getRelated().getText().equals(item.getText()) && child.getRelated().getId().equals(item.getId())
                        )
                        .toList();

        if (!children.isEmpty()) {
            children.forEach(c -> {
                c.setIndex(item.getIndex() + "." + count++);
            });
            count = 1;
        }

        VBox subMenu = new VBox();
        for (TreeTitle child : children) {
            VBox i = buildTree(child);
            subMenu.getChildren().add(i);

            if (!subMenu.getStyleClass().contains("menu")) subMenu.getStyleClass().add("sub-menu");

            i.getChildren().stream()
                    .filter(el -> el instanceof ToggleButton)
                    .map(el -> (ToggleButton) el)
                    .forEach(e -> {
                        var offset = 1;
                        String before = String.valueOf(((TreeTitle) e.getUserData()).getIndex());
                        String[] arr = before.split("\\.");
                        for (int k = 0; k < arr.length; k++) {
                            GridPane.setFillWidth(child, true);
                            VBox.setMargin(e, new Insets(0, 0, 0, 10 * (offset++)));
                        }
                    });
        }
        if (!subMenu.getChildren().isEmpty()) {
            parent.getChildren().add(subMenu);
        }
        return parent;
    }

    private VBox createMenu(TreeTitle label) {
        ToggleButton grid = createItem(label);
        return new VBox(grid);
    }

    private ToggleButton createItem(TreeTitle label) {
        ToggleButton toggle = createToggle(label);
        toggle.setMaxHeight(30);
        toggle.setPadding(new Insets(2));
        group.getToggles().add(toggle);
        toggle.setUserData(label);
        toggle.getStyleClass().addAll("overview-item", "h6");
        return toggle;
    }

    public void select(String name) {
        Platform.runLater(() -> {
            Optional<ToggleButton> optional = group.getToggles()
                    .stream()
                    .filter(el -> el instanceof ToggleButton)
                    .map(el -> (ToggleButton) el)
                    .filter(el -> el.getText().equals(name))
                    .findFirst();
            if (optional.isPresent()) {
                group.selectToggle(optional.get());
                optional.get().requestFocus();
            }
        });
    }


    private Button createCopy() {
        Button button = new Button("Copy");
        button.getStyleClass().addAll("min-size-40", "flat");
        button.setGraphic(new SVGIcon(Icon.COPY));
        button.setContentDisplay(ContentDisplay.GRAPHIC_ONLY);
        StackPane.setMargin(button, new Insets(10));
        button.setOnMouseClicked(e -> {
            button.setContentDisplay(ContentDisplay.TEXT_ONLY);
            Root r = (Root) getRoot().getScene().getRoot();
            r.behavior().snack().graphic(new SVGIcon(Icon.PASTE)).message("The content is in clipboard.").show();
            TimerTask timerTask = new TimerTask() {
                @Override
                public void run() {
                    Platform.runLater(() -> {
                        button.setContentDisplay(ContentDisplay.GRAPHIC_ONLY);
                    });
                }
            };
            new Timer().schedule(timerTask, 2000);
        });
        return button;
    }

    private ToggleButton createToggle(TreeTitle label) {
        ToggleButton toggle = new ToggleButton(indicators ? label.getIndex() + ". " + label.getText() : label.getText());
//        ToggleButton toggle = new ToggleButton( label.getIndex() + ". " + label.getText() );
        toggle.setUserData(label);

        toggle.selectedProperty().addListener((_, _, newValue) -> {
            if (rolling) return;
            if (newValue) {
                TreeTitle l = (TreeTitle) toggle.getUserData();

                Scroll.scrollTo(scroll, l)
                        .setOnFinished(e -> rolling = true);
            }
        });

        toggle.setOnMousePressed(e -> rolling = false);

        label.setLabelFor(toggle);
        breaks.add(label);

        toggle.addEventHandler(MouseEvent.MOUSE_PRESSED, event -> {
            if (toggle.isSelected()) {
                event.consume();
            }
        });

        return toggle;
    }

    int idCount = 0;

    @Override
    public Tutorial build() {
        if (overview) {
            Label title = new Label("Overview Content");
            title.getStyleClass().addAll("overview-title");
            title.setGraphic(new SVGIcon(Icon.STACK));
//        title.setStyle("-fx-font-family: \"Instagram Sans Headline Bold\"; " +
//                       "-fx-font-size: 14px; " +
//                       "-fx-fill: -fx-accent; " +
//                       "");
            aside.setPadding(new Insets(20));
            aside.getChildren().add(title);
            aside.setSpacing(20);
            aside.setAlignment(Pos.TOP_CENTER);

            aside.getStyleClass().add("nav");

            // pegando todos os items q são label position e titulos
            data = items.stream()
                    .filter(filter -> filter instanceof TreeTitle
                                      && (filter.getStyleClass().contains("title") || filter.getStyleClass().stream().anyMatch(clazz -> clazz.startsWith("h"))))
                    .map(mapped -> (TreeTitle) mapped)
                    .peek(peeked -> peeked.setId("tile-" + idCount++))
                    .toList();
//                .peek(el -> VBox.setVgrow(el, Priority.ALWAYS))
            items
                    .stream().filter(el -> el instanceof BlockCode)
                    .map(el -> (BlockCode) el)
                    .forEach(e -> {
                        VBox.setVgrow(e, Priority.ALWAYS);
                        double height = e.getContent().lines().count() * 12.5;
                        e.setMinHeight(100);
                        e.setMinHeight(e.getMinHeight() + height);
                    });

            // Criando a tree
            createTree(data, aside);
            body.setRight(aside);

            aside.setMinWidth(250);
//        body.setSpacing(10);
            aside.setStyle("-fx-background-color: -fx-background;");
        }

//        ((ToggleButton)aside.getChildren().get(0)).setSelected(true);

        root.getChildren().setAll(body);
        body.setCenter(scroll);

        scroll.setContent(center);
        root.getChildren().add(btnTop);
        root.setAlignment(Pos.BOTTOM_RIGHT);
        root.getStyleClass().addAll("padding-20".split(" "));

        scroll.setFitToHeight(true);
        scroll.setFitToWidth(true);

        HBox.setHgrow(scroll, Priority.ALWAYS);

        for (Node node : items) {
            center.getChildren().add(node);
        }

        center.setAlignment(Pos.TOP_LEFT);
        btnTop.setVisible(false);

        scroll.vvalueProperty().addListener((observable, oldValue, newValue) -> {
            btnTop.setVisible(newValue.doubleValue() > 0.5);
            if (!rolling) return;

            for (int i = 1; i < breaks.size(); i++) {
                TreeTitle la = breaks.get(i);
                ToggleButton t = (ToggleButton) la.getLabelFor();

                if (Scroll.getY(scroll, la) <= newValue.doubleValue()) {
                    t.setSelected(true);
                    t.getParent().requestFocus();
                }
//
                if (newValue.doubleValue() == 0) {
                    TreeTitle l = breaks.getFirst();
                    ToggleButton f = (ToggleButton) l.getLabelFor();
                    f.setSelected(true);
                    f.getParent().requestFocus();
                }
            }
        });
        return this;
    }

    private Button createButton() {
        Button button = new Button();
        button.getStyleClass().addAll("btn-float", "depth-1");
        SVGPath icon = new SVGPath();
        icon.setFill(Color.WHITE);
        icon.setContent("M11 20V7.825l-5.6 5.6L4 12l8-8 8 8-1.4 1.425-5.6-5.6V20Z");
        button.setGraphic(icon);
        icon.setStyle("-fx-fill: white");
        button.setContentDisplay(ContentDisplay.GRAPHIC_ONLY);
        button.setAlignment(Pos.CENTER);
        button.setOnAction(event -> {
            Timeline timeline = new Timeline();
            timeline.getKeyFrames().addAll(
                    new KeyFrame(Duration.ZERO, new KeyValue(
                            scroll.vvalueProperty(), scroll.getVvalue()
                    )),
                    new KeyFrame(Duration.millis(200), new KeyValue(
                            scroll.vvalueProperty(), 0
                    ))
            );

            timeline.play();
        });
        return button;
    }
}