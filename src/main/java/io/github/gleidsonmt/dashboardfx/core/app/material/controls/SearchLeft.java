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

package io.github.gleidsonmt.dashboardfx.core.app.material.controls;

import io.github.gleidsonmt.gncontrols.controls.GNTextBox;
import io.github.gleidsonmt.gncontrols.material.icon.Icons;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.layout.Priority;
import javafx.scene.layout.TilePane;
import javafx.scene.layout.VBox;

import java.io.*;
import java.util.List;
import java.util.Objects;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

/**
 * @author Gleidson Neves da Silveira | gleidisonmt@gmail.com
 * Version 0.0.1
 * Create on  10/01/2023
 */
public class SearchLeft extends VBox {

    private GNTextBox searchField = new GNTextBox();


    private TilePane tilePane = new TilePane();
    private ScrollPane container = new ScrollPane();
    private VBox body = new VBox();

    private void read(Node control, File... files) {
        try {
            for (File file : files) {
                BufferedReader br
                        = new BufferedReader(new FileReader(file));

                String st;
                // Condition holds true till
                // there is character in a string

                Pattern pattern = Pattern.compile("(^\\.+\\w+[^:]+\\w)");
                while ((st = br.readLine()) != null) {

                    String[] t = st.split(" ");

                    for (String e : t) {
                        Matcher matcher = pattern.matcher(e);
                        if(matcher.find()) {
                            ToggleButton label = new ToggleButton(matcher.group().replaceAll("[.]", ""));
                            label.getStyleClass().add("badge-search");
                            tilePane.getChildren().add(label);
                            label.selectedProperty().addListener((observable, oldValue, newValue) -> {
                                if (newValue) {
                                    control.getStyleClass().add(label.getText());
                                } else {
                                    control.getStyleClass().remove(label.getText());

                                }
                            });
                        }
                    }

//                Matcher matcher = pattern.matcher(st);

                }

                searchField.setIcon(Icons.SEARCH);

                tilePane.setHgap(5);
                tilePane.setVgap(5);
                body.setSpacing(10);
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        container.setContent(tilePane);
        this.getChildren().setAll(searchField, container);

        this.setSpacing(10);
        container.setFitToWidth(true);
        container.setFitToHeight(true);
        tilePane.setTileAlignment(Pos.CENTER_LEFT);
//        tilePane.setPrefTileWidth(50);

    }

    public SearchLeft(Node control) {
        read(control,
                new File(Objects.requireNonNull(getClass().getResource("/core.app/css/helpers.css")).getFile()),
                new File(Objects.requireNonNull(getClass().getResource("/core.app/css/shapes.css")).getFile()));

    }

}
