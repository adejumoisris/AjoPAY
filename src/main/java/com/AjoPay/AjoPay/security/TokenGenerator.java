package com.AjoPay.AjoPay.security;

import java.security.SecureRandom;

public class TokenGenerator {
    private static final String CHARACTERS = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";
    public static String generateVerificationToken(int length){
        SecureRandom random = new SecureRandom();
        StringBuilder token = new StringBuilder(length);

        for (int i = 0 ; i < length; i++){
            int randomIndex = random.nextInt(CHARACTERS.length());
            token.append(CHARACTERS.charAt(randomIndex));

        }
        return token.toString();
    }
}
