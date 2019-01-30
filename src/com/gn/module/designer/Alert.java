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
package com.gn.module.designer;

import com.gn.global.util.Alerts;
import javafx.fxml.FXML;

/**
 * @author Gleidson Neves da Silveira | gleidisonmt@gmail.com
 * Create on  30/01/2019
 */
public class Alert {

    @FXML
    private void info(){
        Alerts.info("Info", "Lorem ipsum dolor color.");
    }

    @FXML
    private void warning(){
        Alerts.warning("Warning", "Lorem ipsum dolor color.");
    }

    @FXML
    private void success(){
        Alerts.success("Success", "Lorem ipsum dolor color.");
    }

    @FXML
    private void error(){
        Alerts.error("Error", "Lorem ipsum dolor color.");
    }
}
