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

import org.jetbrains.annotations.NotNull;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class BlockHtmlParser {

    private final String keyColor = "#CC7832";
    private final String numberColor = "#9876AA";
    private final String constantColor = "#9876AA";
    private final String normalColor = "#707D95";
    private final String stringColor = "#6A8759";
    private final String pre = "<code style='color:";
    private final String end = "'>";
    private final String suffix = "</code>";

    public String javaStringToHtml(String text) {
//#F5F7FA
        return "<body style='background-color:#F5F7FA;'>" +
                pre + normalColor + ';' + end + text
                .replaceAll("([^a-z0-9]\\d+)", "<code style='color:" + numberColor +  "'>$1</code>" )
                .replaceAll("([\".][a-zA-Z0-9 ]+[\".])", "<code style='color:" + stringColor + "'>$1</code>" )
                .replaceAll("([A-Z.][A-Z]+[A-Z.])", "<code style='color:" + constantColor + "'>$1</code>" )
                .replaceAll("([\"\"\" .].[a-zA-Z0-9., ]+[\"\"\". ])", "<code style='color:" + stringColor + "'>$1</code>" )
                // key chars
                .replaceAll(";", pre + keyColor + end + ";" + suffix)
                .replaceAll("new", pre + keyColor + end + "new" + suffix)
                // Escapes chars
                .replaceAll("\n", "</br>")
                .replaceAll("( ) ", "&nbsp;")
                .replaceAll("context", "<code style='color: " + constantColor + ";'><i>" + "$0" + "</i></code>")
//                .replaceAll("\t", "&emsp;")
                + suffix + "</body>";
    }

}

