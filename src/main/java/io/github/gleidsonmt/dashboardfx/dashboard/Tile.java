package io.github.gleidsonmt.dashboardfx.dashboard;

import javafx.geometry.Insets;
import javafx.scene.CacheHint;
import javafx.scene.Node;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;

/**
 * @author Gleidson Neves da Silveira | gleidisonmt@gmail.com
 * Create on  10/08/2025
 */
public class Tile extends VBox {

    private String title;
    private Node content;

    public Tile(String _title, Node content) {
        this.title = _title;
        this.content = content;

        this.getStyleClass().addAll("bg-white border-2 border-light-gray-2 radius-5 align-top-center".split(" "));
        this.setPadding(new Insets(5));

        Text title = new Text(_title);
        title.setCacheHint(CacheHint.QUALITY);
//        title.setStyle("-fx-font-family: \"Instagram Sans\";");
        title.getStyleClass().add("h4");

        this.getChildren().addAll(title, content);
        this.setMinHeight(300);
    }
}
