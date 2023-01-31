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

import org.jetbrains.annotations.ApiStatus;
import org.jetbrains.annotations.NotNull;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

@ApiStatus.Internal
@ApiStatus.Experimental
public final class BlockHtmlParser {

    private final String keyColor = "#0033B3;";
    private final String numberColor = "#9876AA";
    private final String constantColor = "#9876AA";
    private final String normalColor = "#434A54";
    private final String stringColor = "#067D17";
    private final String pre = "<code style='color:";
    private final String end = "'>";
    private final String suffix = "</code>";

    private List<String> convertInLines(String text) {
        List<String> list = text.lines().collect(Collectors.toList());
        for (int i = 0; i < list.size(); i++) {
            list.set(i, list.get(i).replaceAll(" ", "&nbsp;") );
        }
        return list;
    }

    private String convertInText(String text) {
        return text
//                .replaceAll("\\t", "&#9;")
                .replaceAll(" ", "&nbsp;")
                .replaceAll("(<)", "&lt;")
                .replaceAll("(>)", "&gt;")
                .replaceAll("(/)", "&sol;");
    }
    public String javaStringToFxml(String text) {
        text = convertInText(text);

        String styleBlock = "<code style='color:" + keyColor + ";'>";
        String styleCons = "<code style='color:" + constantColor + ";'>";
//        String styleCons = "<code style='color:pink;'>";
        String styleString = "<code style='color:" + stringColor + ";'>";
//        String styleString = "<code style='color:red;'>";
        String styleEnd = "</code>";

        String pBetween = "([a-zA-Z0-9#:\\-;.()%&, ]+)";

        text = text
                .replaceAll("(&nbsp;.+\\.[a-zA-Z]+|'" + pBetween + "'|\"" + pBetween + "\"|=)", styleString + "$1" + styleEnd)
                .replaceAll("(&lt;&sol;.+\\w.+&gt;|&lt;+\\w+|&lt;\\?|\\?&gt;|&sol;&gt;|&gt;|&lt;)", styleBlock + "$1" + styleEnd)
                .replaceAll("(fx)+(:id)", styleCons + "$1" + styleEnd + "$2")
//                .replaceAll("(&gt;|&sol;&gt;)", styleBlock + "$1" + styleEnd)
//                .replaceAll("(&lt;[^&]*)", styleBlock + "$1" + styleEnd)

//                .replaceAll("(<.[a-z]+&nbsp;)", styleString + "$1" + styleEnd)
//                .replaceAll("(&nbsp;.+\\.\\w+)", styleString + "$1" + styleEnd)
//                .replaceAll("(&nbsp;fx)", styleString + "$1" + styleEnd)
        ;

        String f = "<body style='background-color:#F5F7FA; ';><pre><code style='color:" + normalColor + "'>" + text + "</pre></code></body>";
//        f = f.replaceAll("([^ ][&lt;[a-zA-Z]]*. )", styleBlock + "$1" + styleEnd);

        return f;
    }

    public String javaStringToFxml2(String text) {
//        return  text;


        text = "<stylesheet> <fx fasdf style='color:blue'/>";
        text = text.replaceAll("(<)", "&lt;")
                .replaceAll("(>)", "&gt;")
                .replaceAll("(/)", "&sol;");
//        System.out.println("text = " + text.replaceAll("(.*[a-zA-Z ])",

        String _text =
            "<body style='background-color:#F5F7FA;'>" +
                    pre + normalColor + ';' + end +
                    text
                    .replaceAll("(^[&lt;[a-zA-Z;]]*)", "<code style='color:" + keyColor + "'>$1</code>" )
                            .replaceAll("(&sol;&gt;)", "<code style='color:" + keyColor + "'>$1</code>" )
                            .replaceAll("(&gt;)", "<code style='color:" + keyColor + "'>$1</code>" )
                    + suffix + "</body>";
        return _text;
    }

    public String javaStringToHtml(String text) {

//        text = text.replaceAll("\n", "</br>");
//#F5F7FA
        return "<body style='background-color:#F5F7FA;'>" +
                pre + normalColor + ';' + end + text
                .replaceAll("([^a-z0-9]\\d+)", "<code style='color:" + numberColor +  "'>$1</code>" )
                .replaceAll("([\".][a-zA-Z0-9 ]+[\".])", "<code style='color:" + stringColor + "'>$1</code>" )
//                .replaceAll("([A-Z.][A-Z]+[A-Z.])", "<code style='color:" + constantColor + "'>$1</code>" )
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

