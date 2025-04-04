package com.example.travelagency.faker;

import net.datafaker.Faker;

import java.util.Locale;

public class VideoGameDataUtil {

    private static final Faker faker = new Faker(new Locale("ko"));

    public static String generateGameTitle() {
        return faker.videoGame().title();
    }

    public static String generateGenre() {
        return faker.videoGame().genre();
    }

    public static String generateTitle() { return faker.videoGame().title(); }

}
