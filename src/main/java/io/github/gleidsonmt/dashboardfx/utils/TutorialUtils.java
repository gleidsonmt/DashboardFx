package io.github.gleidsonmt.dashboardfx.utils;

import io.github.gleidsonmt.blockcode.BlockCode;
import io.github.gleidsonmt.blockcode.CodeType;
import io.github.gleidsonmt.dashboardfx.ResizablePane;
import io.github.gleidsonmt.dashboardfx.presentation.internal.Tutorial;
import io.github.gleidsonmt.glad.base.drawer.Module;
import io.github.gleidsonmt.glad.base.drawer.SideNav;
import io.github.gleidsonmt.glad.theme.Css;
import io.github.gleidsonmt.glad.theme.Neutral;
import io.github.gleidsonmt.glad.theme.ThemeProvider;
import io.github.gleidsonmt.presentation.Code;
import io.github.gleidsonmt.presentation.TreeTitle;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Orientation;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.input.Clipboard;
import javafx.scene.input.ClipboardContent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;
import javafx.scene.shape.SVGPath;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import org.jetbrains.annotations.NotNull;

import java.awt.*;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.Optional;
import java.util.Timer;
import java.util.TimerTask;

/**
 * @author Gleidson Neves da Silveira | gleidisonmt@gmail.com
 * Create on  15/03/2025
 */
public class TutorialUtils {

    public static @NotNull Node createAction(EventHandler<MouseEvent> event) {
        return createAction("Try on!", event);
    }

    public static @NotNull String installExample(Css css, String code) {
//        ThemeProvider.install(scene,
//                Css.COLORS,
//                Css.PROPERTIES);
        return installExample(css, code, "");
    }

    public static @NotNull String installExample(Css css, String code, String con) {
//        ThemeProvider.install(scene,
//                Css.COLORS,
//                Css.PROPERTIES);
        return "// Install theme on scene\nThemeProvider.install(scene,\n\t\t...\n\t\tCss." + css + ");\n\n//Constructor\n"
               + code + " " + css.toString().toLowerCase() + " = new " + code + "(" + con + ");";
    }

    public static @NotNull String installExample(Css css) {
//        ThemeProvider.install(scene,
//                Css.COLORS,
//                Css.PROPERTIES);
        return "// Install theme on scene\nThemeProvider.install(scene,\n\t\t...\n\t\tCss." + css + ");";
    }

    public static @NotNull String installExample(Css... css) {
        StringBuilder build = new StringBuilder();
        for (Css c : css) {
            build.append("\n\t\tCss.").append(c).append(",");
//            \n\t\t...\n\t\tCss." + css +
        }
        build.deleteCharAt(build.length() - 1);
//        ThemeProvider.install(scene,
//                Css.COLORS,
//                Css.PROPERTIES);
        return "// Install theme on scene\nThemeProvider.install(scene, \n\t\t...\t\t" + build + ");";
    }

    public static @NotNull Node createAction(String placeholder, EventHandler<MouseEvent> event) {
        Button button = new Button(placeholder);
        button.setPadding(new Insets(10));
        VBox.setMargin(button, new Insets(20));
        button.setOnMouseClicked(event);
        return button;
    }

    public static @NotNull Node createCardLink(String placeholder, String uri) {
        try {
            return createCardLink(placeholder, new URI(uri));
        } catch (URISyntaxException e) {
            throw new RuntimeException(e);
        }
    }

    public static @NotNull Node createCardLink(String placeholder, URI uri) {
        SVGPath path = createSVG();
        HBox box = new HBox();
        box.getStyleClass().addAll("border-2", "border-light-gray-2", "padding-10", "radius-10");
        VBox.setMargin(box, new Insets(10, 0, 10, 0));
//        SVGIcon icon = new SVGIcon(Icon.ARROW_RIGHT_ALT);
//        hyperlink.setGraphic(icon);
        Hyperlink hyperlink = createLink(placeholder, uri);
        hyperlink.setContentDisplay(ContentDisplay.RIGHT);
        hyperlink.setGraphicTextGap(10);
        hyperlink.getStyleClass().addAll("font-instagram h5".split(" "));
        box.getChildren().addAll(path, new Separator(Orientation.VERTICAL), hyperlink);
        box.setSpacing(10);
        return box;
    }

    private static @NotNull SVGPath createSVG() {
        SVGPath path = new SVGPath();
        path.setContent("M12 0c-6.626 0-12 5.373-12 12 0 5.302 3.438 9.8 8.207 11.387.599.111.793-.261.793-.577v-2.234c-3.338.726-4.033-1.416-4.033-1.416-.546-1.387-1.333-1.756-1.333-1.756-1.089-.745.083-.729.083-.729 1.205.084 1.839 1.237 1.839 1.237 1.07 1.834 2.807 1.304 3.492.997.107-.775.418-1.305.762-1.604-2.665-.305-5.467-1.334-5.467-5.931 0-1.311.469-2.381 1.236-3.221-.124-.303-.535-1.524.117-3.176 0 0 1.008-.322 3.301 1.23.957-.266 1.983-.399 3.003-.404 1.02.005 2.047.138 3.006.404 2.291-1.552 3.297-1.23 3.297-1.23.653 1.653.242 2.874.118 3.176.77.84 1.235 1.911 1.235 3.221 0 4.609-2.807 5.624-5.479 5.921.43.372.823 1.102.823 2.222v3.293c0 .319.192.694.801.576 4.765-1.589 8.199-6.086 8.199-11.386 0-6.627-5.373-12-12-12z");
        path.setStyle("-fx-fill: -text-color;");
        return path;
    }

