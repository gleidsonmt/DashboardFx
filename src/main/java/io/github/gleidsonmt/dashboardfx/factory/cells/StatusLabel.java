package io.github.gleidsonmt.dashboardfx.factory.cells;

import io.github.gleidsonmt.dashboardfx.model.Status;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import org.jetbrains.annotations.NotNull;

/**
 * @author Gleidson Neves da Silveira | gleidisonmt@gmail.com
 * Create on  18/09/2023
 */
public class StatusLabel extends Label {

    public StatusLabel(@NotNull Status status) {
        switch (status) {
            case INACTIVE -> inactive();
            case ACTIVE -> active();
            case BUSY -> onVacation();
        }
        this.setPrefWidth(80);
        this.setAlignment(Pos.CENTER);
    }

    private void inactive() {
        this.setText("Inactive");
        this.getStyleClass().add("rounded");
        this.setPadding(new Insets(5));
//        this.setStyle("""
//                    -fx-text-fill: -medium-gray-2;
//                    -fx-background-color: -light-gray;
//                    -fx-border-color:  -medium-gray-2;
//                    -fx-border-width: 2px;
//                    -fx-border-radius: 100px;
//                    """);

        this.setStyle("""
                    -fx-text-fill: white;
                    -fx-background-color: -medium-gray-2;
                    -fx-border-color:  derive(-medium-gray-2, -20%);
                    -fx-border-width: 2px;
                    -fx-border-radius: 2px;
                    -fx-background-radius: 2px;
                    """);
    }

    private void active() {
        this.setText("Active");
        this.getStyleClass().add("rounded");
        this.setPadding(new Insets(5));
//        this.setStyle("""
//                    -fx-text-fill: -mint;
//                    -fx-background-color: derive(-mint, 90%);
//                    -fx-border-color: -mint;
//                    -fx-border-width: 2px;
//                    -fx-border-radius: 100px;
//                    """);

        this.setStyle("""
                    -fx-text-fill: white;
                    -fx-background-color: -mint;
                    -fx-border-color: derive(-mint, -20%);
                    -fx-border-width: 2px;
                    -fx-border-radius: 2px;
                    -fx-background-radius: 2px;
                    """);
    }

    private void onVacation() {
        this.setText("Vacation");
        this.getStyleClass().add("rounded");
        this.setPadding(new Insets(5));
//        this.setStyle("""
//                    -fx-text-fill: -warning;
//                    -fx-background-color: derive(-warning, 90%);
//                    -fx-border-color: -warning;
//                    -fx-border-width: 2px;
//                    -fx-border-radius: 100px;
//                    """);

        this.setStyle("""
                    -fx-text-fill: white;
                    -fx-background-color: -warning;
                    -fx-border-color: derive(-warning, -20%);
                    -fx-border-width: 2px;
                    -fx-border-radius: 2px;
                    -fx-background-radius: 2px;
                    """);
    }
}
