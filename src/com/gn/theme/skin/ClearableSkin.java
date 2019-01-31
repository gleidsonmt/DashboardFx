/*
 * Copyright (C) Gleidson Neves da Silveira
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package com.gn.theme.skin;

import javafx.scene.Cursor;
import javafx.scene.control.TextField;

/**
 * @author Gleidson Neves da Silveira | gleidisonmt@gmail.com
 * Create on  23/11/2018
 * Version 1.0
 */
public class ClearableSkin extends SkinAction {

    public ClearableSkin(TextField textField) {
        super(textField);
    }

    @Override
    void mouseReleased() {
        getTextField().setText(""); // no null pointer
    }

    @Override
    void textChanged() {
        if(!getTextField().isFocused() && getTextField().getText() == null )
            return;
        getButton().setVisible(getTextField().isFocused() && !getTextField().getText().isEmpty());
    }

    @Override
    void focusChanged() {
        if(!getTextField().isFocused() && getTextField().getText() == null )
            return;
        getButton().setVisible(getTextField().isFocused() && !getTextField().getText().isEmpty());
    }

    private void altCursor(){
        getButton().setCursor(
                getButton().isVisible()
                        ? Cursor.HAND : Cursor.DEFAULT
        );
    }

    @Override
    void mousePressed() {
    }
}