    public static @NotNull Hyperlink createLink(String placeholder, URI uri) {
        Hyperlink hyperlink = new Hyperlink(placeholder);
        hyperlink.setOnAction(_ -> {
            try {
                Desktop.getDesktop().browse(uri);
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
        });
        return hyperlink;
    }

    public static Node createTextWithLink(String text, String placeholder, String moduleName) {
        return createTextWithLink(text, placeholder, moduleName, null);
    }

    public static Node createTextWithLink(String _text, String placeholder, String moduleName, String topic) {
        Text text = new Text(_text);
        HBox box = new HBox(
                text,
                createLink(placeholder, moduleName, topic)
        );
        box.getStyleClass().add("h5");

        box.setAlignment(Pos.CENTER_LEFT);
        return box;
    }

    public static void putTextOnClipboard(String text) {
        Clipboard clipboard = Clipboard.getSystemClipboard();
        ClipboardContent _content = new ClipboardContent();
        _content.putString(text);
        clipboard.setContent(_content);
    }

    public static @NotNull Hyperlink createLink(String placeholder, String moduleName, String topic) {
        Hyperlink hyperlink = new Hyperlink(placeholder);
        hyperlink.setOnAction(_ -> {
            SideNav drawer = (SideNav) hyperlink.getScene().lookup("#drawer");
            Module moduleImpl = drawer.find(moduleName);
            drawer.currentModuleProperty().set(moduleImpl);

            TimerTask timerTask = new TimerTask() {
                @Override
                public void run() {
                    Optional<Node> optional = drawer.getScene().getRoot().lookupAll("#tutorial-scroll").stream().findFirst();
                    if (optional.isPresent() && optional.get() instanceof ScrollPane scroll) {
                        VBox box = (VBox) scroll.getContent();

                        Optional<TreeTitle> opt = box.getChildren().stream()
                                .filter(el -> el instanceof TreeTitle)
                                .map(el -> (TreeTitle) el)
                                .filter(el -> el.getText().equals(topic))
                                .findAny();
                        opt.ifPresent(e -> Scroll.scrollTo(scroll, e));

                        BorderPane border = (BorderPane) drawer.getScene().getRoot().lookup("#tutorial-body");
                        Tutorial tutorial = (Tutorial) border.getUserData();
                        tutorial.select(topic);
                    }
                }
            };
//
            Timer timer = new Timer();
            timer.schedule(timerTask, 50);
        });
        return hyperlink;
    }


    public static @NotNull Hyperlink createLink(String placeholder, String moduleName) {
        Hyperlink hyperlink = new Hyperlink(placeholder);
        hyperlink.getStyleClass().addAll("h5");
        hyperlink.setOnAction(_ -> {
            SideNav drawer = (SideNav) hyperlink.getScene().lookup("#drawer");
            Module moduleImpl = drawer.find(moduleName);
//            drawer.currentModuleProperty().set(moduleImpl);
            drawer.select(moduleImpl);
//            drawer.navigate(moduleName);
        });
        return hyperlink;
    }

    public static void showPage(Parent node, Neutral... css) {

        Stage stage = new Stage();
        Scene scene = new Scene(node, 1000, 800);
        ThemeProvider.install(scene, Css.ALL);
        stage.setScene(scene);
        stage.show();

    }

    public static Node createCodeOption(Node node, String code) {
        return createCodeOption(node, new Code(code));
    }
    public static Node createCodeOption(Node node, Code... code) {

        VBox container = new VBox();
//        container.setFillWidth(false);
        container.setSpacing(20);
        ToggleButton nodeOption = new ToggleButton("Preview");
//        nodeOption.getStyleClass().addAll( "w-100", "min-h-40", "btn-outlined", "round");
        ToggleButton codeOption = new ToggleButton("Show code");
//        codeOption.getStyleClass().addAll( "w-100", "min-h-40","btn-outlined", "round");
        HBox optionsContainer = new HBox(nodeOption, codeOption);
        optionsContainer.getStyleClass().addAll("min-h-40 bg-light-gray align-center padding-5 radius-10".split(" "));
        optionsContainer.setMaxWidth(Region.USE_PREF_SIZE);

//        optionsContainer.getStyleClass().addAll("w-300", "min-h-40", "border-2", "border-light-gray-2", "padding-10", "radius-10");
        optionsContainer.setSpacing(5);

        ResizablePane preview = new ResizablePane(node);
//        preview.setMaxWidth(-1);

//        BlockCode blockCode = new BlockCode()
//                .content(code)
//                .build();

        VBox.setVgrow(container, Priority.ALWAYS);
        VBox.setVgrow(preview, Priority.ALWAYS);
//        VBox.setVgrow(blockCode, Priority.ALWAYS);


        ToggleGroup group = new ToggleGroup();
        group.getToggles().addAll(nodeOption, codeOption);
        group.selectToggle(nodeOption);

        final Node blockOption;
        if (code.length > 1) {
            TabPane blockView = new TabPane();
            for (Code c : code) {
                Tab tab = new Tab(c.name());
                BlockCode blockCode = new BlockCode()
                        .content(c.content())
                        .codeType(c.type())
                        .build();
                tab.setContent(blockCode);
                blockView.getTabs().add(tab);
            }
            blockOption = blockView;
        } else {
            blockOption = new BlockCode()
                    .content(code[0].content())
                    .codeType(code[0].type())
                    .build();
        }

        VBox.setVgrow(blockOption, Priority.ALWAYS);

        group.selectedToggleProperty().addListener((_, _, newValue) -> {
            if (newValue == codeOption) {
                container.getChildren().set(1, blockOption);
            } else {
                container.getChildren().set(1, preview);
            }
        });

        container.getChildren().addAll(optionsContainer, preview);

        return container;
    }
}
