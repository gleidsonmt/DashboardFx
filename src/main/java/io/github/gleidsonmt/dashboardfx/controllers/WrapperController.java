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

import io.github.gleidsonmt.dashboardfx.core.app.view_wrapper.ActionView;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.web.WebView;
import org.commonmark.node.Node;
import org.commonmark.parser.Parser;
import org.commonmark.renderer.html.HtmlRenderer;

import java.net.URL;
import java.util.ResourceBundle;

public class WrapperController implements ActionView, Initializable {

    @FXML
    private WebView webView;
    
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        Parser parser = Parser.builder().build();

        Node document = parser.parse("```java\n" +
                "  GNDecorator decorator = new GNDecorator();\n" +
                "  decorator.setTitle(\"JavaFx Application\");\n" +
                "  decorator.setContent(content);\n" +
                "  decorator.fullBody() // the content occupies all of size\n" +
                "    \n" +
                "  Menu menu = new Menu(\"File\");\n" +
                "  menu.getItems().add(new MenuItem(\"Open\"));\n" +
                "  menu.getItems().add(new MenuItem(\"Close\"));\n" +
                "  decorator.addMenu(menu);\n" +
                "  decorator.addMenu(1, menu);// add with a index\n" +
                "  \n" +
                "  ButtonTest a1 = new ButtonTest(\"Button 1\");\n" +
                "  decorator.addControl(a1);\n" +
                "  decorator.addControl(index, a1); // add with a index\n" +
                "  ```\n");

        HtmlRenderer renderer = HtmlRenderer.builder().build();
        renderer.render(document);


        String content =
                "Hello World!";

        webView.getEngine().loadContent(renderer.render(document));

    }

    public void openDrawerLeft(ActionEvent actionEvent) {
        
    }

    public void openDrawerRight(ActionEvent actionEvent) {
    }

    public void createAlertInfo(ActionEvent actionEvent) {
    }

    public void createAlertError(ActionEvent actionEvent) {
    }

    public void createAlertWarning(ActionEvent actionEvent) {
    }

    public void createAlertDone(ActionEvent actionEvent) {
    }

    public void createDialog(ActionEvent actionEvent) {
    }

    @Override
    public void onEnter() {

    }

    @Override
    public void onExit() {

    }
}
