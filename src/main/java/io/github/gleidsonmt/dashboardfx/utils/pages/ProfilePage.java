package io.github.gleidsonmt.dashboardfx.utils.pages;

import io.github.gleidsonmt.dashboardfx.dashboard.ActionableView;
import io.github.gleidsonmt.dashboardfx.utils.Assets;
import io.github.gleidsonmt.glad.base.Root;
import io.github.gleidsonmt.glad.base.internal.View;
import io.github.gleidsonmt.glad.base.responsive.Break;
import io.github.gleidsonmt.glad.controls.avatar.AvatarView;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.Separator;
import javafx.scene.layout.*;
import javafx.scene.shape.Circle;
import javafx.scene.text.Text;

/**
 * @author Gleidson Neves da Silveira | gleidisonmt@gmail.com
 * Create on  23/08/2025
 */
public class ProfilePage extends View implements ActionableView {

    private final ScrollPane container = new ScrollPane();
    private final GridPane body = new GridPane();

    public ProfilePage() {
        super("Profile");
        init();
        configLayout();
    }

    private void init() {
        setContent(container);
        container.setContent(body);
        body.setHgap(20);
        body.setVgap(20);
    }

    @Override
    public void onEnter(Root root) {

        var header = new Header();
        var blockOne = new Block(new AvatarBlock());
        var blockTwo = new Block(new DetailsBlock());
        var footer = new Block(new Footer());

        body.getChildren().setAll(header, blockOne, blockTwo, footer);

        GridPane.setVgrow(blockOne, Priority.ALWAYS);
        GridPane.setVgrow(footer, Priority.ALWAYS);
        GridPane.setColumnSpan(footer, GridPane.REMAINING);

        root.addPoint(e -> {
            GridPane.setConstraints(header, 0, 0);
            GridPane.setConstraints(blockOne, 0, 1);
            GridPane.setConstraints(blockTwo, 1, 1);
            GridPane.setConstraints(footer, 0, 2);

            GridPane.setHgrow(header, Priority.ALWAYS);
            GridPane.setColumnSpan(header, GridPane.REMAINING);
//
            GridPane.setHgrow(blockTwo, Priority.ALWAYS);
//            GridPane.setVgrow(sectionOne, Priority.ALWAYS);

            ( (AvatarBlock) blockOne.getChildren().getLast()).setAvatarSize(400);
            ((DetailsBlock) blockTwo.getChildren().getFirst()).updateLayout(2);
        }, Break.XXL, Break.XL, Break.WIDE);

        root.addPoint(e -> {
            System.out.println("wide");
            GridPane.setConstraints(header, 0, 0);
            GridPane.setConstraints(blockOne, 0, 1);
            GridPane.setConstraints(blockTwo, 1, 1);

            GridPane.setHgrow(header, Priority.ALWAYS);
            GridPane.setColumnSpan(header, GridPane.REMAINING);
//
            GridPane.setHgrow(blockTwo, Priority.ALWAYS);
//            GridPane.setVgrow(sectionOne, Priority.ALWAYS);

            ( (AvatarBlock) blockOne.getChildren().getLast()).setAvatarSize(300);
            ((DetailsBlock) blockTwo.getChildren().getFirst()).updateLayout(1);
        }, Break.LG);

        root.addPoint(e -> {
            System.out.println("wide");
            GridPane.setConstraints(header, 0, 0);
            GridPane.setConstraints(blockOne, 0, 1);
            GridPane.setConstraints(blockTwo, 0, 2);
            GridPane.setConstraints(footer, 0, 3);

            GridPane.setHgrow(header, Priority.ALWAYS);
            GridPane.setColumnSpan(header, GridPane.REMAINING);

            ( (AvatarBlock) blockOne.getChildren().getLast()).setAvatarSize(300);
//
//            GridPane.setColumnSpan(sectionOne, GridPane.REMAINING);
            GridPane.setHgrow(blockTwo, Priority.ALWAYS);
            ((DetailsBlock) blockTwo.getChildren().getFirst()).updateLayout(1);
//            GridPane.setVgrow(sectionOne, Priority.ALWAYS);


        }, Break.MD, Break.SM, Break.MOBILE);

    }

    private void configLayout() {
        container.getStyleClass().addAll("fit-width fit-height padding-10".split(" "));
    }
}

class Header extends GridPane {

    public Header() {
        Text title = new Text("Profile");
        Text legend = new Text("View all profile details here.");
        title.getStyleClass().addAll("h3".split(" "));
        legend.getStyleClass().addAll("h5".split(" "));

        Separator separator = new Separator();
        add(title, 0, 0);
        add(legend, 0, 1);
        add(separator, 0, 2);
        GridPane.setFillWidth(title, true);
        GridPane.setHgrow(separator, Priority.ALWAYS);

    }
}

class DetailsBlock extends GridPane {


