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

package io.github.gleidsonmt.dashboardfx.factory.tasks;

import io.github.gleidsonmt.dashboardfx.core.Context;
import io.github.gleidsonmt.dashboardfx.factory.cells.Experience;
import io.github.gleidsonmt.dashboardfx.model.Developer;
import io.github.gleidsonmt.dashboardfx.model.Status;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.concurrent.Task;

import java.math.BigDecimal;
import java.util.Random;

/**
 * @author Gleidson Neves da Silveira | gleidisonmt@gmail.com
 * Create on  23/10/2023
 */
public class CreateDevelopers extends Task<ObservableList<Developer>> {

    private final ObservableList<Developer> developers;
    private final Context context;

    public CreateDevelopers(Context context, ObservableList<Developer> developers) {
        this.developers = developers;
        this.context = context;
    }

    private final ObservableList<String> maleNames = FXCollections.observableArrayList(

            "Liam",
            "Noah",
            "William",
            "James",
            "Oliver",
            "Benjamin",
            "Elijah",
            "Lucas",
            "Mason",
            "Logan"
    );

    private final ObservableList<String> femaleNames = FXCollections.observableArrayList(

            "Olivia",
            "Emma",
            "Ava",
            "Sophia",
            "Isabella",
            "Mia",
            "Charlotte",
            "Amelia",
            "Harper",
            "Evelyn"
    );



    private final Random random = new Random();

    private String randomMaleNames() {
        return maleNames.get(random.nextInt(maleNames.size() - 1));
    }

    private String randomFemaleNames() {
        return femaleNames.get(random.nextInt(femaleNames.size() - 1));
    }

    private Developer.Gender randomGender() {
        Developer.Gender[] gender = Developer.Gender.values();
        return gender[random.nextInt(gender.length)];
    }

    private String avatar(Developer developer) {
        int test = random.nextInt(5);
        String image = "";

        if (developer.getGender().equals(Developer.Gender.MALE)) {
            if (test == 0) {
                image = context.getResource("style/avatars/man@50.png").toExternalForm();
            } else {
                image = context.getResource("style/avatars/man" + test + "@50.png").toExternalForm();
            }
        } else {
            if (test == 0) {
                image = context.getResource("style/avatars/woman@50.png").toExternalForm();
            } else {
                image = context.getResource("style/avatars/woman" + test + "@50.png").toExternalForm();
            }
        }
        return image;
    }

    public Status randomStatus()  {
        Status[] status = Status.values();
        return status[random.nextInt(status.length)];
    }

    public Experience randomExperience()  {
        Experience[] status = Experience.values();
        return status[random.nextInt(status.length)];
    }

    public BigDecimal generateRandomBigDecimalFromRange(BigDecimal min, BigDecimal max) {
        BigDecimal randomBigDecimal = min.add(new BigDecimal(Math.random()).multiply(max.subtract(min)));
        return randomBigDecimal.setScale(2,BigDecimal.ROUND_HALF_UP);
    }

    @Override
    protected ObservableList<Developer> call() throws Exception {

        for (int i = 1; i <= 51; i++) {

            Developer developer = new Developer();

            switch (randomGender()) {
                case MALE -> {
                    developer.setGender(Developer.Gender.MALE);
                    developer.setName(randomMaleNames());
                }
                case FEMALE -> {
                    developer.setGender(Developer.Gender.FEMALE);
                    developer.setName(randomFemaleNames());
                }
            }

            developer.setEmail(developer.getName() + "@gmail.com");
            developer.setExperience(randomExperience());
            developer.setAvatar(avatar(developer));
            developer.setStatus(randomStatus());
            developer.setPricePerHour(generateRandomBigDecimalFromRange(
                    new BigDecimal(10),
                    new BigDecimal(10000)
            ));

            developers.add(developer);
        }

        return developers;
    }
}
