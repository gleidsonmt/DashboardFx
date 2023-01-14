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

import javafx.scene.control.Button;
import javafx.scene.control.ContentDisplay;
import javafx.scene.shape.SVGPath;

public class SearchWorldButton extends Button {

    private SVGPath icon = new SVGPath();

    public SearchWorldButton() {
        icon.setContent("M12 22q-2.075 0-3.9-.788-1.825-.787-3.175-2.137-1.35-1.35-2.137-3.175Q2 14.075 2 12t.788-3.9q.787-1.825 2.137-3.175 1.35-1.35 3.175-2.138Q9.925 2 12 2q3.65 0 6.387 2.287 2.738 2.288 3.413 5.738h-2.05q-.475-1.825-1.712-3.263Q16.8 5.325 15 4.6V5q0 .825-.587 1.412Q13.825 7 13 7h-2v2q0 .425-.287.712Q10.425 10 10 10H8v2h2v3H9l-4.8-4.8q-.075.45-.138.9Q4 11.55 4 12q0 3.275 2.3 5.625T12 20Zm9.1-.5-3.2-3.2q-.525.3-1.125.5T15.5 19q-1.875 0-3.188-1.312Q11 16.375 11 14.5q0-1.875 1.312-3.188Q13.625 10 15.5 10q1.875 0 3.188 1.312Q20 12.625 20 14.5q0 .675-.2 1.275-.2.6-.5 1.125l3.2 3.2ZM15.5 17q1.05 0 1.775-.725Q18 15.55 18 14.5q0-1.05-.725-1.775Q16.55 12 15.5 12q-1.05 0-1.775.725Q13 13.45 13 14.5q0 1.05.725 1.775Q14.45 17 15.5 17Z");
        this.setGraphic(icon);
        this.setContentDisplay(ContentDisplay.GRAPHIC_ONLY);
        this.getStyleClass().addAll("transparent", "border-1");
    }
}
