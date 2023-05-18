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
import io.github.gleidsonmt.dashboardfx.core.view.layout.options.ActionOptions;
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
import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;

import java.util.List;

@SuppressWarnings("unused")
public class PresentationCreator extends StackPane implements BuildCreator {

    private final VBox body = new VBox();
    protected ObservableList<Node> items = FXCollections.observableArrayList();
    private final Context context;

    public PresentationCreator(Context _context) {
        this.context = _context;

        ScrollPane scroll = new ScrollPane();
        this.getChildren().setAll(scroll);
        scroll.setContent(body);

        scroll.setFitToHeight(true);
        scroll.setFitToWidth(true);

        body.setPadding(new Insets(0,30,30,30));
        body.setSpacing(10);
    }

    private String title = null;
    public PresentationCreator title(String _title) {
        if (title == null) title = _title;
        items.add(createTitle(_title));
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

    public PresentationCreator footer(ObservableList<Author> authors) {
        for(Author author : authors) {
            items.add(createFooter(author));
        }
        return this;
    }

    public ObservableList<Author> createDefaultControl() {
        return FXCollections.observableArrayList(
            new Author("OpenJFX",
                    "https://github.com/openjfx/openjfx.github.io",
                    "https://openjfx.io/javadoc/17/javafx.controls/javafx/scene/control/"+title+".html")
            );
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

        root.getStyleClass().addAll("border-light-gray-2", "border-1");
        return tabPane;
    }

    private Node createMultBlock(Node node, String java, String fxml) {
        return createMultBlock(List.of(node), java, fxml);
    }

    private StackPane createBlockCode(String text, String language) {
        return new BlockCode(context, text, language);
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
        return createLabel(title, "subtitle", "h6", "text-bold");
    }

    private @NotNull Label createLabel(String text, String... clazz) {
        LabelPosition label = new LabelPosition(text);
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
    public Node build() {
        if (items.stream().noneMatch(n-> n.getStyleClass().contains("title"))) {
            body.setPadding(new Insets(30, 30, 30, 30));
        }
        body.getChildren().setAll(items);
        return this;
    }

    @Override
    public StackPane getRoot() {
        return this;
    }


}


