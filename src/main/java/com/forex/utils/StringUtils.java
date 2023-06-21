package com.forex.utils;

import java.util.Random;

public class StringUtils {
    public  static final String[] pathArrayPermitAll = new String[] { "/", "/webjars/**",
            "/forgot_password", "/reset_password", "/signup","/verify/**", "/process_register","/error","/category/**","/symbol/**"};

    public  static final String[] pathArrayView = new String[] { "/books/" };
    public  static final String[] pathArrayNew = new String[] { "/books/new" };
    public  static final String[] pathArrayDelete = new String[] { "/books/edit/**" };
    public  static final String[] pathArrayUpdate = new String[] { "/books/delete/**" };
    private static final String CHARACTERS = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
    public static String generateRandomString(int length) {
        Random random = new Random();
        StringBuilder sb = new StringBuilder();

        for (int i = 0; i < length; i++) {
            int index = random.nextInt(CHARACTERS.length());
            char randomChar = CHARACTERS.charAt(index);
            sb.append(randomChar);
        }

        return sb.toString();
    }
}
