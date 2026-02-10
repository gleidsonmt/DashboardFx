package io.github.gleidsonmt.dashboardfx.drawer;

import io.github.gleidsonmt.glad.base.drawer.Drawer;
import io.github.gleidsonmt.glad.controls.text_box.SearchBox;
import javafx.geometry.Insets;
import javafx.geometry.VPos;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Priority;
import javafx.scene.layout.RowConstraints;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.StrokeType;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;

/**
 * @author Gleidson Neves da Silveira | gleidisonmt@gmail.com
 * Create on  11/03/2025
 */
public class DrawerHeader extends GridPane {

    public DrawerHeader(Drawer drawer) {

        setPrefSize(200, 60);
        this.setPadding(new Insets(10));
        this.setHgap(10);

        Text title = new Text("Blue Galaxy");
        title.setStyle(" -fx-font-size: 18px; -fx-fill: -fx-accent; -fx-font-weight: bold;");
        title.getStyleClass().addAll("depth-1", "font-instagram-headline");

        Text legend = new Text("Gleidson, Inc. v0.7.223");
        legend.setStyle("-fx-text-weight: 14pt");

        Text logoTest = new Text("G");
        logoTest.setStroke(Color.WHITE);
        logoTest.setRotate(180);
        logoTest.setStyle("-fx-fill: -fx-accent; -fx-font-size: 38px; -fx-font-family: \"JetBrains Mono\"; ");
        logoTest.setStrokeType(StrokeType.OUTSIDE);
        logoTest.getStyleClass().addAll("depth-1", "font-instagram");
        logoTest.setStrokeWidth(1);
        logoTest.setUnderline(true);
        logoTest.setWrappingWidth(20);
        logoTest.setTextAlignment(TextAlignment.CENTER);

        this.add(logoTest, 0,0,1,2);
        this.add(title, 1,0);
        this.add(legend, 1,1);

        GridPane.setHgrow(title, Priority.ALWAYS);

        GridPane.setValignment(title, VPos.BOTTOM);
        GridPane.setValignment(legend, VPos.TOP);

        SearchBox searchBox = new SearchBox();
        this.add(searchBox, 0,2,2,1);

        VBox.setMargin(this, new Insets(0,0,10,0));

        var rows = 3;
        for (int i = 0; i < rows; i++) {
             RowConstraints row = new RowConstraints();
             row.setPercentHeight((double) 100 / rows);
             getRowConstraints().add(row);
        }

        drawer.setSearchable(searchBox.textProperty(), name -> name.toLowerCase().contains(searchBox.getText().toLowerCase()));

    }
}