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

import io.github.gleidsonmt.dashboardfx.controllers.BlockHtmlParser;

import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class BlockParser {

    private static List<String> convertInLines(String text) {
        List<String> list = text.lines().collect(Collectors.toList());
        for (int i = 0; i < list.size(); i++) {
//            list.set(i, list.get(i).replaceAll(" ", "&nbsp;") + "</br>");
        }
        return list;
    }

    private static String convertInText(String text) {
        return
            text
//                .replaceAll(" ", "&#9;")
//                .replaceAll(" ", "&nbsp;")
//                .replaceAll("(<)", "&lt;")
//                .replaceAll("(>)", "&gt;")
//                .replaceAll("(/)", "&sol;")
                ;
    }

    public static void main(String[] args) {
        BlockHtmlParser parser = new BlockHtmlParser();

        String text = convertInText("""
                    <?xml version='1.0' encoding='UTF-8'?>

                    <?import javafx.scene.control.Label?>
                    <Label />
                        <Button&nbsp;fx:id='my' text='-fx-stroke:red;' asdf />
                    <Label style="-fx-stroke-dash-offset: 30; -fx-stroke-dash-array: 200; -fx-stroke: derive(-aqua, 50%);"/>
                    </StackPane>
                    """);


//        System.out.println(text.replaceAll("(.*fd$)", "<$1>"));
//        System.out.println(text.replaceAll("(^<[a-z]*)", "/$1/"));
//        System.out.println(text.replaceAll("(<[^ ]*)", "/$1/"));// coool
//        System.out.println(text.replaceAll("(=.*\")", "/$1/"));
//        (&lt;[^&]*)
        text = text

//                .replaceAll("(fx)+(:id)", "z$1$2")
                .replaceAll("(\"[a-zA-Z0-9#:\\-;.()%, ]+\")", "x$1x")
//                .replaceAll("('[a-zA-Z0-9-]+')", "x$1x")

//                .replaceAll("(<\\?|\\?>|/>|>|<)", "/$1/")  //jk
//                .replaceAll("(\\w.*.[.]? )", "/$1/")
//                .replaceAll("(&nbsp;.+\\.\\w+)", "/$1/")
//                .replaceAll("(.+\\..\\w+)", "/$1/")
                ;

        String re = "gleidsonmt@hotmail.com";

//        <?import javafx.scene.shape.Circle ?>


        System.out.println("text = " + text);

        List<String> lines = convertInLines(text);
        StringBuilder builder = new StringBuilder();

        for (String z : lines  ) {
//            z = z.replaceAll("(^&lt;[a-zA-Z]*)", "/$1/");
//            z = z.replaceAll("([^ ][&lt;[a-zA-Z]]*. )", "<$1>");
//            z = z.replaceAll("(^*. [&lt;][a-zA-Z]* )", "<$1>");
//            z = z.replaceAll("(/^[ \\t]+$/)", "<$1>");
//            z = z.replaceAll("(.l$)", "<$1>");
//            z = z.replaceAll("(^L.*l$)", "<$1>");
//            z = z.replaceAll("(&gt;)", "/$1/");
//            z = z.replaceAll("(&sol;)", "/$1/");
//            z = z.replaceAll("(['.].[a-zA-Z0-9., ]+['.])", "zz");
//            z = z.replaceAll(".*&gt;$", "/x/");
            builder.append(z);
        }
//        System.out.println("z = " + builder.toString());


    }
    
}
