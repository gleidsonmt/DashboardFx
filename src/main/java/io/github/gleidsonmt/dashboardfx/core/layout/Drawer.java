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

package io.github.gleidsonmt.dashboardfx.core.layout;

import io.github.gleidsonmt.dashboardfx.core.layout.conteiners.creators.TutorialCreator;
import io.github.gleidsonmt.dashboardfx.views.TutorialUnderstanding;
import io.github.gleidsonmt.dashboardfx.views.WrappersView;
import io.github.gleidsonmt.dashboardfx.core.app.exceptions.NavigationException;
import io.github.gleidsonmt.dashboardfx.core.app.material.controls.ControlData;
import io.github.gleidsonmt.dashboardfx.core.app.material.controls.ControlViewPanel;
import io.github.gleidsonmt.dashboardfx.core.app.services.Context;
import io.github.gleidsonmt.dashboardfx.core.app.interfaces.View;
import io.github.gleidsonmt.dashboardfx.core.app.view_wrapper.ActionView;
import io.github.gleidsonmt.dashboardfx.core.layout.conteiners.creators.TutorialCreator1;
import io.github.gleidsonmt.dashboardfx.core.layout.conteiners.creators.DeclarativeComponent;
import io.github.gleidsonmt.dashboardfx.core.layout.conteiners.interfaces.NestedWrapperContainer;
import io.github.gleidsonmt.dashboardfx.views.AreaChartView;
import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.geometry.HPos;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.control.ToggleButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.StackPane;
import javafx.util.Duration;

import java.net.URL;
import java.util.ResourceBundle;

public class Drawer extends DeclarativeComponent<Drawer> implements ActionView, NestedWrapperContainer {

    @FXML private ToggleGroup group;
    @FXML private StackPane root;
    private final IWrapper wrapper;
    private StackPane content;
    private final Timeline timeline = new Timeline();

    private double maxSize = 250;
    private HPos side;
    private Context context;
    private final EventHandler<MouseEvent> closeEvent = event -> hide();

    public Drawer(IWrapper _wrapper) {
        this.wrapper = _wrapper;
    }

    public Drawer content(StackPane _content) {
        this.content = _content;
        _content.setMaxWidth(maxSize);
        return this;
    }

    @Override
    public Drawer style(String style) {
        this.content.setStyle(style);
        return this;
    }

    @Override
    public Drawer size(double width, double height) {
        return null;
    }

    public void show() {
        this.wrapper.setAlignment(
                side == HPos.LEFT ?
                        Pos.CENTER_LEFT : Pos.CENTER_RIGHT
        );

        final double tx = side == HPos.LEFT ?
                -content.getMaxWidth() :
                250;


        if (!this.wrapper.getChildren().contains(content))
            this.wrapper.getChildren().setAll(content);

        timeline.getKeyFrames().setAll(
                new KeyFrame(Duration.ZERO, new KeyValue(
                    content.translateXProperty(), tx
                )),
                new KeyFrame(Duration.millis(100), new KeyValue(
                        content.translateXProperty(), 0
                ))
        );

        this.timeline.setOnFinished(null);
        this.wrapper.setOnMouseClicked(closeEvent);
        this.wrapper.toFront();
        timeline.play();
    }

    public void hide() {

        final double tx = side == HPos.LEFT ?
                -content.getMaxWidth() :
                250;

        timeline.getKeyFrames().setAll(
                new KeyFrame(Duration.ZERO, new KeyValue(
                        content.translateXProperty(), 0
                )),
                new KeyFrame(Duration.millis(200), new KeyValue(
                        content.translateXProperty(), tx
                ))
        );

        timeline.setOnFinished(event -> {
            wrapper.getChildren().clear();
            content.setTranslateX(0);
            wrapper.toBack();
        });

        timeline.play();
    }



    @Override
    public Drawer side(HPos _side) {
        this.side = _side;
        return this;
    }

    @Override
    public void onEnter(Context context) {
        this.context = context;
    }

    @Override
    public void onExit(Context context) {

    }

    @Override
    public void onInit(Context context) {
        this.context = context;
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        new DrawerBehavior(root, group);
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

                .blockCode("""
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
