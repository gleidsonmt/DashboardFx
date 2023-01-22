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

package io.github.gleidsonmt.dashboardfx.core.layout.conteiners.creators;

import io.github.gleidsonmt.dashboardfx.controllers.BlockHtmlParser;
import io.github.gleidsonmt.dashboardfx.core.app.interfaces.View;
import io.github.gleidsonmt.dashboardfx.core.app.material.controls.BuildCreator;
import io.github.gleidsonmt.dashboardfx.core.app.services.Context;
import io.github.gleidsonmt.dashboardfx.core.controls.GNBadge;
import io.github.gleidsonmt.dashboardfx.core.layout.conteiners.options.ActionOptions;
import io.github.gleidsonmt.dashboardfx.core.layout.conteiners.options.SnackColors;
import io.github.gleidsonmt.gncontrols.controls.GNButton;
import io.github.gleidsonmt.gncontrols.material.icon.IconContainer;
import io.github.gleidsonmt.gncontrols.material.icon.Icons;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Orientation;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.input.Clipboard;
import javafx.scene.input.ClipboardContent;
import javafx.scene.layout.*;
import javafx.scene.shape.SVGPath;
import javafx.scene.text.Text;
import javafx.scene.text.TextFlow;
import javafx.scene.web.WebView;
import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;

import java.util.List;

public class PresentationCreator extends Container implements BuildCreator {

    private final ScrollPane scroll = new ScrollPane();
    private final VBox body = new VBox();

    private String name;
    private List<String> sections = List.of();
    private final List<Label> titles = List.of();
    protected ObservableList<Node> items = FXCollections.observableArrayList();

    private Context context;

    public PresentationCreator(String _name, Context _context) {
        super(_name);
        this.name = _name;
        this.context = _context;

        this.getChildren().setAll(scroll);
        scroll.setContent(body);

        scroll.setFitToHeight(true);
        scroll.setFitToWidth(true);

        body.setPadding(new Insets(0,30,30,30));
        body.setSpacing(10);
    }

    public PresentationCreator title(String title) {
        items.add(createTitle(title));
        return this;
    }

    public PresentationCreator subTitle(String title) {
        items.add(createSubTitle(title));
        return this;
    }
    public PresentationCreator image(Image image) {
        items.add(createImage(image));
        return this;
    }

    public PresentationCreator text(String text) {
        items.add(createText(text));
        return this;
    }

    public PresentationCreator text(String text, String... style) {
        items.add(createText(text, style));
        return this;
    }

    public PresentationCreator options(ActionOptions... options) {
        items.add(createOptions(options));
        return this;
    }

    public PresentationCreator footer(Author... authors) {
        items.add(createFooter(authors));
        return this;
    }

    private Node createFooter(Author... authors) {
        VBox root = new VBox();

        Label title = new Label("Author" + (authors.length > 1 ? "s" : ""));
        Separator separator = new Separator();
        HBox.setHgrow(separator, Priority.ALWAYS);
        HBox.setMargin(separator, new Insets(5, 0, 0, 0));

        HBox header = new HBox();
        header.setAlignment(Pos.CENTER);
        header.getChildren().setAll(title, separator);

        HBox body = new HBox();
        for (Author author : authors) {
            Hyperlink hp = new Hyperlink();
            hp.setGraphic(new IconContainer(Icons.GITHUB));
            hp.setText(author.getName());
            hp.setGraphicTextGap(10);
//            hp.setOnAction(event -> context.openLink(author.getGitUrl()));
            body.getChildren().add(hp);

            if (authors.length > 1) {
                Separator sep = new Separator();
                sep.setOrientation(Orientation.VERTICAL);
                HBox.setMargin(sep, new Insets(0, 10, 0, 10));
                body.getChildren().add(sep);
            }
        }
        root.getChildren().setAll(header, body);
        root.setPadding(new Insets(10));
        return root;
    }

    public PresentationCreator separator() {
        items.add(new Separator());
        return this;
    }

    public PresentationCreator code(String text) {
        items.add(createBlockCode(text, false));
        return this;
    }

    public PresentationCreator multCode(Node node, String java, String fxml) {
        items.add(createMultBlock(node, java, fxml));
        return this;
    }

    public PresentationCreator multCode(List<Node> nodes, String java, String fxml) {
        items.add(createMultBlock(nodes, java, fxml));
        return this;
    }

    private Node createMultBlock(List<Node> list, String java, String fxml) {

        TabPane tabPane = new TabPane();
        tabPane.setMinHeight(400);
        Tab javaTab = new Tab("Java");
        Tab fxmlTab = new Tab("FXML");
        Tab nodeTab = new Tab("Node");
        tabPane.setTabClosingPolicy(TabPane.TabClosingPolicy.UNAVAILABLE);
        javaTab.setContent(createBlockCode(java, false));
        fxmlTab.setContent(createBlockCode(fxml, true));
        HBox root = new HBox();
        root.setPadding(new Insets(20));
        root.setSpacing(20);
        root.setAlignment(Pos.CENTER);
        root.getChildren().setAll(list);
        root.setAlignment(Pos.CENTER);
        nodeTab.setContent(root);
        tabPane.getTabs().setAll(nodeTab, javaTab, fxmlTab);
        root.getStyleClass().addAll("border-light-gray-2", "border-1");
        return tabPane;
    }

