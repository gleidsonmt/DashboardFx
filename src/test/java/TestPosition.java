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

import java.util.List;

/**
 * @author Gleidson Neves da Silveira | gleidisonmt@gmail.com
 * Version 0.0.1
 * Create on  10/01/2023
 */
public class TestPosition {

    public static void main(String[] args) {
        List<Winner> list = List.of(
                new Winner("Gleidson", 150),
                new Winner("Leonardo", 82),
                new Winner("Walisson", 56),
                new Winner("Daniel", 38),
                new Winner("Deusdete", 23)
        );

        int score = 70;
        int temp = 0;

       for (int i = 0; i < list.size(); i++) {
           if (score > list.get(i).score) {
               System.out.println("list = " + list.get(i));

//               if (i < (list.size() - 1))
//                pass = list.get(i + 1).score;
                temp = list.get(i).score;
                list.get(i).score = score;
                score = temp;
//               temp = list.get(i -1).score;
           }
       }
        
        System.out.println();


        printList(list);
    }

    private static void printList(List<Winner> winners) {
        winners.forEach(System.out::println);
    }


}

class Winner  {

    public String name;
    public int score;

    public Winner(String name, int score) {
        this.name = name;
        this.score = score;
    }

    @Override
    public String toString() {
        return "Winner{" +
                "name='" + name + '\'' +
                ", score=" + score +
                '}';
    }
}