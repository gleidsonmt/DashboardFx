package io.github.gleidsonmt.dashboardfx.controllers;

import io.github.gleidsonmt.dashboardfx.core.Context;
import io.github.gleidsonmt.dashboardfx.core.controls.GNAvatar;
import io.github.gleidsonmt.dashboardfx.core.controls.GNIconButton;
import io.github.gleidsonmt.dashboardfx.core.controls.icon.IconContainer;
import io.github.gleidsonmt.dashboardfx.core.controls.icon.Icons;
import io.github.gleidsonmt.dashboardfx.core.exceptions.NavigationException;
import io.github.gleidsonmt.dashboardfx.core.impl.layout.Direction;
import io.github.gleidsonmt.dashboardfx.core.interfaces.ActionView;
import io.github.gleidsonmt.dashboardfx.core.model.NotificationCell;
import io.github.gleidsonmt.dashboardfx.core.services.DrawerBehavior;
import io.github.gleidsonmt.dashboardfx.core.view.SimpleView;
import io.github.gleidsonmt.dashboardfx.core.view.View;
import io.github.gleidsonmt.dashboardfx.core.view.layout.DialogContainer;
import io.github.gleidsonmt.dashboardfx.core.view.layout.creators.TutorialCreator;
import io.github.gleidsonmt.dashboardfx.views.TutorialUnderstanding;
import io.github.gleidsonmt.dashboardfx.views.WrappersView;
import io.github.gleidsonmt.dashboardfx.views.controls.*;
import io.github.gleidsonmt.dashboardfx.views.tutorial.NewsLetter;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.geometry.VPos;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Priority;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;
import javafx.scene.text.TextFlow;

import java.net.URL;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Locale;
import java.util.ResourceBundle;

/**
 * @author Gleidson Neves da Silveira | gleidisonmt@gmail.com
 * Version 0.0.1
 * Create on  02/04/2023
 */
public class SideNavController extends ActionView {

    @FXML private StackPane root;
    @FXML private ToggleGroup group;

    @FXML
    private void goDash() throws NavigationException {
        context.routes().nav("dash");
//        context.routes().nav("sales_left", "sales_right");
    }

    @FXML
    private void goNewsletter() {
        go("view_news", new NewsLetter(context));
    }

    @FXML
    private void goUnderstanding() {
        go("tutorial_understanding", new TutorialUnderstanding(context));
    }

    @FXML
    private void goButton() {
        go("view_button", new ButtonPresCreator(context));
    }

    @FXML
    private void goCheckBox() {
        go("view_check", new CheckBoxPresCreator(context));
    }

    @FXML
    private void goComboBox() {
        go("view_check", new ComboBoxPresCreator(context));
    }

    @FXML
    private void goHyperlink() {
        go("view_hyperlink", new HyperlinkPresCreator(context));
    }

    @FXML
    private void goLabels() {
        go("view_label", new LabelPresCreator(context));
    }

    @FXML
    private void goListView() {
        go("view_list", new ListViewCreator(context));
    }
    @FXML
    private void goPassword() {
        go("view_pass", new PasswordPresCreator(context));
    }

    @FXML
    private void goTableView() {
        go("view_table", new TableViewPresCreator(context));
    }

    @FXML
    private void goTextField() {
        go("view_label", new TextFieldPresCreator(context));
    }

    @FXML
    private void goTreeView() {
        go("view_tree", new TreeViewPresCreator(context));
    }



    private View presentation;

    @FXML
    private void goWrappers() {
        if(presentation == null) presentation =
                new SimpleView("view_wrapper",
            new WrappersView(context));

        context.routes().putAndGo(presentation);

//        View view = new SimpleView("view1", "/views/view01.fxml");
//        context.routes().putAndGo(view);
    }



    private void go(String name, TutorialCreator tutorialCreator) {
        context.routes().putAndGo(new SimpleView(name, tutorialCreator));
    }

    @FXML
    private void goBuild() {
        createProblemView();
    }


    @FXML
    private void goLogin() {
        createProblemView();
    }

    private void createProblemView(){
        Circle rectangle = new Circle();
        rectangle.setFill(new ImagePattern(
                new Image(context.getResource("style/img/404.png").toExternalForm())
        ));
        rectangle.setRadius(200);
        context.routes().putAndGo(new SimpleView("error_404", new StackPane(rectangle)
        ));
    }

