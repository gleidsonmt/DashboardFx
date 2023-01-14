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

import javafx.scene.control.Label;

import java.io.*;
import java.util.Objects;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author Gleidson Neves da Silveira | gleidisonmt@gmail.com
 * Version 0.0.1
 * Create on  12/01/2023
 */
public class ParseCssTest {

    public static void main(String[] args) {
        File file = new File(Objects.requireNonNull(ParseCssTest.class.getResource("/core.app/css/helpers.css")).getFile());

        BufferedReader br;
        try {
            br = new BufferedReader(new FileReader(file));


        String st;

//        Pattern pattern = Pattern.compile("(^\\..*[a-zA-Z0-9-].*[ a-zA-Z0-9.,-[^:]].*[^:] )");
//        Pattern pattern = Pattern.compile("(^\\..*[a-zA-Z0-9-])");
//        Pattern pattern = Pattern.compile("(^\\..*[a-zA-Z0-9-, ].[, ])");
//        Pattern pattern = Pattern.compile("([a-zA-Z0-9-,].*[^:].*,$)");
        Pattern pattern = Pattern.compile("(^\\.+\\w+[^:]+\\w)");

        while ((st = br.readLine()) != null) {
            String[] t = st.split(" ");
            for (String e : t) {
                Matcher matcher = pattern.matcher(e);
                System.out.println("e = " + e);
                if(matcher.find()) {
//                        System.out.println(matcher.group());
                    }
                }
            }

        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }

}