    private Node createMultBlock(Node node, String java, String fxml) {
        return createMultBlock(List.of(node), java, fxml);
    }

    private StackPane createBlockCode(String text, boolean fxml) {
        StackPane root = new StackPane();
        root.setMinHeight(150);
        root.setAlignment(Pos.TOP_RIGHT);
        GNButton btn = new GNButton();
        btn.setPrefSize(37, 30);
        SVGPath path = new SVGPath();
        btn.setContentDisplay(ContentDisplay.GRAPHIC_ONLY);
        path.setContent("M5 22q-.825 0-1.413-.587Q3 20.825 3 20V6h2v14h11v2Zm4-4q-.825 0-1.412-.587Q7 16.825 7 16V4q0-.825.588-1.413Q8.175 2 9 2h9q.825 0 1.413.587Q20 3.175 20 4v12q0 .825-.587 1.413Q18.825 18 18 18Zm0-2h9V4H9v12Zm0 0V4v12Z");
        btn.getStyleClass().add("btn-flat");
        btn.setStyle("" +
                "-fx-background-color : white;" +
                "-fx-border-color :  -medium-gray;" +
                "-fx-border-width :  1;"
        );
        btn.setGraphic(path);
        root.setStyle("-fx-border-color : -light-gray-2;");
        WebView webView = new WebView();
        webView.setContextMenuEnabled(false);
//        webView.setMouseTransparent(true);
        if (fxml) {
            webView.getEngine().loadContent(
                    new BlockHtmlParser().javaStringToFxml(text)
            );
        } else {
            webView.getEngine().loadContent(
                    new BlockHtmlParser().javaStringToHtml(text)
            );
        }
        webView.setOnScroll(event -> {
//            System.out.println(event.getDeltaY());
//            System.out.println("event.getTotalDeltaY() = " + event.getTotalDeltaY());
//            System.out.println("event.getTextDeltaY() = " + event.getTextDeltaY());
//            if (event.getDeltaY() > 0) {
//                event.getTarget();
//            }
        });
        root.getChildren().addAll(webView, btn);

        btn.setOnAction(event -> {
            ClipboardContent content = new ClipboardContent();
            content.putString(text);
            content.putHtml("<b>Bold</b> text");
            Clipboard.getSystemClipboard().setContent(content);

            context.root()
                    .createSnackBar()
                    .icon(new IconContainer(Icons.DONE))
                    .color(SnackColors.SUCCESS)
                    .message("Copied!")
                    .show();

        });
        return root;
    }

    private TilePane createOptions(ActionOptions... options) {
        TilePane layout = new TilePane();
        layout.setHgap(10);
        layout.setVgap(10);

        for (ActionOptions option : options) {
            GNButton btn = createButton(option.getName(), option.getAction());
            btn.setStyle(option.getStyle());
            layout.getChildren().add(btn);
        }
        return layout;
    }

    private GNButton createButton(String title, EventHandler<ActionEvent> actionEventEventHandler) {
        GNButton gn = new GNButton(title);
        gn.setDefaultButton(true);
        gn.setOnAction(actionEventEventHandler);
        return gn;
    }

    private @NotNull Label createTitle(String title) {
        return createLabel(title, "title", "h4");
    }

    private @NotNull Label createText(String title, String... styles) {
        return createLabel(title, styles);
    }

    private @NotNull Label createSubTitle(String title) {
        return createLabel(title, "title", "h6");
    }

    private @NotNull Label createLabel(String text, String... clazz) {
        Label label = new Label(text);
        label.getStyleClass().addAll(clazz);
        return label;
    }

    private @NotNull Node createImage(Image image) {
        Region region = new Region();
        region.setMinSize(image.getWidth(), image.getHeight());
        region.setBackground(
                new Background(
                        new BackgroundImage(
                                image,
                                BackgroundRepeat.NO_REPEAT,
                                BackgroundRepeat.NO_REPEAT,
                                BackgroundPosition.DEFAULT,
                                BackgroundSize.DEFAULT
//                                new BackgroundSize(100, 100, true, true, true, true)
                        )
                )
        );
        return region;
    }

    @Contract("_ -> new")
    private @NotNull TextFlow createText(String _text) {
        Text text = new Text(_text);
        text.getStyleClass().addAll("text-14");
        return new TextFlow(text);
    }

    @Override
    public View build() {
        body.getChildren().setAll(items);
        return this;
    }



}


