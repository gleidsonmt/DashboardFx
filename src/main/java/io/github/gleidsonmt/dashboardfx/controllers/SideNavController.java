package io.github.gleidsonmt.dashboardfx.controllers;

import io.github.gleidsonmt.dashboardfx.core.interfaces.ActionView;
import io.github.gleidsonmt.dashboardfx.core.services.DrawerBehavior;
import io.github.gleidsonmt.dashboardfx.core.view.View;
import io.github.gleidsonmt.dashboardfx.core.view.layout.SimpleView;
import io.github.gleidsonmt.dashboardfx.core.view.layout.creators.TutorialCreator;
import io.github.gleidsonmt.dashboardfx.views.TutorialUnderstanding;
import io.github.gleidsonmt.dashboardfx.views.WrappersView;
import io.github.gleidsonmt.dashboardfx.views.controls.*;
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
    }

    @FXML
    private void goLabels() {
        context.routes().putAndGo(
                new SimpleView("view_label", new LabelPresCreator(context))
        );
    }

    @FXML
    private void goTextField() {
        context.routes().putAndGo(
                new SimpleView("view_label", new TextFieldPresCreator(context))
        );
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
    private void goCheckBox() {
        go("view_check", new CheckBoxPresCreator(context));
    }

    private void go(String name, TutorialCreator tutorialCreator) {
        context.routes().putAndGo(new SimpleView(name, tutorialCreator));
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