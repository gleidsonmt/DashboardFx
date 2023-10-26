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

package io.github.gleidsonmt.dashboardfx.model;

import io.github.gleidsonmt.dashboardfx.factory.cells.Experience;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

import java.math.BigDecimal;

/**
 * @author Gleidson Neves da Silveira | gleidisonmt@gmail.com
 * Create on  21/10/2023
 */
public final class Developer extends Item {

    private final ObjectProperty<Status> status = new SimpleObjectProperty<>();
    private final ObjectProperty<Gender> gender = new SimpleObjectProperty<>();
    private final ObjectProperty<Experience> experience = new SimpleObjectProperty<>();

    private final StringProperty email = new SimpleStringProperty();

    private final ObjectProperty<BigDecimal> pricePerHour = new SimpleObjectProperty<>();

    public enum Gender { MALE, FEMALE };

    public Status getStatus() {
        return status.get();
    }

    public ObjectProperty<Status> statusProperty() {
        return status;
    }

    public void setStatus(Status status) {
        this.status.set(status);
    }

    public Gender getGender() {
        return gender.get();
    }

    public ObjectProperty<Gender> genderProperty() {
        return gender;
    }

    public void setGender(Gender gender) {
        this.gender.set(gender);
    }

    public Experience getExperience() {
        return experience.get();
    }

    public ObjectProperty<Experience> experienceProperty() {
        return experience;
    }

    public void setExperience(Experience experience) {
        this.experience.set(experience);
    }

    public String getEmail() {
        return email.get();
    }

    public StringProperty emailProperty() {
        return email;
    }

    public void setEmail(String email) {
        this.email.set(email);
    }

    public BigDecimal getPricePerHour() {
        return pricePerHour.get();
    }

    public ObjectProperty<BigDecimal> pricePerHourProperty() {
        return pricePerHour;
    }

    public void setPricePerHour(BigDecimal pricePerHour) {
        this.pricePerHour.set(pricePerHour);
    }

    @Override
    public String toString() {
        return super.getName();
    }
}
