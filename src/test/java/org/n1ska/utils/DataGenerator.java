package org.n1ska.utils;

import com.github.javafaker.Faker;

import java.time.LocalDateTime;
import java.util.Locale;
import java.util.Random;

public class DataGenerator {
    private static final Locale DefaultLocale = new Locale("ru");

    private DataGenerator() {
    }

    public static String generateCardNumberValid()
    {
        return "4444 4444 4444 4441";
    }

    public static String generateCardNumberNotValid()
    {
        return "4444 4444 4444 4442";
    }

    public static String generateCardPassCodeValid() {
        return String.valueOf(generateIntNum(100, 999));
    }

    public static String generateCardPassCodeNotValid() {
        return String.valueOf(generateIntNum(1, 99));
    }

    public static String generateCardPassCodeNotValidFormat() {
        return generateCardPassCodeNotValid().substring(1);
    }

    public static String generateCardHolderNameCyrilic() {
        Faker faker = new Faker(DefaultLocale);
        return faker.name().fullName();
    }

    public static String generateCardHolderNameEng() {
        Faker faker = new Faker(new Locale("en"));
        return faker.name().fullName();
    }

    public static String generateCardHolderNameCharacters() {
        return "%#$WSA5235'D'\"SDF";
    }

    public static String generateCardExpiryYearNoValid() {
        return String.valueOf(getCurrentYear() - generateIntNum(1, 5)).substring(2);
    }
    public static String generateCardExpiryYearValid() {
        return  String.valueOf(getCurrentYear() + generateIntNum(0, 5)).substring(2);
    }

    public static String generateCardExpiryYearNoValidFormat() {
        return generateCardExpiryYearNoValid().substring(1);
    }

    public static String generateCardExpiryMonthNoValid(){
        return "14";
    }

    public static String generateCardExpiryMonthNoValidFormat(){
        return "X";
    }

    public static String generateCardExpiryMonthValid() {
        return String.format("%02d", getCurrentMonth());
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
