package io.github.gleidsonmt.dashboardfx.dashboard.notifications.component;

import io.github.gleidsonmt.glad.controls.button.Button;
import javafx.geometry.HPos;
import javafx.geometry.Pos;
import javafx.geometry.VPos;
import javafx.scene.layout.*;

/**
 * @author Gleidson Neves da Silveira | gleidisonmt@gmail.com
 * Create on  25/04/2025
 */
public class NotificationFooter extends GridPane {

    public NotificationFooter() {
        VBox.setVgrow(this, Priority.ALWAYS);
        setAlignment(Pos.BOTTOM_CENTER);

        getStyleClass().add("notification-footer");
        setMinHeight(60);
        Button viewAll = new Button("View all notifications");
        Button manage = new Button("Manage notifications");
        manage.setCancelButton(true);

        add(manage, 0, 0);
        add(viewAll, 1, 0);

        GridPane.setHalignment(viewAll, HPos.RIGHT);
        GridPane.setVgrow(viewAll, Priority.ALWAYS);

        GridPane.setValignment(manage, VPos.BOTTOM);
        GridPane.setValignment(viewAll, VPos.BOTTOM);

        ColumnConstraints one = new ColumnConstraints();
        one.setPercentWidth(50);
        ColumnConstraints two = new ColumnConstraints();
        two.setPercentWidth(50);
        getColumnConstraints().addAll(one, two);
        RowConstraints rowOne = new RowConstraints();
        rowOne.setPercentHeight(100);
        getRowConstraints().add(rowOne);
    }
}
