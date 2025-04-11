package com.example.travelagency.faker;

import net.datafaker.Faker;

import java.util.Locale;

public class PersonDataUtil {

    private static final Faker faker = new Faker(new Locale("ko"));

    public static String generateName() {
        return faker.name().fullName();
    }

    public static String generateAddress() {
        return faker.address().fullAddress();
    }

    public static String generatePhoneNumber() {
        return faker.phoneNumber().phoneNumber();
    }

    public static String generateEmail() {
        return faker.internet().emailAddress();
    }

    public static int generateNumber(int min, int max) {
        return faker.number().numberBetween(min, max);
    }

    public static String generateLorem(int wordCount) {
        return faker.lorem().words(wordCount).toString();
    }

    // 추가적인 Faker 메서드 활용 가능
    // 예: faker.company().name(), faker.date().birthday() 등

    public static void main(String[] args) {
        System.out.println("이름: " + generateName());
        System.out.println("주소: " + generateAddress());
        System.out.println("전화번호: " + generatePhoneNumber());
        System.out.println("이메일: " + generateEmail());
        System.out.println("숫자 (1~100): " + generateNumber(1, 100));
        System.out.println("단어 5개: " + generateLorem(5));
    }
}
