/*
 *    Copyright (C) Gleidson Neves da Silveira
 *
 *    This program is free software: you can redistribute it and/or modify
 *    it under the terms of the GNU General Public License as published by
 *    the Free Software Foundation, either version 3 of the License, or
 *    (at your option) any later version.
 *
 *    This program is distributed in the hope that it will be useful,
 *    but WITHOUT ANY WARRANTY; without even the implied warranty of
 *    MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *    GNU General Public License for more details.
 *
 *    You should have received a copy of the GNU General Public License
 *    along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */

package io.github.gleidsonmt.dashboardfx.core.layout;

import io.github.gleidsonmt.dashboardfx.core.app.services.Context;
import io.github.gleidsonmt.dashboardfx.core.app.interfaces.Layout;
import io.github.gleidsonmt.dashboardfx.core.app.interfaces.View;
import io.github.gleidsonmt.dashboardfx.core.app.view_wrapper.BreakPoints;
import io.github.gleidsonmt.gncontrols.controls.GNIconButton;
import io.github.gleidsonmt.gncontrols.material.icon.Icons;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;

/**
 * @author Gleidson Neves da Silveira | gleidisonmt@gmail.com
 * Create on  04/10/2022
 */
public final class ILayout extends BorderPane implements Layout {

    private final CenterLayout centerLayout = new CenterLayout();
//    private final GridPane bar = new GridPane();
    private final Text title        = new Text("SpeedCut");
    private final GNIconButton hamburger = new GNIconButton(Icons.HAMBURGER);

    private Node oldLeftNode;

    public ILayout(Context context) {

        setId("layout");

        hamburger.getStyleClass().addAll("btn-flat");

        leftProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue != null) oldLeftNode = newValue;
        });

//        hasDrawer.bind(leftProperty().isNull());

        getStyleClass().add("layout");

//        getChildren().add(0, bar);
//        setTop(bar);
//        bar.toBack();
        centerLayout.setPadding(new Insets(0, 0, 0,0));

        setCenter(centerLayout);

        widthProperty().addListener((observable, oldValue, newValue) -> {


            if (newValue.intValue() < BreakPoints.MEDIUM) {

                if (!getBar().containsInLeft(hamburger)){
                    getBar().addInLeft(0, hamburger);
                }
//                    setLeft(null);
//                    oldDrawer.setTranslateX(0);

//                    if (!context.getDecorator().controls().contains(iconButton)) {
//                        context.getDecorator().controls().add(iconButton);
//                    }

            } else {
                getBar().removeInLeft(hamburger);
//                    setLeft(oldDrawer);
//                    context.getDecorator().controls().remove(iconButton);

            }


        });

        hamburger.setOnMouseClicked(event -> {
            context.wrapper()
                    .getDrawer()
                    .content(oldDrawer)
                    .show();
        });
    }

    private StackPane oldDrawer = null;

    @Override
    public void setDrawer(View view) {
        oldDrawer = (StackPane) view.getRoot();
        setLeft(oldDrawer);
    }

    @Override
    public void setAside(View view) {

    }

    @Override
    public void setNav(View view) {

    }

    @Override
    public void setFooter(View view) {

    }

    @Override
    public void setBody(Node iView) {
        centerLayout.setBody((Parent) iView);
    }

    public Node getOldLeftNode() {
        return oldLeftNode;
    }

    public Bar getBar() {
        return centerLayout.getBar();
    }
}
