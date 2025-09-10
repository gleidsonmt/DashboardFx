package io.github.gleidsonmt.dashboardfx.presentation.presentations.components;

import io.github.gleidsonmt.dashboardfx.presentation.core.CustomizablePresentation;
import io.github.gleidsonmt.dashboardfx.presentation.internal.Tutorial;
import io.github.gleidsonmt.dashboardfx.utils.Assets;
import io.github.gleidsonmt.glad.controls.avatar.AvatarView;
import io.github.gleidsonmt.glad.controls.icon.Icon;
import io.github.gleidsonmt.glad.controls.icon.SVGIcon;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Priority;
import javafx.scene.layout.StackPane;
import javafx.scene.text.Text;

import static javafx.scene.layout.GridPane.REMAINING;

/**
 * @author Gleidson Neves da Silveira | gleidisonmt@gmail.com
 * Create on  13/03/2025
 */
public class CardsPres extends CustomizablePresentation {

    public CardsPres() {
        super("Cards");
    }

    public Tutorial create() {
        return new Tutorial()
                .h3("Cards")
                .separator()
                .text("A card is a combination with structures.\nUsually the best option is a GridPane")
                .demo(createDefaultCard())
                .code("""
                        // Create body
                        GridPane grid = new GridPane();
                        setPrefSize(100, 100);
                        grid.setPadding(new Insets(20));
                        grid.setHgap(10);
                        grid.getStyleClass().addAll("bg-white", "border-2", "border-light-gray-2", "radius-5");
                        // Create The big text
                        Text number = new Text("39");
                        number.getStyleClass().addAll("h2", "text-primary", "bold");
                        Text info = new Text("Messages");
                        info.getStyleClass().addAll("h5",  "");
                        // Create The icon
                        SVGIcon icon = new SVGIcon(Icon.CHAT);
                        icon.setScale(1.5);
                        icon.getPath().setStyle("-fx-fill: white;");
                        // Create the block
                        StackPane block = new StackPane(icon);
                        block.getStyleClass().addAll("bg-primary", "bg-insets-1",  "border-2", "border-white", "depth-1", "radius-5");
                        block.setMinSize(100, 50);
                        // Add to grid 
                        card.add(block, 0,0);
                        grid.add(number, 1,0);
                        grid.add(info, 1,1);
                        // Add some constraints
                        GridPane.setHgrow(info, Priority.ALWAYS);
                        GridPane.setVgrow(info, Priority.ALWAYS);
                        GridPane.setRowSpan(block, GridPane.REMAINING);
                        GridPane.setHalignment(number, HPos.RIGHT);
                        GridPane.setHalignment(info, HPos.RIGHT);
                        """)
                .h3("Grouping")
                .demo(createHorizontalCard())
                .code("""
                        GridPane card = new GridPane();
                        card.setHgap(15);
                        Text title = new Text("John Doe");
                        title.getStyleClass().addAll("h5", "bold");
                        Text legend = new Text("Tech Lead");
                        legend.getStyleClass().addAll("h5");
                        AvatarView avatar = new AvatarView(Assets.getImage("default_avatar.jpg"), 20);
                        card.add(avatar, 0,0);
                        card.add(title, 1,0);
                        card.add(legend, 1,1);
                        GridPane.setRowSpan(avatar, REMAINING);
                        """)
                ;
    }

    private Node createHorizontalCard() {
        GridPane card = new GridPane();
        card.setHgap(15);
        Text title = new Text("John Doe");
        title.getStyleClass().addAll("h5", "bold");
        Text legend = new Text("Tech Lead");
        legend.getStyleClass().addAll("h5");
        AvatarView avatar = new AvatarView(Assets.getImage("default_avatar.jpg"), 40);
        card.add(avatar, 0, 0);
        card.add(title, 1, 0);
        card.add(legend, 1, 1);
        GridPane.setRowSpan(avatar, REMAINING);
        return card;
    }

    private Node createDefaultCard() {
        GridPane grid = new GridPane();
        grid.setPrefSize(100, 100);
        grid.setPadding(new Insets(20));
        grid.setHgap(10);
        grid.getStyleClass().addAll("bg-white", "border-2", "border-light-gray-2", "radius-5");

        Text number = new Text("39");
        number.getStyleClass().addAll("h2", "text-accent", "bold");

        Text info = new Text("Messages");
        info.getStyleClass().addAll("h5", "");

        SVGIcon icon = new SVGIcon(Icon.CHAT);
        icon.setScale(1.5);
        icon.getPath().setStyle("-fx-fill: white;");
        StackPane block = new StackPane(icon);
        block.getStyleClass().addAll("bg-accent", "bg-insets-1", "border-2", "border-white", "depth-1", "radius-5");
        block.setMinSize(100, 50);


        grid.add(block, 0, 0);
        grid.add(number, 1, 0);
        grid.add(info, 1, 1);

        GridPane.setHgrow(info, Priority.ALWAYS);
        GridPane.setVgrow(info, Priority.ALWAYS);
//        GridPane.setHgrow(block, Priority.ALWAYS);
        GridPane.setRowSpan(block, GridPane.REMAINING);
        GridPane.setHalignment(number, HPos.RIGHT);
        GridPane.setHalignment(info, HPos.RIGHT);

        return grid;
    }

}
