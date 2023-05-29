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

package io.github.gleidsonmt.dashboardfx.core.controls.options.model;

import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.scene.image.Image;

/**
 * @author Gleidson Neves da Silveira | gleidisonmt@gmail.com
 * Create on  23/01/2022
 */
public class Person extends Model {

    public Person() {
        this(null);
    }

    public Person(String name) {
        setName(name);
    }

    private ObjectProperty<Avatar> avatar = new SimpleObjectProperty<>();

    public Image getAvatar(int sizeInPixes) { // don't use double in sizePixels
        String path = avatar.get().getPath();
        if(path.contains("@")) {
            String extension = path.substring(path.lastIndexOf("."));
            String construct = path.substring(0, path.lastIndexOf("@"));
            return new Avatar(construct + "@" + sizeInPixes + extension);
        } else {
            return avatar.get();
        }
    }

    public Avatar getAvatar() {
        return avatar.get();
    }

    public ObjectProperty<Avatar> avatarProperty() {
        return avatar;
    }

    public void setAvatar(Avatar avatar) {
        this.avatar.set(avatar);
    }
}
