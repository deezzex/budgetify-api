package com.budgetify.service;

import com.budgetify.entity.User;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.util.Random;

class SessionServiceTest {
    @Test
    void testSessionGeneration() {
        User user = User.builder()
                .email("email@email.com")
                .id(1)
                .passportNumber("123fdf")
                .build();

        LocalDateTime now = LocalDateTime.now();

        int randomInt = new Random().nextInt();

        Integer.hashCode(randomInt);

        String randomString = generateRandomString();
        String randomString2 = generateRandomString();
        System.out.println(randomString);
        System.out.println(randomString2);
        System.out.println("User hash: " + user.hashCode());
        System.out.println("Now hash: " + now.hashCode());
        System.out.println("Int hash: " +  Integer.hashCode(randomInt));

        String sessionId = String.format("%s-%s-%s-%s-%s", user.hashCode(), randomString, now.hashCode(), randomString2, Integer.hashCode(randomInt));
        System.out.println(sessionId);
    }

    private static final String CHARACTERS = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
    private static final int LENGTH = 10;

    public static String generateRandomString() {
        StringBuilder sb = new StringBuilder(LENGTH);
        Random random = new Random();

        for (int i = 0; i < LENGTH; i++) {
            int randomIndex = random.nextInt(CHARACTERS.length());
            char randomChar = CHARACTERS.charAt(randomIndex);
            sb.append(randomChar);
        }

        return sb.toString();
    }

    @Test
    void testPasswordValidation(){

        String encryptPassword = PasswordEncryption.encryptPassword("admin");
        String decryptPassword = PasswordEncryption.decryptPassword("YWRtaW4=");

        System.out.println(encryptPassword);
        System.out.println(decryptPassword);
    }
}