package io.github.gleidsonmt.dashboardfx.dashboard;

import io.github.gleidsonmt.dashboardfx.utils.Assets;
import io.github.gleidsonmt.glad.controls.avatar.AvatarView;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Region;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;

/**
 * @author Gleidson Neves da Silveira | gleidisonmt@gmail.com
 * Create on  10/08/2025
 */
public class PersonalCard extends VBox {
    public PersonalCard() {

        StackPane header = new StackPane();
        header.setId("card-header");
        Region background = new Region();
        background.getStyleClass().add("card-poster");
        background.setId("card-header-background");
        background.setPrefHeight(150);
        background.setMaxHeight(Region.USE_PREF_SIZE);
        StackPane.setAlignment(background, Pos.TOP_CENTER);

        VBox headerContent = new VBox();
        headerContent.setId("card-header-content");

        header.getChildren().setAll(background, headerContent);

        VBox body = new VBox();
        body.setSpacing(20);
        body.setId("card-body");
        body.setAlignment(Pos.CENTER);

        Text title = new Text("@jhon_doe");
        title.getStyleClass().addAll("h3");

        Text legend = new Text("The mission is always done perfectly.");
        legend.setTextAlignment(TextAlignment.CENTER);
        legend.getStyleClass().addAll("h5");
        legend.setWrappingWidth(300);

        HBox social = new HBox();
        social.setAlignment(Pos.BOTTOM_CENTER);
        social.setSpacing(10);
        social.setPadding(new Insets(20));
        social.getChildren().addAll(
                new AvatarView(Assets.getImage("social/facebook.png", 80), 60),
                new AvatarView(Assets.getImage("social/twitter.png", 80), 60),
                new AvatarView(Assets.getImage("social/youtube.png", 80), 60)
        );
        body.getChildren().addAll(title, legend, social);

        setSpacing(10);
        getChildren().setAll(header, body);

        getStyleClass().addAll("bg-white border-2 border-light-gray-2 radius-5 align-top-center".split(" "));
    }
}
