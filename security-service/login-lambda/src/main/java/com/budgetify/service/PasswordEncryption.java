package com.budgetify.service;

import java.util.Base64;

public class PasswordEncryption {

    public static String encryptPassword(String password) {
        byte[] encodedBytes = Base64.getEncoder().encode(password.getBytes());
        return new String(encodedBytes);
    }

    public static String decryptPassword(String encryptedPassword) {
        byte[] decodedBytes = Base64.getDecoder().decode(encryptedPassword.getBytes());
        return new String(decodedBytes);
    }
}
