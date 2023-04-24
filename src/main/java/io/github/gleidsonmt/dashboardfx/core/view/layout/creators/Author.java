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

package io.github.gleidsonmt.dashboardfx.core.view.layout.creators;

public class Author {

    private String name;
    private String gitUrl;
    private String documentation;

    public Author(String name) {
        this(name, null);
    }

    public Author(String name, String gitUrl) {
        this(name, gitUrl, null);
    }

    public Author(String name, String gitUrl, String documentation) {
        this.name = name;
        this.gitUrl = gitUrl;
        this.documentation = documentation;
    }

    public String getName() {
        return name;
    }

    public String getGitUrl() {
        return gitUrl;
    }

    public String getDocumentation() {
        return documentation;
    }
}
