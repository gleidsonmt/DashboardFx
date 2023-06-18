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
import io.github.gleidsonmt.dashboardfx.core.controls.icon.IconContainer;
import io.github.gleidsonmt.dashboardfx.core.controls.icon.Icons;
import io.github.gleidsonmt.dashboardfx.core.view.layout.BlockCode;
import io.github.gleidsonmt.dashboardfx.core.view.layout.DialogContainer;
import io.github.gleidsonmt.dashboardfx.core.view.layout.options.ActionOptions;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
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
import javafx.scene.layout.*;
import javafx.scene.text.Text;
import javafx.scene.text.TextFlow;
import javafx.scene.web.WebView;
import org.jetbrains.annotations.ApiStatus;
import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;

import java.net.URI;
import java.net.URL;
import java.util.List;
import java.util.function.Supplier;

/**
 * Base class to create imperative presentations.
 * Used to create view without fxml and to fast way to create presentation.
 * It's a top and down blocks. Which blocks represents one method inside
 * this class.
 *      Ex. the method title(String title). creates a label, if I put another method
 * in sequence two titles will be created in a vbox layout.
 *      More: this.title("One").title("Two").build(); creates a vbos with two titles.
 * if I want a text between ones -
 *      this.title("One").text("between").title("Two").build();
 * indented:
 *      this.title("One)
 *          .text("Between")
 *          .title("Two")
 *          .build().
 * Create a node as like a document with sections and blocks.
 * @author Gleidson Neves da Silveira | gleidisonmt@gmail.com
 * Create on  22/01/2023
 */

