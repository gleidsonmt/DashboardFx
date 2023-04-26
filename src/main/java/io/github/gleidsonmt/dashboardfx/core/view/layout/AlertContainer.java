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

package io.github.gleidsonmt.dashboardfx.core.view.layout;

import io.github.gleidsonmt.dashboardfx.core.Context;
import io.github.gleidsonmt.dashboardfx.core.controls.GNButton;
import io.github.gleidsonmt.dashboardfx.core.interfaces.IAlert;
import io.github.gleidsonmt.dashboardfx.core.view.layout.creators.DeclarativeComponent;
import io.github.gleidsonmt.dashboardfx.core.view.layout.options.AlertType;
import io.github.gleidsonmt.dashboardfx.core.view.layout.options.DialogAction;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.ButtonBar;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import javafx.scene.shape.SVGPath;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;
import javafx.scene.text.TextFlow;
import org.jetbrains.annotations.NotNull;
import org.kordamp.ikonli.material.Material;

/**
 * @author Gleidson Neves da Silveira | gleidisonmt@gmail.com
 * Version 0.0.1
 * Create on  04/03/2023
 */
public class AlertContainer extends DeclarativeComponent<AlertContainer> implements IAlert {

    private String title;
    private String text;
    private final VBox body;
    private AlertType alertType = AlertType.WARNING;

    private ObservableList<DialogAction> dialogActions = FXCollections.observableArrayList();
    private Context context;

    public AlertContainer(Context context) {
        this.context = context;
        getStyleClass().add("container");
        setMaxSize(350, 300);
        getStyleClass().add("alert-container");

        this.body = new VBox();
        this.body.setAlignment(Pos.TOP_CENTER);
        body.getStyleClass().add("alert-body");

        getChildren().setAll(body);
    }

    @Override
    public AlertContainer title(String _title) {
        this.title = _title;
        return this;
    }

    @Override
    public AlertContainer text(String _text) {
        this.text = _text;
        return this;
    }

    @Override
    public AlertContainer actions(DialogAction... dialogActions) {
        this.dialogActions.setAll(dialogActions);
        return this;
    }

    @Override
    public AlertContainer type(AlertType _alertType) {
        this.alertType = _alertType;
        return this;
    }