    @FXML
    private void goCarousel() {

        Button button = new Button("Click Here");
        StackPane root = new StackPane(button);

        VBox b = createDialogNotification();

        button.setOnMouseClicked(event -> {
            context.flow()
//                    .getPopup()
//                    .size(400,300)
                    .content(
                            new DialogContainer(b)
                                    .style("-fx-background-radius : 10px;")
                                    .size(400, 280)
                    )
//                    .background(Wrapper.WrapperBackgroundType.GRAY)
                    .show(Direction.BOTTOM_RIGHT, button);
        });

        context.routes().putAndGo(new SimpleView("error_404", root));
    }

    private VBox createNotifications(NotificationCell... cells) {
        VBox box = new VBox();
        for (NotificationCell item : cells) {

            ToggleButton toggleButton = new ToggleButton();
            toggleButton.setMaxWidth(Double.MAX_VALUE);
            toggleButton.getStyleClass().addAll("btn-flat", "transparent");

            Text text = new Text(item.text());
            TextFlow textFlow = new TextFlow(text);
            text.getStyleClass().addAll("text-12", "text-bold");
            String pattern = "dd MMM yyyy HH:mm:ss";
            Text time = new Text(item.time().format(DateTimeFormatter.ofPattern(pattern, Locale.US)));
            GridPane grid = new GridPane();
            Node icon = item.icon();
            icon.setStyle("-fx-fill : white; -fx-text-fill: white; -text-color : white;");
            Circle circle = new Circle();
            circle.setRadius(5);
            if (item.read()) {
                circle.setStyle("-fx-fill : -info;");
            } else {
                circle.setStyle("-fx-fill : white;");
            }
            grid.getChildren().setAll(circle, textFlow, time, icon);
//            grid.setGridLinesVisible(true);
            GridPane.setConstraints(circle, 0,0,1,2, HPos.LEFT, VPos.CENTER, Priority.ALWAYS, Priority.ALWAYS);
            GridPane.setConstraints(textFlow, 1,0,1,1, HPos.LEFT, VPos.CENTER, Priority.ALWAYS, Priority.ALWAYS);
            GridPane.setConstraints(time, 1,1,1,1, HPos.LEFT, VPos.TOP, Priority.ALWAYS, Priority.ALWAYS);
            GridPane.setConstraints(icon, 2,0,1,2, HPos.RIGHT, VPos.CENTER, Priority.ALWAYS, Priority.ALWAYS);
            grid.setHgap(10);
            toggleButton.setGraphic(grid);
            box.getChildren().addAll(toggleButton, new Separator());
        }

        return box;
    }

    private VBox createDialogNotification() {
        VBox root = new VBox();
        root.setAlignment(Pos.TOP_CENTER);

        Text title = new Text("Notifications");
        title.getStyleClass().addAll("h5", "text-bold");
        Hyperlink btn = new Hyperlink("Mark as read");
        btn.setGraphic(new IconContainer(Icons.DONE_ALL));
        btn.setPadding(new Insets(10));
        btn.getStyleClass().addAll("text-bold","transparent", "text-info", "no-border");

        GridPane header = new GridPane();
        header.getChildren().addAll(title, btn);
        GridPane.setConstraints(title, 0,0,1,1, HPos.LEFT, VPos.CENTER, Priority.ALWAYS, Priority.ALWAYS);
        GridPane.setConstraints(btn, 1,0,1,1, HPos.RIGHT, VPos.CENTER, Priority.ALWAYS, Priority.ALWAYS);

//        ListView<NotificationCell> listView = new ListView<>();
//        listView.setCellFactory(new NotificationListFactory());

        VBox vBox = createNotifications(
                new NotificationCell(
                        true,
                        "Your Password has been changed succesfully.",
                        new GNIconButton(Icons.BADGE),
                        LocalDateTime.now()
                ),
                new NotificationCell(
                        false,
                        "Thank you for booking a meeting with us.",
                        new GNAvatar(new Image(context.getResource("style/img/avatar.png").toExternalForm()), 20),
                        LocalDateTime.now()
                ),
                new NotificationCell(
                        false,
                        "Great News! We are lauching our 5th fund: DLE Senior Living.",
                        new GNAvatar(new Image(context.getResource("style/img/avatar.png").toExternalForm()), 20),
                        LocalDateTime.now()
                )
        );


//        listView.setPrefHeight(3 * 45);
//        listView.setStyle("-fx-fixed-cell-size : 100px;");
//

        Hyperlink btnAll = new Hyperlink("View All Notifications");
        btnAll.setPadding(new Insets(10));
        btnAll.getStyleClass().addAll("text-bold", "transparent", "no-border", "text-info");

        root.getChildren().setAll(header, vBox, btnAll);
        return root;
    }


    @FXML
    private void goAbout() throws NavigationException{
        context.routes().nav("about");
    }

    @Override
    public void onInit(Context context) {
        this.context = context;
        new DrawerBehavior(root, group, context);
    }
}