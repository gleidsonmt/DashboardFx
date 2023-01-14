/*
 *    Copyright (C) Gleidson Neves da Silveira
 *
 *    This program is free software: you can redistribute it and/or modify
 *    it under the terms of the GNU General Public License as published by
 *    the Free Software Foundation, either version 3 of the License, or
 *    (at your option) any later version.
 *
 *    This program is distributed in the hope that it will be useful,
 *    but WITHOUT ANY WARRANTY; without even the implied warranty of
 *    MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *    GNU General Public License for more details.
 *
 *    You should have received a copy of the GNU General Public License
 *    along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package io.github.gleidsonmt.dashboardfx.core.app.services;

import io.github.gleidsonmt.dashboardfx.core.app.interfaces.View;

import java.util.List;
import java.util.StringJoiner;

/**
 * @author Gleidson Neves da Silveira | gleidisonmt@gmail.com
 * Create on  28/12/2021
 */
public class ViewComposer {

    private String                  title;
    private String                  name;
    private String                  directory;
    private String                  fxml;
    private List<ViewComposer>      views;
    private ViewComposer            root;

    public ViewComposer() {

    }

    public ViewComposer(String title, String name) {
        this.title = title;
        this.name = name;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDirectory() {
        return directory;
    }

    public void setDirectory(String directory) {
        this.directory = directory;
    }

    public String getFxml() {
        return fxml;
    }

    public void setFxml(String fxml) {
        this.fxml = fxml;
    }

    public List<ViewComposer> getViews() {
        return views;
    }

    public void setViews(List<ViewComposer> views) {
        this.views = views;
    }

    public ViewComposer getRoot() {
        return root;
    }

    public void setRoot(ViewComposer root) {
        this.root = root;
    }

    @Override
    public String toString() {
        return new StringJoiner(", ", ViewComposer.class.getSimpleName() + "[", "]")
                .add("name='" + name + "'")
                .add("title='" + title + "'")
                .add("fxml='" + fxml + "'")
                .add("directory='" + directory + "'")
                .toString();
    }
}