    public AlertContainer build() {
        if (text == null || text.isEmpty()) {
            try {
                throw new Exception("Error : Alert needs a text information.");
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        if (title == null || title.isEmpty()) {
            title = alertType.name();
        }

        String linear1 = "";
        String linear2 = "";
        Material material = Material.WARNING;
        String buttonColor = "";

        switch (alertType) {
            case WARNING -> {
                linear1 = "-sunflower";
                linear2 = "-amber";
                buttonColor = "sunflower";
            }
            case INFO -> {
                linear1 = "-info";
                linear2 = "-aqua";
                buttonColor = "info";
                material = Material.INFO;
            }

            case ERROR -> {
                linear1 = "-grapefruit";
                linear2 = "-red";
                buttonColor = "grapefruit";
                material = Material.ERROR;
            }

            case SUCCESS -> {
                linear1 = "-success";
                linear2 = "-mint";
                buttonColor = "mint";
                material = Material.DONE;
            }
        }


        body.getChildren().setAll(
                createHeader(linear1, linear2, title),
                createContent(text),
                createFooter(buttonColor, dialogActions)
        );


//        AnimationFX animation = new Pulse(container);
//        animation.setSpeed(1.8);
//        animation.play();
        return this;
    }

    private @NotNull Node createContent(String _text) {
        TextFlow content = new TextFlow();
        content.setTextAlignment(TextAlignment.CENTER);

        content.setPadding(new Insets(20));

        Text text = new Text(_text);
        content.getChildren().add(text);
        text.getStyleClass().add("text-14");

        text.setTextAlignment(TextAlignment.CENTER);

        VBox.setVgrow(content, Priority.ALWAYS);

        return content;
    }

    private VBox createHeader(String linear1, String linear2, String _title) {

        VBox header = new VBox();
        header.setMinHeight(100);
        header.setSpacing(10);
        String color = "linear-gradient(to left, " + linear1 + ", " + linear2 + ");";
//        container.setStyle("-fx-background-color : " + color);
        this.setStyle("-fx-background-color: " + color);
        header.setAlignment(Pos.CENTER);

        Label title = new Label(_title);
        title.getStyleClass().addAll("h2", "sub");

        header.getChildren().addAll(createSymbol(alertType, color));
        return header;
    }

    private ButtonBar createFooter(String color, ObservableList<DialogAction> options) {
        ButtonBar buttonBar = new ButtonBar();

        options.forEach(e -> buttonBar.getButtons().add(createButton(color, e)));

        if (options.size() == 1) {
            buttonBar.setPadding(new Insets(10, 120, 10, 10));
        } else {
            buttonBar.setPadding(new Insets(10, 10, 10, 10));
        }
        return buttonBar;
    }

    private GNButton createButton(String color, DialogAction item) {
        GNButton btn = new GNButton();
        btn.setOnAction(item.getAction());
        btn.setText(item.getName());
        ButtonBar.setButtonData(btn, item.getButtonType().getButtonData());

        btn.addEventFilter(MouseEvent.MOUSE_CLICKED, event -> {
            this.context.wrapper().close();
        });

        btn.getStyleClass().add(color);

        switch (item.getButtonType().getButtonData()) {
            case YES, OK_DONE, FINISH, APPLY -> btn.setDefaultButton(true);
            case NO, CANCEL_CLOSE, BACK_PREVIOUS -> btn.setCancelButton(true);
        }
        return btn;
    }

    private @NotNull SVGPath createSymbol(AlertType type, String color) {
        SVGPath path = new SVGPath();
        path.setScaleX(1.8);
        path.setScaleY(1.8);

        path.setStyle("-fx-fill : " + color);

        switch (type) {
            case SUCCESS -> path.setContent("M21.05 33.1 35.2 18.95l-2.3-2.25-11.85 11.85-6-6-2.25 2.25ZM24 44q-4.1 0-7.75-1.575-3.65-1.575-6.375-4.3-2.725-2.725-4.3-6.375Q4 28.1 4 24q0-4.15 1.575-7.8 1.575-3.65 4.3-6.35 2.725-2.7 6.375-4.275Q19.9 4 24 4q4.15 0 7.8 1.575 3.65 1.575 6.35 4.275 2.7 2.7 4.275 6.35Q44 19.85 44 24q0 4.1-1.575 7.75-1.575 3.65-4.275 6.375t-6.35 4.3Q28.15 44 24 44Zm0-3q7.1 0 12.05-4.975Q41 31.05 41 24q0-7.1-4.95-12.05Q31.1 7 24 7q-7.05 0-12.025 4.95Q7 16.9 7 24q0 7.05 4.975 12.025Q16.95 41 24 41Zm0-17Z");
            case INFO -> path.setContent("M24.15 34q.65 0 1.075-.425.425-.425.425-1.075v-9.05q0-.6-.45-1.025Q24.75 22 24.15 22q-.65 0-1.075.425-.425.425-.425 1.075v9.05q0 .6.45 1.025.45.425 1.05.425ZM24 18.3q.7 0 1.175-.45.475-.45.475-1.15t-.475-1.2Q24.7 15 24 15q-.7 0-1.175.5-.475.5-.475 1.2t.475 1.15q.475.45 1.175.45ZM24 44q-4.25 0-7.9-1.525-3.65-1.525-6.35-4.225-2.7-2.7-4.225-6.35Q4 28.25 4 24q0-4.2 1.525-7.85Q7.05 12.5 9.75 9.8q2.7-2.7 6.35-4.25Q19.75 4 24 4q4.2 0 7.85 1.55Q35.5 7.1 38.2 9.8q2.7 2.7 4.25 6.35Q44 19.8 44 24q0 4.25-1.55 7.9-1.55 3.65-4.25 6.35-2.7 2.7-6.35 4.225Q28.2 44 24 44Zm0-20Zm0 17q7 0 12-5t5-12q0-7-5-12T24 7q-7 0-12 5T7 24q0 7 5 12t12 5Z");
            case WARNING -> path.setContent("M24.05 24.45ZM4.6 42q-.85 0-1.3-.75-.45-.75 0-1.5l19.4-33.5q.45-.75 1.3-.75.85 0 1.3.75l19.4 33.5q.45.75 0 1.5t-1.3.75Zm19.6-22.6q-.65 0-1.075.425-.425.425-.425 1.075v8.2q0 .65.425 1.075.425.425 1.075.425.65 0 1.075-.425.425-.425.425-1.075v-8.2q0-.65-.425-1.075-.425-.425-1.075-.425Zm0 16.75q.65 0 1.075-.425.425-.425.425-1.075 0-.65-.425-1.075-.425-.425-1.075-.425-.65 0-1.075.425Q22.7 34 22.7 34.65q0 .65.425 1.075.425.425 1.075.425ZM7.2 39h33.6L24 10Z");
            case ERROR -> path.setContent("M24.2 26.35q.6 0 1.025-.425.425-.425.425-1.075v-9.7q0-.6-.45-1.025-.45-.425-1.05-.425-.65 0-1.075.425-.425.425-.425 1.075v9.7q0 .6.45 1.025.45.425 1.1.425ZM24 34q.7 0 1.175-.475.475-.475.475-1.175 0-.7-.475-1.175Q24.7 30.7 24 30.7q-.7 0-1.175.475-.475.475-.475 1.175 0 .7.475 1.175Q23.3 34 24 34Zm0 10q-4.25 0-7.9-1.525-3.65-1.525-6.35-4.225-2.7-2.7-4.225-6.35Q4 28.25 4 24q0-4.2 1.525-7.85Q7.05 12.5 9.75 9.8q2.7-2.7 6.35-4.25Q19.75 4 24 4q4.2 0 7.85 1.55Q35.5 7.1 38.2 9.8q2.7 2.7 4.25 6.35Q44 19.8 44 24q0 4.25-1.55 7.9-1.55 3.65-4.25 6.35-2.7 2.7-6.35 4.225Q28.2 44 24 44Zm0-20Zm0 17q7 0 12-5t5-12q0-7-5-12T24 7q-7 0-12 5T7 24q0 7 5 12t12 5Z");
        }

        return path;
    }
}
