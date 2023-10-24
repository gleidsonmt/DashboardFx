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

package io.github.gleidsonmt.dashboardfx.tasks;

import io.github.gleidsonmt.dashboardfx.model.Developer;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.concurrent.Task;

import java.util.List;

/**
 * @author Gleidson Neves da Silveira | gleidisonmt@gmail.com
 * Create on  23/10/2023
 */
public class CreateDevelopers extends Task<ObservableList<Developer>> {
    private ObservableList<Developer> developers;
    public CreateDevelopers(ObservableList<Developer> developers) {
        this.developers = developers;
    }

    @Override
    protected ObservableList<Developer> call() throws Exception {

        Thread.sleep(1000);
        for (int i = 1; i <= 51; i++) {
            Developer developer = new Developer();
            developer.setName("Dev" + i);
            developers.add(developer);
        }

        return developers;
    }
}
