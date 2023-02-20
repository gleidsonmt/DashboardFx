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

import java.util.Arrays;
import java.util.regex.Pattern;

/**
 * @author Gleidson Neves da Silveira | gleidisonmt@gmail.com
 * Version 0.0.1
 * Create on  22/01/2023
 */
public class TestNumbers {
    public static void main(String[] args) {

        String test1 = " 665d"; // true
        String test2 = "ANDREIA TEM 663"; // true
        String test3 = "665"; // true
        String test4 = "665.24f"; // true
//        String test5 = " 665d "; // true
//        String test6 = "65k"; // false
//        String test7 = "65f"; // true
//        String test8 = "65l "; // true
//?[ ]*[dfhl]$
        Pattern pattern = Pattern.compile("^[ 0-9]+(?:[.0-9]+)*[dfhl]");  // it's a number

        System.out.println("test1 = " + pattern.matcher(test1).matches());
        System.out.println("test2 = " + pattern.matcher(test2).matches());
        System.out.println("test3 = " + pattern.matcher(test3).matches());
        System.out.println("test4 = " + pattern.matcher(test4).matches());


//        System.out.println("test5 = " + pattern.matcher(test5).find());
//        System.out.println("test6 = " + pattern.matcher(test6).find());
//        System.out.println("test7 = " + pattern.matcher(test7).find());
//        System.out.println("test8 = " + pattern.matcher(test8).find());


        System.out.println();
        System.out.println("-------------------------------------------");
        System.out.println();

        double x = 2.4;
        double z = 8;
        double y = 34.2f;

    }
}
