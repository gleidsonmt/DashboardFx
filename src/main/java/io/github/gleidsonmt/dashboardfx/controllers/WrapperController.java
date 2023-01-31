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

import io.github.gleidsonmt.dashboardfx.core.app.services.Context;
import io.github.gleidsonmt.dashboardfx.core.app.view_wrapper.ActionView;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.web.WebView;
import org.commonmark.node.Node;
import org.commonmark.parser.Parser;
import org.commonmark.renderer.html.HtmlRenderer;
import org.jetbrains.annotations.ApiStatus;

import java.net.URL;
import java.util.ResourceBundle;

@ApiStatus.Experimental
public final class WrapperController implements ActionView {

    @FXML
    private WebView webView;

    @Override
    public void onEnter(Context context) {

    }

    @Override
    public void onExit(Context context) {

    }

    @Override
    public void onInit(Context context) {
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        Parser parser = Parser.builder().build();

        Node document = parser.parse("" +
                "<code>Random <code style='color: red'>r</code> = new Random();<code/> ");

        HtmlRenderer renderer = HtmlRenderer.builder().build();
        renderer.render(document);


        String _code = """
                context.getDecorator()
                \t.getRoot()
                \t.createSnackBar()
                \t.message("My Snack")
                \t.undo(event -> {
                \t.show();""";

        String random =
                new BlockHtmlParser().javaStringToHtml("Random r = new Random(12);" +
                        "\nr.setTitle(\"Welcome\");");
//

        String content = new BlockHtmlParser().javaStringToHtml(_code);

        webView.getEngine().loadContent(content);

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

}
