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

package io.github.gleidsonmt.dashboardfx.views.controls;

import io.github.gleidsonmt.dashboardfx.core.Context;
import io.github.gleidsonmt.dashboardfx.core.view.layout.creators.TutorialCreator;
import javafx.geometry.Insets;
import javafx.geometry.NodeOrientation;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.ContentDisplay;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;

import java.util.List;

/**
 * @author Gleidson Neves da Silveira | gleidisonmt@gmail.com
 * Create on  22/06/2023
 */
public class RadioButtonPresCreator extends TutorialCreator {
    public RadioButtonPresCreator(Context context) {
        super(context);
        this
                .title("Intro")
                .text("RadioButtons create a series of items where only one item can be selected. RadioButtons are a specialized ToggleButton. When a RadioButton is pressed and released a ActionEvent is sent. Your application can perform some action based on this event by implementing an EventHandler to process the ActionEvent.")
                .demonstration(new RadioButton("RadioButton"), """
                        //Constructor
                        RadioButton radioBtn = new RadioButton("Radio Button");
                        """)
                .title("Groups")
                .text("A class which contains a reference to all Toggles whose selected variables should be managed such that only a single Toggle within the ToggleGroup may be selected at any one time.")
                .demonstration(
                        List.of(
                                createSimpleList(),
                                createRightList(),
                                createStorage(),
                                createColorList()
                        ),
                        """
                        """)
                .title("Links")
                .footer(createDefaultAuthor("RadioButton"))
                .build();

    }

    private Node createColorList() {
        HBox box = new HBox();
        box.setPrefWidth(500);
        box.setSpacing(20);
        RadioButton danger = new RadioButton("Danger");
        danger.getStyleClass().addAll("radio-color", "radio-danger");
        RadioButton info = new RadioButton("Danger");
        info.getStyleClass().addAll("radio-color", "radio-info");
        RadioButton success = new RadioButton("Danger");
        success.getStyleClass().addAll("radio-color", "radio-success");
        RadioButton warning = new RadioButton("Danger");
        warning.getStyleClass().addAll("radio-color", "radio-warning");
        RadioButton secondary = new RadioButton("Danger");
        secondary.getStyleClass().addAll("radio-color", "radio-secondary");
        box.getChildren().setAll(danger, info, warning, success, secondary);
        return box;
    }

    private Node createSimpleList() {
        VBox body = new VBox();
        Text title = new Text("Notifications");
        title.getStyleClass().addAll("h5", "text-bold");
        Text legend = new Text("How do you prefer to receive notifications?");
        legend.getStyleClass().addAll("h5");
        ToggleGroup group = new ToggleGroup();
        RadioButton email = new RadioButton("Email");
        RadioButton phone = new RadioButton("Phone (SMS)");
        RadioButton push = new RadioButton("Push notification");
        body.setSpacing(10);
        body.getChildren().setAll(title, legend, email, phone, push);
        group.getToggles().addAll(email, phone, push);
        return body;
    }

    private Node createRightList() {
        VBox body = new VBox();
        Text title = new Text("Transfer funds");
        title.getStyleClass().addAll("h5", "text-bold");
        Text legend = new Text("Transfer your balance to your bank account");
        legend.getStyleClass().addAll("h5");
        ToggleGroup group = new ToggleGroup();
        RadioButton checking = new RadioButton("Email");
        RadioButton saving = new RadioButton("Phone (SMS)");
        RadioButton mastercard = new RadioButton("Push notification");

        checking.setContentDisplay(ContentDisplay.GRAPHIC_ONLY);
        saving.setContentDisplay(ContentDisplay.GRAPHIC_ONLY);
        mastercard.setContentDisplay(ContentDisplay.GRAPHIC_ONLY);

        checking.setNodeOrientation(NodeOrientation.RIGHT_TO_LEFT);
        saving.setNodeOrientation(NodeOrientation.RIGHT_TO_LEFT);
        mastercard.setNodeOrientation(NodeOrientation.RIGHT_TO_LEFT);

        checking.setGraphic(createGraphic("Checking", "CIBC ••••6610"));
        saving.setGraphic(createGraphic("Savings", "Bank of America ••••0149"));
        mastercard.setGraphic(createGraphic("Mastercard", "Capital One ••••7877"));

        body.setSpacing(10);
        body.getChildren().setAll(title, legend, checking, saving, mastercard);
        group.getToggles().addAll(checking, saving, mastercard);

        return body;
    }

    private Node createStorage() {
        HBox body = new HBox();
        VBox box = new VBox();

        Text title = new Text("Storage");
        title.getStyleClass().addAll("h5", "text-bold");
        Text legend = new Text("Transfer your balance to your bank account");
        legend.getStyleClass().addAll("h5");
        ToggleGroup group = new ToggleGroup();

        RadioButton four = new RadioButton("4 GB");
        RadioButton eight = new RadioButton("8 GB");
        RadioButton sixteen = new RadioButton("16 GB");
        RadioButton thirtyTwo = new RadioButton("32 GB");
        RadioButton sixtyFour = new RadioButton("64 GB");

        body.setSpacing(20);

        group.getToggles().setAll(four, eight, sixteen, thirtyTwo,  sixtyFour);
        body.getChildren().setAll(four, eight, sixteen, thirtyTwo, sixtyFour);
        box.getChildren().addAll(title, body);

        return box;
    }

    private Node createGraphic(String text, String legend) {
        VBox box = new VBox();
        Text title = new Text(text);
        Text leg = new Text(legend);
        box.setPrefWidth(200);
        box.setAlignment(Pos.CENTER_RIGHT);
//        box.setPadding(new Insets(10));
        box.getChildren().setAll(title, leg);
        return box;

    }
}
