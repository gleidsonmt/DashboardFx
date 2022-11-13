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

import io.github.gleidsonmt.dashboardfx.core.app.interfaces.ILayout;
import io.github.gleidsonmt.dashboardfx.core.app.interfaces.IView;
import io.github.gleidsonmt.dashboardfx.core.layout.conteiners.CenterLayout;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;

/**
 * @author Gleidson Neves da Silveira | gleidisonmt@gmail.com
 * Create on  04/10/2022
 */
public final class Layout extends BorderPane implements ILayout {

    private final CenterLayout centerLayout = new CenterLayout();
//    private final GridPane bar = new GridPane();
    private final FlowPane bar          = new FlowPane();
    private final Text title        = new Text("SpeedCut");

    public Layout() {
        setId("layout");

//        leftProperty().addListener((observable, oldValue, newValue) -> {
//            if (newValue != null) oldLeftNode = newValue;
//        });

//        hasDrawer.bind(leftProperty().isNull());

        getStyleClass().add("layout");

        //        getChildren().add(0, bar);
        centerLayout.setPadding(new Insets(0, 0, 0,0));

        bar.setPrefHeight(40);
        bar.setMinHeight(40);

        bar.getStyleClass().addAll("border", "border-b-1");

        VBox.setMargin(bar, new Insets(0, 10, 0, 10));

        title.setStyle("-fx-font-size : 18pt; -fx-fill : -info;");

        title.setMouseTransparent(true);
        bar.getChildren().add(title);

        setCenter(centerLayout);
//        setLeft(drawerBody);

        widthProperty().addListener(new ChangeListener<Number>() {
            @Override
            public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {
                System.out.println(newValue);

                Number n = 10;


            }
        });
    }



    @Override
    public void setDrawer(IView iView) {
        setLeft(iView.getRoot());
    }

    @Override
    public void setAside(IView iView) {

    }

    @Override
    public void setNav(IView iView) {

    }

    @Override
    public void setFooter(IView iView) {

    }

    @Override
    public void setBody(Node iView) {
        centerLayout.setBody((Parent) iView);
    }
}
