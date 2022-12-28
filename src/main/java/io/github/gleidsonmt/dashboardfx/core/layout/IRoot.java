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

import io.github.gleidsonmt.dashboardfx.core.app.interfaces.Layout;
import io.github.gleidsonmt.dashboardfx.core.app.interfaces.Root;
import io.github.gleidsonmt.dashboardfx.core.app.services.Context;
import io.github.gleidsonmt.dashboardfx.core.app.view_wrapper.BreakPoints;
import io.github.gleidsonmt.dashboardfx.core.layout.conteiners.SnackBar;
import javafx.geometry.Insets;
import javafx.scene.Parent;
import javafx.scene.layout.StackPane;
import org.jetbrains.annotations.ApiStatus;

/**
 * @author Gleidson Neves da Silveira | gleidisonmt@gmail.com
 * Create on  04/10/2022
 */
@ApiStatus.Internal
public final class IRoot extends StackPane implements Root {

    private SnackBar snackBar;
    private final ILayout layout;
    private final IWrapper wrapper;

    public IRoot(Context context) {

        layout = new ILayout(context);
        wrapper = new IWrapper();

        layout.setMinSize(400, 400);

        getChildren().setAll(wrapper, layout);
        setId("root");
        setPadding(new Insets(2));
        setStyle("-fx-border-color : -light-gray-2; -fx-border-width : 1px;");

        widthProperty().addListener((obs, oldValue, newValue) -> {
            double drawerWidth = 250;
            if (newValue.doubleValue() < BreakPoints.MEDIUM) {
                if (layout.getLeft() == null) return;
                layout.setLeft(null);
            } else {
                if (layout.getLeft() == layout.getOldLeftNode()) return;
                layout.setLeft(layout.getOldLeftNode());
            }
        });

//        window.widthProperty().addListener((observable, oldValue, newValue) -> {
//            double drawerWidth = 250;
//            double _new = newValue.doubleValue();

//            needsBar.set(window.getContent().equals(this));
//
//            if (needsBar.get()) {
//                if (_new < BreakPoints.X_LARGE) {
//                    ILayout.setLeft(null);
//                    window.addControl(0, hamburger);
//                    ILayout.getBar().setPadding(new Insets(0,0,0,40));
//
//                } else {
//                    window.removeControl(hamburger);
//                    ILayout.setLeft(ILayout.getOldLeft());
//                    ILayout.getBar().setPadding(new Insets(0));
//                }
//            }
//
//        });

    }

    @Override
    public void setTitle(String title) {

    }

    @Override
    public SnackBar createSnackBar() {
        if (snackBar == null) snackBar = new SnackBar(this);
        return snackBar;
    }

    @Override
    public void setContent(Parent content) {
        layout.setBody(content);
    }

    @Override
    public IWrapper getWrapper() {
        return wrapper;
    }

    @Override
    public Layout getLayout() {
        return layout;
    }

    @Override
    public Bar bar() {
        return layout.getBar();
    }
}
