package com.mct.practical.practical3.utils;

import java.util.regex.Pattern;

public class Validate {

    public static int getValidPage(String field, int def) {
        String pattern = "[1-9][0-9]*";
        return field != null && match(pattern, field) ? Integer.parseInt(field) : def;
    }

    private static boolean match(String pattern, String field) {
        return Pattern.compile(pattern, Pattern.CASE_INSENSITIVE).matcher(field).matches();
    }
}
