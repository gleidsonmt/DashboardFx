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

import io.github.gleidsonmt.dashboardfx.core.app.services.Context;
import io.github.gleidsonmt.dashboardfx.views.BlockCode;
import javafx.collections.ListChangeListener;
import javafx.scene.Node;
import org.jetbrains.annotations.NotNull;

import java.util.Objects;

/**
 * @author Gleidson Neves da Silveira | gleidisonmt@gmail.com
 * Version 0.0.1
 * Create on  13/01/2023
 */
public class BlockCopyControl {

    private final Node node;
    
    private final BlockCode javaBlock;
    private final BlockCode fxmlBlock;
    // cabeçalho
    private final StringBuilder javaHeader = new StringBuilder();
    private final StringBuilder fxmlHeader = new StringBuilder();
    // style class
    private String javaStyleClass = "";
    private String fxmlStyleClass = "";

    public BlockCopyControl(Context context, @NotNull Node _node) {
        this.node = _node;
        String clazz = _node.getClass().getSimpleName();
        ControlData controlData = (ControlData) _node.getUserData();

        javaHeader.append(clazz).append(" ").append(clazz.toLowerCase()).append(" = ").append("new ").append(clazz).append("(\"").append(controlData.name()).append("\");");
        fxmlHeader.append("<").append(clazz).append(" text=\"").append(clazz).append("\"/>");

        javaBlock = new BlockCode(context, javaHeader.toString(), false);
        fxmlBlock = new BlockCode(context, fxmlHeader.toString(), true);

        node.getStyleClass().addListener((ListChangeListener<String>) c -> {
            if (c.next()) {

                StringBuilder builderFxml = new StringBuilder();
                StringBuilder builderJava = new StringBuilder();

//                StringBuilder builder = new StringBuilder();

                builderJava.append("\n").append(clazz.toLowerCase()).append(".getStyleClass().setAll(");
                if (node.getStyleClass().size() > 2) {
                    builderFxml.append("\n").append("<styleClass>");
                    if (fxmlHeader.toString().contains("styleClass"))
                        fxmlHeader.delete(fxmlHeader.lastIndexOf(" "), fxmlHeader.length() - 2);
                } else {
                    if (node.getStyleClass().size() > 1)
                    fxmlHeader.insert(fxmlHeader.length() - 2 ,
                            " styleClass=\"" + c.getList().get(1) + "\"");
                    else {
                        fxmlHeader.delete(fxmlHeader.lastIndexOf(" "), fxmlHeader.length() - 2);
                    }
                }

                for (String each : c.getList()) {
                    if (!Objects.equals(each, clazz.toLowerCase())) {
                        builderJava.append(each).append(", ");
                        if (node.getStyleClass().size() > 2) {
                            builderFxml.append("\n\t").append("<String fx:value=\"").append(each).append("\"/>");
                        }
                    }
                }

                if (node.getStyleClass().size() > 2) {
                    builderFxml.append("\n").append("<styleClass/>");
                }

                builderJava.delete(builderJava.length() -2, builderJava.length()).append(");");

                javaStyleClass = builderJava.toString();
                fxmlStyleClass = builderFxml.toString();
                update();

            }
        });
    }

    public BlockCode getJavaBlock() {
        return javaBlock;
    }

    public BlockCode getFxmlBlock() {
        return fxmlBlock;
    }

    private void update() {
        //string final

        fxmlBlock.setContent(fxmlHeader + fxmlStyleClass);
        javaBlock.setContent(javaHeader + javaStyleClass);

    }
}
