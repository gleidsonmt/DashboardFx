package io.github.gleidsonmt.testfx;

import io.github.gleidsonmt.glad.base.responsive.Container;
import io.github.gleidsonmt.glad.controls.icon.Icon;
import io.github.gleidsonmt.glad.controls.icon.SVGIcon;
import io.github.gleidsonmt.glad.controls.text_box.PasswordBox;
import io.github.gleidsonmt.glad.controls.text_box.TextBox;
import io.github.gleidsonmt.glad.theme.Css;
import io.github.gleidsonmt.glad.theme.ThemeProvider;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import org.scenicview.ScenicView;

import java.util.Objects;

/**
 * @author Gleidson Neves da Silveira | gleidisonmt@gmail.com
 * Create on  20/02/2026
 */
public class App extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage stage) {

        VBox body = new VBox();

        TextBox perfilName = new TextBox();
        perfilName.setPromptText("Your perfil name");
        perfilName.setIcon(new SVGIcon(Icon.FACE));
        perfilName.setHelperText("Minimal size 4");
        perfilName.setMinHeight(50);

        PasswordBox pass = new PasswordBox();
        pass.setPromptText("Your password name");
        pass.setIcon(new SVGIcon(Icon.FACE));
        pass.setHelperText("Minimal size 4");
        pass.setMinHeight(50);
//        user.nameProperty().bind(perfilName.textProperty());

        perfilName.validProperty().bind(perfilName.getEditor().lengthProperty().greaterThan(3));

        var btn = new Button("Confirm");
        btn.setOnAction(e -> perfilName.validate());
        Container container = new Container(body);
        body.getChildren().addAll(perfilName, pass, btn);
        stage.setScene(new Scene(container, 600, 600));
        stage.show();

        ThemeProvider.install(stage.getScene(), Css.ALL);
        stage.getScene().getStylesheets().add(Objects.requireNonNull(App.class.getResource("/io.github.gleidsonmt.testfx/css/app.css")).toExternalForm());

        ScenicView.show(stage.getScene());
    }
}
