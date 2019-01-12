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
package com.gn.global.plugin;

import com.gn.global.Section;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Properties;

/**
 * @author Gleidson Neves da Silveira | gleidisonmt@gmail.com
 * Create on  27/11/2018
 */
public class SectionManager {

    private SectionManager(){

    }

    public static Section get(){
        try {
            File file = new File("dashboard.properties");
            Properties properties = new Properties();

            if(!file.exists()){
                file.createNewFile();
            }

            FileInputStream inputStream = new FileInputStream(file);
            properties.load(inputStream);

            return new Section(Boolean.valueOf(properties.getProperty("logged")), properties.getProperty("userLogged"));
        } catch (IOException e){
            e.printStackTrace();
            return null;
        }
    }

    public static void save(Section section) {
        try {
            File file = new File("dashboard.properties");
            Properties properties = new Properties();

            FileInputStream inputStream = new FileInputStream(file);
            properties.load(inputStream);

            FileOutputStream outputStream = new FileOutputStream(file);


            properties.setProperty("logged", String.valueOf(section.isLogged()));
            properties.setProperty("userLogged", section.getUserLogged());
            properties.store(outputStream, "Update Section");

        } catch (IOException e){
            e.printStackTrace();
        }
    }

}