    public DetailsBlock() {
        setVgap(20);

        var title = new Text("Bio & other details");
        title.getStyleClass().add("h4");
        addRow(0, title);
        GridPane.setColumnSpan(title, REMAINING);

        // Row one
        add(createDefaultContent("My Role", "BeatMaker"), 1,1);
        add(createDefaultContent("My Experience Level", "Intermediate"), 2,1);

        // Row two
        add(createDefaultContent("My 3 Favorite Artists", "Ninho, Travis Scott, Metro Boomin"),1,2);
        add(createDefaultContent("My Favorite Music Genre", "Trap"), 2,2);

        // Row Three
        add(createDefaultContent("The Software or Equipment I Use", "Ableton"),1,3);
        add(createDefaultContent("My Preferred Music Mood", "Melancholic"), 2,3);

        // Row Four
        add(createDefaultContent("The City or Region", "California, USA"),1,4);
        add(createCustom("Availability"), 2,4);

        // Row Five
        add(createDefaultContent("Tags", "#Drill, #Melancholic, #Rap-US"),1,5);


//        for (int i = 1; i > cols; i++) {
//            ColumnConstraints col = new ColumnConstraints();
//            getColumnConstraints().add(col);
//
//            RowConstraints row = new RowConstraints();
//            row.setPercentHeight(16.67);
//            row.setMinHeight(180);
//            getRowConstraints().add(row);
//        }


//        ColumnConstraints col1 = new ColumnConstraints();
//        ColumnConstraints col2 = new ColumnConstraints();
//        ColumnConstraints col3 = new ColumnConstraints();
////        col1.setMaxWidth(25);
////        col1.setMinWidth(25);
//        col1.setPrefWidth(25);
//        col1.setMaxWidth(25);
//        col2.setPercentWidth(49);
//        col3.setPercentWidth(49);
//        getColumnConstraints().addAll(col1, col2, col3);


        updateLayout(2);

    }

    public void updateLayout(int _cols) {
        int maxCols = _cols;
        int rows = 1;
        int cols = 0;
        for (int i = 1; i < getChildren().size() ; i++) {
            GridPane.setColumnIndex(getChildren().get(i), ++cols);
            GridPane.setRowIndex(getChildren().get(i), rows);
            GridPane.setMargin(getChildren().get(i), new Insets(0,0,0,10));
            GridPane.setHgrow(getChildren().get(i), Priority.ALWAYS);

            if (cols == maxCols) {
                cols = 0;
                rows++;
            }
        }
        GridPane.setColumnSpan(getChildren().getLast(), REMAINING);

    }

    private VBox createCustom(String _title) {
        var box = new VBox();
        box.setAlignment(Pos.BOTTOM_LEFT);
        box.setMinHeight(50);
        Text title = new Text(_title);
        title.getStyleClass().add("h5");
        title.setStyle("-text-color: -medium-gray-2");
        Label content = new Label("Available for collaboration");
        content.setStyle("-text-color: -mint; -fx-background-color: derive(-mint, 90%); -fx-background-radius: 10px;");
        content.setPadding(new Insets(10));

        Circle circle = new Circle();
        circle.setRadius(4);
        circle.setStyle("-fx-stroke: white; -fx-fill: -mint;");
        content.setGraphic(circle);
//        content.getStyleClass().add("h5");
        box.getChildren().setAll(title, content, new Separator());
//        box.setMaxHeight(50);
        return box;
    }

    private VBox createDefaultContent(String _title, String _content) {
        var box = new VBox();
        box.setAlignment(Pos.BOTTOM_LEFT);
        box.setMinHeight(50);
        Text title = new Text(_title);
        title.getStyleClass().add("h5");
        title.setStyle("-text-color: -medium-gray-2");
        Text content = new Text(_content);
        content.getStyleClass().add("h5");
        box.getChildren().setAll(title, content, new Separator());
        return box;
    }
}

class AvatarBlock extends GridPane {

    private AvatarView avatar = new AvatarView(Assets.getImage("default_avatar.jpg"));

    public AvatarBlock() {
        getStyleClass().addAll("padding-20".split(" "));

        avatar.getStyleClass().addAll("stroke-4".split(" "));
        avatar.setStyle("-fx-stroke: -light-gray;");
        avatar.setSize(400);
        avatar.setRadius(400);
//        avatar.setStrokeWidth(2);

        Text title = new Text("Jhon Doe");
        title.getStyleClass().addAll("h4".split(" "));
        addRow(0, title);
        addRow(1, avatar);
        GridPane.setHalignment(title, HPos.CENTER);
    }

    public void setAvatarSize(double size) {
        avatar.setSize(size);
    }
}

class Block extends StackPane {
    public Block(Node... children) {
        super(children);
        getStyleClass().addAll("align-center radius-2 border-2 padding-10 border-light-gray".split(" "));

    }
}

class Footer extends GridPane {
    public Footer() {
        var title = new Text("Social Media");
        title.getStyleClass().addAll("h3");
        add(title, 0,0);

        HBox social = new HBox();
        social.setAlignment(Pos.BOTTOM_CENTER);
        social.setSpacing(10);
        social.setPadding(new Insets(20));
        social.getChildren().addAll(
                new AvatarView(Assets.getImage("social/facebook.png", 100), 70),
                new AvatarView(Assets.getImage("social/twitter.png", 100), 70),
                new AvatarView(Assets.getImage("social/youtube.png", 100), 70)
        );
        add(social, 0,1);
    }
}