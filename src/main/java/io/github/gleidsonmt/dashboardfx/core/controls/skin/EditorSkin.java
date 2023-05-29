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
import javafx.scene.Parent;
import javafx.scene.control.skin.TextFieldSkin;

/**
 * @author Gleidson Neves da Silveira | gleidisonmt@gmail.com
 * Create on  20/09/2022
 */
public class EditorSkin extends TextFieldSkin {

    private final Editor control;

    public EditorSkin(Editor _control) {
        super(_control);
        this.control = _control;

        Parent inFocus; // Change the focus when the parent is gntextbox
        if (control.getParent() instanceof GNTextBoxBase) {
            inFocus = control.getParent();
        } else inFocus = control;


        registerChangeListener(inFocus.focusedProperty(), c -> {
            if ((boolean) c.getValue()) {
                control.requestFocus();
            }
        });


    }

    @Override
    protected String maskText(String txt) {

        if (getSkinnable() instanceof Editor editor) {
            if (editor.isMaskText()) {

                final char BULLET = '\u25cf';
                int n = txt.length();

                return String.valueOf(BULLET).repeat(n);
            } else {
                return editor.textProperty().getValueSafe();
            }
        } else return txt;

    }

}
