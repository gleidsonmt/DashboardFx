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

package io.github.gleidsonmt.dashboardfx.core.controls.skin;

import io.github.gleidsonmt.dashboardfx.core.controls.text_box.Editor;
import io.github.gleidsonmt.dashboardfx.core.controls.text_box.FloatEditor;
import javafx.css.PseudoClass;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.VPos;
import javafx.scene.control.SkinBase;

/**
 * @author Gleidson Neves da Silveira | gleidisonmt@gmail.com
 * Create on  10/09/2022
 */
public class GNTextBoxBaseSkin extends SkinBase<GNTextBoxBase> {

    //Fixed
    private final GNTextBoxBase control;

    protected GNTextBoxBaseSkin(GNTextBoxBase _control) {
        super(_control);
        this.control = _control;

        if (_control.isAnimated()) {
            _control.setEditor(new FloatEditor());
            pseudoClassStateChanged(PseudoClass.getPseudoClass("animated"), true);
        } else {
            _control.setEditor(new Editor());
            pseudoClassStateChanged(PseudoClass.getPseudoClass("animated"), false);
        }

        if (_control.getEditor() != null) {

            _control.getEditor().textProperty().bindBidirectional(_control.textProperty());
            _control.getEditor().promptTextProperty().bindBidirectional(_control.promptTextProperty());
            _control.getEditor().maskTextProperty().bindBidirectional(_control.maskTextProperty());

        }

        control.editorProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue != null) {
                if (!getChildren().contains(newValue)){
                    getChildren().add(newValue);
                }
            }
        });

        if (control.getEditor() != null) {
            if (!getChildren().contains(control.getEditor()))
                getChildren().add(control.getEditor());
        }

        if (control.getRightNode() != null) {
            control.getRightNode().setManaged(false);
            getChildren().add(_control.getRightNode());
        }

        control.leftNodeProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue != null) {
                getChildren().add(1, newValue);
                newValue.setManaged(false);
            }
        });

        control.rightNodeProperty().addListener((observable, oldValue, newValue) -> {

            if (newValue == null) {
                    getChildren().remove(oldValue);
            }  else {
                if (!getChildren().contains(newValue))
                    getChildren().add(getChildren().size(), newValue);
            }
        });

        if (control.getLeftNode() != null) {
            control.getLeftNode().setManaged(false);
            getChildren().add( _control.getLeftNode());

            if (control.getEditor() instanceof FloatEditor floatEditor ) {

                floatEditor.setPadding(new Insets(4, 5, 4, 0));
                floatEditor.setDistanceX(-(25));

            }
        }
    }

    @Override
    protected void layoutChildren(double x, double y, double w, double h) {
//        super.layoutChildren(x, y, w, h);
//
        double edX;
        double edW;

       if (getChildren().contains(control.getRightNode()) && getChildren().contains(control.getLeftNode())) {

            edX = x +  control.getLeftNode().getBoundsInLocal().getWidth();
            edW = w - (control.getRightNode().getBoundsInLocal().getWidth() +
                    control.getLeftNode().getBoundsInLocal().getWidth() );

        } else if (getChildren().contains(control.getLeftNode())) {

            edX = x +  control.getLeftNode().getBoundsInLocal().getWidth() ;
            edW = w -  control.getLeftNode().getBoundsInLocal().getWidth() ;

        } else if (getChildren().contains(control.getRightNode())) {

            edX = x;
            edW = w - control.getRightNode().getBoundsInLocal().getWidth();

        } else {

            edX = x;
            edW = w;

        }

        if (getChildren().contains(control.getEditor())) {
           positionInArea(control.getEditor(), edX , y , edW, h, -1, HPos.LEFT, VPos.CENTER);
        }

        if (getChildren().contains(control.getLeftNode()))
            layoutInArea(control.getLeftNode(), x , y , w , h, -1, HPos.LEFT, VPos.CENTER);

        if (getChildren().contains(control.getRightNode())) {
            layoutInArea(control.getRightNode(), x, y, w, h, -1, HPos.RIGHT, VPos.CENTER);
        }

        if (control.getEditor() != null)
            control.getEditor().resize(edW, control.getHeight());

    }

    @Override
    protected double computePrefHeight(double width, double topInset, double rightInset, double bottomInset, double leftInset) {
        return 56;
    }


}
