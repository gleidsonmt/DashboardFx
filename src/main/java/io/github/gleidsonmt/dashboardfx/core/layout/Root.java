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
import io.github.gleidsonmt.dashboardfx.core.app.interfaces.IRoot;
import io.github.gleidsonmt.dashboardfx.core.app.interfaces.IWrapper;
import io.github.gleidsonmt.dashboardfx.core.layout.conteiners.SnackBar;
import javafx.scene.Parent;
import javafx.scene.layout.StackPane;

/**
 * @author Gleidson Neves da Silveira | gleidisonmt@gmail.com
 * Create on  04/10/2022
 */
public final class Root extends StackPane implements IRoot {

    private SnackBar snackBar;
    private final Layout layout;
    private final Wrapper wrapper;

    public Root(Context context) {

        layout = new Layout(context);
        wrapper = new Wrapper();

        layout.setMinSize(400, 400);

        getChildren().setAll(wrapper, layout);


        setId("root");


//        window.widthProperty().addListener((observable, oldValue, newValue) -> {
//            double drawerWidth = 250;
//            double _new = newValue.doubleValue();

//            needsBar.set(window.getContent().equals(this));
//
//            if (needsBar.get()) {
//                if (_new < BreakPoints.X_LARGE) {
//                    layout.setLeft(null);
//                    window.addControl(0, hamburger);
//                    layout.getBar().setPadding(new Insets(0,0,0,40));
//
//                } else {
//                    window.removeControl(hamburger);
//                    layout.setLeft(layout.getOldLeft());
//                    layout.getBar().setPadding(new Insets(0));
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
}
