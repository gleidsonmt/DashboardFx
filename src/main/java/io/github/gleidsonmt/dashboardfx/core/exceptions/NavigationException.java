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
package io.github.gleidsonmt.dashboardfx.core.exceptions;

import io.github.gleidsonmt.dashboardfx.core.Context;
import io.github.gleidsonmt.dashboardfx.core.view.SimpleView;
import io.github.gleidsonmt.dashboardfx.core.view.View;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.image.Image;
import javafx.scene.layout.*;
import javafx.scene.text.Text;
import org.jetbrains.annotations.NotNull;

import java.util.Objects;

/**
 * @author Gleidson Neves da Silveira | gleidisonmt@gmail.com
 * Create on  31/03/2023
 */
public final class NavigationException extends Exception {

    private String code;
    private Context context;

    private final VBox blank = new VBox();
    private final Text message = new Text();
    private View errorView;


    public NavigationException(Context context, String code, String message) {
        super(message);
        this.setCode(code);
        this.context = context;
        errorView = new SimpleView(
                "error_page", createRouteNotFound(null)
        );
    }

    public String getCode() {
        return code;
    }

    private void setCode(String code) {
        this.code = code;
    }



    private void setMessage(String _message) {
        message.setText(_message);
    }

    public StackPane createRouteNotFound(String message) {
        blank.setAlignment(Pos.BOTTOM_CENTER);
//        root.setPadding(new Insets(100));
        blank.setBackground(new Background(
                new BackgroundImage(
                        new Image(context.getResource("style/img/404.png").toExternalForm()),
                        BackgroundRepeat.NO_REPEAT,
                        BackgroundRepeat.NO_REPEAT,
                        BackgroundPosition.CENTER,
                        new BackgroundSize(
                                500, 400, false, false,
                                false, false
                        )
                )

        ));

        StackPane root = new StackPane(blank);
        if (!blank.getChildren().contains(this.message))
            blank.getChildren().add(this.message);

        root.setPadding(new Insets(100));

        return root;
    }

    public void getRouteNotFound(@NotNull Context context, String message) {
        if (context.routes().getView(errorView.getName()) == null) {
            setMessage(message);
            context.routes().put(errorView);
        }
//        try {
            context.routes().setView(errorView.getName());
//        } catch (NavigationException e) {
//            throw new RuntimeException(e);
//        }
    }
}