@ApiStatus.AvailableSince("1.0")
@SuppressWarnings("unused")
public class PresentationCreator
        implements BuildCreator {

    //Top and down root
    private final VBox body = new VBox();
    // items for add in tree
    protected ObservableList<Node> items;
    // context for actions
    protected final Context context;
    // base from nodes
    protected final StackPane root;

    public PresentationCreator(Context _context) {
        this.context = _context;
        this.root = new StackPane();
        items = FXCollections.observableArrayList();

        ScrollPane scroll = new ScrollPane();
        this.root.getChildren().setAll(scroll);
//        scroll.setContent(body);

        scroll.setFitToHeight(true);
        scroll.setFitToWidth(true);

        body.setPadding(new Insets(0,30,30,30));
        body.setSpacing(10);
    }

    private String title = null;

    /**
     * Create a title in tree.
     * @param _title the name of the title
     * @return The Presentation.
     */
    public PresentationCreator title(String _title) {
        if (title == null) title = _title;
        items.add(createTitle(_title, null, null));
        return this;
    }

    /**
     *  Create a title and set its a parent.
     * @param _title the name of title
     * @param parent the first parent.
     * @return The Presentation
     */
    public PresentationCreator title(String _title, String parent) {
        if (title == null) title = _title;
        items.add(createTitle(_title, parent, null));
        return this;
    }

    public PresentationCreator title(String _title, List<String> options) {
        if (title == null) title = _title;
        items.add(createTitle(_title, null, options));
        return this;
    }

    public PresentationCreator title(String _title, String parent, List<String> options) {
        if (title == null) title = _title;
        items.add(createTitle(_title, parent, options));
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

    public PresentationCreator text(String text, String... options) {
        items.add(createText(text, options));
        return this;
    }

    public PresentationCreator legend(String legend) {
        items.add(createLabel(legend, "text-12", "text-bold"));
        return this;
    }

    public PresentationCreator separator() {
        items.add(new Separator());
        return this;
    }

    public PresentationCreator code(String text) {
        items.add(createBlockCode(text, "java"));
        return this;
    }

    public PresentationCreator code(String text, String language) {
        items.add(createBlockCode(text, language));
        return this;
    }

    public PresentationCreator youTube(String url, URL resource) {

//        Region region = (Region) createImage(
//                new Image(resource.toExternalForm())
//        );
//
//        region.setPrefSize(300, 300);

        StackPane root = new StackPane();
        WebView webView = new WebView();


//        webView.getEngine().load("https://www.youtube.com/watch?v=maX5ymmQixM");
//        webView.setMinSize(200, 200);

        Button button = new Button("Open Player");

        button.setOnMouseClicked(event -> {

            double width = context.stage().getWidth()
                    > 700 ? 700 : context.stage().getWidth() - 100 ;

            double height = context.stage().getHeight()
                    > 500 ? 500 : context.stage().getHeight() - 200 ;

            webView.getEngine().setJavaScriptEnabled(true);

            webView.getEngine().loadContent(
                "<!DOCTYPE html>" +
                "<html lang=\"en\">" +
                    "<body>" +
                        "<iframe width=\"" + width + "\" height=\"" + height + "\"" +
                            """
                            src="https://www.youtube.com/embed/maX5ymmQixM"
                            title="JavaFX UI: iOS Style Toggle Switch"
                            frameborder="0" allow="accelerometer;
                            autoplay; clipboard-write; encrypted-media;
                            gyroscope; picture-in-picture; web-share" allowfullscreen>
                        </iframe>
                            """ +
                    "</body>" +
                "</html>"
            );

            webView.getEngine().getLoadWorker().stateProperty()
                    .addListener((obs, oldValue, newValue) -> {
                        context.logger().info(
                                newValue.name()
                        );
                    });

            context.wrapper()
                    .content(
                            new DialogContainer(webView)
                                    .size(width + 50, height + 45)
                    )
                    .pos(Pos.CENTER)
                    .show();
        });
//        items.add(webView);
        items.add(button);
        return this;
    }

    @ApiStatus.Experimental
    public PresentationCreator table(@NotNull TableCreator<?> table) {
        VBox.setMargin(table.getRoot(), new Insets(10, 0, 10,0));
        items.add(table.getRoot());
        return this;
    }

    @ApiStatus.Experimental
    public PresentationCreator options(ActionOptions... options) {
        items.add(createOptions(options));
        return this;
    }

    public PresentationCreator footer(Author... authors) {
        items.add(createFooter(authors));
        return this;
    }

    public PresentationCreator footer(@NotNull ObservableList<Author> authors) {
        for(Author author : authors) {
            items.add(createFooter(author));
        }
        return this;
    }


    @ApiStatus.OverrideOnly
    public ObservableList<Author> createDefaultControl() {
        return FXCollections.observableArrayList(
            new Author("OpenJFX",
                    "https://github.com/openjfx/openjfx.github.io",
                    "https://openjfx.io/javadoc/17/javafx.controls/javafx/scene/control/"+title+".html")
            );
    }

    @ApiStatus.OverrideOnly
    public Author createDefaultAuthor() {
       return new Author("OpenJFX",
                        "https://github.com/openjfx/openjfx.github.io",
                        "https://openjfx.io/javadoc/17/javafx.controls/javafx/scene/control/"+title+".html");
    }

    @ApiStatus.Internal
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
            hp.setOnAction(event -> context.openLink(author.getGitUrl()));
            body.getChildren().add(hp);

            if (author.getDocumentation() != null) {
                Hyperlink h = new Hyperlink();
                h.setText(" / Documentation");
                h.setOnAction(event -> context.openLink(author.getDocumentation()));
                HBox.setMargin(h, new Insets(5, 0, 0, 0));
                h.setGraphicTextGap(10);
                body.getChildren().add(h);
            }

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

    public PresentationCreator demonstration(Node node, String java, String fxml) {
        items.add(createTabs(List.of(node), java, fxml, null));
        return this;
    }

//    public PresentationCreator demonstration(Node node, String java, String fxml) {
//        items.add(createTabs(node, java, fxml, null));
//        return this;
//    }

    public PresentationCreator demonstration(List<Node> nodes, String java, String fxml) {
        items.add(createTabs(nodes, java, fxml, null));
        return this;
    }

    public PresentationCreator demonstration(List<Node> nodes, String java, String fxml, String css) {
        items.add(createTabs(nodes, java, fxml, css));
        return this;
    }

    @ApiStatus.Internal
    private Node createTabs(List<Node> list, String java, String fxml, String css) {

        TabPane tabPane = new TabPane();
        VBox.setVgrow(tabPane, Priority.ALWAYS);
        tabPane.setMinHeight(400);
        Tab javaTab = new Tab("Java");
        Tab nodeTab = new Tab("Node");
        tabPane.setTabClosingPolicy(TabPane.TabClosingPolicy.UNAVAILABLE);
        javaTab.setContent(createBlockCode(java, "java"));
        FlowPane root = new FlowPane();
        root.setPadding(new Insets(20));
        root.setVgap(10);
        root.setHgap(10);
//        root.setSpacing(20);
        root.setAlignment(Pos.CENTER);
        root.getChildren().setAll(list);
        root.setAlignment(Pos.CENTER);
        nodeTab.setContent(root);
        tabPane.getTabs().setAll(nodeTab, javaTab);

        if (!fxml.isEmpty()) {
            Tab fxmlTab = new Tab("FXML");
            fxmlTab.setContent(createBlockCode(fxml, "html"));
            tabPane.getTabs().add(fxmlTab);
        }

        if(css != null && !css.isEmpty()) {
            Tab cssTab = new Tab("CSS");
            cssTab.setContent(createBlockCode(css, "css"));
            tabPane.getTabs().add(cssTab);
        }

        root.getStyleClass().addAll("border-light-gray-2", "border-1", "depth-2");
        root.setStyle("-fx-background-color: -light-gray;");
        return tabPane;
    }



    @ApiStatus.Internal
    private StackPane createBlockCode(String text, String language) {
        return new BlockCode(context, text, language);
    }

    @ApiStatus.Internal
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

    @ApiStatus.Internal
    private GNButton createButton(String title, EventHandler<ActionEvent> actionEventEventHandler) {
        GNButton gn = new GNButton(title);
        gn.setDefaultButton(true);
        gn.setOnAction(actionEventEventHandler);
        return gn;
    }

    private @NotNull Label createSubTitle(String title) {
        return createLabel(title, "subtitle", "h6", "text-bold");
    }

    private @NotNull Label createTitle(String title, String related, List<String> options) {

        TreeTitle label = new TreeTitle(title);
        label.getStyleClass().addAll("title", "h4");

        if (related != null) {
            label.setRelated(new TreeTitle(related));
        }

        if (options != null) label.getStyleClass().addAll(options);
        VBox.setMargin(label, new Insets(0, 0, 10, 0));

        return label;

    }

    private @NotNull Label createLabel(String text, String... options) {
        Label label = new Label(text);
        label.getStyleClass().addAll(options);
        VBox.setMargin(label, new Insets(0, 0, 20, 0));
        return label;
    }

    private @NotNull Node createImage(Image image) {
        Region region = new Region();
//        region.setMinSize(image.getWidth(), image.getHeight());
        region.setMinHeight(image.getHeight());
        region.setBackground(
                new Background(
                        new BackgroundImage(
                                image,
                                BackgroundRepeat.NO_REPEAT,
                                BackgroundRepeat.NO_REPEAT,
                                BackgroundPosition.DEFAULT,
                                new BackgroundSize(300, 300, true, true, true, false)
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

    private @NotNull TextFlow createText(String _text, String... options) {
        Text text = new Text(_text);
        text.getStyleClass().add("text-14");
        TextFlow flow = new TextFlow(text);

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

    @Override
    public PresentationCreator build() {
        if (items.stream().noneMatch(n-> n.getStyleClass().contains("title"))) {
            body.setPadding(new Insets(30, 30, 30, 30));
        }
        body.getChildren().setAll(items);
        return this;
    }

    @Override
    public Node getRoot() {
        return root;
    }


}


