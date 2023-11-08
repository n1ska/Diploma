package org.n1ska.utils;

import com.github.javafaker.Faker;

import java.time.LocalDateTime;
import java.util.Locale;
import java.util.Random;

public class DataGenerator {
    private static Locale DefaultLocale = new Locale("ru");
    private static int LastMonthOfYear = 12;

    private DataGenerator() {
    }

    public static int generateCardPassCodeValid() {
        return generateIntNum(100, 999);
    }

    public static int generateCardPassCodeNotValid() {
        return generateIntNum(1, 99);
    }

    public static String generateCardHolderName() {
        Faker faker = new Faker(DefaultLocale);
        return faker.artist().name();
    }

    public static String generateCardNo() {
        Faker faker = new Faker(DefaultLocale);
        return faker.business().creditCardNumber();
    }

    public static int generateCardExpiryYearNoValid() {
        return getCurrentYear() - generateIntNum(1, 10);
    }

    public static int generateCardExpiryMonthNoValid(int year) {
        if (year == getCurrentYear()) {
            var monthBeforeCurrentMonth = getCurrentMonth() - 1;
            return getCurrentMonth() - generateIntNum(1, monthBeforeCurrentMonth);
        }

        return generateIntNum(1, LastMonthOfYear);
    }

    public static int generateCardExpiryYearValid() {
        return getCurrentYear() + generateIntNum(0, 5);
    }

    public static int generateCardExpiryMonthValid(int year) {
        if (year == getCurrentYear()) {
            var leastMonthForEndYear = LastMonthOfYear - getCurrentMonth();
            return getCurrentMonth() + generateIntNum(1, leastMonthForEndYear);
        }

        return generateIntNum(1, LastMonthOfYear);
    }

    private static int generateIntNum(int start, int bound) {
        return new Random().nextInt(bound) + start;
    }

    private static int getCurrentYear() {
        return LocalDateTime.now().getYear();
    }

    private static int getCurrentMonth() {
        return LocalDateTime.now().getMonth().getValue();
    }

}
