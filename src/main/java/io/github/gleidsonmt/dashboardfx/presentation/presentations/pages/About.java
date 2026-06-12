package io.github.gleidsonmt.dashboardfx.presentation.presentations.pages;


import io.github.gleidsonmt.dashboardfx.App;
import io.github.gleidsonmt.dashboardfx.model.Member;
import io.github.gleidsonmt.dashboardfx.utils.Assets;
import io.github.gleidsonmt.glad.controls.avatar.AvatarView;
import javafx.geometry.HPos;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.ScrollPane;
import javafx.scene.image.Image;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Priority;
import javafx.scene.layout.TilePane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.scene.text.TextFlow;
import org.yaml.snakeyaml.LoaderOptions;
import org.yaml.snakeyaml.Yaml;
import org.yaml.snakeyaml.constructor.Constructor;
import org.yaml.snakeyaml.inspector.TagInspector;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * @author Gleidson Neves da Silveira | gleidisonmt@gmail.com
 * Create on  01/10/2025
 */
public class About extends ScrollPane {

    private final VBox body = new VBox();

    public About() {
        getStyleClass().addAll("fit-width".split(" "));
        body.getStyleClass().addAll("padding-30 align-center".split(" "));
        setContent(body);
        body.setSpacing(20);
        body.getChildren().add(createMissionHeader());
        body.getChildren().add(createTeamBox());
    }

    private Node createTeamBox() {
        VBox container = new VBox();
        container.setSpacing(20);
        var title = new Text("Our Team");
        title.getStyleClass().addAll("h2", "text-bold", "font-instagram");
        container.getChildren().setAll(title, createFlowText("Sit facilis neque ab nulla vel. Cum eos in laudantium. Temporibus eos totam in dolorum. Nemo vel facere repellendus ut eos dolores similique.", "h4"));

        TilePane tilePane = new TilePane();

        try {

            parseYaml().forEach(member -> {
                AvatarView avatarView = new AvatarView(Assets.getImage(member.getImg()), 100,100);
                var text = new Text(member.getName());
                text.getStyleClass().addAll("h5", "bold");
                var legend = new Text(member.getLegend());
                text.getStyleClass().addAll("h5");
                VBox box = new VBox(avatarView, text, legend);
                box.setAlignment(Pos.CENTER);
                tilePane.getChildren().add(box);
            });
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        container.getChildren().add(tilePane);
        return container;
    }

    public List<Member> parseYaml() throws IOException {
        var loaderoptions = new LoaderOptions();
        TagInspector taginspector =
                tag -> tag.getClassName().equals(Member.class.getName());
        loaderoptions.setTagInspector(taginspector);

        Yaml yaml = new Yaml(new Constructor(Member.class, loaderoptions));

        InputStream inputStream = App.class.getResourceAsStream("yaml/members.yml");
        List<Member> members = new ArrayList<>();
        var load = yaml.loadAll(inputStream);
        while (load.iterator().hasNext()) {
            members.add((Member) load.iterator().next());
        }
        return members;
    }

    private Node createMissionHeader() {
        GridPane grid = new GridPane();

        Text title = new Text("Our Mission");
        title.getStyleClass().addAll("h2", "text-bold", "font-instagram");
        grid.setHgap(10);
        grid.setVgap(10);
        grid.add(title, 0, 0);
        grid.add(createFlowText("Our mission is to provide a platform for people to share their dietary information and to help them to achieve their goals.", "h4"), 0, 1);
        grid.add(createFlowText("Our mission is to provide a platform for people to share their dietary information and to help them to achieve their goals.", "h4"), 0, 2);
        grid.add(createFlowText("Our mission is to provide a platform for people to share their dietary information and to help them to achieve their goals.", "h4"), 0, 3);

        var box = createBox();

//        grid.add(box, 1, 0, 1, GridPane.REMAINING);
        GridPane.setHalignment(title, HPos.LEFT);
        grid.setHgap(40);
//        GridPane.setHgrow(title, Priority.ALWAYS);

        return grid;
    }

    private VBox createBox() {
        VBox box = new VBox();
        box.setSpacing(10);
        box.setAlignment(Pos.CENTER);

        Text transactions = new Text("44 million");
        transactions.getStyleClass().addAll("h3", "text-bold", "bold");

        Text transactionsLegend = new Text("Transactions every 24 hours");
        transactionsLegend.getStyleClass().addAll("h5");

        Text assets = new Text("$119 trillion");
        assets.getStyleClass().addAll("h3", "bold");

        Text assetsLegend = new Text("Assets under holding");
        assetsLegend.getStyleClass().addAll("h5");

        Text newUsers = new Text("46,000");
        newUsers.getStyleClass().addAll("h3", "bold");

        Text newUserLegend = new Text("New users annually");
        newUserLegend.getStyleClass().addAll("h5");

        box.getChildren().setAll(transactions, transactionsLegend, assets, assetsLegend, newUsers, newUserLegend);
        return box;
    }

    private TextFlow createFlowText(String text, String... classes) {
        Text content = new Text(text);
        TextFlow textFlow = new TextFlow(content);
        textFlow.getStyleClass().addAll(classes);
        return textFlow;
    }

}


