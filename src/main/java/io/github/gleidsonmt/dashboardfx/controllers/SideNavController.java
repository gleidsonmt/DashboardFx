package io.github.gleidsonmt.dashboardfx.controllers;

import io.github.gleidsonmt.dashboardfx.core.interfaces.ActionView;
import io.github.gleidsonmt.dashboardfx.core.services.DrawerBehavior;
import io.github.gleidsonmt.dashboardfx.core.view.View;
import io.github.gleidsonmt.dashboardfx.core.view.layout.SimpleView;
import io.github.gleidsonmt.dashboardfx.views.TutorialUnderstanding;
import io.github.gleidsonmt.dashboardfx.views.WrappersView;
import io.github.gleidsonmt.dashboardfx.views.controls.ButtonPresCreator;
import io.github.gleidsonmt.dashboardfx.views.controls.HyperlinkPresCreator;
import io.github.gleidsonmt.dashboardfx.views.controls.LabelPresCreator;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.concurrent.Worker;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.StackPane;
import javafx.scene.web.WebView;

import java.net.URL;
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
    private void goDash() {
        context.routes().nav("dash");
//        context.routes().nav("sales_left", "sales_right");
    }

    @FXML
    private void goUnderstanding() {
        context.routes().putAndGo(
                new SimpleView(
                        "tutorial_understanding",
                        new TutorialUnderstanding(context)
                        )
        );
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

    @FXML
    private void goButton() {
        context.routes().putAndGo(
                new SimpleView("view_button", new ButtonPresCreator(context))
        );
    }

    @FXML
    private void goHyperlink() {
        context.routes().putAndGo(
                new SimpleView("view_hyperlink", new HyperlinkPresCreator(context))
        );
        WebView webView = new WebView();
        webView.setContextMenuEnabled(false);
        webView.getEngine().setJavaScriptEnabled(true);
        webView.getEngine().load("https://openjfx.io/javadoc/17/javafx.controls/javafx/scene/control/Button.html");
        webView.getEngine().getLoadWorker().stateProperty().addListener(new ChangeListener<Worker.State>() {
            @Override
            public void changed(ObservableValue<? extends Worker.State> observable, Worker.State oldValue, Worker.State newValue) {
                if (newValue == Worker.State.SUCCEEDED) {
                    System.out.println(
                            webView.getEngine().getDocument().getElementsByTagName("block")
                    );
                }
            }
        });


    }

    @FXML
    private void goLabels() {
        context.routes().putAndGo(
                new SimpleView("view_label", new LabelPresCreator(context))
        );
    }


    public void goBuild(ActionEvent actionEvent) {
    }


    public void goLogin(ActionEvent actionEvent) {
    }

    @FXML
    private void goAbout() {
        context.routes().nav("about");
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        new DrawerBehavior(root, group);
    }
}