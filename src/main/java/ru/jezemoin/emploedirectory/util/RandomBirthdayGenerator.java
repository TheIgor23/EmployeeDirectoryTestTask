package ru.jezemoin.emploedirectory.util;

import java.time.LocalDate;
import java.time.Period;
import java.util.Random;

public class RandomBirthdayGenerator {
    private static final Random random = new Random();

    public static LocalDate generateRandomBirthDate() {
        int minAge = 18;
        int maxAge = 60;
        int randomAge = random.nextInt(maxAge - minAge + 1) + minAge;

        LocalDate currentDate = LocalDate.now();
        return currentDate.minus(Period.ofYears(randomAge))
                .minusDays(random.nextInt(365));
    }
}
