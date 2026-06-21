package io.github.gleidsonmt.dashboardfx.dashboard;

import io.github.gleidsonmt.glad.controls.icon.Icon;
import io.github.gleidsonmt.glad.controls.icon.SVGIcon;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Priority;
import javafx.scene.layout.StackPane;
import javafx.scene.text.Text;

/**
 * @author Gleidson Neves da Silveira | gleidisonmt@gmail.com
 * Create on  13/03/2025
 */
public class Widget extends GridPane {

    public Widget(Icon _icon, String _number, String _legend, String cls) {
        this.getStyleClass().addAll("bg-foreground", "border-2", "border-theme", "radius-5");
        setPrefSize(100, 100);
        this.setPadding(new Insets(20));
        this.setHgap(10);

        Text number = new Text(_number);
        number.getStyleClass().addAll("h2", "bold", "text" + cls);
        Text info = new Text(_legend);
        info.getStyleClass().addAll("h5",  "");

        SVGIcon icon = new SVGIcon(_icon);
        icon.setScale(1.5);
        icon.getPath().setStyle("-fx-fill: white;");
        StackPane block = new StackPane(icon);
        block.getStyleClass().addAll("bg" + cls, "bg-insets-1",  "border-2", "border-theme",  "radius-5");
        block.setMinSize(100, 50);

//        setGridLinesVisible(true);

        add(block, 0,0);
        add(number, 1,0);
        add(info, 1,1);

        GridPane.setHgrow(info, Priority.ALWAYS);
        GridPane.setVgrow(info, Priority.ALWAYS);
//        GridPane.setHgrow(block, Priority.ALWAYS);
        GridPane.setRowSpan(block, REMAINING);
        GridPane.setHalignment(number, HPos.RIGHT);
        GridPane.setHalignment(info, HPos.RIGHT);
    }
}
