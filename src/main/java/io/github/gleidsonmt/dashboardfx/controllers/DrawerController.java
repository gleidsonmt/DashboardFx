/*
 *
 *    Copyright (C) Gleidson Neves da Silveira
 *
 *    This program is free software: you can redistribute it and/or modify
 *    it under the terms of the GNU General Public License as published by
 *    the Free Software Foundation, either version 3 of the License, or
 *   (at your option) any later version.
 *
 *    This program is distributed in the hope that it will be useful,
 *    but WITHOUT ANY WARRANTY; without even the implied warranty of
 *    MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *    GNU General Public License for more details.
 *
 *     You should have received a copy of the GNU General Public License
 *     along with this program.  If not, see <http://www.gnu.org/licenses/>.
 *
 */

package io.github.gleidsonmt.dashboardfx.controllers;

import io.github.gleidsonmt.dashboardfx.core.app.controllers.Drawer;
import io.github.gleidsonmt.dashboardfx.core.app.exceptions.NavigationException;
import io.github.gleidsonmt.dashboardfx.core.app.interfaces.View;
import io.github.gleidsonmt.dashboardfx.core.app.interfaces.Wrapper;
import io.github.gleidsonmt.dashboardfx.core.app.material.controls.ControlData;
import io.github.gleidsonmt.dashboardfx.core.app.material.controls.ControlViewPanel;
import io.github.gleidsonmt.dashboardfx.core.app.services.Context;
import io.github.gleidsonmt.dashboardfx.core.layout.IWrapper;
import io.github.gleidsonmt.dashboardfx.core.layout.conteiners.creators.TutorialCreator;
import io.github.gleidsonmt.dashboardfx.views.*;
import io.github.gleidsonmt.dashboardfx.views.controls.ButtonPresCreator;
import io.github.gleidsonmt.dashboardfx.views.controls.HyperlinkPresCreator;
import io.github.gleidsonmt.dashboardfx.views.controls.LabelPresCreator;
import io.github.gleidsonmt.dashboardfx.views.controls.ListViewCreator;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.control.ToggleButton;

import java.net.URL;
import java.util.ResourceBundle;

/**
 * @author Gleidson Neves da Silveira | gleidisonmt@gmail.com
 * Version 0.0.1
 * Create on  19/01/2023
 */
public final class DrawerController extends Drawer {

    @Override
    public void onInit(Context context) {
        super.onInit(context);
//        super.wrapper = (IWrapper) context.wrapper();
        super.show();
    }

    @FXML
    private void goDash() {
        try {
            context.routes().setContent("dash");
        } catch (NavigationException e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void goNews() {
//        WebView webView = new WebView();
//        webView.getEngine().load("https://gleidsonmt.github.io");
//        Container container = new Container("newsletter");
//        container.getChildren().setAll(webView);
//        context.routes().registryAndGo(container);
    }

    @FXML
    private void goTutorial() {

        View builder = new TutorialCreator("Inicializando", context)
                .title("Exclusive selection vs. multiple selection")
                .text("""
                        By default, mat-button-toggle-group acts like a radio-button group- only one item can be selected. 
                        In this mode, the value of the mat-button-toggle-group will reflect the value of the selected button and ngModel is supported.
                        """)
                .text("""
                        Adding the multiple attribute allows multiple items to be selected (checkbox behavior). In this mode the values of the toggles are not used, the mat-button-toggle-group does not have a value, and ngModel is not supported.
                        """)
                .title("Appearance")
                .text("""
                        By default, the appearance of mat-button-toggle-group and mat-button-toggle will follow the latest Material Design guidelines. If you want to, you can switch back to the appearance that was following the previous Material Design spec by using the appearance input. The appearance can be configured globally using the MAT_BUTTON_TOGGLE_DEFAULT_OPTIONS injection token.
                        """)
                .title("Use with @angular/forms")
                .text("""
                        <mat-button-toggle-group> is compatible with @angular/forms and supports both FormsModule and ReactiveFormsModule.
                        """)
                .title("Orientation")
                .text("""
                        The button-toggles can be rendered in a vertical orientation by adding the vertical attribute.
                        """)

                .code("""
                    context.getWrapper()
                        .getDialog()
                        .content(
                            new StackPane(
                                new Label("Custom Dialog Wrapper"))
                            )
                        .show();
                """)
                .build();

        context.routes().registryAndGo(builder);


    }
    @FXML
    private void goUnderstanding() {
        context.routes().registryAndGo(
                new TutorialUnderstanding("Understanding", context));
    }

    @FXML
    private void goLabel(){
        context.routes().registryAndGo(new LabelPresCreator("Label", context));
    }

    @FXML
    private void goButton() {
        context.routes().registryAndGo(new ButtonPresCreator("Label", context));
    }

    @FXML
    private void goHyperlink() {
        context.routes().registryAndGo(new HyperlinkPresCreator("Hyperlink", context));
    }

    @FXML
    private void goListView() {
        context.routes().registryAndGo(new ListViewCreator("ListView", context));
    }

    @FXML
    private void goControlStyle() {
        Label node = new Label("Label");
        node.setUserData(new ControlData("lbl", "Label"));
        goPanel(node);
    }

    @FXML
    private void goToggleButton() {
        ToggleButton node = new ToggleButton("ToggleButton");
        node.setUserData(new ControlData("tg", "Toggle Button"));
        goPanel(node);
    }

    private ControlViewPanel controlViewPanel;
    private void goPanel(Node node) {
        if (controlViewPanel == null) controlViewPanel = new ControlViewPanel(context, node);
        else controlViewPanel.setNode(node);
        try {
            context.routes().registry("Label", controlViewPanel);
            context.routes().setContent("Label");
        } catch (NavigationException e) {
            throw new RuntimeException(e);
        }
    }

    private View presentation;
    @FXML
    private void goWrappers() {
        if(presentation == null) presentation = new WrappersView("presentation", context);
        try {
            context.routes().registry("presentation", presentation.getRoot());
            context.routes().setContent(presentation.getName());
        } catch (NavigationException e) {
            throw new RuntimeException(e);
        }
    }

    @FXML
    private void goTextField() {
        try {
            context.routes().setContent("control_view_pane");
        } catch (NavigationException e) {
            throw new RuntimeException(e);
        }
    }

    @FXML
    private void goAreaChart() {
        try {
            context.routes().registry("area-chart", new AreaChartView(context));
            context.routes().setContent("area-chart");
        } catch (NavigationException e) {
            throw new RuntimeException(e);
        }
    }

    @FXML
    private void goBuild() {
        try {
            context.routes().setContent("developers");
        } catch (NavigationException e) {
            e.getRouteNotFound(context, "view 'developers' not found");
        }
    }

    @FXML
    private void goAbout()  {
        try {
            context.routes().setContent("about");
        } catch (NavigationException e) {
            e.getRouteNotFound(context, "view 'aboout' not found");
        }
    }


}
