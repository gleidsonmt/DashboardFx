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

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author Gleidson Neves da Silveira | gleidisonmt@gmail.com
 * Version 0.0.1
 * Create on  12/01/2023
 */
public class ParseMin {
    public static void main(String[] args) {
        String test = ".raised:pressed, " +
                ".zaised adf -fx-border : 0px; .border-1 " +
                ".border-r-1";
        String[] array = test.split(" ");
//        Pattern pattern = Pattern.compile("([^:]+\\w)");
        Pattern pattern = Pattern.compile("(^\\.+\\w+[^:]+\\w)");
        for (String t : array) {
            Matcher matcher = pattern.matcher(t);

//            System.out.println(t);

            if (matcher.find()) {
                System.out.println("matcher = " + matcher.group());
            }
        }

    }
}
