package io.github.gleidsonmt.dashboardfx.controllers;

import io.github.gleidsonmt.dashboardfx.core.interfaces.ActionView;
import io.github.gleidsonmt.dashboardfx.core.services.DrawerBehavior;
import io.github.gleidsonmt.dashboardfx.core.view.View;
import io.github.gleidsonmt.dashboardfx.core.view.layout.SimpleView;
import io.github.gleidsonmt.dashboardfx.views.TutorialUnderstanding;
import io.github.gleidsonmt.dashboardfx.views.WrappersView;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.StackPane;

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
        System.out.println("going");
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

    public void goBuild(ActionEvent actionEvent) {
    }

    public void goButton(ActionEvent actionEvent) {
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